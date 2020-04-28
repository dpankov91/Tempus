/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

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
    ProjectModel model;
    ManageProjectsWindowController projContr;
    
    
   private Project selectedProject;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new ProjectModel();
        projContr = new ManageProjectsWindowController();
        selectedProject= projContr.selectedProject;
        lblName.setText(selectedProject.getProjectName());
    }    

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tempus/gui/view/ManageProjectsWindow.fxml"));
          Parent z = loader.load();
          Scene scene = new Scene(z);
          Stage s = new Stage();
          s.setScene(scene);
          s.show();
    }

    @FXML
    private void handleConfirmDeleting(ActionEvent event) {
      
        model.deleteProject(selectedProject);
        
    }
    
}
