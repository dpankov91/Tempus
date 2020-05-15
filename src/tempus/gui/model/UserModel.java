/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.model;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tempus.be.Project;
import tempus.be.User;
import tempus.bll.BllManager;
import tempus.bll.IBllFacade;

/**
 *
 * @author Tienesh
 */
public class UserModel {

    static UserModel model = new UserModel();
    private User selectedUser;
    private final IBllFacade facade;
    private User loggedInUser;
    private final ObservableList<User> usList = FXCollections.observableArrayList();

    public static UserModel getInstance() {
        return model;
    }

    public UserModel() {
        this.facade = new BllManager();
        usList.addAll(facade.getAllUsers());
    }

    public User loginUser(String username, String password) {
        loggedInUser = facade.getUser(username, password);
        return loggedInUser;
    }
    
     public List<User> getAllUsers() {
        return facade.getAllUsers();
    }
     
       public ObservableList<User> getObsUsers() {
        return usList;
    }

    public void deleteUser(User useDelete) {
        facade.deleteUser(useDelete);
    }

    public void createUser(String fName, String lName, String password, String email, String role, String address, int phone, int postcode) {
        facade.createUser(fName, lName, password, email, role, address, phone, postcode);
    }

    public void editUser(int id, String name, String Lname, String email, int realphone, int realpostcode, String address, String imgURL) {
        facade.editUser(id, name, Lname, email, realphone, realpostcode, address,imgURL);
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public User getloggedInUser() {
        return loggedInUser;
    }

    public void deleteSelectedUser() {
        facade.deleteUser(selectedUser);
    }

    public void assignUsersToProj(Project selectedProject, List<User> usersAssign) {
         facade.assignUsersToProj(selectedProject, usersAssign);
    }

    public void setUpLocalIMG(String absolutePath) {
    loggedInUser.setPhotoURL(absolutePath);
    }
}
