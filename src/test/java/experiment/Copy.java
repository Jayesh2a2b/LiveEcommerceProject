/*package register;
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
	}

	
	@Test
	public void VerifyRegisteringAnAccountByUsingTheKeyboardKeys()
	{
		//Pressing tab key some element highlight and some are not
		Actions action=new Actions(driver);
		action.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
		action.sendKeys(Keys.ARROW_DOWN).pause(2000).sendKeys(Keys.ENTER).build().perform();

	    for(int i=1;i<=23;i++)
		{
			action.sendKeys(Keys.TAB).build().perform();
		}
		action.sendKeys(prop.getProperty("FirstName")).sendKeys(Keys.TAB).sendKeys(prop.getProperty("LastName")).sendKeys(Keys.TAB)
		.sendKeys(CommonUtilities.getGenerateNewMail()).sendKeys(Keys.TAB).sendKeys(prop.getProperty("Telephone")).sendKeys(Keys.TAB)
		.sendKeys(prop.getProperty("PWD")).sendKeys(Keys.TAB).sendKeys(prop.getProperty("CONPWD")).sendKeys(Keys.TAB).sendKeys(Keys.ARROW_LEFT)
		.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.SPACE).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();

		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Success']")).isDisplayed());


		
	}

	 * @Test public void
	 * VerifyRegisteringAnAccountByProvidingAnInvalidEmailAddressIntoTheE_MailField(
	 * ) throws InterruptedException { {
	 * driver.findElement(By.id("input-firstname")).sendKeys(prop.getProperty( } //
	 * * "FirstName")); // *
	 * driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty( // *
	 * "LastName")); // *
	 * driver.findElement(By.id("input-email")).sendKeys(prop.getProperty( // *
	 * "InvalidEmailOne")); // *
	 * driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty( // *
	 * "Telephone")); // *
	 * driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("PWD"))
	 * // * ; // *
	 * driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("CONPWD"
	 * // * )); driver.findElement(By.name("agree")).click(); // *
	 * driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click(); //
	 * * // * Thread.sleep(2000); if(browserName.equalsIgnoreCase("chrome")) {
	 * String // *
	 * expectedWarningMsgOne="Please include an '@' in the email address. 'amotoori' is missing an '@'."
	 * // * ; String // *
	 * actualWarningMsgOne=driver.findElement(By.id("input-email")).getAttribute( //
	 * * "validationMessage"); // System.out.println(actualWarningMsgOne); // * // *
	 * Assert.assertEquals(expectedWarningMsgOne,actualWarningMsgOne ); // * // *
	 * driver.findElement(By.id("input-email")).clear(); // *
	 * driver.findElement(By.id("input-email")).sendKeys(prop.getProperty( // *
	 * "InvalidEmailTwo")); // *
	 * driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click(); //
	 * * // * String // *
	 * expectedWarningMsgTwo="Please enter a part following '@'. 'amotoori2225@' is incomplete."
	 * // * ; String // *
	 * actualWarningMsgTwo=driver.findElement(By.id("input-email")).getAttribute( //
	 * * "validationMessage"); //System.out.println(actualWarningMsgTwo); // *
	 * Assert.assertEquals(expectedWarningMsgTwo,actualWarningMsgTwo ); // * // *
	 * driver.findElement(By.id("input-email")).clear(); // *
	 * driver.findElement(By.id("input-email")).sendKeys(prop.getProperty( // *
	 * "InvalidEmailThree")); // *
	 * driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click(); //
	 * * // * String // *
	 * expectedWarningMsgThree="'.' is used at a wrong position in 'gmail.'."; // *
	 * String // *
	 * actualWarningMsgThree=driver.findElement(By.id("input-email")).getAttribute(
	 * // * "validationMessage"); //System.out.println(actualWarningMsgThree); // *
	 * Assert.assertEquals(expectedWarningMsgThree,actualWarningMsgThree ); // * //
	 * * driver.findElement(By.id("input-email")).clear(); // * // *
	 * driver.findElement(By.id("input-email")).sendKeys(prop.getProperty( // *
	 * "InvalidEmailFour")); // *
	 * driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click(); //
	 * * // * String // *
	 * expectedWarningMsgFour="Please enter a part followed by '@'. '@gmail.com' is incomplete."
	 * // * ; String // *
	 * actualWarningMsgFour=driver.findElement(By.id("input-email")).getAttribute(
	 * // * "validationMessage"); //System.out.println(actualWarningMsgFour); // *
	 * Assert.assertEquals(expectedWarningMsgFour,actualWarningMsgFour ); } else //
	 * * if(browserName.equalsIgnoreCase("firefox")) { String // *
	 * expectedWarningMsgOne="Please enter an email address."; String // *
	 * actualWarningMsgOne=driver.findElement(By.id("input-email")).getAttribute( //
	 * * "validationMessage"); // System.out.println(actualWarningMsgOne); // * // *
	 * Assert.assertEquals(expectedWarningMsgOne,actualWarningMsgOne ); // * // *
	 * driver.findElement(By.id("input-email")).clear(); // *
	 * driver.findElement(By.id("input-email")).sendKeys(prop.getProperty( // *
	 * "InvalidEmailTwo")); // *
	 * driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click(); //
	 * * // * String expectedWarningMsgTwo="Please enter an email address."; String
	 * // *
	 * actualWarningMsgTwo=driver.findElement(By.id("input-email")).getAttribute( //
	 * * "validationMessage"); //System.out.println(actualWarningMsgTwo); // *
	 * Assert.assertEquals(expectedWarningMsgTwo,actualWarningMsgTwo ); // * // *
	 * driver.findElement(By.id("input-email")).clear(); // *
	 * driver.findElement(By.id("input-email")).sendKeys(prop.getProperty( // *
	 * "InvalidEmailThree")); // *
	 * driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click(); //
	 * * // * String expectedWarningMsgThree="Please enter an email address.";
	 * String // *
	 * actualWarningMsgThree=driver.findElement(By.id("input-email")).getAttribute(
	 * // * "validationMessage"); //System.out.println(actualWarningMsgThree); // *
	 * Assert.assertEquals(expectedWarningMsgThree,actualWarningMsgThree ); // * //
	 * * driver.findElement(By.id("input-email")).clear(); // * // *
	 * driver.findElement(By.id("input-email")).sendKeys(prop.getProperty( // *
	 * "InvalidEmailFour")); // *
	 * driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click(); //
	 * * // * String expectedWarningMsgFour="Please enter an email address."; String
	 * // *
	 * actualWarningMsgFour=driver.findElement(By.id("input-email")).getAttribute(
	 * // * "validationMessage"); //System.out.println(actualWarningMsgFour); // *
	 * Assert.assertEquals(expectedWarningMsgFour,actualWarningMsgFour ); // * // *
	 * } } 
	  @Test public void // *
	 * VerifyAllTheMandatoryFieldsInTheRegisterAccountPageAreMarkedWithRedColorAsteriskSymbol
	 * // * () { WebElement FirstNameLabel = // *
	 * driver.findElement(By.cssSelector("label[for='input-firstname']")); // *
	 * WebElement LastNameLabel = // *
	 * driver.findElement(By.cssSelector("label[for='input-lastname']")); WebElement
	 * // * EmailLabel =
	 * driver.findElement(By.cssSelector("label[for='input-email']")); // *
	 * WebElement TelephoneLabel = // *
	 * driver.findElement(By.cssSelector("label[for='input-telephone']")); // *
	 * WebElement PasswordLabel = // *
	 * driver.findElement(By.cssSelector("label[for='input-password']")); WebElement
	 * // * PasswordConfirmLabel = // *
	 * driver.findElement(By.cssSelector("label[for='input-confirm']")); // * // *
	 * String expectedLabelContent="\"* \""; String expectedColor="rgb(255, 0, 0)";
	 * // * // * JavascriptExecutor js = (JavascriptExecutor)driver; String // *
	 * firstNameLabelContent = (String)js. // *
	 * executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');"
	 * // * , FirstNameLabel); Assert.assertEquals(expectedLabelContent, // *
	 * firstNameLabelContent); // * // * String lastNameLabelcontent = (String)js.
	 * // *
	 * executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');"
	 * // * , LastNameLabel); Assert.assertEquals(expectedLabelContent, // *
	 * lastNameLabelcontent); // * // * String emailLabelContent = (String)js. // *
	 * executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');"
	 * // * , EmailLabel); Assert.assertEquals(expectedLabelContent,
	 * emailLabelContent); // * // * String telephoneLabelContent = (String)js. // *
	 * executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');"
	 * // * ,TelephoneLabel ); Assert.assertEquals(expectedLabelContent, // *
	 * telephoneLabelContent); // * // * String passwordLabelContent = (String)js.
	 * // *
	 * executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');"
	 * // * , PasswordLabel); Assert.assertEquals(expectedLabelContent, // *
	 * passwordLabelContent); // * // * String passwordConfirmlabelcontent =
	 * (String)js. // *
	 * executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');"
	 * // * , PasswordConfirmLabel); Assert.assertEquals(expectedLabelContent, // *
	 * passwordConfirmlabelcontent); // * // * String firstNameLabelContentColor =
	 * (String)js. // *
	 * executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');"
	 * // * ,FirstNameLabel ); Assert.assertEquals(expectedColor, // *
	 * firstNameLabelContentColor); // * // * String lastNameLabelContentColor =
	 * (String)js. // *
	 * executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');"
	 * // * ,LastNameLabel ); Assert.assertEquals(expectedColor, // *
	 * lastNameLabelContentColor); // * // * String emailLabelContentColor =
	 * (String)js. // *
	 * executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');"
	 * // * , EmailLabel); Assert.assertEquals(expectedColor,
	 * emailLabelContentColor); // * // * String telephoneLabelContentColor =
	 * (String)js. // *
	 * executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');"
	 * // * , TelephoneLabel); Assert.assertEquals(expectedColor, // *
	 * telephoneLabelContentColor); // * // * String passwordLabelContentColor =
	 * (String)js. // *
	 * executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');"
	 * // * , PasswordLabel); Assert.assertEquals(expectedColor, // *
	 * passwordLabelContentColor); // * // * String passwordConfirmLabelContentColor
	 * = (String)js. // *
	 * executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');"
	 * // * , PasswordConfirmLabel); Assert.assertEquals(expectedColor, // *
	 * passwordConfirmLabelContentColor); // * // * // * } // * //
	 * * @Test(dataProvider="password supplier") public void // *
	 * VerifyWhetherThePasswordFieldsInTheRegisterAccountPageAreFollowingPasswordComplexityStandards
	 * // * (String pwd) { // * // *
	 * driver.findElement(By.id("input-firstname")).sendKeys(prop.getProperty( // *
	 * "FirstName")); // *
	 * driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty( // *
	 * "LastName")); // *
	 * driver.findElement(By.id("input-email")).sendKeys(CommonUtilities. // *
	 * getGenerateNewMail()); // *
	 * driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty( // *
	 * "Telephone")); driver.findElement(By.id("input-password")).sendKeys(pwd); //
	 * * driver.findElement(By.id("input-confirm")).sendKeys(pwd); // *
	 * driver.findElement(By.name("agree")).click(); // *
	 * driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click(); //
	 * * String // *
	 * expectedWarning="Enter Password Which Follow Password Complexity Standard!";
	 * // * // * boolean b=false; try { String actualWarning=driver.findElement(By.
	 * // *
	 * xpath("//div[text()='Password confirmation does not match password!']")). //
	 * * getText(); if(actualWarning.equals(expectedWarning)) b=true; } // *
	 * catch(Exception e) { b=false; } Assert.assertTrue(b); } // * //
	 * * @DataProvider(name="password supplier") public Object[][] supplyPasswords()
	 * // * // * { Object [][] data= // *
	 * {{"12345"},{"abcdefghi"},{"abcd1234"},{"abcd123$"},{"ABCD456#"}}; return // *
	 * data; } // * // * @Test public void VerifyHightAndFieldOfTextField() { String
	 * // * First_Name_Actual_Width = // *
	 * driver.findElement(By.xpath("//input[@id='input-firstname']")).getCssValue(
	 * // * "width"); String First_Name_Actual_Hieght=driver.findElement(By.xpath(
	 * // * "//input[@id='input-firstname']")).getCssValue("height"); String // *
	 * ExpectedFNWidth="701.25px"; String ExpectedFNHieght="34px"; // *
	 * Assert.assertEquals(First_Name_Actual_Width, ExpectedFNWidth); // *
	 * Assert.assertEquals(First_Name_Actual_Hieght, ExpectedFNHieght); // * // *
	 * String Last_Name_Actual_Width=driver.findElement(By.xpath( // *
	 * "//input[@id='input-lastname']")).getCssValue("width"); String // *
	 * Last_Name_Actual_Hieght=driver.findElement(By.xpath( // *
	 * "//input[@id='input-lastname']")).getCssValue("height"); String // *
	 * ExpectedLNWidth="701.25px"; String ExpectedLNHieght="34px"; // * // *
	 * Assert.assertEquals(Last_Name_Actual_Width, ExpectedLNWidth); // *
	 * Assert.assertEquals(Last_Name_Actual_Hieght, ExpectedLNHieght); // * // * //
	 * * String // *
	 * E_Mail_Actual_Width=driver.findElement(By.xpath("//input[@id='input-email']")
	 * // * ).getCssValue("width"); String // *
	 * E_Mail_Actual_Hieght=driver.findElement(By.xpath("//input[@id='input-email']"
	 * // * )).getCssValue("height"); String ExpectedEmailWidth="701.25px"; String
	 * // * ExpectedEmailHieght="34px"; // * // *
	 * Assert.assertEquals(E_Mail_Actual_Width, ExpectedEmailWidth); // *
	 * Assert.assertEquals(E_Mail_Actual_Hieght, ExpectedEmailHieght); // * // *
	 * String Telephone_Actual_Width=driver.findElement(By.xpath( // *
	 * "//input[@id='input-telephone']")).getCssValue("width"); String // *
	 * Telephone_Actual_Hieght=driver.findElement(By.xpath( // *
	 * "//input[@id='input-telephone']")).getCssValue("height"); // *
	 * System.out.println(Telephone_Actual_Width+" "+Telephone_Actual_Hieght); // *
	 * String ExpectedTelephoneWidth="701.25px"; String // *
	 * ExpectedTelephoneHieght="34px"; // * // *
	 * Assert.assertEquals(ExpectedTelephoneWidth, Telephone_Actual_Width); // *
	 * Assert.assertEquals(ExpectedTelephoneHieght, Telephone_Actual_Hieght); // *
	 * // * String Password_Actual_Width=driver.findElement(By.xpath( // *
	 * "//input[@id='input-password']")).getCssValue("width"); String // *
	 * Password_Actual_Hieght=driver.findElement(By.xpath( // *
	 * "//input[@id='input-password']")).getCssValue("height"); // * // * String
	 * ExpectedPasswordWidth="701.25px"; String // * ExpectedPasswordHieght="34px";
	 * // * // * Assert.assertEquals(Password_Actual_Width, ExpectedPasswordWidth);
	 * // * Assert.assertEquals(Password_Actual_Hieght, ExpectedPasswordHieght); //
	 * * // * String Password_Confirm_Actual_Width=driver.findElement(By.xpath( // *
	 * "//input[@id='input-confirm']")).getCssValue("width"); String // *
	 * Password_Confirm_Actual_Hieght=driver.findElement(By.xpath( // *
	 * "//input[@id='input-confirm']")).getCssValue("height"); String // *
	 * ExpectedPasswordConfirmWidth="701.25px"; String // *
	 * ExpectedPasswordConfirmHieght="34px"; // * // *
	 * Assert.assertEquals(Password_Confirm_Actual_Width, // *
	 * ExpectedPasswordConfirmWidth); // *
	 * Assert.assertEquals(Password_Confirm_Actual_Hieght, // *
	 * ExpectedPasswordConfirmHieght); // * // * } // * // * @Test public void
	 * VerifyWarningMsgOfTextField() // * // * { //check Zero character Warning Msg
	 * String // *
	 * expectedFirstNameWarningMsg="First Name must be between 1 and 32 characters!"
	 * // * ; // *
	 * driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("");
	 * // * driver.findElement(By.xpath("//input[@value='Continue']")).click(); try
	 * { // * Thread.sleep(500); } catch (InterruptedException e1) { // TODO
	 * Auto-generated // * catch block e1.printStackTrace(); } // small pause to
	 * allow reload // * // * WebElement fNameWarning = driver.findElement(By. // *
	 * xpath("//div[@class='col-sm-10']//div[text()='First Name must be between 1 and 32 characters!']"
	 * // * )); String actualFirstNameWarningMsg=fNameWarning.getText(); // *
	 * Assert.assertEquals(expectedFirstNameWarningMsg, actualFirstNameWarningMsg);
	 * // * // * //check for 33 Character // *
	 * driver.findElement(By.xpath("//input[@id='input-firstname']")).clear(); // *
	 * driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys( // *
	 * "abcdefghijklmnopqrstuvwxyzabcdefg"); // *
	 * driver.findElement(By.xpath("//input[@value='Continue']")).click(); try { //
	 * * Thread.sleep(500); } catch (InterruptedException e1) { // TODO
	 * Auto-generated // * catch block e1.printStackTrace(); } // small pause to
	 * allow reload // * // * fNameWarning = driver.findElement(By. // *
	 * xpath("//div[@class='col-sm-10']//div[text()='First Name must be between 1 and 32 characters!']"
	 * // * )); actualFirstNameWarningMsg=fNameWarning.getText(); // *
	 * Assert.assertEquals(expectedFirstNameWarningMsg, actualFirstNameWarningMsg);
	 * // * // * //check for 1 Character // *
	 * driver.findElement(By.xpath("//input[@id='input-firstname']")).clear(); // *
	 * driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("a");
	 * // * driver.findElement(By.xpath("//input[@value='Continue']")).click(); // *
	 * // * // * boolean firstNameWarningStatus=false; try { // *
	 * firstNameWarningStatus=driver.findElement(By. // *
	 * xpath("//div[@class='col-sm-10']//div[text()='First Name must be between 1 and 32 characters!']"
	 * // * )).isDisplayed(); } catch(Exception e) { firstNameWarningStatus=false; }
	 * // * Assert.assertFalse(firstNameWarningStatus); // * // * //check for 32
	 * Characters // *
	 * driver.findElement(By.xpath("//input[@id='input-firstname']")).clear(); // *
	 * driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys( // *
	 * "abcdefghijklmnopqrstuvwxyzabcdef"); // *
	 * driver.findElement(By.xpath("//input[@value='Continue']")).click(); try { //
	 * * Thread.sleep(500); } catch (InterruptedException e1) { // TODO
	 * Auto-generated // * catch block e1.printStackTrace(); } // small pause to
	 * allow reload // * // * // * try {
	 * firstNameWarningStatus=driver.findElement(By. // *
	 * xpath("//div[@class='col-sm-10']//div[text()='First Name must be between 1 and 32 characters!']"
	 * // * )).isDisplayed(); } catch(Exception e) { firstNameWarningStatus=false; }
	 * // * Assert.assertFalse(firstNameWarningStatus); // * // * //Verification For
	 * Last Name Field // zero Character check String // *
	 * expectedLastNameWarningMsg="Last Name must be between 1 and 32 characters!";
	 * // *
	 * driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("");
	 * // * driver.findElement(By.xpath("//input[@value='Continue']")).click(); try
	 * { // * Thread.sleep(500); } catch (InterruptedException e1) { // TODO
	 * Auto-generated // * catch block e1.printStackTrace(); } // small pause to
	 * allow reload // * // * WebElement warningLastName = driver.findElement(By. //
	 * *
	 * xpath("//div[@class='col-sm-10']//div[text()='Last Name must be between 1 and 32 characters!']"
	 * // * )); String actualWarningLastName = warningLastName.getText(); // *
	 * Assert.assertEquals(expectedLastNameWarningMsg, actualWarningLastName); // *
	 * // * //check for 33 Character // *
	 * driver.findElement(By.xpath("//input[@id='input-lastname']")).clear(); // *
	 * driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys( // *
	 * "abcdefghijklmnopqrstuvwxyzabcdefg"); // *
	 * driver.findElement(By.xpath("//input[@value='Continue']")).click(); try { //
	 * * Thread.sleep(500); } catch (InterruptedException e1) { // TODO
	 * Auto-generated // * catch block e1.printStackTrace(); } // small pause to
	 * allow reload // * // * warningLastName = driver.findElement(By. // *
	 * xpath("//div[@class='col-sm-10']//div[text()='Last Name must be between 1 and 32 characters!']"
	 * // * )); actualWarningLastName = warningLastName.getText(); // *
	 * Assert.assertEquals(expectedLastNameWarningMsg, actualWarningLastName); // *
	 * // * //1 Character check // * // *
	 * driver.findElement(By.xpath("//input[@id='input-lastname']")).clear(); // *
	 * driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("a");
	 * // * driver.findElement(By.xpath("//input[@value='Continue']")).click(); try
	 * { // * Thread.sleep(500); } catch (InterruptedException e1) { // TODO
	 * Auto-generated // * catch block e1.printStackTrace(); } // small pause to
	 * allow reload // * // * // * boolean lastNamewarningMsg=false; // * // * try {
	 * lastNamewarningMsg=driver.findElement(By. // *
	 * xpath("//div[@class='col-sm-10']//div[text()='Last Name must be between 1 and 32 characters!']"
	 * // * )).isDisplayed(); } catch(Exception e) { lastNamewarningMsg=false; } //
	 * * Assert.assertFalse(lastNamewarningMsg); // * // * //32 Character check // *
	 * driver.findElement(By.xpath("//input[@id='input-lastname']")).clear(); // *
	 * driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys( // *
	 * "abcdefghijklmnopqrstuvwxyzabcdef"); // *
	 * driver.findElement(By.xpath("//input[@value='Continue']")).click(); try { //
	 * * Thread.sleep(500); } catch (InterruptedException e1) { // TODO
	 * Auto-generated // * catch block e1.printStackTrace(); } // small pause to
	 * allow reload // * // * try { lastNamewarningMsg=driver.findElement(By. // *
	 * xpath("//div[@class='col-sm-10']//div[text()='Last Name must be between 1 and 32 characters!']"
	 * // * )).isDisplayed(); } catch(Exception e) { lastNamewarningMsg=false; } //
	 * * Assert.assertFalse(lastNamewarningMsg); // * // * //Verification of Email
	 * field // *
	 * driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys( // *
	 * "abcdefghijklmnopqrstuvwxyzabcdefgh@gmail.com"); // *
	 * driver.findElement(By.xpath("//input[@value='Continue']")).click(); try { //
	 * * Thread.sleep(500); } catch (InterruptedException e1) { // TODO
	 * Auto-generated // * catch block e1.printStackTrace(); } // small pause to
	 * allow reload // * // * boolean expectedEmailWarningMsg=false; try { // *
	 * expectedEmailWarningMsg=driver.findElement(By. // *
	 * xpath("//div[@class='col-sm-10']//div[text()='E-Mail Address does not appear to be valid!']"
	 * // * )).isDisplayed(); } catch(Exception e) { expectedEmailWarningMsg=false;
	 * } // * Assert.assertFalse(expectedEmailWarningMsg); // * // * //Verification
	 * for Telephone Field String // *
	 * expectedTelephoneWaringMsg="Telephone must be between 3 and 32 characters!";
	 * // *
	 * driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("1");
	 * // * driver.findElement(By.xpath("//input[@value='Continue']")).click(); try
	 * { // * Thread.sleep(500); } catch (InterruptedException e1) { // TODO
	 * Auto-generated // * catch block e1.printStackTrace(); } // small pause to
	 * allow reload // * // * WebElement telephoneWarningMsg =
	 * driver.findElement(By. // *
	 * xpath("//div[@class='col-sm-10']//div[text()='Telephone must be between 3 and 32 characters!']"
	 * // * )); String actualTelephoneWarningMsg = telephoneWarningMsg.getText(); //
	 * * Assert.assertEquals(expectedTelephoneWaringMsg, actualTelephoneWarningMsg);
	 * // * // * //verify more than 32 Character // *
	 * driver.findElement(By.xpath("//input[@id='input-telephone']")).clear(); // *
	 * driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys( // *
	 * "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); // *
	 * driver.findElement(By.xpath("//input[@value='Continue']")).click(); try { //
	 * * Thread.sleep(500); } catch (InterruptedException e1) { // TODO
	 * Auto-generated // * catch block e1.printStackTrace(); } // small pause to
	 * allow reload // * // * telephoneWarningMsg = driver.findElement(By. // *
	 * xpath("//div[@class='col-sm-10']//div[text()='Telephone must be between 3 and 32 characters!']"
	 * // * )); actualTelephoneWarningMsg = telephoneWarningMsg.getText(); // *
	 * Assert.assertEquals(expectedTelephoneWaringMsg, actualTelephoneWarningMsg);
	 * // * // * //verify 3 Character // *
	 * driver.findElement(By.xpath("//input[@id='input-telephone']")).clear(); // *
	 * driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("123"
	 * // * ); driver.findElement(By.xpath("//input[@value='Continue']")).click();
	 * try { // * Thread.sleep(500); } catch (InterruptedException e1) { // TODO
	 * Auto-generated // * catch block e1.printStackTrace(); } // small pause to
	 * allow reload // * // * // * boolean expectedTelephoneWarningMsg=false; try {
	 * // * expectedTelephoneWarningMsg=driver.findElement(By. // *
	 * xpath("//div[@class='col-sm-10']//div[text()='Telephone must be between 3 and 32 characters!']"
	 * // * )).isDisplayed(); } catch(Exception e) {
	 * expectedTelephoneWarningMsg=false; } // *
	 * Assert.assertFalse(expectedTelephoneWarningMsg); //verify 32 Character // *
	 * driver.findElement(By.xpath("//input[@id='input-telephone']")).clear(); // *
	 * driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys( // *
	 * "12345678912345678912345678912345"); // *
	 * driver.findElement(By.xpath("//input[@value='Continue']")).click(); try { //
	 * * Thread.sleep(500); } catch (InterruptedException e1) { // TODO
	 * Auto-generated // * catch block e1.printStackTrace(); } // small pause to
	 * allow reload // * // * // * try {
	 * expectedTelephoneWarningMsg=driver.findElement(By. // *
	 * xpath("//div[@class='col-sm-10']//div[text()='Telephone must be between 3 and 32 characters!']"
	 * // * )).isDisplayed(); } catch(Exception e) {
	 * expectedTelephoneWarningMsg=false; } // *
	 * Assert.assertFalse(expectedTelephoneWarningMsg); // * // * //verify Password
	 * Field for 1 character String // *
	 * expectedPasswordWarningMsg="Password must be between 4 and 20 characters!";
	 * // *
	 * driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("1");
	 * // * driver.findElement(By.xpath("//input[@value='Continue']")).click(); try
	 * { // * Thread.sleep(500); } catch (InterruptedException e1) { // TODO
	 * Auto-generated // * catch block e1.printStackTrace(); } // small pause to
	 * allow reload // * // * WebElement passwordWarningMsg = driver.findElement(By.
	 * // *
	 * xpath("//div[@class='col-sm-10']//div[contains(text(),'Password must be between 4 and 20 characters!')]"
	 * // * )); String actualPasswordWarningMsg = passwordWarningMsg.getText(); // *
	 * //Assert.assertEquals(expectedPasswordWarningMsg, driver.findElement(By. // *
	 * xpath("//div[@class='col-sm-10']//div[contains(text(),'Password must be between 4 and 20 characters!')]"
	 * // * )).getText()); //above line give StaleElementReferenceException: stale
	 * // * element reference: stale element not found so solve this issue we store
	 * // * variable and hen use //// 🔁 Re-find element after page update // * // *
	 * Assert.assertEquals(expectedPasswordWarningMsg, actualPasswordWarningMsg); //
	 * * // * // * //verify 4 character // * // *
	 * driver.findElement(By.xpath("//input[@id='input-password']")).clear(); // *
	 * driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("1234"
	 * // * ); driver.findElement(By.xpath("//input[@value='Continue']")).click();
	 * try { // * Thread.sleep(500); } catch (InterruptedException e1) { // TODO
	 * Auto-generated // * catch block e1.printStackTrace(); } // small pause to
	 * allow reload // * // * boolean expectedPasswordWarninhMsg=false; try {
	 * expectedPasswordWarninhMsg // * =driver.findElement(By. // *
	 * xpath("//div[@class='col-sm-10']//div[contains(text(),'Password must be between 4 and 20 characters!')]"
	 * // * )).isDisplayed(); } catch(Exception e) {
	 * expectedPasswordWarninhMsg=false; } // * // *
	 * Assert.assertFalse(expectedPasswordWarninhMsg); // * // * //verify 20
	 * character // * // *
	 * driver.findElement(By.xpath("//input[@id='input-password']")).clear(); // *
	 * driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys( // *
	 * "12345123451234512345"); // *
	 * driver.findElement(By.xpath("//input[@value='Continue']")).click(); try { //
	 * * Thread.sleep(500); } catch (InterruptedException e1) { // TODO
	 * Auto-generated // * catch block e1.printStackTrace(); } // small pause to
	 * allow reload // * // * // * try { expectedPasswordWarninhMsg
	 * =driver.findElement(By. // *
	 * xpath("//div[@class='col-sm-10']//div[contains(text(),'Password must be between 4 and 20 characters!')]"
	 * // * )).isDisplayed(); } catch(Exception e) {
	 * expectedPasswordWarninhMsg=false; } // * // *
	 * Assert.assertFalse(expectedPasswordWarninhMsg); // * // * } // * // * @Test
	 * public void VerifyButtonTextColorBackgroundColorFontSize() { WebElement // *
	 * continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
	 * // * boolean buttonAvailabilityStatus=continueButton.isDisplayed(); // *
	 * System.out.println(buttonAvailabilityStatus); // *
	 * if(browserName.equalsIgnoreCase("chrome")) { String // *
	 * expectedTextColorChrome="rgba(255, 255, 255, 1)"; String // *
	 * actualButtonTextColor = continueButton.getCssValue("color"); // *
	 * Assert.assertEquals(actualButtonTextColor, expectedTextColorChrome); // * //
	 * * String actualButtonBackgroundColor = // *
	 * continueButton.getCssValue("background-color"); String // *
	 * expectedButtonBackgroundColor="rgba(34, 154, 200, 1)"; // *
	 * Assert.assertEquals(actualButtonBackgroundColor, // *
	 * expectedButtonBackgroundColor); } else // *
	 * if(browserName.equalsIgnoreCase("firefox")) { String // *
	 * expectedTextColor="rgb(255, 255, 255)"; String actualButtonTextColor = // *
	 * continueButton.getCssValue("color"); // *
	 * Assert.assertEquals(actualButtonTextColor, expectedTextColor); // * // *
	 * String actualButtonBackgroundColor = // *
	 * continueButton.getCssValue("background-color"); String // *
	 * expectedButtonBackgroundColor="rgb(34, 154, 200)"; // *
	 * Assert.assertEquals(actualButtonBackgroundColor, // *
	 * expectedButtonBackgroundColor); // * // * } String actualButtonFontSize =
	 * continueButton.getCssValue("font-size"); // * String expectedFontSize="12px";
	 * Assert.assertEquals(actualButtonFontSize, // * expectedFontSize); // * // *
	 * // * } // * // * @Test public void // *
	 * VerifyThePasswordTextEnteredIntoThePasswordAndPasswordConfirmFieldOfRegisterAccountFunctionalityIsToggledToHideItsVisibility
	 * // * () { //logic attribute value is present 'password' for toggled or hiding
	 * and // * compare getDomeAttributeValue() // *
	 * Assert.assertEquals(driver.findElement(By.id("input-password")). // *
	 * getDomAttribute("type"), "password"); // *
	 * Assert.assertEquals(driver.findElement(By.id("input-confirm")). // *
	 * getDomAttribute("type"), "password"); // * // * } // * // * @Test public void
	 * // *
	 * VerifyNavigatingToOtherPagesUsingTheOptionsOrLinksProvidedOnTheRegisterAccountPage
	 * // * () { driver.findElement(By. // *
	 * xpath("//ul[@class='list-inline']//i[@class='fa fa-phone']")).click(); // *
	 * Assert.assertEquals(driver.getTitle(), "Contact Us"); // *
	 * driver.navigate().back(); // * // * driver.findElement(By. // *
	 * xpath("//a[@id='wishlist-total']//i[@class='fa fa-heart']")).click(); // *
	 * Assert.assertEquals(driver.getTitle(), "Account Login"); // *
	 * driver.navigate().back(); // * // * driver.findElement(By. // *
	 * xpath("//span[@class='hidden-xs hidden-sm hidden-md'][text()='Wish List (0)']"
	 * // * )).click(); Assert.assertEquals(driver.getTitle(), "Account Login"); //
	 * * driver.navigate().back(); // * // * driver.findElement(By. // *
	 * xpath("//a[@title='Shopping Cart']//i[@class='fa fa-shopping-cart']")).click(
	 * // * ); Assert.assertEquals(driver.getTitle(), "Shopping Cart"); // *
	 * driver.navigate().back(); // * // * driver.findElement(By. // *
	 * xpath("//span[text()='Shopping Cart'][@class='hidden-xs hidden-sm hidden-md']"
	 * // * )).click(); Assert.assertEquals(driver.getTitle(), "Shopping Cart"); //
	 * * driver.navigate().back(); // * // *
	 * driver.findElement(By.xpath("//a[@title='Checkout']//i[@class='fa fa-share']"
	 * // * )).click(); Assert.assertEquals(driver.getTitle(), "Shopping Cart"); //
	 * * driver.navigate().back(); // * // * driver.findElement(By. // *
	 * xpath("//span[@class='hidden-xs hidden-sm hidden-md'][text()='Checkout']")).
	 * // * click(); Assert.assertEquals(driver.getTitle(), "Shopping Cart"); // *
	 * driver.navigate().back(); // * // *
	 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1)); // *
	 * driver.findElement(By.linkText("Qafox.com")).click(); // *
	 * Assert.assertEquals(driver.getTitle(), "Your Store"); // *
	 * driver.navigate().back(); // *
	 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1)); // * // *
	 * driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")). //
	 * * click(); driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
	 * // * Assert.assertEquals(driver.getTitle(), "Search");
	 * driver.navigate().back(); // *
	 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); // *
	 * System.out.println(driver.getTitle()); // * // * //WebDriverWait wait = new
	 * WebDriverWait(driver, Duration.ofSeconds(10)); // * //WebElement homeIcon =
	 * // * wait.until(ExpectedConditions.elementToBeClickable(By. // *
	 * xpath("//i[@class='fa fa-home']"))); // //((JavascriptExecutor) // *
	 * driver).executeScript("arguments[0].scrollIntoView(true);", homeIcon); // *
	 * //((JavascriptExecutor) // *
	 * driver).executeScript("document.activeElement.blur();"); // // *
	 * //homeIcon.click(); // // //// Home icon (fixed with explicit wait) // *
	 * ////homeIcon.click(); //Assert.assertEquals(driver.getTitle(), "Your Store");
	 * // * //driver.navigate().back(); // // *
	 * ////driver.findElement(By.xpath("//i[@class='fa fa-home']")).click(); // *
	 * ////driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); // *
	 * ////Assert.assertEquals(driver.getTitle(), "Your Store"); // *
	 * ////driver.navigate().back(); // // //driver.findElement(By.xpath( // *
	 * "//ul[@class='breadcrumb']//a[text()='Account']")).click(); // *
	 * //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1)); // // *
	 * //Assert.assertEquals(driver.getTitle(), "Account Login"); // *
	 * //driver.navigate().back(); // // *
	 * //driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")
	 * // * ).click(); // *
	 * //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1)); // // *
	 * //Assert.assertEquals(driver.getTitle(), "Account Login"); // *
	 * //driver.navigate().back(); // driver.findElement(By.xpath( // *
	 * "//div[@class='list-group']//a[text()='Register']")).click(); // *
	 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1)); // *
	 * Assert.assertEquals(driver.getTitle(), "Register Account"); // *
	 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1)); // * // *
	 * driver.findElement(By. // *
	 * xpath("//div[@class='list-group']//a[text()='Forgotten Password']")).click();
	 * // * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1)); // *
	 * Assert.assertEquals(driver.getTitle(), "Forgot Your Password?"); // *
	 * driver.navigate().back(); // * // * driver.findElement(By. // *
	 * xpath("//div[@class='list-group']//a[text()='My Account']")).click(); // *
	 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1)); // * // *
	 * Assert.assertEquals(driver.getTitle(), "Account Login"); // *
	 * driver.navigate().back(); // * // * driver.findElement(By. // *
	 * xpath("//div[@class='list-group']//a[text()='Address Book']")).click(); // *
	 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1)); // * // *
	 * Assert.assertEquals(driver.getTitle(), "Account Login"); // *
	 * driver.navigate().back(); // * // * driver.findElement(By. // *
	 * xpath("//div[@class='list-group']//a[text()='Wish List']")).click(); // *
	 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1)); // * // *
	 * Assert.assertEquals(driver.getTitle(), "Account Login"); // *
	 * driver.navigate().back(); // * // * driver.findElement(By. // *
	 * xpath("//div[@class='list-group']//a[text()='Order History']")).click(); // *
	 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1)); // * // *
	 * Assert.assertEquals(driver.getTitle(), "Account Login"); // *
	 * driver.navigate().back(); // * // * driver.findElement(By.xpath( // *
	 * "//div[@class='list-group']//a[text()='Downloads']")).click(); // *
	 * Assert.assertEquals(driver.getTitle(), "Account Login"); // *
	 * driver.navigate().back(); // * // * driver.findElement(By. // *
	 * xpath("//div[@class='list-group']//a[text()='Recurring payments']")).click();
	 * // * Assert.assertEquals(driver.getTitle(), "Account Login"); // *
	 * driver.navigate().back(); // * // * driver.findElement(By. // *
	 * xpath("//div[@class='list-group']//a[text()='Reward Points']")).click(); // *
	 * Assert.assertEquals(driver.getTitle(), "Account Login"); // *
	 * driver.navigate().back(); // * // *
	 * driver.findElement(By.xpath("//div[@class='list-group']//a[text()='Returns']"
	 * // * )).click(); Assert.assertEquals(driver.getTitle(), "Account Login"); //
	 * * driver.navigate().back(); // * // * driver.findElement(By.xpath( // *
	 * "//div[@class='list-group']//a[text()='Transactions']")).click(); // *
	 * Assert.assertEquals(driver.getTitle(), "Account Login"); // *
	 * driver.navigate().back(); // * // * driver.findElement(By.xpath( // *
	 * "//div[@class='list-group']//a[text()='Newsletter']")).click(); // *
	 * Assert.assertEquals(driver.getTitle(), "Account Login"); // *
	 * driver.navigate().back(); // * // *
	 * driver.findElement(By.xpath("//footer//a[text()='About Us']")).click(); // *
	 * Assert.assertEquals(driver.getTitle(), "About Us"); driver.navigate().back();
	 * // * // *
	 * driver.findElement(By.xpath("//footer//a[text()='Delivery Information']")).
	 * // * click(); Assert.assertEquals(driver.getTitle(), "Delivery Information");
	 * // * driver.navigate().back(); // * // *
	 * driver.findElement(By.xpath("//footer//a[text()='Privacy Policy']")).click();
	 * // * Assert.assertEquals(driver.getTitle(), "Privacy Policy"); // *
	 * driver.navigate().back(); // * // *
	 * driver.findElement(By.xpath("//footer//a[text()='Terms & Conditions']")). //
	 * * click(); Assert.assertEquals(driver.getTitle(), "Terms & Conditions"); // *
	 * driver.navigate().back(); // * // *
	 * driver.findElement(By.xpath("//footer//a[text()='Contact Us']")).click(); //
	 * * Assert.assertEquals(driver.getTitle(), "Contact Us"); // *
	 * driver.navigate().back(); // * // *
	 * driver.findElement(By.xpath("//footer//a[text()='Returns']")).click(); // *
	 * Assert.assertEquals(driver.getTitle(), "Product Returns"); // *
	 * driver.navigate().back(); // * // *
	 * driver.findElement(By.xpath("//footer//a[text()='Site Map']")).click(); // *
	 * Assert.assertEquals(driver.getTitle(), "Site Map"); driver.navigate().back();
	 * // * // *
	 * driver.findElement(By.xpath("//footer//a[text()='Brands']")).click(); // *
	 * Assert.assertEquals(driver.getTitle(), "Find Your Favorite Brand"); // *
	 * driver.navigate().back(); // * // *
	 * driver.findElement(By.xpath("//footer//a[text()='Gift Certificates']")).click
	 * // * (); Assert.assertEquals(driver.getTitle(),
	 * "Purchase a Gift Certificate"); // * driver.navigate().back(); // * // *
	 * driver.findElement(By.xpath("//footer//a[text()='Affiliate']")).click(); // *
	 * Assert.assertEquals(driver.getTitle(), "Affiliate Program"); // *
	 * driver.navigate().back(); // * // *
	 * driver.findElement(By.xpath("//footer//a[text()='Specials']")).click(); // *
	 * Assert.assertEquals(driver.getTitle(), "Special Offers"); // *
	 * driver.navigate().back(); // * // *
	 * driver.findElement(By.xpath("//footer//a[text()='My Account']")).click(); //
	 * * Assert.assertEquals(driver.getTitle(), "Account Login"); // *
	 * driver.navigate().back(); // * // *
	 * driver.findElement(By.xpath("//footer//a[text()='Order History']")).click();
	 * // * Assert.assertEquals(driver.getTitle(), "Account Login"); // *
	 * driver.navigate().back(); // * // *
	 * driver.findElement(By.xpath("//footer//a[text()='Wish List']")).click(); // *
	 * Assert.assertEquals(driver.getTitle(), "Account Login"); // *
	 * driver.navigate().back(); // * // *
	 * driver.findElement(By.xpath("//footer//a[text()='Newsletter']")).click(); //
	 * * Assert.assertEquals(driver.getTitle(), "Account Login"); // *
	 * driver.navigate().back(); // * // * WebElement homeLink =
	 * driver.findElement(By. // *
	 * xpath("//ul[@class='breadcrumb']//a[i[@class='fa fa-home']]")); // *
	 * ((JavascriptExecutor) // *
	 * driver).executeScript("arguments[0].scrollIntoView(true);", homeLink); // *
	 * homeLink.click(); Assert.assertEquals(driver.getTitle(), "Your Store"); // *
	 * driver.navigate().back(); // * // * WebElement Account = // *
	 * driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Account']")
	 * // * ); ((JavascriptExecutor)driver).executeScript( // *
	 * "arguments[0].scrollIntoView(true);", Account); Account.click(); // *
	 * Assert.assertEquals(driver.getTitle(), "Account Login"); // *
	 * driver.navigate().back(); // * // * //clickInterceptedException //WebElement
	 * Login = // *
	 * driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']"));
	 * // * //((JavascriptExecutor)driver).executeScript( // *
	 * "arguments[0].scrollIntoView(true);", Login); //Login.click(); // *
	 * //Assert.assertEquals(driver.getTitle(), "Account Login"); // *
	 * //driver.navigate().back(); // * // * } // * // * @Test public void // *
	 * VerifyTheBreadcrumbPageHeadingPageURLPageTitleOfRegisterAccountPage() { // *
	 * //Title of Page System.out.println(driver.getTitle()); // * // * //URL Of
	 * Page System.out.println(driver.getCurrentUrl()); // * // * //verify
	 * Breadcrumb boolean status = // *
	 * driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Register']"
	 * // * )).isDisplayed();
	 * System.out.println("Breadcrumb  availability "+status); } // * //
	 * * @Test(dataProvider="supply Environments") public void // *
	 * VerifyRegisterAccountFunctionalityInAllTheSupportedEnvironments(String // *
	 * browserName) { if(browserName.equalsIgnoreCase("chrome")) { WebDriver // *
	 * driver=new ChromeDriver(); driver.get("https://tutorialsninja.com/demo/"); }
	 * // * else if(browserName.equalsIgnoreCase("firefox")) { WebDriver driver=new
	 * // * FirefoxDriver(); driver.get("https://tutorialsninja.com/demo/"); } // *
	 * // * // * } // * // * @DataProvider(name="supply Environments") public
	 * Object[][] getEnvironments() // * // * { Object [][] env=
	 * {{"chrome"},{"firefox"}}; return env; } // * // * // //
	 

	 }
	}
*/
package experiment;

