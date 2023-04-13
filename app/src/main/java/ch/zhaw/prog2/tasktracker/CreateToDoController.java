package ch.zhaw.prog2.tasktracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * This class is responsible for controlling the "Create Todo" window of the
 * application.
 */
public class CreateToDoController {

    /**
     * Submit button to create a new ToDo.
     */
    @FXML
    private Button newTodoSubmitButton;

    /**
     * DatePicker to select a deadline for the ToDo.
     */
    @FXML
    private DatePicker todoDeadline;

    /**
     * TextArea to enter a description for the ToDo.
     */
    @FXML
    private TextArea todoDescription;

    /**
     * TextArea to enter tasks for the ToDo.
     */
    @FXML
    private TextArea todoTasks;

    /**
     * This method is called when the "Submit" button is clicked.
     * It creates a new ToDo with the entered information and closes the "Create
     * ToDo" window.
     * 
     * TODO: Implement the creation of a new Todo.
     * 
     * @param event The event triggered by clicking the "Submit" button.
     */
    @FXML
    void createTodo(ActionEvent event) {

        // TODO Create a new todo

        // Close window after creation
        Stage stage = (Stage) newTodoSubmitButton.getScene().getWindow();
        stage.close();
    }

}
