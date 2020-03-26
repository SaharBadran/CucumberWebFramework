package VodafoneFramework.utilities.readers;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ExcelReader {
    String sheetName;
    String filePath;

    public ExcelReader(String path, String sheetName) {
        this.filePath = path;
        this.sheetName = sheetName;
    }

    private Map<String, Map<String, ArrayList<String>>> loadRowsIntoMap() throws IOException {
        FileInputStream fis = new FileInputStream(this.filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(this.sheetName);
        DataFormatter formatter = new DataFormatter();
        Map<String, Map<String, ArrayList<String>>> excelFileMap = new HashMap<String, Map<String, ArrayList<String>>>();
        Map<String, ArrayList<String>> dataMap = new HashMap<String, ArrayList<String>>();
        Iterator<Row> rowIterator = sheet.iterator();
        //Looping over rows
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            ArrayList<String> valueRow = new ArrayList<String>();
            String key = null;
            for (int cellIterator = row.getFirstCellNum(); cellIterator < row.getLastCellNum(); cellIterator++) {
                if (cellIterator == row.getFirstCellNum()) {
                    Cell keyCell;
                    keyCell = row.getCell(cellIterator);
                    key = formatter.formatCellValue(keyCell).trim();
                } else {
                    Cell valueCell;
                    valueCell = row.getCell(cellIterator);
                    String value = formatter.formatCellValue(valueCell).trim();
                    valueRow.add(value);

                }
            }
            dataMap.put(key, valueRow);
        }
        //Putting dataMap to excelFileMap
        excelFileMap.put(sheetName, dataMap);
        //Returning excelFileMap
        return excelFileMap;
    }
    public ArrayList<String> readMultiValuesUsingKey(String key) {
        Map<String, ArrayList<String>> m = null;
        try {
            m = loadRowsIntoMap().get(sheetName);
        } catch (IOException e) {
            System.out.println("The data couldn't be fetched from this test data resource");
            e.printStackTrace();
        }
        ArrayList<String> values = m.get(key);
        return values;
    }
    private Map<String, Map<String, String>> loadCellsIntoMap() throws IOException {
        FileInputStream fis = new FileInputStream(this.filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(this.sheetName);
        DataFormatter formatter = new DataFormatter();
        int lastRow = sheet.getLastRowNum();
        Map<String, Map<String, String>> excelFileMap = new HashMap<String, Map<String, String>>();
        Map<String, String> dataMap = new HashMap<String, String>();
        //Looping over rows
        for (int i = 0; i <= lastRow; i++) {
            Row row = sheet.getRow(i);
            //1st Cell as Value
            Cell valueCell = row.getCell(1);
            //0th Cell as Key
            Cell keyCell = row.getCell(0);
            formatter.formatCellValue(valueCell);
            //To format numeric cells to strings
            String value = formatter.formatCellValue(valueCell).trim();
            String key = formatter.formatCellValue(keyCell).trim();
            //Putting key & value in dataMap
            dataMap.put(key, value);
            //Putting dataMap to excelFileMap
            excelFileMap.put(sheetName, dataMap);
        }
        //Returning excelFileMap
        return excelFileMap;
    }
    public String readValueUsingKey(String key) {
        Map<String, String> m = null;
        try {
            m = loadCellsIntoMap().get(sheetName);
        } catch (IOException e) {
            System.out.println("The data couldn't be fetched from this test data resource");
            e.printStackTrace();
        }
        String value = m.get(key);
        return value;
    }
}
