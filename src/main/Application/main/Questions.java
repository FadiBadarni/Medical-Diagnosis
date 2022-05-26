package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Questions implements Initializable {
    public Text questionText;
    public Button noButton, ignoreButton, yes_Button, askButton;
    public Button infoButton;
    @FXML
    private Pane parentContainer;
    @FXML
    private Button homeButton, submitButton, returnButton;
    @FXML
    private AnchorPane anchorPane;
    Hashtable<String, Integer> values = new Hashtable<>();
    private static Stage stg;
    public static Set<Integer> questionsAsked = new HashSet<>();
    private final String[] question = new String[]
            {
                    "Is Your Temperature Average ? [~ 37°C]",
                    "Are You A Smoker ?",
                    "Do You Drink Enough Water ?", //
                    "Any Existing History With Lung Diseases ?",
                    "Are You Currently Pregnant ?",
                    "Do You Currently Have Diarrhea ?",
                    "Did You Vomit Recently ?",
                    "Do You Do A Lot Of Physical Activity ?",
                    "Is Your Meat Intake Regular ?",
                    "Any Previous History With Muscle Diseases ?"
            };

    Patient p;

    private Integer[] qnumber;
    int q = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        questionText.setVisible(false);
        yes_Button.setVisible(false);
        noButton.setVisible(false);
        ignoreButton.setVisible(false);
    }

    public void homeButton_Click(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("Home.fxml");
    }

    public void signOutButton_Click(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("Main.fxml");
    }

    public void returnButton_Click(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("Diagnosis.fxml");
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

    public void yesButton_Click(ActionEvent actionEvent) {
        values.put(question[qnumber[q++]], 1);
        if (q == qnumber.length) {goTo(actionEvent);
        } else questionText.setText(question[qnumber[q]]);
    }

    public void goTo(ActionEvent actionEvent)
    {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setUserData(p);
        Main m = new Main();
        try {
            m.changeScene("Treatment.fxml");
        } catch (IOException e) {
            questionText.setText(e.getMessage());
        }
    }
    public void noButton_Click(ActionEvent actionEvent) {
        values.put(question[qnumber[q++]], -1);
        if (q == qnumber.length) {goTo(actionEvent);
        } else questionText.setText(question[qnumber[q]]);
    }

    public void ignoreButton_Click(ActionEvent actionEvent) {
        values.put(question[qnumber[q++]], 0);
        if (q == qnumber.length) {goTo(actionEvent);
        } else questionText.setText(question[qnumber[q]]);
    }

    public void askButton_Click(ActionEvent actionEvent) throws IOException {
        questionText.setVisible(true);
        yes_Button.setVisible(true);
        noButton.setVisible(true);
        ignoreButton.setVisible(true);
        askButton.setVisible(false);
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        p = (Patient) stage.getUserData();
        values = p.getValues();
        values.put("Is Your Temperature Average ? [~ 37°C]",0);
        values.put("Are You A Smoker ?",0);
        values.put("Do You Drink Enough Water ?",0);
        values.put("Any Existing History With Lung Diseases ?",0);
        values.put("Are You Currently Pregnant ?",0);
        values.put("Do You Currently Have Diarrhea ?",0);
        values.put("Do You Do A Lot Of Physical Activity ?",0);
        values.put("Is Your Meat Intake Regular ?",0);
        values.put("Any Previous History With Muscle Diseases ?",0);
        values.put("Did You Vomit Recently ?",0);


        if (values.get("WBC") == 1) {
            questionsAsked.add(0);  //temperature
        }
        if (values.get("RBC") == 1 && values.get("HCT") == 1) {
            questionsAsked.add(1);  //smoker
            questionsAsked.add(3);  //lung disease
        }
        if (values.get("HCT") == 1) {
            questionsAsked.add(1);  //smoker
        }
        if (values.get("Urea") == 1) {
            questionsAsked.add(2); //water
        }
        if (values.get("Crtn") == 1) {
            questionsAsked.add(5); //diarrhea
            questionsAsked.add(6); //muscle disease
            questionsAsked.add(8); //meat
            questionsAsked.add(9); //meat
        }
        if (values.get("Iron") == -1 || values.get("Urea") == -1) {
            if (Objects.equals(p.getGender(), "Female")) {
                questionsAsked.add(4);  //pregnancy question
            }
        }
        if (values.get("HDL") == 1) {
            questionsAsked.add(7); //physical activity
        }

        qnumber = questionsAsked.toArray(new Integer[0]);
        if (qnumber.length == 0) {
            Main m = new Main();
            m.changeScene("Treatment.fxml");
        }
        else {
            questionText.setText(question[qnumber[0]]);
        }
    }
}
