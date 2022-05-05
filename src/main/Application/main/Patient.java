package main;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.Iterator;

public class Patient {

    private int id, age, weight, length, phone;
    private String firstName, lastName, bloodType, gender;
    private Hashtable<String, Double> bloodTest;
    private int isEthiopian = 0, isEastern = 0;

    public Patient(int id, String firstName, String lastName, int age, int weight, int lenght, int phone, String blood, String gender,int iseast, int isethiopian) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.weight = weight;
        this.length = lenght;
        this.phone = phone;
        this.bloodType = blood;
        this.gender = gender;
        this.isEthiopian=isethiopian;
        this.isEastern=iseast;
        this.bloodTest = new Hashtable<>();
    }


    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Patient(Patient p) {
        this.id = p.getId();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.age = p.getAge();
        this.weight = p.getWeight();
        this.length = p.getLength();
        this.bloodType = p.getBloodType();
        this.phone = p.getPhone();
        this.bloodTest = new Hashtable<>(p.getBloodTest());
    }

    public void addBloodTest(Hashtable<String, Double> bloodTest) {
        this.bloodTest = new Hashtable<>(bloodTest);
    }

    public void addBloodTest(String path) {
        bloodTest.clear();
        String key = null;
        int value = 0;
        try {
            FileInputStream file = new FileInputStream(new File(path));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Iterator<Cell> cellIterator = row.cellIterator();
                Cell cell = cellIterator.next();
                if (cell.getCellType() == CellType.STRING) key = cell.getStringCellValue();
                cell = cellIterator.next();
                if (cell.getCellType() == CellType.NUMERIC) value = (int) cell.getNumericCellValue();
                if (key != null && value != 0) bloodTest.put(key, (double) value);
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Hashtable<String, Double> getBloodTest() {
        return bloodTest;
    }

    public void setBloodTest(Hashtable<String, Double> bloodTest) {
        this.bloodTest = bloodTest;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public int getPhone() {
        return phone;
    }

    public int getEthiopian() {
        return isEthiopian;
    }

    public void setEthiopian(int ethiopian) {
        isEthiopian = ethiopian;
    }

    public int getEastern() {
        return isEastern;
    }

    public void setEastern(int eastern) {
        isEastern = eastern;
    }
}
