package ch.zhaw.prog2.tasktracker.Observerable;

import ch.zhaw.prog2.tasktracker.task.Task;

/**
 * The ProjectEvent interface defines methods that are called when events occur
 * in a project.
 */
public interface ProjectEventListener {

    /**
     * Called when all tasks in the project are finished.
     */
    public void allTasksFinished();

    /**
     * Called when the state of a task in the project has changed.
     * 
     * @param t the task whose state has changed
     */
    public void taskStateChange(Task t);

    /**
     * Called when a task has been deleted from the project.
     * 
     * @param t the task that was deleted
     */
    public void taskDeleted(Task t);

    /**
     * Called when a new task has been added to the project.
     * 
     * @param t the task that was added
     */
    public void taskCreated(Task t);
}
