package main;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class ReadWriteXlsx {

  //  private Workbook workbook;
    private Sheet sheet;
     //private FileOutputStream outputStream;
     private String filePath;
    // private  FileInputStream inputStream;
     private File file;

    ReadWriteXlsx(String filePath) throws IOException, InvalidFormatException {

        this.filePath=filePath;
        file = new File(filePath);
       // workbook = new XSSFWorkbook();
     //   outputStream = new FileOutputStream(file);
      //  inputStream = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(file);
        sheet=wb.getSheetAt(0);
    }
//    public void write( Map<String, Object[]> data,String SheetName) {
//        sheet = workbook.createSheet(SheetName);
//        Set<String> keyset = data.keySet();
//        int rownum = 0;
//        for (String key : keyset)
//        {
//            Row row = sheet.createRow(rownum++);
//            Object [] objArr = data.get(key);
//            int cellnum = 0;
//            for (Object obj : objArr)
//            {
//                Cell cell = row.createCell(cellnum++);
//                if(obj instanceof String)
//                    cell.setCellValue((String)obj);
//                else if(obj instanceof Integer)
//                    cell.setCellValue((Integer)obj);
//            }
//        }
//        try
//        {
//            //Write the workbook in file system
//           // out = new FileOutputStream(new File("howtodoinjava_demo.xlsx"));
//        //    workbook.write(outputStream);
//         //   outputStream.close();
//            //System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//    public void print(){
//        try
//        {
//            FileInputStream file = new FileInputStream(new File("howtodoinjava_demo.xlsx"));
//            XSSFWorkbook workbook = new XSSFWorkbook(file);
//            XSSFSheet sheet = workbook.getSheetAt(0);
//            Iterator<Row> rowIterator = sheet.iterator();
//            while (rowIterator.hasNext())
//            {
//                Row row = rowIterator.next();
//                Iterator<Cell> cellIterator = row.cellIterator();
//
//                while (cellIterator.hasNext())
//                {
//                    Cell cell = cellIterator.next();
//                    switch (cell.getCellType())
//                    {
//                        case NUMERIC:
//                            System.out.print(cell.getNumericCellValue() + "\t");
//                            break;
//                        case STRING:
//                            System.out.print(cell.getStringCellValue() + "\t");
//                            break;
//                    }
//                }
//                System.out.println("");
//            }
//            file.close();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
//    public void isfound(){
//
//    }
//
//    public void update(int row,int column,String value){
//        Cell cell2Update = sheet.getRow(row).getCell(column);
//        cell2Update.setCellValue(value);
//    }

    public void add(Object[] data){

        try {
        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(inputStream);
        sheet = workbook.getSheetAt(0);


            int rowCount = sheet.getLastRowNum();

                Row row = sheet.createRow(++rowCount);

                int columnCount = 0;

                Cell cell ;
                //= row.createCell(columnCount);

               // cell.setCellValue(rowCount);

                for (Object field : data) {
                    cell = row.createCell(columnCount++);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {

                        cell.setCellValue((Integer) field);
                    }
                }

            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (IOException | EncryptedDocumentException ex) {

              ex.printStackTrace();
          }


    }

    public  Iterator<Cell> getAllRow(String rowName)  {

        try
        {
            Iterator<Row> itr = sheet.iterator();
        while (itr.hasNext())
        {
            Row row = itr.next();
            Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
            Cell cell = cellIterator.next();
            switch (cell.getCellType()) {
                case STRING:
                if (cell.getStringCellValue().equals(rowName))
                    return cellIterator;
                    break;
            }
        }
        return null;
        }
     catch(Exception e)
            {
               e.printStackTrace();
           }
        return null;
    }

    public Set<String> getColumnList() {
        Set<String> columnList = new HashSet<>();
        try {

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case STRING:
                        columnList.add(cell.getStringCellValue());
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return columnList;
    }

}


