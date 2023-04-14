package ch.zhaw.prog2.tasktracker;

import javafx.application.Application;

/**
 * This class serves as the entry point of the TaskTracker application.
 * It launches the MainWindow of the application.
 */
public class App {

  /**
   * This is the main method that launches the application.
   *
   * @param args the command line arguments.
   */
  public static void main(String[] args) {
    /*ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(1);
    TimeTracker timeTracker = new TimeTracker();
    timeTracker.start();
    stpe.scheduleAtFixedRate(() -> {
      System.out.println(timeTracker.getCurrentTime());
    }, 0, 1, TimeUnit.SECONDS);*/

    Application.launch(MainWindow.class, args);
  }
}
