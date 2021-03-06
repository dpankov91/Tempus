/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * Provides a simplified interface 
 * and open the template in the editor.
 */
package tempus.bll;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


import tempus.be.Client;



import tempus.be.Project;
import tempus.be.Task;

import tempus.be.User;

/**
 *
 * @author dpank
 */
public interface IBllFacade {

    public User getUser(String username, String password);
    
     public User getPassword(String password);

    public void createProject(String name, Client client, int hRate, String description);

    public void deleteProject(Project  pro);

    public List<Client> getAllClientss();

    public List<Project> getAllProjects();

    public void deleteUser(User useDelete);

    public List<User> getAllUsers();

    public void createUser(String fName, String lName, String password, String email, String role, String address, int phone, int postcode);

    public void editUser(int id, String name, String Lname, String email, int realphone, int realpostcode, String address, String imageURL, String password);

    public void editProject(int id,String projectName, String clientName, int hourlyRate, String description);

    public void assignUsersToProj(Project selectedProject, List<User> usersAssign);

    public List<Task> getAllTasksOverview();

    public void deleteClient(Client selectedClient);

    public void createClient(String name, String city, int phone, String email);

    public void newPassword(String pswSecond, int userID);

    public void editClient(int id, String name, String city, int phone, String email);
   
    public void editTask(int id, String name, LocalDateTime startTime, LocalDateTime endTime, String note, double spentTime);

    public void saveStoppedTask(Project selectedProject, String taskName, String note, User loggedUser, LocalDateTime startTime, LocalDateTime endTime);

    public void deleteTask(Task selectedTask);

    public void newPasswordForSelectedUser(String pswSecond, int id);
    
}
