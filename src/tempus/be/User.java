/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.be;

import java.io.File;
import javafx.scene.image.Image;

/**
 *
 * @author dpank
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

    public String getRealPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    //Nedas edit 
    public User(int id, String fName, String lName) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
    }

    public User(int id, String fName, String lName, boolean isAdmin) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.isAdmin = isAdmin;
    }

    public User(String fName, String lName, String email, boolean isAdmin) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public User(String fName, String lName, String email, String role) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.role = role;

    }

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
    
    public User(String lName){
        this.lName = lName;
    }

    @Override
    public String toString() {
        return fName + " " + lName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFName() {
        return fName;
    }

    public String getLName() {
        return lName;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void newPassword(String pswSecond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
