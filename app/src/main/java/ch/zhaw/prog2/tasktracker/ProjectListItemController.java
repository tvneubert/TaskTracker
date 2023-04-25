package ch.zhaw.prog2.tasktracker;

import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

/**
 * This class is a controller for the Project list item.
 */
public class ProjectListItemController implements Observable {

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
    private Project project;
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
            projectController.setProject(project);
            projectController.addToDosToScrollPane();

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
    public void setProject(Project project){
        if(project != null){
            this.project = project;
        }
    }
    public Project getProject(){return project;}
    @Override
    public void addListener(InvalidationListener listener) {
        if(listener != null){
            observers.add(listener);
        }
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        if(observers.contains(listener)){
            observers.remove(listener);
        }
    }
    private void notifyListeners(){
        for(InvalidationListener listener : observers){
            listener.invalidated(this);
        }
    }

}
