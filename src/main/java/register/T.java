package register;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.RegisterPage;
import utils.CommonUtilities;

public class T {
	WebDriver driver;
	String browserName;
	Properties prop;
RegisterPage registerPage;
	@BeforeMethod
	public void setup() {
		try {
			prop = CommonUtilities.loadPropertiesFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		browserName = prop.getProperty("browserName");
		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get(prop.getProperty("AppUrl"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

	}

	@AfterMethod
	public void teardown() {
		if (driver != null) { // Prevents NullPointerException
			//driver.quit(); // Close browser & end session

		}
	}

	@Test
	public void VerifyHightAndWidthOfTextField() {

		Assert.assertEquals(registerPage.getFirstNameCSSValue("width"), "701.25px");
		Assert.assertEquals(registerPage.getFirstNameCSSValue("height"), "34px");
		Assert.assertEquals(registerPage.getLastNameCSSValue("width"), "701.25px");
		Assert.assertEquals(registerPage.getLastNameCSSValue("height"), "34px");
		Assert.assertEquals(registerPage.getEmailCSSValue("width"), "701.25px");
		Assert.assertEquals(registerPage.getEmailCSSValue("height"), "34px");
		Assert.assertEquals("701.25px", registerPage.getTelephoneCSSValue("width"));
		Assert.assertEquals("34px", registerPage.getTelephoneCSSValue("height"));
		Assert.assertEquals(registerPage.getPasswordCSSValue("width"), "701.25px");
		Assert.assertEquals(registerPage.getConfirmPasswordCSSValue("height"), "34px");
		Assert.assertEquals(registerPage.getConfirmPasswordCSSValue("width"), "701.25px");
		Assert.assertEquals(registerPage.getConfirmPasswordCSSValue("height"), "34px");
	}

	@Test
	public void VerifyThePasswordTextEnteredIntoThePasswordAndPasswordConfirmFieldOfRegisterAccountFunctionalityIsToggledToHideItsVisibility() {
		// logic attribute value is present 'password' for toggled or hiding and compare
		// getDomeAttributeValue()
		Assert.assertEquals(registerPage.getPasswordFieldDomAttribute("type"), "password");
		Assert.assertEquals(registerPage.getConfirmPasswordFieldDomAttribute("type"), "password");
	}






}	
