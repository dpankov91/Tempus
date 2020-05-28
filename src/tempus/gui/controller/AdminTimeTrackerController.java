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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
import javafx.scene.control.Alert;
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
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import tempus.be.Client;
import tempus.be.Project;
import tempus.be.Task;
import tempus.be.User;
import tempus.gui.model.ProjectModel;
import tempus.gui.model.TaskModel;
import tempus.gui.model.UserModel;

/**
 * FXML Controller class
 *
 * @author dpank
 */
public class AdminTimeTrackerController implements Initializable {

    @FXML
    private TableView<Task> tbv_timetracker;
    @FXML
    private TableColumn<Task, Object> colProj;
    @FXML
    private TableColumn<Task, String> colTask;
    @FXML
    private TableColumn<Task, String> colNote;
    @FXML
    private TableColumn<Task, String> colStartTime;
    @FXML
    private TableColumn<Task, String> colEndTime;
    @FXML
    private TableColumn<Task, Double> colHrs;
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
    @FXML
    private Label lbl_date;

    private static final int STARTTIME = 0;
    private final IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    private final IntegerProperty timeMinutes = new SimpleIntegerProperty(STARTTIME);
    private final IntegerProperty timeHours = new SimpleIntegerProperty(STARTTIME);

    private ScheduledExecutorService ThreadExecutor;
    long totalSeconds = 0;
    boolean isStopped = true;
    private TaskModel tsModel;
    private ProjectModel projModel;
    private UserModel usModel;
    boolean now = true;

    ObservableList<Project> allProjects = FXCollections.observableArrayList();
    @FXML
    private JFXButton btn_startTime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tsModel = TaskModel.getInstance();
        projModel = ProjectModel.getInstance();
        usModel = UserModel.getInstance();
        secondsTimer.textProperty().bind(timeSeconds.asString());
        minutesTimer.textProperty().bind(timeMinutes.asString());
        hoursTimer.textProperty().bind(timeHours.asString());
        btn_stop.setDisable(true);
        lbl_date.setVisible(false);
        loadProjectsToComboBox();
        setUpTableView();
    }

    @FXML
    private void handle_Play(ActionEvent event) {
        //Plays the thread
        if (isStopped) {
            isStopped = false;
            setUpThread();
            imgView.setImage(new Image("/tempus/gui/assets/icons8-pause-button-50.png"));
            btn_stop.setDisable(false);
            tsModel.setTimeStart(LocalDateTime.now());

        } else {
            //Pauses the thread
            isStopped = true;
            ThreadExecutor.shutdownNow();
            imgView.setImage(new Image("/tempus/gui/assets/icons8-circled-play-50.png"));
        }
    }

    private LocalDateTime showTime() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        lbl_date.setText(dateFormat.format(date));
        lbl_date.setVisible(true);
        return LocalDateTime.now();
    }

    @FXML
    private void handle_Stop(ActionEvent event) {
        tsModel.setTimeEnd(LocalDateTime.now());
        if (txt_task.getText() == null || txt_note.getText() == null || cb_projects.getSelectionModel().getSelectedItem() == null) {
            setUpAlert("Not filled Error", "Check if project is choosen, task name and task note are filled");
        } else {
            ThreadExecutor.shutdownNow();
            imgView.setImage(new Image("/tempus/gui/assets/icons8-circled-play-50.png"));

            //save task to db
            Project selectedProject = cb_projects.getSelectionModel().getSelectedItem();
            String taskName = txt_task.getText();
            String note = txt_note.getText();
            User loggedUser = usModel.getloggedInUser();
            tsModel.saveStoppedTask(selectedProject, taskName, note, loggedUser, tsModel.getTimeStart(), tsModel.getTimeEnd());
            //reset fields
            cb_projects.getSelectionModel().clearSelection();
            txt_task.clear();
            txt_note.clear();
            lbl_date.setVisible(false);
            //Reset time
            timeSeconds.setValue(0);
            timeMinutes.setValue(0);
            timeHours.setValue(0);
            totalSeconds = 0;
            isStopped = true;
            btn_stop.setDisable(true);
            //Here call model and insert time into database
        }
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
                0,//Initial delay of thread, no delay
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
        tbv_timetracker.setEditable(true);
        //colProj.setCellFactory(TextFieldTableCell.forTableColumn());
        colProj.setCellValueFactory(new PropertyValueFactory<>("projName"));
        colTask.setCellFactory(TextFieldTableCell.forTableColumn());
        colTask.setCellValueFactory(new PropertyValueFactory<>("task"));
        colNote.setCellFactory(TextFieldTableCell.forTableColumn());
        colNote.setCellValueFactory(new PropertyValueFactory<>("note"));
        colStartTime.setCellFactory(TextFieldTableCell.forTableColumn());
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellFactory(TextFieldTableCell.forTableColumn());
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        //colHrs.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
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

    private void setUpAlert(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
    @FXML
    private void writeToDatabase(TableColumn.CellEditEvent<Task, String> event) {
        Task task = event.getRowValue();
        String assignedValue = event.getNewValue();
        if (event.getNewValue().toString().isEmpty()) {
            assignedValue = "None";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        Task //Start Time //End Time // Note // Time Spent
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
//                case "Time Spent":
//                tsModel.editTask(task.getId(), task.getTask(), task.getsStartTime(), task.getsEndTime(), task.getNote(), assignedValue());
//                task.setSpentTime(assignedValue);
//                break;
        }

    }

    @FXML
    private void handle_StartTime(ActionEvent event) {
        showTime();    
    }

    @FXML
    private void writeToDatabaseNumber(TableColumn.CellEditEvent<String, Task> event) {
    }
}
