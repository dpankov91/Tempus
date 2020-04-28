/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.dal;

import java.util.List;
import tempus.be.Client;
import tempus.be.User;

/**
 *
 * @author dpank
 */
public interface IDalFacade {

    public User getUser(String username, String password);

    public void createProject(String projectName, String clientName, String hourlyRate, String description);

    public void deleteProject(String projectName, String clientName, String hourlyRate, String description);

    public List<Client> getAllClientss();
    
}
