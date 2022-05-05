package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Treatment implements Initializable {
    @FXML
    private Label result1, result2, result3, result4,result5, result1Desc, result2Desc, result3Desc, result4Desc,result5Desc;
    @FXML
    private StackPane parentContainer;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button homeButton, signOutButton, returnButton, exportButton, infoButton,showButton;
    private static Stage stg;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showButton.setVisible(false);
        result1.setVisible(false);
        result2.setVisible(false);
        result3.setVisible(false);
        result4.setVisible(false);
        result5.setVisible(false);
        result1Desc.setVisible(false);
        result2Desc.setVisible(false);
        result3Desc.setVisible(false);
        result4Desc.setVisible(false);
        result5Desc.setVisible(false);
    }
    public void homeButton_Click(ActionEvent actionEvent) throws IOException {
        new FadeTransitions(parentContainer, "Home.fxml");
    }

    public void signOutButton_Click(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("Main.fxml");
    }

    public void returnButton_Click(ActionEvent actionEvent) throws IOException {
        new FadeTransitions(parentContainer, "Questions.fxml");
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

    public void infoButton_Click(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Info.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
}
