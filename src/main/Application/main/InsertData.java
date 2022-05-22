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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Objects;
import java.util.ResourceBundle;


public class InsertData implements Initializable {
    public TextField ulrTextField;
    public Label message;
    @FXML
    StackPane parentContainer;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button homeButton, uploadDataButton, questionsButton, signOutButton, addPatientButton, saveButton, dropSaveButton, diagnosisButton;
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
        Main m = new Main();
        m.changeScene("Home.fxml");
    }

    public void signOutButton_Click(ActionEvent e) throws IOException {
        Main m = new Main();
        m.changeScene("Main.fxml");
    }

    public void saveButton_Click(ActionEvent e) throws IOException {

        if (checkField(whiteBloodCellsField, 1000, 30000) &&
                checkField(neutrophilField, 0, 100) &&
                checkField(lymphocytesField, 0, 100) &&
                checkField(redBloodCellsField, 0, 15) &&
                checkField(hematocritField, 0, 100) &&
                checkField(ureaField, 0, 150) &&
                checkField(hemoglobinField, 5, 25) &&
                checkField(creatinineField, 0, 3) &&
                checkField(ironField, 0, 300) &&
                checkField(lipoproteinField, 0, 150) &&
                checkField(phophataseField, 0, 200)) {
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
            Main m = new Main();
            m.changeScene("Diagnosis.fxml");
        }
    }

    public boolean checkField(TextField field, int min, int max) {
        if (field.getText().length() != 0) {
            try {
                double x = Double.parseDouble(field.getText());
                if (x >= min && x <= max) {
                    field.setStyle(null);
                    return true;
                }
            } catch (RuntimeException e) {
                field.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
                new animatefx.animation.RubberBand(field).play();
                return false;
            }
        }
        field.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
        new animatefx.animation.RubberBand(field).play();
        return false;
    }

    public void handleDragOver(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasFiles())
            dragEvent.acceptTransferModes(TransferMode.ANY);
    }

    public void handleDrop(DragEvent dragEvent) {
        String pa = String.valueOf(dragEvent.getDragboard().getFiles());
        pa = pa.replace("[", "").replace("]", "");
        char[] xlsx = new char[5];
        pa.getChars(pa.length() - 5, pa.length(), xlsx, 0);
        if (xlsx[0] == '.' && xlsx[1] == 'x' && xlsx[2] == 'l' && xlsx[3] == 's' && xlsx[4] == 'x') {
            ulrTextField.setText(pa);
            drop_text.setText("File Uploaded - Hit Save Button");
            this.path = pa;
        } else ulrTextField.setText("Error the file not xlsx");
    }

    public void dropSaveButton_Click(ActionEvent actionEvent) throws IOException {
        if (path != null) {
            Node node = (Node) actionEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Patient p = (Patient) stage.getUserData();
            String s=p.addBloodTest(path);
            if (s.equals("ok")) {
                if (p.getBloodTest().get("WBC") > 1000 && p.getBloodTest().get("WBC") < 300000){
                    if (p.getBloodTest().get("Neut") > 0 && p.getBloodTest().get("Neut") < 100){
                        if (p.getBloodTest().get("Lymph") > 0 && p.getBloodTest().get("Lymph") < 100){
                            if (p.getBloodTest().get("RBC") > 0 && p.getBloodTest().get("RBC") < 15){
                                if (p.getBloodTest().get("HCT") > 0 && p.getBloodTest().get("HCT") < 100){
                                    if (p.getBloodTest().get("Urea") > 0 && p.getBloodTest().get("Urea") < 120){
                                        if (p.getBloodTest().get("Hb") > 5 && p.getBloodTest().get("Hb") < 25){
                                            if (p.getBloodTest().get("Crtn") > 0 && p.getBloodTest().get("Crtn") < 3){
                                                if (p.getBloodTest().get("Iron") > 0 && p.getBloodTest().get("Iron") < 300){
                                                    if (p.getBloodTest().get("HDL") > 0 && p.getBloodTest().get("HDL") < 150){
                                                        if (p.getBloodTest().get("AP") > 0 && p.getBloodTest().get("AP") < 200) {
                                                            Main m = new Main();
                                                            m.changeScene("Diagnosis.fxml");
                                                        } else drop_text.setText("WBC values error");
                                                    } else drop_text.setText("Neut values error");
                                                } else drop_text.setText("Lymph values error");
                                            } else drop_text.setText(" RBC values error");
                                        } else drop_text.setText("HCT values error");
                                    } else drop_text.setText("Urea values error");
                                } else drop_text.setText("Hb values error");
                            } else drop_text.setText("Crtn values error");
                        } else drop_text.setText("Iron values error");
                    } else drop_text.setText("HDL values error");
                } else drop_text.setText("AP values error");
            }  else drop_text.setText(s);
        } else drop_text.setText("File Missing");
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

    public void browseFileButton_Click(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            char[] xlsx = new char[5];
            String path = file.getAbsolutePath();
            path.getChars(path.length() - 5, path.length(), xlsx, 0);
            if (xlsx[0] == '.' && xlsx[1] == 'x' && xlsx[2] == 'l' && xlsx[3] == 's' && xlsx[4] == 'x') {
                ulrTextField.setText(file.getAbsolutePath());
                this.path = file.getAbsolutePath();
            } else ulrTextField.setText("Error the file not xlsx");
        }

    }
}
