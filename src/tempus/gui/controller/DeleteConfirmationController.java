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
 * The DeleteConfirmationController is a class. It allows the user to delete
 * a project inside the ManageProjectsWindowController
 * Events in this class are invoked when a specific event of the type for which this handler is registered happens.
 *
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Christian Hansen
 * @author Dmitri Pankov
 * @author Nebojsa Gutic
 * @author Tienesh Kanagarasan
 */
public class DeleteConfirmationController implements Initializable {

    @FXML
    private Label lblName;
    @FXML
    private JFXButton btnCancel;
    @FXML
    private JFXButton btnConfirm;

    
    
    ProjectModel model; //Instance variable to utilze ProjectModel methods.
    ManageProjectsWindowController prevContrl; // Takes information frmo the ManageProjectsWindow fxml.
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model=ProjectModel.getInstance();
        lblName.setText(model.getSelectedProject().getName().trim()); // The name of the selected project is shown on the label.
    }    

    @FXML
    private void goBack(ActionEvent event)
    {
        //This is used to return the previous window upon registering the event.
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close(); //Called when there is an external request to close this Window.
    }

    @FXML
    private void handleConfirmDeleting(ActionEvent event) {
           model.deleteSelectedProject(); // having selected a project to delete, it's brought down to the layer down.
           prevContrl.loadTableView(); // A refresh of the tableView after the deletion of the project.
           goBack(event); //Utilizes another method in the controller to return the previous screen.
        
    }
    
     void setInfo(ManageProjectsWindowController aThis) {
        prevContrl = aThis;
            }
    
}
