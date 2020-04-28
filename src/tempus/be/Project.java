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

    private final StringProperty projectName = new SimpleStringProperty();
    private final IntegerProperty projectID = new SimpleIntegerProperty();
    private final IntegerProperty hourlyRate = new SimpleIntegerProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty clientName = new SimpleStringProperty();

    public Project(int projectID, String projectName, String clientName, int hourlyRate, String description) {
        this.projectID.set(projectID);
        setProjectName(projectName);
        
    }

    public String getProjectName() {
        return projectName.get();
    }

    public void setProjectName(String value) {
        projectName.set(value);
    }

    public StringProperty projectNameProperty() {
        return projectName;
    }

    public int getProjectID() {
        return projectID.get();
    }

    public IntegerProperty projectIDProperty() {
        return projectID;
    }
    
    public int getHourlyRate() {
        return hourlyRate.get();
    }

    public void setHourlyRate(int value) {
        hourlyRate.set(value);
    }

    public IntegerProperty hourlyRateProperty() {
        return hourlyRate;
    }
    
    public String getDescription() {
        return description.get();
    }

    public void setDescription(String value) {
        description.set(value);
    }

    public StringProperty descriptionProperty() {
        return description;
    }
    
    public String getClientName() {
        return clientName.get();
    }

    public void setClientName(String value) {
        clientName.set(value);
    }

    public StringProperty clientNameProperty() {
        return clientName;
    }
    
    
    
}
