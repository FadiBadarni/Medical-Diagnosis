package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class diagnosis {
    @FXML
    StackPane parentContainer;
    @FXML
    Button goToTreatmentButton,proceedTreatmentButton,homeButton;
    @FXML
    AnchorPane anchorPane;

    public void homeButton_Click(ActionEvent actionEvent) {

    }

    public void signOutButton_Click(ActionEvent actionEvent) {

    }

    public void proceedTreatmentButton_Click(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("askQuestionsPopUp.fxml")));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void goToTreatmentButton_Click(ActionEvent actionEvent) throws IOException {
        new fadeTransitions().exitFadeTransition(parentContainer, goToTreatmentButton);
        Main m = new Main();
        m.changeScene("questions.fxml");
    }

    public void goToQuestionsButton_Click(ActionEvent actionEvent) {

    }
}
