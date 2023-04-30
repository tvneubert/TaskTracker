package ch.zhaw.prog2.tasktracker;

import java.io.IOException;

import ch.zhaw.prog2.tasktracker.project.Project;
import ch.zhaw.prog2.tasktracker.project.ProjectListItemController;
import ch.zhaw.prog2.tasktracker.todo.DummyProjectOverview;
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
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The controller for the main window of the Task Tracker application.
 */
public class MainWindowController implements InvalidationListener {

    /**
     * The button for opening the create Project window.
     */
    @FXML
    private Button openCreateProjectWindowButton;

    /**
     * The VBox for displaying the list of Projects.
     */
    @FXML
    private VBox projectOverviewContent;

    @FXML
    private Label timestamp;

    @FXML 
    private Label timestampLabel;

    /**
     * Placeholder for a projectOverview for testing purposes
     * task Replace with proper ProjectOverview once implemented
     */
    private DummyProjectOverview projectOverview = new DummyProjectOverview();

        /**
     *
     this timeline is for summarizing the time of all tasks
     */

     private Timeline allProjectsTimeLine;

    /**
     * This method is called when the "open create Project window" button is
     * clicked.
     * It initializes and loads the window scene graph from the fxml description and
     * then creates a new stage with the new scene and shows it.
     * 
     * @param event the ActionEvent that triggered the method call
     */
    @FXML
    private void openCreateProjectWindow(ActionEvent event) {
        try {
            // initialize and load the window scene graph from the fxml description
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateProject.fxml"));
            Pane rootPane = loader.load();
            CreateProjectController controller = loader.getController();
            controller.addListener(this);
            controller.setRootProjectOverview(this.projectOverview);
            // create a scene with the new the root-Node
            Scene scene = new Scene(rootPane);
            // create a new stage and show the new window
            Stage stageOfNewWindow = new Stage();
            stageOfNewWindow.getIcons().add(new Image(getClass().getResourceAsStream("/TaskTrackerIcon.png")));
            stageOfNewWindow.setTitle("Erstelle ein Projekt");
            stageOfNewWindow.setResizable(false);
            stageOfNewWindow.setScene(scene);
            stageOfNewWindow.show();
        } catch (IOException e) {
            System.err.println("Error while loading FXML file: " + e.getMessage());
        }
    }

    private void startSummarizingTimer() {
        // we can only start the timeline if we do have a task object because it does contain the timer
        allProjectsTimeLine = new Timeline(new KeyFrame(Duration.millis(16.6), (ActionEvent e) -> {
            int timerSum = 0;
            for (Project project : projectOverview.getProjectList()) {
                timerSum += project.getTotalTaskTime();
            }
            timestamp.setText(TimeFormater.showTheTime(timerSum));
        }));
        allProjectsTimeLine.setCycleCount(Animation.INDEFINITE);
        allProjectsTimeLine.play();
    }

    /**
     * This method adds for each Project in the list a Project to the scrollPane
     * 
     * This method is here for testing and will need to be changed!
     */
    public void addProjectsToScrollPane() {
        for (Project project : projectOverview.getProjectList()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProjectListItem.fxml"));
                Pane projectPane = loader.load();

                ProjectListItemController projectListItemController = loader.getController();
                projectListItemController.addListener(this);
                projectListItemController.setProjectNameLabel(project.getName());
                projectListItemController.setProject(project);
                this.startSummarizingTimer();

                projectOverviewContent.getChildren().add(projectPane);
            } catch (IOException e) {
                System.err.println("Error while loading FXML file: " + e.getMessage());
            }
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
        if(observable instanceof ProjectListItemController){
            Project project = ((ProjectListItemController) observable).getProject();
            projectOverview.removeProject(project);
        }
        projectOverviewContent.getChildren().clear();
        addProjectsToScrollPane();
    }
}
