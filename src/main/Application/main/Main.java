package main;

import animatefx.animation.Bounce;
import animatefx.animation.RollIn;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Main extends Application implements Initializable {
    private static Stage stg;
    @FXML
    private StackPane parentContainer;
    @FXML
    private Button loginButton, registerButton, infoButton, exitButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Circle circle1, circle2, circle3;
    @FXML
    private Label label;

    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        stage.setResizable(false);
        //stage.getIcons().add(new Image("appIcon.ico"));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //scene.getStylesheets().add("/CSS/Main.css");
        stage.setTitle("Medical Diagnosis Application");
        stage.setScene(scene);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stg.setX((primScreenBounds.getWidth() - stg.getWidth()) / 3);
        stg.setY((primScreenBounds.getHeight() - stg.getHeight()) / 3);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parentContainer.setOpacity(0);
        makeFadeTransition();
        new RotationAnimation(circle1, true, 360, 15);
        new RotationAnimation(circle2, true, 360, 20);
        new RotationAnimation(circle3, true, 360, 25);
        new Bounce(label).play();
        new RollIn(loginButton).play();
        new RollIn(registerButton).play();
        new RollIn(infoButton).play();
        new RollIn(exitButton).play();
    }

    private void makeFadeTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(1));
        fadeTransition.setNode(parentContainer);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        stg.getScene().setRoot(pane);
        stg.sizeToScene();
    }

    public void loginButton_Click(ActionEvent e) throws IOException {
        new SlideTransitions(parentContainer, loginButton, anchorPane, "Login.fxml");
    }

    public void infoButton_Click(ActionEvent e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("""
                Final Project For The Course Quality And Software Testing
                Made By
                Fadi Badarni
                Abdalla Sheikh Khalil
                Abed Alrhman Abu Khiet
                """);
        alert.showAndWait();
    }

    public void registerButton_Click(ActionEvent e) throws IOException {
        new FadeTransitions(parentContainer, "Register.fxml");
    }

    public void exitButton_Click(ActionEvent e) throws IOException {
        Main m = new Main();
        m.changeScene("Home.fxml");
    }

}