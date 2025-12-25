package experiment;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.AccountSuccessPage;
import pages.HeaderOptions;
import pages.LoginPage;
import pages.MyAccountInformationPage;
import pages.MyAccountPage;
import pages.NewsletterSubscriptionPage;
import pages.RegisterPage;
import pages.RightColumnOptions;
import utils.CommonUtilities;

public class TC_RF_001 {

	WebDriver driver;
	String browserName;
	Properties prop;
	HeaderOptions headerOptions;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	MyAccountPage myAccountPage;
	NewsletterSubscriptionPage newsletterSubscriptionPage;
	LoginPage loginPage;
	RightColumnOptions rightColumnOptions;
	MyAccountInformationPage myAccountInformationPage;

	@BeforeMethod(alwaysRun = true)
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
		} else if (browserName.equals("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("AppUrl"));

		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccountDropmenu();;
		registerPage = headerOptions.clickOnRegisterOption();

	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {
		if (driver != null) { // Prevents NullPointerException
			driver.quit(); // Close browser & end session
			driver = null;

		}
	}

	@Test(priority = 2)
	public void VerifyRegisteringAccountByProvidingMandatoryFields() {
		registerPage.enterFirstName(prop.getProperty("FirstName"));
		registerPage.enterLastName(prop.getProperty("LastName"));
		registerPage.enterEmail(CommonUtilities.getGenerateNewMail());
		registerPage.enterTelephone(prop.getProperty("Telephone"));
		registerPage.enterPassword(prop.getProperty("PWD"));
		registerPage.enterConfirmPassword(prop.getProperty("CONPWD"));
		registerPage.clickOnPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		Assert.assertTrue(accountSuccessPage.isUserLoggedIn());
		Assert.assertTrue(accountSuccessPage.didWeNavigateToAccountSuccessPage());
		Assert.assertTrue(accountSuccessPage.isContentAvailableOnAccountSuccessPage());
		myAccountPage = accountSuccessPage.clickOnContinueButtonOnAccountSuccesspage();
		String expectedTitle = "My Account";
		Assert.assertEquals(expectedTitle, driver.getTitle());
		Assert.assertTrue(myAccountPage.didWeNavigateToMYAccountPage());
		Assert.assertTrue(myAccountPage.isAccountBreadcrumbAvailableOnMyAccountPage());

	}
	@Test
	public void VerifyThePasswordTextEnteredIntoThePasswordAndPasswordConfirmFieldOfRegisterAccountFunctionalityIsToggledToHideItsVisibility() {
		// logic attribute value is present 'password' for toggled or hiding and compare
		// getDomeAttributeValue()
		Assert.assertEquals(registerPage.getPasswordFieldDomAttribute("type"), "password");
		Assert.assertEquals(registerPage.getConfirmPasswordFieldDomAttribute("type"), "password");
	}


	@Test
	public void VerifyRegisteringAccountByAllFields() {
		registerPage.enterFirstName(prop.getProperty("FirstName"));
		registerPage.enterLastName(prop.getProperty("LastName"));
		registerPage.enterEmail(CommonUtilities.getGenerateNewMail());
		registerPage.enterTelephone(prop.getProperty("Telephone"));
		registerPage.enterPassword(prop.getProperty("PWD"));
		registerPage.enterConfirmPassword(prop.getProperty("CONPWD"));
		registerPage.clickOnNewsLetterOption();
		registerPage.clickOnPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		Assert.assertTrue(accountSuccessPage.didWeNavigateToAccountSuccessPage());
		Assert.assertTrue(accountSuccessPage.isContentAvailableOnAccountSuccessPage());
		Assert.assertTrue(accountSuccessPage.IsCongratulationsYourNewAccountHasBeenSuccessfullyCreatedContentAvailableOnAccountSuccessPage());
		myAccountPage = accountSuccessPage.clickOnContinueButtonOnAccountSuccesspage();
		myAccountPage.didWeNavigateToMYAccountPage();
		myAccountPage.isAccountBreadcrumbAvailableOnMyAccountPage();
	}

	@Test
	public void VerifyRegisteringAccountWithoutEnteringAnyDetails() {
		registerPage.clickOnContinueButton();
		Assert.assertTrue(registerPage.isFirstNameWarningMsgAvailableOnRegisterPage());
		Assert.assertTrue(registerPage.IsLastNameWarningMsgAvailableOnRegisterPage());
		Assert.assertTrue(registerPage.IsEmailWarningMsgAvailableOnRegisterPage());
		Assert.assertTrue(registerPage.IsTelephoneWarningMsgAvailableOnRegisterPage());
		Assert.assertTrue(registerPage.IsPasswordWarningMsgAvailableOnRegisterPage());
		Assert.assertTrue(registerPage.IsPrivacyPolicyWarningMsgAvailableOnRegisterPage());

	}

	@Test
	public void VerifyRegisteringAccountBySelectingYesOptionInNewsletterField() {
		registerPage.enterFirstName(prop.getProperty("FirstName"));
		registerPage.enterLastName(prop.getProperty("LastName"));
		registerPage.enterEmail(CommonUtilities.getGenerateNewMail());
		registerPage.enterTelephone(prop.getProperty("Telephone"));
		registerPage.enterPassword(prop.getProperty("PWD"));
		registerPage.enterConfirmPassword(prop.getProperty("CONPWD"));
		registerPage.clickOnNewsLetterOption();
		registerPage.clickOnPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		myAccountPage = accountSuccessPage.clickOnContinueButtonOnAccountSuccesspage();
		Assert.assertTrue(myAccountPage.didWeNavigateToMYAccountPage());
		newsletterSubscriptionPage = myAccountPage.clickOnSubscibeAndUnsubscribeToNewsletterLinkOnMyaccountpage();
		Assert.assertTrue(newsletterSubscriptionPage.IsNewsletterInBreadcrumbAvailableOnNewsletterSubscriptionPage());
		Assert.assertTrue(newsletterSubscriptionPage.IsSubscribeOptionInSelectedMode());

	}

	@Test
	public void VerifyRegisteringAccountBySelectingNoOptioninTheNewsletterField() {
		registerPage.enterFirstName(prop.getProperty("FirstName"));
		registerPage.enterLastName(prop.getProperty("LastName"));
		registerPage.enterEmail(CommonUtilities.getGenerateNewMail());
		registerPage.enterTelephone(prop.getProperty("Telephone"));
		registerPage.enterPassword(prop.getProperty("PWD"));
		registerPage.enterConfirmPassword(prop.getProperty("CONPWD"));
		registerPage.clickOnPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		myAccountPage = accountSuccessPage.clickOnContinueButtonOnAccountSuccesspage();
		newsletterSubscriptionPage = myAccountPage.clickOnSubscibeAndUnsubscribeToNewsletterLinkOnMyaccountpage();
		newsletterSubscriptionPage.IsNewsletterInBreadcrumbAvailableOnNewsletterSubscriptionPage();
		Assert.assertFalse(newsletterSubscriptionPage.IsSubscribeOptionInSelectedMode());

	}

	@Test
	public void VerifyDiffWaysOfNavigatingToRegisterAccountPage() {
		Assert.assertTrue(registerPage.didWeNavigateToRegisterPage());
//	driver=registerPage.getDriver();
//	headerOptions=new HeaderOptions(driver);
		headerOptions = new HeaderOptions(registerPage.getDriver());// one line perform by passing
		headerOptions.clickOnMyAccountDropmenu();
		loginPage = headerOptions.clickOnLoginOption();
		loginPage.clickOnContinueButton();
		Assert.assertTrue(registerPage.didWeNavigateToRegisterPage());
		headerOptions = new HeaderOptions(registerPage.getDriver());//
		headerOptions.clickOnMyAccountDropmenu();
		loginPage = headerOptions.clickOnLoginOption();
		rightColumnOptions = new RightColumnOptions(loginPage.getDriver());
		registerPage = rightColumnOptions.clickOnRegisterOption();

		Assert.assertTrue(registerPage.didWeNavigateToRegisterPage());

	}

	@Test
	public void VerifyRegisteringAccountByEnterringDiffPasswordsIntoPasswordAndConfirmPasswordField() {
		registerPage.enterFirstName(prop.getProperty("FirstName"));
		registerPage.enterLastName(prop.getProperty("LastName"));
		registerPage.enterEmail(CommonUtilities.getGenerateNewMail());
		registerPage.enterTelephone(prop.getProperty("Telephone"));
		registerPage.enterPassword(prop.getProperty("PWD"));
		registerPage.enterConfirmPassword(prop.getProperty("CONDIFFPWD"));
		registerPage.clickOnPrivacyPolicy();
		registerPage.clickOnContinueButton();
		// Method 1
		// Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Password
		// confirmation does not match password!']")).isDisplayed());
		// Method 2
		String expected = "Password confirmation does not match password!";
		Assert.assertEquals(registerPage.getTextOfPasswordConfirmWarningMsgAvailableOnRegisterPage(), expected);

	}

	@Test
	public void VerifyRegisteringAccountByProvidingInvalidTelephoneNumber() {
		registerPage.enterFirstName(prop.getProperty("FirstName"));
		registerPage.enterLastName(prop.getProperty("LastName"));
		registerPage.enterEmail(CommonUtilities.getGenerateNewMail());
		registerPage.enterTelephone(prop.getProperty("InvalidTelephone"));
		registerPage.enterPassword(prop.getProperty("PWD"));
		registerPage.enterConfirmPassword(prop.getProperty("CONPWD"));
		registerPage.clickOnPrivacyPolicy();
		registerPage.clickOnContinueButton();
		Assert.assertTrue(registerPage.IsTelephoneWarningMsgAvailableOnRegisterPage());

	}

	@Test
	public void VerifyRegisteringAccountByFillingPasswordFilledAndNotFiilingConfPasswordFilled() {
		registerPage.enterFirstName(prop.getProperty("FirstName"));
		registerPage.enterLastName(prop.getProperty("LastName"));
		registerPage.enterEmail(CommonUtilities.getGenerateNewMail());
		registerPage.enterTelephone(prop.getProperty("Telephone"));
		registerPage.enterPassword(prop.getProperty("PWD"));
		registerPage.clickOnPrivacyPolicy();
		registerPage.clickOnContinueButton();
		String expectedWarningMsg = "Password confirmation does not match password!";
		Assert.assertEquals(registerPage.getTextOfPasswordConfirmWarningMsgAvailableOnRegisterPage(),
				expectedWarningMsg);

	}

	@Test
	public void VerifyRegisteringAccountWithoutSelectingThePrivacyPolicyCheckboxOption() {
		registerPage.enterFirstName(prop.getProperty("FirstName"));
		registerPage.enterLastName(prop.getProperty("LastName"));
		registerPage.enterEmail(CommonUtilities.getGenerateNewMail());
		registerPage.enterTelephone(prop.getProperty("Telephone"));
		registerPage.enterPassword(prop.getProperty("PWD"));
		registerPage.enterConfirmPassword(prop.getProperty("CONPWD"));
		registerPage.clickOnContinueButton();
		Assert.assertTrue(registerPage.IsPrivacyPolicyWarningMsgAvailableOnRegisterPage());

	}

	@Test
	public void VerifyRegisteringAccountWhetherTheLeadingAndTrailingSpacesEnteredIntoTheRegisterAccountFieldsAreTrimmed() {
		// Firefox not support email field
		SoftAssert softAssert=new SoftAssert();
		registerPage.enterFirstName("   " + prop.getProperty("FirstName") + "  ");
		registerPage.enterLastName("   " + prop.getProperty("LastName") + "   ");
		String timeStamp = CommonUtilities.getGenerateNewMail();
		registerPage.enterEmail(timeStamp);
		registerPage.enterTelephone("  " + prop.getProperty("Telephone") + "  ");
		registerPage.enterPassword("  " + prop.getProperty("PWD") + "  ");
		registerPage.enterConfirmPassword("  " + prop.getProperty("CONPWD") + "  ");
		registerPage.clickOnPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		Assert.assertTrue(accountSuccessPage.didWeNavigateToAccountSuccessPage());
		Assert.assertTrue(accountSuccessPage.IsCongratulationsYourNewAccountHasBeenSuccessfullyCreatedContentAvailableOnAccountSuccessPage());
		myAccountPage=accountSuccessPage.clickOnContinueButtonOnAccountSuccesspage();
		myAccountPage.didWeNavigateToMYAccountPage();
	    myAccountInformationPage = myAccountPage.clickOnEditYourAccountInformation();
	    ;
	    if(browserName.equals("chrome"))
	    {
	    softAssert.assertEquals(myAccountInformationPage.getFirstNameFieldAttribute("value"),prop.getProperty("FirstName") );
	    softAssert.assertEquals(myAccountInformationPage.getLastNameFieldAttribute("value"), prop.getProperty("LastName"));
	    softAssert.assertEquals(myAccountInformationPage.getEmailFieldAttribute("value"), timeStamp);
	    softAssert.assertEquals(myAccountInformationPage.getTelephoneFieldAttribute("value"),prop.getProperty("Telephone") );
	    softAssert.assertAll();
	    }
	    else if(browserName.equals("firefox"))
	    {
	    	Assert.assertEquals("Please enter an email address.", myAccountInformationPage.getEmailFieldDomPoperty("validationMessage"));
	    }
	}

	@Test
	public void VerifyWhetherTheMandatoryFieldsInTheRegisterAccountPageAreNotAcceptingOnlySpaces() {
		registerPage.enterFirstName(" ");
		registerPage.enterLastName(" ");
		registerPage.enterEmail("  ");
		registerPage.enterTelephone("    ");
		registerPage.enterPassword("     ");
		registerPage.enterConfirmPassword("     ");
		registerPage.clickOnPrivacyPolicy();
		registerPage.clickOnContinueButton();
		if (browserName.equalsIgnoreCase("chrome")) {
			Assert.assertTrue(registerPage.isFirstNameWarningMsgAvailableOnRegisterPage());
			Assert.assertTrue(registerPage.IsLastNameWarningMsgAvailableOnRegisterPage());
			Assert.assertTrue(registerPage.IsEmailWarningMsgAvailableOnRegisterPage());
			Assert.assertTrue(registerPage.IsTelephoneWarningMsgAvailableOnRegisterPage());
		} else if (browserName.equalsIgnoreCase("firefox")) {
			Assert.assertEquals(registerPage.getEmailWarningMsg(), "Please enter an email address.");

		}

	}

	@Test
	public void VerifyWhetherThePrivacyPolicyCheckboxOptionIsNotSelectedByDefault() {
		Assert.assertFalse(registerPage.statusOfPrivacyPolicyField());

	}

	@Test
	public void VerifyAllTheFieldsInTheRegisterAccountPagehavetheProperPlaceholders() {

		Assert.assertEquals(registerPage.getFirstNamePlaceholderText(), "First Name");
		Assert.assertEquals(registerPage.getLastNamePlaceholderText(), "Last Name");
		Assert.assertEquals(registerPage.getEmailPlaceholderText(), "E-Mail");
		Assert.assertEquals(registerPage.getTelephonePlaceholderText(), "Telephone");
		Assert.assertEquals(registerPage.getPasswordPlaceholderText(), "Password");
		Assert.assertEquals(registerPage.getPasswordConfirmPlaceholderText(), "Password Confirm");

	}

	@Test
	public void VerifyRegisteringAnAccountByProvidingTheExistingEmailAddress() {
		registerPage.enterFirstName(prop.getProperty("FirstName"));
		registerPage.enterLastName(prop.getProperty("LastName"));
		registerPage.enterEmail(prop.getProperty("ExistingEmail"));
		registerPage.enterTelephone(prop.getProperty("Telephone"));
		registerPage.enterPassword(prop.getProperty("PWD"));
		registerPage.enterConfirmPassword(prop.getProperty("CONPWD"));
		registerPage.clickOnPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		Assert.assertTrue(registerPage.didWeGetDuplicateEmailWarningMsg());
//	String expect="Warning: E-Mail Address is already registered!";
//	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText(), expect);

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
	public void VerifyRegisteringAnAccountByProvidingAnInvalidEmailAddressIntoTheE_MailField()
			throws InterruptedException {
		registerPage.enterFirstName(prop.getProperty("FirstName"));
		registerPage.enterLastName(prop.getProperty("LastName"));
		registerPage.enterEmail(prop.getProperty("InvalidEmailOne"));
		registerPage.enterTelephone(prop.getProperty("Telephone"));
		registerPage.enterPassword(prop.getProperty("PWD"));
		registerPage.enterConfirmPassword(prop.getProperty("CONPWD"));
		registerPage.clickOnPrivacyPolicy();
		registerPage.clickOnContinueButton();
		Thread.sleep(2000);
		if (browserName.equalsIgnoreCase("chrome")) {
			String expectedWarningMsgOne = "Please include an '@' in the email address. 'amotoori' is missing an '@'.";
			String actualWarningMsgOne = registerPage.getEmailValidationMessage();
			System.out.println(actualWarningMsgOne);
			Assert.assertEquals(expectedWarningMsgOne, actualWarningMsgOne);
			registerPage.getEmailFieldReset();
			registerPage.getEmailWarningMsg();
			registerPage.enterEmail(prop.getProperty("InvalidEmailTwo"));
			registerPage.clickOnContinueButton();
			String expectedWarningMsgTwo = "Please enter a part following '@'. 'amotoori2225@' is incomplete.";
			String actualWarningMsgTwo = registerPage.getEmailValidationMessage();
			// System.out.println(actualWarningMsgTwo);
			Assert.assertEquals(expectedWarningMsgTwo, actualWarningMsgTwo);
			registerPage.getEmailFieldReset();
			registerPage.enterEmail(prop.getProperty("InvalidEmailThree"));
			registerPage.clickOnContinueButton();
			String expectedWarningMsgThree = "'.' is used at a wrong position in 'gmail.'.";
			String actualWarningMsgThree = registerPage.getEmailValidationMessage();
			// System.out.println(actualWarningMsgThree);
			Assert.assertEquals(expectedWarningMsgThree, actualWarningMsgThree);
			registerPage.getEmailFieldReset();
			registerPage.enterEmail(prop.getProperty("InvalidEmailFour"));
			registerPage.clickOnContinueButton();
			String expectedWarningMsgFour = "Please enter a part followed by '@'. '@gmail.com' is incomplete.";
			String actualWarningMsgFour = registerPage.getEmailValidationMessage();
			// System.out.println(actualWarningMsgFour);
			Assert.assertEquals(expectedWarningMsgFour, actualWarningMsgFour);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			String expectedWarningMsgOne = "Please enter an email address.";
			String actualWarningMsgOne = registerPage.getEmailValidationMessage();
			// System.out.println(actualWarningMsgOne);
			Assert.assertEquals(expectedWarningMsgOne, actualWarningMsgOne);
			registerPage.getEmailFieldReset();
			registerPage.enterEmail(prop.getProperty("InvalidEmailTwo"));
			registerPage.clickOnContinueButton();
			String expectedWarningMsgTwo = "Please enter an email address.";
			String actualWarningMsgTwo = registerPage.getEmailValidationMessage();
			// System.out.println(actualWarningMsgTwo);
			Assert.assertEquals(expectedWarningMsgTwo, actualWarningMsgTwo);
			registerPage.getEmailFieldReset();
			registerPage.enterEmail(prop.getProperty("InvalidEmailThree"));
			registerPage.clickOnContinueButton();
			String expectedWarningMsgThree = "Please enter an email address.";
			String actualWarningMsgThree = registerPage.getEmailValidationMessage();
			// System.out.println(actualWarningMsgThree);
			Assert.assertEquals(expectedWarningMsgThree, actualWarningMsgThree);
			registerPage.getEmailFieldReset();
			registerPage.enterEmail(prop.getProperty("InvalidEmailFour"));
			registerPage.clickOnContinueButton();
			String expectedWarningMsgFour = "Please enter an email address.";
			String actualWarningMsgFour = registerPage.getEmailValidationMessage();
			// System.out.println(actualWarningMsgFour);
			Assert.assertEquals(expectedWarningMsgFour, actualWarningMsgFour);
		}
	}

	@Test
	public void VerifyRegisteringAnAccountByUsingTheKeyboardKeys() {
		// Pressing tab key some element highlight and some are not
		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
		action.sendKeys(Keys.ARROW_DOWN).pause(2000).sendKeys(Keys.ENTER).build().perform();

		for (int i = 1; i <= 23; i++) {
			action.sendKeys(Keys.TAB).build().perform();
		}
		action.sendKeys(prop.getProperty("FirstName")).sendKeys(Keys.TAB).sendKeys(prop.getProperty("LastName"))
				.sendKeys(Keys.TAB).sendKeys(CommonUtilities.getGenerateNewMail()).sendKeys(Keys.TAB)
				.sendKeys(prop.getProperty("Telephone")).sendKeys(Keys.TAB).sendKeys(prop.getProperty("PWD"))
				.sendKeys(Keys.TAB).sendKeys(prop.getProperty("CONPWD")).sendKeys(Keys.TAB).sendKeys(Keys.ARROW_LEFT)
				.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.SPACE).sendKeys(Keys.TAB).sendKeys(Keys.ENTER)
				.build().perform();
		RightColumnOptions rightColumnbOptions = new RightColumnOptions(registerPage.getDriver());
		Assert.assertTrue(rightColumnbOptions.isLogoutOptionAvailable());
		AccountSuccessPage accountSuccessPage = new AccountSuccessPage(rightColumnbOptions.getDriver());
		Assert.assertTrue(accountSuccessPage.didWeNavigateToAccountSuccessPage());

	}

	@Test
	public void VerifyAllTheMandatoryFieldsInTheRegisterAccountPageAreMarkedWithRedColorAsteriskSymbol() {
		String expectedLabelContent = "\"* \"";
		String expectedColor = "rgb(255, 0, 0)";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String firstNameLabelContent = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getFirstNameFieldLabel());
		Assert.assertEquals(expectedLabelContent, firstNameLabelContent);
		String lastNameLabelcontent = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getLastNameFieldLabel());
		Assert.assertEquals(expectedLabelContent, lastNameLabelcontent);
		String emailLabelContent = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getEmailFieldLabel());
		Assert.assertEquals(expectedLabelContent, emailLabelContent);
		String telephoneLabelContent = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getTelephoneFieldLabel());
		Assert.assertEquals(expectedLabelContent, telephoneLabelContent);
		String passwordLabelContent = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getPasswordFieldLabel());
		Assert.assertEquals(expectedLabelContent, passwordLabelContent);
		String passwordConfirmlabelcontent = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getConfirmPasswordFieldLabel());
		Assert.assertEquals(expectedLabelContent, passwordConfirmlabelcontent);
		String firstNameLabelContentColor = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",
				registerPage.getFirstNameFieldLabel());
		Assert.assertEquals(expectedColor, firstNameLabelContentColor);
		String lastNameLabelContentColor = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",
				registerPage.getLastNameFieldLabel());
		Assert.assertEquals(expectedColor, lastNameLabelContentColor);
		String emailLabelContentColor = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",
				registerPage.getEmailFieldLabel());
		Assert.assertEquals(expectedColor, emailLabelContentColor);
		String telephoneLabelContentColor = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",
				registerPage.getTelephoneFieldLabel());
		Assert.assertEquals(expectedColor, telephoneLabelContentColor);
		String passwordLabelContentColor = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",
				registerPage.getPasswordFieldLabel());
		Assert.assertEquals(expectedColor, passwordLabelContentColor);
		String passwordConfirmLabelContentColor = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",
				registerPage.getConfirmPasswordFieldLabel());
		Assert.assertEquals(expectedColor, passwordConfirmLabelContentColor);
	}

	@Test(dataProvider = "password supplier")
	public void VerifyWhetherThePasswordFieldsInTheRegisterAccountPageAreFollowingPasswordComplexityStandards(
			String pwd) {
		registerPage.enterFirstName(prop.getProperty("FirstName"));
		registerPage.enterLastName(prop.getProperty("LastName"));
		registerPage.enterEmail(CommonUtilities.getGenerateNewMail());
		registerPage.enterTelephone(prop.getProperty("Telephone"));
		registerPage.enterPassword(pwd);
		registerPage.enterConfirmPassword(pwd);
		registerPage.clickOnPrivacyPolicy();
		registerPage.clickOnContinueButton();
		String expectedWarning = "Enter Password Which Follow Password Complexity Standard!";
		boolean b = false;
		try {
			String actualWarning = registerPage.getPasswordWarningMsg();
			if (actualWarning.equals(expectedWarning))
				b = true;
		} catch (Exception e) {
			b = false;
		}
		Assert.assertTrue(b);
	}

	@DataProvider(name = "password supplier")
	public Object[][] supplyPasswords() {
		Object[][] data = { { "12345" }, { "abcdefghi" }, { "abcd1234" }, { "abcd123$" }, { "ABCD456#" } };
		return data;
	}

	@Test
	public void VerifyButtonTextColorBackgroundColorFontSize() {
		WebElement continueButton = registerPage.getContinueButtonFieldElement();

		if (browserName.equalsIgnoreCase("chrome")) {
			String expectedTextColorChrome = "rgba(255, 255, 255, 1)";
			String actualButtonTextColor = continueButton.getCssValue("color");
			Assert.assertEquals(actualButtonTextColor, expectedTextColorChrome);
			String actualButtonBackgroundColor = continueButton.getCssValue("background-color");
			String expectedButtonBackgroundColor = "rgba(34, 154, 200, 1)";
			Assert.assertEquals(actualButtonBackgroundColor, expectedButtonBackgroundColor);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			String expectedTextColor = "rgb(255, 255, 255)";
			String actualButtonTextColor = continueButton.getCssValue("color");
			Assert.assertEquals(actualButtonTextColor, expectedTextColor);
			String actualButtonBackgroundColor = continueButton.getCssValue("background-color");
			String expectedButtonBackgroundColor = "rgb(34, 154, 200)";
			Assert.assertEquals(actualButtonBackgroundColor, expectedButtonBackgroundColor);
		}
		String actualButtonFontSize = continueButton.getCssValue("font-size");
		String expectedFontSize = "12px";
		Assert.assertEquals(actualButtonFontSize, expectedFontSize);
	}

	@Test(priority = -2)
	public void VerifyWarningMsgOfTextField() {
		// check Zero character Warning Msg

		String expectedFirstNameWarningMsg = "First Name must be between 1 and 32 characters!";
		registerPage.enterFirstName("");
		registerPage.clickOnContinueButton();
		try {
			Thread.sleep(500);

		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}
		// small pause to allow reload
		String actualFirstNameWarningMsg = registerPage.getFirstNameWarningMsg();

		Assert.assertEquals(expectedFirstNameWarningMsg, actualFirstNameWarningMsg);
		// check for 33 Character
		registerPage.getFirstNameFieldReset();
		registerPage.enterFirstName(prop.getProperty("FirstNameWith33Character"));
		registerPage.clickOnContinueButton();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e11) {
			e11.printStackTrace();
		}
		// small pause to allow reload
		actualFirstNameWarningMsg = registerPage.getFirstNameWarningMsg();
		Assert.assertEquals(expectedFirstNameWarningMsg, actualFirstNameWarningMsg);
		// check for 1 Character
		registerPage.getFirstNameFieldReset();
		registerPage.enterFirstName((prop.getProperty("FirstNameWithOneCharacter")));
		registerPage.clickOnContinueButton();
		boolean firstNameWarningStatus = false;
		try {
			firstNameWarningStatus = registerPage.isFirstNameWarningMsgAvailableOnRegisterPage();
		} catch (Exception e3) {
			firstNameWarningStatus = false;
		}
		Assert.assertFalse(firstNameWarningStatus);
		// check for 32 Characters
		registerPage.getFirstNameFieldReset();
		registerPage.enterFirstName(prop.getProperty("FirstNameWith32Character"));
		registerPage.clickOnContinueButton();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		// small pause to allow reload
		try {
			firstNameWarningStatus = registerPage.isFirstNameWarningMsgAvailableOnRegisterPage();
		} catch (Exception e) {
			firstNameWarningStatus = false;
		}
		Assert.assertFalse(firstNameWarningStatus);
		// Verification For Last Name Field
		// zero Character check
		String expectedLastNameWarningMsg = "Last Name must be between 1 and 32 characters!";
		registerPage.getLastNameFieldReset();
		registerPage.enterLastName("");
		registerPage.clickOnContinueButton();
		try {
			Thread.sleep(500); // small pause to allow reload
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		String actualWarningLastName = registerPage.getLastNameWarningMsg();
		Assert.assertEquals(expectedLastNameWarningMsg, actualWarningLastName);
		// check for 33 Character
		registerPage.getLastNameFieldReset();
		registerPage.enterLastName(prop.getProperty("LastNameWith33Character"));
		registerPage.clickOnContinueButton();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} // small pause to allow reload
		actualWarningLastName = registerPage.getLastNameWarningMsg();
		Assert.assertEquals(expectedLastNameWarningMsg, actualWarningLastName);
		// 1 Character check
		registerPage.getLastNameFieldReset();
		registerPage.enterLastName(prop.getProperty("LastNameWithOneCharacter"));
		registerPage.clickOnContinueButton();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} // small pause to allow reload
		boolean lastNamewarningMsg = false;
		try {
			lastNamewarningMsg = registerPage.IsLastNameWarningMsgAvailableOnRegisterPage();
		} catch (Exception e) {
			lastNamewarningMsg = false;
		}
		Assert.assertFalse(lastNamewarningMsg);
		// 32 Character check
		registerPage.getLastNameFieldReset();
		registerPage.enterLastName(prop.getProperty("LastNameWith32Character"));
		registerPage.clickOnContinueButton();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} // small pause to allow reload
		try {
			lastNamewarningMsg = registerPage.IsLastNameWarningMsgAvailableOnRegisterPage();
		} catch (Exception e) {
			lastNamewarningMsg = false;

		}
		Assert.assertFalse(lastNamewarningMsg);
		// Verification of Email field
		registerPage.enterEmail(prop.getProperty("EmailWithMorethan34Character"));
		registerPage.clickOnContinueButton();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		// small pause to allow reload
		boolean expectedEmailWarningMsg = false;
		try {
			expectedEmailWarningMsg = registerPage.IsEmailWarningMsgAvailableOnRegisterPage();
		} catch (Exception e) {
			expectedEmailWarningMsg = false;
		}
		Assert.assertFalse(expectedEmailWarningMsg);
		// Verification for Telephone Field
		String expectedTelephoneWarningMsg = "Telephone must be between 3 and 32 characters!";
		// verify for 1 character
		registerPage.enterTelephone(prop.getProperty("TelephoneWithOneCharacter"));
		registerPage.clickOnContinueButton();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} // small pause to allow reload
		String actualTelephoneWarningMsg = registerPage.getTelephoneWarningMsg();
		Assert.assertEquals(expectedTelephoneWarningMsg, actualTelephoneWarningMsg);
		// verify more than 32 Character
		registerPage.getTelephoneFieldReset();
		registerPage.enterTelephone(prop.getProperty("TelephoneWithMoreThan32Character"));
		registerPage.clickOnContinueButton();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} // small pause to allow reload
		actualTelephoneWarningMsg = registerPage.getTelephoneWarningMsg();
		Assert.assertEquals(expectedTelephoneWarningMsg, actualTelephoneWarningMsg);
		// verify 3 Character
		registerPage.getTelephoneFieldReset();
		registerPage.enterTelephone(prop.getProperty("TelephoneWithThreeCharacter"));
		registerPage.clickOnContinueButton();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} // small pause to allow reload
		boolean expectedTelephoneWarningMsgStatus = false;
		try {
			expectedTelephoneWarningMsgStatus = registerPage.IsTelephoneWarningMsgAvailableOnRegisterPage();
		} catch (Exception e) {
			expectedTelephoneWarningMsgStatus = false;
		}
		Assert.assertFalse(expectedTelephoneWarningMsgStatus);
		// verify 32 Character
		registerPage.getTelephoneFieldReset();
		registerPage.enterTelephone(prop.getProperty("TelephoneWith32Character"));
		registerPage.clickOnContinueButton();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} // small pause to allow reload
		try {
			expectedTelephoneWarningMsgStatus = registerPage.IsTelephoneWarningMsgAvailableOnRegisterPage();
		} catch (Exception e) {
			expectedTelephoneWarningMsgStatus = false;
		}
		Assert.assertFalse(expectedTelephoneWarningMsgStatus);
		// verify Password Field for 1 character
		String expectedPasswordWarningMsg = "Password must be between 4 and 20 characters!";
		registerPage.enterPassword(prop.getProperty("PasswordWithOneCharacter"));
		registerPage.clickOnContinueButton();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} // small pause to allow reload
		String actualPasswordWarningMsg = registerPage.getPasswordWarningMsg();
		Assert.assertEquals(expectedPasswordWarningMsg, actualPasswordWarningMsg);
		// above line give StaleElementReferenceException:
		// stale element reference: stale element not found so solve this issue we store
		// variable and hen use 🔁 Re-find element after page update
		// verify 4 character
		registerPage.getPasswordFieldReset();
		registerPage.enterPassword(prop.getProperty("PasswordWithFourCharacter"));
		registerPage.clickOnContinueButton();
		try {
			Thread.sleep(500);

		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} // small pause to allow reload
		boolean expectedPasswordWarninhMsg = false;
		try {
			expectedPasswordWarninhMsg = registerPage.IsPasswordWarningMsgAvailableOnRegisterPage();
		} catch (Exception e) {
			expectedPasswordWarninhMsg = false;
		}
		Assert.assertFalse(expectedPasswordWarninhMsg);
		// verify 20 character
		registerPage.getPasswordFieldReset();
		registerPage.enterPassword(prop.getProperty("PasswordWith20Character"));
		registerPage.clickOnContinueButton();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} // small pause to allow reload
		try {
			expectedPasswordWarninhMsg = registerPage.IsPasswordWarningMsgAvailableOnRegisterPage();
		} catch (Exception e) {
			expectedPasswordWarninhMsg = false;
		}
		Assert.assertFalse(expectedPasswordWarninhMsg);
	}

	@Test
	public void VerifyTheBreadcrumbPageHeadingPageURLPageTitleOfRegisterAccountPage() {
		// Title of Page
		System.out.println(driver.getTitle());
		// URL Of Page
		System.out.println(driver.getCurrentUrl());
		// verify Breadcrumb
		boolean status = driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Register']")).isDisplayed();
		System.out.println("Breadcrumb  availability " + status);
	}

	@Test(dataProvider = "supply Environments")
	public void VerifyRegisterAccountFunctionalityInAllTheSupportedEnvironments(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriver driver = new ChromeDriver();
			driver.get("https://tutorialsninja.com/demo/");
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriver driver = new FirefoxDriver();
			driver.get("https://tutorialsninja.com/demo/");
		}
	}

	@DataProvider(name = "supply Environments")
	public Object[][] getEnvironments() {
		Object[][] env = { { "chrome" }, { "firefox" } };
		return env;

	}

}
