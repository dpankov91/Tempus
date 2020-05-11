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
 *
 * @author dpank
 */
public class ProjectModel {
    
 static ProjectModel projmodel = new ProjectModel() ;
 private final ObservableList<Project> projectList = FXCollections.observableArrayList();
 
 private final IBllFacade facade;
 
    private Project selectedProject;
    
   public static ProjectModel getInstance() 
   {
        return projmodel;
    }
    
   public ProjectModel() {
       
        this.facade = new BllManager();
        projectList.addAll(facade.getAllProjects());
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    
    public void createProject(String name, Client client, int hRate, String description) {
        
        facade.createProject(name, client, hRate, description);
    }

    public void deleteProject(Project pToDelete) {
        facade.deleteProject(pToDelete);
    }
      
     public List<Project> getAllProjects() {
        return facade.getAllProjects();
          
    }

    public void deleteSelectedProject() {
        facade.deleteProject(selectedProject);
    }

    public void editProject(int id,String projectName, String clientName, String hourlyRate, String description) {
     facade.editProject(id,projectName, clientName, hourlyRate, description);
    }

    public ObservableList<Project> getObsProjects() {
        return projectList;
    }

    public List<Project> getAllProjectsOverview() {
        return facade.getAllProjectsOverview();
    }

}
