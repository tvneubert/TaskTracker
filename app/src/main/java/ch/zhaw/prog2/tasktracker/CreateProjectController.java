package ch.zhaw.prog2.tasktracker;

import ch.zhaw.prog2.tasktracker.todo.DummyProjectOverwiev;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * This class is responsible for controlling the "Create Project" window of the
 * application.
 */
public class CreateProjectController {

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
    private DummyProjectOverwiev rootProjectOverview;

    /**
     * This method is called when the user clicks the "Create Project" button.
     * It creates a new project and closes the window.
     *
     * TODO: Implement the creation of a new project.
     *
     * @param event The ActionEvent triggered by the user clicking the "Create
     *              Project" button.
     */
    @FXML
    void createProject(ActionEvent event) {
        String emptyName = "Bitten geben Sie dem Projekt einen Namen";
        // TODO add to ProjectOverview list
        if(newProjectTextField == null || newProjectTextField.toString().trim() == "" || newProjectTextField.toString().equals(emptyName)){
            newProjectTextField.setText(emptyName);
        }else{
            Project project = new Project(newProjectTextField.toString());
            rootProjectOverview.addProject(project);
        }

        // Close window after creation
        Stage stage = (Stage) newProjectSubmitButton.getScene().getWindow();
        stage.close();
    }
    public void setRootProjectOverview(DummyProjectOverwiev projectOverwiev){
        if(projectOverwiev != null){
            rootProjectOverview = projectOverwiev;
        }
    }

}
