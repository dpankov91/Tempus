/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author dpank
 */
public class AddClientController implements Initializable {

    @FXML
    private JFXTextField firstNameNewClient;
    @FXML
    private JFXTextField cityNewClient;
    @FXML
    private JFXTextField phoneNewClient;
    @FXML
    private JFXButton saveNewClient;
    @FXML
    private JFXButton cancelAddingClient;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionSaveNewClient(ActionEvent event) {
    }

    @FXML
    private void onActionCancelAdding(ActionEvent event) {
    }

//    void setInfo(ManageClientsWindowController aThis) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
}
