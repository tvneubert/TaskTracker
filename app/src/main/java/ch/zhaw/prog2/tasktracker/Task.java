package ch.zhaw.prog2.tasktracker;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Task {

    private String title;
    private String description;
    private String goal;
    private Date dateNow;
    private Calendar calendar;
    private boolean taskStatus;


    public Task(String title, String description, String goal, Date deadline) {
        this.title = title;
        this.description = description;
        this.goal = goal;
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

    protected String getTitle() {
        return title;
    }

    protected String getDescription() {
        return description;
    }

    protected String getGoal() {
        return goal;
    }

}
