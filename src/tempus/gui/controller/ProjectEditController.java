/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author dpank
 */
public class ProjectEditController implements Initializable {

    @FXML
    private ComboBox<?> cmbExistedProjects;
    @FXML
    private JFXTextField txtProjectName;
    @FXML
    private JFXTextField txtClientName;
    @FXML
    private JFXTextField txtHourlyRate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleSaveUpdates(ActionEvent event) {
    }

    @FXML
    private void goBack(ActionEvent event) {
    }
    
}
