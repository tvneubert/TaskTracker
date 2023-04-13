package ch.zhaw.prog2.tasktracker;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The MainWindow class is the main entry point of the TaskTracker application.
 * It is responsible for opening the main window of the application.
 * The main window consists of a scroll pane that displays a list of projects,
 * and a button to create a new project.
 */
public class MainWindow extends Application {

    /**
     * The start method is called by the JavaFX runtime when the application is
     * launched.
     * 
     * @param primaryStage The primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        openMainWindow(primaryStage);
    }

    /**
     * This method loads the main window and displays it on the screen.
     * 
     * @param stage The primary stage for this application.
     */
    private void openMainWindow(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainWindow.fxml"));
        try {
            Pane rootNode = loader.load();

            // Add random Projects to scrollPane (FOR DEMONSTRATION ONLY!!)
            MainWindowController mainWindowController = loader.getController();
            mainWindowController.addProjectsToScrollPane();

            Scene scene = new Scene(rootNode);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error while loading FXML file: " + e.getMessage());
        }
    }
}
