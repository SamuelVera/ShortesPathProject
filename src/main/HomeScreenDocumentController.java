/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 * Controller created with netbeans internal tools for fxml docs
 */
public class HomeScreenDocumentController implements Initializable {

    /**
     * fx-ids declared in the scene editor 
     */
    @FXML
    private Label label;
    @FXML
    private JFXButton goA;
    @FXML
    private JFXButton goB;
    @FXML
    private JFXButton goC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    /**
     * Event handler to select going to The Darkness Discotheque.
     * @param event 
     */
    @FXML
    private void goAClick(MouseEvent event) throws IOException{
        System.out.println("Go to Darkness Discotheque");
            //Selecting Darkness Discotheque as ending node for the algorithm
            //Calle 55 - 5 = 50 y Carrera 15 - 1 = 14
        main.bogota.setTerminalNode(5, 1);
        this.changeScreen(); //Change screen from the root
    }

    /**
     * Event handler to select going to La Pasión Bar.
     * @param event 
     */
    @FXML
    private void goBClick(MouseEvent event) throws IOException{
        System.out.println("Go to La Pasión Bar");
            //Selecting La Pasión Bar as ending node for the algorithm
            //Calle 55 - 1 = 54 y Carrera 15 - 4 = 11
        main.bogota.setTerminalNode(1, 4);
        this.changeScreen(); //Change screen from the root
    }

    /**
     * Event handler to select going to Mi Rolita Brewery.
     * @param event 
     */
    @FXML
    private void goCClick(MouseEvent event) throws IOException{
        System.out.println("Go to Mi Rolita Brewery");
            //Selecting Mi Rolita Brewery as ending node for the algorithm
            //Calle 55 - 5 = 50 y Carrera 15 - 3 = 12
        main.bogota.setTerminalNode(5, 3);
        this.changeScreen(); //Change screen from the root
    }
    
    /**
     * Modular screen change
     */
    private void changeScreen() throws IOException{
            //Use singleton to change screen
        main.sController.changeScene("/UI/GraphScreenDocument.fxml");
    }
    
}
