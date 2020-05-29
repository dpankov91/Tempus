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
    
        // List of all tasks by date range
    public List<Task> getTasksBetweenForTable(LocalDate fromDate, LocalDate toDate) {
        if(dateTo != null && dateFrom !=null)
        {   
        List<Task> TasksBetween = new ArrayList();

        LocalDate ldtFrom = fromDate;
        LocalDate ldtTo = toDate;

        for (Task alltas : alltasks) {
            if (((alltas.getsStartTime().toLocalDate()).isAfter(ldtFrom) || (alltas.getsStartTime().toLocalDate()).isEqual(ldtFrom)) && ((alltas.getsStartTime().toLocalDate()).isBefore(ldtTo) || (alltas.getsStartTime().toLocalDate()).isEqual(ldtTo))) {
                TasksBetween.add(alltas);
            }
        }
        return TasksBetween;
        }else{
            return null;
        }
    }
    
    // sum of all tasks by date range
    public List<Task> getTasksBetweenSumHrs(LocalDate fromDate, LocalDate toDate) {
        if(dateFrom != null && dateTo != null)
        {
        List<Task> TasksBetween = new ArrayList();
        LocalDate ldtFrom = fromDate;
        LocalDate ldtTo = toDate;

        for (Task alltas : alltasks) {
            if (((alltas.getsStartTime().toLocalDate()).isAfter(ldtFrom) || (alltas.getsStartTime().toLocalDate()).isEqual(ldtFrom)) && ((alltas.getsStartTime().toLocalDate()).isBefore(ldtTo) || (alltas.getsStartTime().toLocalDate()).isEqual(ldtTo))) {
                TasksBetween.add(alltas);
            }
        }
        return calculateTotalTime(TasksBetween);
        }else{
            return null;
        }
    }
    
    //////////////////////////
    /////// BY PROJECT////////
    //////////////////////////

    //Task List for selected project.
    public List<Task> getAllTasksOfSelectedProject(Project proj) {
        List<Task> allspecProjTasks = new ArrayList();
        for (Task alltas : alltasks) {
            if (alltas.getProjName().equals(proj.getName())) {
                allspecProjTasks.add(alltas);
            }
        }
        return allspecProjTasks;
    }
    
    //Task List by chosen date range for selected project.
    public List<Task> getAllTasksOfSelectedProjectByDate(Project proj) {
        if(selectedProject != null && dateFrom != null && dateTo != null)
        {
        List<Task> allspecProjTasks = new ArrayList();
        for (Task alltas : getTasksBetweenForTable(dateFrom, dateTo)) {
            if (alltas.getProjName().equals(proj.getName())) {
                allspecProjTasks.add(alltas);
            }
        }
        return allspecProjTasks;
        }
          else{
            return null;
        }
    }
    
    //sum of hrs for selected project by date range
    public List<Task> getTasksBetweenSumHrsProject(LocalDate fromDate, LocalDate toDate) 
    {
        if(selectedProject != null && dateFrom !=null && dateTo != null){
        List<Task> TasksBetweenProject = new ArrayList();
        LocalDate ldtFrom = fromDate;
        LocalDate ldtTo = toDate;

        for (Task alltas : getAllTasksOfSelectedProjectByDate(selectedProject)) {
            if (((alltas.getsStartTime().toLocalDate()).isAfter(ldtFrom) || (alltas.getsStartTime().toLocalDate()).isEqual(ldtFrom)) && ((alltas.getsStartTime().toLocalDate()).isBefore(ldtTo) || (alltas.getsStartTime().toLocalDate()).isEqual(ldtTo))) {
                TasksBetweenProject.add(alltas);
            }
        }
        return calculateTotalTime(TasksBetweenProject);
        }
          else{
            return null;
        }
    }
    
    /////////////////////////
    /////// BY USER//////////
    ////////////////////////

    //Task List for selected user.
    public List<Task> getAllTasksOfSelectedUser(User us) {

        if(selectedUser != null){
        List<Task> allspecUsTasks = new ArrayList();
        for (Task alltas : alltasks) {
            if (alltas.getUserLastName().equals(us.getLName())) {
                allspecUsTasks.add(alltas);
            }
        }
        return allspecUsTasks;
        }
          else{
            return null;
        }
    }

    //Task List by chosen date range for selected user.
    public List<Task> getTasksOfSelectedUserByDate(User us) {
        if(selectedUser != null && dateFrom != null && dateTo != null){
        List<Task> allspecUsTasks = new ArrayList();
        for (Task alltas : getTasksBetweenForTable(dateFrom, dateTo)) {
            if (alltas.getUserLastName().equals(us.getLName())) {
                allspecUsTasks.add(alltas);
            }
        }
        return allspecUsTasks;
        }
          else{
            return null;
        }
    }
    
    //sum of hrs for selected user by date range
    public List<Task> getTasksBetweenSumHrsUser(LocalDate fromDate, LocalDate toDate) {
        if((selectedUser != null && dateFrom !=null && dateTo != null)){
        List<Task> TasksBetweenProject = new ArrayList();
        LocalDate ldtFrom = fromDate;
        LocalDate ldtTo = toDate;

        for (Task alltas : getTasksOfSelectedUserByDate(selectedUser)) {
            if (((alltas.getsStartTime().toLocalDate()).isAfter(ldtFrom) || (alltas.getsStartTime().toLocalDate()).isEqual(ldtFrom)) && ((alltas.getsStartTime().toLocalDate()).isBefore(ldtTo) || (alltas.getsStartTime().toLocalDate()).isEqual(ldtTo))) {
                TasksBetweenProject.add(alltas);
            }
        }
        return calculateTotalTime(TasksBetweenProject);
                }
          else{
            return null;
        }
    }
    
    //////////////////////////
    ///BY USER AND PROJECT///
    ////////////////////////
    
    //Task List for selected project and selected user.
    public List<Task> getAllTasksOfSelectedProjectAndUser(Project selectedProject, User selectedUser) {
        List<Task> allspecProjTasks = new ArrayList();
        for (Task alltas : alltasks) {
            if (alltas.getProjName().equals(selectedProject.getName()) && alltas.getUserLastName().equals(selectedUser.getLName())) {
                allspecProjTasks.add(alltas);
            }
        }
        return allspecProjTasks;
    }
    
    //Task List by chosen date range for selected project and selected user.
    public List<Task> getAllTasksOfSelectedProjectAndUserByDate(Project selectedProject, User selectedUser ) {
        if(selectedProject !=null && selectedUser != null){
        List<Task> allspecProjTasks = new ArrayList();
        for (Task alltas : getTasksBetweenForTable(dateFrom, dateTo)) {
            if (alltas.getProjName().equals(selectedProject.getName()) && alltas.getUserLastName().equals(selectedUser.getLName())) {
                allspecProjTasks.add(alltas);
            }
        }
        return allspecProjTasks;
        }else{
            return null;
        }
    }
    
    //sum of hrs for selected project by date range
    public List<Task> getTasksBetweenSumHrsProjectAndUser(LocalDate fromDate, LocalDate toDate) 
    {
        if(selectedProject !=null && selectedUser != null){
        List<Task> TasksBetweenProject = new ArrayList();
        LocalDate ldtFrom = fromDate;
        LocalDate ldtTo = toDate;

        for (Task alltas : getAllTasksOfSelectedProjectAndUserByDate(selectedProject, selectedUser)) {
            if (((alltas.getsStartTime().toLocalDate()).isAfter(ldtFrom) || (alltas.getsStartTime().toLocalDate()).isEqual(ldtFrom)) && ((alltas.getsStartTime().toLocalDate()).isBefore(ldtTo) || (alltas.getsStartTime().toLocalDate()).isEqual(ldtTo))) {
                TasksBetweenProject.add(alltas);
            }
        }
        return calculateTotalTime(TasksBetweenProject);
        }else{
            return null;
        }
    }
    
    //method returns sum of spent hrs for list of tasks
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

    ///////////////
    //LOGGED USER//
    //////////////
    
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
    
    //method returns task List by chosen date range for logged user.
    public List<Task> getAllTasksOfLoggedUserByDate() {
        
        List<Task> allspecProjTasks = new ArrayList();
        for (Task alltas : getTasksBetweenForTable(dateFrom, dateTo)) {
            if (alltas.getUserLastName().equals(usModel.getloggedInUser().getLName())) {
                allspecProjTasks.add(alltas);
            }
        }
        return allspecProjTasks;
    }
    
    //methods returns sum of hrs for logged user by date
    public List<Task> getTasksBetweenSumHrsLoggedUser(LocalDate fromDate, LocalDate toDate) 
    {
        List<Task> TasksBetweenProject = new ArrayList();
        LocalDate ldtFrom = fromDate;
        LocalDate ldtTo = toDate;

        for (Task alltas : getAllTasksOfLoggedUserByDate()) {
            if (((alltas.getsStartTime().toLocalDate()).isAfter(ldtFrom) || (alltas.getsStartTime().toLocalDate()).isEqual(ldtFrom)) && ((alltas.getsStartTime().toLocalDate()).isBefore(ldtTo) || (alltas.getsStartTime().toLocalDate()).isEqual(ldtTo))) {
                TasksBetweenProject.add(alltas);
            }
        }
        return calculateTotalTime(TasksBetweenProject);
    }
    
    

}
