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
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class AdminOverviewController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbDateRange;
    @FXML
    private JFXComboBox<String> cmbUserOrProject;
    @FXML
    private StackedBarChart<?, ?> chartBar;
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
    
    ObservableList<Project>  allProjects = FXCollections.observableArrayList();
    private ProjectModel projModel;
    private UserModel usModel;
    private TaskModel taskModel;
    //Project selectedProject = cmbProjects.getSelectionModel().getSelectedItem();
   


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
    }    


    @FXML
    private void onSelectAppearUserOrProjectTable(ActionEvent event) 
    {
        
    }

    @FXML
    private void onSelectLoadSelectedProjectTable(ActionEvent event) 
    {
        
      
        
        
    }
    
    @FXML
    private void onClickLoadAllProjectsTable(ActionEvent event) {
       
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
//        if(selectedProject == null){
         loadTaskTableView();
//        }
//        else{
//            loadSelectedProjectTableView(selectedProject);
//        }
    }
    
    
    
    
    
}
