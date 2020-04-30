/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Tienesh
 */
public class Project {

    private final StringProperty projectNameProperty;
    private final ReadOnlyIntegerWrapper projectIDProperty;
    private final IntegerProperty hourlyRateProperty;
    private final StringProperty descriptionProperty;
    private final StringProperty clientNameProperty;

    public Project(String projectName, int projectID,  String clientName, int hourlyRate, String description) {
        
        projectNameProperty = new SimpleStringProperty(projectName);
        projectIDProperty = new ReadOnlyIntegerWrapper(projectID);
        hourlyRateProperty = new SimpleIntegerProperty(hourlyRate);
        descriptionProperty = new SimpleStringProperty(description);
        clientNameProperty = new SimpleStringProperty(clientName);
        
    }
    
    

    public String getProjectName() {
        return projectNameProperty.get();
    }

    public void setProjectName(String value) {
        projectNameProperty.set(value);
    }

    public StringProperty projectNameProperty() {
        return projectNameProperty;
    }

    public int getProjectID() {
        return projectIDProperty.get();
    }
    
    public int getHourlyRate() {
        return hourlyRateProperty.get();
    }

    public void setHourlyRate(int value) {
        hourlyRateProperty.set(value);
    }

    public IntegerProperty hourlyRateProperty() {
        return hourlyRateProperty;
    }
    
    public String getDescription() {
        return descriptionProperty.get();
    }

    public void setDescription(String value) {
        descriptionProperty.set(value);
    }

    public StringProperty descriptionProperty() {
        return descriptionProperty;
    }
    
    public String getClientName() {
        return clientNameProperty.get();
    }
    
    public void setClientName(String value) {
        clientNameProperty.set(value);
    }

    public StringProperty clientNameProperty() {
        return clientNameProperty;
    }
    
    
    
}
