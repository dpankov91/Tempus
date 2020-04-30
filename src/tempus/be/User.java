/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.be;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author dpank
 */
public class User 
{

    
    private final ReadOnlyIntegerWrapper idProperty;
    private final StringProperty fNameProperty;
    private final StringProperty lNameProperty;
    private final StringProperty emailProperty;
    private final BooleanProperty isAdminProperty;
    public final BooleanProperty getIsAdminProperty;

    
    public User(int id, String fName, String lName, boolean isAdmin) {
        
        idProperty = new ReadOnlyIntegerWrapper(id);
        fNameProperty = new SimpleStringProperty(fName);
        lNameProperty = new SimpleStringProperty(lName);
        isAdminProperty = new SimpleBooleanProperty(isAdmin);
        
    }

    public User(String fName, String lName, String email) {
        
        fNameProperty = new SimpleStringProperty(fName);
        lNameProperty = new SimpleStringProperty(lName);
        emailProperty = new SimpleStringProperty(email);
        
    }
   


    public StringProperty fNameProperty() {
        return fNameProperty;
    }
    



    public StringProperty lNameProperty() {
        return lNameProperty;
    }
   
    }

    public StringProperty emailProperty() {
        return emailProperty;
    }
    
    public boolean getIsAdmin() {
        return isAdminProperty.get();
    }

    public void setIsAdmin(boolean value) {
        isAdminProperty.set(value);

    }

    public BooleanProperty isAdminProperty() {
        return isAdminProperty;
    }

     public boolean getGetIsAdmin() {
        return getIsAdminProperty.get();
    }

    public void setGetIsAdmin(boolean value) {
        getIsAdminProperty.set(value);
    }

    public BooleanProperty getIsAdminProperty() {
        return getIsAdminProperty;
    }

}
