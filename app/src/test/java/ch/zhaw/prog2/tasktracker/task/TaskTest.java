package ch.zhaw.prog2.tasktracker.task;

import ch.zhaw.prog2.tasktracker.Observerable.TaskEventListener;
import ch.zhaw.prog2.tasktracker.project.Project;
import javafx.beans.Observable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskTest {

    Task task;

    @BeforeEach
    void setUp() {
        task = new Task("task", "Test the Task", new Date(111111111L));
    }

    /**
     * Tests whether the `setTaskStatus` method successfully updates the task status to "Active".
     * It also tests whether the listener was notified properly.
     */
    @Test
    public void testSetTaskStatusActive() {
        TaskEventListener listener = mock(TaskEventListener.class);
        task.addListener(listener);

        Task.TaskStatus newStatus = Task.TaskStatus.ACTIVE;
        task.setTaskStatus(newStatus);

        assertEquals(newStatus, task.getTaskStatus());

        verify(listener).taskStateChanged(task);
    }

    /**
     * Tests whether the `setTaskStatus` method successfully updates the task status to "Finished".
     * It also tests whether the listener was notified properly.
     */
    @Test
    public void testSetTaskStatusFinished() {
        TaskEventListener listener = mock(TaskEventListener.class);
        task.addListener(listener);

        Task.TaskStatus newStatus = Task.TaskStatus.FINISHED;
        task.setTaskStatus(newStatus);

        assertEquals(newStatus, task.getTaskStatus());

        verify(listener).taskStateChanged(task);
    }

    /**
     * Tests whether the `setTaskStatus` method does not update the task status when the new status is the same as the old one.
     * It also tests whether the task status remains correct.
     */
    @Test
    public void testSetTaskStatusNoUpdate() {
        Task.TaskStatus oldStatus = task.getTaskStatus();

        task.setTaskStatus(oldStatus);

        assertEquals(oldStatus, task.getTaskStatus());
    }

    /**
     * Tests whether the `setTaskStatus` method notifies the listeners in the correct order when the task status changes.
     */
    @Test
    public void testSetTaskStatusNotifyListenersInOrder() {
        TaskEventListener listener1 = mock(TaskEventListener.class);
        TaskEventListener listener2 = mock(TaskEventListener.class);
        task.addListener(listener1);
        task.addListener(listener2);

        Task.TaskStatus newStatus = Task.TaskStatus.FINISHED;
        task.setTaskStatus(newStatus);

        InOrder inOrder = inOrder(listener1, listener2);
        inOrder.verify(listener1).taskStateChanged(task);
        inOrder.verify(listener2).taskStateChanged(task);
    }

}