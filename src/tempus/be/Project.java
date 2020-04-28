/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Tienesh
 */
public class Project {

//    private final StringProperty projectName = new SimpleStringProperty();
//    private final IntegerProperty projectID = new SimpleIntegerProperty();
//    private final IntegerProperty hourlyRate = new SimpleIntegerProperty();
//    private final StringProperty description = new SimpleStringProperty();
//    private final IntegerProperty clientID = new SimpleIntegerProperty();
//
//    public Project(int projectID, String projectName, int clientID, int hourlyRate, String description) {
//        this.projectID.set(projectID);
//        this.projectName.set(projectName);
//        this.clientID.set(clientID);
//        this.hourlyRate.set(hourlyRate);
//        this.description.set(description);
//        
//       // setProjectName(projectName);
//        
//    }

   int projectID;
   String projectName;
   int clientID;
   int hourlyRate;
   String descrip;
   
          

    
    

//    public String getProjectName() {
//        return projectName.get();
//    }
//
//    public void setProjectName(String value) {
//        projectName.set(value);
//    }
//
//    public StringProperty projectNameProperty() {
//        return projectName;
//    }
//
//    public int getProjectID() {
//        return projectID.get();
//    }
//
//    public IntegerProperty projectIDProperty() {
//        return projectID;
//    }
//    
//    public int getHourlyRate() {
//        return hourlyRate.get();
//    }
//
//    public void setHourlyRate(int value) {
//        hourlyRate.set(value);
//    }
//
//    public IntegerProperty hourlyRateProperty() {
//        return hourlyRate;
//    }
//    
//    public String getDescription() {
//        return description.get();
//    }
//
//    public void setDescription(String value) {
//        description.set(value);
//    }
//
//    public StringProperty descriptionProperty() {
//        return description;
//    }
//    
//    public int getClientID() {
//        return clientID.get();
//    }
//
//    public void setClientID(Integer value) {
//        clientID.set(value);
//    }
//
//    public IntegerProperty clientIDProperty() {
//        return clientID;
//    }

    public Project(String projectName, int projectID, int clientID, int hourlyRate, String descrip) {
        this.projectName = projectName;
        this.projectID = projectID;
        
        this.clientID = clientID;
        this.hourlyRate = hourlyRate;
        this.descrip = descrip;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
    
    
    
}
