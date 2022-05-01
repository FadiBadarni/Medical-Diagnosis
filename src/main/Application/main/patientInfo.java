package main;

import animatefx.animation.BounceIn;
import animatefx.animation.Flip;
import animatefx.animation.JackInTheBox;
import animatefx.animation.SlideInLeft;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class patientInfo implements Initializable {
    @FXML
    private TextField nameField,idField,ageField,phoneField,weightField,lengthField,bloodField;
    @FXML
    private Label nameLabel,idLabel,ageLabel,phoneLabel,weightLabel,lengthLabel,bloodLabel;
    @FXML
    private Button exitButton;
    @FXML
    private StackPane parentContainer;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parentContainer.setOpacity(0);
        makeFadeTransition();
        new BounceIn(nameField).play();
        new BounceIn(idField).play();
        new BounceIn(ageField).play();
        new BounceIn(phoneField).play();
        new BounceIn(weightField).play();
        new BounceIn(lengthField).play();
        new BounceIn(bloodField).play();
        new JackInTheBox(nameLabel).play();
        new JackInTheBox(idLabel).play();
        new JackInTheBox(ageLabel).play();
        new JackInTheBox(phoneLabel).play();
        new JackInTheBox(weightLabel).play();
        new JackInTheBox(lengthLabel).play();
        new JackInTheBox(bloodLabel).play();
        nameField.setEditable(true);
        idField.setEditable(false);
        ageField.setEditable(false);
        phoneField.setEditable(false);
        weightField.setEditable(false);
        lengthField.setEditable(false);
        bloodField.setEditable(false);
    }
    private void makeFadeTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(1));
        fadeTransition.setNode(parentContainer);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    public void exitButton_Click(ActionEvent actionEvent) {
        new fadeTransitions().exitFadeTransition(parentContainer,exitButton);
    }
}
