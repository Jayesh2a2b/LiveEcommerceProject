package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class MyAccountPage extends RootPage {
	WebDriver driver;

	public MyAccountPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Edit your account information")
	private WebElement EditYourAccountInformation;

	public boolean didWeNavigateToMYAccountPage() {
		return elementUtilities.isElementDisplayed(EditYourAccountInformation);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Account']")
	private WebElement accountBreadcrumb;

	public boolean isAccountBreadcrumbAvailableOnMyAccountPage() {
		return elementUtilities.isElementDisplayed(accountBreadcrumb);
	}

	public MyAccountInformationPage clickOnEditYourAccountInformation() {
		elementUtilities.clickOnElement(EditYourAccountInformation);
		return new MyAccountInformationPage(driver);
	}

	@FindBy(linkText = "Subscribe / unsubscribe to newsletter")
	private WebElement SubscibeAndUnsubscribeToNewsletterLink;

	public NewsletterSubscriptionPage clickOnSubscibeAndUnsubscribeToNewsletterLinkOnMyaccountpage() {
		elementUtilities.clickOnElement(SubscibeAndUnsubscribeToNewsletterLink);
		return new NewsletterSubscriptionPage(driver);
	}

	@FindBy(linkText = "Change your password")
	private WebElement changeYourPasswordOption;

	public ChangePasswordPage clickOnChangeYourPasswordOption() {
		changeYourPasswordOption.click();
		return new ChangePasswordPage(driver);
	}
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	private WebElement successMsgOnMyAccountPage;
	public void isSuccessMsgAvailableOnMyAccountPage()
	{
		elementUtilities.isElementDisplayedOnPage(successMsgOnMyAccountPage);
	}
	public RightColumnOptions  getRightColumnOptions()
	{
		return new RightColumnOptions(driver);
	}
}
