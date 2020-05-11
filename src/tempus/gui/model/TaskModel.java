/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.model;

import tempus.bll.BllManager;
import tempus.bll.IBllFacade;

/**
 *
 * @author dpank
 */
public class TaskModel {
    
    static TaskModel model = new TaskModel();
    private final IBllFacade bllManager;

    public static TaskModel getInstance() {
        return model;
    }

    public TaskModel() {
        this.bllManager = new BllManager();
    }
    
    
    
}
