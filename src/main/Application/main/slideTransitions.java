package main;

import javafx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class slideTransitions {
    public slideTransitions() {

    }

    public slideTransitions(StackPane parentContainer, Button button, AnchorPane anchorPane, String resource) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(resource)));
        Scene scene = button.getScene();
        root.translateYProperty().set(scene.getHeight());
        parentContainer.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event -> {
            parentContainer.getChildren().remove(anchorPane);
        });
        timeline.play();
    }

    public void rightToLeftTransition(StackPane parentContainer, Button returnButton, AnchorPane anchorPane1, AnchorPane anchorPane2) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        Scene scene = returnButton.getScene();
        root.translateXProperty().set(scene.getWidth());
        parentContainer.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event -> {
            parentContainer.getChildren().remove(anchorPane1);
            parentContainer.getChildren().remove(anchorPane2);
        });
        timeline.play();
    }

    public void leftToRightTransition(StackPane parentContainer, Button button, AnchorPane anchorPane1, Pane pane, String resource) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(resource)));
        Scene scene = button.getScene();
        root.translateXProperty().set(-1 * scene.getWidth());
        parentContainer.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event -> {
            parentContainer.getChildren().remove(anchorPane1);
            parentContainer.getChildren().remove(pane);

        });
        timeline.play();
    }

}
