/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author dpank
 */
public class AdminTimeTrackerController implements Initializable {

    @FXML
    private DatePicker datepicker;
    @FXML
    private TableView<?> tbv_week;
    @FXML
    private TableColumn<?, ?> monday;
    @FXML
    private TableColumn<?, ?> tuesday;
    @FXML
    private TableColumn<?, ?> wednesday;
    @FXML
    private TableColumn<?, ?> thursday;
    @FXML
    private TableColumn<?, ?> friday;
    @FXML
    private TableColumn<?, ?> saturday;
    @FXML
    private TableColumn<?, ?> col_timespent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
