/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package etter.excel_reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import static org.apache.poi.ss.usermodel.CellType.BOOLEAN;
import static org.apache.poi.ss.usermodel.CellType.FORMULA;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author felix
 *
 * program to read an excelfile into an array
 * 
 * libaries: Apache POI Libary (supports xls and xlsx files)
 * 
 * It provides the Workbook interface for modeling an Excel file, and the Sheet, Row and Cell interfaces that model the elements of an Excel file, as well as implementations of each interface for both file formats.
 *
 * When working with the newer .xlsx file format, we would use the XSSFWorkbook, XSSFSheet, XSSFRow and XSSFCell classes.
 * 
 * To work with the older .xls format, we use the HSSFWorkbook, HSSFSheet, HSSFRow and HSSFCell classes.
 * 
 * 
 **/
 


public class Excel_reader {
    
    
    
    static String PATH = "C:\\Users\\felix\\Documents\\Projects\\example.xlsx";
    
    public static FileInputStream read_file(String Path) throws FileNotFoundException{
        
       FileInputStream file = new FileInputStream(new File(Path));
       return file;
    }
    
    public static void main(String[] args) {
        try {
        FileInputStream inputfile = read_file(PATH);
        Workbook workbook;
    
            workbook = new XSSFWorkbook(inputfile);
            Sheet sheet = workbook.getSheetAt(0);
            Map<Integer, List<String>> data = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            data.put(i, new ArrayList<String>());
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING: data.get(i).add(cell.getStringCellValue()); break;
                    case NUMERIC: data.get(i).add(String.valueOf(cell.getNumericCellValue())); break;
                    case BOOLEAN: data.get(i).add(String.valueOf(cell.getBooleanCellValue())); break;
                    case FORMULA: data.get(i).add(cell.getCellFormula()); break;
                    default: System.out.println("could not read");
                }
            }
            i++;
        }
        for (Integer index: data.keySet()){
          System.out.println(data.get(index));
        }
        } catch (IOException ex) {
            Logger.getLogger(Excel_reader.class.getName()).log(Level.SEVERE, null, ex);
        }
       

    }
}


