package main;

import javafx.animation.RotateTransition;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class RotationAnimation {

    public RotationAnimation(Circle c, boolean reverse, int angle, int duration) {
        RotateTransition rt = new RotateTransition(Duration.seconds(duration), c);
        rt.setAutoReverse(reverse);
        rt.setByAngle(angle);
        rt.setDelay(Duration.seconds(0));
        rt.setRate(3);
        rt.setCycleCount(18);
        rt.play();
    }
}
