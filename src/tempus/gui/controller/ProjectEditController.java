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
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import tempus.be.Project;
import tempus.be.User;
import tempus.gui.model.ProjectModel;
import tempus.gui.model.UserModel;

/**
 * FXML Controller class
 *
 * @author dpank
 */
public class ProjectEditController implements Initializable {

    @FXML
    private ComboBox<Project> cmbExistedProjects;
    @FXML
    private JFXTextField txtProjectName;
    @FXML
    private JFXTextField txtClientName;
    @FXML
    private JFXTextField txtHourlyRate;
    @FXML
    private JFXTextArea txtDescription;
    
     ProjectModel projectModel;
     Project currentSelectedProject ;
     
    @FXML
    private Button saveUpdates;
    @FXML
    private Button btnClose;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        projectModel= ProjectModel.getInstance();
        ObservableList<Project> obsProjects = FXCollections.observableArrayList(projectModel.getAllProjects());
         cmbExistedProjects.setItems(obsProjects);
    }    

    @FXML
    private void onActionSaveUpdates(ActionEvent event) {
        
        String projectName = txtProjectName.getText();
        String clientName = txtClientName.getText();
        String hourlyRate = txtHourlyRate.getText();
        String description = txtDescription.getText();
         
        projectModel.editProject(currentSelectedProject.getId(),projectName, clientName, hourlyRate, description);
        
        
        
    }

    @FXML
    private void goBack(ActionEvent event) {
         Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
    
    private int convertToInt(String gottentString) {
        return Integer.parseInt(gottentString);
    }

    @FXML
    private void onActionChooseProjects(ActionEvent event) {
        
        currentSelectedProject = cmbExistedProjects.getSelectionModel().getSelectedItem();
        txtProjectName.setText(currentSelectedProject.getName());
        txtClientName.setText(currentSelectedProject.getClientName());
        txtHourlyRate.setText(Integer.toString(currentSelectedProject.getHRate()));
        txtDescription.setText(currentSelectedProject.getDescription());
       
    }
}
