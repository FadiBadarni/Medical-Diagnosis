package main;

import animatefx.animation.Bounce;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class Home implements Initializable {
    @FXML
    public Button displayInfoButton, addPatientButton;
    @FXML
    private Button homeButton, uploadDataButton, questionsButton, signOutButton;
    @FXML
    private StackPane parentContainer;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane pane;
    @FXML
    private TextField nameField, idField, ageField, phoneField, weightField, lengthField, bloodField;

    @FXML
    private ListView<String> listview1;

    private ListPatient listPatient = new ListPatient();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateData();

    }

    private void updateData() {
        listPatient.insertData("PatientList.xlsx");
        listview1.getItems().addAll(listPatient.getIdList());
    }


    public void homeButton_Click(ActionEvent e) throws IOException {
        new SlideTransitions().leftToRightTransition(parentContainer, homeButton, anchorPane, "Home.fxml");
    }

    public void addPatientButton_Click(ActionEvent e) throws IOException {
        new SlideTransitions().leftToRightTransition(parentContainer, addPatientButton, anchorPane, "AddPatient.fxml");
    }

    public void signOutButton_Click(ActionEvent e) throws IOException {
        new FadeTransitions(parentContainer, "Main.fxml");
    }


    public void info(ActionEvent actionEvent) {
        displayInfoButton.setText(listview1.getSelectionModel().getSelectedItem());
    }


    @FXML
    public void diagnosisButton_Click(ActionEvent actionEvent) throws IOException {

        Patient p = listPatient.getPatients().get(listview1.getSelectionModel().getSelectedIndex());
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setUserData(p);
        new SlideTransitions().leftToRightTransition(parentContainer, homeButton, anchorPane, "InsertData.fxml");

    }

    @FXML
    private void sendData(ActionEvent event) {

    }

    public void displayInfoButton_Click(ActionEvent actionEvent) throws IOException {

        Stage stage = new Stage();
        Patient p = listPatient.getPatients().get(listview1.getSelectionModel().getSelectedIndex());

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PatientInfo.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
}
