package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class insertData implements Initializable {
    @FXML
    StackPane parentContainer;
    @FXML
    AnchorPane anchorPane;
    @FXML
    private Button homeButton, insertDataButton, uploadDataButton, questionsButton, signOutButton;
    @FXML
    private TextField whiteBloodCellsField,neutrophilField,lymphocytesField,hematocritField,ureaField,hemoglobinField,
            creatinineField,ironField,lipoproteinField,phophataseField,redBloodCellsField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void homeButton_Click(ActionEvent e) throws IOException {
        new slideTransitions().leftToRightTransition(parentContainer, insertDataButton, anchorPane, "home.fxml");
    }

    public void insertDataButton_Click(ActionEvent e) throws IOException {
        new slideTransitions().leftToRightTransition(parentContainer, insertDataButton, anchorPane, "insertData.fxml");
    }

    public void uploadDataButton_Click(ActionEvent e) throws IOException {
        new slideTransitions().leftToRightTransition(parentContainer, insertDataButton, anchorPane, "uploadData.fxml");
    }

    public void signOutButton_Click(ActionEvent e) throws IOException {
        new fadeTransitions(parentContainer, "main.fxml");
    }
    public void saveButton_Click(ActionEvent e)throws IOException{
        checkField(whiteBloodCellsField);
        checkField(neutrophilField);
        checkField(lymphocytesField);
        checkField(hematocritField);
        checkField(ureaField);
        checkField(hemoglobinField);
        checkField(creatinineField);
        checkField(ironField);
        checkField(lipoproteinField);
        checkField(phophataseField);
        checkField(redBloodCellsField);
    }
    public void checkField(TextField field){
        if(field.getText().length()==0){
            field.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
            new animatefx.animation.RubberBand(field).play();
        }
        else{
            field.setStyle(null);
        }
    }
}
