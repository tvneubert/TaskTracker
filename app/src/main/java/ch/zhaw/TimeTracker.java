package ch.zhaw;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * This class implements a simple time tracker logic.
 * It can be started, paused and resumed.
 * It can be queried for the current time.
 */

public class TimeTracker {
    private boolean running;
    private long startTime;
    private long pausedTime;


    /**
     * This function starts the time tracker.
     */
    public void start() {
        if (!running) {
            startTime = System.currentTimeMillis();
            running = true;
        }
    }


    /**
     * This function pauses the time tracker.
     */
    public void pause() {
        if (running) {
            pausedTime = System.currentTimeMillis();
            running = false;
        }
    }

    
    /**
     * This function resumes the time tracker.
     */
    public void resume() {
        if (!running) {
            startTime += System.currentTimeMillis() - pausedTime;
            running = true;
        }
    }

    
    /**
     * This function returns the current time in milliseconds.
     * @return the current time in milliseconds as integer.
     */
    @JsonIgnore
    public int getCurrentTime() {
        if (running) {
            return (int) (System.currentTimeMillis() - startTime);
        } else {
            return (int) (pausedTime - startTime);
        }
    }


    /**
     * This function returns the time tracker is running.
     * @return the state of the time tracker.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Gets the StartTime 
     * @return
     */
    public long getStartTime() {
        return this.startTime;
    }

    /**
     * Gets the paused time
     * @return
     */
    public long getPausedTime() {
        return this.pausedTime;
    }
}
