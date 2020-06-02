/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.be;

import java.util.Date;
import java.util.List;


/**
 * The Project class is an entity class. It represents a table in the database,
 * where each entity instance corresponds to a row in the table. The columns of
 * each row is the attribute of the entity.
 *
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Christian Hansen
 * @author Dmitri Pankov
 * @author Nebojsa Gutic
 * @author Tienesh Kanagarasan
 */
public class Project {

    private int id;
    private String name;
    private int hRate;
    private int clientID;
    private String description;
    private String clientName;
    private String usString;
    List<User> userList;
    
    private String userLastName;
    private String taskName;
    private Date taskDate;
    private int spentTime;
    
    /**
     *
     * @param name
     * @param hRate
     * @param clientName
     * @param description
     * @param userList
     */
    public Project(String name, int hRate, String clientName, String description, List<User> userList) {
        
        this.name = name;
        this.hRate = hRate;
        this.clientName = clientName;
        this.description = description;
        this.userList = userList;
    }

    /**
     *Constructs the project
     * 
     * @param name Name of the project
     * @param userLastName User's last name
     * @param taskName Task's name
     * @param taskDate Task's date
     * @param spentTime Task's spent time
     */
    public Project(String name, String userLastName, String taskName, Date taskDate, int spentTime) 
    {
        this.name = name;
        this.userLastName = userLastName;
        this.taskName = taskName;
        this.taskDate = taskDate;
        this.spentTime = spentTime;
    }

    /**
     *
     * @param i
     * @param all_users
     * @param string
     * @param i0
     */
    public Project(int i, String all_users, String string, int i0) {
        
    }
    
    /**
     *Retrieves the catString
     * @return
     */
    public String getCatString() {
        String finalString = "";
        for (int i = 0; i<userList.size();i++) {
            if(i == 0) finalString = userList.get(i).getFName()+" "+userList.get(i).getLName();
            else finalString = finalString +  " , " +userList.get(i).getFName()+" "+userList.get(i).getLName();
        }
        return finalString;
    }

    /**
     *Retrieves the list of the user
     * @return
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     *Sets the list of the user
     * @param userList
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    /**
     *Retrieves the user's last name
     * @return
     */
    public String getUserLastName() {
        return userLastName;
    }

    /**
     *Sets the user's last name
     * @param userLastName
     */
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    /**
     *Retrieves the name of the task
     * @return
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     *Sets the name of the task
     * @param taskName
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     *Retrieves the date of the task
     * @return
     */
    public Date getTaskDate() {
        return taskDate;
    }

    /**
     *Sets the date of the task
     * @param taskDate
     */
    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    /**
     *Retrieves the spent time of project
     * @return
     */
    public int getSpentTime() {
        return spentTime;
    }

    /**
     *Sets the spent time of the project
     * @param spentTime
     */
    public void setSpentTime(int spentTime) {
        this.spentTime = spentTime;
    }

    /**
     *Retrieves the user string
     * @return
     */
    public String getUsString() {
        return getCatString();
    }

    /**
     *Sets the user string
     * @param usString
     */
    public void setUsString(String usString) {
        this.usString = usString;
    }

    /**
     *
     * @param id Id of the project
     * @param name Name of the project
     * @param hRate Hourly rate of the project
     * @param clientID Id of the client
     * @param description Description of the project
     */
    public Project(int id, String name, int hRate, int clientID, String description) {
        this.id = id;
        this.name = name;
        this.hRate = hRate;
        this.clientID = clientID;
        this.description = description;
    }

    /**
     *Retrieves the name of the client 
     * @return
     */
    public String getClientName() {
        return clientName;
    }

    /**
     *Sets the name of the client
     * @param clientName
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     *Retrieves the ID of the project
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *Sets the ID of the project
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *Retrieves the name of the project
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *Sets name of the project
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name;
    }

    /**
     *Retrieves the hourly rate of the project
     * @return
     */
    public int getHRate() {
        return hRate;
    }

    /**
     *Sets the hourly rate of the project
     * @param hRate
     */
    public void sethRate(int hRate) {
        this.hRate = hRate;
    }

    /**
     *Retrieves the ID of the client
     * @return
     */
    public int getClientID() {
        return clientID;
    }

    /**
     *Sets the ID of the client
     * @param clientID
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    /**
     *Retrieves the description of the project
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *Sets the description of the project
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    
    

}
