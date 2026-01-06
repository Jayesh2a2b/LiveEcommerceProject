package experiment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PrintExceldata {

	public static void main(String[] args) {
		/*
		| Term     | Meaning          |
		| -------- | ---------------- |
		| Workbook | Poori Excel file |
		| Sheet    | Excel ka ek tab  |
		| Row      | Horizontal line  |
		| Cell     | Ek box           |
		*/

		String filePath = System.getProperty("user.dir") + "//src//test//resources//ExcelStudy.xlsx";
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
		//Total cell In sheet
		int totalCells = mySheet.getRow(0).getLastCellNum();
		
		for(int i=0;i<totalRows;i++)
		{
			XSSFRow currentRow = mySheet.getRow(i);
			for(int j=0;j<totalCells;j++)
			{
				XSSFCell currentCell = currentRow.getCell(j);
				System.out.print(currentCell.getStringCellValue()+" || ");
			}
			System.out.println();
		}

	}

}
