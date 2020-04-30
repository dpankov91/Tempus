/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.dal;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import tempus.be.Project;

import tempus.be.Client;



import tempus.be.Project;

import tempus.be.User;
import tempus.dal.dao.ClientDAO;
import tempus.dal.dao.ProjectDAO;
import tempus.dal.dao.UserDAO;

/**
 *
 * @author dpank
 */
public class DalManager implements IDalFacade{
    
    UserDAO userDao;
    ProjectDAO projectDao;
    ClientDAO clientDao;

    public DalManager() {
        userDao = new UserDAO();
        projectDao = new ProjectDAO();
        clientDao = new ClientDAO();
    }

    @Override
    public User getUser(String username, String password) {
        try {
            return  userDao.getUser(username, password);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void createProject(String projectName, String clientName, String hourlyRate, String description) {
        
        projectDao.createProject(projectName, clientName, hourlyRate, description);
        
    }

    @Override
    public void deleteProject(Project projectToDelete) {
        projectDao.deleteProject(projectToDelete);
    }

    @Override
    public List<Client> getAllClientss() 
    {
        
            
        try {
            List<Client> allClientss = clientDao.getAllClientss();
            return allClientss;
            
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
            return null;
        
    }

    @Override
    public List<Project> getAllProjects(){
        List<Project> allProjects = null; 
        try {
            allProjects = projectDao.getAllProjects();
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
            return allProjects;
    }

    @Override

    public void deleteUser(User userToDelete) {
        userDao.deleteUser(userToDelete);
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

   
    
}
