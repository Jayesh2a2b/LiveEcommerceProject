package experiment;

import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestDropdown {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		driver.findElement(By.id("input-search")).sendKeys("Mac");
		driver.findElement(By.id("button-search")).click();
		
		driver.findElement(By.id("list-view")).click();
		WebElement sortBy = driver.findElement(By.id("input-sort"));
		Select select=new Select(sortBy);
		select.selectByVisibleText("Name (Z - A)");
		List<WebElement> orginalList = driver.findElements(By.xpath("//div[@class='product-thumb']//h4"));
		List<String>uiOriginalList=new ArrayList<>();
		for(WebElement itemText:orginalList)
		{
			String uiName = itemText.getText().trim();
			uiOriginalList.add(uiName);
			
		}
		System.out.println("UI List      : " + uiOriginalList);
		isDropdownSortedWithDesendingOrderByName(uiOriginalList);
		driver.close();  
	}
public static void isDropdownSortedWithDesendingOrderByName(List<String>uiOriginalList)
{
	List<String>sortedList=new ArrayList<>(uiOriginalList);
	
	System.out.println("Sorted List before  : " + sortedList);
	Collections.sort(sortedList,String.CASE_INSENSITIVE_ORDER);
    Collections.reverse(sortedList);

	//Collections.sort(sortedList,Collections.reverseOrder());
	
	System.out.println("Sorted List after1  : " + sortedList);
System.out.println(uiOriginalList.equals(sortedList));
	
	;
}

}
