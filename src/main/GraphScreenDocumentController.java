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
    
    private void showPaths() throws IOException{
        
        main.sController.changeScene("/UI/MatrixScreenDocument.fxml");
        
    }

    /**
     * Event handler for calc first Javier's path
     * @param event 
     */
    @FXML
    private void onCalcJavierClick(MouseEvent event) throws IOException {
        System.out.println("Javier first");
            //Set Javier going first
        main.bogota.prepareForDijkstra();
        main.bogota.runDijkstraFirstForJavier();
        System.out.println("First Dijkstra done");
        main.bogota.prepareForSecondDijkstraForAndreina();
        this.calcJavier.setVisible(false);
        this.calcAndreina.setVisible(false);
        this.showPaths();
    }

    /**
     * Event handler for calc first Andreina's path
     * @param event 
     */
    @FXML
    private void onCalcAndreinaClick(MouseEvent event) throws IOException {
        System.out.println("Andreina first");
        main.bogota.prepareForDijkstra();
        main.bogota.runDijkstraFirstForAndreina();
        main.bogota.prepareForSecondDijkstraForJavier();
        this.calcJavier.setVisible(false);
        this.calcAndreina.setVisible(false);
        this.showPaths();
    }
    
}
