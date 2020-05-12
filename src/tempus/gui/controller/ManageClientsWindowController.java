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
import tempus.be.Client;
import tempus.be.User;
import tempus.gui.model.ClientModel;

/**
 * FXML Controller class
 *
 * @author dpank
 */
public class ManageClientsWindowController implements Initializable {

    @FXML
    private TableView<Client> tableViewClients;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colAddress;
    @FXML
    private TableColumn<?, ?> colPhone;
    @FXML
    private TableColumn<?, ?> colEmail;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXButton createButton;
    @FXML
    private JFXButton editButton;
    
    private ClientModel cModel;
    
    public Client selectedClient;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cModel = ClientModel.getInstance();
        setUpTableView();
    }    

    @FXML
    private void handleDelete(ActionEvent event) throws IOException 
    {
        cModel.setSelectedClient(tableViewClients.getSelectionModel().getSelectedItem());
        if(cModel.getSelectedClient() !=null){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/tempus/gui/view/DeleteConfirmationClient.fxml"));
                Parent z = loader.load();
                loader.<DeleteConfirmationClientController>getController().setInfo(this);
                Scene scene = new Scene(z);
                Stage s = new Stage();
                s.setScene(scene);
                s.show();   
        }
    }

    @FXML
    private void handleCreate(ActionEvent event) throws IOException 
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tempus/gui/view/AddClient.fxml"));
        Parent z = loader.load();
        Scene scene = new Scene(z);
        Stage s = new Stage();
        s.setScene(scene);
        s.show();
    }

    @FXML
    private void handleEdit(ActionEvent event) 
    {
        
    }
    
    void loadTableView() {
         tableViewClients.getItems().clear();
         List<Client> allClients = cModel.getAllClients();
         ObservableList<Client> clients = FXCollections.observableArrayList();
         clients.addAll(allClients);
         tableViewClients.setItems(clients);
    }
    
    private void setUpTableView()
    {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("city"));
        
        loadTableView();
    }
    
}
