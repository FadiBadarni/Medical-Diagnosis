package main;

import animatefx.animation.BounceIn;
import animatefx.animation.JackInTheBox;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PatientInfo implements Initializable {
    @FXML
    public AnchorPane ap;
    @FXML
    private TextField nameField, idField, ageField, phoneField, weightField, lengthField, bloodField, genderField;
    @FXML
    private Label nameLabel, idLabel, ageLabel, phoneLabel, weightLabel, lengthLabel, bloodLabel, genderLabel;
    @FXML
    private Button exitButton;
    @FXML
    private StackPane parentContainer;
    private static Stage stg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parentContainer.setOpacity(0);
        makeFadeTransition();
        new BounceIn(nameField).play();
        new BounceIn(idField).play();
        new BounceIn(ageField).play();
        new BounceIn(phoneField).play();
        new BounceIn(weightField).play();
        new BounceIn(lengthField).play();
        new BounceIn(bloodField).play();
        new BounceIn(genderField).play();
        new JackInTheBox(nameLabel).play();
        new JackInTheBox(idLabel).play();
        new JackInTheBox(ageLabel).play();
        new JackInTheBox(phoneLabel).play();
        new JackInTheBox(weightLabel).play();
        new JackInTheBox(lengthLabel).play();
        new JackInTheBox(bloodLabel).play();
        new JackInTheBox(genderField).play();
        nameField.setEditable(false);
        idField.setEditable(false);
        ageField.setEditable(false);
        phoneField.setEditable(false);
        weightField.setEditable(false);
        lengthField.setEditable(false);
        bloodField.setEditable(false);
        genderField.setEditable(false);
    }

    private void makeFadeTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(1));
        fadeTransition.setNode(parentContainer);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    public void exitButton_Click(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
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
}
