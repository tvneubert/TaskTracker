package ch.zhaw.prog2.tasktracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateProjectController {

    @FXML
    private Button newProjectSubmitButton;

    @FXML
    private TextField newProjectTextField;

    @FXML
    void createProject(ActionEvent event) {
        
        // TODO Create a new project

        // Close window after creation
        Stage stage = (Stage) newProjectSubmitButton.getScene().getWindow();
        stage.close();
    }

}
