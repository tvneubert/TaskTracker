package ch.zhaw.prog2.tasktracker.project;

import java.io.IOException;

import ch.zhaw.prog2.tasktracker.App;
import ch.zhaw.prog2.tasktracker.TimeFormater;
import ch.zhaw.prog2.tasktracker.Observerable.ProjectEventListener;
import ch.zhaw.prog2.tasktracker.task.CreateTaskController;
import ch.zhaw.prog2.tasktracker.task.Task;
import ch.zhaw.prog2.tasktracker.task.TaskListItemController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class is a controller for the main window of the task application.
 */
public class ProjectController implements ProjectEventListener {

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
     * This is the UI Emement for the Filter, you can coose between "Fertig" and
     * "Offen"
     */
    @FXML
    private ChoiceBox<String> filter;

    /**
     * This UI Element stes the Name/Title of the Project
     */
    @FXML
    private Label projectName;

    /**
     * The VBox for displaying the list of tasks.
     */
    @FXML
    private VBox taskOverviewContent;

    /**
     * This label shows the total time od the TimeTracker
     */
    @FXML
    private Label totalTimeLabel;

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
            controller.setProject(this.project);
            // create a scene with the new the root-Node
            Scene scene = new Scene(rootPane);
            // create a new stage and show the new window
            Stage stageOfNewWindow = new Stage();
            // This is some customazation of the window, with logo icon and defines that the
            // windows cant be changed in size
            stageOfNewWindow.getIcons().add(new Image(getClass().getResourceAsStream("/TaskTrackerIcon.png")));
            stageOfNewWindow.setTitle("Erstelle einen Task");
            stageOfNewWindow.setResizable(false);
            stageOfNewWindow.setScene(scene);
            stageOfNewWindow.show();
        } catch (IOException e) {
            System.err.println("Error while loading FXML file: " + e.getMessage());
        }
    }

    public void allTasks() {
        taskOverviewContent.getChildren().clear();
        for (Task task : project.getAllTasks()) {
            loadFxml(task);
        }
        this.startSummarizingTimer();
    }

    /**
     * This method adds for each Project in the list a Project to the scrollPane
     */
    public void filtertOpenTasks() {
        taskOverviewContent.getChildren().clear();
        for (Task task : project.getOpenTasks()) {
            loadFxml(task);
        }
        this.startSummarizingTimer();
    }

    /**
     * Clears the task overview content and loads the FXML representation of all closed tasks in the project.
     * Starts the timer for summarizing the project status.
     */
    public void filtertCloseTasks() {
        taskOverviewContent.getChildren().clear();
        for (Task task : project.getClosedTasks()) {
            loadFxml(task);
        }
        this.startSummarizingTimer();
    }

    /**
     * Filters the task overview content by effort, clearing the current content and loading only the open tasks with effort.
     */
    private void filterEffortTasks() {
        taskOverviewContent.getChildren().clear();
        for (Task task : project.getOpenTasksEffort()) {
            loadFxml(task);
        }
        this.startSummarizingTimer();
    }

    /**
     * Filters the task overview content by date, clearing the current content and loading only the open tasks with a due date.
     */
    private void filterDateTasks() {
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

            taskOverviewContent.getChildren().add(taskPane);
            taskListItemController.setTaskObject(task);
        } catch (IOException e) {
            System.err.println("Error while loading FXML file: " + e.getMessage());
        }

    }

    /**
     * Here the title of the projectwindow is been set
     *
     * @param title the title you want to set to your project
     */
    private void setProjectTitle(String title) {
        this.projectName.setText(title);
    }

    /**
     * This method starts the timeline for summarizing the time of all tasks.
     */
    private void startSummarizingTimer() {
        // create a timeline that is able to update the projects time with a sum of each tasks time
        projectTimeLine = new Timeline(new KeyFrame(Duration.millis(App.timerRefreshRate), (ActionEvent e) -> {
            int timerSum = 0;
            for (Task task : project.getTasks()) {
                timerSum += task.getTimeTracker().getCurrentTime();
            }
            String formattedTime = TimeFormater.formatTimerTime(timerSum);
            timeLabel.setText(formattedTime);
        }));
        projectTimeLine.setCycleCount(Animation.INDEFINITE);
        projectTimeLine.play();
    }

    /**
     * Set the project that this Window is controlling and the name of the Title and
     * the Filters for the Project
     *
     * @param project Project to be used
     */
    public void setProject(Project project) {
        if (project != null) {
            this.project = project;
            this.setProjectTitle(project.getName());
            this.project.addListener(this);
            setFilterOptions();
            setFilterListener();
        }
    }

    /**
     * Sets the filter options for the filter combo box.
     */
    private void setFilterOptions() {
        ObservableList<String> filterOptions = FXCollections.observableArrayList(
                "Alle Tasks",
                "Offen",
                "Fertig",
                "Deadline (Offen)",
                "Aufwand (Offen)");
        filter.setItems(filterOptions);
        filter.setValue("Offen");
    }

    /**
     * Sets the listener for the filter combo box.
     */
    private void setFilterListener() {
        filter.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case "Fertig":
                    filtertCloseTasks();
                    break;
                case "Alle Tasks":
                    allTasks();
                    break;
                case "Offen":
                    filtertOpenTasks();
                    break;
                case "Deadline (Offen)":
                    filterDateTasks();
                    break;
                case "Aufwand (Offen)":
                    filterEffortTasks();
                    break;
                default:
                    project.getOpenTasks();
            }
        });
    }

    /**
     * This function refreshes the list view
     * It deletes everything after a new task was added or deleted and loads the
     * view new
     * Implementation of the InvalidationListener
     * Processes the invalidation event from the Observable
     */
    private void reloadTaskList() {
        taskOverviewContent.getChildren().clear();
        filtertOpenTasks();
    }

    /**
     * This method is called when all tasks in the project have been finished.
     * This method is part of the TaskListener interface.
     */
    @Override
    public void allTasksFinished() {
    }

    /**
     * This method is called when a task has been deleted from the project.
     * It reloads the task list in the UI to reflect the updated project.
     * This method is part of the TaskListener interface.
     *
     * @param t the Task object that was deleted
     */
    @Override
    public void taskDeleted(Task t) {
        this.reloadTaskList();
    }

    /**
     * This method is called when a new task has been created in the project.
     * It reloads the task list in the UI to reflect the updated project.
     * This method is part of the TaskListener interface.
     *
     * @param t the Task object that was created
     */
    @Override
    public void taskCreated(Task t) {
        this.reloadTaskList();
    }

    /**
     * This method is called when the state of a task in the project has changed.
     * This method is part of the TaskListener interface.
     *
     * @param t the Task object whose state has changed
     */
    @Override
    public void taskStateChange(Task t) {
    }
}