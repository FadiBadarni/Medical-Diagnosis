package main;

import animatefx.animation.*;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class Info implements Initializable {
    @FXML
    private StackPane parentContainer;
    @FXML
    private Button exitButton;
    @FXML
    private Label  label1, label2, label3;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parentContainer.setOpacity(0);
        makeFadeTransition();
        AnimationFX animatelabel1 = new JackInTheBox(label1);
        AnimationFX animatelabel2 = new JackInTheBox(label2);
        AnimationFX animatelabel3 = new JackInTheBox(label3);
        AnimationFX animateExit = new Shake(exitButton);
        exitButton.setVisible(false);
        animatelabel1.setOnFinished(actionEvent -> {
            animatelabel2.setOnFinished(actionEvent1 -> {
                animatelabel3.setOnFinished(actionEvent2 -> {
                    animateExit.play();
                    exitButton.setVisible(true);
                });
                animatelabel3.play();
            });
            animatelabel2.play();
        });
        animatelabel1.play();
    }

    private void makeFadeTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(1));
        fadeTransition.setNode(parentContainer);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    public void exitButton_Click(ActionEvent actionEvent) throws IOException {
        new FadeTransitions().exitFadeTransition(parentContainer, exitButton);
    }


}
