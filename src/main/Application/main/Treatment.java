package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Treatment implements Initializable {
    @FXML
    private Label result1, result2, result3, result4,result5, result1Desc, result2Desc, result3Desc, result4Desc,result5Desc;
    public Label treatmentText;
    @FXML
    private Pane pane;
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
    Hashtable<String, Integer> values=new Hashtable<>();


    Patient p;

    public void homeButton_Click(ActionEvent actionEvent) throws IOException {
        new FadeTransitions(pane, "Home.fxml");
    }

    public void signOutButton_Click(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("Main.fxml");
    }

    public void returnButton_Click(ActionEvent actionEvent) throws IOException {
        new FadeTransitions(pane, "Questions.fxml");
    }


    public void exportButton_Click(ActionEvent actionEvent) throws IOException, InvalidFormatException {

        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        p = (Patient) stage.getUserData();
        values =p.getValues();
        ReadWriteXlsx readWriteXlsx=new ReadWriteXlsx("output.xlsx");

        readWriteXlsx.add(new String[]{"First name","Last name","Age","weight","length","phone","Boold Type","gender","Is Ethiopian","Is Eastern","WBC","Neut","Lymph","RBC","HCT",
                "Urea","Hb","Crtn","Iron","HDL","AP","Diagnosis","Recommendation"});

        readWriteXlsx.add(new String[]{p.getFirstName(),p.getLastName(),p.getAge()+"",p.getWeight()+"",p.getLength()+"",p.getPhone()+"",p.getBloodType(),p.getGender(),p.getEthiopian()+"",
                p.getEastern()+"",p.getBloodTest().get("WBC")+"",p.getBloodTest().get("Neut")+"",p.getBloodTest().get("Lymph")+"",p.getBloodTest().get("RBC")+"",p.getBloodTest().get("HCT")+"",p.getBloodTest().get("Urea")+"",
                p.getBloodTest().get("Hb")+"",p.getBloodTest().get("Crtn")+"",p.getBloodTest().get("Iron")+"","",p.getBloodTest().get("HDL")+"",p.getBloodTest().get("AP")+""});
        if(values.get("RBC")==1 || values.get("HCT")==-1 || values.get("Hb")==-1 || values.get("HCT")==-1)
            readWriteXlsx.add(new String[]{"","","","","","","","","","","","","","","","","","","","","","אנמיה","שני כדורי 10 מ\"ג של B12 ביום למשך חודש "});
        if(values.get("Urea")==1 || values.get("Urea")==1) {
            readWriteXlsx.add(new String[]{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "Urea", "לתאם פגישה עם תזונאי "});
        treatmentText.setText("Urea                 לתאם פגישה עם תזונאי \n");
        }
        if(values.get("RBC")==-1||values.get("HCT")==-1 || values.get("Hb")==-1||values.get("Iron")==-1) {
            readWriteXlsx.add(new String[]{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "דימום", "להתפנות בדחיפות לבית החולים "});
            treatmentText.setText(treatmentText.getText()+"דימום      להתפנות בדחיפות לבית החולים \n");
        }

     //   if(values.get("HDL")==-1)
       //     readWriteXlsx.add(new String[]{"","","","","","","","","","","","","","","","","","","","","","היפרליפידמיה","לתאם פגישה עם תזונאי, כדור 5 מ\"ג של סימוביל ביום למשך שב"});
        //if(values.get("HDL")==-1)
          //  readWriteXlsx.add(new String[]{"","","","","","","","","","","","","","","","","","","","","","היפרליפידמיה","לתאם פגישה עם תזונאי, כדור 5 מ\"ג של סימוביל ביום למשך שב"});
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

      //  }

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
