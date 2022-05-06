package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Objects;
import java.util.ResourceBundle;



public class InsertData implements Initializable {
    public Label title;
    @FXML
    StackPane parentContainer;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button homeButton, uploadDataButton, questionsButton, signOutButton, addPatientButton,saveButton,dropSaveButton,diagnosisButton;
    @FXML
    private TextField whiteBloodCellsField, neutrophilField, lymphocytesField, hematocritField, ureaField, hemoglobinField,
            creatinineField, ironField, lipoproteinField, phophataseField, redBloodCellsField;
    @FXML
    private Pane pane;
    private static Stage stg;

    @FXML
    private Text drop_text;

    private String path;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void homeButton_Click(ActionEvent e) throws IOException {
        new FadeTransitions(parentContainer, "Home.fxml");     }

    public void signOutButton_Click(ActionEvent e) throws IOException {
        Main m = new Main();
        m.changeScene("Main.fxml");
    }

    public void saveButton_Click(ActionEvent e) throws IOException {

        if (checkField(whiteBloodCellsField) &&
                checkField(neutrophilField) &&
                checkField(lymphocytesField) &&
                checkField(hematocritField) &&
                checkField(ureaField) &&
                checkField(hemoglobinField) &&
                checkField(creatinineField) &&
                checkField(ironField) &&
                checkField(lipoproteinField) &&
                checkField(phophataseField) &&
                checkField(redBloodCellsField)) {
            Hashtable<String, Double> bloodTest = new Hashtable<>();
            bloodTest.put("WBC", Double.valueOf(whiteBloodCellsField.getText()));
            bloodTest.put("Neut", Double.valueOf(neutrophilField.getText()));
            bloodTest.put("Lymph", Double.valueOf(lymphocytesField.getText()));
            bloodTest.put("RBC", Double.valueOf(redBloodCellsField.getText()));
            bloodTest.put("HCT", Double.valueOf(hematocritField.getText()));
            bloodTest.put("Urea", Double.valueOf(ureaField.getText()));
            bloodTest.put("Hb", Double.valueOf(hemoglobinField.getText()));
            bloodTest.put("Crtn", Double.valueOf(creatinineField.getText()));
            bloodTest.put("Iron", Double.valueOf(ironField.getText()));
            bloodTest.put("HDL", Double.valueOf(lipoproteinField.getText()));
            bloodTest.put("AP", Double.valueOf(phophataseField.getText()));
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Patient p = (Patient) stage.getUserData();
            p.setBloodTest(bloodTest);
            new FadeTransitions(parentContainer, "Diagnosis.fxml");
        }
    }

    public boolean checkField(TextField field) {
        if (field.getText().length() == 0) {
            field.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
            new animatefx.animation.RubberBand(field).play();
            return false;
        } else {
            field.setStyle(null);
            return true;
        }
    }

    public void handleDragOver(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasFiles())
            dragEvent.acceptTransferModes(TransferMode.ANY);
    }

    public void handleDrop(DragEvent dragEvent) throws FileNotFoundException {
        path = String.valueOf(dragEvent.getDragboard().getFiles());
        path = path.replace("[", "").replace("]", "");
        drop_text.setText("Done");
    }

    public void dropSaveButton_Click(ActionEvent actionEvent) {
        if (path != null) {
            Node node = (Node) actionEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Patient p = (Patient) stage.getUserData();
          if(p.addBloodTest(path))
            new FadeTransitions(parentContainer, "Diagnosis.fxml");
          else drop_text.setText("Error");
        } else drop_text.setText("Error");
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
