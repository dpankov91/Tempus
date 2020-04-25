/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tienesh
 */
public class LoginController implements Initializable {

    @FXML
    private Button btnLogin;
    @FXML
    private Button btnCancel;
    @FXML
    private JFXRadioButton rdoRememberMe;
    @FXML
    private TextField txtFieldUsername;
    @FXML
    private PasswordField pasPasswordField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickLogIn(ActionEvent event) {
        checkIfFieldsAreEmpty();
        login();
    }

    @FXML
    private void clickCancel(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void clickRememberMe(ActionEvent event) {
    }

    private void checkIfFieldsAreEmpty() {
        if(txtFieldUsername.getText() == null || txtFieldUsername.getText().isEmpty())
        {
            setUpAlert("No Username Error" , "Please insert Username.");
        }
        else if(pasPasswordField.getText() == null || pasPasswordField.getText().isEmpty())
        {
            setUpAlert("No Password Error" , "Please insert Password."); 
        }
    }

    private void login() throws IOException {
        User us = model.loginUser(txtFieldUsername.getText(), pasPasswordField.getText());
        if (us.getIsAdmin){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tempus/gui/view/AdminOverview.fxml"));
            Parent z = loader.load();
            Scene scene = new Scene(z);
            Stage s = new Stage();
            s.setScene(scene);
            s.show();
            closeWindow();
        }else
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tempus/gui/view/DeveloperOverview.fxml"));
            Parent z = loader.load();
            Scene scene = new Scene(z);
            Stage s = new Stage();
                loader.<DeveloperOverviewController>getController().setUser(us); //Sets controler by default for both creating and editing categories
       
            s.setScene(scene);
            s.show();
            closeWindow();
        }
        
    
    }

    /*
    The setUpAlert method is used to alert the user, be it developer or admin, if either their password or username is not inserted into the right text fields-
    With a title and a message showing, the user is admin is notified to input the password or username if the fields are empty
    */
    
    private void setUpAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
        
    }

    private void closeWindow() {
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        stage.close();    }
    
}
