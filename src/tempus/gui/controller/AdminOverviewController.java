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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
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

    @FXML
    private JFXComboBox<String> cmbDateRange;
    @FXML
    private JFXComboBox<String> cmbUserOrProject;
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
    private TableColumn<?, Date> colDate;
    @FXML
    private TableColumn<?, Integer> colTime;
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

    
    ObservableList<Project>  allProjects = FXCollections.observableArrayList();
    ObservableList<User>  allUsers = FXCollections.observableArrayList();
    private ProjectModel projModel;
    private UserModel usModel;
    private TaskModel taskModel;
    private LocalDate fromDate;
    private LocalDate toDate;
    @FXML
    private JFXButton btnShow;
    
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fromDate = dateFrom.getValue();
        toDate = dateTo.getValue();
        projModel = ProjectModel.getInstance();
        usModel = UserModel.getInstance();
        taskModel = TaskModel.getInstance();
        loadProjectsToCombobox();
        loadUsersToCombobox();
        cmbDateRange.setItems(FXCollections.observableArrayList(
                "This Week", "This Month"));
        cmbUserOrProject.setItems(FXCollections.observableArrayList(
                "Project", "User"));
        setUpTaskTableView();
        loadDataForAllProjectsInChart();
    }    


    @FXML
    private void onSelectAppearUserOrProjectTable(ActionEvent event) 
    {
        
    }

    @FXML
    private void onSelectLoadSelectedProjectTable(ActionEvent event) 
    {
        //cmbUsers.getSelectionModel().clearSelection();
        Project pro = cmbProjects.getSelectionModel().getSelectedItem();
        loadSelectedProjectTableView(pro);
    }
    
     @FXML
    private void onSelectLoadSelectedUserTable(ActionEvent event) {
        //cmbProjects.getSelectionModel().clearSelection();
        User us = cmbUsers.getSelectionModel().getSelectedItem();
        loadSelectedUsersTableView(us);
    }
    
    @FXML
    private void onClickLoadAllProjectsTable(ActionEvent event) {
        setUpTaskTableView();
    }

    @FXML
    private void onSelectSetDateRangeChartBar(ActionEvent event) {
    }
    
    private void loadProjectsToCombobox() 
    { 
        allProjects = projModel.getObsProjects();
        
        for (Project proj : allProjects) {
            cmbProjects.setItems(allProjects);
        }    
    }
    
    private void loadUsersToCombobox() 
    { 
        allUsers = usModel.getObsUsers();
        
        for (User us : allUsers) {
            cmbUsers.setItems(allUsers);
        }    
    }
    
    private void loadSelectedUsersTableView(User us){
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
    
    private void loadSelectedProjectTableView(Project selectedProject){
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
        colDate.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("spentTime"));
        loadAllTaskTableView();
    }

    
    
    private void loadDataForAllProjectsInChart(){
        paneBarChart.getChildren().clear();
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Week Days");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Hours");
        BarChart weekProject = new BarChart(xAxis, yAxis);
        weekProject.setTitle("This week projects stats");
        XYChart.Series series = new XYChart.Series();
        series.setName("Hours Amount");
        series.getData().add(new XYChart.Data<>("Monday", 54));
        series.getData().add(new XYChart.Data<>("Tuesday", 30));
        series.getData().add(new XYChart.Data<>("Wednesday", 50));
        series.getData().add(new XYChart.Data<>("Thursday", 80));
        series.getData().add(new XYChart.Data<>("Friday", 75));
        series.getData().add(new XYChart.Data<>("Saturday", 67));
        series.getData().add(new XYChart.Data<>("Sunday", 34));
        weekProject.getData().add(series);
        paneBarChart.getChildren().add(weekProject);
    }
    
    
    public void getThisWeekHrs() {
        
//        XYChart.Series series = new XYChart.Series();
//        series.setName("Hours Amount");
//        
//        Calendar calendar = Calendar.getInstance();
//        int day = calendar.get(Calendar.DAY_OF_WEEK);
//
//        switch (day) {
//            case Calendar.MONDAY:
//                Method to reset barchart();// reset barchart 
//                series.getData().add(new XYChart.Data<>("Monday", Method to load current monday hrs();      ));//load sum of hrs for Monday in barchart
//                break;
//            case Calendar.TUESDAY:
//                series.getData().add(new XYChart.Data<>("Tuesday", 30));
//                break;
//            case Calendar.WEDNESDAY:
//                series.getData().add(new XYChart.Data<>("Wednesday", 50));
//                break;
//            case Calendar.THURSDAY:
//                series.getData().add(new XYChart.Data<>("Thursday", 80));
//                break;
//            case Calendar.FRIDAY:
//                series.getData().add(new XYChart.Data<>("Friday", 75));
//                break;
//            case Calendar.SATURDAY:
//                series.getData().add(new XYChart.Data<>("Saturday", 67));
//                break;
//            case Calendar.SUNDAY:
//                series.getData().add(new XYChart.Data<>("Sunday", 34));
//                break;
//        }
    }

    @FXML
    private void onClickShowBarChart(ActionEvent event) {
    }
    
    
    
}
