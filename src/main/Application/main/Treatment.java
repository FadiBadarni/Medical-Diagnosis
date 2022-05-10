package main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import javafx.stage.Stage;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
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
    Patient p;
    Hashtable<String, String> data = new Hashtable<>();
    @FXML
    private ListView<String> listView;

    boolean isdo=true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        m.changeScene("Questions.fxml");
    }

    public void exportButton_Click(ActionEvent actionEvent) {


        String path = null;
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory != null) path = selectedDirectory.getAbsolutePath();


        String s = path + "\\output.xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook();
        workbook.createSheet("Java Books");

        boolean isExist = true;
        int i = 1;
        while (isExist) {
            try (FileOutputStream outputStream = new FileOutputStream(s)) {
                workbook.write(outputStream);
                isExist = false;
            } catch (IOException e) {
                s = path + "\\output" + i + ".xlsx";
                i++;
            }
        }


        try {
            ReadWriteXlsx readWriteXlsx = new ReadWriteXlsx(s);
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
                    p.getBloodTest().get("AP") + ""}, data);

        } catch (IOException | InvalidFormatException e) {
            treatmentText.setText("Error");
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

    public void mouseMoved(MouseEvent mouseEvent) {
        if(isdo) {
            Node node = (Node) mouseEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            p = (Patient) stage.getUserData();
            values = p.getValues();
          data=  analysis(values);
            listView.getItems().clear();
            listView.getItems().addAll(data.keySet());
            listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent arg0) {
                    treatmentText.setText(data.get(listView.getSelectionModel().getSelectedItem()));
                }
            });
            isdo=false;
        }
    }

    public   Hashtable<String, String> analysis( Hashtable<String, Integer> values ) {
        Hashtable<String, String> data=new Hashtable<>();

        if (values.get("WBC") == -1) {
            data.put("Viral Disease",
                    "Rest at home.");
            if (values.get("Do You Drink Enough Water ?") == -1) {
                data.put("Water Dehydration",
                        "Drink More Water.");
            }
        }
        if (values.get("WBC") == 1) {
            data.put("Disruption of blood / blood cell formation", "10 MG pill of B12 a day for a month\n" +
                    "5 MG pill of folic acid a day for a month");
            if (values.get("Is Your Temperature Average ? [~ 37°C]") == 1) {
                data.put("Infection", "Dedicated Antibiotics");
            }
            //TODO: if over 100k cancer
        }
        if (values.get("Neut") == -1) {
            //TODO: in very rare cases this points to cancerous process.
            data.put("Disruption of blood / blood cell formation", "10 MG pill of B12 a day for a month\n" +
                    "5 MG pill of folic acid a day for a month");
            data.put("Tendency to bacterial infections", "Dedicated Antibiotics");
        }
        if (values.get("Neut") == 1) {
            data.put("Infection", "Dedicated Antibiotics");
        }
        if (values.get("Lymph") == -1) {
            data.put("Disruption of blood / blood cell formation",
                    "10 MG pill of B12 a day for a month\n" +
                            "5 MG pill of folic acid a day for a month");
        }
        if (values.get("Lymph") == 1) {
            data.put("Prolonged Bacterial Infection",
                    "Dedicated Antibiotics");
            data.put("Lymphoma Cancer",
                    "Entrectinib");
        }
        if (values.get("RBC") == -1) {
            data.put("Anemia",
                    "Two 10 MG B12 pills a day for a month.");
            data.put("Bleeding",
                    "To be rushed to the hospital urgently.");
        }
        if (values.get("RBC") == 1) {
            data.put("Infection",
                    "Dedicated Antibiotics");
            if (values.get("Are You A Smoker ?") == 1) {
                data.put("Smoker",
                        "Quit Smoking");
            }
            if (values.get("Any Existing History With Lung Diseases ?") == 1) {
                data.put("Lung Diseases",
                        "Stop smoking / Refer to an X-ray of the lungs");
            }
        }
        if (values.get("HCT") == -1) {
            data.put("Anemia",
                    "Two 10 MG B12 pills a day for a month.");
            data.put("Bleeding",
                    "To be rushed to the hospital urgently.");
        }
        if (values.get("HCT") == 1) {
            if (values.get("Are You A Smoker ?") == 1) {
                data.put("Smoker",
                        "Quit Smoking");
            }
        }
        if (values.get("Urea") == -1) {
            data.put("Malnutrition",
                    "Schedule an appointment with a nutritionist.");
            if (values.get("Are You Currently Pregnant ?") == 1) {
                data.put("Pregnancy",
                        "During Pregnancy The Urea Level Decreases.");
            }
        }
        if (values.get("Urea") == 1) {
            data.put("Kidney diseases",
                    "Balance blood sugar levels.");
            data.put("Malnutrition - A high-protein diet",
                    "Schedule an appointment with a nutritionist.");
            data.put("Dehydration",
                    "Complete rest while lying down, increase fluids intake by drinking water more.");
        }
        if (values.get("Hb") == -1) {
            data.put("Anemia",
                    "Two 10 MG B12 pills a day for a month.");
        }
        if (values.get("Crtn") == -1) {
            data.put("Muscle diseases - Poor Muscle Mass",
                    "Two 5 MG pills of Altman C3 turmeric a day for a month.");
            data.put("Malnutrition - A low-protein diet",
                    "Schedule an appointment with a nutritionist.");
        }
        if (values.get("Crtn") == 1) {
            data.put("Kidney diseases",
                    "Balance blood sugar levels.");
            if (values.get("Do You Currently Have Diarrhea ?") == 1) {
                data.put("Diarrhea",
                        "Diarrhea Cause");
            }
            if (values.get("Any Previous History With Muscle Diseases ?") == 1) {
                data.put("Muscle diseases",
                        "Two 5 MG pills of Altman C3 turmeric a day for a month.");
            }
            if (values.get("Did You Vomit Recently ?") == 1) {
                data.put("Vomit",
                        "Vomit Cause");
            }
            if (values.get("Is Your Meat Intake Regular ?") == 1) {
                data.put("Increased consumption of meat",
                        "Schedule an appointment with a nutritionist.");
            }
        }
        if (values.get("Iron") == -1) {
            if (values.get("Are You Currently Pregnant ?") == 1) {
                data.put("Pregnancy",
                        "During Pregnancy The Iron Level Decreases.");
            }
            data.put("Malnutrition - Inadequate nutrition",
                    "Schedule an appointment with a nutritionist.");
            data.put("Blood Loss",
                    "To be rushed to the hospital urgently.");
        }
        if (values.get("Iron") == 1) {
            data.put("Iron poisoning",
                    "To be rushed to the hospital urgently.");
        }
        if (values.get("HDL") == -1) {
            data.put("Heart Diseases",
                    "Schedule an appointment with a nutritionist.");
            data.put("Hyperlipidemia",
                    "Schedule an appointment with a nutritionist. A 5 MG pill of Simobil daily for a week.");
            data.put("Adult Diabetes",
                    "Insulin adjustment for the patient.");
        }
        if (values.get("HDL") == 1) {
            data.put("Harmless",
                    "");
        }
        if (values.get("AP") == -1) {
            data.put("Malnutrition - A low-protein diet",
                    "Schedule an appointment with a nutritionist." +
                            "");
            data.put("Vitamin deficiency",
                    "Referral for a blood test to identify the missing vitamins.");
        }
        if (values.get("AP") == 1) {
            if (values.get("Are You Currently Pregnant ?") == 1) {
                data.put("Pregnancy",
                        "During Pregnancy The AP Level Increases.");
            }
            data.put("Liver disease",
                    "Referral to a specific diagnosis for the purpose of determining treatment.");
            data.put("Diseases of the biliary tract",
                    "Referral to surgical treatment.");
            data.put("Overactive thyroid gland",
                    "Propylthiouracil To reduce thyroid activity.");
            data.put("Use of various medications",
                    "Referral to a family doctor for a match between medications.");

        }
        return data;
    }
}