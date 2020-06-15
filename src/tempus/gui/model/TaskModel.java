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
 * The TaskModel is a model. It gets and passes data about the tasks to the BLL
 *
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Christian Hansen
 * @author Dmitri Pankov
 * @author Nebojsa Gutic
 * @author Tienesh Kanagarasan
 */
public class TaskModel {

    static TaskModel model = new TaskModel();
    private final IBllFacade bllManager;
    List<Task> alltasks = new ArrayList();
    private final ObservableList<Task> taskList = FXCollections.observableArrayList();
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Project selectedProject;
    private User selectedUser;
    private UserModel usModel;
    private Task selectedTask;

    private final ObjectProperty<LocalDateTime> timeStart = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDateTime> timeEnd = new SimpleObjectProperty<>();

    /**
     *
     * @return
     */
    public LocalDateTime getTimeEnd() {
        return timeEnd.get();
    }

    /**
     *
     * @param value
     */
    public void setTimeEnd(LocalDateTime value) {
        timeEnd.set(value);
    }

    /**
     *
     * @return
     */
    public Task getSelectedTask() {
        return selectedTask;
    }

    /**
     *
     * @param selectedTask
     */
    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }

    /**
     *
     */
    public void deleteSelectedTask() {
        bllManager.deleteTask(selectedTask);
    }

    /**
     *
     * @return
     */
    public ObjectProperty timeEndProperty() {
        return timeEnd;
    }

    /**
     *
     * @return
     */
    public LocalDateTime getTimeStart() {
        return timeStart.get();
    }

    /**
     *
     * @param value
     */
    public void setTimeStart(LocalDateTime value) {
        timeStart.set(value);
    }

    /**
     *
     * @return
     */
    public ObjectProperty timeStartProperty() {
        return timeStart;
    }

    /**
     *
     * @return
     */
    public Project getSelectedProject() {
        return selectedProject;
    }

    /**
     *
     * @param selectedProject
     */
    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    /**
     *
     * @return
     */
    public User getSelectedUser() {
        return selectedUser;
    }

    /**
     *
     * @param selectedUser
     */
    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    /**
     *
     * @return
     */
    public LocalDate getDateFrom() {
        return dateFrom;
    }

    /**
     *
     * @param dateFrom
     */
    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     *
     * @return
     */
    public LocalDate getDateTo() {
        return dateTo;
    }

    /**
     *
     * @param dateTo
     */
    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    //private AdminOverviewController adOvController;

    /**
     *
     * @return
     */
    public static TaskModel getInstance() {
        return model;
    }

    /*
    public void injectAdminOverviewController(AdminOverviewController aoc){
        adOvController = aoc;
    }*/

    /**
     *
     */

    public TaskModel() {
        this.bllManager = new BllManager();
        usModel = UserModel.getInstance();
    }

    /**
     *
     * @return
     */
    public List<Task> getAllTasksOverview() {
        alltasks = bllManager.getAllTasksOverview();
        return alltasks;
    }

    /**
     *
     * @return
     */
    public List<Task> getAllTasks() {
        return bllManager.getAllTasksOverview();
    }

    //returns sum of spent hrs for list of tasks

    /**
     *
     * @param lisToFilter
     * @return
     */
    //calculates amount of time from tasks whats in the list
    public List<Task> calculateTotalTime(List<Task> lisToFilter) {
        HashMap<String, String> datesStored = new HashMap<String, String>(); //key is date

        List<Task> filteredList = new ArrayList();

        for (Task task : lisToFilter) {
            Task tsk = new Task(task.getTask(), task.getNote(), task.getsStartTime(), task.getsEndTime(), task.getRSpentTime());
            if (datesStored.get(tsk.getsStartTime().toLocalDate().atStartOfDay().toString()) != null) {

                for (Task taskAlreadyInList : filteredList) {
                    LocalDateTime d1 = taskAlreadyInList.getsStartTime().toLocalDate().atStartOfDay();
                    LocalDateTime d2 = tsk.getsStartTime().toLocalDate().atStartOfDay();
                    //if dates are equal we add hrs 
                    if (d1.equals(d2)) {
                    taskAlreadyInList.setSpentTime(taskAlreadyInList.getRSpentTime() + tsk.getRSpentTime());
                    }
                }
            } else {
                datesStored.put(tsk.getsStartTime().toLocalDate().atStartOfDay().toString(), "None");
                filteredList.add(tsk);
            }
        }
        return filteredList;
    }

    //updates tasks table when changes are done in tableview

    /**
     *
     * @param id
     * @param name
     * @param startTime
     * @param endTime
     * @param note
     * @param spentTime
     */
    public void editTask(int id, String name, LocalDateTime startTime, LocalDateTime endTime, String note, double spentTime) {
        bllManager.editTask(id, name, startTime, endTime, note, spentTime);
    }

    //saves task to db, after stop button is clicked

    /**
     *
     * @param selectedProject
     * @param taskName
     * @param note
     * @param loggedUser
     * @param startTime
     * @param endTime
     */
    public void saveStoppedTask(Project selectedProject, String taskName, String note, User loggedUser, LocalDateTime startTime, LocalDateTime endTime) {
        bllManager.saveStoppedTask(selectedProject, taskName, note, loggedUser, startTime, endTime);
    }

    //refresges logged user task

    /**
     *
     * @return
     */
    public List<Task> refreshUserTasks() {
        getAllTasksOverview();
        return getAllTasksOverviewForLoggedUser();
    }

    //returns all tasks of logged user

    /**
     *
     * @return
     */
    public List<Task> getAllTasksOverviewForLoggedUser() {
        List<Task> allTasksLoggedUser = new ArrayList();
        for (Task alltas : alltasks) {
            if (alltas.getUserLastName().equals(usModel.getloggedInUser().getLName())) {
                allTasksLoggedUser.add(alltas);
            }
        }
        return allTasksLoggedUser;
    }
    
    //filters list by projects

    /**
     *
     * @param listToFilter
     * @param pro
     * @return
     */
    public List<Task> filterByProjects(List<Task> listToFilter, Project pro) {
        List<Task> allspecProjTasks = new ArrayList();
        for (Task alltas : listToFilter) {
            if (alltas.getProjName().equals(pro.getName())) { // if names of the project from combobox equals to name of the project in database it will add proj. to list
                allspecProjTasks.add(alltas);
            }
        }
        return allspecProjTasks;
    }

    //filters list by user

    /**
     *
     * @param listToFilter
     * @param us
     * @return
     */
    public List<Task> filterByUser(List<Task> listToFilter, User us) {
        List<Task> allspecUsTasks = new ArrayList();
        for (Task alltas : listToFilter) {
            if (alltas.getUserLastName().equals(us.getLName())) {// if names of the user  from combobox equals to name of the user  in database it will add user to list
                allspecUsTasks.add(alltas);
            }
        }
        return allspecUsTasks;
    }

    //filters list by dates

    /**
     *
     * @param listToFilter
     * @param from
     * @param to
     * @return
     */
    public List<Task> filterByDates(List<Task> listToFilter, LocalDate from, LocalDate to) {
        List<Task> allspecProjTasks = new ArrayList();
        for (Task alltas : listToFilter) {
            //if dates from datePciker and date in db match we will add project to list
            if (((alltas.getsStartTime().toLocalDate()).isAfter(from) || (alltas.getsStartTime().toLocalDate()).isEqual(from)) && ((alltas.getsStartTime().toLocalDate()).isBefore(to) || (alltas.getsStartTime().toLocalDate()).isEqual(to))) {
                allspecProjTasks.add(alltas);
            }
        }
        return allspecProjTasks;
    }

}
