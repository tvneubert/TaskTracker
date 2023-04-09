package ch.zhaw.prog2.tasktracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class CreateToDoController {

    @FXML
    private Button newTodoSubmitButton;

    @FXML
    private DatePicker todoDeadline;

    @FXML
    private TextArea todoDescription;

    @FXML
    private TextArea todoTasks;

    @FXML
    void createTodo(ActionEvent event) {
        
        // TODO Create a new todo

        // Close window after creation
        Stage stage = (Stage) newTodoSubmitButton.getScene().getWindow();
        stage.close();
    }

}
