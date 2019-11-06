/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * 
 */
public class SceneController {
    
        //Stage object for navigation
    protected Stage stage;
    
    SceneController(Stage stage) throws Exception {
        
        this.stage = stage; //Ref to stage object
        
            //Change scene
        this.changeScene("/UI/HomeScreenDocument.fxml");
            //Show stage post change
        this.stage.show();
        
    }

        //Change scene for navigation
    public void changeScene(String fxml) throws IOException{
            //Load FXML File
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
            //Create Scene
        Scene scene = new Scene( pane );
            //Set the scene
        stage.setScene(scene);
    }
}