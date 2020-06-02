/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tempus.gui.model.TaskModel;
import tempus.gui.model.UserModel;

/**
 * FXML Controller class
 *
 * @author dpank
 */
public class DeleteConfirmationTaskController implements Initializable {

    @FXML
    private JFXButton btnCancel;
    @FXML
    private Label lblName;

    
    TaskModel tsModel;
    UserModel usModel;
    AdminTimeTrackerController prevContrl;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tsModel = TaskModel.getInstance();
        usModel = UserModel.getInstance();
        lblName.setText(tsModel.getSelectedTask().getTask().trim());
    }    

    @FXML
    private void goBack(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleConfirmDeleting(ActionEvent event) {
           tsModel.deleteSelectedTask();
           prevContrl.loadInTaskView();
           goBack(event);
    }

    void setInfo(AdminTimeTrackerController aThis) {
        prevContrl = aThis;
    }
}
