/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tempus.be.User;
import tempus.gui.model.UserModel;

/**
 * FXML Controller class
 *
 * @author Tienesh
 */
public class LoginController implements Initializable {

    @FXML
    private Button btnLogin;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtFieldUsername;
    @FXML
    private PasswordField pasPasswordField;
    UserModel model;
    @FXML
    private JFXRadioButton rdoRememberMe;

    private RootAdminController rtcontroller;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = UserModel.getInstance();
        rtcontroller = new RootAdminController();
        checkRememberMe();
    }

    @FXML
    private void clickLogIn(ActionEvent event) throws IOException {
        if (!checkIfFieldsAreEmpty()) {
            login();
        }
    }

    @FXML
    private void clickCancel(ActionEvent event) {
        Platform.exit();
    }

    private boolean checkIfFieldsAreEmpty() {
        if (txtFieldUsername.getText() == null || txtFieldUsername.getText().isEmpty()) {
            setUpAlert("No Username Error", "Please insert Username");
            return true;
        } else if (pasPasswordField.getText() == null || pasPasswordField.getText().isEmpty()) {
            setUpAlert("No Password Error", "Please insert Password");
            return true;
        }
        return false;
    }

    private void login() throws IOException {
        User us = model.loginUser(txtFieldUsername.getText(), pasPasswordField.getText());
        if (us != null) {
            setRememberMe();
            if (us.getIsAdmin()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tempus/gui/view/RootAdmin.fxml"));
                Parent z = loader.load();
                Scene scene = new Scene(z);
                Stage s = new Stage();
                s.setScene(scene);
                s.show();
                closeWindow();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tempus/gui/view/RootDeveloper.fxml"));
                Parent z = loader.load();
                Scene scene = new Scene(z);
                Stage s = new Stage();
                s.setScene(scene);
                s.show();
                closeWindow();
            }
        }
    }

    private void checkRememberMe() {
        Preferences prefs = Preferences.userNodeForPackage(LoginController.class);
        String username = prefs.get("email", null);
        String password = prefs.get("password", null);
        if (username != null) {
            txtFieldUsername.setText(username);
        }
        if (password != null) {
            rdoRememberMe.setSelected(true);
            pasPasswordField.setText(password);
        }
    }

    private void setRememberMe() {

        Preferences prefs = Preferences.userNodeForPackage(LoginController.class);
        prefs.put("email", txtFieldUsername.getText());
        if (rdoRememberMe.isSelected()) {
            prefs.put("password", pasPasswordField.getText());
        } else {
            prefs.remove("password");
        }
    }

    /*
    The setUpAlert method is used to alert the user, be it developer or admin, if either their password or username is not inserted into the right text fields-
    With a title and a message showing, the user is admin is notified to input the password or username if the fields are empty
     */
    private void setUpAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();

    }

    private void closeWindow() {
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clickRememberMe(ActionEvent event) {
    }

}
