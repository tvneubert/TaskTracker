package ch.zhaw.prog2.tasktracker;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class Project {
    //TODO Replace Object with the proper Task class
    private ArrayList<Object> tasks = new ArrayList<>();
    //TODO Replace float with the proper Time
    private float time;
    private boolean complete = false;
    private String name;

    public Project(String name){
        this.name = name;
        this.time = 0;
    }
    public boolean isCompleted(){return complete;}
    private void calculateTime(){
        for(Object task : tasks){
            //Placeholder TODO replace with proper method
            time += task.time();
        }
    }
    //TODO Replace return value with proper type
    public float getTime(){
        calculateTime();
        return time;
    }
    public ArrayList<Object> getOpenTasks(){
        ArrayList<Object> openTasks = new ArrayList<>();
        for(Object task : tasks){
            if(task.isOpen){
                openTasks.add(task);
            }
        }
        return openTasks;
    }
    public String getName(){
        return name;
    }
    public void addTask(Object task){
        if(complete){
            complete = !complete;

        }
        tasks.add(task);
    }
    public ArrayList<Object> getTasks(){return tasks;}
    public ArrayList<Object> getClosedTasks(){
        ArrayList<Object> closedTasks = new ArrayList<>();
        for(Object task : tasks){
            if(!task.isOpen){
                closedTasks.add(task);
            }
        }
        return closedTasks;
    }
    public void removeTask (Object task){
        tasks.remove(task);
    }

}
