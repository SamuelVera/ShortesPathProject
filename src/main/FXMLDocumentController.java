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
 * Made with netbeans internal tools
 */
public class FXMLDocumentController implements Initializable {

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

    @FXML
    private void goAClick(MouseEvent event) {
        System.out.println("I'll go A");
    }

    @FXML
    private void goBClick(MouseEvent event) {
        System.out.println("I'll go B");
    }

    @FXML
    private void goCClick(MouseEvent event) {
        System.out.println("I'll go C");
    }
    
}
