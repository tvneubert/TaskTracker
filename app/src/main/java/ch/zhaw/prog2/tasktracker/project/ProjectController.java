package ch.zhaw.prog2.tasktracker.project;

import java.io.IOException;
import java.util.Date;

import ch.zhaw.prog2.tasktracker.TimeFormater;
import ch.zhaw.prog2.tasktracker.oservables.ITaskEvent;
import ch.zhaw.prog2.tasktracker.task.CreateTaskController;
import ch.zhaw.prog2.tasktracker.task.Task;
import ch.zhaw.prog2.tasktracker.task.TaskListItemController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
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
public class ProjectController implements ITaskEvent {

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

    @FXML
    private ChoiceBox<String> filter;

    @FXML
    private Label projectName;

    /**
     * The VBox for displaying the list of tasks.
     */
    @FXML
    private VBox taskOverviewContent;

    @FXML
    private Label totalTimeLabel;

    /**
     *
     this timeline is for summarizing the time of all tasks
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
            controller.addListener(this);
            // create a scene with the new the root-Node
            Scene scene = new Scene(rootPane);
            // create a new stage and show the new window
            Stage stageOfNewWindow = new Stage();
            stageOfNewWindow.getIcons().add(new Image(getClass().getResourceAsStream("/TaskTrackerIcon.png")));
            stageOfNewWindow.setTitle("Erstelle einen Task");
            stageOfNewWindow.setResizable(false);
            stageOfNewWindow.setScene(scene);
            stageOfNewWindow.show();
        } catch (IOException e) {
            System.err.println("Error while loading FXML file: " + e.getMessage());
        }
    }

    /**
     * This method adds for each Project in the list a Project to the scrollPane
     * 
     * This method is here for testing and will need to be changed!
     */
    public void addTasksToScrollPane() {
        for(Task task : project.getOpenTasks()){
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
        this.startSummarizingTimer();

    }


    private void setProjectTitle(String title) {
        this.projectName.setText(title);
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
     * Set the project that this Window is controlling and the name of the Title and the Filters for the Project
     * @param project Project to be used
     */
    public void setProject(Project project){
        if(project != null){
            this.project = project;
            this.setProjectTitle(project.getName());
            

            ObservableList<String> filterOptions = FXCollections.observableArrayList(
                "Offen",
                "Fertig");
            filter.setItems(filterOptions);
        }
    }

    @Override
    public void taskDeleteClick(Task taskListItem) {
        project.removeTask(taskListItem);

        taskOverviewContent.getChildren().clear();
        addTasksToScrollPane();
    }

    @Override
    public void taskCreate(String taskGoal, String taskDescription, Date deadlineDate) {
        Task task = new Task(taskDescription, taskGoal, deadlineDate);
        this.project.addTask(task);

        taskOverviewContent.getChildren().clear();
        addTasksToScrollPane();
    }
}
