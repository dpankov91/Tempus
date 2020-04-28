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
    @FXML
    private ComboBox<Client> cmbClient;

    /**
     * Initializes the controller class.
     */
    
    private UserModel userModel;
    private ClientModel clientModel;
    
    ObservableList<Client>  allClients = FXCollections.observableArrayList();

    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clientModel = new ClientModel();
        loadClientsToComboBox();
        
        // TODO
    }
    
//    private boolean checkIfFilled() {
//        
//        if (txtProjectName.getText() == null || txtProjectName.getText().trim().isEmpty())
//     {
//         setUpAlert("Incorrect Info Error" , "You forgot to add the Project name.");
//     
//     }
//     else if (txtClientName.getText() == null || txtClientName.getText().trim().isEmpty())
//     {
//         
//        setUpAlert("Incorrect Info Error" , "You forgot to add the Client name.");
//        
//     }
//     else if (txtHourlyRate.getText() == null || txtHourlyRate.getText().trim().isEmpty())
//     {
//        setUpAlert("Incorrect Info Error" , "You forgot to add the Hourly Rate.");
//       
//     }
//     else if (txtDescription.getText() == null || txtDescription.getText().trim().isEmpty())
//     {
//        setUpAlert("Incorrect Info Error" , "You forgot the description of the Project.");
//     }
//     return true;
//        
//    }

    @FXML
    private void handleSave(ActionEvent event) {
        
//        if (checkIfFilled())
//        {
//            String name = txtProjectName.getText();
//            String client = txtClientName.getText();
//            String rate = txtHourlyRate.getText();
//            String description = txtDescription.getText();
//            
//            userModel.createProject(name, client, rate, description);
//            
//            setUpAlert("Project is created")
//            
//        }
   
    }
    
    

    @FXML
    private void handleGoBack(ActionEvent event) {
        
        closeWindow(event);
        
    }

//    private void setUpAlert(String incorrect_Info_Error, String manYou_forgot_to_choose_IMDb_rating) {
//        
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle(title);
//        alert.setHeaderText(message);
//        alert.showAndWait();
//        
//    }

    private void closeWindow(ActionEvent event) {
        
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.close();
        
    }
    
    public void loadClientsToComboBox() 
    { 
        allClients = clientModel.getObsClients();
        for (Client cl : allClients) {
            cmbClient.setItems(allClients);
        }    
    }
    
}
