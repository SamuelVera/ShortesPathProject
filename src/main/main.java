package main;

//Data models
import Objects.Graph;
//FX
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Main class
 */
public class main extends Application {

        //Scene controller Singleton
    protected static SceneController sController;
        //Graph object singleton
    protected static Graph bogota;
    
        //Start JAVA FX App method
    @Override
    public void start(Stage stage) throws Exception {
            //Hide native menubar
        stage.initStyle(StageStyle.UNDECORATED);
            //Scene Controller construction
        sController = new SceneController(stage);
            //Initialize graph
        bogota = new Graph();
    }
    
}