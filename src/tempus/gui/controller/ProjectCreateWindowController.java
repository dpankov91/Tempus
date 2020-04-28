/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import tempus.gui.model.UserModel;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class ProjectCreateWindowController implements Initializable {

    @FXML
    private JFXTextField txtProjectName;
    @FXML
    private JFXTextField txtClientName;
    @FXML
    private JFXTextField txtHourlyRate;
    @FXML
    private Button saveButton;
    @FXML
    private Button goBackButton;
    @FXML
    private JFXTextArea txtDescription;

    /**
     * Initializes the controller class.
     */
    
    private UserModel userModel;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    private boolean checkIfFilled() {
        
        if (txtProjectName.getText() == null || txtProjectName.getText().trim().isEmpty())
     {
         setUpAlert("Incorrect Info Error" , "Add text please.");
     
     }
     else if (txtClientName.getText() == null || txtClientName.getText().trim().isEmpty())
     {
         
        setUpAlert("Incorrect Info Error" , "Add text please.");
        
     }
     else if (txtHourlyRate.getText() == null || txtHourlyRate.getText().trim().isEmpty())
     {
        setUpAlert("Incorrect Info Error" , "Add text please.");
       
     }
     else if (txtDescription.getText() == null || txtDescription.getText().trim().isEmpty())
     {
        setUpAlert("Incorrect Info Error" , "Add text please.");
     }
     return true;
        
    }

    @FXML
    private void handleSave(ActionEvent event) {
        
        if (checkIfFilled())
        {
            String name = txtProjectName.getText();
            String client = txtClientName.getText();
            String rate = txtHourlyRate.getText();
            String description = txtDescription.getText();
            
            userModel.createProject(name, client, rate, description);
            
            setUpAlert("Project is created");
            
        }
   
    }

    @FXML
    private void handleGoBack(ActionEvent event) {
        
        closeWindow(event);
        
    }

    private void closeWindow(ActionEvent event) {
        
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.close();
        
    }

    private void setUpAlert(String project_is_created) {
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    private void setUpAlert(String incorrect_Info_Error, String add_text_please) {
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
        
    }

}
