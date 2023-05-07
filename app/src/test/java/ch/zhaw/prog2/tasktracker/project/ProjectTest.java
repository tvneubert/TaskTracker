package ch.zhaw.prog2.tasktracker.project;

import ch.zhaw.prog2.tasktracker.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {
    Project testProject;
    Task testtask1;
    Task testtask2;
    Task testtask3;
    Task testtask4;

    @BeforeEach
    void setUp() {
        testProject = new Project("testProject");
        testtask1 = new Task("testtask1", "Test the project ", new Date(111111111L));
        testtask2 = new Task("testtask2", "Test the project", new Date(222222222L));
        testtask3 = new Task("testtask3", "Test the project", new Date(333333333L));
        testtask4 = new Task("testtask4", "Test the project", new Date(444444444L));
        testtask1.setTaskStatus(Task.TaskStatus.ACTIVE);
        testtask2.setTaskStatus(Task.TaskStatus.FINISHED);
        testtask3.setTaskStatus(Task.TaskStatus.ACTIVE);
        testtask4.setTaskStatus(Task.TaskStatus.ACTIVE);
    }

    /**
     * Test that a project that does not contain any tasks is considered completed
     */
    @Test
    void isCompletedWhenEmpty() {
        assertTrue(testProject.isCompleted());
        testProject.addTask(testtask1);
        testProject.removeTask(testtask1);
        assertTrue(testProject.isCompleted());
    }

    /**
     * Test that a project that only contains finished tasks is considered completed
     */
    @Test
    void isCompletedWhenAllTaskCompleted(){
        testProject.addTask(testtask2);
        assertTrue(testProject.isCompleted());
        testProject.addTask(testtask1);
        testtask1.setTaskStatus(Task.TaskStatus.FINISHED);
        assertTrue(testProject.isCompleted());
    }

    /**
     * Test that a project that has at least one active task is considered incomplete
     */
    @Test
    void isNotCompletedWithOpenTask(){
        testProject.addTask(testtask1);
        assertFalse(testProject.isCompleted());
        testProject.addTask(testtask2);
        assertFalse(testProject.isCompleted());
    }

    /**
     * Test that an empty list is returned when there are no tasks in the project
     */
    @Test
    void getOpenTasksWhenEmpty() {
        assertEquals(testProject.getOpenTasks(), new ArrayList<Task>());
        testProject.addTask(testtask1);
        testProject.removeTask(testtask1);
        assertEquals(testProject.getOpenTasks(), new ArrayList<Task>());
    }

    /**
     * Test that only the open Tasks are returned
     */
    @Test
    void getOnlyOpenTasks(){
        testProject.addTask(testtask1);
        ArrayList<Task> expectedList = new ArrayList<>();
        expectedList.add(testtask1);
        assertEquals(testProject.getOpenTasks(),expectedList);
        testProject.addTask(testtask2);
        assertEquals(testProject.getOpenTasks(),expectedList);
    }

    /**
     * Test that an empty list is returned when there are no tasks in the project
     */
    @Test
    void getClosedTasksWhenEmpty() {
        assertEquals(testProject.getOpenTasks(), new ArrayList<Task>());
        testProject.addTask(testtask1);
        testProject.removeTask(testtask1);
        assertEquals(testProject.getOpenTasks(), new ArrayList<Task>());
    }

    /**
     * Test that only the closed Tasks are returned
     */
    @Test
    void getOnlyClosedTasks(){
        testProject.addTask(testtask2);
        ArrayList<Task> expectedList = new ArrayList<>();
        expectedList.add(testtask2);
        assertEquals(testProject.getClosedTasks(),expectedList);
        testProject.addTask(testtask1);
        assertEquals(testProject.getClosedTasks(),expectedList);
    }

    /**
     * Test that nothing happens when a task is removed that is not in the project
     */
    @Test
    void removeNotAddedTask() {
        ArrayList<Task> expectedList = new ArrayList<>();
        testProject.removeTask(testtask1);
        assertEquals(testProject.getTasks(), expectedList);
        testProject.addTask(testtask1);
        expectedList.add(testtask1);
        testProject.removeTask(testtask2);
        assertEquals(testProject.getTasks(), expectedList);
    }

    /**
     * Test that the method removeTask only removes the supplied Task
     */
    @Test
    void removeAddedTask() {
        ArrayList<Task> expectedList = new ArrayList<>();
        testProject.addTask(testtask1);
        testProject.removeTask(testtask1);
        assertEquals(testProject.getTasks(), expectedList);
        testProject.addTask(testtask1);
        testProject.addTask(testtask2);
        testProject.removeTask(testtask1);
        expectedList.add(testtask2);
        assertEquals(testProject.getTasks(), expectedList);
    }

    /**
     * Tests the getAllTasks() method of the Project class to ensure it returns a list of all tasks in the project.
     */
    @Test
    void testGetAllTasks() {
        testProject.addTask(testtask1);
        testProject.addTask(testtask2);
        testProject.addTask(testtask3);
        testProject.addTask(testtask4);
        ArrayList<Task> expectedList = new ArrayList<>();
        expectedList.add(testtask1);
        expectedList.add(testtask2);
        expectedList.add(testtask3);
        expectedList.add(testtask4);
        assertEquals(testProject.getAllTasks(),expectedList);
    }

    /**
     * Tests the getAllTasks() method of the Project class to ensure it returns an empty list
     * when there are no tasks in the project.
     */
    @Test
    void testGetAllTasksNoTasks() {
        ArrayList<Task> expectedList = new ArrayList<>();
        assertEquals(testProject.getAllTasks(), expectedList);
    }

    /**
     * Tests the getOpenTasksDate() method of the Project class to ensure it returns a list of
     * open tasks in the project, sorted by date.
     */
    @Test
    void testGetOpenTasksDate() {
        testProject.addTask(testtask3);
        testProject.addTask(testtask2);
        testProject.addTask(testtask4);
        testProject.addTask(testtask1);

        ArrayList<Task> expectedList = new ArrayList<>();
        expectedList.add(testtask1);
        expectedList.add(testtask3);
        expectedList.add(testtask4);

        assertEquals(testProject.getOpenTasksDate(), expectedList);
    }

    /**
     * Tests the getOpenTasksDate() method of the Project class to ensure it returns a list of open tasks in the project,
     * sorted by date, but fails due to a different sort order.
     */
    @Test
    void testGetOpenTasksDateWrongSortOrder() {
        testProject.addTask(testtask4);
        testProject.addTask(testtask1);
        testProject.addTask(testtask3);

        ArrayList<Task> expectedList = new ArrayList<>();
        expectedList.add(testtask3);
        expectedList.add(testtask1);
        expectedList.add(testtask4);

        assertNotEquals(testProject.getOpenTasksDate(), expectedList);
    }
}