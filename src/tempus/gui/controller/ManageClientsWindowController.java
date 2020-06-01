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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
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
    private TableColumn<Client, String> colName;
    @FXML
    private TableColumn<Client, String> colAddress;
    @FXML
    private TableColumn<Client, Integer> colPhone;
    @FXML
    private TableColumn<Client, String> colEmail;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXButton createButton;

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
    private void handleDelete(ActionEvent event) throws IOException {
        cModel.setSelectedClient(tableViewClients.getSelectionModel().getSelectedItem()); // A client is selected in the tableview
        if (cModel.getSelectedClient() != null) { //if a client is selected, the following happens.
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
    private void handleCreate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tempus/gui/view/AddClient.fxml"));
        Parent z = loader.load();
        loader.<AddClientController>getController().setInfo(this);
        Scene scene = new Scene(z);
        Stage s = new Stage();
        s.setScene(scene);
        s.show();
    }


    void loadTableView() {
        tableViewClients.getItems().clear();
        List<Client> allClients = cModel.getAllClients();
        ObservableList<Client> clients = FXCollections.observableArrayList();
        clients.addAll(allClients);
        tableViewClients.setItems(clients);
    }

    private void setUpTableView() {
        tableViewClients.setEditable(true);
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        colAddress.setCellValueFactory(new PropertyValueFactory<>("city"));

        loadTableView();
    }

    @FXML
    private void writeToDatabase(TableColumn.CellEditEvent<Client, String> event) {
        Client client = event.getRowValue();
        String assignedValue = event.getNewValue();
        if (event.getNewValue().toString().isEmpty()) {
            assignedValue = "None";
        }
        switch (event.getTableColumn().getText()) {
            case "Client Name":
                cModel.editClient(client.getId(), assignedValue, client.getCity(), client.getPhone(), client.getEmail());
                client.setName(assignedValue);
                break; // int id,String projectName, String clientName, String hourlyRate, String description
            case "Address":
                cModel.editClient(client.getId(), client.getName(), assignedValue, client.getPhone(), client.getEmail());
                client.setCity(assignedValue);
                break;
            case "Email":
                cModel.editClient(client.getId(), client.getName(), client.getCity(), client.getPhone(), assignedValue);
                client.setEmail(assignedValue);
                break;
               

        }
    }

    @FXML
    private void writeToDatabaseNumber(TableColumn.CellEditEvent<Client, Integer> event) {
        Client client = event.getRowValue();
        int assignedValue;
        if (event.getNewValue() == null) {
            assignedValue = -1;
        } else {
            assignedValue = event.getNewValue();
        }
        switch (event.getTableColumn().getText()) {
            case "Phone":
                cModel.editClient(client.getId(), client.getName(), client.getCity(), assignedValue, client.getEmail());
                client.setPhone(assignedValue);
                break;
        }
    }
}
