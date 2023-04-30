package ch.zhaw.prog2.tasktracker.task;

import ch.zhaw.prog2.tasktracker.oservables.ITaskEvent;
import ch.zhaw.prog2.tasktracker.oservables.ObservableTaskView;
import ch.zhaw.prog2.tasktracker.project.Project;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;

/**
 * This class is responsible for controlling the "Create task" window of the
 * application.
 */
public class CreateTaskController implements ObservableTaskView {

    /**
     * Submit button to create a new task.
     */
    @FXML
    private Button newTaskSubmitButton;

    /**
     * DatePicker to select a deadline for the task.
     */
    @FXML
    private DatePicker taskDeadline;

    /**
     * TextArea to enter a description for the task.
     */
    @FXML
    private TextArea taskDescription;

    /**
     * TextArea to enter tasks for the task.
     */
    @FXML
    private TextArea taskGoal;
    private LocalDate deadlineDate;
    /**
     * List of observers
     * Required for the implementation of Observable
     */
    private ArrayList<ITaskEvent> observers = new ArrayList<>();



    /**
     * Creates a new task based on user input.
     * Validates the input fields before creating the task.
     *
     * @param event The event that triggered the method.
     */
    @FXML
    private void createTask(ActionEvent event) {
        Date date = null;
        if (taskDeadline.getValue() != null) {
            deadlineDate = taskDeadline.getValue();
            date = Date.valueOf(deadlineDate);
        }

        boolean isDescriptionSet = checkDecriptionSet();
        boolean isTaskSet = checkTaskSet();
        boolean isDeadlineSet = checkDeadlineSet();

        if (isDescriptionSet && isTaskSet && isDeadlineSet) {
            notifyListeners(taskGoal.getText(), taskDescription.getText(), date);
            Stage stage = (Stage) newTaskSubmitButton.getScene().getWindow();
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
        if (taskDescription.getText().trim().isEmpty()) {
            this.taskDescription.appendText(emptyDecription);
            taskDescription.setStyle("-fx-border-color: red ;");
            return false;
        } else if (taskGoal.getText().equals(emptyDecription)) {
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
        if (taskGoal.getText().trim().isEmpty()) {
            this.taskGoal.appendText(emptyTask);
            return false;
        } else if (taskGoal.getText().equals(emptyTask)) {
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
        if (taskDeadline.getValue() == null || deadlineDate.isBefore(today)) {
            taskDeadline.getStyleClass().add("emptyField");
            return false;
        } else {
            taskDeadline.getStyleClass().removeAll("emptyField");
            return true;
        }
    }

    /**
     * Implementation of Observable
     * Add listener to the list of listeners to be notified
     * @param listener InvalidationListener to add to the list
     *            The listener to register
     */
    @Override
    public void addListener(ITaskEvent listener) {
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
    public void removeListener(ITaskEvent listener) {
        if(observers.contains(listener)){
            observers.remove(listener);
        }
    }

    /**
     * Required for the function of Observable
     * Loop though all listeners and notify them all
     */
    private void notifyListeners(String taskGoal, String taskDescription, Date deadlineDate){
        for(ITaskEvent listener : observers){
            listener.taskCreate(taskGoal, taskDescription, deadlineDate);
        }
    }
}
