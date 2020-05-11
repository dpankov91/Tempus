/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
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
import tempus.gui.model.ProjectModel;
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
    private TableView<Project> tableProject;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colTask;
    @FXML
    private TableColumn<?, ?> colUser;
    @FXML
    private TableColumn<?, ?> colDate;
    @FXML
    private TableColumn<?, ?> colTime;
    @FXML
    private JFXComboBox<Project> cmbProjectName;
    @FXML
    private JFXButton btnAllProjects;
    
    ObservableList<Project>  allProjects = FXCollections.observableArrayList();
    private ProjectModel projModel;
    private UserModel usModel;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        projModel = ProjectModel.getInstance();
        usModel = UserModel.getInstance();
        loadProjectsToCombobox();
        cmbDateRange.setItems(FXCollections.observableArrayList(
                "This Week", "This Month"));
        cmbUserOrProject.setItems(FXCollections.observableArrayList(
                "Project", "User"));
        setUpTableView();
    }    


    @FXML
    private void onSelectAppearUserOrProjectTable(ActionEvent event) {
    }

    @FXML
    private void onSelectLoadSelectedProjectTable(ActionEvent event) {
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
            cmbProjectName.setItems(allProjects);
        }    
    }
    
    private void loadTableView() {
        tableProject.getItems().clear();
        List<Project> allProjectsOverview = projModel.getAllProjectsOverview();
        ObservableList<Project> projects = FXCollections.observableArrayList();
        projects.addAll(allProjectsOverview);
        tableProject.setItems(projects);
    }
    
    private void setUpTableView() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTask.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        colUser.setCellValueFactory(new PropertyValueFactory<>("userLastName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("taskDate"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("spentTime"));
        loadTableView();
    }
    
    
    
}
