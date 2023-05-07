package ch.zhaw.prog2.tasktracker.projectmodels;

import java.util.List;

import ch.zhaw.prog2.tasktracker.project.Project;

public interface ProjectOverview {
    
    /**
     * Add Project to the list
     * @param project Project to be added to the list
     */
    public void addProject(Project project);

    /**
     * remove a project from the list again
     * @param project project to be removed from the list
     */
    public void removeProject(Project project);

    /**
     * Return list of projects in the overview
     * @return ArrayList of Projects from the projectList
     */
    public List<Project> getProjectList();

    /**
     * Return list of projects in the overview
     * @return ArrayList of Projects from the projectList
     */
    public void save();
}
