package experiment;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class A {
	
@Test
public void check() throws InterruptedException
{
	 WebDriver   driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

WebElement firstName = driver.findElement(By.id("input-firstname"));
WebElement lastName = driver.findElement(By.id("input-lastname"));
WebElement email = driver.findElement(By.id("input-email"));
WebElement telephone = driver.findElement(By.id("input-telephone"));
WebElement PWD = driver.findElement(By.id("input-password"));
WebElement conPWD = driver.findElement(By.id("input-confirm"));
WebElement newsLatterYesRadioBtn = driver.findElement(By.name("newsletter"));
WebElement privacyPolicy = driver.findElement(By.name("agree"));
WebElement continueButton = driver.findElement(By.xpath("//input[@type='submit']"));

//privacyPolicy.click();
//System.out.println(isElementInSelectedState(privacyPolicy));
//System.out.println("after de select");
//privacyPolicy.click();
//System.out.println(isElementInSelectedState(privacyPolicy));
//








//System.out.println(isElementDisplayed(continueButton));
//System.out.println(isElementDisplayed(firstName));
//System.out.println(isElementDisplayed(lastName));
//System.out.println(isElementDisplayed(email));
//System.out.println(isElementDisplayed(telephone));
//System.out.println(isElementDisplayed(PWD));
//System.out.println(isElementDisplayed(conPWD));
//System.out.println(isElementDisplayed(newsLatterYesRadioBtn));
//System.out.println(isElementDisplayed(privacyPolicy));
//





}
public static boolean isElementDisplayed(WebElement element) {
	
	boolean b = false;
	
	try {
		b = element.isDisplayed();
	}catch(NoSuchElementException e) {
		b = false;
	}
	
	return b;
	
}
public static boolean isElementInSelectedState(WebElement element) {
	boolean b = false;
	if(isElementDisplayed(element)) {
		b = element.isSelected();
	}
	return b;
}
public static void clickOnElement(WebElement element) {
	if(isElementDisplayed(element) && isElementInSelectedState(element)) {
		element.click();
	}
}

}
