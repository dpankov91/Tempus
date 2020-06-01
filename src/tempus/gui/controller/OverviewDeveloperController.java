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
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
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
    @FXML
    private JFXButton btnResetDates;

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
        taskModel.getAllTasksOverview();
        taskModel.getAllTasksOverviewForLoggedUser();
    }    
    
    private void loadProjectsToCombobox() {
        allProjects = projModel.getObsProjects();

        for (Project proj : allProjects) {
            cmbProjects.setItems(allProjects);
        }
    }

    @FXML
    private void onSelectLoadSelectedProjectTable(ActionEvent event) {
    }


    @FXML
    private void formateDate(ActionEvent event) {
    }

    @FXML
    private void onClickShowBarChart(ActionEvent event) {
        filterEverything(cmbProjects.getSelectionModel().getSelectedItem(), dateFrom.getValue(), dateTo.getValue());
    }
    
    private void filterEverything(Project pro, LocalDate from, LocalDate to) {
        List<Task> listToFilter = taskModel.getAllTasksOverviewForLoggedUser();
        LocalDate fromaDate = LocalDate.now().minusYears(1);
        LocalDate toaDate = LocalDate.now();
        if (pro != null) {
            //Filter by projects
            listToFilter = taskModel.filterByProjects(listToFilter, pro);
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
    
        private List<LocalDate> getDifferenceDays(LocalDate fromDate, LocalDate toDate) {
            return Stream.iterate(fromDate, date -> date.plusDays(1))
                    .limit(ChronoUnit.DAYS.between(fromDate, toDate))
                    .collect(Collectors.toList());
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
        xAxis.setLabel("Week Days");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Minutes");
        BarChart weekProject = new BarChart(xAxis, yAxis);
        weekProject.setTitle("Statistics");
        XYChart.Series series = new XYChart.Series();
        series.setName("Time Spent Each Day");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");

        for (LocalDate localDate : datesToIterate) {
            for (Task task : filteredList) {
                if (formatter.format(localDate).equals(formatter.format(task.getsStartTime()))) {
                    series.getData().add(new XYChart.Data<>(localDate.toString(), task.getSpentTime()));
                    break;
                }
            }
        }
        weekProject.getData().add(series);
        paneBarChart.getChildren().add(weekProject);
    }

    private void setSumHrsToLabel() {
        double total = tableProject.getItems().stream().collect(Collectors.summingDouble(Task::getSpentTime));
        lblSumHrs.setText(String.valueOf(total));
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
        List<Task> allTasksOverview = taskModel.getAllTasksOverviewForLoggedUser();
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        tasks.addAll(allTasksOverview);
        tableProject.setItems(tasks);
    }

    @FXML
    private void onClickResetDates(ActionEvent event) {
    }
    
}
