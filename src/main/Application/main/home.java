package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class home implements Initializable {
    @FXML
    private Button homeButton, insertDataButton, uploadDataButton, questionsButton, signOutButton;
    @FXML
    private StackPane parentContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void signOutButton_Click(ActionEvent e)throws IOException{
        new fadeTransitions(parentContainer, "main.fxml");
    }
}
