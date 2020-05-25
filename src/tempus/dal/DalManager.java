/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.dal;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import tempus.be.Project;

import tempus.be.Client;

import tempus.be.Project;
import tempus.be.Task;

import tempus.be.User;
import tempus.dal.dao.ClientDAO;
import tempus.dal.dao.ProjectDAO;
import tempus.dal.dao.TaskDAO;
import tempus.dal.dao.UserDAO;

/**
 *
 * @author dpank
 */
public class DalManager implements IDalFacade {

    UserDAO userDao;
    ProjectDAO projectDao;
    ClientDAO clientDao;
    TaskDAO taskDao;

    public DalManager(){
        userDao = new UserDAO();
        try {
            projectDao = new ProjectDAO();
        } catch (IOException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        clientDao = new ClientDAO();
        try {
            taskDao = new TaskDAO();
        } catch (IOException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User getUser(String username, String password) {
        try {
            return userDao.getUser(username, password);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void createProject(String name, Client client, int hRate, String description) {

        projectDao.createProject(name, client, hRate, description);

    }

    @Override
    public void deleteProject(Project projectToDelete) {
        projectDao.deleteProject(projectToDelete);
    }

    @Override
    public List<Client> getAllClientss() {

        try {
            List<Client> allClientss = clientDao.getAllClientss();
            return allClientss;

        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> allProjects = null;
        try {
            allProjects = projectDao.getAllProjects();
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allProjects;
    }

    @Override

    public User deleteUser(User userToDelete) {
       return userDao.deleteUser(userToDelete);
    }

    public List<User> getAllUsers() {
        List<User> allUsers = null;
        try {
            allUsers = userDao.getAllUsers();
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allUsers;

    }

    @Override
    public User createUser(String fName, String lName, String hashedPassword, String email, String role, String address, int phone, int postcode) {
      return  userDao.createUser(fName, lName, hashedPassword, email, role, address, phone, postcode);
    }

    @Override
    public User editUser(int id, String name, String Lname, String email, int realphone, int realpostcode, String address, String imageURL, String password) {
       return userDao.editUser(id, name, Lname, email, realphone, realpostcode, address, imageURL, password);
    }

    @Override
    public void editProject(int id,String projectName, String clientName, int hourlyRate, String description) {
        try {
            projectDao.editProject(id,projectName, clientName, hourlyRate, description);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

    @Override
    public void assignUsersToProj(Project selectedProject, List<User> usersAssign) {
        try {
            userDao.assignUsersToProj(selectedProject, usersAssign);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    @Override
    public List<Task> getAllTasksOverview() {
        List<Task> allTasksOverview = null;
        try {
            allTasksOverview = taskDao.getAllTasksOverview();
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allTasksOverview;
    }

    @Override
    public void deleteClient(Client selectedClient) {
        clientDao.deleteClient(selectedClient);
    }

    @Override
    public void createClient(String name, String city, int phone, String email) {
        clientDao.createClient(name, city, phone, email);
    }

    @Override
    public void newPassword(String pswSecond, int userID) {
        try {
            userDao.newPassword(pswSecond, userID);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editClient(int id, String name, String city, int phone, String email) {
    clientDao.editClient(id,name, city, phone, email);
    }

    @Override
    public void editTask(int id, String name, LocalDateTime startTime, LocalDateTime endTime, String note, double spentTime) {
    taskDao.editTask(id,name,startTime,endTime,note,spentTime);
    }

    @Override
    public void saveStoppedTask(Project selectedProject, String taskName, String note, User loggedUser, LocalDateTime startTime, LocalDateTime endTime, long spentMinutes) {
        try {
            taskDao.saveStoppedTask(selectedProject, taskName, note, loggedUser, startTime, endTime, spentMinutes);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




}
