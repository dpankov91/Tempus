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
    static MainModel model = new MainModel();
    
    
    public static MainModel getInstance() {
        return model;
    }
    private BllFacade facade;
    
    
    
     private MainModel(){
        this.facade = new BllManager();
    }
     
     public User loginUser(String username, String password)
    {
       return loggedInUser =  facade.getUser(username, password);
    } 

}
       

       
        
