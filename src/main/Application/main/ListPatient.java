package main;

import java.io.FileInputStream;
import java.util.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ListPatient {
    private ArrayList<Patient> patients;

    public ListPatient() {
        patients = new ArrayList<>();
    }

    public void insertData(String path) {
        int id = 0;
        String firstName = null;
        String lastName = null;
        String gender = null;
        int iseast = 0;
        int isethiopian = 0;
        int age = 0;
        int weight = 0;
        int length = 0;
        int phone = 0;
        String bloodType = null;
        try {
            FileInputStream file = new FileInputStream(new File(path));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (cell.getCellType() == CellType.NUMERIC)
                        id = (int) cell.getNumericCellValue();
                    cell = cellIterator.next();
                    if (cell.getCellType() == CellType.STRING)
                        firstName = cell.getStringCellValue();
                    cell = cellIterator.next();
                    if (cell.getCellType() == CellType.STRING)
                        lastName = cell.getStringCellValue();
                    cell = cellIterator.next();
                    if (cell.getCellType() == CellType.NUMERIC)
                        age = (int) cell.getNumericCellValue();
                    cell = cellIterator.next();
                    if (cell.getCellType() == CellType.NUMERIC)
                        weight = (int) cell.getNumericCellValue();
                    cell = cellIterator.next();
                    if (cell.getCellType() == CellType.NUMERIC)
                        length = (int) cell.getNumericCellValue();
                    cell = cellIterator.next();
                    if (cell.getCellType() == CellType.NUMERIC)
                        phone = (int) cell.getNumericCellValue();
                    cell = cellIterator.next();
                    if (cell.getCellType() == CellType.STRING)
                        bloodType = cell.getStringCellValue();
                    cell = cellIterator.next();
                    if (cell.getCellType() == CellType.STRING)
                        gender = cell.getStringCellValue();
                    cell = cellIterator.next();
                    if (cell.getCellType() == CellType.NUMERIC)
                        iseast = (int) cell.getNumericCellValue();
                    cell = cellIterator.next();
                    if (cell.getCellType() == CellType.NUMERIC)
                        isethiopian = (int) cell.getNumericCellValue();
                    patients.add(new Patient(id, firstName, lastName, age, weight, length, phone, bloodType, gender, iseast, isethiopian));
                }
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }


    public Patient getPatient(int x) {
        return this.patients.get(x);
    }

    public Set<String> getIdList() {
        Set<String> ids = new HashSet<>();
        for (Patient p : patients) {
            ids.add(p.getId() + "             " + p.getFirstName() + " " + p.getLastName());
        }
        return ids;
    }


}
