package ch.zhaw.prog2.tasktracker.task;

import java.util.Date;

import ch.zhaw.TimeTracker;

public class Task {

    public enum TaskStatus {
        task, ACTIVE, FINISHED
    }

    private String description;
    private String goal;
    private Date deadline;
    private TimeTracker tt = new TimeTracker();

    private TaskStatus taskStatus;

    public Task(String description, String goal, Date deadline) {
        this.description = description;
        this.goal = goal;
        this.deadline = deadline;
        this.taskStatus = TaskStatus.task;
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
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    protected String getDescription() {
        return description;
    }

    public String getGoal() {
        return goal;
    }

    public Date getDate() {
        return deadline;
    }

}
