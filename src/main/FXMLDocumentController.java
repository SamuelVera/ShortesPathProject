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
 * FXML Controller class
 * Controller created with netbeans internal tools for fxml docs
 */
public class FXMLDocumentController implements Initializable {

    //Identified fx-id
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
        // TODO
    }    

    //Identified events
    @FXML
    private void goAClick(MouseEvent event) {
        System.out.println("Go A Logic");
    }

    @FXML
    private void goBClick(MouseEvent event) {
        System.out.println("Go B Logic");
    }

    @FXML
    private void goCClick(MouseEvent event) {
        System.out.println("Go C Logic");
    }
    
}
