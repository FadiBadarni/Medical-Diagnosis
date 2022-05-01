package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class Treatment {
    @FXML
    private StackPane parentContainer;
    @FXML
    private AnchorPane anchorPane;
    public void homeButton_Click(ActionEvent actionEvent) {
        new FadeTransitions(parentContainer, "Home.fxml");
    }

    public void signOutButton_Click(ActionEvent actionEvent) {
        new FadeTransitions(parentContainer, "Main.fxml");
    }
}
