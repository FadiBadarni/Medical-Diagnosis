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

    private int id;
    private String firstName;
    private String lastName;

    private int age;
    private int weight;
    private int length;
    private String email;

    private Hashtable<String, Integer> bloodTest;



    public Patient(int id, String firstName, String lastName, int age, int weight, int lenght, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.weight = weight;
        this.length = lenght;
        this.email = email;
        this.bloodTest =new Hashtable<>();
    }

    public Patient(Patient p) {

        this.id = p.getId();
        this.firstName =p.getFirstName();
        this.lastName =p.getLastName();
        this.age = p.getAge();
        this.weight = p.getWeight();
        this.length =p.getLength();
        this.email = p.getEmail();
        this.bloodTest =new Hashtable<>(p.getBloodTest());
    }

    public void addBloodTest(Hashtable<String,Integer> bloodTest)
    {
        this.bloodTest=new Hashtable<>(bloodTest);
    }

    public void addBloodTest(String path)
    {
        bloodTest.clear();
        String key = null;
        int value = 0;
        try
        {
            FileInputStream file = new FileInputStream(new File(path));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                Cell cell = cellIterator.next();

                if(cell.getCellType()== CellType.STRING)
                    key=cell.getStringCellValue();

                cell = cellIterator.next();
                if(cell.getCellType()==CellType.NUMERIC) NUMERIC:
                        value= (int) cell.getNumericCellValue();

                if(key!=null && value!=0)
                    bloodTest.put(key,value);
            }
            file.close();
        }
        catch (Exception e)
        {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Hashtable<String, Integer> getBloodTest() {
        return bloodTest;
    }

    public void setBloodTest(Hashtable<String, Integer> bloodTest) {
        this.bloodTest = bloodTest;
    }
}
