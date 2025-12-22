package experiment;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PrintDropDownElementName {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		driver.findElement(By.id("input-search")).sendKeys("Mac");
		driver.findElement(By.id("button-search")).click();
		
		List<WebElement> allOptions = driver.findElements(By.id("input-sort"));
		List<String> list = new ArrayList<>();
		for (WebElement option : allOptions) {
			String itemName = option.getText();
			list.add(itemName);
		}
		
		for(String dropdownName:list)
		{
			System.out.println(dropdownName);
		}
driver.close();
	}

}
