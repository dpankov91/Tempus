/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author dpank
 */
public class DeveloperProjectInfoController implements Initializable {

    @FXML
    private JFXPasswordField txt_password;
    @FXML
    private JFXTextField txt_author;
    @FXML
    private JFXTextField txt_company;
    @FXML
    private JFXTextField txt_email;
    @FXML
    private JFXTextField txt_address;
    @FXML
    private JFXTextField txt_date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
