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
 * @author dpank
 */
public class Client {
    
    private final ReadOnlyIntegerWrapper idProperty;
    private final StringProperty nameProperty; 
    private final StringProperty cityProperty; 
    private final IntegerProperty phoneProperty; 

    public Client(int id, String name, String city, int phone) {
        
        idProperty = new ReadOnlyIntegerWrapper(id);
        nameProperty = new SimpleStringProperty(name);
        cityProperty = new SimpleStringProperty(city);
        phoneProperty = new SimpleIntegerProperty(phone);
        
    }

    public Integer getId() {
        return idProperty.get();
    }

    public String getName() {
        return nameProperty.get();
    }

    public void setName(String value) {
        nameProperty.set(value);
    }

    public StringProperty nameProperty() {
        return nameProperty;
    }
    
    public String getProjectCity() {
        return cityProperty.get();
    }

    public void setCity(String value) {
        cityProperty.set(value);
    }

    public StringProperty cityProperty() {
        return cityProperty;
    }
    
    public Integer getPhone() {
        return phoneProperty.get();
    }

    public void setPhone (Integer value) {
        phoneProperty.set(value);
    }

    public IntegerProperty phoneProperty() {
        return phoneProperty;
    }
    
}
