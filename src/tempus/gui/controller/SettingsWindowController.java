/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tempus.be.User;
import tempus.gui.model.UserModel;

/**
 * The SettingsWindowController is a class. It shows the settings for the users
 * such as changing the password for a user and their info
 *
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Christian Hansen
 * @author Dmitri Pankov
 * @author Nebojsa Gutic
 * @author Tienesh Kanagarasan
 */
public class SettingsWindowController implements Initializable {

    private final List<Image> images = new ArrayList<>();

    @FXML
    private JFXButton btnClose;
    @FXML
    private JFXPasswordField pswFirstTime;
    @FXML
    private JFXPasswordField pswSecondConfirm;
    @FXML
    private JFXButton btnConfrim;
    @FXML
    private ImageView imgPhoto;
    @FXML
    private JFXButton btnEditPic;
    
    //Instance variables:
    private User user;
    private UserModel userModel;
    RootAdminController prevContrl;
    RootDeveloperController prevDevContrl;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userModel = UserModel.getInstance();
        showImg();
    }

    @FXML
    private void onClickClose(ActionEvent event) {
        goBack();
    }

    @FXML
    private void onClickConfirm(ActionEvent event) {
        String pswFirst = pswFirstTime.getText().trim(); // String Method to get text from first field
        String pswSecond = pswSecondConfirm.getText().trim(); // String Method to get text from second field
        // the Trim feature is used to remove whitespace.
        // String are used to store the text
            if (isValid(pswFirst, pswSecond)) { // uses the convenience method to validate the password.
            // If statement only works if there is input from the two text fields.
            User us = userModel.getloggedInUser(); //invokes loggedInUser method to get logged in user, get their userID and gets password.
            us.setPassword(pswSecond); // This method invokes the setPassword in the user model, placing the second password parameter in.
            userModel.newPassword(pswSecond); // Takes the input in the second password field and pushes it to the userModel as a method is invoked.
            setUpAlert("Password Changed", "You have a new password");
        }
    }
    
    
    
    private boolean isValid(String firstPW, String secondPW) {        
        //  Convenience Method for checking password fields inputs.
        //  While it's private, it's a boolean method, made to find out if an expression is true or false, with two outcomes.
        if (firstPW.isEmpty()) { // If first textfield is empty
            setUpAlert("Passwords Error" , "Please, input new password in first field");
            return false;
        }               
        if (secondPW.isEmpty()) { // if second is field is empty
            setUpAlert("Passwords Error" , "Please, input new password in second field");
            return false;
        }
        if (!firstPW.equals(secondPW)) { // if first password input does not match second input
             setUpAlert("Password Error" , "New passwords dont match");
             return false;
        }
        
        return true; 
        // True: it brings the new password down a layer.
        // False: Nothing happens, alerts the user with a message to correct their mistake.
        // ! is used as the logical concept NOT.
    }
    
    private void setUpAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
        
    }

    @FXML
    private void onClickOpenPicSearcher(ActionEvent event) {
        //Pressing the button, invokes this method, letting the user search through their device for a photo.
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select image files");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images",
                "*.png", "*.jpg", "*.gif", "*.tif", "*.bmp"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {//continues the statement if a photo is selected.
            User us = userModel.getloggedInUser(); //gets the user's ID and information.
            userModel.editUser(us.getId(), us.getFName(), us.getLName(), us.getEmail(), us.getPhone(), us.getPostcode(), us.getAddress(),selectedFile.getAbsolutePath(), us.getPassword());
            //EditUser allows a series of functions
            us.setPhotoURL(selectedFile.getAbsolutePath());
            userModel.setUpLocalIMG(selectedFile.getAbsolutePath()); //specifically the ability to get the path of a photo file.
            showImg(); //Uses another method in the controller.
            setUpAlert("Picture is changed" , "Your new picture is set up");
        }
    }

    private void showImg() {
        //Method for displaying an image on a designated area.
        User us = userModel.getloggedInUser();
        imgPhoto.setImage(us.getPhotoURL());
    }

    private void goBack() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        if(userModel.getloggedInUser().getIsAdmin()){
        prevContrl.setUpimg();
        }else{
        prevDevContrl.setUpimg();       
        }
        stage.close();
    }

    void setInfo(RootAdminController aThis) {
        prevContrl = aThis;
    }
    
    void setInfoDev(RootDeveloperController aThis) {
        prevDevContrl = aThis;
    }


}
