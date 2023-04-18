package ch.zhaw.prog2.tasktracker;

import java.util.Date;

import ch.zhaw.TimeTracker;

public class Task {

    public enum TaskStatus {
        TODO, ACTIVE, FINISHED
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
        this.taskStatus = TaskStatus.TODO;
    }

        /**
     * This method creates the TimeTracker for the ToDo.
     * @return the TimeTracker of the ToDo
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

    protected String getGoal() {
        return goal;
    }
    protected Date getDate() { return deadline;}

}
