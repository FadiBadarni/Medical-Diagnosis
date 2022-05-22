package main;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.Iterator;

public class Patient {
    private int id, age, weight, length, phone;
    private String firstName, lastName, gender;
    private Hashtable<String, Double> bloodTest;
    private int isEthiopian = 0, isEastern = 0;
    private Hashtable<String, Integer> values;

    public Patient(int id, String firstName, String lastName, int age, int weight, int lenght, int phone,  String gender, int iseast, int isethiopian) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.weight = weight;
        this.length = lenght;
        this.phone = phone;
        this.gender = gender;
        this.isEthiopian = isethiopian;
        this.isEastern = iseast;
        this.bloodTest = new Hashtable<>();
        this.values = new Hashtable<>();
    }

    public Patient(Patient p) {
        this.id = p.getId();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.age = p.getAge();
        this.weight = p.getWeight();
        this.length = p.getLength();
        this.phone = p.getPhone();
        this.bloodTest = new Hashtable<>(p.getBloodTest());
    }

    public void addBloodTest(Hashtable<String, Double> bloodTest) {
        this.bloodTest = new Hashtable<>(bloodTest);
    }

    public String addBloodTest(String path) {
        bloodTest.clear();
        String key = null;
        double value = -1;
        try {
            FileInputStream file = new FileInputStream(new File(path));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
                XSSFRow cell = sheet.getRow(0);
                if (cell.getCell(i).getCellType() == CellType.STRING)
                    key = (cell.getCell(i).getStringCellValue());
                else return "Error in column name :"+i;
                if (sheet.getRow(1).getCell(i).getCellType() == CellType.NUMERIC)
                    value = (sheet.getRow(1).getCell(i).getNumericCellValue());
                else  return "Error in column value :"+i;
                if(value<0) return "the value must to be more then 0 in colum:"+i ;
                if (key != null && value != 0) bloodTest.put(key, value);
                else return "Error int colum:" +i;
            }
            file.close();
        } catch (Exception e) {
            return "Error";
        }
        return "ok";
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

    public Hashtable<String, Integer> getValues() {
        return values;
    }

    public void setValues(Hashtable<String, Integer> values) {
        this.values = values;
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

    public int getIsEthiopian() {
        return isEthiopian;
    }

    public void setIsEthiopian(int isEthiopian) {
        this.isEthiopian = isEthiopian;
    }

    public int getIsEastern() {
        return isEastern;
    }

    public void setIsEastern(int isEastern) {
        this.isEastern = isEastern;
    }

}
