package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
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

    @FXML
    private Text drop_text;

    private String path;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void homeButton_Click(ActionEvent e) throws IOException {
        new SlideTransitions().leftToRightTransition(parentContainer, homeButton, anchorPane, "Home.fxml");
    }

    public void addPatientButton_Click(ActionEvent actionEvent) throws IOException {
        new SlideTransitions().leftToRightTransition(parentContainer, addPatientButton, anchorPane, "AddPatient.fxml");
    }

    public void signOutButton_Click(ActionEvent e) throws IOException {
        new FadeTransitions(parentContainer, "Main.fxml");
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
            Hashtable<String, Integer> bloodTest = new Hashtable<>();
            bloodTest.put("WBC", Integer.valueOf(whiteBloodCellsField.getText()));
            bloodTest.put("Neut", Integer.valueOf(neutrophilField.getText()));
            bloodTest.put("Lymph", Integer.valueOf(lymphocytesField.getText()));
            bloodTest.put("RBC", Integer.valueOf(redBloodCellsField.getText()));
            bloodTest.put("HCT", Integer.valueOf(hematocritField.getText()));
            bloodTest.put("Urea", Integer.valueOf(ureaField.getText()));
            bloodTest.put("Hb", Integer.valueOf(hemoglobinField.getText()));
            bloodTest.put("Crtn", Integer.valueOf(creatinineField.getText()));
            bloodTest.put("Iron", Integer.valueOf(ironField.getText()));
            bloodTest.put("HDL", Integer.valueOf(lipoproteinField.getText()));
            bloodTest.put("AP", Integer.valueOf(phophataseField.getText()));

            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Patient p = (Patient) stage.getUserData();
            p.setBloodTest(bloodTest);
        }
        new SlideTransitions().leftToRightTransition(parentContainer, homeButton, anchorPane, "Diagnosis.fxml");


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
        //   xlsxfile= new File( dragEvent.getDragboard().getFiles().toString());
        drop_text.setText("Done");
    }


    public void dropSaveButton_Click(ActionEvent actionEvent) {
        if (path != null) {

            Node node = (Node) actionEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Patient p = (Patient) stage.getUserData();
            p.addBloodTest(path);
            System.out.println(p.getFirstName());

            System.out.println(p.getBloodTest().toString());

        } else drop_text.setText("Error");
    }
}
