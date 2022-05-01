package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addPatient implements Initializable {
    @FXML
    StackPane parentContainer;
    @FXML
    AnchorPane anchorPane;
    @FXML
    private Button homeButton, insertDataButton, uploadDataButton, questionsButton, signOutButton, addPatientButton;
    @FXML
    private Pane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void homeButton_Click(ActionEvent e) throws IOException {
        new slideTransitions().leftToRightTransition(parentContainer, insertDataButton, anchorPane, pane, "home.fxml");
    }

    public void insertDataButton_Click(ActionEvent e) throws IOException {
        new slideTransitions().leftToRightTransition(parentContainer, insertDataButton, anchorPane, pane, "insertData.fxml");
    }

    public void uploadDataButton_Click(ActionEvent e) throws IOException {
        new slideTransitions().leftToRightTransition(parentContainer, insertDataButton, anchorPane, pane, "addPatient.fxml");
    }

    public void signOutButton_Click(ActionEvent e) throws IOException {
        new fadeTransitions(parentContainer, "main.fxml");
    }

    public void addPatientButton_Click(ActionEvent actionEvent) throws IOException {
        new slideTransitions().leftToRightTransition(parentContainer, insertDataButton, anchorPane, pane, "addPatient.fxml");
    }

    public void saveButton_Click(ActionEvent actionEvent) {

    }
}
