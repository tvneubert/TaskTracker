package ch.zhaw.prog2.tasktracker.project;

import ch.zhaw.prog2.tasktracker.Observerable.ObservableProject;
import ch.zhaw.prog2.tasktracker.Observerable.ProjectEventListener;
import ch.zhaw.prog2.tasktracker.Observerable.TaskEventListener;
import ch.zhaw.prog2.tasktracker.task.Task;
import ch.zhaw.prog2.tasktracker.task.Task.TaskStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
//Needed import for our JSON File Database
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class to represent a project.
 * Has a list of tasks and keeps track of time used for each task
 */

public class Project implements ObservableProject, TaskEventListener {
    // Creates a list of observers for the Project
    private ArrayList<ProjectEventListener> observers = new ArrayList<>();
    // Creates a List of Tasks
    private ArrayList<Task> tasks = new ArrayList<>();
    // This is a name..
    private String name;

    /**
     * Constructor for a new project.
     * Tells the ObjectMapper thet the name field has to be parsed here
     *
     * @param name String for the name of the Project
     */
    public Project(@JsonProperty("name") String name) {
        this.name = name;
    }

    /**
     * Return the state of the project
     * Tells the ObjectMapper thet this field needs to be ignored (bcs its computed)
     *
     * @return boolean of the pjoject.
     */
    @JsonIgnore
    public boolean isCompleted() {
        for (Task task : tasks) {
            if (!task.getTaskStatus().equals(TaskStatus.FINISHED)) { // task Replace with correct method of task class
                return false;
            }
        }
        return true;
    }

    /**
     * Method to return the time already spent on this project.
     * Tells the ObjectMapper thet this field needs to be ignored (bcs its computed)
     *
     * @return Time spent on the project task Replace return value with proper type
     */
    @JsonIgnore
    public int getTotalTaskTime() {
        int time = 0;
        for (Task task : tasks) {
            time += task.getTimeTracker().getCurrentTime();
        }
        return time;
    }

    /**
     * Return only the open tasks associated with this projcet
     * Tells the ObjectMapper thet this field needs to be ignored (bcs its computed)
     *
     * @return List of task objects task Replace type of ArrayList with proper class
     */
    @JsonIgnore
    public ArrayList<Task> getOpenTasks() {
        ArrayList<Task> openTasks = new ArrayList<>();
        if (tasks.size() != 0) {
            for (Task task : tasks) {
                if (!task.getTaskStatus().equals(TaskStatus.FINISHED)) { // task Replace with correct method of task
                                                                         // class
                    openTasks.add(task);
                }
            }
        }
        return openTasks;
    }

    /**
     * Returns a sorted list of open tasks in the project, sorted by deadline date in ascending order.
     * @return An ArrayList of open tasks sorted by deadline date in ascending order.
     */
    public ArrayList<Task> getOpenTasksDate(){
        ArrayList<Task> openTasks = getOpenTasks();

        Comparator<Task> byDeadline = new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t1.getDate().compareTo(t2.getDate());
            }
        };
        Collections.sort(openTasks, byDeadline);

        return openTasks;
    }

    /**
     * Returns a sorted list of open tasks in the project, sorted by time tracked in ascending order.
     * @return An ArrayList of open tasks sorted by time tracked in ascending order.
     */
    public ArrayList<Task> getOpenTasksEffort(){
        ArrayList<Task> openTasks = getOpenTasks();
        Comparator<Task> byTimeTracker = new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                long t1Time = t1.getTimeTracker().getCurrentTime();
                long t2Time = t2.getTimeTracker().getCurrentTime();
                return Long.compare(t1Time, t2Time);
            }
        };
        Collections.sort(openTasks, byTimeTracker);

        return openTasks;
    }



    /**
     * Get the name of the project
     *
     * @return String of the name of the project
     */
    public String getName() {
        return name;
    }

    /**
     * Method to add a task to the project
     * If the project has been marked as complete and an open task is added the
     * project will reopen.
     *
     * @param task Task to be added to the project task Replace type of parameter
     *             with proper task Class
     */
    public void addTask(Task task) {
        tasks.add(task);
        task.addListener(this);

        for (ProjectEventListener pe : this.observers) {
            pe.taskCreated(task);
        }
    }

    /**
     * Get all the tasks of the Project
     *
     * @return List of task of this project task Replace type of ArrayList with
     *         proper class
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Get all the tasks of this project that are closed.
     * Tells the ObjectMapper thet this field needs to be ignored (bcs its computed)
     *
     * @return List of closed tasks task Replace type of ArrayList with proper class
     */
    @JsonIgnore
    public ArrayList<Task> getClosedTasks() {
        ArrayList<Task> closedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTaskStatus().equals(TaskStatus.FINISHED)) { // task Replace with proper method
                closedTasks.add(task);
            }
        }
        return closedTasks;
    }

    public void addAllTaskListeners() {
        for (Task task : tasks) {
            task.addListener(this);
        }
    }

    /**
     * Remove a task from this project
     *
     * @param task task to be removed task Replace type of parameter with proper
     *             task Class
     */
    public void removeTask(Task task) {
        if (tasks.contains(task)) {
            tasks.remove(task);
        }

        for (ProjectEventListener pe : this.observers) {
            pe.taskDeleted(task);
        }
    }

    /**
     * Implementation of Observable
     * Add listener to the list of listeners to be notified
     *
     * @param listener InvalidationListener to add to the list
     *                 The listener to register
     */
    @Override
    public void addListener(ProjectEventListener listener) {
        if (listener != null && !this.observers.contains(listener)) {
            observers.add(listener);
        }
    }

    /**
     * Implementation of Observable
     * remove listener from the list of listeners to be notified
     *
     * @param listener InvalidationListener to remove from the list
     *                 The listener to remove
     */
    @Override
    public void removeListener(ProjectEventListener listener) {
        if (observers.contains(listener)) {
            observers.remove(listener);
        }
    }

    /**
     * Implementation of TaskEvent
     * Called when the state of a task has changed.
     *
     * @param task the task whose state has changed
     */
    @Override
    public void taskStateChanged(Task task) {
        for (ProjectEventListener pe : this.observers) {
            pe.taskStateChange(task);
            if (this.getOpenTasks().size() == 0) {
                pe.allTasksFinished();
            }
        }
    }

    /**
     * Implementation of TaskEvent
     * Called when a request is made to delete a task.
     *
     * @param t the task to be deleted
     */
    @Override
    public void deleteRequest(Task t) {
        this.removeTask(t);
    }

}
