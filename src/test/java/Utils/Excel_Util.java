package Utils;  

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel_Util {
    private Workbook wb;
    private Sheet sh;
    private String excelFilePath;
    private Map<String, Integer> columns = new HashMap<>();

    public Excel_Util(String excelPath, String sheetName) throws IOException, InvalidFormatException {
        setExcelFile(excelPath, sheetName);
    }

    public void setExcelFile(String excelPath, String sheetName) throws IOException, InvalidFormatException {
        File file = new File(excelPath);
        if (!file.exists()) {
            wb = WorkbookFactory.create(true);
            sh = wb.createSheet(sheetName);
            try (FileOutputStream fileOut = new FileOutputStream(excelPath)) {
                wb.write(fileOut);
            }
        } else {
            try (FileInputStream fis = new FileInputStream(excelPath)) {
                wb = WorkbookFactory.create(fis);
            }
            sh = wb.getSheet(sheetName);
            if (sh == null) {
                sh = wb.createSheet(sheetName);
            }
            if (sh.getRow(0) != null) {
                sh.getRow(0).forEach(cell -> columns.put(cell.getStringCellValue(), cell.getColumnIndex()));
            }
        }
        this.excelFilePath = excelPath;
    }

    public String getCellData(int rownum, int colnum) {
        try {
            Row row = sh.getRow(rownum);
            if (row == null) {
                return "";
            }
            Cell cell = row.getCell(colnum);
            if (cell == null) {
                return "";
            }
            return cellToString(cell);
        } catch (Exception e) {
            return "";
        }
    }

    public String getCellData(int rownum, String columnName) {
        Integer colnum = columns.get(columnName);
        if (colnum == null) {
            throw new IllegalArgumentException("Column " + columnName + " does not exist in the sheet.");
        }
        return getCellData(rownum, colnum);
    }

    public void setCellData(String text, int rownum, int colnum) throws IOException {
        Row row = sh.getRow(rownum);
        if (row == null) {
            row = sh.createRow(rownum);
        }
        Cell cell = row.getCell(colnum);
        if (cell == null) {
            cell = row.createCell(colnum);
        }
        cell.setCellValue(text);

        try (FileOutputStream fileOut = new FileOutputStream(excelFilePath)) {
            wb.write(fileOut);
        }
    }

    public void setCellData(String text, int rownum, String columnName) throws IOException {
        Integer colnum = columns.get(columnName);
        if (colnum == null) {
            throw new IllegalArgumentException("Column " + columnName + " does not exist in the sheet.");
        }
        setCellData(text, rownum, colnum);
    }

    public int getRowCount() {
        return sh.getLastRowNum() + 1;
    }

    private String cellToString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return String.valueOf(cell.getDateCellValue());
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return Boolean.toString(cell.getBooleanCellValue());
            case BLANK:
                return "";
            default:
                return "";
        }
    }

    public void addNewRow(Object[] rowData) {
        int rowCount = getRowCount();
        Row row = sh.createRow(rowCount);
        for (int i = 0; i < rowData.length; i++) {
            Cell cell = row.createCell(i);
            if (rowData[i] instanceof String) {
                cell.setCellValue((String) rowData[i]);
            } else if (rowData[i] instanceof Double) {
                cell.setCellValue((Double) rowData[i]);
            } else if (rowData[i] instanceof Boolean) {
                cell.setCellValue((Boolean) rowData[i]);
            } else if (rowData[i] instanceof Integer) {
                cell.setCellValue((Integer) rowData[i]);
            }
        }

        try (FileOutputStream fileOut = new FileOutputStream(excelFilePath)) {
            wb.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteRow(int rowIndex) {
        int lastRowNum = sh.getLastRowNum();
        if (rowIndex >= 0 && rowIndex < lastRowNum) {
            sh.shiftRows(rowIndex + 1, lastRowNum, -1);
        }
        if (rowIndex == lastRowNum) {
            Row removingRow = sh.getRow(rowIndex);
            if (removingRow != null) {
                sh.removeRow(removingRow);
            }
        }

        try (FileOutputStream fileOut = new FileOutputStream(excelFilePath)) {
            wb.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
}
