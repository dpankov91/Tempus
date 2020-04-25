/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.model;

/**
 *
 * @author Tienesh
 */
public class MainModel 
{
    private BllFacade facade;
    
    static MainModel model = new MainModel();
    
     private MainModel(){
        this.facade = new BllManager();
    }
}
        
public static MainModel getInstance(){
    return model;
  
}

       
        
public User loginUser(String username, String password)
    {
       return loggedInUser =  facade.getUser(username, password);
    } 
