/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.bll;

import tempus.be.User;
import tempus.dal.DalManager;
import tempus.dal.IDalFacade;

/**
 *
 * @author dpank
 */
public class BllManager implements IBllFacade {
    
    IDalFacade facade;

    public BllManager() {   
        facade = new DalManager();
    }

    @Override
    public User getUser(String username, String password) {

        return facade.getUser(username, password);
    }

    
}
