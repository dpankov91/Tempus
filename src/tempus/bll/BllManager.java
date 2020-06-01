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
    public void createProject(String name, Client client, int hRate, String description) {

        facade.createProject(name, client, hRate, description);
    }

    @Override
    public void deleteProject(Project projectToDelete) {
        facade.deleteProject(projectToDelete); // Like before, the project for deletion is sent down to the layer under, to the dal.
    }

    @Override

    public List<Project> getAllProjects() {
        return facade.getAllProjects();
    }

    @Override
    public List<Client> getAllClientss() {
        return facade.getAllClientss();
    }

    @Override
    public void deleteUser(User userToDelete) {
        facade.deleteUser(userToDelete); // Like before, the user selected for deletion is sent down to the layer under, to the dal.
    }

    @Override
    public List<User> getAllUsers() {
        return facade.getAllUsers();
    }

    @Override
    public void createUser(String fName, String lName, String password, String email, String role, String address, int phone, int postcode) {
        String hashedPassword = securityManager.hashPassword(password);
        facade.createUser(fName, lName, hashedPassword, email, role, address, phone, postcode);
    }

    @Override
    public void editUser(int id, String name, String Lname, String email, int realphone, int realpostcode, String address, String imageURL, String password) {
        facade.editUser(id, name, Lname, email, realphone, realpostcode, address, imageURL, password);
    }

    @Override
    public void editProject(int id, String projectName, String clientName, int hourlyRate, String description) {
        facade.editProject(id, projectName, clientName, hourlyRate, description);
    }

    @Override
    public void assignUsersToProj(Project selectedProject, List<User> usersAssign) {
        facade.assignUsersToProj(selectedProject, usersAssign);
    }

    @Override
    public List<Task> getAllTasksOverview() {
        return facade.getAllTasksOverview();
    }

    @Override
    public void deleteClient(Client selectedClient) { // Like before, the client selected for deletion is sent down to the layer under, to the dal.
        facade.deleteClient(selectedClient);
    }

    @Override
    public void createClient(String name, String city, int phone, String email) {
        facade.createClient(name, city, phone, email);
    }

    @Override
    public User getPassword(String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void newPassword(String pswSecond, int userID) {
        String newPassword = securityManager.hashPassword(pswSecond);
        facade.newPassword(newPassword, userID);
    }

    @Override
    public void editClient(int id, String name, String city, int phone, String email) {
        facade.editClient(id, name, city, phone, email);
    }

    @Override
    public void editTask(int id, String name, LocalDateTime startTime, LocalDateTime endTime, String note, double spentTime) {
        facade.editTask(id, name, startTime, endTime, note, spentTime);
    }

    private long getSecondsFromTimespan(LocalDateTime startTime, LocalDateTime endTime){
        Duration dur = Duration.between(startTime, endTime);
        return dur.toSeconds();
       // String.format("%02d:%02d:%02d", dur.toHoursPart(), dur.toMinutesPart(), dur.toSecondsPart());
    }
    
    @Override
    public void saveStoppedTask(Project selectedProject, String taskName, String note, User loggedUser, LocalDateTime startTime, LocalDateTime endTime) {
        facade.saveStoppedTask(selectedProject, taskName, note, loggedUser, startTime, endTime, getSecondsFromTimespan(startTime, endTime));
    }

    @Override
    public void deleteTask(Task selectedTask) {
         facade.deleteTask(selectedTask);
    }

    @Override
    public void newPasswordForSelectedUser(String pswSecond, int id) {
        String newPassword = securityManager.hashPassword(pswSecond); // The inputted password is changed into a hash value, by using the security method from the security manager.
        facade.newPasswordForSelectedUser(newPassword, id); // It takes the inew password down a layer again, into the dal manager.
    }

}
