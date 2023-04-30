package ch.zhaw.prog2.tasktracker.task;

import java.util.ArrayList;
import java.util.Date;

import ch.zhaw.TimeTracker;
import ch.zhaw.prog2.tasktracker.oservables.ObservableTask;
import ch.zhaw.prog2.tasktracker.oservables.TaskEvent;

public class Task implements ObservableTask {
    private ArrayList<TaskEvent> observers = new ArrayList<>();

    public enum TaskStatus {
        ACTIVE, FINISHED
    }

    private String description;
    private String goal;
    private Date deadline;
    private TimeTracker tt = new TimeTracker();

    private TaskStatus taskStatus = TaskStatus.ACTIVE;

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

    public void setTaskStatus(TaskStatus ts) {
        this.taskStatus = ts;
        
        for(TaskEvent listener : observers){
            listener.taskStateChanged(this);
        }
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    protected String getDescription() {
        return description;
    }

    protected String getGoal() {
        return goal;
    }

    protected Date getDate() {
        return deadline;
    }

    /**
     * Implementation of Observable
     * Add listener to the list of listeners to be notified
     * @param listener InvalidationListener to add to the list
     *            The listener to register
     */
    @Override
    public void addListener(TaskEvent listener) {
        if(listener != null && !this.observers.contains(listener)) {
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
    public void removeListener(TaskEvent listener) {
        if(observers.contains(listener)){
            observers.remove(listener);
        }
    }

    public void wantsDelete() {
        for(TaskEvent listener : observers){
            listener.deleteRequest(this);
        }
    }
}
