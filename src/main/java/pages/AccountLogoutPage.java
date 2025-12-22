package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class AccountLogoutPage extends RootPage {
	WebDriver driver;

	public AccountLogoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public LoginPage getLoginPage() {
		return new LoginPage(driver);

	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Logout']")
	private WebElement logoutBreadcrumb;

	public boolean didWeNavigateToAccountLogutPage() {
		return elementUtilities.isElementDisplayed(logoutBreadcrumb);
	}

	@FindBy(xpath = "//a[text()='Continue']")
	private WebElement continueButton;
	public HomePage clickOnContinueButton()
	{
		elementUtilities.clickOnElement(continueButton);
		return new  HomePage(driver);
	}
}
