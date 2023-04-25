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
 * This class is a controller for the ToDo list item.
 */
public class TaskListItemController implements Observable {

    /**
     * The ToDo object that is represented by this list item.
     */
    private Task taskListItem;
    private ArrayList<InvalidationListener> observers = new ArrayList<>();

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
    /**
     * Event-handler for the delete button of the list item
     * Deletes the Task
     * @param event the ActionEvent that triggered this method
     */
    @FXML
    void deleteTodo(ActionEvent event) {
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
