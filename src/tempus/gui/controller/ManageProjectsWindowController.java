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
import javafx.stage.Stage;
import tempus.be.Project;
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
    private JFXButton createButton;
    @FXML
    private JFXButton editButton;
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

        projModel.setSelectedProject(tableViewProjects.getSelectionModel().getSelectedItem());
        if (projModel.getSelectedProject() != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tempus/gui/view/DeleteConfirmation.fxml"));
            Parent z = loader.load();
            Scene scene = new Scene(z);
            Stage s = new Stage();
            s.setScene(scene);
            s.show();
        } else {
            setUpAlert("Choose project error", "Choose  project from Table View");
        }
    }

    void setUpTableView() {
        columnProject.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnHourlyRate.setCellValueFactory(new PropertyValueFactory<>("hRate"));
        colClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        loadTableView();
    }

    @FXML
    private void handleCreate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tempus/gui/view/ProjectCreateWindow.fxml"));
        Parent z = loader.load();
        Scene scene = new Scene(z);
        Stage s = new Stage();
        s.setScene(scene);
        s.show();
    }

    @FXML
    private void handleEdit(ActionEvent event) {
    }

    @FXML
    private void handleAssigningTo(ActionEvent event) {
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

}
