/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.gui.controller;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import tempus.TempusApp;

/**
 *
 * @author PC
 */
public class FxmlLoader {
private Pane view;

public Pane getPage(String fileName){
    
        try {
            URL fileUrl = TempusApp.class.getResource("/tempus/gui/view/" + fileName + ".fxml");
            if(fileUrl == null){
                throw new java.io.FileNotFoundException("FXML file can't be found");
                
            }
    
            view = new FXMLLoader().load(fileUrl);
            
        }  catch (Exception e){
                    System.out.println("No page " + fileName + " please check FxmlLoader." + e);
                    }
   return view;
    }
}