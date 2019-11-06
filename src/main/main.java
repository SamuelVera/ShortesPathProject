package main;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Main class
 */
public class main extends Application {

        //Scene controller Singleton
    protected static SceneController sController;
    
        //Start JAVA FX App method
    @Override
    public void start(Stage stage) throws Exception {
            //Hide native menubar
        stage.initStyle(StageStyle.UNDECORATED);
            //Scene Controller construction
        sController = new SceneController(stage);
    }
    
}