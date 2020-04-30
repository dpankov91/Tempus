/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tempus.be.User;
import tempus.gui.model.UserModel;

/**
 * FXML Controller class
 *
 * @author dpank
 */
public class ManageUsersWindowController implements Initializable {
    @FXML
    private TableView<User> tableViewUsers;
    @FXML
    private TableColumn<User,String> firstName;
    @FXML
    private TableColumn<User,String> lastName;
    @FXML
    private TableColumn<User,Integer> userID;
    @FXML
    private TableColumn<User, String> idEmail;
   
    @FXML
    private JFXButton addUser;
    @FXML
    private JFXButton editUser;
    @FXML
    private JFXButton deleteUser;
    @FXML


  
    
    Object selectedUser = tableViewUsers.getSelectionModel().getSelectedItem();
    UserModel useModel;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tempus/gui/view/DeleteConfirmationUser.fxml"));
            Parent z = loader.load();
            Scene scene = new Scene(z);
            Stage s = new Stage();
            s.setScene(scene);
            s.show();
        }
        
    }
     private void setUpTableView()
    {
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
          userID.setCellValueFactory(new PropertyValueFactory<>("userID"));
            idEmail.setCellValueFactory(new PropertyValueFactory<>("idEmail"));
       
        loadTableView();
    }

    private void loadTableView() {
         tableViewUsers.getItems().clear();
        List<User> allUsers = UserModel.getAllUsers();
        ObservableList<User> users = FXCollections.observableArrayList();
       users.addAll(allUsers);
        tableViewUsers.setItems(users);
    }
    
}
