/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.be;


/**
 * The Client class is an entity class. It represents a table in the database,
 * where each entity instance corresponds to a row in the table. The columns of
 * each row is the attribute of the entity.
 *
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Christian Hansen
 * @author Dmitri Pankov
 * @author Nebojsa Gutic
 * @author Tienesh Kanagarasan
 */
public class Client {
    
    private int id;
    private String name;
    private String city;
    private String email;
    int phone;

    /**
     *Constructs the client
     * 
     * @param id ID of the client
     * @param name Name of the client
     * @param city City of the client
     * @param phone Phone of the client
     * @param email Email of the client
     */
    public Client(int id, String name, String city, int phone, String email) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.phone = phone;
        this.email = email;
    }
 
    /**
     *Retrieves the city of the client
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *Sets the city of the client
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Retrieves the email of the client
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the client
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *Retrieves the ID of the client
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *Sets the ID of the client
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *Retrieves the name of the client
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *Sets the name of the client
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     *Retrieves the phone nr of the client
     * @return
     */
    public int getPhone() {
        return phone;
    }

    /**
     *Sets the phone nr of the client
     * @param phone
     */
    public void setPhone(int phone) {
        this.phone = phone;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return name;
    }
    
    
}
