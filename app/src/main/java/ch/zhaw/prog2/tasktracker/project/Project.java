package ch.zhaw.prog2.tasktracker.project;

import ch.zhaw.prog2.tasktracker.task.Task;
import ch.zhaw.prog2.tasktracker.task.Task.TaskStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Class to represent a project.
 * Has a list of tasks and keeps track of time used for each task
 */

public class Project{
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
    }
}
