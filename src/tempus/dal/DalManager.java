/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.dal;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tempus.be.User;
import tempus.dal.dao.UserDAO;

/**
 *
 * @author dpank
 */
public class DalManager implements IDalFacade{
    
    UserDAO userDao;

    public DalManager() {
        userDao = new UserDAO();
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
    
}
