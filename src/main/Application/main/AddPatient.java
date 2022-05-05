package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
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
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
    private static Stage stg;

    @FXML
    StackPane parentContainer;
    @FXML
    AnchorPane anchorPane;
    @FXML
    private Button homeButton, insertDataButton, uploadDataButton, questionsButton, signOutButton, addPatientButton,infoButton;
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
        Main m = new Main();
        m.changeScene("Main.fxml");
    }
    public void addPatientButton_Click(ActionEvent actionEvent) throws IOException {
        new FadeTransitions(parentContainer, "AddPatient.fxml");     }

    public void saveButton_Click(ActionEvent actionEvent) {
        if(isCurrentinput()) {
            String[] data = {idField.getText(),firstNameField.getText(), lastNameField.getText(),
                    ageField.getText(), weightField.getText(), lengthField.getText(),
                    phoneField.getText(),bloodTypeField.getText(),genderBox.getSelectionModel().getSelectedItem()};
            try {
                ReadWriteXlsx file=new ReadWriteXlsx("PatientList.xlsx");
                file.add(data);
                new FadeTransitions(parentContainer, "Home.fxml");
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
            new FadeTransitions(parentContainer, "Home.fxml");
        }
        else   drop_text.setText("Error");
    }

    public void infoButton_Click(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Info.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
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
