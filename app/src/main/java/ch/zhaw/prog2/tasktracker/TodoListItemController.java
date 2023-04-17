package ch.zhaw.prog2.tasktracker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * This class is a controller for the ToDo list item.
 */
public class TodoListItemController {

    /**
     * The label for displaying the name of the ToDo.
     */
    @FXML
    private Label TodoNameLabel;

    /**
     * The button for deleting the ToDo.
     */
    @FXML
    private Button deleteTodoButton;

    /**
     * The label for displaying the time spent on the ToDo.
     */
    @FXML
    private Label timerLabel;

    /**
     * The button for resetting the timer for the ToDo.
     */
    @FXML
    private Button timerResetButton;

    /**
     * The HBox containing the start button for the timer.
     */
    @FXML
    private HBox timerStartButton;

    /**
     * The button for stopping the timer for the ToDo.
     */
    @FXML
    private Button timerStopButton;

    /**
     * Sets the text of the TodoNameLabel to the specified name.
     * 
     * @param name the name of the ToDo to be displayed
     */
    public void setTodoNameLabel(String name) {
        TodoNameLabel.setText(name);
    }

}
