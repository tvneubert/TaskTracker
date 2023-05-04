package ch.zhaw.prog2.tasktracker.Observerable;

import ch.zhaw.prog2.tasktracker.task.Task;

/**
 * The TaskEvent interface defines methods that are called when events related
 * to a task occur.
 */
public interface TaskEventListener {

    /**
     * Called when the state of a task has changed.
     * 
     * @param task the task whose state has changed
     */
    public void taskStateChanged(Task task);

    /**
     * Called when a request is made to delete a task.
     * 
     * @param t the task to be deleted
     */
    public void deleteRequest(Task t);
}
