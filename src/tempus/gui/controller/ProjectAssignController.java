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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    
    private UserModel userModel;
    private ProjectModel projModel;
    @FXML
    private JFXButton btnCancel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userModel = UserModel.getInstance();
        projModel = ProjectModel.getInstance();
        ObservableList<User> obsUsers = FXCollections.observableArrayList(userModel.getAllUsers());
        cmbDevelopers.setItems(obsUsers);
        //lblProjName.setText(projModel.getSelectedProject().getName());
    }    

    @FXML
    private void handleConfirm(ActionEvent event) {
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
    
}
