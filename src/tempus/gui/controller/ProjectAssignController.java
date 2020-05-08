/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tempus.be.User;
import tempus.gui.model.ProjectModel;
import tempus.gui.model.UserModel;

/**
 * FXML Controller class
 *
 * @author dpank
 */
public class ProjectAssignController implements Initializable {

    @FXML
    private JFXListView<User> lstAddedDevelopers;
    @FXML
    private JFXComboBox<User> cmbDevelopers;
    @FXML
    private Label lblProjName;
    @FXML
    private JFXButton btnCancel;
    
    private UserModel userModel;
    private ProjectModel projModel;
    ManageProjectsWindowController prevContrl;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userModel = UserModel.getInstance();
        projModel = ProjectModel.getInstance();
        ObservableList<User> obsUsers = FXCollections.observableArrayList(userModel.getAllUsers());
        cmbDevelopers.setItems(obsUsers);
        
        lblProjName.setText(projModel.getSelectedProject().getName());
    }    

    @FXML
    private void handleConfirm(ActionEvent event) {
        
    if(lstAddedDevelopers.getItems() != null){
//        projModel.getSelectedProject(), 
        List<User> usersAssign = lstAddedDevelopers.getItems();
        userModel.assignUsersToProj(projModel.getSelectedProject(), usersAssign);
        
        setUpAlert("Users are assigned", "Selected users are assigned to project");
        prevContrl.loadTableView();
        handleClose(event);
        }
    else{
        setUpAlert("User not assigned", "Noone is selected");
        handleClose(event);
        }   
    }

    @FXML
    private void handleClose(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void RemoveFromList(ActionEvent event) 
    {
        User selectedUser = lstAddedDevelopers.getSelectionModel().getSelectedItem();
        lstAddedDevelopers.getItems().remove(selectedUser);
    }

    @FXML
    private void addUserToList(ActionEvent event) {
         User selectedUser = cmbDevelopers.getSelectionModel().getSelectedItem();
        if (!lstAddedDevelopers.getItems().contains(selectedUser)) {
            lstAddedDevelopers.getItems().add(selectedUser);
        }   
    }
    
     private void setUpAlert(String title, String message){
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    void setInfo(ManageProjectsWindowController aThis) {
        prevContrl = aThis;
    }
}
