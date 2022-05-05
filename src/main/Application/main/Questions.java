package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Questions implements Initializable {
    public Text questionText;
    public Button noButton;
    public Button ignoreButton;
    public Button yes_Button;
    public Button askButton;
    public Button skip_Button;
    @FXML
    private StackPane parentContainer;
    @FXML
    private Button homeButton, submitButton, skipButton, returnButton;
    @FXML
    private AnchorPane anchorPane;

    Hashtable<String, Integer> values=new Hashtable<>();

    //   @FXML
  //  private ChoiceBox<String> question1, question2, question3, question4, question5, question6, question7, question8;

    private String[] question=new String[]{"If there is a fever.?","do you smoke?","do you drink water?","q3","q4","q5"};

    Patient p;
    private int[] answer;
    private Integer[] qnumber;
    int q=0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        fillChoices(question1, question2, question3, question4);
//        fillChoices(question5, question6, question7, question8);

        questionText.setVisible(false);
        yes_Button.setVisible(false);
        noButton.setVisible(false);
        ignoreButton.setVisible(false);

    }

//    private void fillChoices(ChoiceBox<String> question1, ChoiceBox<String> question2, ChoiceBox<String> question3, ChoiceBox<String> question4) {
//        question1.getItems().add("Yes");
//        question1.getItems().add("No");
//        question2.getItems().add("Yes");
//        question2.getItems().add("No");
//        question3.getItems().add("Yes");
//        question3.getItems().add("No");
//        question4.getItems().add("Yes");
//        question4.getItems().add("No");
//    }

    public void homeButton_Click(ActionEvent actionEvent) throws IOException {
        new FadeTransitions(parentContainer, "Home.fxml");
    }

    public void signOutButton_Click(ActionEvent actionEvent) {
        new FadeTransitions(parentContainer, "Main.fxml");
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

    public void yesButton_Click(ActionEvent actionEvent) {
     //   answer[q++]=1;
        values.put(question[qnumber[q++]],1);
        if(q==qnumber.length) {
            Node node = (Node) actionEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setUserData(p);
            new FadeTransitions(parentContainer, "Treatment.fxml");
        }
        else questionText.setText(question[qnumber[q]]);



    }

    public void noButton_Click(ActionEvent actionEvent) {
        //answer[q++]=-1;
        values.put(question[qnumber[q++]],-1);
        if(q==qnumber.length) {
            Node node = (Node) actionEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setUserData(p);
            new FadeTransitions(parentContainer, "Treatment.fxml");
        }
        else questionText.setText(question[qnumber[q]]);
    }

    public void ignorButton_Click(ActionEvent actionEvent) {
        //answer[q++]=0;
        values.put(question[qnumber[q++]],0);
        if(q==qnumber.length) {
            Node node = (Node) actionEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setUserData(p);
            new FadeTransitions(parentContainer, "Treatment.fxml");
        }
        else questionText.setText(question[qnumber[q]]);

    }

    public void askButton_Click(ActionEvent actionEvent) {

        questionText.setVisible(true);
        yes_Button.setVisible(true);
        noButton.setVisible(true);
        ignoreButton.setVisible(true);

        askButton.setVisible(false);
        skip_Button.setVisible(false);

        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        p = (Patient) stage.getUserData();
        values=p.getValues();
       // qnumber=new int[question.length];

        Set<Integer> wq=new HashSet<>();

        if(values.get("WBC")==1)  wq.add(0);
       if(values.get("RBC")==1) wq.add(1);
       if(values.get("HCT")==1) wq.add(1);
       if(values.get("Urea")==1) wq.add(2);

        if(values.get("Urea")==-1) wq.add(3);
        if(values.get("Hb")==-1) wq.add(4);
        qnumber=wq.toArray(new Integer[0]);
      //  qnumber=new int[]{1,3,4};
        if(qnumber.length==0)
            new FadeTransitions(parentContainer, "Treatment.fxml");
        else {
            answer = new int[qnumber.length];
            questionText.setText(question[qnumber[0]]);
        }
    }





}
