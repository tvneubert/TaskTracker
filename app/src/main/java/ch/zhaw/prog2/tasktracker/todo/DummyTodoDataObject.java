package ch.zhaw.prog2.tasktracker.todo;

import ch.zhaw.TimeTracker;


/*
 * This class is a dummy for the DataObject of the ToDo.
 * It contains the data of the toDo Object.
 * Needs to be replaced with the final ToDo DataObject
 */
public class DummyTodoDataObject {
    //Name of the ToDo
    private String todoName;
    //TimeTracker for the ToDo
    private TimeTracker tt = new TimeTracker();

    /**
     * This method creates a new ToDo with the given name.
     * @param name
     */
    public DummyTodoDataObject(String name) {
        this.todoName = name;
    }

    /**
     * This method creates the TimeTracker for the ToDo.
     * @return the TimeTracker of the ToDo
     */
    public TimeTracker getTimeTracker() {
        return tt;
    }

    /**
     * This method sets the name of the ToDo.
     * @param name
     */
    public void setTodoName(String name) {
        this.todoName = name;
    }

    /**
     * This method returns the name of the ToDo.
     * @return the name of the ToDo
     */
    public String getTodoName() {
        return this.todoName;
    }
}
