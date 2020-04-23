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
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class RootAdminController implements Initializable {

    @FXML
    private Button btn_editprofile;
    @FXML
    private Button btn_settings;
    @FXML
    private JFXButton btn_users;
    @FXML
    private TableView<?> tb_users;
    @FXML
    private JFXButton btn_edituser;
    @FXML
    private JFXButton btn_deleteuser;
    @FXML
    private JFXButton btn_adduser;

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
    private void handle_Users(ActionEvent event) {
        tb_users.setVisible(true);
        btn_adduser.setVisible(true);
        btn_deleteuser.setVisible(true);
        btn_edituser.setVisible(true);
    }

    @FXML
    private void handle_EditUser(ActionEvent event) {
    }
    
}
