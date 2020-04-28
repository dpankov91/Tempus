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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private TableColumn<?, ?> columnProject;
    @FXML
    private TableColumn<?, ?> columnSpendHours;
    @FXML
    private TableColumn<?, ?> columnAssignedTo;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXButton createButton;
    @FXML
    private JFXButton editButton;
    @FXML
    private JFXButton assignToButton;
    @FXML
    private JFXButton goBackButton;


    Object selectedProject = tableViewProjects.getSelectionModel().getSelectedItem();
    ProjectModel projModel;

    Project selectedProject = tableViewProjects.getSelectionModel().getSelectedItem();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         projModel = ProjectModel.getInstance();
    }    

    @FXML
    private void handleDelete(ActionEvent event) throws IOException {
            if (selectedProject != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tempus/gui/view/DeleteConfirmation.fxml"));
            Parent z = loader.load();
            Scene scene = new Scene(z);
            Stage s = new Stage();
            s.setScene(scene);
            s.show();
        }
    }
    private void setUpTableView()
    {
        columnProject.setCellValueFactory(new PropertyValueFactory<>("Project"));
       
        loadTableView();
    }

    @FXML
    private void handleCreate(ActionEvent event) {
    }

    @FXML
    private void handleEdit(ActionEvent event) {
    }

    @FXML
    private void handleAssigningTo(ActionEvent event) {
    }

    @FXML
    private void handleGoBack(ActionEvent event) {
    }

    private void loadTableView() {
         tableViewProjects.getItems().clear();
        List<Project> allProjects = projModel.getAllProjects();
        ObservableList<Project> projects = FXCollections.observableArrayList();
        projects.addAll(allProjects);
        tableViewProjects.setItems(projects);
        
    }
    
}
