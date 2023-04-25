package ch.zhaw.prog2.tasktracker;

import java.io.IOException;

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
 * This class is a controller for the main window of the ToDo application.
 */
public class ProjectController implements InvalidationListener {

    /**
     * The button for opening the create ToDo window.
     */
    @FXML
    private Button openCreateTodoWindowButton;

    /**
     * The label for displaying the added up time of all todos of the project.
     */
    @FXML
    private Label timeLabel;

    /**
     * The VBox for displaying the list of ToDos.
     */
    @FXML
    private VBox todoOverviewContent;


    /**
     *
     this timeline is for summarizing the time of all todos
     */

    private Timeline projectTimeLine;

    /**
     * Project this window is displaying
     */
    private Project project;

    /**
     * This method is called when the "open create ToDo window" button is clicked.
     * It initializes and loads the window scene graph from the fxml description and
     * then creates a new stage with the new scene and shows it.
     * 
     * @param event the ActionEvent that triggered the method call
     */
    @FXML
    void openCreateTodoWindow(ActionEvent event) {
        try {
            // initialize and load the window scene graph from the fxml description
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateToDo.fxml"));
            Pane rootPane = loader.load();
            CreateToDoController controller = loader.getController();
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
     * 
     * This method is here for testing and will need to be changed!
     */
    public void addToDosToScrollPane() {
        for(Task task : project.getOpenTasks()){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/TodoListItem.fxml"));
                Pane todoPane = loader.load();

                TodoListItemController todoListItemController = loader.getController();
                todoListItemController.addListener(this);
                todoListItemController.setTodoObject(task);

                todoOverviewContent.getChildren().add(todoPane);
            } catch (IOException e) {
                System.err.println("Error while loading FXML file: " + e.getMessage());
            }
        }
        this.startSummarizingTimer();

    }

    /**
     * This method starts the timeline for summarizing the time of all todos.
     */
    private void startSummarizingTimer() {
        // we can only start the timeline if we do have a todo object because it does contain the timer
        projectTimeLine = new Timeline(new KeyFrame(Duration.millis(16.6), (ActionEvent e) -> {
            int timerSum = 0;
            for (Task todo : project.getTasks()) {
                timerSum += todo.getTimeTracker().getCurrentTime();
            }
            timeLabel.setText(TimeFormater.showTheTime(timerSum));
        }));
        projectTimeLine.setCycleCount(Animation.INDEFINITE);
        projectTimeLine.play();
    }

    /**
     * Set the project that this Window is controlling
     * @param project Project to be used
     */
    public void setProject(Project project){
        if(project != null){
            this.project = project;
        }
    }

    /**
     * Implementation of the InvalidationListener
     * Processes the invalidation event from the Observable
     * @param observable the Observable that triggered the invalidation event
     *            The {@code Observable} that became invalid
     */
    @Override
    public void invalidated(Observable observable) {
        if(observable instanceof TodoListItemController){
            Task task = ((TodoListItemController) observable).getTaskListItem();
            project.removeTask(task);
        }
        todoOverviewContent.getChildren().clear();
        addToDosToScrollPane();
    }
}
