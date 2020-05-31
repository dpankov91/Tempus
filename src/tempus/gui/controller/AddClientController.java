/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import tempus.gui.model.ClientModel;

/**
 * FXML Controller class
 *
 * @author dpank
 */
public class AddClientController implements Initializable {

    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtPhone;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnCancel;
    
    private ClientModel clientModel;
    
    ManageClientsWindowController prevContrl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clientModel = new ClientModel();
    }
    
    private boolean checkIfFilled() 
      {
        if (txtName.getText() == null || txtName.getText().trim().isEmpty())
        
     {
         setUpAlert("Incorrect Info Error" , "Add text please.");
        
    }
     else if (txtAddress.getText() == null || txtAddress.getText().trim().isEmpty())
     {
         
        setUpAlert("Incorrect Info Error" , "Add text please.");
        
     }
     else if (txtPhone.getText() == null || txtPhone.getText().trim().isEmpty() || !txtPhone.getText().matches("^\\d+(\\.\\d+)?"))
     {
        setUpAlert("Incorrect Info Error" , "Check Phone field");
       
     }
     else if (txtEmail.getText() == null || txtEmail.getText().trim().isEmpty())
     {
        setUpAlert("Incorrect Info Error" , "Add text please.");
        
     }
    return true;
    }
    
    @FXML
    private void onActionSaveClient(ActionEvent event){
        
        if (checkIfFilled())
        {
            try {
            String name = txtName.getText();
            String city = txtAddress.getText();
            int phone = Integer.parseInt(txtPhone.getText());
            String email = txtEmail.getText();
            clientModel.createClient(name, city, phone, email);
            prevContrl.loadTableView();
            
            onActionCancel(event); 
            }catch (NumberFormatException e) {

         System.out.println("We can catch the NumberFormatException");
          }

            
        }
        
    }

    @FXML
    private void onActionCancel(ActionEvent event) {
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
    
    void setInfo(ManageClientsWindowController aThis) {
        prevContrl = aThis;
    }
}
