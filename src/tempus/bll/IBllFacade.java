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

/**
 *
 * @author dpank
 */
public interface IBllFacade {

    

   

    

    
    
     public User getUser(String username, String password);

    public void createProject(String name, String clientName, String hRate, String description);

    public void deleteProject(Project  pro);

    public List<Client> getAllClientss();

    public List<Project> getAllProjects();


    public void deleteUser(User useDelete);

    public List<User> getAllUsers();


    
}
