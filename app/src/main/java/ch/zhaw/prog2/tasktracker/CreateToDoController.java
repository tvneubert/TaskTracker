package ch.zhaw.prog2.tasktracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.sql.Date;

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
    private TextArea todoGoal;
    private Task task;
    private LocalDate deadlineDate;
    private Date date;
    private Project rootProject;

    /**
     * Creates a new task based on user input.
     * Validates the input fields before creating the task.
     *
     * @param event The event that triggered the method.
     */
    @FXML
    private void createTodo(ActionEvent event) {
        if (todoDeadline.getValue() != null) {
            deadlineDate = todoDeadline.getValue();
            date = Date.valueOf(deadlineDate);
        }
        if (checkDecriptionSet() && checkTaskSet() && checkDeadlineSet()) {
            task = new Task(todoDescription.getText(), todoGoal.getText(), date);
            rootProject.addTask(task);
            Stage stage = (Stage) newTodoSubmitButton.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Checks if a description has been entered.
     * If the field is empty, an error message is displayed.
     *
     * @return True if a description has been entered, false otherwise.
     */
    private boolean checkDecriptionSet() {
        String emptyDecription = "Bitte gib eine Beschreibung ein!";
        if (todoDescription.getText().trim().isEmpty()) {
            this.todoDescription.appendText(emptyDecription);
            return false;
        } else if (todoGoal.getText().equals(emptyDecription)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if a task has been entered.
     * If the field is empty, an error message is displayed.
     *
     * @return True if a task has been entered, false otherwise.
     */
    private boolean checkTaskSet() {
        String emptyTask = "Bitte gib eine Beschreibung ein!";
        if (todoGoal.getText().trim().isEmpty()) {
            this.todoGoal.appendText(emptyTask);
            return false;
        } else if (todoGoal.getText().equals(emptyTask)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if a deadline has been set and if it is in the future.
     * If the deadline is missing or in the past, an error message is displayed.
     *
     * @return True if a valid deadline has been set, false otherwise.
     */
    private boolean checkDeadlineSet() {
        LocalDate today = LocalDate.now();
        if (todoDeadline.getValue() == null || deadlineDate.isBefore(today)) {
            todoDeadline.setStyle("-fx-background-color: #e50808;");
            return false;
        } else {
            return true;
        }
    }
    public void setRootProject(Project project){
        if(project != null){
            rootProject = project;
        }
    }
}
