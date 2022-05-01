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
    private String path;

    public ListPatient(ArrayList<Patient> patients) {
        this.patients =new ArrayList<>(patients);
    }

    public ListPatient(String path) {
        patients=new ArrayList<>();
        insertData(path);
    }

    public void insertData(String path){
        int id = 0;
        String firstName=null;
        String lastName=null;
        int age=0;
        int weight=0;
        int length=0;
        int phone=0;
        String bloodType=null;
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

                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    if(cell.getCellType()== CellType.NUMERIC)
                            id= (int) cell.getNumericCellValue();
                    cell = cellIterator.next();
                    if(cell.getCellType()== CellType.STRING)
                       firstName=cell.getStringCellValue();
                    cell = cellIterator.next();
                    if(cell.getCellType()== CellType.STRING)
                        lastName=cell.getStringCellValue();
                    cell = cellIterator.next();
                    if(cell.getCellType()== CellType.NUMERIC)
                        age= (int) cell.getNumericCellValue();
                    cell = cellIterator.next();
                    if(cell.getCellType()== CellType.NUMERIC)
                       weight= (int) cell.getNumericCellValue();
                    cell = cellIterator.next();
                    if(cell.getCellType()== CellType.NUMERIC)
                        length= (int) cell.getNumericCellValue();
                    cell = cellIterator.next();
                    if(cell.getCellType()== CellType.NUMERIC)
                       phone = (int) cell.getNumericCellValue();
                    cell = cellIterator.next();
                    if(cell.getCellType()== CellType.STRING)
                        bloodType=cell.getStringCellValue();

                    patients.add(new Patient(id,firstName,lastName,age,weight,length,phone,bloodType));
                }
            }
            file.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addPatient(Patient p) throws IOException, InvalidFormatException {
        ReadWriteXlsx file=new ReadWriteXlsx(path);
      // p.getFirstName();
       // file.add(data);
        patients.add(new Patient(p));
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }


    public Patient getPatient(int x)
    {
        return this.patients.get(x);
    }
    public void setPatients(ArrayList patients) {
        this.patients = patients;
    }

    public Set<String> getIdList()
    {
        Set<String> ids=new HashSet<>();
        for(Patient p:patients)
        {
            ids.add(p.getFirstName()+" "+p.getLastName()+"             "+p.getId());

        }
        return ids;
    }


}
