/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.be;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * The Task class is an entity class. It represents a table in the database,
 * where each entity instance corresponds to a row in the table. The columns of
 * each row is the attribute of the entity.
 *
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Christian Hansen
 * @author Dmitri Pankov
 * @author Nebojsa Gutic
 * @author Tienesh Kanagarasan
 */
public class Task {

    private String task;
    private String note;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double spentTime;

    private String projName;
    private String userLastName;
    private String userFirstName;
    private int id;

    /**
     *Sets the ID of the task
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @param task Name of the task
     * @param note Notes of the task
     * @param startTime StartTime of the task
     * @param endTime EndTime of the task
     * @param spentTime SpentTime of the task
     */
    public Task(String task, String note, LocalDateTime startTime, LocalDateTime endTime, double spentTime) {
        this.task = task;
        this.note = note;
        this.startTime = startTime;
        this.endTime = endTime;
        this.spentTime = spentTime;
    }

    /**
     *
     * @param id Id of the task
     * @param task Name of the task
     * @param note Notes of the task
     * @param startTime EndTime of the task
     * @param endTime EndTime of the task
     * @param spentTime SpentTime of the task
     */
    public Task(int id,String task, String note, LocalDateTime startTime, LocalDateTime endTime, double spentTime) {
        this.id = id;
        this.task = task;
        this.note = note;
        this.startTime = startTime;
        this.endTime = endTime;
        this.spentTime = spentTime;
    }

    /**
     *
     * @param projName Project name
     * @param userLastName User last name
     * @param userFirstName User first name
     * @param task Name of the task
     * @param note Notes of the task
     * @param startTime StartTime of the task
     * @param endTime EndTime of the task
     * @param spentTime SpentTime of the task
     */
    public Task(String projName, String userLastName, String userFirstName, String task, String note, LocalDateTime startTime, LocalDateTime endTime, double spentTime) {
        this.projName = projName;
        this.task = task;
        this.spentTime = spentTime;
        this.userLastName = userLastName;
        this.userFirstName = userFirstName;
        this.note = note;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     *Retrieves the user first name
     * @return
     */
    public String getUserFirstName() {
        return userFirstName;
    }

    /**
     *Sets the user first name
     * @param userFirstName
     */
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    /**
     *Retrieves the project name
     * @return
     */
    public String getProjName() {
        return projName;
    }

    /**
     *Sets the project name
     * @param projName
     */
    public void setProjName(String projName) {
        this.projName = projName;
    }

    /**
     *Retrieves user last name
     * @return
     */
    public String getUserLastName() {
        return userLastName;
    }

    /**
     *Sets the user last name
     * @param userLastName
     */
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    /**
     *Retrieves the task 
     * @return
     */
    public String getTask() {
        return task;
    }

    /**
     *Sets the task
     * @param task
     */
    public void setTask(String task) {
        this.task = task;
    }

    /**
     *Retrieves the note of the task
     * @return
     */
    public String getNote() {
        return note;
    }

    /**
     *Sets the note of the task
     * @param note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     *Retrieves the StartTime of the task
     * @return
     */
    public String getStartTime() {
        return convertTime(startTime);
    }

    private String convertTime(LocalDateTime toConvert) {
        int day = toConvert.getDayOfMonth();
        int month = toConvert.getMonth().getValue();
        int year = toConvert.getYear();
        int second = toConvert.getSecond();
        int minute = toConvert.getMinute();
        int hour = toConvert.getHour();

        String rday;
        String rmonth;
        String rsecond;
        String rminute;
        String rhour;

        if (second > 9) {
            rsecond = ":" + second;
        } else {
            rsecond = ":0" + second;
        }
        if (minute > 9) {
            rminute = ":" + minute;
        } else {
            rminute = ":0" + minute;
        }
        if (hour > 9) {
            rhour = " " + hour;
        } else {
            rhour = " 0" + hour;
        }
        if (second > 9) {
            rsecond = ":" + second;
        } else {
            rsecond = ":0" + second;
        }

        if (month > 9) {
            rmonth = "-" + month;
        } else {
            rmonth = "-0" + month;
        }
        if (day > 9) {
            rday = "-" + day;
        } else {
            rday = "-0" + day;
        }
        return year + rmonth + rday + rhour + rminute + rsecond;

    }

    /**
     *Retrieves the startTime of the task
     * @return
     */
    public LocalDateTime getsStartTime() {
        return startTime;
    }

    /**
     *Sets the startTime of the task
     * @param startTime
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     *Retrieves the endTime of the task
     * @return
     */
    public String getEndTime() {
        return convertTime(endTime);
    }
    
    /**
     *Retrieves the endTime of the task
     * @return
     */
    public LocalDateTime getsEndTime() {
        return endTime;
    }
    
    /**
     *Sets the endTime of the task
     * @param endTime
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     *Retrieves the spentTime of the task
     * @return
     */
    public double getSpentTime() {
        BigDecimal spentTim = new BigDecimal(spentTime / 3600);
        MathContext m = new MathContext(1); // 4 precision 

        // b1 is rounded using m 
        BigDecimal b2 = spentTim.round(m);
        return b2.doubleValue();
    }
    
    public double getRSpentTime() {
        return spentTime;
    }

    /**
     *Sets the spentTime of the task
     * @param spentTime
     */
    public void setSpentTime(double spentTime) {
        
        this.spentTime = spentTime;
    }

    /**
     *Retrieves the ID of the task
     * @return
     */
    public int getId() {
        return id;
    }
   
}
