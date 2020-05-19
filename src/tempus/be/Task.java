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
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int spentTime;

    private String projName;
    private String userLastName;
    private String userFirstName;

    public Task(String task, String note, LocalDateTime startTime, LocalDateTime endTime, int spentTime) {
        this.task = task;
        this.note = note;
        this.startTime = startTime;
        this.endTime = endTime;
        this.spentTime = spentTime;
    }

    public Task(String projName, String userLastName, String userFirstName, String task, LocalDateTime startTime, LocalDateTime endTime, int spentTime) {
        this.projName = projName;
        this.task = task;
        this.spentTime = spentTime;
        this.userLastName = userLastName;
        this.userFirstName = userFirstName;
        this.startTime = startTime;
        this.endTime = startTime;
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

    public LocalDateTime getsStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return convertTime(endTime);
    }
    public LocalDateTime getsEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(int spentTime) {
        this.spentTime = spentTime;
    }

}
