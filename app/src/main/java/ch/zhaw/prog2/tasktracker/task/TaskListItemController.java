package ch.zhaw.prog2.tasktracker.task;

import ch.zhaw.prog2.tasktracker.TimeFormater;
import ch.zhaw.prog2.tasktracker.oservables.ITaskEvent;
import ch.zhaw.prog2.tasktracker.oservables.ObservableTaskView;
import ch.zhaw.prog2.tasktracker.task.Task.TaskStatus;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * This class is a controller for the task list item.
 */
public class TaskListItemController implements ObservableTaskView {

    /**
     * The task object that is represented by this list item.
     */
    private Task taskListItem;
    private ArrayList<ITaskEvent> observers = new ArrayList<>();

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

    @FXML 
    public void toggleTaskState() {
        if(taskListItem.getTaskStatus().equals(TaskStatus.ACTIVE)) {
            taskListItem.setTaskStatus(TaskStatus.FINISHED);
            for(Node n : taskNameLabel.getChildrenUnmodifiable()) {
                n.setStyle("-fx-strikethrough: true;");
            }
            taskNameLabel.setStyle("-fx-text-fill: #535151;");
            timerResetButton.setStyle("-fx-text-fill: green;");
        } else {
            taskListItem.setTaskStatus(TaskStatus.ACTIVE);
            for(Node n : taskNameLabel.getChildrenUnmodifiable()) {
                n.setStyle("-fx-strikethrough: false;");
            }
            taskNameLabel.setStyle("-fx-text-fill: #000;");
            timerResetButton.setStyle("-fx-text-fill: #000;");
        }
    }


    @FXML
    public void timerButton() {
        System.out.println(this.taskListItem.getTimeTracker().getCurrentTime());
        if(this.taskListItem.getTimeTracker().getCurrentTime() == 0){
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
        notifyListenersDeleteClick();
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
    private void notifyListenersDeleteClick(){
        for(ITaskEvent listener : observers){
            listener.taskDeleteClick(this.taskListItem);
        }
    }
    /**
     * get the Task object this list item represents
     * @return Task object
     */
    public Task getTaskListItem(){return taskListItem;}
}
