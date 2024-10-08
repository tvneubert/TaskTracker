package ch.zhaw.prog2.tasktracker.project;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

import ch.zhaw.prog2.tasktracker.projectmodels.ProjectOverview;

/**
 * This class is responsible for controlling the "Create Project" window of the
 * application.
 */
public class CreateProjectController implements Observable {

    /**
     * Submit button to create a new Project.
     */
    @FXML
    private Button newProjectSubmitButton;

    /**
     * TextArea to enter a name for the Project.
     */
    @FXML
    private TextField newProjectTextField;

    /**
     * ProjectOverview this new Project will be added to
     */
    private ProjectOverview rootProjectOverview;
    /**
     * List of observers
     * Required for the implementation of Observable
     */
    private ArrayList<InvalidationListener> observers = new ArrayList<>();


    /**
     * This method is called when the user clicks the "Create Project" button.
     * It creates a new project and closes the window.
     * <p>
     * task: Implement the creation of a new project.
     *
     * @param event The ActionEvent triggered by the user clicking the "Create
     *              Project" button.
     */
    @FXML
    public void createProject(ActionEvent event) {
        String emptyName = "Bitten geben Sie dem Projekt einen Namen";
        // task add to ProjectOverview list
        if (newProjectTextField == null || newProjectTextField.toString().trim() == ""
                || newProjectTextField.toString().equals(emptyName)) {
            newProjectTextField.setText(emptyName);
        } else {
            Project project = new Project(newProjectTextField.getText());
            System.out.println("Created new project " + project.getName());
            rootProjectOverview.addProject(project);
            for (Project logProject : rootProjectOverview.getProjectList()) {
                System.out.println(logProject.getName());
            }
        }

        // Close window after creation
        Stage stage = (Stage) newProjectSubmitButton.getScene().getWindow();

        stage.close();
        notifyListeners();
    }

    /**
     * Set the ProjectOverview this task will be added to
     *
     * @param projectOverview Project to add this task to
     */
    public void setRootProjectOverview(ProjectOverview projectOverview) {
        if (projectOverview != null) {
            rootProjectOverview = projectOverview;
        }
    }

    /**
     * Implementation of Observable
     * Add listener to the list of listeners to be notified
     *
     * @param listener InvalidationListener to add to the list
     *                 The listener to register
     */
    @Override
    public void addListener(InvalidationListener listener) {
        if (listener != null) {
            observers.add(listener);
        }
    }

    /**
     * Implementation of Observable
     * remove listener from the list of listeners to be notified
     *
     * @param listener InvalidationListener to remove from the list
     *                 The listener to remove
     */
    @Override
    public void removeListener(InvalidationListener listener) {
        if (observers.contains(listener)) {
            observers.remove(listener);
        }
    }

    /**
     * Required for the function of Observable
     * Loop though all listeners and notify them all
     */
    private void notifyListeners() {
        for (InvalidationListener listener : observers) {
            listener.invalidated(this);
        }
    }

}
