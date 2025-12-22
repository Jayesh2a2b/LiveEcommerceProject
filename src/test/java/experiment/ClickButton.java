package experiment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ClickButton {
@Test
	public void check()
	{
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	driver.get("https://tutorialsninja.com/demo/");
	
	driver.findElement(By.xpath("//span[text()='My Account']")).click();
	driver.findElement(By.linkText("Register")).click();
//WebElement homeIcon = driver.findElement(By.xpath("//i[@class='fa fa-home']"));
//homeIcon.click();
//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.breadcrumb")));
//	WebElement homeLink = wait.until(ExpectedConditions.elementToBeClickable(
//	    By.xpath("//ul[@class='breadcrumb']//a[i[@class='fa fa-home']]")
//	));
//	homeLink.click();
//
	WebElement homeLink = driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[i[@class='fa fa-home']]"));
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", homeLink);
	homeLink.click();

	
	
	
	}
}
