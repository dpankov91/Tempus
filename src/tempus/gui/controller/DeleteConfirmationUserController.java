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
 * The DeleteConfirmationUserController is a class. It allows the user to delete 
 * another user inside the ManageUsersWindowController
 *
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Christian Hansen
 * @author Dmitri Pankov
 * @author Nebojsa Gutic
 * @author Tienesh Kanagarasan
 */
public class DeleteConfirmationUserController implements Initializable {


    @FXML
    private Label lblName;
    UserModel model;
    
    
    
    private User selectedUser;
    @FXML
    private JFXButton btnCancel;
    
    ManageUsersWindowController prevContrl; // Takes information frmo the ManageUserstsWindow fxml.
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
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
        model.deleteSelectedUser();  // having selected a user to delete, it's brought down to the layer down, to the user model
        prevContrl.loadTableView();
        goBack(event);
    }

    void setInfo(ManageUsersWindowController aThis) {
        prevContrl = aThis;
    }
    


}
