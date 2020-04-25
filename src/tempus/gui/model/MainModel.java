/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.model;

import tempus.be.User;
import tempus.bll.BllManager;
import tempus.bll.IBllFacade;

/**
 *
 * @author Tienesh
 */
public class MainModel
{
    static MainModel model = new MainModel();
    private final IBllFacade facade;
    private User loggedInUser;
    
    public static MainModel getInstance() {
        return model;
    }
    
    private MainModel(){
        this.facade = new BllManager();
    }
      
     public User loginUser(String username, String password)
    {
       return loggedInUser = facade.getUser(username, password);
    } 

    public void login() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
       

       
        