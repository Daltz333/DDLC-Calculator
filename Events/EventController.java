package Calculator.Events;

import Calculator.Logic.Hacks;
import Calculator.Logic.Threaded;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.AWTException;
import java.util.Timer;

import Calculator.Logic.Basic;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EventController {
    @FXML private TextField textBox;

    Basic basicMath = new Basic();
    Hacks hacker = new Hacks();
    String temp = "";
    Timer timer = new Timer();
    Threaded music = new Threaded();
    Thread t = new Thread(music);
    int delay = 5000;   // delay for 5 sec.
    int interval = 1000;  // iterate every sec.
    Boolean justSayori = false;

    int currentState = 0;

    @FXML public void inputNum(ActionEvent event){
        //get current numButton, convert from string to int
        int numPressed = Integer.parseInt(((Button)event.getSource()).getText());
        temp = temp + Integer.toString(numPressed);
        textBox.setText(temp);

        if (basicMath.getCurrentState() == 0) {
            basicMath.setNumOne(numPressed);

        } else if (basicMath.getCurrentState() == 1 || basicMath.getCurrentState() == 2) {
            basicMath.setNumTwo(numPressed);

        } else {
            //do nothing, don't need number

        }


    }

    @FXML public void setOperand(ActionEvent event) {
        //convert string to operand
        char operand = ((Button)event.getSource()).getText().charAt(0);
        textBox.setText(Character.toString(operand));
        temp = "";
        basicMath.setOperand(operand);

    }

    int counter = 0;

    @FXML public void justMonika(ActionEvent event) throws InterruptedException, AWTException {

        Stage stageSource = (Stage)((Node)event.getSource()).getScene().getWindow();
        textBox.setText("Just Monika");
        hacker.changeWallpaper();
        if (t.getState() == Thread.State.RUNNABLE) {
            //do nothing
            System.out.println("Thread in use");

        } else {
            t.start();
            System.out.println("Playing Music");

        }

        stageSource.toFront();
        stageSource.setMaximized(true);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(5), e -> {
                    if (counter == 0) { textBox.setText("Just.. Sayori.");}
                    if (counter == 1) { textBox.setText("You can't hide");}
                    if (counter == 2) { textBox.setText("I loved you");}
                    if (counter == 3) { textBox.setText("and you did this to me");}
                    if (counter == 4) { textBox.setText("you can't run");}
                    if (counter == 5) { textBox.setText("Together.. forever");}
                    if (counter == 6) { try {
                        stageSource.setMaximized(false);
                        hacker.showDesktop();

                    } catch (AWTException e1) {
                        e1.printStackTrace();

                    }}
                    counter += 1;

                })

        );

        justSayori = true;
        timeline.setCycleCount(7);
        timeline.play();

    }

    @FXML public void calculate() {
        temp="";
        if (basicMath.getCurrentState() == 2) {
            String result = basicMath.calculate();
            System.out.println("Exited Calc");
            textBox.setText(result);

        } else {
            textBox.setText("ERR");

        }

    }

    @FXML
    public void exitApplication(ActionEvent event) {
        Platform.exit();
    }

    @FXML public void clearTextField() {
        textBox.clear();
        basicMath.clearNumData();

        if (justSayori) {
            System.exit(0);

        }

    }



}
