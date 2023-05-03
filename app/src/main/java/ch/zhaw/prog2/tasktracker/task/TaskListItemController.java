package ch.zhaw.prog2.tasktracker.task;

import ch.zhaw.prog2.tasktracker.TimeFormater;
import ch.zhaw.prog2.tasktracker.task.Task.TaskStatus;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * This class is a controller for the task list item.
 */
public class TaskListItemController {

    /**
     * The task object that is represented by this list item.
     */
    private Task taskListItem;

    /**
     * The label for displaying the name of the task.
     */
    @FXML
    private Label taskNameLabel;

    /**
     * The button for deleting the task.
     */
    @FXML
    private Button deletetaskButton;

    /**
     * The label for displaying the time spent on the task.
     */
    @FXML
    private Label timerLabel;

    /**
     * The button for resetting the timer for the task.
     */
    @FXML
    private Button timerResetButton;

    /**
     * The Button for starting the timer for the task.
     */
    @FXML
    private Button timerStartButton;

    /**
     * The button for stopping the timer for the task.
     */
    @FXML
    private Button timerStopButton;

    /**
     * The timeline for the timer.
     * Otherwise it would not look that nice in the UI.
     */
    private Timeline tl;

    /**
     * This method is called when the task is set and initializes the timeline for
     * timer that is displayed in the task list item.
     * 
     * @param task
     */
    public void setTaskObject(Task task) {
        this.taskListItem = task;
        // we can only start the timeline if we do have a task object because it does
        // contain the timer
        tl = new Timeline(new KeyFrame(Duration.millis(16.6), (ActionEvent e) -> {
            timerLabel.setText(TimeFormater.showTheTime(this.taskListItem.getTimeTracker().getCurrentTime()));
        }));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();

        taskNameLabel.setText(this.taskListItem.getDescription());
    }

    /**
     * This is the constructor for the controller
     */
    public TaskListItemController() {
    }

    /**
     * Toggles the status of a task item between active and finished.
     * If the task was active, it will be set to finished and its name will be
     * striked-through and displayed in gray. The timer reset button will be styled
     * in green.
     * If the task was finished, it will be set to active and its name will be
     * displayed normally. The timer reset button will be styled in black.
     */
    @FXML
    public void toggleTaskState() {
        if (taskListItem.getTaskStatus().equals(TaskStatus.ACTIVE)) {
            taskListItem.setTaskStatus(TaskStatus.FINISHED);
            for (Node n : taskNameLabel.getChildrenUnmodifiable()) {
                n.setStyle("-fx-strikethrough: true;");
            }
            taskNameLabel.setStyle("-fx-text-fill: #535151;");
            timerResetButton.setStyle("-fx-text-fill: green;");
        } else {
            taskListItem.setTaskStatus(TaskStatus.ACTIVE);
            for (Node n : taskNameLabel.getChildrenUnmodifiable()) {
                n.setStyle("-fx-strikethrough: false;");
            }
            taskNameLabel.setStyle("-fx-text-fill: #000;");
            timerResetButton.setStyle("-fx-text-fill: #000;");
        }
    }

    /**
     * Starts or pauses the timer for the task item.
     * If the timer is stopped, it will start and the button will display a pause
     * icon.
     * If the timer is running, it will be paused and the button will display a play
     * icon.
     * If the timer is paused, it will resume and the button will display a pause
     * icon.
     */
    @FXML
    public void timerButton() {
        System.out.println(this.taskListItem.getTimeTracker().getCurrentTime());
        if (this.taskListItem.getTimeTracker().getCurrentTime() == 0) {
            this.taskListItem.getTimeTracker().start();
            this.timerStartButton.setText("⏸");
        } else if (this.taskListItem.getTimeTracker().isRunning()) {
            this.taskListItem.getTimeTracker().pause();
            this.timerStartButton.setText("▶");
        } else {
            this.taskListItem.getTimeTracker().resume();
            this.timerStartButton.setText("⏸");
        }
    }

    /**
     * Event-handler for the delete button of the list item
     * Deletes the Task
     * 
     * @param event the ActionEvent that triggered this method
     */
    @FXML
    void deleteTask(ActionEvent event) {
        this.taskListItem.wantsDelete();
    }

    /**
     * get the Task object this list item represents
     * 
     * @return Task object
     */
    public Task getTaskListItem() {
        return taskListItem;
    }
}
