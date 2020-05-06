/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tempus.be.Project;
import tempus.gui.model.ProjectModel;
import tempus.gui.model.UserModel;

/**
 * FXML Controller class
 *
 * @author dpank
 */
public class DeleteConfirmationController implements Initializable {

    @FXML
    private Label lblName;
    @FXML
    private JFXButton btnCancel;
    @FXML
    private JFXButton btnConfirm;

    
    
    ProjectModel model;
    ManageProjectsWindowController prevContrl;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=ProjectModel.getInstance();
        lblName.setText(model.getSelectedProject().getName());
    }    

    @FXML
    private void goBack(ActionEvent event)
    {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleConfirmDeleting(ActionEvent event) {

           model.deleteSelectedProject();
           prevContrl.loadTableView();
           goBack(event);
        
    }
    
     void setInfo(ManageProjectsWindowController aThis) {
    prevContrl = aThis;
            }
    
}
