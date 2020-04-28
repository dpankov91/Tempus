/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.model;

import tempus.bll.BllManager;
import tempus.bll.IBllFacade;

/**
 *
 * @author dpank
 */
public class ProjectModel {
    
    static ProjectModel model = new ProjectModel();
    private final IBllFacade facade;
    
    public static ProjectModel getInstance() {
        
        return model;
        
    }
    
    public ProjectModel(){
        
        this.facade = new BllManager();
        
    }
    
    public void createProject(String projectName, String clientName, String hourlyRate, String description) {
        
        facade.createProject(projectName, clientName, hourlyRate, description);
    }
      
    
}
