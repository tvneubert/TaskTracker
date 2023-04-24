package ch.zhaw.prog2.tasktracker.todo;

import ch.zhaw.prog2.tasktracker.Project;

import java.util.ArrayList;
import java.util.HashMap;

public class DummyProjectOverwiev {
    private ArrayList<Project> projectList = new ArrayList<>();


    public DummyProjectOverwiev(){

        projectList.add(new Project("Project 1"));
        projectList.add(new Project("Project 2"));
        projectList.add(new Project("Project 3"));
        projectList.add(new Project("Project 4"));
        projectList.add(new Project("Project 5"));
        projectList.add(new Project("Project 6"));
        projectList.add(new Project("Project 7"));
        projectList.add(new Project("Project 8"));
        projectList.add(new Project("Project 9"));
        projectList.add(new Project("TEst"));

    }

    public void addProject(Project project){
        if(project == null) {
            projectList.add(project);
        }
    }
    public void removeProject(Project project){
        if(projectList.contains(project)){
            projectList.remove(project);
        }
    }
    public ArrayList<Project> getProjectList(){
        return projectList;
    }
}
