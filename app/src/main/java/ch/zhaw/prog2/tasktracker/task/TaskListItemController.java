package ch.zhaw.prog2.tasktracker.task;

import ch.zhaw.prog2.tasktracker.TimeFormater;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * This class is a controller for the task list item.
 */
public class TaskListItemController implements Observable {

    /**
     * The task object that is represented by this list item.
     */
    private Task taskListItem;
    private ArrayList<InvalidationListener> observers = new ArrayList<>();

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
     * @param task
     */
    public void setTaskObject(Task task) {
        this.taskListItem = task;
        // we can only start the timeline if we do have a task object because it does contain the timer
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

    @FXML
    public void timerButton() {
        System.out.println(this.taskListItem.getTimeTracker().getCurrentTime());
        if(this.taskListItem.getTimeTracker().getCurrentTime()==0){
            this.taskListItem.getTimeTracker().start();
            this.timerStartButton.setText("⏸");
        } else if(this.taskListItem.getTimeTracker().isRunning()){
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
     * @param event the ActionEvent that triggered this method
     */
    @FXML
    void deleteTask(ActionEvent event) {
        notifyListeners();
    }
    /**
     * Implementation of Observable
     * Add listener to the list of listeners to be notified
     * @param listener InvalidationListener to add to the list
     *            The listener to register
     */
    @Override
    public void addListener(InvalidationListener listener) {
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
    public void removeListener(InvalidationListener listener) {
        if(observers.contains(listener)){
            observers.remove(listener);
        }
    }
    /**
     * Required for the function of Observable
     * Loop though all listeners and notify them all
     */
    private void notifyListeners(){
        for(InvalidationListener listener : observers){
            listener.invalidated(this);
        }
    }
    /**
     * get the Task object this list item represents
     * @return Task object
     */
    public Task getTaskListItem(){return taskListItem;}
}
