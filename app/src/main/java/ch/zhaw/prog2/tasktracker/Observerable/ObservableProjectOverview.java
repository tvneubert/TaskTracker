package ch.zhaw.prog2.tasktracker.Observerable;

public interface ObservableProjectOverview {
    /**
     * Adds the listener
     * 
     * @param listener the listener that has been added
     */
    public void addListener(ProjectOverviewEventListener listener);

    /**
     * Removes the listener
     * 
     * @param listener the listener that has been removed
     */
    public void removeListener(ProjectOverviewEventListener listener);
}
