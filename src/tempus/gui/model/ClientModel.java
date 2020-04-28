/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tempus.be.Client;
import tempus.bll.BllManager;
import tempus.bll.IBllFacade;

/**
 *
 * @author dpank
 */
public class ClientModel {
    
    IBllFacade bllManager;
    private final ObservableList<Client> clientList = FXCollections.observableArrayList();

    public ClientModel() 
    {
        bllManager = new BllManager();
        clientList.addAll(bllManager.getAllClientss());
    }
    
    public ObservableList<Client> getObsClients() 
    {
        return clientList;
    }
    
}
