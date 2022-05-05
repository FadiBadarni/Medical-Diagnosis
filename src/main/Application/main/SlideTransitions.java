package main;

import javafx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class SlideTransitions {
    public SlideTransitions() {

    }

    public void rightToLeftTransition(Parent root, StackPane parentContainer, Button returnButton, AnchorPane anchorPane1) throws IOException {
        Scene scene = returnButton.getScene();
        root.translateXProperty().set(scene.getWidth());
        parentContainer.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event -> {
            parentContainer.getChildren().remove(anchorPane1);

        });
        timeline.play();
    }

    public void leftToRightTransition(StackPane parentContainer, Button button, AnchorPane anchorPane1, String resource) throws IOException {
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

        });
        timeline.play();
    }

}
