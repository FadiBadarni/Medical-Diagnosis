package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Objects;

public class Treatment {
    @FXML
    private StackPane parentContainer;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button homeButton, signOutButton, returnButton;

    public void homeButton_Click(ActionEvent actionEvent) throws IOException {
        new FadeTransitions(parentContainer, "Home.fxml");
    }

    public void signOutButton_Click(ActionEvent actionEvent) {
        new FadeTransitions(parentContainer, "Main.fxml");
    }

    public void returnButton_Click(ActionEvent actionEvent) throws IOException {
        new FadeTransitions(parentContainer, "Questions.fxml");
    }
}
