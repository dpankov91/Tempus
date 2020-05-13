/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author dpank
 */
public class SettingsWindowController implements Initializable {

    @FXML
    private JFXButton btnClose;
    @FXML
    private JFXPasswordField pswFirstTime;
    @FXML
    private JFXPasswordField pswSecondConfirm;
    @FXML
    private JFXButton btnConfrim;
    @FXML
    private ImageView imgPhoto;
    @FXML
    private JFXButton btnEditPic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickClose(ActionEvent event) {
    }

    @FXML
    private void onClickConfirm(ActionEvent event) {
    }

    @FXML
    private void onClickOpenPicSearcher(ActionEvent event) {
    }
    
}
