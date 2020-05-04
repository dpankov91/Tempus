/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import tempus.be.User;

import javax.swing.JComboBox;
import tempus.be.User;
import tempus.gui.model.UserModel;


/**
 * FXML Controller class
 *
 * @author dpank
 */
public class UserAddController implements Initializable {

    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXTextField txtFirstName;
    @FXML
    private JFXTextField txtLastName;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnCancel;
    @FXML
    private ComboBox<String> cmbChooseRole;

    /**
     * Initializes the controller class.
     */
    
    private UserModel userModel;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

      
        
        cmbChooseRole.setItems(FXCollections.observableArrayList(
                "Admin", "Developer"));
    }    
        // TODO
   

    private boolean checkIfFilled() 
      {
        
        if (txtPassword.getText() == null || txtPassword.getText().trim().isEmpty())
        
     {
         setUpAlert("Incorrect Info Error" , "Add text please.");
        
    }
     else if (txtFirstName.getText() == null || txtFirstName.getText().trim().isEmpty())
     {
         
        setUpAlert("Incorrect Info Error" , "Add text please.");
        
     }
     else if (txtLastName.getText() == null || txtLastName.getText().trim().isEmpty())
     {
        setUpAlert("Incorrect Info Error" , "Add text please.");
       
     }
     else if (txtEmail.getText() == null || txtEmail.getText().trim().isEmpty())
     {
        setUpAlert("Incorrect Info Error" , "Add text please.");
     }
     return true;
        
    }

    @FXML
    private void handleSave(ActionEvent event) {
        
        if (checkIfFilled())
        {
            
            String password = txtPassword.getText();
            String fName = txtFirstName.getText();
            String lName = txtLastName.getText();
            String email = txtEmail.getText();
            User user = (User) cmbChooseRole.getSelectionModel().getSelectedItem();
           
            userModel.createUser(fName, lName, password, email, user);
            
            //setUpAlert("Project is created");
            
        }
        else{
            setUpAlert("Blabla" , "BlabBLaBla.");
        }
        }
        

    @FXML
    private void handleCancel(ActionEvent event) {
    }

    private void setUpAlert(String title, String message) {
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
        
    }
    
}
