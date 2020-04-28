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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import tempus.gui.model.UserModel;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import tempus.be.Client;
import tempus.gui.model.ClientModel;
import tempus.gui.model.ProjectModel;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class ProjectCreateWindowController implements Initializable {

    @FXML
    private JFXTextField txtProjectName;
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
    
    private ProjectModel projectModel;
    @FXML
    private ComboBox<Client> cmbClient;
    
    ClientModel cmodel;
    ObservableList<Client>  allClients = FXCollections.observableArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        cmodel = ClientModel.getInstance();
        loadClientsToComboBox(); 
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
            String projectName = txtProjectName.getText();
            String clientName = txtClientName.getText();
            String hourlyRate = txtHourlyRate.getText();
            String description = txtDescription.getText();
            
            projectModel.createProject(projectName, clientName, hourlyRate, description);
            
            //setUpAlert("Project is created");
            
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

    private void setUpAlert(String title, String message){
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
    
    public void loadClientsToComboBox() 
    { 
        allClients = cmodel.getObsClients();
        
        for (Client cl : allClients) {
            cmbClient.setItems(allClients);
        }    
    }
    

}
