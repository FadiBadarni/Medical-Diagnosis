package main;

import animatefx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.w3c.dom.events.MouseEvent;
import org.apache.poi.ss.usermodel.CellType;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Objects;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    private Circle circle1;
    @FXML
    private Button loginButton, registerButton, returnButton;
    @FXML
    private Label wrongLogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Pane parentContainer;
    @FXML
    private AnchorPane anchorPane1, anchorPane2;
    @FXML
    private Label newLabel;
    private static Stage stg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new RotationAnimation(circle1, true, 360, 10);
        AnimationFX animateRegister = new Shake(registerButton);
        animateRegister.setDelay(Duration.seconds(5));
        animateRegister.play();

    }
    public void userLogin(ActionEvent e) throws IOException {
        validateLogin();
    }

    private void validateLogin() throws IOException {
        Main m = new Main();
        if (isCorrectPassword(username.getText(), password.getText())) {
            wrongLogin.setText("Success");
            m.changeScene("Home.fxml");
        } else if (username.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogin.setText("Missing Credentials");
        } else {
            wrongLogin.setText("Wrong Credentials");
        }
    }
    public void returnButton_Click(ActionEvent e) throws IOException {
        Main m = new Main();
        m.changeScene("Main.fxml");

    }
    public void registerButton_Click(ActionEvent e) throws IOException {
        Main m = new Main();
        m.changeScene("Register.fxml");
    }

    public boolean isCorrectPassword(String username, String password) {
        try {
            ReadWriteXlsx file = new ReadWriteXlsx("Users.xlsx");
            Iterator<Cell> cellIterator = file.getAllRow(username,5);
            if (cellIterator != null && cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                return cell.getStringCellValue().equals(password);
            }
        } catch (IOException | InvalidFormatException ex) {
            return false;
        }
        return false;
    }

    public void panePressed(javafx.scene.input.MouseEvent mouseEvent) {
        stg = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Delta.x = stg.getX() - mouseEvent.getScreenX();
        Delta.y = stg.getY() - mouseEvent.getScreenY();
    }
    public void paneDragged(javafx.scene.input.MouseEvent mouseEvent) {
        stg = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stg.setX(Delta.x + mouseEvent.getScreenX());
        stg.setY(Delta.y + mouseEvent.getScreenY());
    }
}

