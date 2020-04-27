/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.bll;

import tempus.be.User;

/**
 *
 * @author dpank
 */
public interface IBllFacade {

    public User getUser(String username, String password);

    public void createProject(String name, String client, String rate, String description);

    
}
