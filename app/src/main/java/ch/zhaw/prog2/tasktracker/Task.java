package ch.zhaw.prog2.tasktracker;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Task {

    private int taskID;
    private String title;
    private String description;
    private String goal;
    private Date deadline;
    private boolean taskStatus;
    private boolean timerStatus;
    private Timer timer;
    private int minutes = 0;
    private int hours = 0;


    public Task(String title, String description, String goal, Date deadline, double stopwatch) {
        this.title = title;
        this.description = description;
        this.goal = goal;
        this.deadline = deadline;
        this.taskStatus = true;
        this.timerStatus = false;
    }

    /**
     * Test constructor
     * will be deleted afterwards!!!
     */
    public Task() {}

    /**
     * Changes the status of the task to the specified boolean value.
     * true = The task is active.
     * false = the task is finished and completed.
     * @param changeStatus the boolean value to set the task status to
     */
    protected void changeTaskStatus(boolean changeStatus) {
        if (changeStatus == false) {
            timerStatus = false;
        } else {
            timerStatus = true;
        }
    }

    /**
     * Toggles the timer between the start and stop states.
     */
    protected void toggleTimer() {
        if (timerStatus == false) {
            startTimer();
        } else if (timerStatus == true) {
            stopTimer();
        }
    }

    /**
     * Starts the timer by scheduling a TimerTask to run every minute and incrementing the hours and minutes accordingly.
     */
    private void startTimer() {
        timerStatus = true;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                minutes++;
                if (minutes == 60) {
                    hours++;
                    minutes = 0;
                }
            }
        }, 0, 60000);
    }

    /**
     * Stops the timer and cancels any scheduled TimerTasks.
     */
    private void stopTimer() {
        timerStatus = false;
        timer.cancel();
    }

    /**
     * Returns a formatted string of the elapsed time in hours and minutes.
     * @return A string in the format "Xh Ymin" where X is the number of hours and Y is the number of minutes.
     */
    protected String returnTimer() {
        return hours + "h " + minutes + "min";
    }

}

class Main {
    public static void main(String[] args) throws InterruptedException {
//        Task task = new Task();
//        task.startTimer();
//        Thread.sleep(2 * 60000);
//        task.stopTimer();
//        task.startTimer();
//        Thread.sleep(3 * 60000);
//        task.stopTimer();
//        System.out.println(task.returnTimer());
    }
}
