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
import tempus.be.User;
import tempus.gui.model.UserModel;
/**
 * FXML Controller class
 *
 * @author Tienesh
 */
public class DeleteConfirmationUserController implements Initializable {


    @FXML
    private Label lblName;
    UserModel model;
    
    
    
    private User selectedUser;
    @FXML
    private JFXButton btnCancel;
    
    ManageUsersWindowController prevContrl;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new UserModel().getInstance();
        lblName.setText(model.getSelectedUser().getFName().trim());
    }    
    
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleConfirmDeleting(ActionEvent event) throws IOException {
        model.deleteSelectedUser();
        prevContrl.loadTableView();
        goBack(event);
    }

    void setInfo(ManageUsersWindowController aThis) {
        prevContrl = aThis;
    }
    


}
