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
    public Button noButton, ignoreButton, yes_Button, askButton, skipButton;
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
                    "Do You Do A Lot Of Physical Activity ?"
            };

    Patient p;
    private int[] answer;
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
        new FadeTransitions(parentContainer, "Home.fxml");
    }

    public void signOutButton_Click(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("Main.fxml");
    }

    public void submitButton_Click(ActionEvent actionEvent) throws IOException {
        //TODO :SAVE QUESTION ANSWERS AND TAKE THEM INTO CONSIDERATION
        new FadeTransitions(parentContainer, "Treatment.fxml");
    }

    public void skipButton_Click(ActionEvent actionEvent) throws IOException {
        new FadeTransitions(parentContainer, "Treatment.fxml");
    }


    public void returnButton_Click(ActionEvent actionEvent) throws IOException {
        new FadeTransitions(parentContainer, "Diagnosis.fxml");
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
        //   answer[q++]=1;
        values.put(question[qnumber[q++]], 1);
        if (q == qnumber.length) {
            Node node = (Node) actionEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setUserData(p);
            new FadeTransitions(parentContainer, "Treatment.fxml");
        } else questionText.setText(question[qnumber[q]]);


    }

    public void noButton_Click(ActionEvent actionEvent) {
        //answer[q++]=-1;
        values.put(question[qnumber[q++]], -1);
        if (q == qnumber.length) {
            Node node = (Node) actionEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setUserData(p);
            new FadeTransitions(parentContainer, "Treatment.fxml");
        } else questionText.setText(question[qnumber[q]]);
    }

    public void ignoreButton_Click(ActionEvent actionEvent) {
        //answer[q++]=0;
        values.put(question[qnumber[q++]], 0);
        if (q == qnumber.length) {
            Node node = (Node) actionEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setUserData(p);
            new FadeTransitions(parentContainer, "Treatment.fxml");
        } else questionText.setText(question[qnumber[q]]);

    }

    public void askButton_Click(ActionEvent actionEvent) {
        questionText.setVisible(true);
        yes_Button.setVisible(true);
        noButton.setVisible(true);
        ignoreButton.setVisible(true);
        askButton.setVisible(false);
        skipButton.setVisible(false);
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        p = (Patient) stage.getUserData();
        values = p.getValues();

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
            questionsAsked.add(6); //diarrhea
            questionsAsked.add(7); //vomit
        }
        if (values.get("Iron") == -1 || values.get("Urea") == -1) {
            if (Objects.equals(p.getGender(), "Female")) {
                questionsAsked.add(4);  //pregnancy question
            }
        }
        if (values.get("HDL") == 1) {
            questionsAsked.add(8); //physical activity
        }

        qnumber = questionsAsked.toArray(new Integer[0]);
        if (qnumber.length == 0)
            new FadeTransitions(parentContainer, "Treatment.fxml");
        else {
            answer = new int[qnumber.length];
            questionText.setText(question[qnumber[0]]);
        }
    }
    public static Set<Integer> getQuestionsAsked() {
        return questionsAsked;
    }
    public void setQuestionsAsked(Set<Integer> questionsAsked) {
        Questions.questionsAsked = questionsAsked;
    }
}
