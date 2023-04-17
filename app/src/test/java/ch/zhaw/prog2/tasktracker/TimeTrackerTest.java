package ch.zhaw.prog2.tasktracker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ch.zhaw.TimeTracker;

    /**
     * This class is used to test the TimeTracker class.
     */
public class TimeTrackerTest {

    /**
     * Test if the time tracker can be started.
     * and paused and resumed.
     * 
     * @throws InterruptedException
     */
    @Test
    public void testPausePositiv() throws InterruptedException {
        TimeTracker tracker = new TimeTracker();
        tracker.start();
        Thread.sleep(1000);
        tracker.pause();
        int timeBeforePause = tracker.getCurrentTime();
        Thread.sleep(1000);
        int timeAfterPause = tracker.getCurrentTime();
        assertEquals(timeBeforePause, timeAfterPause);
    }

    /**
     * Test if the time tracker can be started.
     * and if we get the correct time after 1 second.
     * 
     * @throws InterruptedException
     */
    @Test
    public void testGetCurrentTimePositiv() throws InterruptedException {
        TimeTracker tracker = new TimeTracker();
        tracker.start();
        Thread.sleep(1000);
        int currentTime = tracker.getCurrentTime();
        assertEquals(true, currentTime >= 1000 && currentTime < 1100);
    }

}
