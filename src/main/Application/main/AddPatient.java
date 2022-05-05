package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class AddPatient implements Initializable {
    public TextField weightField;
    public TextField lastNameField;
    public TextField firstNameField;
    public TextField ageField;
    public TextField idField;
    public TextField lengthField;
    public TextField phoneField;
    public TextField bloodTypeField;
    public CheckBox eastCheckBox,ethiopianCheckBox;
    public ChoiceBox<String> genderBox;

    @FXML
    StackPane parentContainer;
    @FXML
    AnchorPane anchorPane;
    @FXML
    private Button homeButton, insertDataButton, uploadDataButton, questionsButton, signOutButton, addPatientButton;
    @FXML
    private Pane pane;

    private String path;

    @FXML
    private Text drop_text;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderBox.getItems().add("Male");
        genderBox.getItems().add("Female");
    }

    public void homeButton_Click(ActionEvent e) throws IOException {
        new FadeTransitions(parentContainer, "Home.fxml");     }

    public void signOutButton_Click(ActionEvent e) throws IOException {
        new FadeTransitions(parentContainer, "Main.fxml");     }

    public void addPatientButton_Click(ActionEvent actionEvent) throws IOException {
        new FadeTransitions(parentContainer, "AddPatient.fxml");     }

    public void saveButton_Click(ActionEvent actionEvent) {
        if(isCurrentInput()) {

            String[] data = {idField.getText(),firstNameField.getText(), lastNameField.getText(),
                    ageField.getText(), weightField.getText(), lengthField.getText(),
                    phoneField.getText(),bloodTypeField.getText(),genderBox.getSelectionModel().getSelectedItem(),eastCheckBox.isSelected()+"",ethiopianCheckBox.isSelected()+""};
            try {
                ReadWriteXlsx file=new ReadWriteXlsx("PatientList.xlsx");
                file.add(data);
                new SlideTransitions().leftToRightTransition(parentContainer, insertDataButton, anchorPane, "Home.fxml");
            } catch (IOException | InvalidFormatException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public boolean isCurrentInput(){
        if(idField.getText().length()!=9) return false;

        try {
            int number = Integer.parseInt(idField.getText());
            ReadWriteXlsx file = new ReadWriteXlsx("PatientList.xlsx");
            Iterator<Cell> cellIterator= file.getAllRow(number);
            if(cellIterator!=null) return false;
            if(firstNameField.getText().length()==0) return false;
            if(lastNameField.getText().length()==0) return false;
            Integer.parseInt(ageField.getText());
            Integer.parseInt(weightField.getText());
            Integer.parseInt(lengthField.getText());
            Integer.parseInt(phoneField.getText());
            if(phoneField.getText().length()!=10) return false;
            if(!genderBox.isScaleShape()) return false;
        } catch (NumberFormatException | IOException | InvalidFormatException e) {
            return false;
        }

        return true;
    }


    public void handleDrop(DragEvent dragEvent) {
        path = String.valueOf(dragEvent.getDragboard().getFiles());
        path = path.replace("[", "").replace("]", "");
        drop_text.setText("Done");
    }
    public void handleDragOver(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasFiles())
            dragEvent.acceptTransferModes(TransferMode.ANY);
    }


    public void saveButton1_Click(ActionEvent actionEvent) throws IOException, InvalidFormatException {
        if(path!=null) {
            ReadWriteXlsx readWriteXlsx = new ReadWriteXlsx("PatientList.xlsx");
            readWriteXlsx.copy(path);
            new FadeTransitions(parentContainer, "Home.fxml");
        }
        else   drop_text.setText("Error");
    }
}
