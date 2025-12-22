package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class AccountSuccessPage extends RootPage {
	WebDriver driver;

	public AccountSuccessPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")
	private WebElement logoutOption;

	public boolean isUserLoggedIn() {
		return elementUtilities.isElementDisplayed(logoutOption);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Success']")
	private WebElement accountSuccessPageBreadcrumb;

	public boolean didWeNavigateToAccountSuccessPage() {
		return elementUtilities.isElementDisplayed(accountSuccessPageBreadcrumb);
	}

	@FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
	private WebElement accountSuccessPageContent;

	public boolean isContentAvailableOnAccountSuccessPage() {
		return elementUtilities.isElementDisplayed(accountSuccessPageContent);
	}

	@FindBy(xpath = "//div[@class='pull-right']//a[text()='Continue']")
	private WebElement continueButton;

	public MyAccountPage clickOnContinueButtonOnAccountSuccesspage() {
		elementUtilities.clickOnElement(continueButton);
		return new MyAccountPage(driver);
	}

	@FindBy(xpath = "//p[text()='Congratulations! Your new account has been successfully created!']")
	private WebElement CongratulationsYournewaccounthasbeensuccessfullycreated;

	public boolean IsCongratulationsYourNewAccountHasBeenSuccessfullyCreatedContentAvailableOnAccountSuccessPage() {
		return elementUtilities.isElementDisplayed(CongratulationsYournewaccounthasbeensuccessfullycreated);
	}

}
