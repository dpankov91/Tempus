/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class NewTimeEntryController implements Initializable {

    @FXML
    private JFXComboBox<?> cb_box;
    @FXML
    private JFXComboBox<?> cb_task;
    @FXML
    private JFXTextField txt_notes;
    @FXML
    private JFXButton btn_save;
    @FXML
    private JFXButton btn_cancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handle_ProjectName(ActionEvent event) {
    }

    @FXML
    private void handle_TaskName(ActionEvent event) {
    }

    @FXML
    private void handle_Notes(ActionEvent event) {
    }

    @FXML
    private void handle_Save(ActionEvent event) {
    }

    @FXML
    private void handle_Cancel(ActionEvent event) {
    }
    
}
