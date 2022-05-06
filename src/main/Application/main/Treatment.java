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
import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.util.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Treatment implements Initializable {
    @FXML
    private Label result1, result2, result3, result4, result5, result1Desc, result2Desc, result3Desc, result4Desc, result5Desc;
    public Label treatmentText;
    @FXML
    private Pane pane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button homeButton, signOutButton, returnButton, exportButton, infoButton, showButton;
    private static Stage stg;
    Hashtable<String, Integer> values = new Hashtable<>();
    Set<Integer> indicator = new HashSet<>(Questions.questionsAsked);
    Patient p;

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
        values = p.getValues();
        ReadWriteXlsx readWriteXlsx = new ReadWriteXlsx("output.xlsx");

        readWriteXlsx.add(new String[]{"First name", "Last name", "Age", "weight", "length", "phone", "Blood Type", "gender", "Is Ethiopian", "Is Eastern", "WBC", "Neut", "Lymph", "RBC", "HCT",
                "Urea", "Hb", "Crtn", "Iron", "HDL", "AP", "Diagnosis", "Treatment"});

        readWriteXlsx.add(new String[]{
                p.getFirstName(),
                p.getLastName(),
                p.getAge() + "",
                p.getWeight() + "",
                p.getLength() + "",
                p.getPhone() + "",
                p.getBloodType(),
                p.getGender(),
                p.getEthiopian() + "",
                p.getEastern() + "",
                p.getBloodTest().get("WBC") + "",
                p.getBloodTest().get("Neut") + "",
                p.getBloodTest().get("Lymph") + "",
                p.getBloodTest().get("RBC") + "",
                p.getBloodTest().get("HCT") + "",
                p.getBloodTest().get("Urea") + "",
                p.getBloodTest().get("Hb") + "",
                p.getBloodTest().get("Crtn") + "",
                p.getBloodTest().get("Iron") + "",
                p.getBloodTest().get("HDL") + "",
                p.getBloodTest().get("AP") + ""});

        if (values.get("WBC") == -1) {
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Viral Disease",
                            "Rest at home."
                    });
            if (values.get("Do You Drink Enough Water ?") == 1) {
                readWriteXlsx.add(new String[]
                        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                                "Water Dehydration",
                                "Drink More Water."
                        });
            }
        }
        if (values.get("WBC") == 1) {
            //TODO: Check if the person smokes or have a previous history with lung diseases.
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Disruption of blood / blood cell formation",
                            "10 MG pill of B12 a day for a month\n" +
                                    "5 MG pill of folic acid a day for a month"});
        }
        if (values.get("Neut") == -1) {
            //TODO: in very rare cases this points to cancerous process.
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Disruption of blood / blood cell formation",
                            "10 MG pill of B12 a day for a month\n" +
                                    "5 MG pill of folic acid a day for a month"});
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Tendency to bacterial infections",
                            "Dedicated Antibiotics"
                    });
        }
        if (values.get("Neut") == 1) {
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Infection",
                            "Dedicated Antibiotics"
                    });
        }
        if (values.get("Lymph") == -1) {
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Disruption of blood / blood cell formation",
                            "10 MG pill of B12 a day for a month\n" +
                                    "5 MG pill of folic acid a day for a month"
                    });
        }
        if (values.get("Lymph") == 1) {
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Prolonged Bacterial Infection",
                            "Dedicated Antibiotics"
                    });
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Lymphoma Cancer",
                            "Entrectinib"
                    });
        }
        if (values.get("RBC") == -1) {
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Anemia",
                            "Two 10 MG B12 pills a day for a month."
                    });
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Bleeding",
                            "To be rushed to the hospital urgently."
                    });
        }
        if (values.get("RBC") == 1) {
            readWriteXlsx.add(new String[]
                    //TODO: Check if the person has a fever. +
                    // Check if the person smokes or have a previous history with lung diseases.
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Infection",
                            "Dedicated Antibiotics"
                    });
        }
        if (values.get("HCT") == -1) {
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Anemia",
                            "Two 10 MG B12 pills a day for a month."
                    });
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Bleeding",
                            "To be rushed to the hospital urgently."
                    });
        }
        if (values.get("HCT") == 1) {
            //TODO: Check if the person smokes.
        }

        if (values.get("Urea") == -1) {
            //TODO: Check if the person is pregnant.
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Malnutrition",
                            "Schedule an appointment with a nutritionist."
                    });
            int test = indicator.iterator().next();  //2 questions were asked (2 & 5) i received question numbers and questions answers (0,1)
            //treatmentText.setText("Urea                 לתאם פגישה עם תזונאי \n");
        }
        if (values.get("Urea") == 1) {
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Kidney diseases",
                            "Balance blood sugar levels."
                    });
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Malnutrition - A high-protein diet",
                            "Schedule an appointment with a nutritionist."
                    });
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Dehydration",
                            "Complete rest while lying down, increase fluids intake by drinking water more."
                    });
        }
        if (values.get("Hb") == -1) {
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Anemia",
                            "Two 10 MG B12 pills a day for a month."
                    });
        }
        if (values.get("Crtn") == -1) {
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Muscle diseases - Poor Muscle Mass",
                            "Two 5 MG pills of Altman C3 turmeric a day for a month."
                    });
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Malnutrition - A low-protein diet",
                            "Schedule an appointment with a nutritionist."
                    });
        }
        if (values.get("Crtn") == 1) {
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Malnutrition - Increased consumption of meat",
                            "Schedule an appointment with a nutritionist."
                    });
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Muscle diseases",
                            "Two 5 MG pills of Altman C3 turmeric a day for a month."
                    });
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Kidney diseases",
                            "Balance blood sugar levels."
                    });
        }
        if (values.get("Iron") == -1) {
            //TODO: check if person is pregnant which will explain increased consumption of iron.
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Malnutrition - Inadequate nutrition",
                            "Schedule an appointment with a nutritionist."
                    });
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Blood Loss",
                            "To be rushed to the hospital urgently."
                    });
        }
        if (values.get("Iron") == 1) {
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Iron poisoning",
                            "To be rushed to the hospital urgently."
                    });
        }
        if (values.get("HDL") == -1) {
            //TODO:
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Heart Diseases",
                            "Schedule an appointment with a nutritionist."
                    });
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Hyperlipidemia",
                            "Schedule an appointment with a nutritionist. A 5 MG pill of Simobil daily for a week."
                    });
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Adult Diabetes",
                            "Insulin adjustment for the patient."
                    });
        }
        if (values.get("HDL") == 1) {
            //Harmless
        }
        if (values.get("AP") == -1) {
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Malnutrition - A low-protein diet",
                            "Schedule an appointment with a nutritionist." +
                                    ""});
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Vitamin deficiency",
                            "Referral for a blood test to identify the missing vitamins."
                    });
        }
        if (values.get("AP") == 1) {
            //TODO: Check if the person is pregnant.
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Liver disease",
                            "Referral to a specific diagnosis for the purpose of determining treatment."
                    });
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Diseases of the biliary tract",
                            "Referral to surgical treatment."
                    });
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Overactive thyroid gland",
                            "Propylthiouracil To reduce thyroid activity."
                    });
            readWriteXlsx.add(new String[]
                    {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "Use of various medications",
                            "Referral to a family doctor for a match between medications."
                    });
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
