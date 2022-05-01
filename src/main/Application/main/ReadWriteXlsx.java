package main;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellFill;

import java.io.*;

import java.util.Iterator;


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
        XSSFWorkbook wb = new XSSFWorkbook(file);
        sheet=wb.getSheetAt(0);
    }

    public void add(Object[] data){

        try {
        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(inputStream);
        sheet = workbook.getSheetAt(0);


            int rowCount = sheet.getLastRowNum();

                Row row = sheet.createRow(++rowCount);

                int columnCount = 0;

                Cell cell ;

                for (Object field : data) {
                    cell = row.createCell(columnCount++);
                    this.setCellValue((XSSFCell) cell,field);
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

    public void copy(String path)
    {
            // Read xlsx file
            XSSFWorkbook oldWorkbook = null;
            try {
                oldWorkbook = (XSSFWorkbook) WorkbookFactory.create(new File(path));
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

             XSSFWorkbook newWorkbook = new XSSFWorkbook();


            // Copy sheets
            for (int sheetNumber = 0; sheetNumber < oldWorkbook.getNumberOfSheets(); sheetNumber++) {
                final XSSFSheet oldSheet = oldWorkbook.getSheetAt(sheetNumber);
                final XSSFSheet newSheet = newWorkbook.createSheet(oldSheet.getSheetName());

                newSheet.setDefaultRowHeight(oldSheet.getDefaultRowHeight());
                newSheet.setDefaultColumnWidth(oldSheet.getDefaultColumnWidth());

                // Copy content
                for (int rowNumber = oldSheet.getFirstRowNum(); rowNumber < oldSheet.getLastRowNum(); rowNumber++) {
                    final XSSFRow oldRow = oldSheet.getRow(rowNumber);
                    if (oldRow != null) {
                        final XSSFRow newRow = newSheet.createRow(rowNumber);
                        newRow.setHeight(oldRow.getHeight());

                        for (int columnNumber = oldRow.getFirstCellNum(); columnNumber < oldRow
                                .getLastCellNum(); columnNumber++) {
                            newSheet.setColumnWidth(columnNumber, oldSheet.getColumnWidth(columnNumber));

                            final XSSFCell oldCell = oldRow.getCell(columnNumber);
                            if (oldCell != null) {
                                final XSSFCell newCell = newRow.createCell(columnNumber);

                                // Copy value
                                setCellValue(newCell, getCellValue(oldCell));

                                // Copy style
                                XSSFCellStyle newCellStyle = newWorkbook.createCellStyle();
                                newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
                                newCell.setCellStyle(newCellStyle);
                            }
                        }
                    }
                }
            }

            try {
                oldWorkbook.close();
                newWorkbook.write(new FileOutputStream(this.filePath));
                newWorkbook.close();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

        private void setCellValue( XSSFCell cell,  Object value) {
            if (value instanceof Boolean) {
                cell.setCellValue((boolean) value);
            } else if (value instanceof Byte) {
                cell.setCellValue((byte) value);
            } else if (value instanceof Double) {
                cell.setCellValue((double) value);
            } else if (value instanceof String) {
                cell.setCellValue((String) value);
            } else {
                throw new IllegalArgumentException();
            }
        }

        private  Object getCellValue(XSSFCell cell) {
            switch (cell.getCellType()) {
                case BOOLEAN:
                    return cell.getBooleanCellValue(); // boolean
                case ERROR:
                    return cell.getErrorCellValue(); // byte
                case NUMERIC:
                    return cell.getNumericCellValue(); // double
                case STRING:
                case BLANK:
                    return cell.getStringCellValue(); // String
                case FORMULA:
                    return  "=" + cell.getCellFormula(); // String for formula
                default:
                    throw new IllegalArgumentException();
            }
        }
}



