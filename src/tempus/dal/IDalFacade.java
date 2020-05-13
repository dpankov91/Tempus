/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.dal;

import java.util.List;


import tempus.be.Client;



import tempus.be.Project;
import tempus.be.Task;

import tempus.be.User;

/**
 *
 * @author dpank
 */
public interface IDalFacade {

    public User getUser(String username, String password);

    public void createProject(String name, Client client, int hRate, String description);

    public List<Project> getAllProjects();

    public void deleteProject(Project projectToDelete);

    public List<Client> getAllClientss();

    public void deleteUser(User userToDelete);

    public List<User> getAllUsers();

    public void createUser(String fName, String lName, String password, String email, String role, String address, int phone, int postcode);

    public void editUser(int id, String name, String Lname, String email, int realphone, int realpostcode, String address,String imageURL);

    public void editProject(int id,String projectName, String clientName, int hourlyRate, String description);
    
    public void assignUsersToProj(Project selectedProject, List<User> usersAssign);

    public List<Task> getAllTasksOverview();

    public void deleteClient(Client selectedClient);

    public void createClient(String name, String city, int phone, String email);


    
}
