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
 * The FxmlLoader is a class. This loads up a scene whenever the user clicks on
 * the buttons inside the Root Admin and Root Developer scenes
 *
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Christian Hansen
 * @author Dmitri Pankov
 * @author Nebojsa Gutic
 * @author Tienesh Kanagarasan
 */
public class FxmlLoader {
private Pane view;

    /**
     *
     * @param fileName
     * @return
     */
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