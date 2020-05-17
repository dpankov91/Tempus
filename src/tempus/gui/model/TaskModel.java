/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tempus.be.Project;
import tempus.be.Task;
import tempus.be.User;
import tempus.bll.BllManager;
import tempus.bll.IBllFacade;

/**
 *
 * @author dpank
 */
public class TaskModel {

    static TaskModel model = new TaskModel();
    private final IBllFacade bllManager;
    List<Task> alltasks = new ArrayList();

    public static TaskModel getInstance() {
        return model;
    }

    public TaskModel() {
        this.bllManager = new BllManager();
    }

    public List<Task> getAllTasksOverview() {
        alltasks = bllManager.getAllTasksOverview();
        return alltasks;
    }

    public List<Task> getTasksOfSelectedProject(Project selectedProject) {
        List<Task> allspecTasks = new ArrayList();
        for (Task alltas : alltasks) {
            if (alltas.getProjName().equals(selectedProject.getName())) {
                allspecTasks.add(alltas);
            }
        }
        return allspecTasks;
    }

    public List<Task> getTasksOfSelectedUser(User us) {

        List<Task> allspecUsTasks = new ArrayList();
        for (Task alltas : alltasks) {
            if (alltas.getUserLastName().equals(us.getLName())) {
                allspecUsTasks.add(alltas);
            }
        }
        return allspecUsTasks;
    }

    public List<Task> getTasksBetween(LocalDate fromDate, LocalDate toDate) {
        List<Task> TasksBetween = new ArrayList();
        Date dateFrom = java.sql.Date.valueOf(fromDate);
        Date dateTo = java.sql.Date.valueOf(toDate);

        for (Task alltas : alltasks) {
            if ((alltas.getCreatedDate().after(dateFrom) || alltas.getCreatedDate().equals(dateFrom)) && (alltas.getCreatedDate().before(dateTo) || alltas.getCreatedDate().equals(dateTo))) {
                TasksBetween.add(alltas);
            }
        }
        return calculateTotalTime(TasksBetween);
    }

    public List<Task> getTasksOfLoggedUser(User loggedUser) {
        List<Task> allspecTasks = new ArrayList();
        for (Task alltas : alltasks) {
            if (alltas.getUserLastName().equals(loggedUser.getLName())) {
                allspecTasks.add(alltas);
            }
        }
        return allspecTasks;
    }

    private List<Task> calculateTotalTime(List<Task> lisToFilter) {
        HashMap<String, String> datesStored = new HashMap<String, String>(); //key is date
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
        
        List<Task> filteredList = new ArrayList();
        
        for (Task task : lisToFilter) {
            Task tsk = new Task(task.getTask(), task.getNote(), task.getCreatedDate(), task.getStartTime(), task.getEndTime(), task.getSpentTime());
            if (datesStored.get(tsk.getCreatedDate().toString()) != null) {
        
                for (Task taskAlreadyInList : filteredList) {
                            LocalDate ld = new java.sql.Date( taskAlreadyInList.getCreatedDate().getTime() ).toLocalDate();
                            LocalDate ld2 = new java.sql.Date( tsk.getCreatedDate().getTime() ).toLocalDate();
                    if (formatter.format(ld).equals(formatter.format(ld2))) {
                        taskAlreadyInList.setSpentTime(taskAlreadyInList.getSpentTime() + tsk.getSpentTime());
                    }
                }
            } else {
                datesStored.put(tsk.getCreatedDate().toString(), "None");
                filteredList.add(tsk);
            }
        }
        return filteredList;
    }

}
