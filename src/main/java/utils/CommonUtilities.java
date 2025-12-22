package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class CommonUtilities {
	public static String getGenerateNewMail() {
		Date date = new Date();
		String value = date.toString();
		String attach = value.replace(" ", "_").replace(":", "_");
		String withoutSpaceColonAndAttchMailId = attach + "@gmail.com";
		return withoutSpaceColonAndAttchMailId;

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
