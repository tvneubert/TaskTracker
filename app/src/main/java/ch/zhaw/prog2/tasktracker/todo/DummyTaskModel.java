package ch.zhaw.prog2.tasktracker.todo;

import java.util.ArrayList;
import java.util.List;

import ch.zhaw.prog2.tasktracker.todo.DummyTaskDataObject;


/*
 * This class is a dummy for the Model of the task.
 * It contains the data logic of the task Object.
 * 
 */
public class DummyTaskModel {
    private List<DummyTaskDataObject> tasks = new ArrayList<>();

    /**
     * This constructor creates a dummy taskModel with some dummy tasks.
     */
    public DummyTaskModel() {
        tasks.add(new DummyTaskDataObject("task 1"));
        tasks.add(new DummyTaskDataObject("task 2"));
        tasks.add(new DummyTaskDataObject("task 3"));
        tasks.add(new DummyTaskDataObject("task 4"));
        tasks.add(new DummyTaskDataObject("task 5"));
        tasks.add(new DummyTaskDataObject("task 6"));
        tasks.add(new DummyTaskDataObject("task 7"));
    }

    /**
     * This method adds a new task to the list of tasks.
     * @return the new task
     */
    public List<DummyTaskDataObject> gettasks() {
        return new ArrayList<>(tasks);
    }
}
