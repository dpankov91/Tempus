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
import javax.swing.JComboBox;
import tempus.be.Client;
import tempus.be.Project;
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
    @FXML
    private JFXTextField txtHourlyRate;
    @FXML
    private Button saveButton;
    @FXML
    private Button goBackButton;
    @FXML
    private JFXTextArea txtDescription;
    @FXML
    private ComboBox<Client> cmbClient;

    /**
     * Initializes the controller class.
     */
    
    private ProjectModel projectModel;
    
    ClientModel cmodel;
    ObservableList<Client>  allClients = FXCollections.observableArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        projectModel = ProjectModel.getInstance();
        cmodel = ClientModel.getInstance();
        loadClientsToComboBox();
        
    }
    
    private boolean checkIfFilled() {
        
        if (txtProjectName.getText() == null || txtProjectName.getText().trim().isEmpty())
     {
         setUpAlert("Incorrect Info Error" , "Add text please.");
     
     }
     else if (cmbClient.getSelectionModel().getSelectedItem()== null)
     {
         
        setUpAlert("Incorrect Info Error" , "Select client please.");
        
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
            int hRate = Integer.parseInt(txtHourlyRate.getText());
            String description = txtDescription.getText();
            Client client = cmbClient.getSelectionModel().getSelectedItem();
            
            projectModel.createProject(name, client, hRate, description);
            
            //setUpAlert("Project is created");
            
        }
        else{
            setUpAlert("Blabla" , "BlabBLaBla.");
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
