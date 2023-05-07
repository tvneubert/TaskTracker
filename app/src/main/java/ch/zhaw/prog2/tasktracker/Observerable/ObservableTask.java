package ch.zhaw.prog2.tasktracker.Observerable;

/**
 * This is the Oberserver Pattern for the Project,
 * it helps other Task to stay informed about changes
 */
public interface ObservableTask {

  /**
   * Adds the listener
   * 
   * @param listener the listener that has been added
   */
  public void addListener(TaskEventListener listener);

  /**
   * Removes the listener
   * 
   * @param listener the listener thet has been removed
   */
  public void removeListener(TaskEventListener listener);
}
