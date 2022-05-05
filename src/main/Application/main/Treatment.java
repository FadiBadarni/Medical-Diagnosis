package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.*;

public class Treatment {
    public Label treatmentText;
    @FXML
    private StackPane parentContainer;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button homeButton, signOutButton, returnButton;
    Hashtable<String, Integer> values=new Hashtable<>();


    Patient p;

    public void homeButton_Click(ActionEvent actionEvent) throws IOException {
        new FadeTransitions(parentContainer, "Home.fxml");
    }

    public void signOutButton_Click(ActionEvent actionEvent) {
        new FadeTransitions(parentContainer, "Main.fxml");
    }

    public void returnButton_Click(ActionEvent actionEvent) throws IOException {
        new FadeTransitions(parentContainer, "Questions.fxml");
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
}
