package ch.zhaw.prog2.tasktracker.projectmodels;

import ch.zhaw.prog2.tasktracker.project.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JSONProjectOverviewTest {
    Project testproject1;
    Project testproject2;
    JSONProjectOverview projectOverview;
    Path databasefile = Paths.get(".\\database.json");
    @BeforeEach
    void setUp() {

        // Using regular Project objects as Mock objects cannot be properly serialized and cause errors when saving to json
        testproject1 = new Project("testproject1");
        testproject2 = new Project("testproject2");
        try {
            Files.deleteIfExists(databasefile);
            projectOverview = new JSONProjectOverview();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test that a Project can sucessfuly be addet to the projectOverview
     */
    @Test
    void addProject() {
        ArrayList<Project> expected = new ArrayList<>();
        projectOverview.addProject(testproject1);
        expected.add(testproject1);
        assertEquals(projectOverview.getProjectList(), expected);
    }

    @Test
    void save(){
        projectOverview.addProject(testproject1);
        projectOverview.save();
        assertTrue(Files.exists(databasefile));
    }

    @Test
    void removeProject() {
        ArrayList<Project> expected = new ArrayList<>();
        projectOverview.addProject(testproject1);
        projectOverview.addProject(testproject2);
        expected.add(testproject1);
        projectOverview.removeProject(testproject2);
        System.out.println(expected);
        assertEquals(projectOverview.getProjectList(), expected);
        projectOverview.removeProject(testproject1);
        expected.remove(testproject1);
        System.out.println(expected);
        assertEquals(projectOverview.getProjectList(), expected);

    }

    /**
     * Test that an empty list is returned when the projectOverview is empty
     */
    @Test
    void getEmptyProjectListWhenNoProject() {
        ArrayList<Project> expected = new ArrayList<>();
        assertEquals(projectOverview.getProjectList(), expected);
        projectOverview.addProject(testproject1);
        projectOverview.removeProject(testproject1);
        assertEquals(projectOverview.getProjectList(), expected);

    }
}