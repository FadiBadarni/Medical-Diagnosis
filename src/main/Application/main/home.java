package main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class home implements Initializable {
    @FXML
    public Button displayInfoButton;
    @FXML
    private Button homeButton, insertDataButton, uploadDataButton, questionsButton, signOutButton;
    @FXML
    private StackPane parentContainer;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane pane;

    @FXML
    private ListView<String> listview1;

     private ListPatient listPatient=new ListPatient();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateData();
    }
    private void updateData() {
        listPatient.insertData("PatientList.xlsx");
        listview1.getItems().addAll(listPatient.getIdList());
    }


    public void homeButton_Click(ActionEvent e) throws IOException {
        new slideTransitions().leftToRightTransition(parentContainer, insertDataButton, anchorPane, pane, "home.fxml");
    }

    public void insertDataButton_Click(ActionEvent e) throws IOException {
        new slideTransitions().leftToRightTransition(parentContainer, insertDataButton, anchorPane, pane, "insertData.fxml");
    }

    public void addPatientButton_Click(ActionEvent e) throws IOException {
        new slideTransitions().leftToRightTransition(parentContainer, insertDataButton, anchorPane, pane, "addPatient.fxml");
    }

    public void signOutButton_Click(ActionEvent e) throws IOException {
        new fadeTransitions(parentContainer, "main.fxml");
    }


    public void info(ActionEvent actionEvent) {
        displayInfoButton.setText(listview1.getSelectionModel().getSelectedItem());
    }



    @FXML
    public void diagnosis_Button_Click(ActionEvent actionEvent) throws IOException {

        if(!listview1.getSelectionModel().isEmpty()) {
            Patient p = listPatient.getPatients().get(listview1.getSelectionModel().getSelectedIndex());
            Node node = (Node) actionEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setUserData(p);
            new slideTransitions().leftToRightTransition(parentContainer, insertDataButton, anchorPane, pane, "insertData.fxml");
        }
    }

    @FXML
    private void sendData(ActionEvent event) {

    }

    public void displayInfoButton_Click(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("patientInfo.fxml")));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
}
