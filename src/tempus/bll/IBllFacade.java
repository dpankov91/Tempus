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

    public void createProject(String name, Client client, int hRate, String description);

    public void deleteProject(Project  pro);

    public List<Client> getAllClientss();

    public List<Project> getAllProjects();


    public void deleteUser(User useDelete);

    public List<User> getAllUsers();

    public void createUser(String fName, String lName, String password, String email, User user);

    public void editUser(int id, String name, String Lname, String email, int realphone, int realpostcode, String address);


    
}
