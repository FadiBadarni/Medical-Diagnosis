package main;

import animatefx.animation.AnimationFX;
import animatefx.animation.Bounce;
import animatefx.animation.BounceIn;
import animatefx.animation.RollIn;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Main extends Application implements Initializable {
    private static Stage stg;
    @FXML
    private Pane parentContainer;
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
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Medical Diagnosis Application");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parentContainer.setOpacity(0);
        makeFadeTransition();
        AnimationFX animateLogin = new BounceIn(loginButton);
        AnimationFX animateRegister = new BounceIn(registerButton);
        AnimationFX animateInfo = new BounceIn(infoButton);
        AnimationFX animateExit = new BounceIn(exitButton);
        registerButton.setVisible(false);
        loginButton.setVisible(false);
        infoButton.setVisible(false);
        exitButton.setVisible(false);
        animateLogin.setOnFinished(actionEvent -> {
            animateRegister.setOnFinished(actionEvent1 -> {
                animateInfo.setOnFinished(actionEvent2 -> {
                    exitButton.setVisible(true);
                    animateExit.play();
                });
                infoButton.setVisible(true);
                animateInfo.play();
            });
            registerButton.setVisible(true);
            animateRegister.play();
        });
        loginButton.setVisible(true);
        animateLogin.play();
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
        Main m = new Main();
        m.changeScene("Login.fxml");
    }

    public void infoButton_Click(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Info.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void registerButton_Click(ActionEvent e) throws IOException {
        Main m = new Main();
        m.changeScene("Register.fxml");
    }

    public void exitButton_Click(ActionEvent e) throws IOException {
        Main m = new Main();
        m.changeScene("Home.fxml");
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

    public void terminateButton_Click(ActionEvent actionEvent) {
        System.exit(0);
    }
}