/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.dal;

import tempus.be.User;

/**
 *
 * @author dpank
 */
public interface IDalFacade {

    public User getUser(String username, String password);
    
}