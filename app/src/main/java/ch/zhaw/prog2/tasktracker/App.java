package ch.zhaw.prog2.tasktracker;

import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.util.Duration;

/**
 * This class serves as the entry point of the TaskTracker application.
 * It launches the MainWindow of the application.
 */
public class App {

  public static final double timerRefreshRate = 1000.0/30.0;

  /**
   * This is the main method that launches the application.
   *
   * @param args the command line arguments.
   */
  public static void main(String[] args) {
    Application.launch(MainWindow.class, args);
  }
}
