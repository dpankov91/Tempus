/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.model;

import java.util.ArrayList;
import java.util.List;
import tempus.be.Project;
import tempus.be.Task;
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
        alltasks=bllManager.getAllTasksOverview();
        return alltasks;
    }


    public List<Task> getTasksOfSelectedProject(Project selectedProject) 
    {
        List<Task> allspecTasks = new ArrayList();
        for (Task alltas : alltasks) {
            if(alltas.getProjName().equals(selectedProject.getName())){
                allspecTasks.add(alltas);
            }
        }
        return allspecTasks;
    }

    

    
}
