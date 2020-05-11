/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.bll;

import java.io.IOException;
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
        facade.deleteProject(projectToDelete);
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
        facade.deleteUser(userToDelete);
    }

    @Override
    public List<User> getAllUsers() {
        return facade.getAllUsers();
    }

    @Override
    public void createUser(String fName, String lName, String password, String email, String role, String address, int phone, int postcode) {
        facade.createUser(fName, lName, password, email, role, address, phone, postcode);
    }

    @Override
    public void editUser(int id, String name, String Lname, String email, int realphone, int realpostcode, String address) {
        facade.editUser(id, name, Lname, email, realphone, realpostcode, address);
    }

    @Override
    public void editProject(int id, String projectName, String clientName, String hourlyRate, String description) {
        facade.editProject( id,projectName, clientName, hourlyRate, description);
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
    public List<Task> getTasksOfSelectedProject(Project selectedProject) {
        return facade.getTasksOfSelectedProject(selectedProject);
    }

 

}
