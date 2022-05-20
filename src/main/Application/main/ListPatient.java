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

    public String insertData(String path) {
        int id = 0;
        String firstName = "unknown";
        String lastName = "unknown";
        String gender = "unknown";
        int iseast = 0;
        int isethiopian = 0;
        int age = 0;
        int weight = 0;
        int length = 0;
        int phone = 0;
        try {
            FileInputStream file = new FileInputStream(path);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) rowIterator.next();
            int i=0;
            while (rowIterator.hasNext()) {
                i++;
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                //  while (cellIterator.hasNext()) {
                Cell cell;
                if (cellIterator.hasNext()) {
                    cell = cellIterator.next();
                    if (cell.getCellType() == CellType.NUMERIC)
                        id = (int) cell.getNumericCellValue();
                    else return "problem in row " + i + " the first cell must to be the ID,\n and it must to be number";
                    if (cellIterator.hasNext()) {
                        cell = cellIterator.next();
                        if (cell.getCellType() == CellType.STRING)
                            firstName = cell.getStringCellValue();
                        else
                            return "problem in row" + i + "the  cell number 2\n must to be the firstname,\n and it must to be string";
                        if (cellIterator.hasNext()) {
                            cell = cellIterator.next();
                            if (cell.getCellType() == CellType.STRING)
                                lastName = cell.getStringCellValue();
                            else
                                return "problem in row" + i + "the  cell number 3 \nmust to be the lastname, \nand it must to be string";
                            if (cellIterator.hasNext()) {
                                cell = cellIterator.next();
                                if (cell.getCellType() == CellType.NUMERIC)
                                    age = (int) cell.getNumericCellValue();
                                else
                                    return "problem in row" + i + "the  cell number 4 \nmust to be the age,\n and it must to be number";
                                if (cellIterator.hasNext()) {
                                    cell = cellIterator.next();
                                    if (cell.getCellType() == CellType.NUMERIC)
                                        weight = (int) cell.getNumericCellValue();
                                    else
                                        return "problem in row" + i + "the  cell number 5 \n to be the weight, \nand it must to be number";
                                    if (cellIterator.hasNext()) {
                                        cell = cellIterator.next();
                                        if (cell.getCellType() == CellType.NUMERIC)
                                            length = (int) cell.getNumericCellValue();
                                        else
                                            return "problem in row" + i + "the  cell number 6 \nmust to be the length,\n and it must to be number";
                                        if (cellIterator.hasNext()) {
                                            cell = cellIterator.next();
                                            if (cell.getCellType() == CellType.NUMERIC)
                                                phone = (int) cell.getNumericCellValue();
                                            else
                                                return "problem in row" + i + "the  cell number 7 \nmust to be the phone number, \nand it must to be number";
                                                  if (cellIterator.hasNext()) {
                                                    cell = cellIterator.next();
                                                    if (cell.getCellType() == CellType.STRING)
                                                        gender = cell.getStringCellValue();
                                                    else
                                                        return "problem in row" + i + "the  cell number 9\n must to be the gander \nand it must to be string";
                                                    if (cellIterator.hasNext()) {
                                                        cell = cellIterator.next();
                                                        if (cell.getCellType() == CellType.NUMERIC)
                                                            iseast = (int) cell.getNumericCellValue();
                                                        else
                                                            return "problem in row" + i + "the  cell number 10 \nmust to be the  is esat\n and it must to be string";
                                                        if (cellIterator.hasNext()) {
                                                            cell = cellIterator.next();
                                                            if (cell.getCellType() == CellType.NUMERIC)
                                                                isethiopian = (int) cell.getNumericCellValue();
                                                            else
                                                                return "problem in row" + i + "the  cell number 11\n must to be the is ethiopian\n and it must to be string";

                                                            if (id < 9999999 || id > 1000000000) return "the id must to be 8,9 number in row " + i;
                                                            if (age < 0 || age > 120) return "the age in row " + i + " \nmust to be from 0 to 120";
                                                            if (length < 50 || length > 300) return "the length \nmust to be 50-300cm in row " + i;
                                                            if (weight < 10 || weight > 300)
                                                                return "the weight must to be 10-300kg in row " + i;
                                                            if (!(gender.equals("male") || gender.equals("Male") || gender.equals("MALE") ||
                                                                    gender.equals("female") || gender.equals("Female") || gender.equals("FEMALE")))
                                                                return "the Gender must to be Male or Female" + i;
                                                            if (!(iseast == 0 || iseast == 1))
                                                                return "Is esat must to be 0 or 1 in row number:" + i;
                                                            if (!(isethiopian == 0 || isethiopian == 1))
                                                                return "Is esat must to be 0 or 1 in row number:" + i;
                                                            patients.add(new Patient(id, firstName, lastName, age, weight, length, phone, gender, iseast, isethiopian));
                                                        }
                                                    } else return "there are problem in row " + i;
                                                } else return "there are problem in row" + i;
                                            } else return "there are problem in row" + i;
                                        } else return "there are problem in row" + i;
                                    } else return "there are problem in row" + i;
                                } else return "there are problem in row" + i;
                            } else return "there are problem in row" + i;
                        } else return "there are problem in row" + i;
                    } else return "there are problem in row" + i;
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }


    public Patient getPatient(int x) {
        return this.patients.get(x);
    }

    public ArrayList<String> getIdList() {
        ArrayList<String> ids = new ArrayList<>();
        for (Patient p : patients) {
            ids.add(p.getId() + "             " + p.getFirstName() + " " + p.getLastName());
        }
        return ids;
    }


}
