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
 * The ClientModel is a model. It gets and passes data about the clients to the BLL
 *
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Christian Hansen
 * @author Dmitri Pankov
 * @author Nebojsa Gutic
 * @author Tienesh Kanagarasan
 */
public class ClientModel {
    
    static ClientModel cmodel = new ClientModel();
    IBllFacade bllManager;
    private final ObservableList<Client> clientList = FXCollections.observableArrayList();
    private Client selectedClient;
    
    /**
     *
     * @return
     */
    public static ClientModel getInstance() {
        return cmodel;
    }

    /**
     *
     */
    public ClientModel() 
    {
        bllManager = new BllManager();
        clientList.addAll(bllManager.getAllClientss());
       
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Client> getObsClients() 
    {
        return clientList;
    }

    /**
     *
     * @return
     */
    public List<Client> getAllClients() {
        return bllManager.getAllClientss();
    }

    /**
     *
     * @return
     */
    public Client getSelectedClient() {
        return selectedClient;
    }

    /**
     *
     * @param selectedClient
     */
    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;
    }

    /**
     *
     */
    public void deleteSelectedClient() {
        bllManager.deleteClient(selectedClient); // Brings the intended client for delettion down a layer, into the BLL.
    }

    /**
     *
     * @param name
     * @param city
     * @param phone
     * @param email
     */
    public void createClient(String name, String city, int phone, String email) {
         bllManager.createClient(name, city, phone, email);
    }

    /**
     *
     * @param id
     * @param name
     * @param city
     * @param phone
     * @param email
     */
    public void editClient(int id, String name, String city, int phone, String email) {
    bllManager.editClient(id,name, city, phone, email);
    }
    
    
}
