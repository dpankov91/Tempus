/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is the main class of the application. It sets and opens the
 * application.
 *
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Christian Hansen
 * @author Dmitri Pankov
 * @author Nebojsa Gutic
 * @author Tienesh Kanagarasan
 */
public class TempusApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("gui/view/Login.fxml"));

        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
