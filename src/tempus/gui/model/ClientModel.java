/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.model;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tempus.be.Client;
import tempus.bll.BllManager;
import tempus.bll.IBllFacade;
import static tempus.gui.model.UserModel.model;

/**
 *
 * @author dpank
 */
public class ClientModel {
    
    static ClientModel cmodel = new ClientModel();
    IBllFacade bllManager;
    private final ObservableList<Client> clientList = FXCollections.observableArrayList();
    private Client selectedClient;
    
    public static ClientModel getInstance() {
        return cmodel;
    }

    public ClientModel() 
    {
        bllManager = new BllManager();
        clientList.addAll(bllManager.getAllClientss());
       
    }
    
    public ObservableList<Client> getObsClients() 
    {
        return clientList;
    }

    public List<Client> getAllClients() {
        return bllManager.getAllClientss();
    }

    public Client getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;
    }

    public void deleteSelectedClient() {
        bllManager.deleteClient(selectedClient);
    }

    public void createClient(String name, String city, int phone, String email) {
         bllManager.createClient(name, city, phone, email);
    }

    public void editClient(int id, String name, String city, int phone, String email) {
    bllManager.editClient(id,name, city, phone, email);
    }
    
    
}
