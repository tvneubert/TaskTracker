package ch.zhaw.prog2.tasktracker.project;

import java.io.IOException;

import ch.zhaw.prog2.tasktracker.TimeFormater;
import ch.zhaw.prog2.tasktracker.task.CreateTaskController;
import ch.zhaw.prog2.tasktracker.task.Task;
import ch.zhaw.prog2.tasktracker.task.TaskListItemController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class is a controller for the main window of the task application.
 */
public class ProjectController implements InvalidationListener {

    /**
     * The button for opening the create task window.
     */
    @FXML
    private Button openCreateTaskWindowButton;

    /**
     * The label for displaying the added up time of all tasks of the project.
     */
    @FXML
    private Label timeLabel;

    /**
     * The VBox for displaying the list of tasks.
     */
    @FXML
    private VBox taskOverviewContent;


    /**
     * this timeline is for summarizing the time of all tasks
     */

    private Timeline projectTimeLine;

    /**
     * Project this window is displaying
     */
    private Project project;

    /**
     * This method is called when the "open create task window" button is clicked.
     * It initializes and loads the window scene graph from the fxml description and
     * then creates a new stage with the new scene and shows it.
     *
     * @param event the ActionEvent that triggered the method call
     */
    @FXML
    void openCreateTaskWindow(ActionEvent event) {
        try {
            // initialize and load the window scene graph from the fxml description
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateTask.fxml"));
            Pane rootPane = loader.load();
            CreateTaskController controller = loader.getController();
            controller.setRootProject(project);
            controller.addListener(this);
            // create a scene with the new the root-Node
            Scene scene = new Scene(rootPane);
            // create a new stage and show the new window
            Stage stageOfNewWindow = new Stage();
            stageOfNewWindow.setScene(scene);
            stageOfNewWindow.show();
        } catch (IOException e) {
            System.err.println("Error while loading FXML file: " + e.getMessage());
        }
    }


    /**
     * This method adds for each Project in the list a Project to the scrollPane
     * <p>
     * This method is here for testing and will need to be changed!
     */
    public void addTasksToScrollPane() {
        for (Task task : project.getOpenTasks()) {
            loadFxml(task);
        }
        this.startSummarizingTimer();

    }

    /**
     * Filters the task overview content by effort, clearing the current content and loading only the open tasks with effort.
     * @param event the event triggering the filter
     */
    @FXML
    private void filterEffort(ActionEvent event) {
        taskOverviewContent.getChildren().clear();
        for (Task task : project.getOpenTasksEffort()) {
            loadFxml(task);
        }
        this.startSummarizingTimer();
    }

    /**
     * Filters the task overview content by date, clearing the current content and loading only the open tasks with a due date.
     * @param event the event triggering the filter
     */
    @FXML
    private void filterDate(ActionEvent event) {
        taskOverviewContent.getChildren().clear();
        for (Task task : project.getOpenTasksDate()) {
            loadFxml(task);
        }
        this.startSummarizingTimer();
    }

    /**
     * Loads the FXML file for a task list item and adds it to the task overview content.
     * @param task the task object to be loaded
     */
    private void loadFxml(Task task) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TaskListItem.fxml"));
            Pane taskPane = loader.load();

            TaskListItemController taskListItemController = loader.getController();
            taskListItemController.addListener(this);
            taskListItemController.setTaskObject(task);

            taskOverviewContent.getChildren().add(taskPane);
        } catch (IOException e) {
            System.err.println("Error while loading FXML file: " + e.getMessage());
        }
    }

    /**
     * This method starts the timeline for summarizing the time of all tasks.
     */
    private void startSummarizingTimer() {
        // we can only start the timeline if we do have a task object because it does contain the timer
        projectTimeLine = new Timeline(new KeyFrame(Duration.millis(16.6), (ActionEvent e) -> {
            int timerSum = 0;
            for (Task task : project.getTasks()) {
                timerSum += task.getTimeTracker().getCurrentTime();
            }
            timeLabel.setText(TimeFormater.showTheTime(timerSum));
        }));
        projectTimeLine.setCycleCount(Animation.INDEFINITE);
        projectTimeLine.play();
    }

    /**
     * Set the project that this Window is controlling
     *
     * @param project Project to be used
     */
    public void setProject(Project project) {
        if (project != null) {
            this.project = project;
        }
    }

    /**
     * Implementation of the InvalidationListener
     * Processes the invalidation event from the Observable
     *
     * @param observable the Observable that triggered the invalidation event
     *                   The {@code Observable} that became invalid
     */
    @Override
    public void invalidated(Observable observable) {
        if (observable instanceof TaskListItemController) {
            Task task = ((TaskListItemController) observable).getTaskListItem();
            project.removeTask(task);
        }
        taskOverviewContent.getChildren().clear();
        addTasksToScrollPane();
    }
}
