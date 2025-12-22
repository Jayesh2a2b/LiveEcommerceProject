package register;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class Copy {
	WebDriver driver;
	String browserName;
	Properties	prop;
	@BeforeMethod
	public void setup()
	{
try {
		prop=CommonUtilities.loadPropertiesFile();
} catch (IOException e) {
	e.printStackTrace();
}
		 browserName=prop.getProperty("browserName");
		if( browserName.equals("chrome"))
		{
		    driver=new ChromeDriver();
		}
		else if(browserName.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get(prop.getProperty("AppUrl"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();


	}
	@AfterMethod
	public void teardown()
	{
	    if (driver != null) {       //  Prevents NullPointerException
	        driver.quit(); // Close browser & end session

	    }
	

	}}
	

	 
	
