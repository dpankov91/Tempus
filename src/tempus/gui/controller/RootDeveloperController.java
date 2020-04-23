/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class RootDeveloperController implements Initializable {

    @FXML
    private Button btn_editprofile;
    @FXML
    private Button btn_settings;
    @FXML
    private JFXButton btn_timesheet;
    @FXML
    private Button btn_play;
    @FXML
    private Button btn_pause;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handle_editProfile(ActionEvent event) {
    }

    @FXML
    private void handle_Settings(ActionEvent event) {
    }

    @FXML
    private void handle_TimeSheet(ActionEvent event) {
        btn_timesheet.setVisible(true);
        btn_play.setVisible(true);
        btn_pause.setVisible(true);
        
    }

    @FXML
    private void handle_Play(ActionEvent event) {
    }

    @FXML
    private void handle_Pause(ActionEvent event) {
    }
    
}
