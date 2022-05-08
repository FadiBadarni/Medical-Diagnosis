package main;

import animatefx.animation.*;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Info implements Initializable {
    @FXML
    private Pane parentContainer;
    @FXML
    private Button exitButton2;
    @FXML
    private Label label1, label2, label3;
    private static Stage stg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parentContainer.setOpacity(0);
        makeFadeTransition();
        AnimationFX animatelabel1 = new JackInTheBox(label1);
        AnimationFX animatelabel2 = new JackInTheBox(label2);
        AnimationFX animatelabel3 = new JackInTheBox(label3);
        AnimationFX animateExit = new Shake(exitButton2);
        exitButton2.setVisible(false);
        animatelabel1.setOnFinished(actionEvent -> {
            animatelabel2.setOnFinished(actionEvent1 -> {
                animatelabel3.setOnFinished(actionEvent2 -> {
                    animateExit.play();
                    exitButton2.setVisible(true);
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
        Stage stage = (Stage) exitButton2.getScene().getWindow();
        stage.close();
    }


    public void panePressed(MouseEvent mouseEvent) {
        stg = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Delta.x = stg.getX() - mouseEvent.getScreenX();
        Delta.y = stg.getY() - mouseEvent.getScreenY();
    }

    public void paneDragged(MouseEvent mouseEvent) {
        stg = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stg.setX(Delta.x + mouseEvent.getScreenX());
        stg.setY(Delta.y + mouseEvent.getScreenY());
    }
}
