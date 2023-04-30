package ch.zhaw.prog2.tasktracker.oservables;

import java.util.Date;

import ch.zhaw.prog2.tasktracker.task.Task;

public interface ITaskEvent {
    public void taskCreate(String taskGoal, String taskDescription, Date deadlineDate);
    public void taskDeleteClick(Task taskListItem);
}