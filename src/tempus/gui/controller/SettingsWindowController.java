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
        String pswFirst = pswFirstTime.getText().trim();
        String pswSecond = pswSecondConfirm.getText().trim();
    // the Trim method is used to remove whitespace.            
        if (isValid(pswFirst, pswSecond)) { // uses the convenience method to validate the password
            User us = userModel.getloggedInUser(); // invokes the loggedInUser method to check logged in user.
            us.setPassword(pswSecond); // This method invokes the setPassword in the user model, placing the second password parameter in.
            userModel.newPassword(pswSecond); // Takes the input in the second password field and pushes it to the userModel as a method is invoked.
            setUpAlert("Password Changed", "You have a new password");
        }
    }
    
    
    //  Convenience Method for checking password fields 
    private boolean isValid(String firstPW, String secondPW) {    
        if (firstPW.isEmpty()) { // If first textfield is empty
            setUpAlert("Passwords Error" , "Please, input new password in first field");
            return false;
        }               
        if (secondPW.isEmpty()) { // if second is field is empty
            setUpAlert("Passwords Error" , "Please, input new password in second field");
            return false;
        }
        if (!firstPW.equals(secondPW)) { // if first password input does not match second field
             setUpAlert("Password Error" , "New passwords dont match");
             return false;
        }
        return true;
    }
    
    private void setUpAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
        
    }

    @FXML
    private void onClickOpenPicSearcher(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select image files");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images",
                "*.png", "*.jpg", "*.gif", "*.tif", "*.bmp"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            User us = userModel.getloggedInUser();
            userModel.editUser(us.getId(), us.getFName(), us.getLName(), us.getEmail(), us.getPhone(), us.getPostcode(), us.getAddress(),selectedFile.getAbsolutePath(), us.getPassword());
            us.setPhotoURL(selectedFile.getAbsolutePath());
            userModel.setUpLocalIMG(selectedFile.getAbsolutePath());
            showImg();
            setUpAlert("Picture is changed" , "Your new picture is set up");
        }
    }

    private void showImg() {
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
