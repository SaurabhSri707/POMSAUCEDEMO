package com.SAUCEDEMO.proj4;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReaddatafromExcel {
    
    public static Object[][] readexceldata(String sheetName) throws IOException {
        // 1. Open the Excel file cleanly
        FileInputStream ip = new FileInputStream("C:\\Users\\user\\Desktop\\Read_Me.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(ip);
        XSSFSheet sh = wb.getSheet(sheetName);
        
        int lastRowIndex = sh.getLastRowNum(); 
        int totalCols = 2; // Fixed to 2 columns matching your current test case signature
        
        // Dynamic list that grows or shrinks automatically
        List<String[]> dynamicRowsList = new ArrayList<>();
        
        // 2. Loop through all rows that Excel thinks exist
        for (int i = 1; i <= lastRowIndex; i++) {
            Row row = sh.getRow(i);
            if (row == null) {
                continue; // Skip if the row object itself is null
            }
            
            // Extract the cells safely as Strings
            String username = (row.getCell(0) != null) ? row.getCell(0).toString().trim() : "";
            String password = (row.getCell(1) != null) ? row.getCell(1).toString().trim() : "";
            
            // GENERIC CHECK: If both columns are blank, ignore the row entirely
            if (username.isEmpty() && password.isEmpty()) {
                continue; 
            }
            
            // Add row data to our list if it contains actual data
            dynamicRowsList.add(new String[]{username, password});
        }
        
        wb.close();
        ip.close();
        
        // 3. Convert the dynamic list into the exact 2D Array size that TestNG needs
        // If you have 1 row, size is 1. If you have 50 rows, size is 50.
        Object[][] finalData = new Object[dynamicRowsList.size()][totalCols];
        for (int i = 0; i < dynamicRowsList.size(); i++) {
            finalData[i] = dynamicRowsList.get(i);
        }
        
        System.out.println("Execution Pool Ready! Total real rows found: " + finalData.length);
        return finalData;
    }
}
