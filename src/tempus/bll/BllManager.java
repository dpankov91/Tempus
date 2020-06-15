/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.bll;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import tempus.be.Client;

import tempus.be.Project;
import tempus.be.Task;

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
//BLL manager implements BLL interface abstract methods, inheirting them, 
//which means all methods in this class are overridden
//Facade Pattern is used for better passage of information.
    IDalFacade facade;
    ISecurityManager securityManager;

    /**
     *
     */
    public BllManager() {
        facade = new DalManager();
        securityManager = new SecurityManager();
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User getUser(String username, String password) {
        String hashedPassword = securityManager.hashPassword(password);
        return facade.getUser(username, hashedPassword);
    }

    /**
     *
     * @param name
     * @param client
     * @param hRate
     * @param description
     */
    @Override
    public void createProject(String name, Client client, int hRate, String description) {
        //the project to be created with its parameters is send to the DAL layer
        facade.createProject(name, client, hRate, description);
    }

    /**
     *
     * @param projectToDelete
     */
    @Override
    public void deleteProject(Project projectToDelete) {
        facade.deleteProject(projectToDelete); 
    // Like before, the project for deletion is sent down to the layer under, to the dalinterface.
    }

    /**
     *
     * @return
     */
    @Override

    public List<Project> getAllProjects() {
        return facade.getAllProjects();
    }

    /**
     *
     * @return
     */
    @Override
    public List<Client> getAllClientss() {
        return facade.getAllClientss();
    }

    /**
     *
     * @param userToDelete
     */
    @Override
    public void deleteUser(User userToDelete) {
        facade.deleteUser(userToDelete); 
// Like before, the user selected for deletion is sent down to the layer under, to the dal.
    }

    /**
     *
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        return facade.getAllUsers();
    }

    /**
     *
     * @param fName
     * @param lName
     * @param password
     * @param email
     * @param role
     * @param address
     * @param phone
     * @param postcode
     */
    @Override
    public void createUser(String fName, String lName, String password, String email, String role, String address, int phone, int postcode) {
        String hashedPassword = securityManager.hashPassword(password);
        // the user that is to be created with its parameters is send to the DAL layer
        facade.createUser(fName, lName, hashedPassword, email, role, address, phone, postcode);
    }

    /**
     *
     * @param id
     * @param name
     * @param Lname
     * @param email
     * @param realphone
     * @param realpostcode
     * @param address
     * @param imageURL
     * @param password
     */
    @Override
    public void editUser(int id, String name, String Lname, String email, int realphone, int realpostcode, String address, String imageURL, String password) {
        facade.editUser(id, name, Lname, email, realphone, realpostcode, address, imageURL, password);
    }

    /**
     *
     * @param id
     * @param projectName
     * @param clientName
     * @param hourlyRate
     * @param description
     */
    @Override
    public void editProject(int id, String projectName, String clientName, int hourlyRate, String description) {
        facade.editProject(id, projectName, clientName, hourlyRate, description);
    }

    /**
     *
     * @param selectedProject
     * @param usersAssign
     */
    @Override
    public void assignUsersToProj(Project selectedProject, List<User> usersAssign) {
        facade.assignUsersToProj(selectedProject, usersAssign);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Task> getAllTasksOverview() {
        return facade.getAllTasksOverview();
    }

    /**
     *
     * @param selectedClient
     */
    @Override
    public void deleteClient(Client selectedClient) { // Like before, the client selected for deletion is sent down to the layer under, to the dal.
        facade.deleteClient(selectedClient);
    }

    /**
     *
     * @param name
     * @param city
     * @param phone
     * @param email
     */
    @Override
    public void createClient(String name, String city, int phone, String email) {
        // the client to be created with its parameters is send to the DAL layer
        facade.createClient(name, city, phone, email);
    }

    /**
     *
     * @param password
     * @return
     */
    @Override
    public User getPassword(String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param pswSecond
     * @param userID
     */
    @Override
    public void newPassword(String pswSecond, int userID) {
    // The inputted password is changed into a hash value, by using the hashPassword method from the security manager.
    // Encrypting it through SHA-256 hash Algorithm.
        String newPassword = securityManager.hashPassword(pswSecond);
    // It takes the new password down a layer again, into the dal manager.
        facade.newPassword(newPassword, userID);
    }

    /**
     *
     * @param id
     * @param name
     * @param city
     * @param phone
     * @param email
     */
    @Override
    public void editClient(int id, String name, String city, int phone, String email) {
        facade.editClient(id, name, city, phone, email);
    }

    /**
     *
     * @param id
     * @param name
     * @param startTime
     * @param endTime
     * @param note
     * @param spentTime
     */
    @Override
    public void editTask(int id, String name, LocalDateTime startTime, LocalDateTime endTime, String note, double spentTime) {
        facade.editTask(id, name, startTime, endTime, note, spentTime);
    }

    private long getSecondsFromTimespan(LocalDateTime startTime, LocalDateTime endTime){
        Duration dur = Duration.between(startTime, endTime);
        return dur.toSeconds();
       // String.format("%02d:%02d:%02d", dur.toHoursPart(), dur.toMinutesPart(), dur.toSecondsPart());
    }
    
    /**
     *
     * @param selectedProject
     * @param taskName
     * @param note
     * @param loggedUser
     * @param startTime
     * @param endTime
     */
    @Override
    public void saveStoppedTask(Project selectedProject, String taskName, String note, User loggedUser, LocalDateTime startTime, LocalDateTime endTime) {
        facade.saveStoppedTask(selectedProject, taskName, note, loggedUser, startTime, endTime, getSecondsFromTimespan(startTime, endTime));
    }

    /**
     *
     * @param selectedTask
     */
    @Override
    public void deleteTask(Task selectedTask) {
         facade.deleteTask(selectedTask);
    }

    /**
     *
     * @param pswSecond
     * @param id
     */
    @Override
    public void newPasswordForSelectedUser(String pswSecond, int id) {
        String newPassword = securityManager.hashPassword(pswSecond); // The inputted password is changed into a hash value, by using the security method from the security manager.
        facade.newPasswordForSelectedUser(newPassword, id); // It takes the inew password down a layer again, into the dal manager.
    }

}
