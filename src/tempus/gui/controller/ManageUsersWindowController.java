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
public class ManageUsersWindowController implements Initializable {

    @FXML
    private TableColumn<?, ?> firstName;
    @FXML
    private TableColumn<?, ?> lastName;
    @FXML
    private TableColumn<?, ?> userID;
    @FXML
    private JFXButton addUser;
    @FXML
    private JFXButton editUser;
    @FXML
    private JFXButton deleteUser;
    @FXML
    private TableView<?> tableViewUsers;
    
    Object selectedUser = tableViewUsers.getSelectionModel().getSelectedItem();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionAddUser(ActionEvent event) {
    }

    @FXML
    private void onActionEditUser(ActionEvent event) {
    }

    @FXML
    private void onActionDeleteUser(ActionEvent event) throws IOException {
        if(selectedUser !=null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tempus/gui/view/DeleteConfirmation.fxml"));
            Parent z = loader.load();
            Scene scene = new Scene(z);
            Stage s = new Stage();
            s.setScene(scene);
            s.show();
        }
        
    }
    
}
