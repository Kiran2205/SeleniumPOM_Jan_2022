package com.qa.opencart.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtil {

    private static String testDataSheetPath = "./src/test/resources/testdata/DemoCartTestData.xlsx";
    private static Workbook wBook;
    private static Sheet sheet;
    private static Object[][] testData;

    public static Object[][] getTestData(String sheetName) {

        try {
            FileInputStream fis = new FileInputStream(testDataSheetPath);
            wBook = WorkbookFactory.create(fis);
            sheet = wBook.getSheet(sheetName);
            testData = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                    testData[i][j] = sheet.getRow(i+1).getCell(j).toString();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return testData;
    }

}
