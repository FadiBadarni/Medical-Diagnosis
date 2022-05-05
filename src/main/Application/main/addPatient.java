package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addPatient implements Initializable {
    public TextField weightField;
    public TextField lastNameField;
    public TextField firstNameField;
    public TextField ageField;
    public TextField idField;
    public TextField lengthField;
    public TextField phoneField;
    public TextField bloodTypeField;

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

    }

    public void homeButton_Click(ActionEvent e) throws IOException {
        new slideTransitions().leftToRightTransition(parentContainer, homeButton, anchorPane, "home.fxml");
    }

    public void signOutButton_Click(ActionEvent e) throws IOException {
        new fadeTransitions(parentContainer, "main.fxml");
    }

    public void addPatientButton_Click(ActionEvent actionEvent) throws IOException {
        new slideTransitions().leftToRightTransition(parentContainer, insertDataButton, anchorPane, "addPatient.fxml");
    }

    public void saveButton_Click(ActionEvent actionEvent) {

        if(isCurrentinput()) {
            String[] data = {idField.getText(),firstNameField.getText(), lastNameField.getText(),
                    ageField.getText(), weightField.getText(), lengthField.getText(),
                    phoneField.getText(),bloodTypeField.getText()};
            try {
                ReadWriteXlsx file=new ReadWriteXlsx("PatientList.xlsx");
                file.add(data);
            } catch (IOException | InvalidFormatException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private boolean isCurrentinput() {
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

        }
        else   drop_text.setText("Error");
    }
}
