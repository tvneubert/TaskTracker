package ch.zhaw.prog2.tasktracker;

import java.io.IOException;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProjectController {

    @FXML
    private Button openCreateTodoWindowButton;

    @FXML
    private Label timeLabel;

    @FXML
    private VBox todoOverviewContent;

    @FXML
    void openCreateTodoWindow(ActionEvent event) {
        try {
            // initialize and load the window scene graph from the fxml description
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateToDo.fxml"));
            Pane rootPane = loader.load();
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
        HashMap<Integer, String> todos = new HashMap<>();
        todos.put(1, "ToDo 1");
        todos.put(2, "ToDo 2");
        todos.put(3, "ToDo 3");
        todos.put(4, "ToDo 4");
        todos.put(5, "ToDo 5");
        todos.put(6, "ToDo 6");
        todos.put(7, "ToDo 7");

        for (String todo : todos.values()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/TodoListItem.fxml"));
                Pane todoPane = loader.load();

                TodoListItemController todoListItemController = loader.getController();
                todoListItemController.setTodoNameLabel(todo);

                todoOverviewContent.getChildren().add(todoPane);
            } catch (IOException e) {
                System.err.println("Error while loading FXML file: " + e.getMessage());
            }
        }
    }

}
