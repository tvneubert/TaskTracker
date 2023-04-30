package ch.zhaw.prog2.tasktracker.oservables;

import java.util.Date;

import ch.zhaw.prog2.tasktracker.task.Task;

public interface TaskEvent {
    public void taskStateChanged(Task task);
    public void deleteRequest(Task t);
}