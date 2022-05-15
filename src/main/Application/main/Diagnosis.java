package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Diagnosis implements Initializable {
    @FXML
   private StackPane parentContainer;
    @FXML
    private Button goToTreatmentButton, advanceButton, homeButton, viewButton, returnButton, infoButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField testField1, testField2, testField3, testField4, testField5, testField6, testField7, testField8, testField9, testField10, testField11;
    @FXML
    private ProgressBar progressBar1, progressBar2, progressBar3, progressBar4, progressBar5, progressBar6, progressBar7, progressBar8, progressBar9, progressBar10, progressBar11;
    private static Stage stg;
    private Patient p;
    private Hashtable<String, Integer> values = new Hashtable<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Effect frostEffect =
                new BoxBlur(24, 30, 2);
        for (TextField textField : Arrays.asList(testField1, testField2, testField3, testField4, testField5, testField6, testField7, testField8, testField9, testField10, testField11)) {
            textField.setEffect(frostEffect);
        }
        for (ProgressBar progressBar : Arrays.asList(progressBar1, progressBar2, progressBar3, progressBar4, progressBar5, progressBar6, progressBar7, progressBar8, progressBar9, progressBar10, progressBar11)) {
            progressBar.setEffect(frostEffect);
        }
        parentContainer.setOpacity(1);
        advanceButton.setDisable(true);
    }
    public void homeButton_Click(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("Home.fxml");
    }
    public void signOutButton_Click(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("Main.fxml");
    }
    public void advanceButton_Click(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        p.setValues(values);
        stage.setUserData(p);
        Main m = new Main();
        m.changeScene("Questions.fxml");
    }
    public void returnButton_Click(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("InsertData.fxml");
    }
    public void viewButton_Click(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        p = (Patient) stage.getUserData();
        Hashtable<String, Double> bloodTest = p.getBloodTest();

        double WBC_Level = bloodTest.get("WBC");
        double Neut_Level = bloodTest.get("Neut");
        double Lymph_Level = bloodTest.get("Lymph");
        double RBC_Level = bloodTest.get("RBC");
        double HCT_Level = bloodTest.get("HCT");
        double Urea_Level = bloodTest.get("Urea");
        double Hb_Level = bloodTest.get("Hb");
        double Crtn_Level = bloodTest.get("Crtn");
        double Iron_Level = bloodTest.get("Iron");
        double HDL_Level = bloodTest.get("HDL");
        double Ap_Level = bloodTest.get("AP");

       values= setResults(p, WBC_Level, Neut_Level, Lymph_Level, RBC_Level, HCT_Level, Urea_Level, Crtn_Level, Iron_Level, HDL_Level, Ap_Level,Hb_Level);
        updateGui( WBC_Level, Neut_Level, Lymph_Level, RBC_Level, HCT_Level, Urea_Level, Crtn_Level, Iron_Level, HDL_Level, Ap_Level,Hb_Level);
        for (TextField textField : Arrays.asList(testField1, testField2, testField3, testField4, testField5, testField6, testField7, testField8, testField9, testField10, testField11))
            textField.setEffect(null);

        for (ProgressBar progressBar : Arrays.asList(progressBar1, progressBar2, progressBar3, progressBar4, progressBar5, progressBar6, progressBar7, progressBar8, progressBar9, progressBar10, progressBar11))
            progressBar.setEffect(null);

        Button viewbutton = (Button) actionEvent.getSource();
        viewbutton.setVisible(false);
        advanceButton.setDisable(false);
    }
    public Hashtable<String, Integer>   setResults(Patient p, double WBC_Level, double neut_Level, double lymph_Level, double RBC_Level,
                                           double HCT_Level, double urea_Level, double crtn_Level, double iron_Level,
                                           double HDL_Level, double ap_Level, double Hb_Level) {
        Hashtable<String, Integer> values =new Hashtable<>();
       values.put("WBC",setWBCLevel(p,WBC_Level));
       values.put("Hb",setHbLevel(p,Hb_Level));
       values.put("AP", setApLevel(p, ap_Level));
       values.put("HDL",setHdlLevel(p, HDL_Level));
        values.put("Iron",setIronLevel(p, iron_Level));
        values.put("Crtn", setCreatinineLevel(p, crtn_Level));
        values.put("Urea", setUreaLevel(p,urea_Level));
        values.put("HCT",setHctLevel(p, HCT_Level));
        values.put("RBC",setRbcLevel(RBC_Level));
        values.put("Lymph",setLymphLevel(lymph_Level));
        values.put("Neut",setNeutLevel(neut_Level));
        return values;
    }
    public int setWBCLevel(Patient p,double WBC_Level) {
        if (Objects.equals(returnStage(p.getAge()), "Adult")) {
            if (WBC_Level < 4500) return -1;
            else if (WBC_Level >=4500 && WBC_Level<=11000) return 0;
            else return 1;
        } else if (Objects.equals(returnStage(p.getAge()), "Child")) {
            if (WBC_Level <5500) return -1;
            else if (WBC_Level >=5500 && WBC_Level <=15500) return 0;
            else return 1;
        } else {
            if (WBC_Level < 6000 ) return -1;
            else if (WBC_Level >=6000 && WBC_Level <=17500)return 0;
            else return 1;
        }
    }
    public int setHbLevel(Patient p,double Hb_Level) {
        if((Objects.equals(returnStage(p.getAge()), "Adult"))) {
            if (Objects.equals(p.getGender(), "male") || Objects.equals(p.getGender(), "Male")) {
                if (Hb_Level < 12) return -1;
                else if (Hb_Level <= 18)  return 0;
                else return 1;
            } else {
                if (Hb_Level < 12) return -1;
                else if (Hb_Level <= 16) return 0;
                else return 1;
            }
        }
        else {
            if (Hb_Level < 11.5) return -1;
            else if (Hb_Level <= 15.5) return 0;
            else return 1;
        }
    }
    public int setApLevel(Patient p, double ap_level) {
        if (p.getEastern() == 1) {
            if (ap_level < 60) return -1;
             else if (ap_level <= 120) return 0;
             else return 1;
        } else {
            if (ap_level < 30)  return -1;
            else if (ap_level <= 90) return 0;
            else return 1;
        }
    }
    public int setHdlLevel(Patient p, double hdl_level) {
        if (p.getIsEthiopian() == 1) {
            if (Objects.equals(p.getGender(), "male") || Objects.equals(p.getGender(), "Male")) {
                if (hdl_level < 29) return -1;
                else if (hdl_level <= 74.4) return 0;
                else return 1;
            } else {
                if (hdl_level < 34) return -1;
                else if (hdl_level <= 98.4) return 0;
                else return 1;
            }
        } else
            if (Objects.equals(p.getGender(), "male") || Objects.equals(p.getGender(), "Male")) {
                if (hdl_level < 29) return -1;
                 else if (hdl_level <= 62) return 0;
                else return 1;
            } else {
                if (hdl_level < 34) return -1;
                else if (hdl_level <= 82) return 0;
                else return 1;
            }
    }
    public int setIronLevel(Patient p, double iron_level) {
        if (Objects.equals(p.getGender(), "male") || Objects.equals(p.getGender(), "Male")) {
            if (iron_level < 60) return -1;
            else if (iron_level <= 160) return 0;
            else return 1;
        } else {
            if (iron_level < 48) return -1;
             else if (iron_level <= 128) return 0;
            else return 1;
        }
    }
    public int setCreatinineLevel(Patient p, double Crtn_Level) {
        if (p.getAge() < 60 && p.getAge()>17) {   //adult
            if (Crtn_Level < 0.6) return -1;
            else if (Crtn_Level <= 1) return 0;
            else return 1;
        } else if (p.getAge() >= 60) {  //old
            if (Crtn_Level < 0.6) return -1;
            else if (Crtn_Level <= 1.2) return 0;
            else return 1;
        } else if (p.getAge() > 2 && p.getAge() < 18) {  //child
            if (Crtn_Level < 0.5) return -1;
            else if (Crtn_Level <= 1) return 0;
            else return 1;
        } else {  //toddler
            if (Crtn_Level < 0.2) return -1;
            else if (Crtn_Level <= 0.5) return 0;
            else return 1;
        }
    }
    public int setUreaLevel(Patient p,double urea_level) {
        if(p.getIsEastern()==0) {
            if (urea_level < 17) return -1;
            else if (urea_level <= 43) return 0;
            return 1;
        }
        else   if (urea_level < 18.7) return -1;
        else if (urea_level <= 47.3) return 0;
        return 1;
    }
    public int setHctLevel(Patient p, double HCT_Level) {
        if (Objects.equals(p.getGender(), "male") || Objects.equals(p.getGender(), "Male")) {
            if (HCT_Level < 37) return -1;
            else if (HCT_Level <= 54) return 0;
             else return 1;
        } else  {
            if (HCT_Level < 33) return -1;
            else if (HCT_Level <= 47) return 0;
             else return 1;
        }
    }
    public int setRbcLevel(double RBC_Level) {
        if (RBC_Level < 4.5) return -1;
        else if (RBC_Level <= 6) return 0;
        return 1;
    }
    public int setLymphLevel(double lymph_level) {
        if (lymph_level < 36) return -1;
        else if (lymph_level >= 36 && lymph_level <= 52) return 0;
        return 1;
    }
    public int setNeutLevel(double Neut_Level) {
        if (Neut_Level < 28) return -1;
         else if (Neut_Level >= 28 && Neut_Level <= 54) return 0;
         return 1;
    }
    public void updateGui(double WBC_Level,double neut_Level, double lymph_Level, double RBC_Level, double urea_Level, double hb_Level, double crtn_Level, double ap_Level, double HDL_Level, double iron_Level, double HCT_Level){

        progressBar2.setProgress(neut_Level / 54);
        progressBar3.setProgress(lymph_Level / 52);
        progressBar4.setProgress(RBC_Level / 6);
        progressBar6.setProgress(urea_Level / 43);
        progressBar7.setProgress(hb_Level / 15.5);
        progressBar8.setProgress(crtn_Level);
        progressBar11.setProgress(ap_Level / 90);
        if(Objects.equals(p.getGender(), "female") || Objects.equals(p.getGender(), "Female")) {
            progressBar10.setProgress(HDL_Level / 82);
            progressBar9.setProgress(iron_Level / 128);
            progressBar5.setProgress(HCT_Level / 47);
        }
        else
        {
            progressBar10.setProgress(HDL_Level / 62);
            progressBar9.setProgress(iron_Level / 160);
            progressBar5.setProgress(HCT_Level / 54);
        }

        if(Objects.equals(returnStage(p.getAge()), "Adult"))  progressBar1.setProgress(WBC_Level / 11000);
        else if(Objects.equals(returnStage(p.getAge()), "Child"))  progressBar1.setProgress(WBC_Level / 15500);
        else progressBar1.setProgress(WBC_Level / 17500);

        if(values.get("AP")==-1) lowValue(progressBar11,testField11);
        if(values.get("AP")==0) normalValue(progressBar11,testField11);
        if(values.get("AP")==1) highValue(progressBar11,testField11);

        if(values.get("HDL")==-1) lowValue(progressBar10,testField10);
        if(values.get("HDL")==0) normalValue(progressBar10,testField10);
        if(values.get("HDL")==1) highValue(progressBar10,testField10);

        if(values.get("Iron")==-1) lowValue(progressBar9,testField9);
        if(values.get("Iron")==0) normalValue(progressBar9,testField9);
        if(values.get("Iron")==1) highValue(progressBar9,testField9);

        if(values.get("Crtn")==-1) lowValue(progressBar8,testField8);
        if(values.get("Crtn")==0) normalValue(progressBar8,testField8);
        if(values.get("Crtn")==1) highValue(progressBar8,testField8);

        if(values.get("Hb")==-1) lowValue(progressBar7,testField7);
        if(values.get("Hb")==0) normalValue(progressBar7,testField7);
        if(values.get("Hb")==1) highValue(progressBar7,testField7);

        if(values.get("Urea")==-1) lowValue(progressBar6,testField6);
        if(values.get("Urea")==0) normalValue(progressBar6,testField6);
        if(values.get("Urea")==1) highValue(progressBar6,testField6);

        if(values.get("HCT")==-1) lowValue(progressBar5,testField5);
        if(values.get("HCT")==0) normalValue(progressBar5,testField5);
        if(values.get("HCT")==1) highValue(progressBar5,testField5);

        if(values.get("RBC")==-1) lowValue(progressBar4,testField4);
        if(values.get("RBC")==0) normalValue(progressBar4,testField4);
        if(values.get("RBC")==1) highValue(progressBar4,testField4);

        if(values.get("Lymph")==-1) lowValue(progressBar3,testField3);
        if(values.get("Lymph")==0) normalValue(progressBar3,testField3);
        if(values.get("Lymph")==1) highValue(progressBar3,testField3);

        if(values.get("Neut")==-1) lowValue(progressBar2,testField2);
        if(values.get("Neut")==0) normalValue(progressBar2,testField2);
        if(values.get("Neut")==1) highValue(progressBar2,testField2);

        if(values.get("WBC")==-1) lowValue(progressBar1,testField1);
        if(values.get("WBC")==0)normalValue(progressBar1,testField1);
        if(values.get("WBC")==1) highValue(progressBar1,testField1);
    }
    private void lowValue(ProgressBar progressBar, TextField testField) {
        progressBar.setStyle("-fx-accent: yellow;");
        testField.setText("Low Value");
    }
    private void normalValue(ProgressBar progressBar, TextField testField) {
        progressBar.setStyle("-fx-accent: green;");
        testField.setText("Value within normal range.");
    }
    private void highValue(ProgressBar progressBar, TextField testField) {
        progressBar.setStyle("-fx-accent: red;");
        testField.setText("Value OverFlow");
    }
    public String returnStage(int age) {
        if (age > 0 && age <= 3) {
            return "Toddler";
        } else if (age > 3 && age <= 17) {
            return "Child";
        } else {return "Adult";
        }
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
