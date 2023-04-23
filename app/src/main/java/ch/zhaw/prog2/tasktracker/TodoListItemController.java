package ch.zhaw.prog2.tasktracker;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * This class is a controller for the ToDo list item.
 */
public class TodoListItemController {

    /**
     * The ToDo object that is represented by this list item.
     */
    private Task taskListItem;
    //private DummyTodoDataObject tdo;          <-- Old placeholder

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
     * The Button for starting the timer for the ToDo.
     */
    @FXML
    private Button timerStartButton;

    /**
     * The button for stopping the timer for the ToDo.
     */
    @FXML
    private Button timerStopButton;

    /**
     * The timeline for the timer.
     * Otherwise it would not look that nice in the UI.
     */
    private Timeline tl;

    /**
     * This method is called when the ToDo is set and initializes the timeline for
     * timer that is displayed in the ToDo list item.
     * @param task
     */
    public void setTodoObject(Task task) {
        this.taskListItem = task;
        // we can only start the timeline if we do have a todo object because it does contain the timer
        tl = new Timeline(new KeyFrame(Duration.millis(16.6), (ActionEvent e) -> {
            timerLabel.setText(TimeFormater.showTheTime(this.taskListItem.getTimeTracker().getCurrentTime()));
        }));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
        
        TodoNameLabel.setText(this.taskListItem.getDescription());
    }

    /**
     * This is the constructor for the controller
     */
    public TodoListItemController() {
    }

    /**
     * This is the controller event for the startButton
     */
    @FXML
    public void startTimer() {
        this.taskListItem.getTimeTracker().start();
    }

    /**
     * This is the controller event for the pauseButton
     */
    @FXML
    public void pauseTimer() {
        this.taskListItem.getTimeTracker().pause();
    }

    /**
     * This is the controller event for the resumeButton
     */
    @FXML
    public void resumeTimer() {
        this.taskListItem.getTimeTracker().resume();
    }

}
