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
    
}
