package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
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
    private StackPane parentContainer;
    @FXML
    private AnchorPane anchorPane1, anchorPane2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new RotationAnimation(circle1, true, 360, 10);
    }

    public void userLogin(ActionEvent e) throws IOException {
        validateLogin();
    }

    private void validateLogin() throws IOException {
        Main m = new Main();
        if (isCorrectPassword(username.getText().toString(), password.getText().toString())) {
            wrongLogin.setText("Success");
            m.changeScene("Home.fxml");
        } else if (username.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogin.setText("Missing Credentials");
        } else {
            wrongLogin.setText("Wrong Credentials");
        }
    }

    public void userRegister(ActionEvent e) throws IOException {

    }

    public void returnButton_Click(ActionEvent e) throws IOException {
        SlideTransitions transition = new SlideTransitions();
        transition.rightToLeftTransition(parentContainer, returnButton, anchorPane1, anchorPane2);
    }

    public void registerButton_Click(ActionEvent e) throws IOException {
        new FadeTransitions(parentContainer, "Register.fxml");
    }


    public boolean isCorrectPassword(String username, String password)
    {
        try {
            ReadWriteXlsx file = new ReadWriteXlsx("Users.xlsx");
            Iterator<Cell>  cellIterator= file.getAllRow(username);
            if(cellIterator!=null && cellIterator.hasNext())
            return cellIterator.next().getStringCellValue().equals(password);
            else return false;

        } catch (IOException | InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }

}
