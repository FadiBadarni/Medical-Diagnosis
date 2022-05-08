package main;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

public class Register implements Initializable {
    private static Stage stg;
    @FXML
    private Button returnButton;
    @FXML
    private Pane parentContainer;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Circle circle1, circle2;
    @FXML
    private Rectangle rectangle1, rectangle2;

    @FXML
    private TextField firstname, lastname, id, age, weight, length, email;

    @FXML
    private PasswordField password, repasswoed;
    public TextField username;
    public Button registerButton;
    @FXML
    private Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new RotationAnimation(circle1, true, 360, 20);
        new RotationAnimation(circle2, true, 360, 20);
        new RotationAnimation(rectangle1);
        new RotationAnimation(rectangle2);

    }

    public void returnButton_Click(ActionEvent e) throws IOException {
        Main m = new Main();
        m.changeScene("Main.fxml");
    }


    public void register() {
        if (isCurrentInput()) {
            String[] data = {id.getText(), password.getText(), firstname.getText(), lastname.getText(), email.getText(), username.getText()};
            try {
                ReadWriteXlsx file = new ReadWriteXlsx("Users.xlsx");
                file.add(data);
                Main m = new Main();
                m.changeScene("Home.fxml");

            } catch (IOException | InvalidFormatException e) {
                throw new RuntimeException(e);
            }

        }
    }


    public boolean isCurrentInput() {
        try {
            int number = Integer.parseInt(id.getText());
            ReadWriteXlsx file = new ReadWriteXlsx("Users.xlsx");
            Iterator<Cell> cellIterator = file.getAllRow(number);
            if (cellIterator != null) return false;
        } catch (NumberFormatException | IOException | InvalidFormatException e) {
            errorLabel.setText("ID Must be a valid number");
            return false;
        }
        if (id.getText().length() != 9) {
            errorLabel.setText("ID is not within required length");
            return false;
        }
        if (!password.getText().equals(repasswoed.getText())) {
            errorLabel.setText("Passwords Do Not Match");
            return false;
        }
        if (password.getText().length() >= 8) {
            errorLabel.setText("Password is not within required length");
            return false;
        }
        if (!(password.getText().contains("!") || password.getText().contains("@") || password.getText().contains("#") ||
                password.getText().contains("$") || password.getText().contains("%") || password.getText().contains("^") ||
                password.getText().contains("&") || password.getText().contains("*") || password.getText().contains("(") ||
                password.getText().contains(")") || password.getText().contains("-") || password.getText().contains("+"))) {
            errorLabel.setText("Add At least one special character to your password");
            return false;
        }
        if (username.getText().length() < 6 || username.getText().length() > 10) {
            errorLabel.setText("Username is not within required length");
            return false;
        }
        if (!Pattern.matches("[a-zA-Z]+", username.getText())) {
            errorLabel.setText("Only english letters are allowed");
            return false;
        }
        if (!Pattern.matches("(?=(.*\\d){2})", username.getText())) {
            errorLabel.setText("A Minimum of 2 numbers is required in the username");
            return false;
        }
        if (!email.getText().contains("@")) {
            errorLabel.setText("Please Enter A Valid Email Address");
            return false;
        }
        return true;
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
