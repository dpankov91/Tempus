/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.be;

/**
 *
 * @author dpank
 */
public class User 
{
    private int id;
    private String fName;
    private String lName;
    private String email;
    private boolean isAdmin;

    
    public User(int id, String fName, String lName, boolean isAdmin) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.isAdmin = isAdmin;
    }

    public User(String fName, String lName, String email) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
    }
   
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getFName() {
        return fName;
    }
    
     public void setfName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
    
     public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
 
    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }


}
