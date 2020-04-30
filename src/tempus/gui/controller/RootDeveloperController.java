/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import tempus.be.User;

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



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void setFirstName(User currentUser) {
        this.user = currentUser;
        lbl_fname.setText(user.getLName());
    }
    
    public void setLastName(User currentUser) {
        this.user = currentUser;
        lbl_lname.setText(user.getLName());
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
    
}
