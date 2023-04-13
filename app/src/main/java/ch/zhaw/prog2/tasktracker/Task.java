package ch.zhaw.prog2.tasktracker;

import java.util.Date;

public class Task {

    private int taskID;
    private String title;
    private String description;
    private String goal;
    private Date deadline;
    private boolean finishTask;
    private double stopwatch;

    public Task(String title, String description, String goal, Date deadline, double stopwatch) {
        this.title = title;
        this.description = description;
        this.goal = goal;
        this.deadline = deadline;
        this. stopwatch = stopwatch;
    }


}
