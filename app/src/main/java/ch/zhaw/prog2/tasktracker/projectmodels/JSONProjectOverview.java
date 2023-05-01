package ch.zhaw.prog2.tasktracker.projectmodels;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.zhaw.prog2.tasktracker.Observerable.ObservableProjectOverview;
import ch.zhaw.prog2.tasktracker.Observerable.ProjectOverviewEventListener;
import ch.zhaw.prog2.tasktracker.project.Project;


/*
 * This class represents the JSON Project Overwiev and how we read and write data to files.
 * This class is an observerable and holds the data of our filebased Database that contains JSON Datascrutures
 */
public class JSONProjectOverview implements ProjectOverview, ObservableProjectOverview {
    //Our list of observers that listen to changes this class
    private ArrayList<ProjectOverviewEventListener> observers = new ArrayList<>();
    //our database file
    private File databaseFile = null;
    //Thee list of our projects
    private List<Project> projectList;
    //mapped text to objects and back. Like a translator for our data to JSON.
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * This is the constructor that sets the default file for oure filebased dadabase
     * @throws IOException
     */
    public JSONProjectOverview() throws IOException {
        this(new File("./database.json"));
    }
    
    /**
     * This constructor takes a file that loads a filebased database filled with JSON
     * If there is no file we create a file with an empty ArrayList by default
     * 
     * We create an Arraylist that cointains the list that the mapper readed from the list we got from the file.(optimistic guess)
     * @param file
     * @throws IOException
     */
    public JSONProjectOverview(File file) throws IOException {
        this.databaseFile = file;
        if(!file.exists()) {
            file.createNewFile();
            this.projectList = new ArrayList<>();
            this.save();
        } else {
            try {
                projectList = new ArrayList<>(objectMapper.readValue(file, new TypeReference<List<Project>>(){}));

                for(Project p : projectList) {
                    p.addAllTaskListeners();
                }
            } catch(Exception e) {
                e.printStackTrace();
                this.projectList = new ArrayList<>();
            }
        }
        System.out.println("Database file is: "+file.getAbsolutePath());
    }

    /**
     * Saved the serialized informations from the classes into that project list 
     * and translates this to JSON to save it in our database file
     * @throws StreamWriteException 
     * @throws DatabindException
     * @throws IOException
     */
    public void saveDatabase() throws StreamWriteException, DatabindException, IOException {
        objectMapper.writeValue(databaseFile, projectList);
    }

    /*
     * Leads the information to the saveDatabase() function.
     * Like a wrapper to handle the exceptions
     */
    @Override
    public void save(){
        try {
            this.saveDatabase();
        } catch (IOException e) {
            System.err.println("Error Saving Database");
            e.printStackTrace();
        }
    }

    /*
     * Adds a project to our projectList and informs all listeners that there is an project created
     * Aftet we save the new state to our databasefile
     */
    @Override
    public void addProject(Project project){
        if(project != null) {
            projectList.add(project);

            for(ProjectOverviewEventListener poe : this.observers) {
                poe.projectCreated(project);
            }

            try {
                this.saveDatabase();
            } catch (IOException e) {
                System.err.println("Error Saving Database");
                e.printStackTrace();
            }
        }
    }

    /*
     * Here we are able to delete a project, after checking if this project exists.
     * Wen inform all listeners that the project was deleted.
     * We safe the new state to our database file
     */
    @Override
    public void removeProject(Project project){
        if(projectList.contains(project)){
            projectList.remove(project);
        }

        for(ProjectOverviewEventListener poe : this.observers) {
            poe.projectDeleted(project);
        }

        try {
            this.saveDatabase();
        } catch (IOException e) {
            System.err.println("Error Saving Database");
            e.printStackTrace();
        }
    }

    /*
     * Getter for the projectList
     */
    @Override
    public List<Project> getProjectList(){
        return new ArrayList<>(projectList);
    }

    /*
     * Adds a listener to our observer list if the listener is not null and not already observer
     */
    @Override
    public void addListener(ProjectOverviewEventListener listener) {
        if (listener != null && !this.observers.contains(listener)) {
            observers.add(listener);
        }
    }

    /*
     * Remove a listener from our observer list 
     */
    @Override
    public void removeListener(ProjectOverviewEventListener listener) {
        if (observers.contains(listener)) {
            observers.remove(listener);
        }
    }
}
