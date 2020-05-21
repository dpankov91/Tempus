/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
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
public class OverviewDeveloperController implements Initializable {

    @FXML
    private TableView<Task> tableProject;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colTask;
    @FXML
    private TableColumn<?, ?> colStartTime;
    @FXML
    private TableColumn<?, ?> colEndTime;
    @FXML
    private TableColumn<?, ?> colHrs;
    @FXML
    private JFXComboBox<Project> cmbProjects;
    @FXML
    private JFXButton btnAllProjects;
    @FXML
    private Label lblSumHrs;
    @FXML
    private Pane paneBarChart;
    @FXML
    private JFXDatePicker dateFrom;
    @FXML
    private JFXDatePicker dateTo;
    @FXML
    private JFXButton btnShow;
    
    ObservableList<Project> allProjects = FXCollections.observableArrayList();
    private ProjectModel projModel;
    private UserModel usModel;
    private TaskModel taskModel;
    private Date fromDate;
    private Date toDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        projModel = ProjectModel.getInstance();
        usModel = UserModel.getInstance();
        taskModel = TaskModel.getInstance();
        loadProjectsToCombobox();
        setUpTaskTableView();
    }    

    @FXML
    private void onSelectLoadSelectedProjectTable(ActionEvent event) {
    }

    @FXML
    private void onClickLoadAllProjectsTable(ActionEvent event) {
    }

    @FXML
    private void formateDate(ActionEvent event) {
    }

    @FXML
    private void onClickShowBarChart(ActionEvent event) {
    }
    
    private void loadProjectsToCombobox() {
        allProjects = projModel.getObsProjects();

        for (Project proj : allProjects) {
            cmbProjects.setItems(allProjects);
        }
    }
    
    private void setUpTaskTableView() {
        colName.setCellValueFactory(new PropertyValueFactory<>("projName"));
        colTask.setCellValueFactory(new PropertyValueFactory<>("task"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        colHrs.setCellValueFactory(new PropertyValueFactory<>("spentTime"));
        loadAllTaskTableView();
        setSumHrsToLabel();
    }
    
    private void loadAllTaskTableView() {
        tableProject.getItems().clear();
        List<Task> allTasksOverview = taskModel.getAllTasksOverview();
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        tasks.addAll(allTasksOverview);
        tableProject.setItems(tasks);
    }
    
     private void setSumHrsToLabel()
    {
        double total = tableProject.getItems().stream().collect(Collectors.summingDouble(Task::getSpentTime));
        lblSumHrs.setText(String.valueOf(total));
    }
    
}
