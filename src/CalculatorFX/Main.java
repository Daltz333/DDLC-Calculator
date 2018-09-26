package CalculatorFX;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class Main extends Application {
	
	private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UI/CalculatorMain.fxml"));
        root.getStylesheets().add(getClass().getResource("Styles/CalculatorMain.css").toExternalForm());
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Anime Calculator");
        primaryStage.setScene(new Scene(root, 290, 450));
        Platform.setImplicitExit(false);
        
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
            	stop();
            }
        });

        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
       
    }


    public static void main(String[] args) {
        launch(args);
        
    }
    
    @Override
    public void stop(){
        System.out.println("Stage is closing");
        System.exit(0);
        
        // Save file
    }
    
}
