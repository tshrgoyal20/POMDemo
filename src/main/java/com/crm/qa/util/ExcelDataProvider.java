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
	
	public ExcelDataProvider()
	{
		File file  = new File("/home/tushar/eclipse-workspace/CRMPRoject/src/main/"
				+ "java/com/crm/qa/testdata/ExcelData.xlsx");
		
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
	
	/*public void getSheetData()
	{	
		fnamedata = exceldata.getStringData("NewContact", 1, 0);
		lnamedata = exceldata.getStringData("NewContact", 1, 1);
		companydata = exceldata.getStringData("NewContact", 1, 2);
		statusdata = exceldata.getStringData("NewContact", 1, 3);
	}*/
	
	/*public void getRows()
	{
		int rowCount = sheet.getPhysicalNumberOfRows();
		System.out.println(rowCount);
		for(int row=1; row<=rowCount; row++)
		{
			System.out.println(row);
		}
	}*/
}
