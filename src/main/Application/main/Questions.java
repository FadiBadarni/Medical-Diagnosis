package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Questions implements Initializable {
    @FXML
    private StackPane parentContainer;
    @FXML
    private Button homeButton, submitButton, skipButton, returnButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ChoiceBox<String> question1, question2, question3, question4, question5, question6, question7, question8;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillChoices(question1, question2, question3, question4);
        fillChoices(question5, question6, question7, question8);
    }

    private void fillChoices(ChoiceBox<String> question1, ChoiceBox<String> question2, ChoiceBox<String> question3, ChoiceBox<String> question4) {
        question1.getItems().add("Yes");
        question1.getItems().add("No");
        question2.getItems().add("Yes");
        question2.getItems().add("No");
        question3.getItems().add("Yes");
        question3.getItems().add("No");
        question4.getItems().add("Yes");
        question4.getItems().add("No");
    }

    public void homeButton_Click(ActionEvent actionEvent) throws IOException {
        new FadeTransitions(parentContainer, "Home.fxml");
    }

    public void signOutButton_Click(ActionEvent actionEvent) {
        new FadeTransitions(parentContainer, "Main.fxml");
    }

    public void submitButton_Click(ActionEvent actionEvent) throws IOException {
        //TODO :SAVE QUESTION ANSWERS AND TAKE THEM INTO CONSIDERATION
        new SlideTransitions().leftToRightTransition(parentContainer, submitButton, anchorPane, "Treatment.fxml");

    }

    public void skipButton_Click(ActionEvent actionEvent) throws IOException {
        new SlideTransitions().leftToRightTransition(parentContainer, skipButton, anchorPane, "Treatment.fxml");
    }


    public void returnButton_Click(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Diagnosis.fxml")));
        SlideTransitions transition = new SlideTransitions();
        transition.rightToLeftTransition(root, parentContainer, returnButton, anchorPane);
    }
}
