package ch.zhaw.prog2.tasktracker.task;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import ch.zhaw.TimeTracker;
import ch.zhaw.prog2.tasktracker.Observerable.ObservableTask;
import ch.zhaw.prog2.tasktracker.Observerable.TaskEventListener;

/**
 * Represents a task with a description, a goal, a deadline and a TimeTracker.
 * It can be in an ACTIVE or FINISHED state.
 * Implements ObservableTask to allow for listeners to be notified of changes to
 * the task.
 **/
public class Task implements ObservableTask {
    private ArrayList<TaskEventListener> observers = new ArrayList<>();

    /**
     * Represents the current status of the task, whether it is ACTIVE or FINISHED.
     */
    public enum TaskStatus {
        ACTIVE, FINISHED
    }

    private String description;
    private String goal;
    private Date deadline;
    private TimeTracker timeTracker = new TimeTracker();

    private TaskStatus taskStatus = TaskStatus.ACTIVE;

    /**
     * Creates a new Task with the given description, goal and deadline.
     * Informs the ObjectMapper about the fields he should save in the JSON file
     *
     * @param description a String representing the description of the task
     * @param goal        a String representing the goal of the task
     * @param deadline    a Date representing the deadline of the task
     */
    public Task(@JsonProperty("description") String description, @JsonProperty("goal") String goal,
            @JsonProperty("deadline") Date deadline) {
        this.description = description;
        this.goal = goal;
        this.deadline = deadline;
    }

    /**
     * This method creates the TimeTracker for the task.
     *
     * @return the TimeTracker of the task
     */
    public TimeTracker getTimeTracker() {
        return timeTracker;
    }

    /**
     * Returns the TimeTracker for this task.
     *
     * @return the TimeTracker of the task
     */
    public void setTaskStatus(TaskStatus ts) {
        this.taskStatus = ts;

        for (TaskEventListener listener : observers) {
            listener.taskStateChanged(this);
        }
    }

    /**
     * Returns the current TaskStatus of the task.
     *
     * @return the TaskStatus of the task
     */
    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    /**
     * Returns the description of the task.
     *
     * @return a String representing the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the goal of the task.
     *
     * @return a String representing the goal of the task
     */
    public String getGoal() {
        return goal;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return a Date representing the deadline of the task
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * Implementation of Observable
     * Add listener to the list of listeners to be notified
     *
     * @param listener InvalidationListener to add to the list
     *                 The listener to register
     */
    @Override
    public void addListener(TaskEventListener listener) {
        if (listener != null && !this.observers.contains(listener)) {
            observers.add(listener);
        }
    }

    /**
     * Implementation of Observable
     * remove listener from the list of listeners to be notified
     *
     * @param listener InvalidationListener to remove from the list
     *                 The listener to remove
     */
    @Override
    public void removeListener(TaskEventListener listener) {
        if (observers.contains(listener)) {
            observers.remove(listener);
        }
    }

    /**
     * Notifies all listeners that this task has been marked for deletion.
     */
    public void wantsDelete() {
        for (TaskEventListener listener : observers) {
            listener.deleteRequest(this);
        }
    }
}
