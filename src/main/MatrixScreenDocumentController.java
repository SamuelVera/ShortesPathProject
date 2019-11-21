/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Objects.Node;
import java.io.IOException;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author samue
 */
public class MatrixScreenDocumentController implements Initializable {

    @FXML
    private Line h6;
    @FXML
    private Line v2;
    @FXML
    private Line v8;
    @FXML
    private Line v14;
    @FXML
    private Line v20;
    @FXML
    private Line v26;
    @FXML
    private Line v3;
    @FXML
    private Line v9;
    @FXML
    private Line v15;
    @FXML
    private Line v21;
    @FXML
    private Line v27;
    @FXML
    private Line v4;
    @FXML
    private Line v10;
    @FXML
    private Line v16;
    @FXML
    private Line v22;
    @FXML
    private Line v28;
    @FXML
    private Line v5;
    @FXML
    private Line v11;
    @FXML
    private Line v17;
    @FXML
    private Line v23;
    @FXML
    private Line v29;
    @FXML
    private Line v6;
    @FXML
    private Line v12;
    @FXML
    private Line v18;
    @FXML
    private Line v24;
    @FXML
    private Line v30;
    @FXML
    private Line h7;
    @FXML
    private Line h8;
    @FXML
    private Line h9;
    @FXML
    private Line h10;
    @FXML
    private Line v1;
    @FXML
    private Line v7;
    @FXML
    private Line v13;
    @FXML
    private Line v19;
    @FXML
    private Line v25;
    @FXML
    private Line h11;
    @FXML
    private Line h12;
    @FXML
    private Line h13;
    @FXML
    private Line h14;
    @FXML
    private Line h15;
    @FXML
    private Line h16;
    @FXML
    private Line h17;
    @FXML
    private Line h18;
    @FXML
    private Line h19;
    @FXML
    private Line h20;
    @FXML
    private Line h21;
    @FXML
    private Line h22;
    @FXML
    private Line h23;
    @FXML
    private Line h24;
    @FXML
    private Line h25;
    @FXML
    private Line h26;
    @FXML
    private Line h27;
    @FXML
    private Line h28;
    @FXML
    private Line h29;
    @FXML
    private Line h30;
    @FXML
    private Line h1;
    @FXML
    private Line h2;
    @FXML
    private Line h3;
    @FXML
    private Line h4;
    @FXML
    private Line h5;
    @FXML
    private Button goBack;
    @FXML
    private Text javierTime;
    @FXML
    private Text andreinaTime;
    @FXML
    private Text timeDifference;

        //Carreras array (vertical lines)
    private Line[][] carreras = new Line[5][6];
        //Calles array (horizontal lines)
    private Line[][] calles = new Line[6][5];
    
    private void paintPaths(){
            //Stack with paths
        Stack<Node> andreinaPath = main.bogota.getAndreinaRealPath();
        Stack<Node> javierPath = main.bogota.getJavierRealPath();
        
        double javierTime;
        double andreinaTime;
        
            //Auxiliar nodes and ints to check movement
        Node n1 = andreinaPath.pop();
        Node n2;
        int carrerasDif;
        int callesDif;
        
        while(!andreinaPath.isEmpty()){
            n2 = andreinaPath.pop();
            carrerasDif = n1.getCarrera() - n2.getCarrera();
            callesDif = n1.getCalle() - n2.getCalle();
            
            System.out.println( n1.getCarrera());
            System.out.println( n2.getCarrera());
            System.out.println( n1.getCalle());
            System.out.println( n2.getCalle());
            
            if(carrerasDif == 0){
                
                System.out.println("Movimiento vertical");
                
                this.carreras[55 - max ( n1.getCalle(), n2.getCalle() )]
                        [15 - n1.getCarrera()]
                        .setStrokeWidth(2.0);
                this.carreras[55 - max ( n1.getCalle(), n2.getCalle() )]
                        [15 - n1.getCarrera()]
                        .setStroke(Color.RED);
                        
                
            }else if(callesDif == 0){
                
                System.out.println("Movimiento horizontal");
                
                this.calles[55 - n1.getCalle() ]
                        [15 - max( n1.getCarrera(), n2.getCarrera() )]
                        .setStrokeWidth(2.0);
                this.calles[55 - n1.getCalle() ]
                        [15 - max( n1.getCarrera(), n2.getCarrera() )]
                        .setStroke(Color.RED);
            
            }
            
            n1 = n2;
        }
        
        andreinaTime = n1.getDx2();
        
        n1 = javierPath.pop();
        
        while(!javierPath.isEmpty()){
            n2 = javierPath.pop();
            carrerasDif = n1.getCarrera() - n2.getCarrera();
            callesDif = n1.getCalle() - n2.getCalle();
            
            System.out.println( n1.getCarrera());
            System.out.println( n2.getCarrera());
            System.out.println( n1.getCalle());
            System.out.println( n2.getCalle());
            
            if(carrerasDif == 0){
                
                System.out.println("Movimiento vertical");
                
                this.carreras[55 - max ( n1.getCalle(), n2.getCalle() )]
                        [15 - n1.getCarrera()]
                        .setStrokeWidth(2.0);
                this.carreras[55 - max ( n1.getCalle(), n2.getCalle() )]
                        [15 - n1.getCarrera()]
                        .setStroke(Color.BLUE);
                        
                
            }else if(callesDif == 0){
                
                System.out.println("Movimiento horizontal");
                
                this.calles[55 - n1.getCalle() ]
                        [15 - max( n1.getCarrera(), n2.getCarrera() )]
                        .setStrokeWidth(2.0);
                this.calles[55 - n1.getCalle() ]
                        [15 - max( n1.getCarrera(), n2.getCarrera() )]
                        .setStroke(Color.BLUE);
            
            }
            
            n1 = n2;
        }
        
        javierTime = n1.getDx1();
        
        this.andreinaTime.setText("Andreina tarda "+ andreinaTime +" minutos en llegar");
        this.javierTime.setText("Javier tarde "+javierTime+" minutos en llegar");
        
        if( javierTime > andreinaTime){
            this.timeDifference.setText("Javier debe salir "+( javierTime - andreinaTime )+" minutos antes de Andreina");
        }else{
            this.timeDifference.setText("Andreina debe salir "+( andreinaTime - javierTime )+" minutos antes de Javier");
        }
        
    }
    
    private int max(int a, int b){
        if(a > b){
            return a;
        }else{
            return b;
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            //Dirty fill carreras matrix
        this.carreras[0][0] = this.v1;
        this.carreras[0][1] = this.v2;
        this.carreras[0][2] = this.v3;
        this.carreras[0][3] = this.v4;
        this.carreras[0][4] = this.v5;
        this.carreras[0][5] = this.v6;
        this.carreras[1][0] = this.v7;
        this.carreras[1][1] = this.v8;
        this.carreras[1][2] = this.v9;
        this.carreras[1][3] = this.v10;
        this.carreras[1][4] = this.v11;
        this.carreras[1][5] = this.v12;
        this.carreras[2][0] = this.v13;
        this.carreras[2][1] = this.v14;
        this.carreras[2][2] = this.v15;
        this.carreras[2][3] = this.v16;
        this.carreras[2][4] = this.v17;
        this.carreras[2][5] = this.v18;
        this.carreras[3][0] = this.v19;
        this.carreras[3][1] = this.v20;
        this.carreras[3][2] = this.v21;
        this.carreras[3][3] = this.v22;
        this.carreras[3][4] = this.v23;
        this.carreras[3][5] = this.v24;
        this.carreras[4][0] = this.v25;
        this.carreras[4][1] = this.v26;
        this.carreras[4][2] = this.v27;
        this.carreras[4][3] = this.v28;
        this.carreras[4][4] = this.v29;
        this.carreras[4][5] = this.v30;
        
            //Dirty fill calles matrix
        this.calles[0][0] = this.h1;
        this.calles[0][1] = this.h2;
        this.calles[0][2] = this.h3;
        this.calles[0][3] = this.h4;
        this.calles[0][4] = this.h5;
        this.calles[1][0] = this.h6;
        this.calles[1][1] = this.h7;
        this.calles[1][2] = this.h8;
        this.calles[1][3] = this.h9;
        this.calles[1][4] = this.h10;
        this.calles[2][0] = this.h11;
        this.calles[2][1] = this.h12;
        this.calles[2][2] = this.h13;
        this.calles[2][3] = this.h14;
        this.calles[2][4] = this.h15;
        this.calles[3][0] = this.h16;
        this.calles[3][1] = this.h17;
        this.calles[3][2] = this.h18;
        this.calles[3][3] = this.h19;
        this.calles[3][4] = this.h20;
        this.calles[4][0] = this.h21;
        this.calles[4][1] = this.h22;
        this.calles[4][2] = this.h23;
        this.calles[4][3] = this.h24;
        this.calles[4][4] = this.h25;
        this.calles[5][0] = this.h26;
        this.calles[5][1] = this.h27;
        this.calles[5][2] = this.h28;
        this.calles[5][3] = this.h29;
        this.calles[5][4] = this.h30;
        
        this.paintPaths();
    }

    @FXML
    private void onGoBackClick(MouseEvent event) throws IOException {
        main.sController.changeScene("/UI/HomeScreenDocument.fxml");
    }
    
}
