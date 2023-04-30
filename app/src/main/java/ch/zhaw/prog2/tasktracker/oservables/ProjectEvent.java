package ch.zhaw.prog2.tasktracker.oservables;

import ch.zhaw.prog2.tasktracker.task.Task;

public interface ProjectEvent {
    public void allTasksFinished();
    public void taskStateChange(Task t);
    public void taskDeleted(Task t);
    public void taskCreated(Task t);
}
