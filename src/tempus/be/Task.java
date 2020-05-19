/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.be;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author dpank
 */
public class Task {
    
    private String task;
    private String note;
    private Date startTime;
    private Date endTime;
    private int spentTime;
    
    private String projName;
    private String userLastName;
    private String userFirstName;

    public Task(String task, String note, Date startTime, Date endTime, int spentTime) {
        this.task = task;
        this.note = note;
        this.startTime = startTime;
        this.endTime = endTime;
        this.spentTime = spentTime;
    }

    public Task(String projName, String userLastName, String userFirstName, String task, Date startTime, Date endTime, int spentTime)
    {
        this.projName = projName;
        this.task = task;
        this.spentTime = spentTime;
        this.userLastName = userLastName;
        this.userFirstName = userFirstName;
        this.startTime = startTime;
        this.endTime = startTime;;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }
    
    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }
    
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(int spentTime) {
        this.spentTime = spentTime;
    }
    
}
