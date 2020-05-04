/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import tempus.be.User;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleConfirm(ActionEvent event) {
    }

    @FXML
    private void handleClose(ActionEvent event) {
    }

    @FXML
    private void addCatsToList(ActionEvent event) {
    }

    @FXML
    private void RemoveFromList(ActionEvent event) {
    }
    
}
