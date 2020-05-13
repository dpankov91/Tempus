/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
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
    private TableColumn<Project, String> colName;
    @FXML
    private TableColumn<Project, String> colTask;
    @FXML
    private TableColumn<Project, String> colUser;
    @FXML
    private TableColumn<Project, Date> colDate;
    @FXML
    private TableColumn<Project, Integer> colTime;
    @FXML
    private JFXComboBox<Project> cmbProjects;
    @FXML
    private JFXButton btnAllProjects;
    @FXML
    private Pane paneBarChart;
    
    ObservableList<Project>  allProjects = FXCollections.observableArrayList();
    private ProjectModel projModel;
    private UserModel usModel;
    private TaskModel taskModel;
    private ObservableList<Task> thisWeekTasks = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        projModel = ProjectModel.getInstance();
        usModel = UserModel.getInstance();
        taskModel = TaskModel.getInstance();
        loadProjectsToCombobox();
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
        Project pro = cmbProjects.getSelectionModel().getSelectedItem();
        loadSelectedProjectTableView(pro);
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
    
    private void loadTaskTableView() {
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
        loadTaskTableView();
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
        //int MonStats = taskModel.getThisWeekHrs();
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
    

    
    
    
}
