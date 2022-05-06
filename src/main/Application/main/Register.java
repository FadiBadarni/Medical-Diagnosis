package main;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Register implements Initializable {
    private static Stage stg;
    @FXML
    private Button returnButton;
    @FXML
    private Pane parentContainer;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Circle circle1,circle2;
    @FXML
    private Rectangle rectangle1,rectangle2;

    @FXML
    private TextField firstname,lastname, id,age,weight,length,email;

    @FXML
    private PasswordField password,repasswoed;
    public TextField phoneNumber;
    public Button registerButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        parentContainer.setOpacity(0);
//        makeFadeTransition();
        new RotationAnimation(circle1, true, 360, 20);
        new RotationAnimation(circle2, true, 360, 20);
        new RotationAnimation(rectangle1);
        new RotationAnimation(rectangle2);

    }

    private void makeFadeTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(1));
        fadeTransition.setNode(parentContainer);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    public void returnButton_Click(ActionEvent e) throws IOException {
        Main m = new Main();
        m.changeScene("Main.fxml");
    }



    public void  register(){
        if(isCurrentInput()) {
            String[] data = {id.getText(),password.getText(),firstname.getText(), lastname.getText(), email.getText(),phoneNumber.getText()};
            try {
                ReadWriteXlsx file=new ReadWriteXlsx("Users.xlsx");
                file.add(data);
                new FadeTransitions(parentContainer, "Home.fxml");

            } catch (IOException | InvalidFormatException e) {
                throw new RuntimeException(e);
            }

        }
        else registerButton.setText("Error");
    }



    public boolean isCurrentInput(){
        if(id.getText().length()!=9) return false;

        try {
            int number = Integer.parseInt(id.getText());
            ReadWriteXlsx file = new ReadWriteXlsx("Users.xlsx");
            Iterator<Cell>  cellIterator= file.getAllRow(number);
            if(cellIterator!=null) return false;
        } catch (NumberFormatException |IOException | InvalidFormatException e) {
            throw new RuntimeException(e);
        }
        if(!password.getText().equals(repasswoed.getText())) return false;

        if(phoneNumber.getText().length()!=10) return false;

        if(email.getText().length()<5) return false;
        if(password.getText().length()<8) return false;

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
