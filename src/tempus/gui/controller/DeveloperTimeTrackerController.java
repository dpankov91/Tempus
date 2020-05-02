/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author dpank
 */
public class DeveloperTimeTrackerController implements Initializable {

    @FXML
    private Button btn_createtask;
    @FXML
    private Label lbl_date;
    @FXML
    private DatePicker datepicker;
    @FXML
    private TableView<?> tbv_week;
    @FXML
    private Button btn_stoptask;
    @FXML
    private Button btn_edittask;
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
    private TableColumn<?, ?> sunday;
    @FXML
    private TableColumn<?, ?> col_timespent;
    @FXML
    private AnchorPane timerPane;
    @FXML
    private Text hoursTimer;
    @FXML
    private Text minutesTimer;
    @FXML
    private Text secondsTimer;
    @FXML
    private JFXButton btn_cancel;
    @FXML
    private AnchorPane menuPane;
    @FXML
    private JFXComboBox<Integer> hoursInput;
    @FXML
    private JFXComboBox<Integer> minutesInput;
    @FXML
    private JFXComboBox<Integer> secondsInput;
    @FXML
    private JFXButton btn_start;
    
    Map<Integer, String> numberMap;
    Integer currSeconds;
    Thread thrd;
         
     /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Integer> hoursList = FXCollections.observableArrayList();
        ObservableList<Integer> minutesAndSecondsList =  FXCollections.observableArrayList();
        for (int i = 0; i <= 60; i++) {
            if (0 <= 1 && i <= 24) {
                hoursList.add(new Integer(i));            
            }
            minutesAndSecondsList.add(new Integer(i));
        }
        hoursInput.setItems(hoursList);
        hoursInput.setValue(0);
        
        minutesInput.setItems(minutesAndSecondsList);
        minutesInput.setValue(0);
        
        secondsInput.setItems(minutesAndSecondsList);
        secondsInput.setValue(0);
        
        numberMap = new TreeMap<Integer, String>();
        for (Integer i = 0; i <= 60; i++) {
            if(0<=i && i<= 9) {
                numberMap.put(i, "0" + i.toString());
            } else {
                numberMap.put(i, i.toString());
            }
        }
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
    
        void scrollUp() {
        ParallelTransition pt = new ParallelTransition();
        pt.setOnFinished(e -> {
            try {
                System.out.println("Start Countdown...");
                startCountDown();
            } catch (Exception e2) {
                // TODO: handle exception
            }
        });
        pt.play();
        
    }
    
    void scrollDown() {
        ParallelTransition pt = new ParallelTransition();
        pt.play();
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
    private void handle_StopTask(ActionEvent event) {
    }

    @FXML
    private void handle_EditTask(ActionEvent event) {
    }

    @FXML
    private void handle_Cancel(ActionEvent event) {
        timerPane.setVisible(false);
        menuPane.setVisible(true);
        thrd.stop();
        scrollDown();
    }

    @FXML
    private void handle_Start(ActionEvent event) {  
        scrollUp();
        timerPane.setVisible(true);
        menuPane.setVisible(false);
        currSeconds = hmsToSeconds(hoursInput.getValue(), minutesInput.getValue(), secondsInput.getValue());
        hoursInput.setValue(0);
        minutesInput.setValue(0);
        secondsInput.setValue(0);     
    }
    
    void startCountDown() {
        thrd = new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    while(true) { 
                            setOutput();
                        Thread.sleep(1000);
                        if(currSeconds == 0) {
                            System.out.println("Finished");
                            scrollDown();
                            thrd.stop();
                        }
                          currSeconds -= 1;
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        });
        thrd.start();
    }
    
    void setOutput() {
        LinkedList<Integer> currHms = secondsToHms(currSeconds);
        hoursTimer.setText(numberMap.get(currHms.get(0)));
        minutesTimer.setText(numberMap.get(currHms.get(1)));
        secondsTimer.setText(numberMap.get(currHms.get(2)));
    }
}
