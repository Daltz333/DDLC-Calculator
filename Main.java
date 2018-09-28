package Calculator;

import Calculator.Logic.Hacks;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class Main extends Application {

	static Hacks resources = new Hacks();
	
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UI/CalculatorMain.fxml"));
        root.getStylesheets().add(getClass().getResource("Styles/CalculatorMain.css").toExternalForm());
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.setTitle("DDLC-Calculator");
        primaryStage.setScene(new Scene(root, 290, 450));

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
    	resources.downloadResources();
        launch(args);

    }

    @Override
    public void stop(){
        System.out.println("Stage is closing");
        System.exit(0);

    }

}
