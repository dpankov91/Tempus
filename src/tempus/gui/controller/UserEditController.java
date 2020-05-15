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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import tempus.be.User;
import tempus.gui.model.UserModel;

/**
 * FXML Controller class
 *
 * @author dpank
 */
public class UserEditController implements Initializable {

    @FXML
    private JFXButton btn_save;
    @FXML
    private JFXButton btn_cancel;
    @FXML
    private ComboBox<User> chooseUserEditCombo;
     @FXML
    private JFXTextField txt_name;
    @FXML
    private JFXTextField txt_Lname;
    @FXML
    private JFXTextField txt_email;
    @FXML
    private JFXTextField txt_phone;
    @FXML
    private JFXTextField txt_address;
    @FXML
    private JFXTextField txt_postcode;

    UserModel userModel;
    User currentSelectedUser ;
    ManageUsersWindowController prevContrl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        userModel = UserModel.getInstance();
        ObservableList<User> obsUsers = FXCollections.observableArrayList(userModel.getAllUsers());
        chooseUserEditCombo.setItems(obsUsers);
    }

    @FXML
    private void handle_Save(ActionEvent event) {
        String name = txt_name.getText();
        String Lname = txt_Lname.getText();
        String email = txt_email.getText();
        if (!name.isEmpty() && !Lname.isEmpty() && !email.isEmpty()) {
            String phone = txt_phone.getText();
            int realphone;
            if (phone.isEmpty()) {
                realphone = -1;
            } else {
                realphone = convertToInt(phone);
            }
            String postcode = txt_postcode.getText();
            int realpostcode;
            if (postcode.isEmpty()) {
                realpostcode = -1;
            } else {
                realpostcode = convertToInt(postcode);
            }
            String address = txt_address.getText();
            if(address.isEmpty()){
                address="";
            }
            userModel.editUser(currentSelectedUser.getId(),name,Lname,email,realphone,realpostcode,address,"No", "No");
            
            prevContrl.loadTableView();
            handle_Cancel(event);  
            
        } else {
            // Error
        }
    }

    private int convertToInt(String gottentString) {
        return Integer.parseInt(gottentString);
    }

    @FXML
    private void handle_Cancel(ActionEvent event) {
         Stage stage = (Stage) btn_cancel.getScene().getWindow();
         prevContrl.loadTableView();
         stage.close();
    }

    @FXML
    private void onActionChooseEditUser(ActionEvent event) {
        currentSelectedUser = chooseUserEditCombo.getSelectionModel().getSelectedItem();
        txt_name.setText(currentSelectedUser.getFName());
        txt_Lname.setText(currentSelectedUser.getLName());
        txt_email.setText(currentSelectedUser.getEmail());
        txt_phone.setText(Integer.toString(currentSelectedUser.getPhone()));
        txt_address.setText(currentSelectedUser.getAddress());
        txt_postcode.setText(Integer.toString(currentSelectedUser.getPostcode()));

    }

    void setInfo(ManageUsersWindowController aThis) {
        prevContrl = aThis;
    }

}
