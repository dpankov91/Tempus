 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.model;


import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tempus.be.Client;


import tempus.be.Project;
import tempus.bll.BllManager;
import tempus.bll.IBllFacade;

/**
 * The ProjectModel is a model. gets and passes data about the projects to the BLL
 *
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Christian Hansen
 * @author Dmitri Pankov
 * @author Nebojsa Gutic
 * @author Tienesh Kanagarasan
 */
public class ProjectModel {
    
 static ProjectModel projmodel = new ProjectModel() ; // Uses variable for Singleton Pattern
 private final ObservableList<Project> projectList = FXCollections.observableArrayList();
 
 private final IBllFacade facade;
 
    private Project selectedProject; // instance variables of project class, to get information from it.
    
    /**
     *
     * @return
     */
    public static ProjectModel getInstance() 
   {
        return projmodel; // returns the class itself
    }
    
    /**
     *
     */
    public ProjectModel() {
       
        this.facade = new BllManager();
        projectList.addAll(facade.getAllProjects());
    }

    /**
     *
     * @return
     */
    public Project getSelectedProject() {
        return selectedProject; //returns the selected project.
    }

    /**
     *
     * @param selectedProject
     */
    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject; // Used to highlight and select a project.
        
    }

    /**
     *
     * @param name
     * @param client
     * @param hRate
     * @param description
     */
    public void createProject(String name, Client client, int hRate, String description) {
        
        facade.createProject(name, client, hRate, description);
    }

    /**
     *
     * @param pToDelete
     */
    public void deleteProject(Project pToDelete) {
        facade.deleteProject(pToDelete);
    }
      
    /**
     *
     * @return
     */
    public List<Project> getAllProjects() {
        return facade.getAllProjects();
          
    }

    /**
     *
     */
    public void deleteSelectedProject() {
        facade.deleteProject(selectedProject); 
    // Brings the intended project for deletion down a layer, into the BLL Interface first.
    // Taking it down the stack.
    }

    /**
     *
     * @param id
     * @param projectName
     * @param clientName
     * @param hourlyRate
     * @param description
     */
    public void editProject(int id,String projectName, String clientName, int hourlyRate, String description) {
     facade.editProject(id,projectName, clientName, hourlyRate, description);
    }

    /**
     *
     * @return
     */
    public ObservableList<Project> getObsProjects() {
        return projectList;
    }


}
