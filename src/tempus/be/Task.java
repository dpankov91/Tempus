/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.be;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author dpank
 */
public class Task {
    
    private String task;
    private String note;
    private Date createdDate;
    private Time startTime;
    private Time endTime;
    private int spentTime;
    
    private String projName;
    private String userLastName;

    public Task(String task, String note, Date createdDate, Time startTime, Time endTime, int spentTime) {
        this.task = task;
        this.note = note;
        this.createdDate = createdDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.spentTime = spentTime;
    }

    public Task(String projName, String userLastName, String task, Date createdDate, int spentTime)
    {
        this.task = task;
        this.createdDate = createdDate;
        this.spentTime = spentTime;
        this.projName = projName;
        this.userLastName = userLastName;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(int spentTime) {
        this.spentTime = spentTime;
    }
    
}
