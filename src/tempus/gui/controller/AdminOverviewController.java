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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
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
public class AdminOverviewController implements Initializable {

    private StackedBarChart<String, Integer> chartBar;
    @FXML
    private TableView<Task> tableProject;
    @FXML
    private TableColumn<?, String> colName;
    @FXML
    private TableColumn<?, String> colTask;
    @FXML
    private TableColumn<?, String> colUser;
    @FXML
    private TableColumn<?, String> colStartTime;
    @FXML
    private TableColumn<?, ?> colEndTime;
    @FXML
    private TableColumn<Task, Integer> colHrs;
    @FXML
    private JFXComboBox<Project> cmbProjects;
    @FXML
    private JFXButton btnAllProjects;
    @FXML
    private Pane paneBarChart;
    @FXML
    private JFXDatePicker dateFrom;
    @FXML
    private JFXDatePicker dateTo;
    @FXML
    private JFXComboBox<User> cmbUsers;
    @FXML
    private JFXButton btnShow;

    ObservableList<Project> allProjects = FXCollections.observableArrayList();
    ObservableList<User> allUsers = FXCollections.observableArrayList();
    private ProjectModel projModel;
    private UserModel usModel;
    private TaskModel taskModel;
    private Date fromDate;
    private Date toDate;
    @FXML
    private Label lblSumHrs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        projModel = ProjectModel.getInstance();
        usModel = UserModel.getInstance();
        taskModel = TaskModel.getInstance();
        loadProjectsToCombobox();
        loadUsersToCombobox();
        setUpTaskTableView();
    }


    @FXML
    private void onSelectLoadSelectedProjectTable(ActionEvent event) {
        //cmbUsers.getSelectionModel().clearSelection();
        Project pro = cmbProjects.getSelectionModel().getSelectedItem();
        loadSelectedProjectTableView(pro);
        setSumHrsToLabel();
    }

    @FXML
    private void onSelectLoadSelectedUserTable(ActionEvent event) {
        //cmbProjects.getSelectionModel().clearSelection();
        User us = cmbUsers.getSelectionModel().getSelectedItem();
        loadSelectedUsersTableView(us);
        setSumHrsToLabel();
    }

    @FXML
    private void onClickLoadAllProjectsTable(ActionEvent event) {
        setUpTaskTableView();
    }


    private void loadProjectsToCombobox() {
        allProjects = projModel.getObsProjects();

        for (Project proj : allProjects) {
            cmbProjects.setItems(allProjects);
        }
    }

    private void loadUsersToCombobox() {
        allUsers = usModel.getObsUsers();

        for (User us : allUsers) {
            cmbUsers.setItems(allUsers);
        }
    }

    private void loadSelectedUsersTableView(User us) {  
        tableProject.getItems().clear();
        List<Task> allTasksOfSelectedUser = taskModel.getTasksOfSelectedUser(us);
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        tasks.addAll(allTasksOfSelectedUser);
        tableProject.setItems(tasks);
    }

    private void loadAllTaskTableView() {
        tableProject.getItems().clear();
        List<Task> allTasksOverview = taskModel.getAllTasksOverview();
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        tasks.addAll(allTasksOverview);
        tableProject.setItems(tasks);
    }

    private void loadSelectedProjectTableView(Project selectedProject) {
        tableProject.getItems().clear();
        List<Task> allTasksOfSelectedProject = taskModel.getTasksOfSelectedProject(selectedProject);
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        tasks.addAll(allTasksOfSelectedProject);
        tableProject.setItems(tasks);
    }

    private void setUpTaskTableView() {
        colName.setCellValueFactory(new PropertyValueFactory<>("projName"));
        colTask.setCellValueFactory(new PropertyValueFactory<>("task"));
        colUser.setCellValueFactory(new PropertyValueFactory<>("userLastName"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        colHrs.setCellValueFactory(new PropertyValueFactory<>("spentTime"));
        loadAllTaskTableView();
        setSumHrsToLabel();
    }

    private void loadDataForAllProjectsInChart() {
        paneBarChart.getChildren().clear();

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Week Days");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Minutes");

        BarChart weekProject = new BarChart(xAxis, yAxis);
        weekProject.setTitle("This week projects stats");

        XYChart.Series series = new XYChart.Series();
        series.setName("Time Spent Each Day");
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
        System.out.println("1");

        List<LocalDate> datesToIterate = getDifferenceDays(dateFrom.getValue(), dateTo.getValue());
        List<Task> taskList = taskModel.getTasksBetween(dateFrom.getValue(), dateTo.getValue());
        // get tasks
        System.out.println("2");
        for (LocalDate localDate : datesToIterate) {
            for (Task task : taskList) {
                if (formatter.format(localDate).equals(formatter.format(task.getsStartTime()))) {
                    System.out.println("4");
                    series.getData().add(new XYChart.Data<>(localDate.toString(), task.getSpentTime()));
                    break;
                }

            }

        }
        weekProject.getData().add(series);
        paneBarChart.getChildren().add(weekProject);
    }

    private List<LocalDate> getDifferenceDays(LocalDate fromDate, LocalDate toDate) {
        System.out.println("btwn1");
        return Stream.iterate(fromDate, date -> date.plusDays(1))
           .limit(ChronoUnit.DAYS.between(fromDate, toDate))
                .collect(Collectors.toList());

    }

    @FXML
    private void onClickShowBarChart(ActionEvent event) {
        loadDataForAllProjectsInChart();
    }

    private void setSumHrsToLabel()
    {
        int total = tableProject.getItems().stream().collect(Collectors.summingInt(Task::getSpentTime));
        lblSumHrs.setText(String.valueOf(total));
    }
    
    @FXML
    private void formateDate(ActionEvent event) {

    }
}
