package experiment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class VerifyStatusOfPrivacyPolicy {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Register']")).isDisplayed());
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.linkText("Continue")).click();
		
		
//		boolean status = driver.findElement(By.name("agree")).isSelected();
//		System.out.println(status);
		
		//WebElement FirstName = driver.findElement(By.xpath("//input[@placeholder='First Name']"));
//		System.out.println(driver.findElement(By.xpath("//input[@placeholder='First Name']")).getAccessibleName());
//		System.out.println(driver.findElement(By.xpath("//input[@placeholder='Last Name']")).getAttribute("placeholder"));
//		System.out.println(driver.findElement(By.xpath("//input[@placeholder='E-Mail']")).getAttribute("placeholder"));
//		System.out.println(driver.findElement(By.xpath("//input[@placeholder='Telephone']")).getAttribute("placeholder"));
//		System.out.println(driver.findElement(By.xpath("//input[@placeholder='Password']")).getAttribute("placeholder"));
//		System.out.println(driver.findElement(By.xpath("//input[@placeholder='Password Confirm']")).getAttribute("placeholder"));

		//System.out.println(FirstName.getAttribute("placeholder"));



	}

}
