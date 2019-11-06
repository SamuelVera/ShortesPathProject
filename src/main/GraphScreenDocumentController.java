/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * Controller for the graph screen
 */
public class GraphScreenDocumentController implements Initializable{
    /**
     * fx-ids declared in the scene editor 
     */
    @FXML
    private Label label;
    @FXML
    private JFXButton calcJavier;
    @FXML
    private JFXButton calcAndreina;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    /**
     * Event handler for calc first Javier's path
     * @param event 
     */
    @FXML
    private void onCalcJavierClick(MouseEvent event) {
        System.out.println("Javier first");
    }

    /**
     * Event handler for calc first Andreina's path
     * @param event 
     */
    @FXML
    private void onCalcAndreinaClick(MouseEvent event) {
        System.out.println("Andreina first");
    }
    
}
