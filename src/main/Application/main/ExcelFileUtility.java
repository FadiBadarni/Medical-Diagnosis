package main;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelFileUtility
{

    public static List<List<String>> readExcelFile(String fileName)
    {
        List<List<String>> excelContent = new ArrayList();
        int columnCounter = 0;
        int rowCounter = 0;

        try (InputStream inputStream = new FileInputStream(fileName)) {
            DataFormatter formatter = new DataFormatter();

            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                List<String> tempList = new ArrayList();

                for (Cell cell : row) {
                    String text = formatter.formatCellValue(cell);
//                    System.out.print(++columnCounter + ": " + text);
//                    System.out.println(text.length());
                    tempList.add(text);
                }
                columnCounter = 0;
                excelContent.add(tempList);

                ++rowCounter;
                //Used for testing
                //if (rowCounter == 5) {
                //    break;
                //}
                //System.out.println(String.join(" - ", tempList));
                //System.out.println(tempList.size());

            }
        }
        catch (IOException | EncryptedDocumentException ex) {
            //System.out.println(ex.toString());
        }

        return excelContent;
    }

    public static List<List<String>> readExcelFileWithHeaderFirstRow(String fileName, boolean allCellHaveData)
    {
        List<List<String>> sheetData = new ArrayList();
        int headerCount = 0;

        try (FileInputStream fis = new FileInputStream(fileName);
             Workbook wb = WorkbookFactory.create(fis)) {

            Sheet sheet = wb.getSheetAt(0);
            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                String value;

                List<String> rowData = new ArrayList();
                //System.out.println("\nROW " + row.getRowNum() + " has " + row.getPhysicalNumberOfCells() + " cell(s).");
                //System.out.println("last cell number: " + row.getLastCellNum());
                if (i == 0) {
                    headerCount = row.getLastCellNum();
                }

                for (int c = 0; c < row.getLastCellNum(); c++) {

                    Cell cell = row.getCell(c);

                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case FORMULA:
                                value = "FORMULA value=" + cell.getCellFormula();
                                rowData.add(cell.getCellFormula());
                                break;

                            case NUMERIC:
                                value = "NUMERIC value=" + cell.getNumericCellValue();
                                rowData.add(Double.toString(cell.getNumericCellValue()));
                                break;

                            case STRING:
                                value = "STRING value=" + cell.getStringCellValue();
                                rowData.add(cell.getStringCellValue());
                                break;

                            case BLANK:
                                value = "<BLANK>";
                                rowData.add("");
                                break;

                            case BOOLEAN:
                                value = "BOOLEAN value-" + cell.getBooleanCellValue();
                                rowData.add(cell.getBooleanCellValue() ? "true" : "false");
                                break;

                            case ERROR:
                                value = "ERROR value=" + cell.getErrorCellValue();
                                rowData.add(Byte.toString(cell.getErrorCellValue()));
                                break;

                            default:
                                value = "UNKNOWN value of type " + cell.getCellType();
                                rowData.add(cell.getCellType().toString());
                        }
                        //System.out.println("CELL col=" + cell.getColumnIndex() + " VALUE=" + value);
                    }
                    else {
                        //System.out.println("CELL col=" + " VALUE=" + "<My Blank>");
                        rowData.add("<My Blank>");
                    }
                }

                if (!allCellHaveData) {
                    int currentRowCount = row.getLastCellNum();
                    while (currentRowCount < headerCount) {
                        rowData.add("<Blank end column>");
                        currentRowCount++;
                    }
                }
                sheetData.add(rowData);
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        catch (IOException ex) {
            System.out.println(ex);
        }

        return sheetData;
    }
}
