/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.be;

import java.util.List;


/**
 *
 * @author Tienesh
 */
public class Project {

    private int id;
    private String name;
    private int hRate;
    private int clientID;
    private String description;
    private String clientName;
    private String usString;
    List<User> userList;

    public Project(String name, int hRate, String clientName, String description, List<User> userList) {
        
        this.name = name;
        this.hRate = hRate;
        this.clientName = clientName;
        this.description = description;
        this.userList = userList;
    }
    
    public String getCatString() {
        String finalString = "";
        for (int i = 0; i<userList.size();i++) {
            if(i == 0) finalString = userList.get(i).getFName()+" "+userList.get(i).getLName();
            else finalString = finalString +  " , " +userList.get(i).getFName()+" "+userList.get(i).getLName();
        }
        return finalString;
    }

    public String getUsString() {
        return getCatString();
    }

    public void setUsString(String usString) {
        this.usString = usString;
    }

    public Project(int id, String name, int hRate, int clientID, String description) {
        this.id = id;
        this.name = name;
        this.hRate = hRate;
        this.clientID = clientID;
        this.description = description;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }


    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name;
    }

    public int getHRate() {
        return hRate;
    }

    public void sethRate(int hRate) {
        this.hRate = hRate;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    

}
