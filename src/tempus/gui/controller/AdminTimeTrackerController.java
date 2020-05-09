/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
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
    private Label lbl_start;
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

    
    Map<Integer, String> numberMap;
    Integer currSeconds;
    Thread thread;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    Integer hmsToSeconds(Integer h, Integer m, Integer s) {
        Integer hToSeconds = h*3600;
        Integer mToSeconds = m*60;
        Integer total = hToSeconds + mToSeconds + s;
        return total;
    }
    
    LinkedList<Integer> secondsToHms(Integer currSecond){
        Integer hours = currSeconds / 3600;
        currSecond = currSecond%3600;
        Integer minutes = currSecond / 60;
        currSecond = currSecond%60;
        Integer seconds = currSecond;
        LinkedList<Integer> answer = new LinkedList<>();
        answer.add(hours);
        answer.add(minutes);
        answer.add(seconds);
        return answer;
    }
    
    void setOutput() {
        LinkedList<Integer> currHms = secondsToHms(currSeconds);
        hoursTimer.setText(numberMap.get(currHms.get(0)));
        minutesTimer.setText(numberMap.get(currHms.get(1)));
        secondsTimer.setText(numberMap.get(currHms.get(2)));
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
        thread.resume();
    }

    @FXML
    private void handle_Pause(ActionEvent event) {
        thread.suspend();
    }

    @FXML
    private void handle_Stop(ActionEvent event) {
       thread.stop();
    }
    
}
