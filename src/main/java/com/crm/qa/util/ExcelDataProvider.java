package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.crm.qa.base.TestBase;

public class ExcelDataProvider extends TestBase {
	
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public String fnamedata;
	public String lnamedata;
	public String companydata;
	public String statusdata;
	
	public ExcelDataProvider(String path)
	{
		File file  = new File(path);
		
		try
		{
		FileInputStream fis = new FileInputStream(file);
		
		wb = new XSSFWorkbook(fis);
		
		sheet = wb.getSheet("NewContact");
		
		}
		catch(Exception e)
		{
			System.out.println("Unable to read excel file "+e.getStackTrace());
		}
	}
	
	public String getStringData(String sheetName, int row, int column)
	{
		return wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
	}

	public double getNumericData(String sheetName, int row, int column)
	{
		return wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
	}
	
}
