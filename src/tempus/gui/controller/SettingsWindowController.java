/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tempus.be.User;
import tempus.gui.model.UserModel;

/**
 * FXML Controller class
 *
 * @author dpank
 */
public class SettingsWindowController implements Initializable {

    private final List<Image> images = new ArrayList<>();

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

    private User user;
    private UserModel userModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userModel = UserModel.getInstance();
        showImg();
    }

    @FXML
    private void onClickClose(ActionEvent event) {
        goBack();
    }

    @FXML
    private void onClickConfirm(ActionEvent event) {
        String pswFirst = pswFirstTime.getText();
        String pswSecond = pswSecondConfirm.getText();
        if (pswFirst.equals(pswSecond)){
            User us = userModel.getloggedInUser();
            userModel.editUser(us.getId(), us.getFName(), us.getLName(), us.getEmail(), us.getPhone(), us.getPostcode(), us.getAddress(),"No", us.getPassword());
            us.setPassword(pswSecond);
            userModel.newPassword(pswSecond);
          
        } 
        else if(pswSecond.equals("")) {
            System.out.println("The second field is empty, please input password");
        }
 
        else {
            System.out.println("The passwords do not match, try again");
        }  
        
        
    }

    @FXML
    private void onClickOpenPicSearcher(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select image files");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images",
                "*.png", "*.jpg", "*.gif", "*.tif", "*.bmp"));
        //  List<File> files = fileChooser.showOpenMultipleDialog(new Stage());
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            User us = userModel.getloggedInUser();
            userModel.editUser(us.getId(), us.getFName(), us.getLName(), us.getEmail(), us.getPhone(), us.getPostcode(), us.getAddress(),selectedFile.getAbsolutePath(), us.getPassword());
            us.setPhotoURL(selectedFile.getAbsolutePath());
            userModel.setUpLocalIMG(selectedFile.getAbsolutePath());
            showImg();
        }
    }

    private void showImg() {
        User us = userModel.getloggedInUser();

        imgPhoto.setImage(us.getPhotoURL());
    }

    private void goBack() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

}
