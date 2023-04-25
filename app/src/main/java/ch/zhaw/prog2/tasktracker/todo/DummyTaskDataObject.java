package ch.zhaw.prog2.tasktracker.task;

import ch.zhaw.TimeTracker;


/*
 * This class is a dummy for the DataObject of the task.
 * It contains the data of the task Object.
 * Needs to be replaced with the final task DataObject
 */
public class DummyTaskDataObject {
    //Name of the task
    private String taskName;
    //TimeTracker for the task
    private TimeTracker tt = new TimeTracker();

    /**
     * This method creates a new task with the given name.
     * @param name
     */
    public DummyTaskDataObject(String name) {
        this.taskName = name;
    }

    /**
     * This method creates the TimeTracker for the task.
     * @return the TimeTracker of the task
     */
    public TimeTracker getTimeTracker() {
        return tt;
    }

    /**
     * This method sets the name of the task.
     * @param name
     */
    public void settaskName(String name) {
        this.taskName = name;
    }

    /**
     * This method returns the name of the task.
     * @return the name of the task
     */
    public String gettaskName() {
        return this.taskName;
    }
}
