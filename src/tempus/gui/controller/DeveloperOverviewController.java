/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tempus.be.Project;
import tempus.be.Task;
import tempus.be.User;
import tempus.gui.model.ProjectModel;
import tempus.gui.model.TaskModel;
import tempus.gui.model.UserModel;

/**
 * FXML Controller class
 *
 * @author Tienesh
 */
public class DeveloperOverviewController implements Initializable {

    @FXML
    private TableView<Task> tableTasks;
    @FXML
    private TableColumn<?, ?> colProject;
    @FXML
    private TableColumn<?, ?> colTask;
    @FXML
    private TableColumn<?, ?> colDate;
    @FXML
    private TableColumn<?, ?> colHours;
    @FXML
    private JFXComboBox<?> cmbDateRange;

    /**
     * Initializes the controller class.
     */
    private ProjectModel projModel;
    private UserModel usModel;
    private TaskModel taskModel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        projModel = ProjectModel.getInstance();
        usModel = UserModel.getInstance();
        taskModel = TaskModel.getInstance();
        setUpTaskTableView();
    }    
    
    private void loadSelectedProjectTableView(User loggedUser){
        tableTasks.getItems().clear();
        List<Task> allTasksOfSelectedProject = taskModel.getTasksOfLoggedUser(loggedUser);
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        tasks.addAll(allTasksOfSelectedProject);
        tableTasks.setItems(tasks);
        
    }
    
    private void setUpTaskTableView() {
        User loggedUser = usModel.getloggedInUser();
        colProject.setCellValueFactory(new PropertyValueFactory<>("projName"));
        colTask.setCellValueFactory(new PropertyValueFactory<>("task"));
        colHours.setCellValueFactory(new PropertyValueFactory<>("spentTime"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
        loadSelectedProjectTableView(loggedUser);
    }
    
}
