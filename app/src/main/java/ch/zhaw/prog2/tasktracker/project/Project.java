package ch.zhaw.prog2.tasktracker.project;

import ch.zhaw.prog2.tasktracker.oservables.ObservableProject;
import ch.zhaw.prog2.tasktracker.oservables.ProjectEvent;
import ch.zhaw.prog2.tasktracker.oservables.TaskEvent;
import ch.zhaw.prog2.tasktracker.task.Task;
import ch.zhaw.prog2.tasktracker.task.Task.TaskStatus;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class to represent a project.
 * Has a list of tasks and keeps track of time used for each task
 */

public class Project implements ObservableProject, TaskEvent {
    private ArrayList<ProjectEvent> observers = new ArrayList<>();

    private ArrayList<Task> tasks = new ArrayList<>();
    private String name;

    /**
     * Constructor for a new project.
     * @param name String for the name of the Project
     */
    public Project(String name){
        this.name = name;
    }

    /**
     * Return the state of the project
     * @return boolean of the pjoject.
     */
    public boolean isCompleted(){
        for(Task task : tasks){
            if(!task.getTaskStatus().equals(TaskStatus.FINISHED)) { // task Replace with correct method of task class
                return false;
            }
        }
        return true;
    }

    /**
     * Method to return the time already spent on this project.
     * @return Time spent on the project task Replace return value with proper type
     */

    public int getTotalTaskTime(){
        int time = 0;
        for(Task task : tasks){
            time += task.getTimeTracker().getCurrentTime();
        }
        return time;
    }

    /**
     * Return only the open tasks associated with this projcet
     * @return List of task objects task Replace type of ArrayList with proper class
     */
    public ArrayList<Task> getOpenTasks(){
        ArrayList<Task> openTasks = new ArrayList<>();
        if(tasks.size() != 0) {
            for (Task task : tasks) {
                if (!task.getTaskStatus().equals(TaskStatus.FINISHED)) { // task Replace with correct method of task class
                    openTasks.add(task);
                }
            }
        }
        return openTasks;
    }

    /**
     * Get the name of the project
     * @return String of the name of the project
     */
    public String getName(){
        return name;
    }

    /**
     * Method to add a task to the project
     * If the project has been marked as complete and an open task is added the project will reopen.
     * @param task Task to be added to the project task Replace type of parameter with proper task Class
     */
    public void addTask(Task task){
        tasks.add(task);
        task.addListener(this);

        for(ProjectEvent pe : this.observers) {
            pe.taskCreated(task);
        }
    }

    /**
     * Get all the tasks of the Project
     * @return List of task of this project task Replace type of ArrayList with proper class
     */
    public ArrayList<Task> getTasks(){
        return tasks;
    }

    /**
     * Get all the tasks of this project that are closed.
     * @return List of closed tasks task Replace type of ArrayList  with proper class
     */
    public ArrayList<Task> getClosedTasks(){
        ArrayList<Task> closedTasks = new ArrayList<>();
        for(Task task : tasks){
            if(task.getTaskStatus().equals(TaskStatus.FINISHED)){       //task Replace with proper method
                closedTasks.add(task);
            }
        }
        return closedTasks;
    }

    /**
     * Remove a task from this project
     * @param task task to be removed task Replace type of parameter with proper task Class
     */
    public void removeTask (Task task){
        if(tasks.contains(task)){
            tasks.remove(task);
        }
        
        for(ProjectEvent pe : this.observers) {
            pe.taskDeleted(task);
        }
    }

    /**
     * Implementation of Observable
     * Add listener to the list of listeners to be notified
     * @param listener InvalidationListener to add to the list
     *            The listener to register
     */
    @Override
    public void addListener(ProjectEvent listener) {
        if(listener != null && !this.observers.contains(listener)) {
            observers.add(listener);
        }
    }
    /**
     * Implementation of Observable
     * remove listener from the list of listeners to be notified
     * @param listener InvalidationListener to remove from the list
     *            The listener to remove
     */
    @Override
    public void removeListener(ProjectEvent listener) {
        if(observers.contains(listener)){
            observers.remove(listener);
        }
    }

    @Override
    public void taskStateChanged(Task task) {
        for(ProjectEvent pe : this.observers) {
            pe.taskStateChange(task);
            if(this.getOpenTasks().size() == 0) {
                pe.allTasksFinished();
            }
        }
    }

    @Override
    public void deleteRequest(Task t) {
        this.removeTask(t);
    }
}
