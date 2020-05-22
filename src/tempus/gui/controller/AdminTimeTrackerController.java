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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
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
    private TableView<Task> tbv_timetracker;
    @FXML
    private TableColumn<Task, String> colProj;
    @FXML
    private TableColumn<Task, String> colTask;
    @FXML
    private TableColumn<Task, String> colNote;
    @FXML
    private TableColumn<Task, String> colStartTime;
    @FXML
    private TableColumn<Task, String> colEndTime;
    @FXML
    private TableColumn<Task, Integer> colHrs;
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
    ImageView imgView;

    private static final int STARTTIME = 0;
    private final IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    private final IntegerProperty timeMinutes = new SimpleIntegerProperty(STARTTIME);
    private final IntegerProperty timeHours = new SimpleIntegerProperty(STARTTIME);

    private ScheduledExecutorService ThreadExecutor;
    long totalSeconds = 0;
    boolean isStopped = true;
    private TaskModel tsModel;
    private ProjectModel projModel;
    @FXML
    private Label lbl_date;

    ObservableList<Project> allProjects = FXCollections.observableArrayList();

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
        btn_stop.setDisable(true);
        loadProjectsToComboBox();
        setUpTableView();
        showDate();
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
            imgView.setImage(new Image("/tempus/gui/assets/icons8-pause-button-50.png"));
            btn_stop.setDisable(false);
            //Plays again
        } else {
            isStopped = true;
            ThreadExecutor.shutdownNow();
            imgView.setImage(new Image("/tempus/gui/assets/icons8-circled-play-50.png"));

            //Stops the thread
        }
    }

    @FXML
    private void handle_Stop(ActionEvent event) {
        ThreadExecutor.shutdownNow();
        imgView.setImage(new Image("/tempus/gui/assets/icons8-circled-play-50.png"));
        //Reset time
        timeSeconds.setValue(0);
        timeMinutes.setValue(0);
        timeHours.setValue(0);
        totalSeconds = 0;
        isStopped = true;
        btn_stop.setDisable(true);
        //Here call model and insert time into database
    }

    private void handle_Start(ActionEvent event) {
        setUpThread();
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

    public void loadProjectsToComboBox() {
        allProjects = projModel.getObsProjects();

        for (Project proj : allProjects) {
            cb_projects.setItems(allProjects);
        }
    }

    void setUpTableView() {

        colProj.setCellFactory(TextFieldTableCell.forTableColumn());
        colProj.setCellValueFactory(new PropertyValueFactory<>("projName"));
        colTask.setCellFactory(TextFieldTableCell.forTableColumn());
        colTask.setCellValueFactory(new PropertyValueFactory<>("task"));
        colNote.setCellFactory(TextFieldTableCell.forTableColumn());
        colNote.setCellValueFactory(new PropertyValueFactory<>("note"));
        colStartTime.setCellFactory(TextFieldTableCell.forTableColumn());
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellFactory(TextFieldTableCell.forTableColumn());
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        colHrs.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colHrs.setCellValueFactory(new PropertyValueFactory<>("spentTime"));
        loadTableView();
    }

    private void loadTableView() {
        tbv_timetracker.getItems().clear();
        List<Task> allTasks = tsModel.getAllTasks();
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        tasks.addAll(allTasks);
        tbv_timetracker.setItems(tasks);
    }

    private void showDate() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        lbl_date.setText(dateFormat.format(date));
    }

    @FXML
    private void writeToDatabase(TableColumn.CellEditEvent<Task, String> event) {
        Task task = event.getRowValue();
        String assignedValue = event.getNewValue();
        if (event.getNewValue().toString().isEmpty()) {
            assignedValue = "None";
        }
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //Task //Start Time //End Time // Note // Time Spent
        switch (event.getTableColumn().getText()) {
            case "Task"://task.getId(), task.getTask(), task.getsStartTime(), task.getEndTime(), task.getNote(),task.getSpentTime()
                tsModel.editTask(task.getId(), assignedValue, task.getsStartTime(), task.getsEndTime(), task.getNote(), task.getSpentTime());
                task.setTask(assignedValue);
                break; // int id,String projectName, String clientName, String hourlyRate, String description
            case "Note":
                tsModel.editTask(task.getId(), task.getTask(), task.getsStartTime(), task.getsEndTime(), assignedValue, task.getSpentTime());
                task.setNote(assignedValue);
                break;
            case "Start Time":
              
                LocalDateTime dateTime = LocalDateTime.parse(assignedValue, formatter);

                tsModel.editTask(task.getId(), task.getTask(), dateTime, task.getsEndTime(), task.getNote(), task.getSpentTime());
                task.setStartTime(dateTime);
                break;
            case "End Time":
                LocalDateTime endTime = LocalDateTime.parse(assignedValue, formatter);

                tsModel.editTask(task.getId(), task.getTask(), task.getsStartTime(), endTime, task.getNote(), task.getSpentTime());
                task.setEndTime(endTime);
                break;
        }

    }

    @FXML
    private void writeToDatabaseNumber(TableColumn.CellEditEvent<Task, Integer> event) {//Time Spent
    }

}
