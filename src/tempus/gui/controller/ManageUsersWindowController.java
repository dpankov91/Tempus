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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
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
    private TableColumn<User, String> firstName;
    @FXML
    private TableColumn<User, String> lastName;
    @FXML
    private TableColumn<User, String> idEmail;
    @FXML
    private TableColumn<User, String> col_role;
    @FXML
    private TableColumn<User, Integer> colPhone;
    @FXML
    private TableColumn<User, String> colAddress;
    @FXML
    private TableColumn<User, Integer> colPostcode;

    private UserModel userModel;
    @FXML
    private JFXButton addUser;
    @FXML
    private JFXButton deleteUser;

    public User selectedUser;
    @FXML
    private JFXButton btnChangePassword;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userModel = UserModel.getInstance();
        setUpTableView();

    }

    @FXML
    private void onActionAddUser(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tempus/gui/view/UserAddWindow.fxml"));
        Parent z = loader.load();
        loader.<UserAddController>getController().setInfo(this);
        Scene scene = new Scene(z);
        Stage s = new Stage();
        s.setScene(scene);
        s.show();
    }

    @FXML
    private void onActionDeleteUser(ActionEvent event) throws IOException {
        {
            userModel.setSelectedUser(tableViewUsers.getSelectionModel().getSelectedItem());
            if (userModel.getSelectedUser() != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tempus/gui/view/DeleteConfirmationUser.fxml"));
                Parent z = loader.load();
                loader.<DeleteConfirmationUserController>getController().setInfo(this);
                Scene scene = new Scene(z);
                Stage s = new Stage();
                s.setScene(scene);
                s.show();
            }
        }
    }

    void loadTableView() {
        tableViewUsers.getItems().clear();
        List<User> allUsers = userModel.getAllUsers();
        ObservableList<User> users = FXCollections.observableArrayList();
        users.addAll(allUsers);
        tableViewUsers.setItems(users);
    }

    private void setUpTableView() {
        tableViewUsers.setEditable(true);

        firstName.setCellFactory(TextFieldTableCell.forTableColumn());
        firstName.setCellValueFactory(new PropertyValueFactory<>("fName"));
        lastName.setCellFactory(TextFieldTableCell.forTableColumn());
        lastName.setCellValueFactory(new PropertyValueFactory<>("lName"));
        //col_role.setCellFactory(TextFieldTableCell.forTableColumn());
        col_role.setCellValueFactory(new PropertyValueFactory<>("role"));
        idEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        idEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPostcode.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colPostcode.setCellValueFactory(new PropertyValueFactory<>("postcode"));
        colPhone.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        loadTableView();
    }

    @FXML
    private void writeToDatabase(TableColumn.CellEditEvent<User, String> event) {
        User us = event.getRowValue();
        String assignedValue = event.getNewValue();
        if (event.getNewValue().toString().isEmpty()) {
            assignedValue = "None";
        }
        switch (event.getTableColumn().getText()) {
            case "First Name":
                userModel.editUser(us.getId(), assignedValue, us.getLName(), us.getEmail(), us.getPhone(), us.getPostcode(), us.getAddress(),us.getRealPhotoURL(),us.getPassword());
                us.setfName(assignedValue);
                break;
            case "Last Name":
                userModel.editUser(us.getId(), us.getFName(), assignedValue, us.getEmail(), us.getPhone(), us.getPostcode(), us.getAddress(),us.getRealPhotoURL(),us.getPassword());
                us.setLName(assignedValue);
                break;
            case "Role":
                /* userModel.editUser(us.getId(), us.getFName(), event.getNewValue(), us.getEmail(), us.getPhone(), us.getPostcode(), us.getAddress());
                us.setRole(event.getNewValue());*/
                break;
            case "E-Mail":
                userModel.editUser(us.getId(), us.getFName(), us.getLName(), assignedValue, us.getPhone(), us.getPostcode(), us.getAddress(),us.getRealPhotoURL(),us.getPassword());
                us.setEmail(assignedValue);
                break;
            case "Address":
                userModel.editUser(us.getId(), us.getFName(), us.getLName(), us.getEmail(), us.getPhone(), us.getPostcode(), assignedValue,us.getRealPhotoURL(),us.getPassword());
                us.setAddress(assignedValue);
                break;
        }

    }

    @FXML
    private void writeToDatabaseNumber(TableColumn.CellEditEvent<User, Integer> event) {
        User us = event.getRowValue();
        int assignedValue ;
        if (event.getNewValue() == null) {
            assignedValue = -1;
        }else{
            assignedValue = event.getNewValue();
        }
        switch (event.getTableColumn().getText()) {
            case "Phone":
                userModel.editUser(us.getId(), us.getFName(), us.getLName(), us.getEmail(), assignedValue, us.getPostcode(), us.getAddress(),us.getRealPhotoURL(),us.getPassword());
                us.setPhone(assignedValue);
                break;
            case "Postcode":
                userModel.editUser(us.getId(), us.getFName(), us.getLName(), us.getEmail(), us.getPhone(), assignedValue, us.getAddress(),us.getRealPhotoURL(),us.getPassword());
                us.setPostcode(assignedValue);
                break;

        }

    }

    @FXML
    private void onClickOpensEditPassWindow(ActionEvent event) throws IOException {
            {
            userModel.setSelectedUser(tableViewUsers.getSelectionModel().getSelectedItem());
            if (userModel.getSelectedUser() != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tempus/gui/view/ChangePassword.fxml"));
                Parent z = loader.load();
                Scene scene = new Scene(z);
                Stage s = new Stage();
                s.setScene(scene);
                s.show();
            }
        }
    }

}
