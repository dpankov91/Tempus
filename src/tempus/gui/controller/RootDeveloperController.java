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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class RootDeveloperController implements Initializable {

    @FXML
    private Button btn_editprofile;
    @FXML
    private Button btn_settings;
    @FXML
    private JFXButton btn_timesheet;
    @FXML
    private BorderPane mainPane;
    @FXML
    private JFXButton btn_logout;
    @FXML
    private JFXButton btn_overview;
    @FXML
    private JFXButton btn_projects;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handle_editProfile(ActionEvent event) {
    }

    @FXML
    private void handle_Settings(ActionEvent event) {
        
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
