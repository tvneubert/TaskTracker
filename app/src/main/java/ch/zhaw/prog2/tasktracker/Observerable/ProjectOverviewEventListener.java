package ch.zhaw.prog2.tasktracker.Observerable;

import ch.zhaw.prog2.tasktracker.project.Project;

/*
 * This interface is an Listener for creating and deleting Projects
 */
public interface ProjectOverviewEventListener {

    /**
     * gets called when a project has been created
     * 
     * @param p the created project
     */
    public void projectCreated(Project p);

    /**
     * gets called when a project has been deleted
     * 
     * @param p the deleted project
     */
    public void projectDeleted(Project p);
}
