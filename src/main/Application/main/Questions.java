package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private ChoiceBox<String> question1, question2, question3, question4;
    private static Stage stg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillChoices(question1, question2, question3, question4);

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

    public void signOutButton_Click(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("Main.fxml");
    }

    public void submitButton_Click(ActionEvent actionEvent) throws IOException {
        //TODO :SAVE QUESTION ANSWERS AND TAKE THEM INTO CONSIDERATION
        new FadeTransitions(parentContainer, "Treatment.fxml");
    }

    public void skipButton_Click(ActionEvent actionEvent) throws IOException {
        new FadeTransitions(parentContainer, "Treatment.fxml");
    }


    public void returnButton_Click(ActionEvent actionEvent) throws IOException {
        new FadeTransitions(parentContainer, "Diagnosis.fxml");
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

    public void infoButton_Click(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Info.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
}
