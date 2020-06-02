/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.be;

import java.io.File;
import javafx.scene.image.Image;

/**
 * The User class is an entity class. It represents a table in the database,
 * where each entity instance corresponds to a row in the table. The columns of
 * each row is the attribute of the entity.
 *
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Christian Hansen
 * @author Dmitri Pankov
 * @author Nebojsa Gutic
 * @author Tienesh Kanagarasan
 */
public class User {

    private int id;
    private String fName;
    private String lName;
    private String password;
    private String email;
    private String address;
    private int phone;
    private int postcode;
    private boolean isAdmin;
    private String role;
    private String photoURL;

    /**
     *Retrieves the URL of the photo
     * @return
     */
    public Image getPhotoURL() {
        File file;
        if (photoURL != null) {
            file = new File(photoURL);
        } else {
            file = new File("https://i.ibb.co/K0B5Vwx/image-not-found-1038x576.jpg");
        }
        Image image = new Image(file.toURI().toString());
        return image;
    }

    /**
     *Retrieves the real URL of the photo
     * @return
     */
    public String getRealPhotoURL() {
        return photoURL;
    }

    /**
     *Sets the URL of the photo
     * @param photoURL
     */
    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    /**
     *Retrieves the address of the user
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *Sets the address of the user
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *Retrieves the phone nr of the user
     * @return
     */
    public int getPhone() {
        return phone;
    }

    /**
     *Sets the phone nr of the user
     * @param phone
     */
    public void setPhone(int phone) {
        this.phone = phone;
    }

    /**
     *Retrieves the postcode of the user
     * @return
     */
    public int getPostcode() {
        return postcode;
    }

    /**
     *Sets the postcode of the photo
     * @param postcode
     */
    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    /**
     *
     * @param id ID of the user
     * @param fName First name of the user
     * @param lName Last name of the user
     */
    public User(int id, String fName, String lName) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
    }

    /**
     *
     * @param id ID of the user
     * @param fName First name of the user
     * @param lName Last name of the user
     * @param isAdmin Checks if user is Admin
     */
    public User(int id, String fName, String lName, boolean isAdmin) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.isAdmin = isAdmin;
    }

    /**
     *
     * @param fName First name of the user
     * @param lName Last name of the user
     * @param email Email of the user 
     * @param isAdmin Checks if user is Admin
     */
    public User(String fName, String lName, String email, boolean isAdmin) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    /**
     *
     * @param fName First name of the user
     * @param lName Last name of the user
     * @param email Email of the user
     * @param role Checks the role of the user (Admin or Developer)
     */
    public User(String fName, String lName, String email, String role) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.role = role;

    }

    /**
     *
     * @param fName First name of the user
     * @param lName Last name of the user
     * @param password Password of the user
     * @param email Email of the user
     * @param role Checks the role of the user (Admin or Developer)
     * @param address Address of the user
     * @param phone Phone nr of the user
     * @param postcode Postcode of the user
     */
    public User(String fName, String lName, String password, String email, String role, String address, int phone, int postcode) {
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.address = address;
        this.phone = phone;
        this.postcode = postcode;
    }
    
    /**
     *Sets the last of user
     * @param lName
     */
    public User(String lName){
        this.lName = lName;
    }

    @Override
    public String toString() {
        return fName + " " + lName;
    }

    /**
     *Retrieves the password of user
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *Sets the password of user
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *Retrieves the role of user
     * @return
     */
    public String getRole() {
        return role;
    }

    /**
     *Sets the role of user
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     *Retrieves email of user
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *Sets the email of user
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *Retrieves the ID of the user
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *Retrieves the first name of user
     * @return
     */
    public String getFName() {
        return fName;
    }

    /**
     *Retrieves the last name of user
     * @return
     */
    public String getLName() {
        return lName;
    }

    /**
     *Checks if user is Admin 
     * @return
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     *Sets the ID of user
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *Sets the first name of user
     * @param fName
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     *Sets the last name of user
     * @param lName
     */
    public void setLName(String lName) {
        this.lName = lName;
    }

    /**
     *Checks if user is Admin
     * @param isAdmin
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     *
     * @param pswSecond
     */
    public void newPassword(String pswSecond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
