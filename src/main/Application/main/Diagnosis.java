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
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Objects;
import java.util.ResourceBundle;

public class Diagnosis implements Initializable {
    @FXML
    StackPane parentContainer;
    @FXML
    Button goToTreatmentButton, advanceButton, homeButton, viewButton, returnButton, infoButton;
    @FXML
    AnchorPane anchorPane;
    @FXML
    TextField testField1, testField2, testField3, testField4, testField5, testField6, testField7, testField8, testField9, testField10, testField11;
    @FXML
    ListView listView;
    @FXML
    ProgressBar progressBar1, progressBar2, progressBar3, progressBar4, progressBar5, progressBar6, progressBar7, progressBar8, progressBar9, progressBar10, progressBar11;
    private static Stage stg;

    Patient p;
    //  int wbc=0,neut=0,lymph=0,rbc=0,hct=0,urea=0,hp=0,crtn=0,iron=0,hdl=0,ap=0;
    Hashtable<String, Integer> values = new Hashtable<>();

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

//    public void goToTreatmentButton_Click(ActionEvent actionEvent) throws IOException {
//        new FadeTransitions().exitFadeTransition(parentContainer, goToTreatmentButton);
//        Main m = new Main();
//        m.changeScene("Treatment.fxml");
//    }

//    public void goToQuestionsButton_Click(ActionEvent actionEvent) throws IOException {
//        new FadeTransitions().exitFadeTransition(parentContainer, goToTreatmentButton);
//        Main m = new Main();
//        m.changeScene("Questions.fxml");
//    }

    public void returnButton_Click(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("InsertData.fxml");
    }

    public void viewButton_Click(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        p = (Patient) stage.getUserData();

        Hashtable<String, Double> bloodTest = p.getBloodTest();
        String human = returnStage(p.getAge());
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

        if (Objects.equals(human, "Adult")) {
            if (WBC_Level / 11000 < 0.409) WBC_UnderFlow();
            else if (WBC_Level / 11000 > 0.409 && WBC_Level / 11000 < 1) WBC_Normal();
            else {
                WBC_OverFlow();
            }
            progressBar1.setProgress(WBC_Level / 11000);

            setAdultHbLevel(p, Hb_Level);
            setResults(p, WBC_Level, Neut_Level, Lymph_Level, RBC_Level, HCT_Level, Urea_Level, Crtn_Level, Iron_Level, HDL_Level, Ap_Level);


        } else if (Objects.equals(human, "Child")) {
            if (WBC_Level / 15500 < 0.354) {
                WBC_UnderFlow();
            } else if (WBC_Level / 15500 > 0.354 && WBC_Level / 15500 < 1) {
                WBC_Normal();
            } else WBC_OverFlow();

            progressBar1.setProgress(WBC_Level / 15500);
            setChildHbLevel(Hb_Level);
            setResults(p, WBC_Level, Neut_Level, Lymph_Level, RBC_Level, HCT_Level, Urea_Level, Crtn_Level, Iron_Level, HDL_Level, Ap_Level);

        } else if (Objects.equals(human, "Toddler")) {
            if (WBC_Level / 17500 < 0.342) {
                WBC_UnderFlow();
            } else if (WBC_Level / 17500 > 0.342 && WBC_Level / 17500 < 1) WBC_Normal();
            else {
                WBC_OverFlow();
            }
            progressBar1.setProgress(WBC_Level / 17500);
            setChildHbLevel(Hb_Level);
            setResults(p, WBC_Level, Neut_Level, Lymph_Level, RBC_Level, HCT_Level, Urea_Level, Crtn_Level, Iron_Level, HDL_Level, Ap_Level);
        }
        for (TextField textField : Arrays.asList(testField1, testField2, testField3, testField4, testField5, testField6, testField7, testField8, testField9, testField10, testField11)) {
            textField.setEffect(null);
        }
        for (ProgressBar progressBar : Arrays.asList(progressBar1, progressBar2, progressBar3, progressBar4, progressBar5, progressBar6, progressBar7, progressBar8, progressBar9, progressBar10, progressBar11)) {
            progressBar.setEffect(null);
        }

        Button viewbutton = (Button) actionEvent.getSource();
        viewbutton.setVisible(false);
        advanceButton.setDisable(false);
    }


    private void setResults(Patient p, double WBC_Level, double neut_Level, double lymph_Level, double RBC_Level,
                            double HCT_Level, double urea_Level, double crtn_Level, double iron_Level,
                            double HDL_Level, double ap_Level) {
        setNeutLevel(WBC_Level, neut_Level);
        setLymphLevel(WBC_Level, lymph_Level);
        setRbcLevel(RBC_Level);
        setHctLevel(p, HCT_Level);
        setUreaLevel(urea_Level);
        setCreatinineLevel(p, crtn_Level);
        setIronLevel(p, iron_Level);
        setHdlLevel(p, HDL_Level);
        setApLevel(p, ap_Level);
    }

    private void setApLevel(Patient p, double ap_level) {
        if (p.getIsEthiopian() == 1) {
            if (ap_level < 60) {
                progressBar11.setStyle("-fx-accent: yellow;");
                testField11.setText("Low Value");
                values.put("AP", -1);
            } else if (ap_level <= 120) {
                progressBar11.setStyle("-fx-accent: green;");
                testField11.setText("Value within normal range.");
                values.put("AP", 0);
            } else {
                AP_OverFlow();
            }
            progressBar11.setProgress(ap_level / 90);
        } else {
            if (ap_level < 30) {
                progressBar11.setStyle("-fx-accent: yellow;");
                testField11.setText("Low Value");

                values.put("AP", -1);
            } else if (ap_level <= 90) {
                progressBar11.setStyle("-fx-accent: green;");
                testField11.setText("Value within normal range.");
                values.put("AP", 0);
            } else {
                AP_OverFlow();
            }
            progressBar11.setProgress(ap_level / 90);
        }

    }

    private void setHdlLevel(Patient p, double hdl_level) {
        if (p.getIsEthiopian() == 1) {
            if (Objects.equals(p.getGender(), "male") || Objects.equals(p.getGender(), "Male")) {
                if (hdl_level < 29) {
                    progressBar10.setStyle("-fx-accent: yellow;");
                    testField10.setText("Low Value");
                    values.put("HDL", -1);
                } else if (hdl_level <= 74.4) {
                    progressBar10.setStyle("-fx-accent: green;");
                    testField10.setText("Value within normal range.");
                    values.put("HDL", 0);
                } else {
                    HDL_OverFlow();
                }
                progressBar10.setProgress(hdl_level / 62);
            } else if (Objects.equals(p.getGender(), "female") || Objects.equals(p.getGender(), "Female")) {
                if (hdl_level < 34) {
                    progressBar10.setStyle("-fx-accent: yellow;");
                    testField10.setText("Low Value");
                    values.put("HDL", -1);

                } else if (hdl_level <= 98.4) {
                    progressBar10.setStyle("-fx-accent: green;");
                    testField10.setText("Value within normal range.");
                    values.put("HDL", 0);
                } else {
                    HDL_OverFlow();
                }
                progressBar10.setProgress(hdl_level / 82);
            }
        } else if (p.getIsEthiopian() == 0) {
            if (Objects.equals(p.getGender(), "male") || Objects.equals(p.getGender(), "Male")) {
                if (hdl_level < 29) {
                    progressBar10.setStyle("-fx-accent: yellow;");
                    testField10.setText("Low Value");
                    values.put("HDL", -1);
                } else if (hdl_level <= 62) {
                    progressBar10.setStyle("-fx-accent: green;");
                    testField10.setText("Value within normal range.");
                    values.put("HDL", 0);
                } else {
                    HDL_OverFlow();
                }
                progressBar10.setProgress(hdl_level / 62);
            } else if (Objects.equals(p.getGender(), "female") || Objects.equals(p.getGender(), "Female")) {
                if (hdl_level < 34) {
                    progressBar10.setStyle("-fx-accent: yellow;");
                    testField10.setText("Low Value");
                    values.put("HDL", -1);

                } else if (hdl_level <= 82) {
                    progressBar10.setStyle("-fx-accent: green;");
                    testField10.setText("Value within normal range.");
                    values.put("HDL", 0);
                } else {
                    HDL_OverFlow();
                }
                progressBar10.setProgress(hdl_level / 82);
            }
        }


    }

    private void setIronLevel(Patient p, double iron_level) {
        if (Objects.equals(p.getGender(), "male") || Objects.equals(p.getGender(), "Male")) {
            if (iron_level < 60) {
                progressBar9.setStyle("-fx-accent: yellow;");
                testField9.setText("Low Value");
                values.put("Iron", -1);
            } else if (iron_level <= 160) {
                progressBar9.setStyle("-fx-accent: green;");
                testField9.setText("Value within normal range.");
                values.put("Iron", 0);

            } else {
                Iron_OverFlow();
            }
            progressBar9.setProgress(iron_level / 160);
        } else if (Objects.equals(p.getGender(), "female") || Objects.equals(p.getGender(), "Female")) {
            if (iron_level < 48) {
                progressBar9.setStyle("-fx-accent: yellow;");
                testField9.setText("Low Value");
                values.put("Iron", -1);
            } else if (iron_level <= 128) {
                progressBar9.setStyle("-fx-accent: green;");
                testField9.setText("Value within normal range.");
                values.put("Iron", 0);
            } else {
                Iron_OverFlow();
            }
            progressBar9.setProgress(iron_level / 128);
        }
    }

    private void setCreatinineLevel(Patient p, double Crtn_Level) {
        if (p.getAge() < 60) {   //adult
            if (Crtn_Level < 0.6) {
                progressBar8.setStyle("-fx-accent: yellow;");
                testField8.setText("Low Value");
                values.put("Crtn", -1);

            } else if (Crtn_Level <= 1) {
                progressBar8.setStyle("-fx-accent: green;");
                testField8.setText("Value within normal range.");
                values.put("Crtn", 0);
            } else {
                Creatinine_OverFlow();
            }
        } else if (p.getAge() >= 60) {  //old
            if (Crtn_Level < 0.6) {
                progressBar8.setStyle("-fx-accent: yellow;");
                testField8.setText("Low Value");
                values.put("Crtn", -1);
            } else if (Crtn_Level <= 1.2) {
                progressBar8.setStyle("-fx-accent: green;");
                testField8.setText("Value within normal range.");
                values.put("Crtn", 0);
            } else {
                Creatinine_OverFlow();
            }
        } else if (p.getAge() > 2 && p.getAge() < 18) {  //child
            if (Crtn_Level < 0.5) {
                progressBar8.setStyle("-fx-accent: yellow;");
                testField8.setText("Low Value");
                values.put("Crtn", -1);
            } else if (Crtn_Level <= 1) {
                progressBar8.setStyle("-fx-accent: green;");
                testField8.setText("Value within normal range.");
                values.put("Crtn", 0);
            } else {
                Creatinine_OverFlow();
            }
        } else if (p.getAge() > 0 && p.getAge() < 3) {  //toddler
            if (Crtn_Level < 0.2) {
                progressBar8.setStyle("-fx-accent: yellow;");
                testField8.setText("Low Value");
                values.put("Crtn", -1);

            } else if (Crtn_Level <= 0.5) {
                progressBar8.setStyle("-fx-accent: green;");
                testField8.setText("Value within normal range.");
                values.put("Crtn", 0);
            } else {
                Creatinine_OverFlow();
            }
        }
        progressBar8.setProgress(Crtn_Level);
    }

    private void setChildHbLevel(double Hb_Level) {
        if (Hb_Level < 11.5) {
            progressBar7.setStyle("-fx-accent: yellow;");
            testField7.setText("Low Value");
            values.put("Hb", -1);

        } else if (Hb_Level <= 15.5) {
            progressBar7.setStyle("-fx-accent: green;");
            testField7.setText("Value within normal range.");
            values.put("Hb", 0);
        } else {
            HB_OverFlow();
        }
        progressBar7.setProgress(Hb_Level / 15.5);
    }

    private void setAdultHbLevel(Patient p, double Hb_Level) {
        if (Objects.equals(p.getGender(), "male") || Objects.equals(p.getGender(), "Male")) {
            if (Hb_Level < 12) {
                progressBar7.setStyle("-fx-accent: yellow;");
                testField7.setText("Low Value");
                values.put("Hb", -1);
            } else if (Hb_Level <= 18) {
                progressBar7.setStyle("-fx-accent: green;");
                testField7.setText("Value within normal range.");
                values.put("Hb", 0);
            } else {
                HB_OverFlow();
            }
            progressBar7.setProgress(Hb_Level / 18);
        } else if (Objects.equals(p.getGender(), "female") || Objects.equals(p.getGender(), "Female")) {
            if (Hb_Level < 12) {
                progressBar7.setStyle("-fx-accent: yellow;");
                testField7.setText("Low Value");
                values.put("Hb", -1);
            } else if (Hb_Level <= 16) {
                progressBar7.setStyle("-fx-accent: green;");
                testField7.setText("Value within normal range.");
                values.put("Hb", 0);
            } else {
                HB_OverFlow();
            }
            progressBar7.setProgress(Hb_Level / 16);
        }
    }

    private void setUreaLevel(double urea_level) {
        if (urea_level < 17) {
            progressBar6.setStyle("-fx-accent: yellow;");
            testField6.setText("Low Value");
            values.put("Urea", -1);

        } else if (urea_level < 43) {
            progressBar6.setStyle("-fx-accent: green;");
            testField6.setText("Value within normal range.");
            values.put("Urea", 0);
        } else {
            progressBar6.setStyle("-fx-accent: red;");
            testField6.setText("UREA OVERFLOW");
            Tooltip tooltip = new Tooltip();
            tooltip.setText("UREA OVERFLOW");
            tooltip.setShowDelay(Duration.seconds(2));
            tooltip.setHideDelay(Duration.seconds(5));
            testField6.setTooltip(tooltip);
            values.put("Urea", 1);
        }
        progressBar6.setProgress(urea_level / 43);
    }

    private void setHctLevel(Patient p, double HCT_Level) {
        if (Objects.equals(p.getGender(), "male") || Objects.equals(p.getGender(), "Male")) {
            if (HCT_Level < 37) {
                progressBar5.setStyle("-fx-accent: yellow;");
                testField5.setText("Low Value");
                values.put("HCT", -1);

            } else if (HCT_Level <= 54) {
                progressBar5.setStyle("-fx-accent: green;");
                testField5.setText("Value within normal range.");
                values.put("HCT", 0);
            } else {
                HCT_OverFlow();
            }
            progressBar5.setProgress(HCT_Level / 54);
        } else if (Objects.equals(p.getGender(), "female") || Objects.equals(p.getGender(), "Female")) {
            if (HCT_Level < 33) {
                progressBar5.setStyle("-fx-accent: yellow;");
                testField5.setText("Low Value");
                values.put("HCT", -1);
            } else if (HCT_Level <= 47) {
                progressBar5.setStyle("-fx-accent: green;");
                testField5.setText("Value within normal range.");
                values.put("HCT", 0);
            } else {
                HCT_OverFlow();
            }
            progressBar5.setProgress(HCT_Level / 47);
        }
    }

    private void setRbcLevel(double RBC_Level) {
        if (RBC_Level < 4.5) {
            progressBar4.setStyle("-fx-accent: yellow;");
            testField4.setText("Low Value");
            values.put("RBC", -1);

        } else if (RBC_Level <= 6) {
            progressBar4.setStyle("-fx-accent: green;");
            testField4.setText("Value within normal range.");
            values.put("RBC", 0);
        } else {
            progressBar4.setStyle("-fx-accent: red;");
            testField4.setText("RBC OVERFLOW");
            Tooltip tooltip = new Tooltip();
            tooltip.setText("RBC OVERFLOW");
            tooltip.setShowDelay(Duration.seconds(2));
            tooltip.setHideDelay(Duration.seconds(5));
            testField4.setTooltip(tooltip);
            values.put("RBC", 1);
        }
        progressBar4.setProgress(RBC_Level / 6);
    }

    private void setLymphLevel(double wbc_level, double lymph_level) {
        if (lymph_level < 36) {
            progressBar3.setStyle("-fx-accent: yellow;");
            testField3.setText("Low Level");
            values.put("Lymph", -1);

        } else if (lymph_level >= 36 && lymph_level <= 52) {
            progressBar3.setStyle("-fx-accent: green;");
            testField3.setText("Value within normal range.");
            values.put("Lymph", 0);
        } else {
            progressBar3.setStyle("-fx-accent: red;");
            testField3.setText(" LYMPH OVERFLOW");
            Tooltip tooltip = new Tooltip();
            tooltip.setText("LYMPH OVERFLOW");
            tooltip.setShowDelay(Duration.seconds(2));
            tooltip.setHideDelay(Duration.seconds(5));
            testField3.setTooltip(tooltip);
            values.put("Lymph", 1);
        }
        progressBar3.setProgress(lymph_level / 52);
    }

    private void setNeutLevel(double WBC_Level, double Neut_Level) {
        if (Neut_Level < 28) {
            progressBar2.setStyle("-fx-accent: yellow;");
            testField2.setText("Low Level");
            values.put("Neut", -1);

        } else if (Neut_Level >= 28 && Neut_Level <= 54) {
            progressBar2.setStyle("-fx-accent: green;");
            testField2.setText("Value within normal range.");
            values.put("Neut", 0);
        } else {
            progressBar2.setStyle("-fx-accent: red;");
            testField2.setText(" NEUT OVERFLOW");
            Tooltip tooltip = new Tooltip();
            tooltip.setText("NEUT OVERFLOW");
            tooltip.setShowDelay(Duration.seconds(2));
            tooltip.setHideDelay(Duration.seconds(5));
            testField2.setTooltip(tooltip);
            values.put("Neut", 1);

        }
        progressBar2.setProgress(Neut_Level / 54);
    }

    private void WBC_OverFlow() {
        progressBar1.setStyle("-fx-accent: red;");
        testField1.setText("Most often indicate the presence of an infection if there is a fever. " +
                "In other cases, very rare, may indicate blood disease or cancer.");
        Tooltip tooltip = new Tooltip();
        tooltip.setText("Most often indicate the presence of an infection if there is a fever. " +
                "In other cases, very rare, may indicate blood disease or cancer.");
        tooltip.setShowDelay(Duration.seconds(2));
        tooltip.setHideDelay(Duration.seconds(5));
        testField1.setTooltip(tooltip);
        values.put("WBC", 1);

    }

    private void WBC_Normal() {
        progressBar1.setStyle("-fx-accent: green;");
        testField1.setText("Value within normal range.");
        values.put("WBC", 0);

    }

    private void WBC_UnderFlow() {
        progressBar1.setStyle("-fx-accent: yellow;");
        testField1.setText("Indicate viral disease. Immune system failure and in very rare cases cancer.");
        values.put("WBC", -1);
    }

    private void HCT_OverFlow() {
        progressBar5.setStyle("-fx-accent: red;");
        testField5.setText("HCT OVERFLOW");
        Tooltip tooltip = new Tooltip();
        tooltip.setText("HCT OVERFLOW");
        tooltip.setShowDelay(Duration.seconds(2));
        tooltip.setHideDelay(Duration.seconds(5));
        testField5.setTooltip(tooltip);
        values.put("HCT", 1);

    }

    private void AP_OverFlow() {
        progressBar11.setStyle("-fx-accent: red;");
        testField11.setText("AP OVERFLOW");
        Tooltip tooltip = new Tooltip();
        tooltip.setText("AP OVERFLOW");
        tooltip.setShowDelay(Duration.seconds(2));
        tooltip.setHideDelay(Duration.seconds(5));
        testField11.setTooltip(tooltip);
        values.put("AP", 1);
    }

    private void HDL_OverFlow() {
        progressBar10.setStyle("-fx-accent: red;");
        testField10.setText("HDL OVERFLOW");
        Tooltip tooltip = new Tooltip();
        tooltip.setText("HDL OVERFLOW");
        tooltip.setShowDelay(Duration.seconds(2));
        tooltip.setHideDelay(Duration.seconds(5));
        testField10.setTooltip(tooltip);
        values.put("HDL", 0);

    }

    private void Creatinine_OverFlow() {
        progressBar8.setStyle("-fx-accent: red;");
        testField8.setText("CREATININE OVERFLOW");
        Tooltip tooltip = new Tooltip();
        tooltip.setText("CREATININE OVERFLOW");
        tooltip.setShowDelay(Duration.seconds(2));
        tooltip.setHideDelay(Duration.seconds(5));
        testField8.setTooltip(tooltip);
        values.put("Crtn", 1);

    }

    private void Iron_OverFlow() {
        progressBar9.setStyle("-fx-accent: red;");
        testField9.setText("IRON OVERFLOW");
        Tooltip tooltip = new Tooltip();
        tooltip.setText("IRON OVERFLOW");
        tooltip.setShowDelay(Duration.seconds(2));
        tooltip.setHideDelay(Duration.seconds(5));
        testField9.setTooltip(tooltip);
        values.put("Iron", 1);

    }

    private void HB_OverFlow() {
        progressBar7.setStyle("-fx-accent: red;");
        testField7.setText("HEMOGLOBIN OVERFLOW");
        Tooltip tooltip = new Tooltip();
        tooltip.setText("HEMOGLOBIN OVERFLOW");
        tooltip.setShowDelay(Duration.seconds(2));
        tooltip.setHideDelay(Duration.seconds(5));
        testField7.setTooltip(tooltip);

        values.put("Hb", 0);
    }

    public String returnStage(int age) {
        if (age > 0 && age < 3) {
            return "Toddler";
        } else if (age >= 3 && age <= 17) {
            return "Child";
        } else {
            return "Adult";
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
