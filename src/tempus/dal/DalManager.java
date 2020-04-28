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
import tempus.be.Client;
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
        clientDao= new ClientDAO();
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
    public void createProject(String name, String client, String rate, String description) {
        
        projectDao.createProject(name, client, rate, description);
        
    }

    @Override
    public void deleteProject(String name, String client, String rate, String description) {
        projectDao.deleteProject(name, client, rate, description);
    }

//    @Override
//    public List<Client> getAllClientNames() 
//    {
//        
//        try {
//            List<Client> allClients = clientDao.getAllClientNames();    
//            return allClients;
//        } catch (SQLException ex) {
//            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }

    @Override
    public List<Client> getAllClientNames() {
      
            System.out.println("dal1");
        try {
            List<Client> allClients = clientDao.getAllClientNames();
            
            return allClients;
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("dal2");
        }
        return null;
    }
    
}
