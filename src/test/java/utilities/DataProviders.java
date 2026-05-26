package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider1
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException{
		String path=System.getProperty("user.dir")+"\\testData\\opencart_logindata.xlsx";
		String sheetname="Sheet1";
		ExcelUtils xlutil=new ExcelUtils(path);
		
		int rows=xlutil.getRowCount(sheetname);
		int cols=xlutil.getCellCount(sheetname,1);
		String logindata[][]=new String[rows][cols]; //creating a 2-D Array
		
		for(int r=1;r<=rows;r++) {
			for(int c=0;c<cols;c++) {
				logindata[r-1][c]=xlutil.getCellData(sheetname,r,c); // array index starts from 0
			}
		}
		
		return logindata; // returns a 2D Array
}}
