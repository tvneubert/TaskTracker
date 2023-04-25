package ch.zhaw.prog2.tasktracker.task;

import ch.zhaw.prog2.tasktracker.project.Project;

import java.util.ArrayList;

public class DummyProjectOverview {

    /**
     * List of projects
     * Something similar will have to be in proper ProjectOverview
     */
    private ArrayList<Project> projectList = new ArrayList<>();

    /**
     * Constructor for the DummmyProjectOverview
     * Adds some placeholder projects for testing
     */
    public DummyProjectOverview(){

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

    /**
     * Add Project to the list
     * @param project Project to be added to the list
     */
    public void addProject(Project project){
        if(project != null) {
            projectList.add(project);
        }
    }

    /**
     * remove a project from the list again
     * @param project project to be removed from the list
     */
    public void removeProject(Project project){
        if(projectList.contains(project)){
            projectList.remove(project);
        }
    }

    /**
     * Return list of projects in the overview
     * @return ArrayList of Projects from the projectList
     */
    public ArrayList<Project> getProjectList(){
        return projectList;
    }
}
