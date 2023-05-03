package ch.zhaw.prog2.tasktracker.oservables;
/**
 * This is the Oberserver Pattern for the Project, 
 * it helps other Objects to stay informed about changes
 */
public interface ObservableProject {
    
    /**
     * Adds the listener
     * @param listener
     */
    public void addListener(ProjectEvent listener);
    
    /**
     * Removes the listener
     * @param listener
     */
    public void removeListener(ProjectEvent listener);
}
