package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.crm.qa.base.TestBase;

public class ExcelDataProvider extends TestBase {
	
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	
	public ExcelDataProvider(String path, String sheetName)
	{
		File file  = new File(path);
		
		try
		{
		FileInputStream fis = new FileInputStream(file);
		
		wb = new XSSFWorkbook(fis);
		
		sheet = wb.getSheet(sheetName);
		
		}
		catch(Exception e)
		{
			System.out.println("Unable to read excel file "+e.getStackTrace());
		}
	}
	
	public String getStringData(int row, int column)
	{
		return sheet.getRow(row).getCell(column).getStringCellValue();
	}

	public double getNumericData(int row, int column)
	{
		return sheet.getRow(row).getCell(column).getNumericCellValue();
	}
	
}
