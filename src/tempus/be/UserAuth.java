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
        

public class UserAuth{
    
    
    private int id;
    private String rememberKey;

    public UserAuth(int id, String rememberKey) {
        this.id = id;
        this.rememberKey = rememberKey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRememberKey() {
        return rememberKey;
    }

    public void setRememberKey(String rememberKey) {
        this.rememberKey = rememberKey;
    }

    

    
    
  
    
    
    
}
