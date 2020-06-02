/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import tempus.be.Project;
import tempus.be.User;
import tempus.gui.model.ProjectModel;

/**
 * FXML Controller class
 *
 * @author dpank
 */
public class ManageProjectsWindowController implements Initializable {

    @FXML
    private TableView<Project> tableViewProjects;
    @FXML
    private TableColumn<Project, String> columnProject;
    @FXML
    private TableColumn<Project, Integer> columnHourlyRate;
    @FXML
    private TableColumn<Project, String> columnDescription;
    @FXML
    private TableColumn<Project, String> colClientName;
    @FXML
    private TableColumn<User, ?> colAssignedUsers;
    @FXML
    private JFXButton createButton;
    @FXML
    private JFXButton assignToButton;

    public Project selectedProject;
    private ProjectModel projModel;
    @FXML
    private JFXButton deleteButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        projModel = ProjectModel.getInstance();
        setUpTableView();

    }

    @FXML
    private void handleDelete(ActionEvent event) throws IOException {

        projModel.setSelectedProject(tableViewProjects.getSelectionModel().getSelectedItem()); // A project is selected in the tableview
        if (projModel.getSelectedProject() != null) { // if the project is selected
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tempus/gui/view/DeleteConfirmation.fxml")); // Button Pressed and shows the designated FXML file
            Parent z = loader.load();
            loader.<DeleteConfirmationController>getController().setInfo(this);
            Scene scene = new Scene(z);
            Stage s = new Stage();
            s.setScene(scene);
            s.show();
        } else {
            setUpAlert("Choose project error", "Choose  project from Table View"); // Else if the button is pressed while without a project, this message shows itself. 
        }
    }

    void setUpTableView() {
        tableViewProjects.setEditable(true);

        columnProject.setCellFactory(TextFieldTableCell.forTableColumn());
        columnProject.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnHourlyRate.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnHourlyRate.setCellValueFactory(new PropertyValueFactory<>("hRate"));
        colClientName.setCellFactory(TextFieldTableCell.forTableColumn());
        colClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        columnDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colAssignedUsers.setCellValueFactory(new PropertyValueFactory<>("usString"));
        loadTableView();
    }

    @FXML
    private void handleCreate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tempus/gui/view/ProjectCreateWindow.fxml"));
        Parent z = loader.load();
        loader.<ProjectCreateWindowController>getController().setInfo(this);
        Scene scene = new Scene(z);
        Stage s = new Stage();
        s.setScene(scene);
        s.show();
    }

    @FXML
    private void handleAssigningTo(ActionEvent event) throws IOException {
        projModel.setSelectedProject(tableViewProjects.getSelectionModel().getSelectedItem());
        if (projModel.getSelectedProject() != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tempus/gui/view/ProjectAssign.fxml"));
            Parent z = loader.load();
            loader.<ProjectAssignController>getController().setInfo(this);
            Scene scene = new Scene(z);
            Stage s = new Stage();
            s.setScene(scene);
            s.show();
        }
    }

    void loadTableView() {
        tableViewProjects.getItems().clear();
        List<Project> allProjects = projModel.getAllProjects();
        ObservableList<Project> projects = FXCollections.observableArrayList();
        projects.addAll(allProjects);
        tableViewProjects.setItems(projects);
    }

    private void setUpAlert(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    @FXML
    private void writeToDatabase(TableColumn.CellEditEvent<Project, String> event) {
        Project proj = event.getRowValue();
        String assignedValue = event.getNewValue();
        if (event.getNewValue().toString().isEmpty()) {
            assignedValue = "None";
        }
        switch (event.getTableColumn().getText()) {
            case "Project name":
                projModel.editProject(proj.getId(), assignedValue, proj.getClientName(), proj.getHRate(), proj.getDescription());
                proj.setName(assignedValue);
                break; // int id,String projectName, String clientName, String hourlyRate, String description
            case "Description":
                projModel.editProject(proj.getId(), proj.getName(), proj.getClientName(), proj.getHRate(), assignedValue);
                proj.setDescription(assignedValue);
                break;
            case "Role":
                /* userModel.editUser(us.getId(), us.getFName(), event.getNewValue(), us.getEmail(), us.getPhone(), us.getPostcode(), us.getAddress());
                us.setRole(event.getNewValue());*/
                break;

        }
    }

    @FXML
    private void writeToDatabaseNumber(TableColumn.CellEditEvent<Project, Integer> event) {
        Project proj = event.getRowValue();
      int assignedValue;
      if (event.getNewValue() == null) {
         assignedValue = -1;
       } else {
           assignedValue = event.getNewValue();
       }
       switch (event.getTableColumn().getText()) {
           case "Hourly Rate":
               projModel.editProject(proj.getId(), proj.getName(), proj.getClientName(), assignedValue, proj.getDescription());
               proj.sethRate(assignedValue);
              break;
      }
    }
}
