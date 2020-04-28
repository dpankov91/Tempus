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
public class Client {
    
    int id;
    String name;
    String city;
    int phone;

    public Client(int id, String name, String city, int phone) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.phone = phone;
    }

    public Client(String name) {
        this.name = name;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
    
    
    
}
