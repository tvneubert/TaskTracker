package ch.zhaw.prog2.tasktracker.task;

import java.util.ArrayList;
import java.util.Date;

import ch.zhaw.TimeTracker;
import ch.zhaw.prog2.tasktracker.oservables.ObservableTask;
import ch.zhaw.prog2.tasktracker.oservables.TaskEvent;

    /**
     * Represents a task with a description, a goal, a deadline and a TimeTracker.
     * It can be in an ACTIVE or FINISHED state.
     * Implements ObservableTask to allow for listeners to be notified of changes to
     * the task.
     **/
public class Task implements ObservableTask {
    private ArrayList<TaskEvent> observers = new ArrayList<>();

    /**
     * Represents the current status of the task, whether it is ACTIVE or FINISHED.
     */
    public enum TaskStatus {
        ACTIVE, FINISHED
    }

    private String description;
    private String goal;
    private Date deadline;
    private TimeTracker tt = new TimeTracker();

    private TaskStatus taskStatus = TaskStatus.ACTIVE;

    /**
     * Creates a new Task with the given description, goal and deadline.
     *
     * @param description a String representing the description of the task
     * @param goal        a String representing the goal of the task
     * @param deadline    a Date representing the deadline of the task
     */
    public Task(String description, String goal, Date deadline) {
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
        return tt;
    }

    /**
     * Returns the TimeTracker for this task.
     *
     * @return the TimeTracker of the task
     */
    public void setTaskStatus(TaskStatus ts) {
        this.taskStatus = ts;

        for (TaskEvent listener : observers) {
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
    protected String getDescription() {
        return description;
    }

    /**
     * Returns the goal of the task.
     *
     * @return a String representing the goal of the task
     */
    protected String getGoal() {
        return goal;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return a Date representing the deadline of the task
     */
    protected Date getDate() {
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
    public void addListener(TaskEvent listener) {
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
    public void removeListener(TaskEvent listener) {
        if (observers.contains(listener)) {
            observers.remove(listener);
        }
    }

    /**
     * Notifies all listeners that this task has been marked for deletion.
     */
    public void wantsDelete() {
        for (TaskEvent listener : observers) {
            listener.deleteRequest(this);
        }
    }
}
