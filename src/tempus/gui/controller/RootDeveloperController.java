/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import tempus.be.User;
import tempus.gui.model.UserModel;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class RootDeveloperController implements Initializable {

    @FXML
    private Button btn_editprofile;
    @FXML
    private JFXButton btn_timesheet;
    @FXML
    private JFXButton btn_overview;
    @FXML
    private JFXButton btn_projects;
    @FXML
    private BorderPane mainPane;
    @FXML
    private JFXButton btn_logout;
    @FXML
    private Button btn_help;
    @FXML
    private Label lbl_fname;
    @FXML
    private Label lbl_lname;

    private User user;
    @FXML
    private Label lblTodaysDate;
    @FXML
    private ImageView imageUser;

    private UserModel userModel;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userModel = UserModel.getInstance();
        showDate();
        setUpimg();
        setFirstName();
        setLastName();
    }  
    
    public void setFirstName() {
        User us = userModel.getloggedInUser();
        lbl_fname.setText(us.getFName());
    }
    
    public void setLastName() {
        User us = userModel.getloggedInUser();
        lbl_lname.setText(us.getLName());
    }

    @FXML
    private void handle_editProfile(ActionEvent event) {
        System.out.println("Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("UserEditWindow");
        mainPane.setCenter(view); 
    }

    @FXML
    private void handle_Help(ActionEvent event) {
        System.out.println("Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("HelpWindow");
        mainPane.setCenter(view); 
    }
    
    @FXML
    private void handle_TimeSheet(ActionEvent event) {
        System.out.println("Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("DeveloperTimeTracker");
        mainPane.setCenter(view);  
    }

    @FXML
    private void handle_Overview(ActionEvent event) {
        System.out.println("Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("DeveloperOverview");
        mainPane.setCenter(view); 
    }

    @FXML
    private void handle_Projects(ActionEvent event) {
        System.out.println("Clicked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("DeveloperProjectInfo");
        mainPane.setCenter(view); 
    }

    @FXML
    private void handle_Logout(ActionEvent event) {
        Platform.exit();
    }

    private void showDate() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        lblTodaysDate.setText(dateFormat.format(date));
    }

    private void setUpimg() {
        User us = userModel.getloggedInUser();
        imageUser.setImage(us.getPhotoURL());
    }
    
}
