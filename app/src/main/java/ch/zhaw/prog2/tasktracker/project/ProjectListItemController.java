package ch.zhaw.prog2.tasktracker.project;

import java.io.IOException;
import java.util.ArrayList;

import ch.zhaw.prog2.tasktracker.oservables.ProjectEvent;
import ch.zhaw.prog2.tasktracker.task.Task;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

/**
 * This class is a controller for the Project list item.
 */
public class ProjectListItemController implements Observable, ProjectEvent {

    /**
     * The label for displaying the name of the Project.
     */
    @FXML
    private Label ProjectNameLabel;

    /**
     * The button for deleting the Project.
     */
    @FXML
    private Button deleteProjectButton;

    /**
     * The button for opening the Project in a new window.
     */
    @FXML
    private Button openProjectButton;

    /**
     * Project this list item represents
     */
    private Project project;

    /**
     * Required for the function of Observable
     * List of observers of this Observable
     */
    private ArrayList<InvalidationListener> observers = new ArrayList<>();

    /**
     * Opens the Project in a new window.
     * 
     * Loads the scene graph from the FXML file, sets up the controller, and
     * displays the window.
     * 
     * @param event the ActionEvent that triggered this method
     */
    @FXML
    private void openProjectWindow(ActionEvent event) {
        try {
            // initialize and load the window scene graph from the fxml description
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project.fxml"));
            Pane rootPane = loader.load();

            // Add random Projects to scrollPane (FOR DEMONSTRATION ONLY!!)
            ProjectController projectController = loader.getController();

            // create a scene with the new the root-Node
            Scene scene = new Scene(rootPane);
            // create a new stage and show the new window
            Stage stageOfNewWindow = new Stage();
            stageOfNewWindow.getIcons().add(new Image(getClass().getResourceAsStream("/TaskTrackerIcon.png")));
            stageOfNewWindow.setTitle("Dein Projekt");
            stageOfNewWindow.setResizable(false);
            stageOfNewWindow.setScene(scene);
            projectController.setProject(this.project);
            projectController.addTasksToScrollPane();
            stageOfNewWindow.show();
        } catch (IOException e) {
            System.err.println("Error while loading FXML file: " + e.getMessage());
        }
    }

    /**
     * Event-handler for the delete button of the list item
     * Deletes the project
     * @param event the ActionEvent that triggered this method
     */
    @FXML
    void deleteProject(ActionEvent event) {
        notifyListeners();
        }

    /**
     * Sets the text of the ProjectNameLabel to the specified name.
     * 
     * @param name the name of the Project to be displayed
     */
    public void setProjectNameLabel(String name) {
        ProjectNameLabel.setText(name);
    }

    public void changeProjectNameLableColor(String color) {
        ProjectNameLabel.setStyle("-fx-text-fill:"+color+";");
    }

    /**
     * Set the Project this list item represents
     * @param project Project of this list item
     */
    public void setProject(Project project){
        if(project != null){
            this.project = project;
            this.project.addListener(this);
        }
    }
    /**
     * get the Project object this list item represents
     * @return Project object
     */
    public Project getProject(){return project;}
    /**
     * Implementation of Observable
     * Add listener to the list of listeners to be notified
     * @param listener InvalidationListener to add to the list
     *            The listener to register
     */
    @Override
    public void addListener(InvalidationListener listener) {
        if(listener != null){
            observers.add(listener);
        }
    }
    /**
     * Implementation of Observable
     * remove listener from the list of listeners to be notified
     * @param listener InvalidationListener to remove from the list
     *            The listener to remove
     */
    @Override
    public void removeListener(InvalidationListener listener) {
        if(observers.contains(listener)){
            observers.remove(listener);
        }
    }
    
    /**
     * Required for the function of Observable
     * Loop though all listeners and notify them all
     */
    private void notifyListeners(){
        for(InvalidationListener listener : observers){
            listener.invalidated(this);
        }
    }

    /**
    * This method is called when all tasks of the project have finished.
    * It checks if there are no tasks in the project and changes the color of the project name label to green if it is the case.
    */
    @Override
    public void allTasksFinished() {
        if(this.project.getTasks().size() == 0) {
            return;
        }
        this.changeProjectNameLableColor("green");
    }

    /**
    * This method is called when a task is deleted from the project. It does nothing.
    * @param t the Task that was deleted
    */
    @Override
    public void taskDeleted(Task t) {
    }

    /**
    * This method is called when a new task is created in the project. It does nothing.
    * @param t the Task that was created
    */
    @Override
    public void taskCreated(Task t) {
    }

    /**
    * This method is called when the state of a task in the project is changed.
    * It checks if there are open tasks in the project and changes the color of the project name label to black if it is the case.
    * @param t the Task whose state was changed
    */
    @Override
    public void taskStateChange(Task t) {
        if(this.project.getOpenTasks().size() != 0) {
            this.changeProjectNameLableColor("black");
        }
    }

}
