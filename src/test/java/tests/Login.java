package tests;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.AccountLogoutPage;
import pages.ChangePasswordPage;
import pages.HeaderOptions;
import pages.LoginPage;
import pages.MyAccountPage;
import utils.CommonUtilities;

public class Login extends Base {
	WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		driver = openBrowserAndApplicationPageUrl();
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccountDropmenu();
		loginPage = headerOptions.clickOnLoginOption();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		closeBrowser(driver);
	}

	@Test(priority = 1)
	public void VerifyLoggingIntoTheApplicationUsingValidCredentials() {
		loginPage.didWeNavigateToLoginPage();
		loginPage.enterEmailIntoEmailAddressField(prop.getProperty("ExistingEmail"));
		loginPage.enterPasswordIntoPasswordField(prop.getProperty("PWD"));
		myAccountPage = loginPage.clickOnLoginButton();
		myAccountPage.didWeNavigateToMYAccountPage();

	}

	@Test(priority = 2)
	public void VerifyLoggingIntoTheApplicationUsingInvalidEmailAddressAndInvalidPassword() {
		loginPage.didWeNavigateToLoginPage();
		loginPage.enterEmailIntoEmailAddressField(prop.getProperty("InvalidEmailOne"));
		loginPage.enterPasswordIntoPasswordField(prop.getProperty("PasswordWithOneCharacter"));// invalid password
		myAccountPage = loginPage.clickOnLoginButton();
		Assert.assertTrue(loginPage.pageLevelWarningMsg());
	}

	@Test(priority = 3)
	public void VerifyLoggingIntoTheApplicationUsingInvalidEmailAddressAndValidPassword() {
		loginPage.didWeNavigateToLoginPage();
		loginPage.enterEmailIntoEmailAddressField(prop.getProperty("InvalidEmailOne"));
		loginPage.enterPasswordIntoPasswordField(prop.getProperty("PWD"));
		loginPage.clickOnLoginButton();
		Assert.assertTrue(loginPage.pageLevelWarningMsg());

	}

	@Test(priority = 4)
	public void VerifyLoggingIntoTheApplicationWithoutProvidingAnyCredentials() {
		loginPage.didWeNavigateToLoginPage();
		loginPage.enterEmailIntoEmailAddressField("");
		loginPage.enterPasswordIntoPasswordField("");
		loginPage.clickOnLoginButton();
		Assert.assertTrue(loginPage.pageLevelWarningMsg());

	}

	@Test(priority = 5)
	public void VerifyLoggingIntoTheApplicationUsingValidEmailAddressAndInvalidPassword() {
		loginPage.enterEmailIntoEmailAddressField(prop.getProperty("ExistingEmail"));
		loginPage.enterPasswordIntoPasswordField(prop.getProperty("PasswordWithOneCharacter"));
		loginPage.clickOnLoginButton();
		Assert.assertTrue(loginPage.pageLevelWarningMsg());

	}

	@Test(priority = 6)
	public void VerifyForgottenPasswordLinkIsAvailableInTheLoginPageAndIsWorking() {
		forgottenPasswordPage = loginPage.clickOnForgottenPasswordLink();
		Assert.assertTrue(forgottenPasswordPage.didWeNavigateToForgottenPasswordPage());
	}

	@Test(priority = 7)
	public void VerifyLoggingIntoTheApplicationUsingKeyboardKeys() {
		loginPage.didWeNavigateToLoginPage();
		actions = getActions(driver);
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.TAB, 23);
		actions = typeTextUsingActions(actions, prop.getProperty("ExistingEmail"));
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.TAB, 1);
		actions = typeTextUsingActions(actions, prop.getProperty("PWD"));
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.TAB, 2);
		clickKeyboardkeyMultipleTimes(actions, driver, Keys.ENTER, 1);
		myAccountPage = new MyAccountPage(driver);
		Assert.assertTrue(myAccountPage.didWeNavigateToMYAccountPage());

	}

	@Test(priority = 8)

	public void VerifyEMailAddressAndPasswordTextFieldsInTheLoginPageHaveThePlaceholderText() {
		Assert.assertEquals(loginPage.getEmailFieldPlaceholderText(), "E-Mail Address");
		Assert.assertEquals(loginPage.getPasswordFieldPlaceholderText(), "Password");

	}

	@Test(priority = 9)
	public void verifyBrowsingBackAfterLogin() {
		loginPage.loginIntoApplication(prop.getProperty("ExistingEmail"), prop.getProperty("PWD"));
		navigateBackInBrowser(driver);
		refreshPage(driver);
		myAccountPage = new MyAccountPage(driver);
		Assert.assertTrue(myAccountPage.didWeNavigateToMYAccountPage());
	}

	@Test(priority = 10)
	public void VerifyBrowsingBackAfterLogout() {
		myAccountPage = loginPage.loginIntoApplication(prop.getProperty("ExistingEmail"), prop.getProperty("PWD"));
		headerOptions = myAccountPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropmenu();
		accountLogoutPage = headerOptions.clickOnLogoutOption();
		navigateBackInBrowser(driver);
		refreshPage(driver);
		loginPage = accountLogoutPage.getLoginPage();
		Assert.assertTrue(loginPage.didWeNavigateToLoginPage());

	}

	@Test(priority = 11)
	public void VerifyLoggingIntoTheApplicationUsingInactiveCredentials() {
		loginPage.loginIntoApplication(prop.getProperty("InactiveCredentials"), prop.getProperty("PWD"));
		Assert.assertTrue(loginPage.pageLevelWarningMsg());
	}

	@Test(priority = 12)
	public void VerifyTheNumberOfUnsucessfulLoginAttemps() {
		loginPage.enterEmailIntoEmailAddressField(prop.getProperty("InvalidEmail"));
		loginPage.enterPasswordIntoPasswordField(prop.getProperty("PasswordWithOneCharacter"));
		loginPage.clickOnLoginButton();

		loginPage.loginIntoApplication(prop.getProperty("ExistingEmail"), prop.getProperty("CONDIFFPWD"));
		loginPage.loginIntoApplication(prop.getProperty("ExistingEmail"), prop.getProperty("CONDIFFPWD"));
		loginPage.loginIntoApplication(prop.getProperty("ExistingEmail"), prop.getProperty("CONDIFFPWD"));
		loginPage.loginIntoApplication(prop.getProperty("ExistingEmail"), prop.getProperty("CONDIFFPWD"));
		loginPage.loginIntoApplication(prop.getProperty("ExistingEmail"), prop.getProperty("CONDIFFPWD"));
		loginPage.loginIntoApplication(prop.getProperty("ExistingEmail"), prop.getProperty("CONDIFFPWD"));

//		String expectedWarningMsg = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
//		Assert.assertEquals(loginPage.getTextOfUnsucessfulAttemptsWarningMsg(), expectedWarningMsg);
		Assert.assertTrue(loginPage.unsuccessfulWarningMsgAvailableOnPage());
	}

	@Test(priority = 13)
	public void VerifyTheTextEnterIntoThePasswordFieldIsToggledToHideItsVisibility() {
		Assert.assertEquals(loginPage.getPasswordFieldDomAttribute("type"), "password");
	}

	@Test(priority = 14)
	public void VerifyTheCopyingOfTheTextEnteredIntoThePasswordField() {
		loginPage.enterPasswordIntoPasswordField(prop.getProperty("PWD"));
		loginPage.copyPasswordFromPasswordField();
		loginPage.pasteCopiedTextIntoEmailAddressField();
		loginPage.clickOnLoginButton();
		Assert.assertNotEquals(loginPage.getPastedTextFromEmailField(), prop.getProperty("PWD"));
	}

	@Test(priority = 15)
	public void VerifyThePasswordIsNotVisibleInThePageSource() {
		loginPage.enterPasswordIntoPasswordField(prop.getProperty("RPWD"));
		Assert.assertTrue(getPageSourceCode().contains(prop.getProperty("RPWD")));
		loginPage.clickOnLoginButton();
		Assert.assertTrue(getPageSourceCode().contains(prop.getProperty("RPWD")));

	}

	@Test(priority = 16)
	public void VerifyLoggingIntoTheApplicationAfterChangingThePassword() {
		myAccountPage = loginPage.loginIntoApplication(prop.getProperty("ExistingEmail"), prop.getProperty("OldPWD"));
		changePasswordPage = myAccountPage.clickOnChangeYourPasswordOption();
		changePasswordPage.enterNewPasswordIntoPasswordField(prop.getProperty("NewPWD"));
		changePasswordPage.enterNewPasswordIntoConPasswordField(prop.getProperty("NewPWD"));
		myAccountPage = changePasswordPage.clickOnContinueButton();
		myAccountPage.didWeNavigateToMYAccountPage();
		myAccountPage.isSuccessMsgAvailableOnMyAccountPage();
		rightColumnOptions = myAccountPage.getRightColumnOptions();
		accountLogoutPage = rightColumnOptions.clickOnLogout();
		accountLogoutPage.didWeNavigateToAccountLogutPage();
		homePage = accountLogoutPage.clickOnContinueButton();
		headerOptions = homePage.getHeaderOptions();
		loginPage = headerOptions.navigateToLoginPage();
		loginPage.didWeNavigateToLoginPage();
		loginPage.loginIntoApplication(prop.getProperty("ExistingEmail"), prop.getProperty("OldPWD"));
		Assert.assertTrue(loginPage.pageLevelWarningMsg());
		myAccountPage = loginPage.loginIntoApplication(prop.getProperty("ExistingEmail"), prop.getProperty("NewPWD"));
		Assert.assertTrue(myAccountPage.didWeNavigateToMYAccountPage());
		swapPasswords(prop);
	}
	@Test(priority=16)
	public void VerifyUserIsAbleToNavigateToDifferentPagesFromLoginPage()
	{
		
	}
}
