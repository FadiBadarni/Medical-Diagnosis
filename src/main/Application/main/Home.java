package main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class Home implements Initializable {
    @FXML
    private Button homeButton, uploadDataButton, questionsButton, signOutButton, diagnosisButton, infoButton, displayInfoButton, addPatientButton;
    @FXML
    private StackPane parentContainer;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane pane;
    @FXML
    private ListView<String> listview1;
    private final ListPatient listPatient = new ListPatient();
    private static Stage stg;
    @FXML
    private Label warning;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateData();
        displayInfoButton.setStyle("-fx-text-fill: red ;");
        diagnosisButton.setStyle("-fx-text-fill: red ;");
        displayInfoButton.setDisable(true);
        diagnosisButton.setDisable(true);
        listview1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                displayInfoButton.setStyle("-fx-text-fill: #000000 ;");
                diagnosisButton.setStyle("-fx-text-fill: #000000 ;");
                displayInfoButton.setDisable(false);
                diagnosisButton.setDisable(false);
                displayInfoButton.setStyle("-fx-cursor: hand");
                diagnosisButton.setStyle("-fx-cursor: hand");
            }
        });
    }

    private void updateData() {
    String s=listPatient.insertData("PatientList.xlsx");
    if(s.equals("ok"))
    //System.out.println(s);
    {
        listview1.setStyle("-fx-control-inner-background: rgba(252,233,158,0.99);");
        listview1.setStyle("-fx-font-size:19.0");
        listview1.getItems().addAll(listPatient.getIdList());
    }else warning.setText(s);
    }

    public void homeButton_Click(ActionEvent e) throws IOException {
        Main m = new Main();
        m.changeScene("Home.fxml");
    }

    public void addPatientButton_Click(ActionEvent e) throws IOException {
        Main m = new Main();
        m.changeScene("AddPatient.fxml");
    }

    public void signOutButton_Click(ActionEvent e) throws IOException {
        Main m = new Main();
        m.changeScene("Main.fxml");
    }

    @FXML
    public void diagnosisButton_Click(ActionEvent actionEvent) throws IOException {
        Patient p = listPatient.getPatients().get(listview1.getSelectionModel().getSelectedIndex());
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setUserData(p);
        Main m = new Main();
        m.changeScene("InsertData.fxml");
    }

    public void displayInfoButton_Click(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PatientInfo.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        TextField nameField = (TextField) scene.lookup("#nameField");
        TextField idField = (TextField) scene.lookup("#idField");
        TextField ageField = (TextField) scene.lookup("#ageField");
        TextField phoneField = (TextField) scene.lookup("#phoneField");
        TextField weightField = (TextField) scene.lookup("#weightField");
        TextField lengthField = (TextField) scene.lookup("#lengthField");
        TextField genderField = (TextField) scene.lookup("#genderField");
        Patient p = listPatient.getPatient(listview1.getSelectionModel().getSelectedIndex());
        idField.setText(p.getId() + "");
        nameField.setText(p.getFirstName() + " " + p.getLastName());
        ageField.setText(p.getAge() + "");
        phoneField.setText("0" + p.getPhone());
        weightField.setText(p.getWeight() + "Kg");
        lengthField.setText(p.getLength() + "Cm");
        genderField.setText(p.getGender());
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
