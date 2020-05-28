/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private final ObservableList<Task> taskList = FXCollections.observableArrayList();
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private final ObjectProperty<LocalDateTime> timeStart = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDateTime> timeEnd = new SimpleObjectProperty<>();

    public LocalDateTime getTimeEnd() {
        return timeEnd.get();
    }

    public void setTimeEnd(LocalDateTime value) {
        timeEnd.set(value);
    }

    public ObjectProperty timeEndProperty() {
        return timeEnd;
    }

    public LocalDateTime getTimeStart() {
        return timeStart.get();
    }

    public void setTimeStart(LocalDateTime value) {
        timeStart.set(value);
    }

    public ObjectProperty timeStartProperty() {
        return timeStart;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
    
    //private AdminOverviewController adOvController;

    public static TaskModel getInstance() {
        return model;
    }

    /*
    public void injectAdminOverviewController(AdminOverviewController aoc){
        adOvController = aoc;
    }*/

    public TaskModel() {
        this.bllManager = new BllManager();
    }

    public List<Task> getAllTasksOverview() {
        alltasks = bllManager.getAllTasksOverview();
        return alltasks;
    }

    public List<Task> getAllTasks() {
        return bllManager.getAllTasksOverview();
    }

    public List<Task> getAllTasksOfSelectedProject(Project selectedProject) {
        List<Task> allspecTasks = new ArrayList();
        for (Task alltas : alltasks) {
            if (alltas.getProjName().equals(selectedProject.getName())) {
                allspecTasks.add(alltas);
            }
        }
        return allspecTasks;
    }

    public List<Task> getAllTasksOfSelectedProjectByDate(Project selectedProject) {
        
        List<Task> allspecTasks = new ArrayList();
        //for (Task alltas : getTasksBetweenForTable(adOvController.getDateFrom(), adOvController.getDateTo())) {
        for (Task alltas : getTasksBetweenForTable(dateFrom, dateTo)) {
            if (alltas.getProjName().equals(selectedProject.getName())) {
                allspecTasks.add(alltas);
            }
        }
        return allspecTasks;
    }

    public List<Task> getTasksBetweenForTable(LocalDate fromDate, LocalDate toDate) {
        List<Task> TasksBetween = new ArrayList();

        LocalDate ldtFrom = fromDate;
        LocalDate ldtTo = toDate;

        for (Task alltas : alltasks) {
            if (((alltas.getsStartTime().toLocalDate()).isAfter(ldtFrom) || (alltas.getsStartTime().toLocalDate()).isEqual(ldtFrom)) && ((alltas.getsStartTime().toLocalDate()).isBefore(ldtTo) || (alltas.getsStartTime().toLocalDate()).isEqual(ldtTo))) {
                TasksBetween.add(alltas);
            }
        }
        return TasksBetween;
    }

    public List<Task> getAllTasksOfSelectedUser(User us) {

        List<Task> allspecUsTasks = new ArrayList();
        for (Task alltas : alltasks) {
            if (alltas.getUserLastName().equals(us.getLName())) {
                allspecUsTasks.add(alltas);
            }
        }
        return allspecUsTasks;
    }

    public List<Task> getTasksOfSelectedUserByDate(User us) {

        List<Task> allspecUsTasks = new ArrayList();
        for (Task alltas : getTasksBetweenForTable(dateFrom, dateTo)) {
            if (alltas.getUserLastName().equals(us.getLName())) {
                allspecUsTasks.add(alltas);
            }
        }
        return allspecUsTasks;
    }

    public List<Task> getTasksBetween(LocalDate fromDate, LocalDate toDate) {
        List<Task> TasksBetween = new ArrayList();
        LocalDate ldtFrom = fromDate;
        LocalDate ldtTo = toDate;

        for (Task alltas : alltasks) {
            if (((alltas.getsStartTime().toLocalDate()).isAfter(ldtFrom) || (alltas.getsStartTime().toLocalDate()).isEqual(ldtFrom)) && ((alltas.getsStartTime().toLocalDate()).isBefore(ldtTo) || (alltas.getsStartTime().toLocalDate()).isEqual(ldtTo))) {
                TasksBetween.add(alltas);
            }
        }
        return calculateTotalTime(TasksBetween);
    }

//    public List<Task> getTasksOfLoggedUser(User loggedUser) {
//        List<Task> allspecTasks = new ArrayList();
//        for (Task alltas : alltasks) {
//            if (alltas.getUserLastName().equals(loggedUser.getLName())) {
//                allspecTasks.add(alltas);
//            }
//        }
//        return allspecTasks;
//    }
    private List<Task> calculateTotalTime(List<Task> lisToFilter) {
        HashMap<String, String> datesStored = new HashMap<String, String>(); //key is date

        List<Task> filteredList = new ArrayList();

        for (Task task : lisToFilter) {
            Task tsk = new Task(task.getTask(), task.getNote(), task.getsStartTime(), task.getsEndTime(), task.getSpentTime());
            if (datesStored.get(tsk.getsStartTime().toLocalDate().atStartOfDay().toString()) != null) {

                for (Task taskAlreadyInList : filteredList) {
                    LocalDateTime d1 = taskAlreadyInList.getsStartTime().toLocalDate().atStartOfDay();
                    LocalDateTime d2 = tsk.getsStartTime().toLocalDate().atStartOfDay();
                    if (d1.equals(d2)) {
                        taskAlreadyInList.setSpentTime(taskAlreadyInList.getSpentTime() + tsk.getSpentTime());
                    }
                }
            } else {
                datesStored.put(tsk.getsStartTime().toLocalDate().atStartOfDay().toString(), "None");
                filteredList.add(tsk);
            }
        }
        return filteredList;
    }

    public void editTask(int id, String name, LocalDateTime startTime, LocalDateTime endTime, String note, double spentTime) {
        bllManager.editTask(id, name, startTime, endTime, note, spentTime);
    }

    public void saveStoppedTask(Project selectedProject, String taskName, String note, User loggedUser, LocalDateTime startTime, LocalDateTime endTime) {
        bllManager.saveStoppedTask(selectedProject, taskName, note, loggedUser, startTime, endTime);
    }

}
