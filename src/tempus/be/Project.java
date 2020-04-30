/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.be;


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

    public Project(int id, String name, int hRate, int clientID, String description) {
        this.id = id;
        this.name = name;
        this.hRate = hRate;
        this.clientID = clientID;
        this.description = description;
    }

    public Project(String name, int id, int hRate, int clientID, String description) {
        this.name = name;
        this.id = id;
        this.hRate = hRate;
        this.clientID=clientID;
        this.description=description;
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
