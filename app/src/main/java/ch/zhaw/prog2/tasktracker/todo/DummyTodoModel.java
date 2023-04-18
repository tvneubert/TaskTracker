package ch.zhaw.prog2.tasktracker.todo;

import java.util.ArrayList;
import java.util.List;


/*
 * This class is a dummy for the Model of the ToDo.
 * It contains the data logic of the toDo Object.
 * 
 */
public class DummyTodoModel {
    private List<DummyTodoDataObject> todos = new ArrayList<>();

    /**
     * This constructor creates a dummy ToDoModel with some dummy ToDos.
     */
    public DummyTodoModel() {
        todos.add(new DummyTodoDataObject("ToDo 1"));
        todos.add(new DummyTodoDataObject("ToDo 2"));
        todos.add(new DummyTodoDataObject("ToDo 3"));
        todos.add(new DummyTodoDataObject("ToDo 4"));
        todos.add(new DummyTodoDataObject("ToDo 5"));
        todos.add(new DummyTodoDataObject("ToDo 6"));
        todos.add(new DummyTodoDataObject("ToDo 7"));
    }

    /**
     * This method adds a new ToDo to the list of ToDos.
     * @return the new ToDo
     */
    public List<DummyTodoDataObject> getTodos() {
        return new ArrayList<>(todos);
    }
}
