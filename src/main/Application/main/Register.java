package main;

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
        String input = isCurrentInput(id.getText(), password.getText(), firstname.getText(), lastname.getText(), email.getText(), username.getText(), repasswoed.getText());
        if (input.equals("true")) {
            String[] data = {id.getText(), password.getText(), firstname.getText(), lastname.getText(), email.getText(), username.getText()};
            try {
                ReadWriteXlsx file = new ReadWriteXlsx("Users.xlsx");
                file.add(data);
                Main m = new Main();
                m.changeScene("Home.fxml");
            } catch (IOException | InvalidFormatException e) {
                throw new RuntimeException(e);
            }
        } else errorLabel.setText(input);
    }

    public String isCurrentInput(String id, String passwordText, String firstnameText, String lastnameText, String emailText, String usernameText, String rePasswordText) {
        try {
            int number = Integer.parseInt(id);
            ReadWriteXlsx file = new ReadWriteXlsx("Users.xlsx");
            Iterator<Cell> cellIterator = file.getAllRow(number, 0);
            if (cellIterator != null) {
                return "The id is used";
            }
            if (firstnameText.length() == 0 || lastnameText.length() == 0) {
                return "You must to add Firstname and Lastname";
            }
            if (file.found(5, usernameText)) {
                return "USERNAME IS USED";
            }
        } catch (NumberFormatException | IOException | InvalidFormatException e) {
            return "ID Must be a valid number";
        }
        if (id.length() != 9) {
            return "ID is not within required length";
        }
        if (!passwordText.equals(rePasswordText)) {
            return "Passwords Do Not Match";
        }
        if (passwordText.length() < 8 || passwordText.length() > 10) {
            return "Password is not within required length";
        }
        int letters = 0, numbers = 0, specialChars = 0;
        for (char c : passwordText.toCharArray()) {
            if (Character.isLetter(c))
                letters++;
            else if (Character.isDigit(c))
                numbers++;
            else
                specialChars++;
        }
        if (letters < 1 || numbers < 1 || specialChars < 1) {
            return "Password Does Not Meat Minimum Requirements.";
        }
        if (usernameText.length() < 6 || usernameText.length() > 9) {
            return "Username is not within required length";
        }
        if (!Pattern.matches("^[a-zA-Z0-9]+$", usernameText)) {
            return "Only alphanumeric characters are allowed.";
        }
        numbers = 0;
        for (char c : usernameText.toCharArray()) {
            if (Character.isDigit(c))
                numbers++;
        }
        if (numbers > 2) {
            return "A Maximum of 2 digits is allowed in usernames.";
        }
        if (!emailText.contains("@")) {
            return "Please Enter A Valid Email Address";
        }
        return "true";
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

    public Label getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(Label errorLabel) {
        this.errorLabel = errorLabel;
    }
}
