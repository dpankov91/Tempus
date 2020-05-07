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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dpank
 */
public class ManageClientsWindowController implements Initializable {

    @FXML
    private TableView<?> tableViewUsers;
    @FXML
    private TableColumn<?, ?> colClientName;
    @FXML
    private TableColumn<?, ?> colPhone;
    @FXML
    private TableColumn<?, ?> colAddress;
    @FXML
    private TableColumn<?, ?> colEmail;
    @FXML
    private JFXButton btnAddClient;
    @FXML
    private JFXButton editClient;
    @FXML
    private JFXButton deleteClient;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionAddClient(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tempus/gui/view/AddClient.fxml"));
            Parent z = loader.load();
//            loader.<AddClientController>getController().setInfo(this);
            Scene scene = new Scene(z);
            Stage s = new Stage();
            s.setScene(scene);
            s.show();
    }

    @FXML
    private void onActionEditClient(ActionEvent event) {
    }

    @FXML
    private void onActionDeleteClient(ActionEvent event) {
    }
    
}
