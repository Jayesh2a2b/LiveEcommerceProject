package tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.HeaderOptions;

public class Logout extends Base {
	WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		driver = openBrowserAndApplicationPageUrl();
		headerOptions = new HeaderOptions(driver);

	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {
		closeBrowser(driver);
	}

	@Test(priority = 1)
	public void VerifyLoggingoutBySelectingLogoutOptionFromMyAccountDropmenu() {
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.loginIntoApplication(prop.getProperty("ValidEmail"), prop.getProperty("PWD"));
		Assert.assertTrue(myAccountPage.didWeNavigateToMYAccountPage());
		headerOptions = myAccountPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropmenu();
		Assert.assertTrue(headerOptions.isLogoutOptionAvailableInDropMenu());
		accountLogoutPage = headerOptions.clickOnLogoutOptionInDropMenu();
		Assert.assertTrue(accountLogoutPage.didWeNavigateToAccountLogutPage());
		headerOptions = myAccountPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropmenu();
		Assert.assertTrue(headerOptions.isLoginOptionAvailableInDropMenu());
		accountLogoutPage = headerOptions.getAccountLogoutPage();
		homePage = accountLogoutPage.clickOnContinueButton();
		Assert.assertEquals(driver.getTitle(), "Your Store");
	}

	@Test(priority = 2)
	public void VerifyLoggingOutBySelectingLogoutOptionFromRightColumnOptions() {
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.loginIntoApplication(prop.getProperty("ValidEmail"), prop.getProperty("PWD"));
		Assert.assertTrue(myAccountPage.didWeNavigateToMYAccountPage());
		rightColumnOptions = myAccountPage.getRightColumnOptions();
		accountLogoutPage = rightColumnOptions.clickOnLogout();
		Assert.assertTrue(accountLogoutPage.didWeNavigateToAccountLogutPage());
		headerOptions = myAccountPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropmenu();
		Assert.assertTrue(headerOptions.isLoginOptionAvailableInDropMenu());
		accountLogoutPage = headerOptions.getAccountLogoutPage();
		homePage = accountLogoutPage.clickOnContinueButton();
		Assert.assertEquals(driver.getTitle(), "Your Store");

	}

	@Test(priority = 3)
	public void VerifyLogoutOptionIsNotDisplayedUnderMyAccountMenuBeforeLoggingIn() {
		headerOptions.clickOnMyAccountDropmenu();
		Assert.assertTrue(headerOptions.isLogoutOptionAvailableInDropMenu());

	}

	@Test(priority = 4)
	public void VerifyLogoutOptionIsNotDisplayedUnderRightColumnOptionsBeforeLoggingIn() {
		loginPage = headerOptions.navigateToLoginPage();
		rightColumnOptions = loginPage.getRightColumnOptions();
		Assert.assertFalse(rightColumnOptions.isLogoutOptionAvailableInRightColumbOptions());

	}

	@Test
	public void VerifyLoggingOutAndBrowsingBack() {
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.loginIntoApplication(prop.getProperty("ValidEmail"), prop.getProperty("PWD"));
		Assert.assertTrue(myAccountPage.didWeNavigateToMYAccountPage());
		headerOptions = myAccountPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropmenu();
		Assert.assertTrue(headerOptions.isLogoutOptionAvailableInDropMenu());
		accountLogoutPage = headerOptions.clickOnLogoutOptionInDropMenu();
		Assert.assertTrue(accountLogoutPage.didWeNavigateToAccountLogutPage());
		navigateBackInBrowser(driver);
		refreshPage(driver);
		headerOptions = myAccountPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropmenu();
		Assert.assertTrue(headerOptions.isLoginOptionAvailableInDropMenu());



	}

}
