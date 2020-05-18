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
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tempus.be.Client;
import tempus.be.Project;
import tempus.be.Task;
import tempus.gui.model.ProjectModel;
import tempus.gui.model.TaskModel;

/**
 * FXML Controller class
 *
 * @author dpank
 */
public class AdminTimeTrackerController implements Initializable {

    @FXML
    private TableColumn<?, ?> monday;
    @FXML
    private TableColumn<?, ?> tuesday;
    @FXML
    private TableColumn<?, ?> wednesday;
    @FXML
    private TableColumn<?, ?> friday;
    @FXML
    private TableColumn<?, ?> saturday;
    @FXML
    private TableColumn<?, ?> col_timespent;
    @FXML
    private TableView<Task> tbv_timetracker;
    @FXML
    private ComboBox<Project> cb_projects;
    @FXML
    private JFXTextField txt_task;
    @FXML
    private JFXTextField txt_note;
    @FXML
    private Button btn_play;
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
    @FXML
    ImageView imgView;

    private static final int STARTTIME = 0;
    private Timeline timeline;
    private final IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    private final IntegerProperty timeMinutes = new SimpleIntegerProperty(STARTTIME);
    private final IntegerProperty timeHours = new SimpleIntegerProperty(STARTTIME);


    private ScheduledExecutorService ThreadExecutor;
    long totalSeconds = 0;
    boolean isStopped = false;
    boolean isPressed = true;
    private TaskModel tsModel;
    private ProjectModel projModel;
    @FXML
    private Label lbl_date;
    
    ObservableList<Project>  allProjects = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tsModel = TaskModel.getInstance();
        projModel = ProjectModel.getInstance();               
        secondsTimer.textProperty().bind(timeSeconds.asString());
        minutesTimer.textProperty().bind(timeMinutes.asString());
        hoursTimer.textProperty().bind(timeHours.asString());
        loadProjectsToComboBox();
    }

    private void updateTime() {
        
        /*//implements seconds            
        int seconds = timeSeconds.get();
        timeSeconds.set(seconds + 1);

        //implements minutes
        int min = timeSeconds.getValue() / 60;
        timeMinutes.set(min);

        // implements hours
        int hours = timeHours.getValue();
        timeHours.set(hours);

        System.out.println("Seconds: " + timeSeconds.getValue() + ", Minutes: " + +timeMinutes.getValue() + ", Hours: " + +timeHours.getValue());
        
        */
    }

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
        if (isStopped) {
            isStopped = false;
            setUpThread();
            //Plays again
        } else {
            isStopped = true;
            ThreadExecutor.shutdownNow();
            //Stops the thread
        }
        
        if (isPressed) {
            isPressed = true;
        imgView.setImage(new Image("/tempus/gui/assets/icons8-pause-button-50.png"));
                } else {
            isPressed = false;
        imgView.setImage(new Image("/tempus/gui/assets/icons8-circled-play-50.png"));
 
        }
        //timeline.play();
    }

    @FXML
    private void handle_Stop(ActionEvent event) {
        ThreadExecutor.shutdownNow();
        //Reset time
        timeSeconds.setValue(0);
        timeMinutes.setValue(0);
        timeHours.setValue(0);
        //Here call model and insert time into database
        //tsModel.insertTask();
    }

    @FXML
    private void handle_Start(ActionEvent event) {
        //Get current time
        totalSeconds = 0;
        setUpThread();        
        /*
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> updateTime()));
        try {
            //System.out.println("Seconds...");
        } catch (Exception e2) {
            // TODO: handle exception
        }
        timeline.setCycleCount(Animation.INDEFINITE); //repeats loop
        timeline.play();*/
    }

    private void setUpThread() {
        ThreadExecutor = Executors.newSingleThreadScheduledExecutor(); //Create new thread
        //Execute specified instructions in thread
        ThreadExecutor.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> { // Run later is for gui updates
                //Calculate seconds / minutes /hours
                totalSeconds++;

                long passedSeconds = (totalSeconds) % 60;
                long passedMinutes = (totalSeconds / 60) % 60;
                long passedHours = ((totalSeconds / 60) / 60) % 24;
                //Set them up in labels
                timeSeconds.setValue(passedSeconds);
                timeMinutes.setValue(passedMinutes);
                timeHours.setValue(passedHours);
                
                System.out.println("Seconds: " + timeSeconds.getValue() + ", Minutes: " + +timeMinutes.getValue() + ", Hours: " + +timeHours.getValue());
            });
        },
                0,//Initial delay of thread
                1, //Execute after given time (example every second)
                TimeUnit.SECONDS // Time unit
        );
    }
    
    public void loadProjectsToComboBox() 
    { 
        allProjects = projModel.getObsProjects();
        
        for (Project proj : allProjects) {
            cb_projects.setItems(allProjects);
        }    
    }


}
