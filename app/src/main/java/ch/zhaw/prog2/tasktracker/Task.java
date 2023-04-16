package ch.zhaw.prog2.tasktracker;

import java.util.Date;

public class Task {

    private String description;
    private String goal;
    private Date deadline;

    private boolean taskStatus;


    public Task(String description, String goal, Date deadline) {
        this.description = description;
        this.goal = goal;
        this.deadline = deadline;
        this.taskStatus = true;
    }

    /**
     * Changes the status of the task to the specified boolean value.
     * true = The task is active.
     * false = the task is finished and completed.
     */
    protected boolean toggleTask() {
        if (taskStatus == true) {
            taskStatus = false;
            return taskStatus;
        } else {
            taskStatus = true;
            return taskStatus;
        }
    }

    protected String getDescription() {
        return description;
    }

    protected String getGoal() {
        return goal;
    }
    protected Date getDate() { return deadline;}

}
