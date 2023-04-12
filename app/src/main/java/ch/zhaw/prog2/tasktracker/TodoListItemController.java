package ch.zhaw.prog2.tasktracker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TodoListItemController {

    @FXML
    private Label TodoNameLabel;

    @FXML
    private Button deleteTodoButton;

    @FXML
    private Label timerLabel;

    @FXML
    private Button timerResetButton;

    @FXML
    private HBox timerStartButton;

    @FXML
    private Button timerStopButton;

    public void setTodoNameLabel(String name) {
        TodoNameLabel.setText(name);
    }

}

