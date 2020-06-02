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
 * The UserModel is a model. It gets and passes data about the user to the BLL
 *
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Christian Hansen
 * @author Dmitri Pankov
 * @author Nebojsa Gutic
 * @author Tienesh Kanagarasan
 */
public class UserModel {

    static UserModel model = new UserModel();
    private User selectedUser;
    private final IBllFacade facade;
    private User loggedInUser;
    private final ObservableList<User> usList = FXCollections.observableArrayList();

    /**
     *
     * @return
     */
    public static UserModel getInstance() {
        return model;
    }

    /**
     *
     */
    public UserModel() {
        this.facade = new BllManager();
        usList.addAll(facade.getAllUsers());
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public User loginUser(String username, String password) {
        loggedInUser = facade.getUser(username, password);
        return loggedInUser;
    }
    
    /**
     *
     * @return
     */
    public List<User> getAllUsers() {
        return facade.getAllUsers();
    }
     
    /**
     *
     * @return
     */
    public ObservableList<User> getObsUsers() {
        return usList;
    }

    /**
     *
     * @param useDelete
     */
    public void deleteUser(User useDelete) {
        facade.deleteUser(useDelete);
    }

    /**
     *
     * @param fName
     * @param lName
     * @param password
     * @param email
     * @param role
     * @param address
     * @param phone
     * @param postcode
     */
    public void createUser(String fName, String lName, String password, String email, String role, String address, int phone, int postcode) {
        facade.createUser(fName, lName, password, email, role, address, phone, postcode);
    }

    /**
     *
     * @param id
     * @param name
     * @param Lname
     * @param email
     * @param realphone
     * @param realpostcode
     * @param address
     * @param imgURL
     * @param password
     */
    public void editUser(int id, String name, String Lname, String email, int realphone, int realpostcode, String address, String imgURL, String password) {
        facade.editUser(id, name, Lname, email, realphone, realpostcode, address, imgURL, password);
    }

    /**
     *
     * @param selectedUser
     */
    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    /**
     *
     * @return
     */
    public User getSelectedUser() {
        return selectedUser;
    }

    /**
     *
     * @return
     */
    public User getloggedInUser() {
        return loggedInUser;
    }

    /**
     *
     */
    public void deleteSelectedUser() {
        facade.deleteUser(selectedUser); // Brings the intended user for delettion down a layer, into the BLL.
    }

    /**
     *
     * @param selectedProject
     * @param usersAssign
     */
    public void assignUsersToProj(Project selectedProject, List<User> usersAssign) {
         facade.assignUsersToProj(selectedProject, usersAssign);
    }

    /**
     *
     * @param absolutePath
     */
    public void setUpLocalIMG(String absolutePath) {
    loggedInUser.setPhotoURL(absolutePath);
    }

    /**
     *
     * @param pswSecond
     */
    public void newPassword(String pswSecond) {
        facade.newPassword(pswSecond, loggedInUser.getId());
    }
    
    /**
     *
     * @param pswSecond
     */
    public void newPasswordForSelectedUser(String pswSecond){
        facade.newPasswordForSelectedUser(pswSecond, selectedUser.getId()); // Here the input from the controller is pushed another layer down, into the BLL.
    }
}
