package main;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class fadeTransitions {
    public fadeTransitions(){

    }
    public fadeTransitions(StackPane parentContainer, String resource) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(0.3));
        fadeTransition.setNode(parentContainer);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(resource)));
                    Scene newScene = new Scene(root);
                    Stage currentStage = (Stage) parentContainer.getScene().getWindow();
                    currentStage.setScene(newScene);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        fadeTransition.play();
    }
    public void fadeTransitionsIn(StackPane parentContainer, String resource) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(0.3));
        fadeTransition.setNode(parentContainer);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(resource)));
                    Scene newScene = new Scene(root);
                    Stage currentStage = (Stage) parentContainer.getScene().getWindow();
                    currentStage.setScene(newScene);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        fadeTransition.play();
    }
    public void exitFadeTransition(StackPane parentContainer, Button button){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(0.3));
        fadeTransition.setNode(parentContainer);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(actionEvent -> {
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
        });
        fadeTransition.play();
    }
}
