/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.model;

import java.util.List;
import tempus.be.Project;
import tempus.bll.BllManager;
import tempus.bll.IBllFacade;
import static tempus.gui.model.UserModel.model;

/**
 *
 * @author dpank
 */
public class ProjectModel {
    
 static ProjectModel projmodel = new ProjectModel();
 private final IBllFacade facade;
 
  public static ProjectModel getInstance() {
        return projmodel;
    }
    
   public ProjectModel(){
        this.facade = new BllManager();
    }

    
    public void createProject(String projectName, String clientName, String hourlyRate, String description) {
        
        facade.createProject(projectName, clientName, hourlyRate, description);
    }

    public void deleteProject(String projectName, String clientName, String hourlyRate, String description) {
        facade.deleteProject(projectName, clientName, hourlyRate, description);
    }
      
     public List<Project> getAllProjects() {
        return facade.getAllProjects();
          
    }
    
}
