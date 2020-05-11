/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author dpank
 */
public class AdminTimeTrackerController implements Initializable {

    @FXML
    private DatePicker datepicker;
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
    @FXML
    private TableView<?> tbv_timetracker;
    @FXML
    private ComboBox<?> cb_projects;
    @FXML
    private JFXTextField txt_task;
    @FXML
    private JFXTextField txt_note;
    @FXML
    private Button btn_createtask;
    @FXML
    private Button btn_play;
    @FXML
    private Button btn_pause;
    @FXML
    private Button btn_stop;
    @FXML
    private Text secondsTimer;
    @FXML
    private Text minutesTimer;
    @FXML
    private Text hoursTimer;
    @FXML
    private JFXButton btn_start;
    
    private static final int STARTTIME = 0;
    private Timeline timeline;
    private final IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        secondsTimer.textProperty().bind(timeSeconds.asString());
    }

    private void updateTime() {
        // implements seconds
        int seconds = timeSeconds.get();
        timeSeconds.set(seconds+1);
    }
    
    @FXML
    private void handle_CreateTask(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tempus/gui/view/NewTimeEntry.fxml"));
        Parent z = loader.load();
        Scene scene = new Scene(z);
        Stage s = new Stage();
        s.setScene(scene);
        s.show();
    }

    @FXML
    private void handle_Play(ActionEvent event) {
        timeline.play();
    }

    @FXML
    private void handle_Pause(ActionEvent event) {
       timeline.pause();

    }

    @FXML
    private void handle_Stop(ActionEvent event) {
        timeline.stop();
    }

    @FXML
    private void handle_Start(ActionEvent event) {
        btn_start.setDisable(true); // prevents multiple instances
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> updateTime())); 
        timeline.setCycleCount(Animation.INDEFINITE); // repeats loop
        timeSeconds.set(STARTTIME);
        timeline.play();
    }
    
}
