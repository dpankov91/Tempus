/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.model;


import java.util.List;
import tempus.be.Client;


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

    
    public void createProject(String name, Client client, int hRate, String description) {
        
        facade.createProject(name, client, hRate, description);
    }

    public void deleteProject(Project pToDelete) {
        facade.deleteProject(pToDelete);
    }
      
     public List<Project> getAllProjects() {
        return facade.getAllProjects();
          
    }
    
}
