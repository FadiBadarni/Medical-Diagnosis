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
       if(isCurrentinput()) {
           String[] data = {id.getText(),password.getText(),firstname.getText(), lastname.getText(),
                   age.getText(), weight.getText(), length.getText(), email.getText()};
           try {
               ReadWriteXlsx file=new ReadWriteXlsx("Users.xlsx");
               file.add(data);
           } catch (IOException | InvalidFormatException e) {
               throw new RuntimeException(e);
           }
       }
    }



    public boolean isCurrentinput(){
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
