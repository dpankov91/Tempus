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
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class EditUserController implements Initializable {

    @FXML
    private JFXButton btn_save;
    @FXML
    private JFXButton btn_cancel;
    @FXML
    private ComboBox<?> chooseUserEditCombo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handle_Save(ActionEvent event) {
    }

    @FXML
    private void handle_Cancel(ActionEvent event) {
           Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onActionChooseEditUser(ActionEvent event) {
    }
    
}
