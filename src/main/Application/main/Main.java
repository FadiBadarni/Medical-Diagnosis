package main;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.util.Objects;
import java.util.ResourceBundle;

public class Main extends Application implements Initializable {
    private static Stage stg;
    @FXML
    private StackPane parentContainer;
    @FXML
    private Button loginButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Circle circle1,circle2,circle3;

    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add("main.css");
        stage.setTitle("Welcome");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parentContainer.setOpacity(0);
        makeFadeTransition();
        new RotationAnimation(circle1, true, 360, 15);
        new RotationAnimation(circle2, true, 360, 20);
        new RotationAnimation(circle3, true, 360, 25);
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
        new slideTransitions(parentContainer,loginButton,anchorPane);
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
        new fadeTransitions(parentContainer,"Register.fxml");
    }

    public void exitButton_Click(ActionEvent e) throws IOException {
        Main m = new Main();
        m.changeScene("home.fxml");

    }


}