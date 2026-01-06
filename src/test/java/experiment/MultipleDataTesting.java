package experiment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MultipleDataTesting {
	public static void main(String[] args) {
		
		FileInputStream fis = null;
		XSSFWorkbook wb=null;

	String filePath=System.getProperty("user.dir")+"//src//test//resources//ExcelStudy.xlsx";
//1.To Open File create Object Of File
	File file=new File(filePath);
	//2. open file in read mode by using FileInputStream
	try {
	 fis=new FileInputStream(file);
	}
	catch(FileNotFoundException e)
	{
		System.out.println("Error in processing the File : "+e.getMessage());
	}
	//create object Of Workbook that refers to .xlsx or .xls extension
	//create object of Workbook that refers .xlsx 
	try {
		wb = new XSSFWorkbook(fis);
	} catch (IOException e) {
		e.printStackTrace();
	}
	//Extract sheet 
	XSSFSheet mySheet = wb.getSheet("Employees");
	//TotalRows
	//int totalRows = mySheet.getPhysicalNumberOfRows();
	int totalRows=mySheet.getLastRowNum()+1;
	//totalcells
	int totalCells=mySheet.getRow(0).getLastCellNum();
	
	for(int i=0;i<totalRows;i++)
	{
		 XSSFRow currentRow = mySheet.getRow(i);
		 for(int j=0;j<totalCells;j++)
		 {
			XSSFCell currentCell=currentRow.getCell(j);
			String resultOfCell = currentCell.getStringCellValue().toString();
			System.out.print(resultOfCell+" || ");
		 }
		 System.out.println();
	}
	
	
	//
	
}
}