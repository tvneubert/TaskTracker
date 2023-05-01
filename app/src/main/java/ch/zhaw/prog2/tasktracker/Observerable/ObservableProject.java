package ch.zhaw.prog2.tasktracker.Observerable;

/**
 * This is the Oberserver Pattern for the Project,
 * it helps other Objects to stay informed about changes
 */
public interface ObservableProject {

    /**
     * Adds the listener
     * 
     * @param listener the listener that has been added
     */
    public void addListener(ProjectEventListener listener);

    /**
     * Removes the listener
     * 
     * @param listener the listener that has been removed
     */
    public void removeListener(ProjectEventListener listener);
}
