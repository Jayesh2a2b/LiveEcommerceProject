package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;

public class CommonUtilities {
	public static String getGenerateNewMail() {
		Date date = new Date();
		String value = date.toString();
		String attach = value.replace(" ", "_").replace(":", "_");
		String withoutSpaceColonAndAttchMailId = attach + "@gmail.com";
		return withoutSpaceColonAndAttchMailId;

	}
	public static ExtentReports getExtentReports() {
		
		ExtentReports extentReport = new ExtentReports();
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\Reports\\ExtentReport.html");
		ExtentSparkReporterConfig sparkReporterConfig = sparkReporter.config();
		sparkReporterConfig.setReportName("TutorialsNinja Results");
		sparkReporterConfig.setDocumentTitle("TN Report");
		
		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("Username",System.getProperty("user.name"));
		extentReport.setSystemInfo("Seleium Version","4.26.0");
		extentReport.setSystemInfo("OS",System.getProperty("os.name"));
		extentReport.setSystemInfo("Java Version",System.getProperty("java.version"));
		
		return extentReport;
		
	}

	public static Object[][] getTestData(MyXLSReader xls_received, String testName, String sheetName) {

		MyXLSReader xls = xls_received;

		String testCaseName = testName;

		String testDataSheet = sheetName;

		int testStartRowNumber = 1;

		while (!(xls.getCellData(testDataSheet, 1, testStartRowNumber).equals(testCaseName))) {

			testStartRowNumber++;

		}

		int columnStartRowNumber = testStartRowNumber + 1;
		int dataStartRowNumber = testStartRowNumber + 2;

		int rows = 0;
		while (!(xls.getCellData(testDataSheet, 1, dataStartRowNumber + rows).equals(""))) {

			rows++;

		}

		// Total number of columns in the required test
		int columns = 1;

		while (!(xls.getCellData(testDataSheet, columns, columnStartRowNumber).equals(""))) {

			columns++;

		}

		Object[][] obj = new Object[rows][1];

		HashMap<String, String> map = null;

		// Reading the data in the test
		for (int i = 0, row = dataStartRowNumber; row < dataStartRowNumber + rows; row++, i++) {

			map = new HashMap<String, String>();

			for (@SuppressWarnings("unused")
			int j = 0, column = 1; column < columns; column++, j++) {

				String key = xls.getCellData(testDataSheet, column, columnStartRowNumber);

				String value = xls.getCellData(testDataSheet, column, row);

				map.put(key, value);

			}

			obj[i][0] = map;

		}

		return obj;

	}
	

public static int convertToInt(String Text)
{
	return Integer.parseInt(Text);
	
}
public static List<String> sortingStringInAscendingOrder(List<String>list)
{
	List<String>sortedList=new ArrayList<String>(list);
	Collections.sort(sortedList);
	return sortedList;
}
public static List<String> sortingStringInDescendingOrder(List<String>list)
{
	List<String>sortedList=new ArrayList<String>(list);
	Collections.sort(sortedList,String.CASE_INSENSITIVE_ORDER);
	Collections.reverse(sortedList);
	//Collections.sort(sortedList,String.CASE_INSENSITIVE_ORDER.reversed());
	return sortedList;
}
public static boolean areItemsInListAreInDescendingOrder(List<String>list)
{
	List<String> expectedSortedList = sortingStringInDescendingOrder(list);
	return list.equals(expectedSortedList);
}


public static boolean areItemsInListAreInAscendingOrder(List<String>list)
{
	List<String>sortedList=new ArrayList<String>(list);
	Collections.sort(sortedList,String.CASE_INSENSITIVE_ORDER);
	return list.equals(sortedList);
}
	public static Properties loadPropertiesFile() throws IOException {
		Properties prop = new Properties();
		// FileInputStream fis=new
		// FileInputStream("C:\\Users\\HP\\OneDrive\\Pictures\\TutorialsNinjaLiveAutomationProjectdemo\\Resources\\projectData.properties");
		String projectPath = System.getProperty("user.dir");
		String filePath = projectPath + "\\Resources\\projectData.properties";

		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		prop.load(fis); 
		return prop;
	}

	public static Properties storePropertyFile(Properties prop)  {
		String projectPath = System.getProperty("user.dir");
		String filePath = projectPath + "\\Resources\\projectData.properties";

		File file = new File(filePath);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			prop.store(fos, "Property file Updated!!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}
}
