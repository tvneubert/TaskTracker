package ch.zhaw.prog2.tasktracker;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

/**
 * Class to represent a project.
 * Has a list of tasks and keeps track of time used for each task
 */

public class Project {
    //TODO Replace Object with the proper Task class
    private ArrayList<Object> tasks = new ArrayList<>();
    //TODO Replace float with the proper Time
    private float time;
    private boolean complete = false;
    private String name;

    /**
     * Constructor for a new project.
     * @param name String for the name of the Project
     */
    public Project(String name){
        this.name = name;
        this.time = 0;
    }

    /**
     * Return the state of the project
     * @return boolean of the pjoject.
     */
    public boolean isCompleted(){return complete;}

    /**
     * Internal method to add the time spent on each task
     */
    private void calculateTime(){
        for(Object task : tasks){
            //Placeholder TODO replace with proper method of the time class
            time += task.time();
        }
    }

    /**
     * Method to return the time already spent on this project.
     * @return Time spent on the project TODO Replace return value with proper type
     */

    public float getTime(){
        calculateTime();
        return time;
    }

    /**
     * Return only the open tasks associated with this projcet
     * @return List of task objects TODO Replace type of ArrayList with proper class
     */
    public ArrayList<Object> getOpenTasks(){
        ArrayList<Object> openTasks = new ArrayList<>();
        for(Object task : tasks){
            if(task.isOpen){ // TODO Replace with correct method of task class
                openTasks.add(task);
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
     * @param task Task to be added to the project TODO Replace type of parameter with proper task Class
     */
    public void addTask(Object task){
        if(complete){
            if(task.isComplete){ //TODO Replace with proper method
                complete = !complete;
            }

        }
        tasks.add(task);
    }

    /**
     * Get all the tasks of the Project
     * @return List of task of this project TODO Replace type of ArrayList with proper class
     */
    public ArrayList<Object> getTasks(){return tasks;}

    /**
     * Get all the tasks of this project that are closed.
     * @return List of closed tasks TODO Replace type of ArrayList  with proper class
     */
    public ArrayList<Object> getClosedTasks(){
        ArrayList<Object> closedTasks = new ArrayList<>();
        for(Object task : tasks){
            if(!task.isOpen){       //TODO Replace with proper method
                closedTasks.add(task);
            }
        }
        return closedTasks;
    }

    /**
     * Remove a task from this project
     * @param task task to be removed TODO Replace type of parameter with proper task Class
     */
    public void removeTask (Object task){
        if(tasks.contains(task)){
            tasks.remove(task);
        }
    }

}
