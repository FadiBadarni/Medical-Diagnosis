package main;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.*;

import java.io.*;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Objects;


public class ReadWriteXlsx {

    //  private Workbook workbook;
    private Sheet sheet;
    //private FileOutputStream outputStream;
    private String filePath;
    // private  FileInputStream inputStream;
    private File file;


    ReadWriteXlsx(String filePath) throws IOException, InvalidFormatException {

        this.filePath = filePath;
        file = new File(filePath);
        XSSFWorkbook wb = new XSSFWorkbook(file);
        sheet = wb.getSheetAt(0);
    }

    public void add(String[] data) {

        try {
            FileInputStream inputStream = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(inputStream);
            sheet = workbook.getSheetAt(0);


            int rowCount = sheet.getLastRowNum();
            Row row = sheet.createRow(++rowCount);

            int columnCount = 0;

            Cell cell;

            for (String field : data) {
                cell = row.createCell(columnCount++);
                try {
                    double x = Double.parseDouble(field);
                    cell.setCellValue(x);
                } catch (NumberFormatException e) {
                    cell.setCellValue(field);
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

    public Iterator<Cell> getAllRow(String rowName,int cellnum) {

        try {
            Iterator<Row> itr = sheet.iterator();
            if(itr.hasNext()) itr.next();
            while (itr.hasNext()) {
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
                Cell cell = cellIterator.next();

                if(cell.getRow().getCell(cellnum).getCellType()==CellType.STRING)
                {
                    String s=cell.getRow().getCell(cellnum).getStringCellValue();
                    if(Objects.equals(s, rowName))
                        return cellIterator;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Iterator<Cell> getAllRow(int rowName,int cellnum) {

        try {
            Iterator<Row> itr = sheet.iterator();
            if(itr.hasNext()) itr.next();
            while (itr.hasNext()) {
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
                Cell cell = cellIterator.next();

                if(cell.getRow().getCell(cellnum).getCellType()==CellType.NUMERIC)
                {
                    if(cell.getRow().getCell(cellnum).getNumericCellValue()==rowName)
                        return cellIterator;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean found(int fRow,String value) {
        try {
            Iterator<Row> itr = sheet.iterator();
            if(itr.hasNext()) itr.next();
            while (itr.hasNext()) {
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
                Cell cell = cellIterator.next();
                if (cell.getCellType() == CellType.STRING) {
                    if(cell.getRow().getCell(fRow).getStringCellValue()==value)
                        return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
    public void copy(String path) {
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
        }
    }

    private void setCellValue(XSSFCell cell, Object value) {
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

    private Object getCellValue(XSSFCell cell) {
        return switch (cell.getCellType()) {
            case BOOLEAN -> cell.getBooleanCellValue(); // boolean
            case ERROR -> cell.getErrorCellValue(); // byte
            case NUMERIC -> cell.getNumericCellValue(); // double
            case STRING, BLANK -> cell.getStringCellValue(); // String
            case FORMULA -> "=" + cell.getCellFormula(); // String for formula
            default -> throw new IllegalArgumentException();
        };
    }

    public void add(String[] fdata,Hashtable<String, String> ldata) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(inputStream);
            sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getLastRowNum();
            Row row = sheet.createRow(++rowCount);

            int columnCount = 0;

            Cell cell;

            for (String field : fdata) {
                cell = row.createCell(columnCount++);
                try {
                    double x = Double.parseDouble(field);
                    cell.setCellValue(x);
                } catch (NumberFormatException e) {
                    cell.setCellValue(field);
                }

            }

            for(String s: ldata.keySet()) {

                cell = row.createCell(columnCount);
                cell.setCellValue(s);
                cell = row.createCell(columnCount+1);
                cell.setCellValue(ldata.get(s));
                row = sheet.createRow(++rowCount);

            }
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (IOException | EncryptedDocumentException ex) {

            ex.printStackTrace();
        }

    }
}



