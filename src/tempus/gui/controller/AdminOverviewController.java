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
import javafx.application.Platform;
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
 *The AdminOverview is a class. It allows an overview of the Admin user
 * which shows all projects/tasks created
 * 
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Christian Hansen
 * @author Dmitri Pankov
 * @author Nebojsa Gutic
 * @author Tienesh Kanagarasan
 */
public class AdminOverviewController implements Initializable {

    private StackedBarChart<String, Integer> chartBar;
    @FXML
    private TableView<Task> tableProject;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colTask;
    @FXML
    private TableColumn<?, ?> colUser;
    @FXML
    private TableColumn<?, ?> colStartTime;
    @FXML
    private TableColumn<?, ?> colEndTime;
    @FXML
    private TableColumn<Task, Integer> colHrs;
    @FXML
    private JFXComboBox<Project> cmbProjects;
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
    private Project selectedProject;

    @FXML
    private Label lblSumHrs;
    @FXML
    private JFXButton btnResetDates;
    @FXML
    private JFXButton btnResetProjects;
    @FXML
    private JFXButton btnResetUser;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        projModel = ProjectModel.getInstance();
        usModel = UserModel.getInstance();
        taskModel = TaskModel.getInstance();
        //taskModel.injectAdminOverviewController(this);
        
        loadProjectsToCombobox();
        loadUsersToCombobox();
        setUpTaskTableView();
//        dateFrom.valueProperty().addListener((obs, old, newest) -> {
//            taskModel.setDateFrom(newest);
//        });
//        dateTo.valueProperty().addListener((obs, old, newest) -> {
//            taskModel.setDateTo(newest);
//        });
//        cmbProjects.getSelectionModel().selectedItemProperty().addListener((obs, old, newest) -> {
//            taskModel.setSelectedProject(newest);
//        });
//        cmbUsers.getSelectionModel().selectedItemProperty().addListener((obs, old, newest) -> {
//            taskModel.setSelectedUser(newest);
//        });
        btnResetProjects.setVisible(false);
        btnResetDates.setVisible(false);
        btnResetUser.setVisible(false);
    }

    @FXML
    private void onSelectLoadSelectedProjectTable(ActionEvent event) {
        btnResetProjects.setVisible(true);
    }

    @FXML
    private void onSelectLoadSelectedUserTable(ActionEvent event) {
        btnResetUser.setVisible(true);
    }

    private void loadUsersToCombobox() {
        allUsers = usModel.getObsUsers();

        for (User us : allUsers) {
            cmbUsers.setItems(allUsers);
        }
    }

    private void loadProjectsToCombobox() {
        allProjects = projModel.getObsProjects();

//        cmbProjects.getItems().add(new Project(-1, "All users", "", -1));
//        cmbProjects.setItems(allProjects);
        for (Project proj : allProjects) {
            
            cmbProjects.setItems(allProjects);
        }
    }

    private void loadAllTaskTableView() {
        tableProject.getItems().clear();
        List<Task> allTasksOverview = taskModel.getAllTasksOverview(); // create list where we put all tasks from databaes Table Task.
        ObservableList<Task> tasks = FXCollections.observableArrayList(); //create new obs.list
        tasks.addAll(allTasksOverview); // add to obs.list all tasks from list
        tableProject.setItems(tasks); // set items to tableview
    }

    private void setUpTaskTableView() {
        //what data we want to put in every column ( excactly same name like in BE class)
        colName.setCellValueFactory(new PropertyValueFactory<>("projName")); 
        colTask.setCellValueFactory(new PropertyValueFactory<>("task"));
        colUser.setCellValueFactory(new PropertyValueFactory<>("userLastName"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        colHrs.setCellValueFactory(new PropertyValueFactory<>("spentTime"));
        loadAllTaskTableView();
        setSumHrsToLabel();
    }

    //get number of days between picked in datePickers
    private List<LocalDate> getDifferenceDays(LocalDate fromDate, LocalDate toDate) {
            return Stream.iterate(fromDate, date -> date.plusDays(1))
                    .limit(ChronoUnit.DAYS.between(fromDate, toDate.plusDays(1)))
                    .collect(Collectors.toList());
    }

    @FXML
    private void onClickShowBarChart(ActionEvent event) 
    {
        filterEverything(cmbProjects.getSelectionModel().getSelectedItem(), cmbUsers.getSelectionModel().getSelectedItem(), dateFrom.getValue(), dateTo.getValue());
    }

    
    private void filterEverything(Project pro, User us, LocalDate from, LocalDate to) {
      
        List<Task> listToFilter = taskModel.getAllTasksOverview();
       
        LocalDate fromaDate = LocalDate.now().minusYears(1);
        LocalDate toaDate = LocalDate.now();
        if (pro != null) {
            //Filter by projects
            listToFilter = taskModel.filterByProjects(listToFilter, pro);
        }
        if (us != null) {
            //Filter by user
            listToFilter = taskModel.filterByUser(listToFilter, us);
        }
        if (from != null && to != null) {
            //filter by date
            toaDate = to;
            fromaDate = from;
            listToFilter = taskModel.filterByDates(listToFilter, from, to);
        }
        setUpTable(listToFilter);
        listToFilter = taskModel.calculateTotalTime(listToFilter);
        setUpChart(listToFilter, getDifferenceDays(fromaDate, toaDate));
        //Set up chart using filtered data
    }

    private void setUpTable(List<Task> filteredList) {
        tableProject.getItems().clear();
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        tasks.addAll(filteredList);
        tableProject.setItems(tasks);
        setSumHrsToLabel();
    }

    private void setUpChart(List<Task> filteredList, List<LocalDate> datesToIterate) {
        paneBarChart.getChildren().clear();

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Days");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Hours");

        BarChart weekProject = new BarChart(xAxis, yAxis);
        weekProject.setTitle("Statistics");

        //bars
        XYChart.Series series = new XYChart.Series();
        series.setName("Time Spent Each Day");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");

        //cheking dates what need to be added
        for (LocalDate localDate : datesToIterate) {
            // cheking tasks from filteredList
            for (Task task : filteredList) {
                //in case they have same date we will add bar
                if (formatter.format(localDate).equals(formatter.format(task.getsStartTime()))) {
                    series.getData().add(new XYChart.Data<>(localDate.toString(), task.getSpentTime()));
                    break;  
                }
            }
        }
        //add barChart in Pane
        weekProject.getData().add(series);
        paneBarChart.getChildren().add(weekProject);
    }

    // add sum of hrs from Hours column
    private void setSumHrsToLabel() {
        double total = tableProject.getItems().stream().collect(Collectors.summingDouble(Task::getSpentTime));
        lblSumHrs.setText(String.valueOf(total));
    }


    @FXML
    private void onActionClearDatePicker(ActionEvent event) {
        dateFrom.setValue(null);
        dateTo.setValue(null);
        btnResetDates.setVisible(false);
    }

    @FXML
    private void onClickResetProjects(ActionEvent event) {
        cmbProjects.getSelectionModel().clearSelection();
        btnResetProjects.setVisible(false);
    }

    @FXML
    private void onClickResetUser(ActionEvent event) {
        cmbUsers.getSelectionModel().clearSelection();
        btnResetUser.setVisible(false);
    }

    @FXML
    private void onClickChooseDate(ActionEvent event) {
        btnResetDates.setVisible(true);
    }
    
   

}
