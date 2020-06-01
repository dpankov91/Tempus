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
import javafx.stage.Stage;

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
    @FXML
    private JFXTextField txtPhone;
    @FXML
    private JFXTextField txtPostcode;
    @FXML
    private JFXTextField txtAddress;
    
    ManageUsersWindowController prevContrl;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userModel = new UserModel();
        loadRolesIntoCombobox();
    }    
    
    private void loadRolesIntoCombobox(){
        cmbChooseRole.setItems(FXCollections.observableArrayList(
                "Admin", "Developer"));
    }    

    private boolean checkIfFilled() {
      if (txtPassword.getText() == null || txtPassword.getText().trim().isEmpty())
    {
         setUpAlert("Incorrect Info Error" , "Check password field");
    }
     else if (txtFirstName.getText() == null || txtFirstName.getText().trim().isEmpty())
    {
        setUpAlert("Incorrect Info Error" , "Check first name field");
    }
     else if (txtLastName.getText() == null || txtLastName.getText().trim().isEmpty())
    {
        setUpAlert("Incorrect Info Error" , "Check last name field");
    }
     else if (txtEmail.getText() == null || txtEmail.getText().trim().isEmpty())
    {
        setUpAlert("Incorrect Info Error" , "Check E-Mail field");
    }
     else if (txtAddress.getText() == null || txtAddress.getText().trim().isEmpty())
    {
        setUpAlert("Incorrect Info Error" , "Check address field");
    }
     else if (txtPhone.getText() == null || txtPhone.getText().trim().isEmpty() || !txtPhone.getText().matches("^\\d+(\\.\\d+)?"))
    {
        setUpAlert("Incorrect Info Error" , "Check phone field");
    }
     else if (txtPostcode.getText() == null || txtPostcode.getText().trim().isEmpty() || !txtPostcode.getText().matches("^\\d+(\\.\\d+)?"))
    {
        setUpAlert("Incorrect Info Error" , "Check postcode field");
    }
     else if (cmbChooseRole.getSelectionModel().getSelectedItem()== null)
    {
        setUpAlert("Incorrect Info Error" , "Check role box");
    }
     return true;
    }

    @FXML
    private void handleSave(ActionEvent event) {
        
        /*
        * checks if the fields are empty or null
        * gets the password, first name, last name, email, address, phone, postcode and selects the role
        * sends the parameters to the userModel
        * and loads it to TableView in the previous controller
        */
        
        if (checkIfFilled()){
            try {
            String password = txtPassword.getText();
            String fName = txtFirstName.getText();
            String lName = txtLastName.getText();
            String email = txtEmail.getText();
            String address = txtAddress.getText();
            int phone = Integer.parseInt(txtPhone.getText());
            int postcode = Integer.parseInt(txtPostcode.getText());
            String role = cmbChooseRole.getSelectionModel().getSelectedItem();
           
            userModel.createUser(fName, lName, password, email, role, address, phone, postcode);
            
            prevContrl.loadTableView();
            handleCancel(event);  
            }catch (NumberFormatException e) {
            System.out.println("We can catch the NumberFormatException");
            }
        }
    }
        

    @FXML
    private void handleCancel(ActionEvent event) {
         Stage stage = (Stage) btnCancel.getScene().getWindow();
         prevContrl.loadTableView();
         stage.close();
    }

    private void setUpAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    void setInfo(ManageUsersWindowController aThis) {
        prevContrl = aThis;
    }
    

    
}
