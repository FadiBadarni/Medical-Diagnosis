package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import org.apache.poi.ss.usermodel.Cell;

import java.util.Iterator;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddPatient implements Initializable {
    public TextField lastNameField, weightField, firstNameField, ageField, idField, lengthField, phoneField;
    public CheckBox eastCheckBox, ethiopianCheckBox;
    public ChoiceBox<String> genderBox;
    private static Stage stg;
    @FXML
    StackPane parentContainer;
    @FXML
    AnchorPane anchorPane;
    @FXML
    private Button homeButton, insertDataButton, uploadDataButton, questionsButton, signOutButton, addPatientButton, infoButton;
    @FXML
    private Pane pane;
    private String path;
    @FXML
    private Text drop_text;
    @FXML
    private Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderBox.getItems().add("Male");
        genderBox.getItems().add("Female");
    }

    public void homeButton_Click(ActionEvent e) throws IOException {
        Main m = new Main();
        m.changeScene("Home.fxml");
    }

    public void signOutButton_Click(ActionEvent e) throws IOException {
        Main m = new Main();
        m.changeScene("Main.fxml");
    }

    public void addPatientButton_Click(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("AddPatient.fxml");
    }

    public void saveButton_Click(ActionEvent actionEvent) {
        if (isCurrentInput()) {
            String[] data = {idField.getText(), firstNameField.getText(), lastNameField.getText(),
                    ageField.getText(), weightField.getText(), lengthField.getText(),
                    phoneField.getText(), genderBox.getSelectionModel().getSelectedItem(), (eastCheckBox.isSelected() ? 1 : 0) + "", (ethiopianCheckBox.isSelected() ? 1 : 0) + ""};
            try {
                ReadWriteXlsx file = new ReadWriteXlsx("PatientList.xlsx");
                file.add(data);
                Main m = new Main();
                m.changeScene("Home.fxml");
            } catch (IOException | InvalidFormatException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean isCurrentInput() {
        firstNameField.setStyle(null);
        lastNameField.setStyle(null);
        idField.setStyle(null);
        ageField.setStyle(null);
        weightField.setStyle(null);
        lengthField.setStyle(null);
        phoneField.setStyle(null);
        genderBox.setStyle(null);
        if (firstNameField.getText().length() == 0) {
            firstNameField.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
            new animatefx.animation.RubberBand(firstNameField).play();
            errorLabel.setText("First name field is empty");
            return false;
        }
        if (lastNameField.getText().length() == 0) {
            lastNameField.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
            new animatefx.animation.RubberBand(lastNameField).play();
            errorLabel.setText("Last name field is empty");
            return false;
        }

        if (idField.getText().length() != 9) {
            idField.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
            new animatefx.animation.RubberBand(idField).play();
            errorLabel.setText("ID is not within required length");
            return false;
        }
            try {
                int number = Integer.parseInt(idField.getText());
                ReadWriteXlsx file = new ReadWriteXlsx("PatientList.xlsx");
                Iterator<Cell> cellIterator = file.getAllRow(number, 0);
                if (cellIterator != null) {
                    idField.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
                    new animatefx.animation.RubberBand(idField).play();
                    errorLabel.setText("the id is exist");
                    return false;
                }
            }catch (IOException |InvalidFormatException  e)
            {
                idField.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
                new animatefx.animation.RubberBand(idField).play();
                errorLabel.setText("the id must to be number");
                return false;
            }


            try {
                    if (ageField.getText().length() == 0)
                    {
                        ageField.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
                        new animatefx.animation.RubberBand(ageField).play();
                        errorLabel.setText("the age is empty");
                        return false;
                    }
                    int age = Integer.parseInt(ageField.getText());
                   if(age<1 || age>120) {
                       ageField.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
                       new animatefx.animation.RubberBand(ageField).play();
                       errorLabel.setText("the age must to be from 0 to 120 year");
                       return false;
                   }

                }catch (NumberFormatException e)
                {
                    ageField.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
                    new animatefx.animation.RubberBand(ageField).play();
                    errorLabel.setText("the age must to be number");
                    return false;
                }
            try {
                if (weightField.getText().length() == 0)
                {
                    weightField.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
                    new animatefx.animation.RubberBand(weightField).play();
                    errorLabel.setText("the weight is empty");
                    return false;
                }
                int  weight= Integer.parseInt(weightField.getText());
                if(weight<10 || weight>300) {
                    weightField.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
                    new animatefx.animation.RubberBand(weightField).play();
                    errorLabel.setText("the weight must to be from 10 to 300kg");
                    return false;
                }

            }catch (NumberFormatException ex)
            {
                weightField.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
                new animatefx.animation.RubberBand(weightField).play();
                errorLabel.setText("the weight must to be number");
                return false;
            }
            try {
                if (lengthField.getText()==null ||lengthField.getText().length()==0)
                {
                    lengthField.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
                    new animatefx.animation.RubberBand(lengthField).play();
                    errorLabel.setText("the length is empty");
                    return false;
                }
                int  length= Integer.parseInt(lengthField.getText());
                if(length<50 || length>300) {
                    lengthField.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
                    new animatefx.animation.RubberBand(lengthField).play();
                    errorLabel.setText("the length must to be from 50 to 300cm ");
                    return false;
                }

            }catch (NumberFormatException ex)
            {
                lengthField.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
                new animatefx.animation.RubberBand(lengthField).play();
                errorLabel.setText("the length must to be number");
                return false;
            }
            try {
                if (phoneField.getText().length() == 0)
                {
                    phoneField.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
                    new animatefx.animation.RubberBand(phoneField).play();
                    errorLabel.setText("the phone is empty");
                    return false;
                }

                if(phoneField.getText().length()!=10) {
                    phoneField.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
                    new animatefx.animation.RubberBand(phoneField).play();
                    errorLabel.setText("the phone must to be 10 number and start from 0 ");
                    return false;
                }
                int  phone= Integer.parseInt(phoneField.getText());
                if(phone>999999999||phone<99999999)
                {
                    phoneField.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
                    new animatefx.animation.RubberBand(phoneField).play();
                    errorLabel.setText("the phone must to be 10 number and start from 0 ");
                    return false;
                }
            }catch (NumberFormatException ex)
            {
                phoneField.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
                new animatefx.animation.RubberBand(phoneField).play();
                errorLabel.setText("the length must to be 10 number");
                return false;
            }
           String s=genderBox.getValue();
            if (s==null || s.length()==0) {
                genderBox.setStyle("-fx-border-color: red ; -fx-border-width: 2px");
                new animatefx.animation.RubberBand(genderBox).play();
                errorLabel.setText("Choose a gender");
                return false;
            }
        return true;  //ADDED
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
        if (path != null) {
            ReadWriteXlsx readWriteXlsx = new ReadWriteXlsx("PatientList.xlsx");
            readWriteXlsx.copy(path);
            Main m = new Main();
            m.changeScene("Home.fxml");
        } else drop_text.setText("Error");
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

