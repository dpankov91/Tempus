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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tempus.be.Client;
import tempus.gui.model.ClientModel;

/**
 * FXML Controller class
 *
 * @author dpank
 */
public class DeleteConfirmationClientController implements Initializable {
    
    ClientModel model;
    private Client selectedClient;
    ManageClientsWindowController clientContrl;
    
    @FXML
    private JFXButton btnCancel;
    @FXML
    private Label lblName;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new ClientModel().getInstance();
        lblName.setText(model.getSelectedClient().getName().trim());
    }    

    @FXML
    private void goBack(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleConfirmDeleting(ActionEvent event) {
         model.deleteSelectedClient();
         clientContrl.loadTableView();
         goBack(event);
    }

    void setInfo(ManageClientsWindowController aThis) {
        clientContrl = aThis;
    }
    
}
