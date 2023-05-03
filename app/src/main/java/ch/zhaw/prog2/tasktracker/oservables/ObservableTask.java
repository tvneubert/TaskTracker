package ch.zhaw.prog2.tasktracker.oservables;
/**
 * This is the Oberserver Pattern for the Project, 
 * it helps other Task to stay informed about changes
 */
public interface ObservableTask {

       /**
     * Adds the listener
     * @param listener
     */
    public void addListener(TaskEvent listener);

     /**
     * Removes the listener
     * @param listener
     */
    public void removeListener(TaskEvent listener);
}
