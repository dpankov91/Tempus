/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.bll;

import java.util.List;

import tempus.be.Client;



import tempus.be.Project;

import tempus.be.User;
import tempus.bll.security.ISecurityManager;
import tempus.bll.security.SecurityManager;

import tempus.dal.DalManager;
import tempus.dal.IDalFacade;

/**
 *
 * @author dpank
 */
public class BllManager implements IBllFacade {
    
    IDalFacade facade;
    ISecurityManager securityManager;

    public BllManager() {   
        facade = new DalManager();
        securityManager = new SecurityManager();
    }

    @Override
    public User getUser(String username, String password) {
        String hashedPassword = securityManager.hashPassword(password);
        return facade.getUser(username, hashedPassword);
    }

    @Override
    public void createProject(String projectName, String clientName, String hourlyRate, String description) {
        
        facade.createProject(projectName, clientName, hourlyRate, description);
                
    }

    @Override
    public void deleteProject(Project projectToDelete) {
        facade.deleteProject(projectToDelete);
    }

    @Override

    public List<Project> getAllProjects() {
      return facade.getAllProjects();
    }


    @Override
    public List<Client> getAllClientss() 
    {
        return facade.getAllClientss();
    }

    @Override
    public List<User> getAllUsers() {
        return facade.getAllUsers();
    }




    
}
