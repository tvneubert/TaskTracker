package ch.zhaw.prog2.tasktracker;

import java.io.IOException;

import ch.zhaw.prog2.tasktracker.Observerable.ProjectEventListener;
import ch.zhaw.prog2.tasktracker.Observerable.ProjectOverviewEventListener;
import ch.zhaw.prog2.tasktracker.project.CreateProjectController;
import ch.zhaw.prog2.tasktracker.project.Project;
import ch.zhaw.prog2.tasktracker.project.ProjectListItemController;
import ch.zhaw.prog2.tasktracker.projectmodels.JSONProjectOverview;
import ch.zhaw.prog2.tasktracker.projectmodels.ProjectOverview;
import ch.zhaw.prog2.tasktracker.task.Task;
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
public class MainWindowController implements InvalidationListener, ProjectEventListener, ProjectOverviewEventListener {

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
    private ProjectOverview projectOverview;

        /**
     *
     this timeline is for summarizing the time of all tasks
     */

     private Timeline allProjectsTimeLine;

     public MainWindowController() {
        super();
        try {
            JSONProjectOverview jpo = new JSONProjectOverview();
            jpo.addListener(this);
            projectOverview = jpo;

            for(Project p : jpo.getProjectList()) {
                p.addListener(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
     }

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
            e.printStackTrace();
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
                e.printStackTrace();
            }
        }
    }

    protected ProjectOverview getProjectOverview() {
        return this.projectOverview;
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

    @Override
    public void allTasksFinished() {}

    @Override
    public void taskStateChange(Task t) {
        this.projectOverview.save();
    }

    @Override
    public void taskDeleted(Task t) {
        this.projectOverview.save();
    }

    @Override
    public void taskCreated(Task t) {
        this.projectOverview.save();
    }

    @Override
    public void projectCreated(Project p) {
        System.out.println("Project has been created");
        p.addListener(this);
    }

    @Override
    public void projectDeleted(Project p) {
        p.removeListener(this);
    }
}
