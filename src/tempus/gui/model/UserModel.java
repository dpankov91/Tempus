/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.model;

import java.util.List;
import tempus.be.User;
import tempus.bll.BllManager;
import tempus.bll.IBllFacade;

/**
 *
 * @author Tienesh
 */
public class UserModel {

    static UserModel model = new UserModel();

    public List<User> getAllUsers() {
        return facade.getAllUsers();
    }
    private final IBllFacade facade;
    private User loggedInUser;

    public static UserModel getInstance() {
        return model;
    }

    public UserModel() {
        this.facade = new BllManager();
    }

    public User loginUser(String username, String password) {
        return loggedInUser = facade.getUser(username, password);
    }

    public void login() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteProject(Object selectedProjectT) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteUser(User useDelete) {
        facade.deleteUser(useDelete);
    }

    public void createUser(String fName, String lName, String password, String email, User user) {
        facade.createUser(fName, lName, password, email, user);
    }

    public void editUser(int id, String name, String Lname, String email, int realphone, int realpostcode, String address) {
        facade.editUser(id, name, Lname, email, realphone, realpostcode, address);
    }

}
