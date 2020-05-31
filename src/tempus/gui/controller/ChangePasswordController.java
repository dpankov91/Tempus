/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import tempus.be.User;
import tempus.gui.model.UserModel;

/**
 * FXML Controller class
 *
 * @author dpank
 */
public class ChangePasswordController implements Initializable {

    @FXML
    private JFXPasswordField pswFirstNew;
    @FXML
    private JFXPasswordField pswSecondNew;
    @FXML
    private JFXButton btnConfirm;
    @FXML
    private JFXButton btnBack;
    
    private UserModel usModel;
    private User selectedUser;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usModel = UserModel.getInstance();
    }    

    @FXML
    private void onClickConfirm(ActionEvent event) {
        String pswFirst = pswFirstNew.getText().trim();
        String pswSecond = pswSecondNew.getText().trim();
    // the Trim method is used to remove whitespace.            
        if (isValid(pswFirst, pswSecond)) { // uses the convenience method to validate the password
            User us = usModel.getSelectedUser(); // invokes the loggedInUser method to check logged in user.
            us.setPassword(pswSecond); // This method invokes the setPassword in the user model, placing the second password parameter in.
            usModel.newPasswordForSelectedUser(pswSecond); // Takes the input in the second password field and brings it to the userModel as a method is invoked.
            setUpAlert("Password Changed", "You have a new password");
        }
    }

    @FXML
    private void onClickCloseWindow(ActionEvent event) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }
    
    private boolean isValid(String firstPW, String secondPW) {
    //  Convenience Method for checking password fields     
        
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
    
}
