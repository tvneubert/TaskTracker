package ch.zhaw.prog2.tasktracker;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class ProjectListItemController {

    @FXML
    private Label ProjectNameLabel;

    @FXML
    private Button deleteProjectButton;

    @FXML
    private Button openProjectButton;

    @FXML
    private void openProjectWindow(ActionEvent event) {
        try {
            // initialize and load the window scene graph from the fxml description
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Project.fxml"));
            Pane rootPane = loader.load();

            // Add random Projects to scrollPane (FOR DEMONSTRATION ONLY!!)
            ProjectController projectController = loader.getController();
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

    public void setProjectNameLabel(String name) {
        ProjectNameLabel.setText(name);
    }

}
