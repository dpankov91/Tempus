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
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import tempus.be.Project;

import tempus.be.Client;

import tempus.be.Project;
import tempus.be.Task;

import tempus.be.User;
import tempus.dal.dao.ClientDAO;
import tempus.dal.dao.LogDAO;
import tempus.dal.dao.ProjectDAO;
import tempus.dal.dao.TaskDAO;
import tempus.dal.dao.UserDAO;

/**
 * The SecurityException is a class. It 
 *
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Christian Hansen
 * @author Dmitri Pankov
 * @author Nebojsa Gutic
 * @author Tienesh Kanagarasan
 */
public class DalManager implements IDalFacade {
//DAL manager implements DAL interface abstract methods, inheirting them, 
//which means all methods in this class are overridden
//Facade Pattern is used for better passage of information.    
    UserDAO userDao;
    ProjectDAO projectDao;
    ClientDAO clientDao;
    TaskDAO taskDao;
    LogDAO logDao;

    public DalManager() {
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
        logDao = new LogDAO();
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
    public Project createProject(String name, Client client, int hRate, String description) {
        
        // sends the project to be created to the projectDao and returns the project
        
        Project pro = projectDao.createProject(name, client, hRate, description);
        try {
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            logDao.insertLog(LogDAO.PROJECT_TABLE, pro.getId(), LogDAO.CREATE_ACTION, date);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pro;
    }

    @Override
    public Project deleteProject(Project projectToDelete) {
        Project pro = projectDao.deleteProject(projectToDelete); //Brings down the project for deletion to the ProjectDAO.
        try {
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            logDao.insertLog(LogDAO.PROJECT_TABLE, pro.getId(), LogDAO.DELETE_ACTION, date);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pro;
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

        User deletedUser = userDao.deleteUser(userToDelete); // Like before, the user for deletion is sent down to the layer under, to the userDao.
        try {
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            logDao.insertLog(LogDAO.USER_TABLE, userToDelete.getId(), LogDAO.DELETE_ACTION, date);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return deletedUser;
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
        // sends the user to be created to the userDao and returns the createdUser
        User createdUser = userDao.createUser(fName, lName, hashedPassword, email, role, address, phone, postcode);
        try {
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            logDao.insertLog(LogDAO.USER_TABLE, createdUser.getId(), LogDAO.CREATE_ACTION, date);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return createdUser;
    }

    @Override
    public User editUser(int id, String name, String Lname, String email, int realphone, int realpostcode, String address, String imageURL, String password) {
        User editedUser = userDao.editUser(id, name, Lname, email, realphone, realpostcode, address, imageURL, password);
        try {
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            logDao.insertLog(LogDAO.USER_TABLE, editedUser.getId(), LogDAO.UPDATE_ACTION, date);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return editedUser;
    }

    @Override
    public Project editProject(int id, String projectName, String clientName, int hourlyRate, String description) {
        try {
            Project editedProj = projectDao.editProject(id, projectName, clientName, hourlyRate, description);
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            logDao.insertLog(LogDAO.PROJECT_TABLE, editedProj.getId(), LogDAO.UPDATE_ACTION, date);
            return editedProj;
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
    public Client deleteClient(Client selectedClient) { //Like before, the client for deletion is sent down to the layer under, to the clientDao.
        clientDao.deleteClient(selectedClient);
        try {
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            logDao.insertLog(LogDAO.CLIENT_TABLE, selectedClient.getId(), LogDAO.DELETE_ACTION, date);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Client createClient(String name, String city, int phone, String email) {
        // send the client to be created to the clientDao and returns null
        Client client = clientDao.createClient(name, city, phone, email);
        try {
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            logDao.insertLog(LogDAO.CLIENT_TABLE, client.getId(), LogDAO.CREATE_ACTION, date);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void newPassword(String pswSecond, int userID) {
        // Via this method, the new hashed password is brought down here and 
        // Then brings it further down, into the user dao to bring it to the database. 
        try {
            userDao.newPassword(pswSecond, userID); 
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex); // Log is used to make a log of this action in database
        }
    }

    @Override
    public Client editClient(int id, String name, String city, int phone, String email) {
        Client client = clientDao.editClient(id, name, city, phone, email);
        try {
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            logDao.insertLog(LogDAO.CLIENT_TABLE, client.getId(), LogDAO.UPDATE_ACTION, date);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void editTask(int id, String name, LocalDateTime startTime, LocalDateTime endTime, String note, double spentTime) {
        try {
            taskDao.editTask(id, name, startTime, endTime, note, spentTime);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void saveStoppedTask(Project selectedProject, String taskName, String note, User loggedUser, LocalDateTime startTime, LocalDateTime endTime, long spentSeconds) {
        try {
            taskDao.saveStoppedTask(selectedProject, taskName, note, loggedUser, startTime, endTime, spentSeconds);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteTask(Task selectedTask) {
        try {    
            taskDao.deleteClient(selectedTask);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void newPasswordForSelectedUser(String newPassword, int id) {
        try {
            userDao.newPasswordForSelectedUser(newPassword, id);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
