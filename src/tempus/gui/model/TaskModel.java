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
    private Project selectedProject;
    private User selectedUser;
    private UserModel usModel;
    private Task selectedTask;

    private final ObjectProperty<LocalDateTime> timeStart = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDateTime> timeEnd = new SimpleObjectProperty<>();

    public LocalDateTime getTimeEnd() {
        return timeEnd.get();
    }

    public void setTimeEnd(LocalDateTime value) {
        timeEnd.set(value);
    }

    public Task getSelectedTask() {
        return selectedTask;
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }

    public void deleteSelectedTask() {
        bllManager.deleteTask(selectedTask);
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

    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
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
        usModel = UserModel.getInstance();
    }

    public List<Task> getAllTasksOverview() {
        alltasks = bllManager.getAllTasksOverview();
        return alltasks;
    }

    public List<Task> getAllTasks() {
        return bllManager.getAllTasksOverview();
    }

    //method returns sum of spent hrs for list of tasks
    public List<Task> calculateTotalTime(List<Task> lisToFilter) {
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

    public List<Task> refreshUserTasks() {
        getAllTasksOverview();
        return getAllTasksOverviewForLoggedUser();
    }
//returns all tasks of logged user

    public List<Task> getAllTasksOverviewForLoggedUser() {
        List<Task> allTasksLoggedUser = new ArrayList();
        for (Task alltas : alltasks) {
            if (alltas.getUserLastName().equals(usModel.getloggedInUser().getLName())) {
                allTasksLoggedUser.add(alltas);
            }
        }
        return allTasksLoggedUser;
    }

    public List<Task> filterByProjects(List<Task> listToFilter, Project pro) {
        List<Task> allspecProjTasks = new ArrayList();
        for (Task alltas : listToFilter) {
            if (alltas.getProjName().equals(pro.getName())) {
                allspecProjTasks.add(alltas);
            }
        }
        return allspecProjTasks;
    }

    public List<Task> filterByUser(List<Task> listToFilter, User us) {
        List<Task> allspecUsTasks = new ArrayList();
        for (Task alltas : listToFilter) {
            if (alltas.getUserLastName().equals(us.getLName())) {
                allspecUsTasks.add(alltas);
            }
        }
        return allspecUsTasks;
    }

    public List<Task> filterByDates(List<Task> listToFilter, LocalDate from, LocalDate to) {
        List<Task> allspecProjTasks = new ArrayList();
        for (Task alltas : listToFilter) {
            
            if (((alltas.getsStartTime().toLocalDate()).isAfter(from) || (alltas.getsStartTime().toLocalDate()).isEqual(from)) && ((alltas.getsStartTime().toLocalDate()).isBefore(to) || (alltas.getsStartTime().toLocalDate()).isEqual(to))) {
                allspecProjTasks.add(alltas);
            }
        }
        return allspecProjTasks;
    }

}
