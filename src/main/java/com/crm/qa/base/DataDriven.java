//package com.crm.qa.base;
//
//import java.util.ArrayList;
//
//import com.crm.qa.util.ExcelDataProvider;
//
//public class DataDriven extends TestBase {
//	
//	static ExcelDataProvider exceldata;
//	
//	
//	public static ArrayList<Object> GetData()
//	{
//		ArrayList<Object> myarray = new ArrayList<Object>();
//	
//		exceldata = new ExcelDataProvider("/home/tushar/eclipse-workspace/CRMPRoject/src/main/"
//				+ "java/com/crm/qa/testdata/ExcelData.xlsx");
//		
//		String firstnamedata = exceldata.getStringData("NewContact", 1, 0);
//		String lastnamedata = exceldata.getStringData("NewContact", 1, 1);
//		String companynamedata = exceldata.getStringData("NewContact", 1, 2);
//		String statusdata = exceldata.getStringData("NewContact", 1, 3);
//	
//		Object ob[] = {firstnamedata, lastnamedata, companynamedata, statusdata};
//		myarray.add(ob);
//		
//		return myarray;
//	}
//}
