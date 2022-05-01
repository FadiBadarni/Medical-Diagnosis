package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class questions extends Application {
    @FXML
    private StackPane parentContainer;
    @FXML
    private Button homeButton;
    @FXML
    private AnchorPane anchorPane;
    public void homeButton_Click(ActionEvent actionEvent) throws IOException {
        new fadeTransitions(parentContainer, "home.fxml");
    }

    public void signOutButton_Click(ActionEvent actionEvent) {
        new fadeTransitions(parentContainer, "main.fxml");
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
