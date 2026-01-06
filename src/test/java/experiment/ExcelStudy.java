package experiment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelStudy {

	public static void main(String[] args) {
		String filePath = System.getProperty("user.dir") + "//src//test//resources//ExcelStudy.xlsx";
		//System.out.println(filePath);
		XSSFWorkbook wb=null;
		File file=new File(filePath);
		try {
			FileInputStream fis=new FileInputStream(file);
			wb=new XSSFWorkbook(fis);
		} catch (IOException e) {
			System.out.println("Error Processing the file:- "+e.getMessage());
            // Handle exception, maybe notify user or log the error
		}
		XSSFSheet mySheet = wb.getSheet("Employees");
		//Total Rows In Sheet
//		int totalRows = mySheet.getPhysicalNumberOfRows();
		int totalRows = mySheet.getLastRowNum()+1;
		System.out.println("Total Rows "+totalRows);
		//Total cell In sheet
		int totalCells = mySheet.getRow(0).getLastCellNum();
		System.out.println("Total cells "+totalCells);

		
	}

}
