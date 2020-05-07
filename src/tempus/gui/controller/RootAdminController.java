/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tempus.be.User;
import tempus.gui.model.UserModel;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class RootAdminController implements Initializable {

    @FXML
    private Button btn_editprofile;
    @FXML
    private JFXButton btn_users;
    @FXML
    private JFXButton btn_overview;
    @FXML
    private JFXButton btn_projects;
    @FXML
    BorderPane mainPane;
    @FXML
    private JFXButton btnLogOut;
    @FXML
    private Button btn_help;
    @FXML
    private Label lbl_fname;

    private User user;
    @FXML
    private ImageView imageUser;
    @FXML
    private Label lblTodaysDate;

    private UserModel userModel;
    @FXML
    private JFXButton btn_timesheet;
    @FXML
    private JFXButton btn_clients;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userModel = UserModel.getInstance();
        showDate();
        setUpimg();
        setFirstName();

    }

    public void setFirstName() {
      User us = userModel.getloggedInUser();
        lbl_fname.setText(us.getFName()+" "+us.getLName());
    }



    public void setUpimg() {
        User us = userModel.getloggedInUser();
        imageUser.setImage(us.getPhotoURL());
    }

    @FXML
    private void handle_editProfile(ActionEvent event) {
//        System.out.println("Clicked");
//        FxmlLoader object = new FxmlLoader();
//        Pane view = object.getPage("");
//        mainPane.setCenter(view); 
    }

    @FXML
    private void handle_Help(ActionEvent event) {
//        System.out.println("Clicked");
//        FxmlLoader object = new FxmlLoader();
//        Pane view = object.getPage("");
//        mainPane.setCenter(view); 

    }

    @FXML
    private void handle_Users(ActionEvent event) {
        System.out.println("Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("ManageUsersWindow");
        mainPane.setCenter(view);
    }

    @FXML
    private void handle_Overview(ActionEvent event) {
        System.out.println("Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("AdminOverview");
        mainPane.setCenter(view);
    }

    @FXML
    void handle_Projects(ActionEvent event) {
        System.out.println("Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("ManageProjectsWindow");
        mainPane.setCenter(view);
    }

    @FXML
    private void handle_Logout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tempus/gui/view/Login.fxml"));
        Parent z = loader.load();
        Scene scene = new Scene(z);
        Stage s = new Stage();
        s.setScene(scene);
        s.show();
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.close();
    }

    private void showDate() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        lblTodaysDate.setText(dateFormat.format(date));
    }

    @FXML
    private void handle_Timesheet(ActionEvent event) 
    {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("AdminTimeTracker");
        mainPane.setCenter(view);  
    }

    @FXML
    private void handle_clients(ActionEvent event) {
    }

}
