package ch.zhaw.prog2.tasktracker.task;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import ch.zhaw.prog2.tasktracker.TimeFormater;
import ch.zhaw.prog2.tasktracker.task.Task.TaskStatus;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * This class is a controller for the task list item.
 */
public class TaskListItemController {

    /**
     * The task object that is represented by this list item.
     */
    private Task taskListItem;

    /*
     * Shows the given deadline
     */
    @FXML
    private Label deadline;

    /*
     * Shows the desciption of the Task
     */
    @FXML
    private Label goalLabel;

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
    private Button taskDoneButton;

    /**
     * The Button for starting the timer for the task.
     */
    @FXML
    private Button timerStartButton;

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
        SimpleDateFormat formatter = new SimpleDateFormat("dd. MM. yyyy");
        String _deadline = formatter.format(this.taskListItem.getDeadline());
        deadline.setText(_deadline);
        goalLabel.setText(this.taskListItem.getGoal());
        Platform.runLater(() -> {
            updateRednerSettings();
        });
    }

    /**
     * This is the constructor for the controller
     */
    public TaskListItemController() {
    }

    /**
     * Updates the rendersettings, so if we reload the programm the changes reload
     * as well
     */
    private void updateRednerSettings() {
        if (taskListItem.getTaskStatus().equals(TaskStatus.FINISHED)) {
            disableText();
        } else {
            enableText();
        }
        if (this.taskListItem.getTimeTracker().isRunning()) {
            this.timerStartButton.setText("⏸");
        } else {
            this.timerStartButton.setText("▶");
        }
    }

    /**
     * If task is moved back to the state of not being done, the text is back to
     * normal as well
     */
    private void enableText() {
        taskNameLabel.setStyle("-fx-text-fill: #000;");
        deadline.setStyle("-fx-text-fill: #000;");
        this.checkDeadline();
        goalLabel.setStyle("-fx-text-fill: #000;");
        for (Node n : taskNameLabel.getChildrenUnmodifiable()) {
            n.setStyle("-fx-strikethrough: false;");
        }
        taskDoneButton.setStyle("-fx-text-fill: #000;");
        this.timerStartButton.setDisable(false);
    }

    /**
     * Grey and strokes the text of an task thets marked as done
     */
    private void disableText() {
        taskNameLabel.setStyle("-fx-text-fill: #535151;");
        deadline.setStyle("-fx-text-fill: #535151;");
        goalLabel.setStyle("-fx-text-fill: #535151;");
        for (Node n : taskNameLabel.getChildrenUnmodifiable()) {
            n.setStyle("-fx-strikethrough: true;");
        }
        taskDoneButton.setStyle("-fx-text-fill: green;");
        this.timerStartButton.setDisable(true);
    }

    /**
     * Checks if the Deadline is in the past, if so the font turns red
     */
    private void checkDeadline() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime deadlineTime = taskListItem.getDeadline().toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate().atTime(LocalTime.MAX);
        if (currentTime.isAfter(deadlineTime)) {
            Platform.runLater(()-> deadline.setStyle("-fx-text-fill:red;"));
        }
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
            if (taskListItem.getTimeTracker().isRunning()) {
                taskListItem.getTimeTracker().pause();
            }
        } else {
            taskListItem.setTaskStatus(TaskStatus.ACTIVE);
        }
        updateRednerSettings();
    }

    /**
     * The Goal desciption can now grow on Click or toggle back to the small view
     */
    @FXML
    public void toggleTaskGoalText() {
        if (goalLabel.getMaxHeight() == 20) {
            goalLabel.setMaxHeight(Double.MAX_VALUE);
        } else {
            goalLabel.setMaxHeight(20);
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
        if (this.taskListItem.getTimeTracker().getCurrentTime() == 0) {
            this.taskListItem.getTimeTracker().start();
        } else if (this.taskListItem.getTimeTracker().isRunning()) {
            this.taskListItem.getTimeTracker().pause();
        } else {
            this.taskListItem.getTimeTracker().resume();
        }
        updateRednerSettings();
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
