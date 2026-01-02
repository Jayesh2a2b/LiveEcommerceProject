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

	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement successMsgOnMyAccountPage;

	public void isSuccessMsgAvailableOnMyAccountPage() {
		elementUtilities.isElementDisplayedOnPage(successMsgOnMyAccountPage);
	}

	public RightColumnOptions getRightColumnOptions() {
		return new RightColumnOptions(driver);
	}

	@FindBy(linkText = "Modify your address book entries")
	private WebElement modifyYourAddressBookEntries;

	public AddressBookPage clickOnModifyYourAddressBookEntries() {
		elementUtilities.clickOnElement(modifyYourAddressBookEntries);
		return new AddressBookPage(driver);
	}

	@FindBy(linkText = "Modify your wish list")
	private WebElement modifyYourWishListOption;

	public MyWishList clickOnModifyYourWishListOption() {
		elementUtilities.clickOnElement(modifyYourWishListOption);
		return new MyWishList(driver);
	}

	@FindBy(linkText = "View your order history")
	private WebElement viewYourOrderHistoryOption;

	public OrderHistoryPage clickOnViewYourOrderHistoryOption() {
		elementUtilities.clickOnElement(viewYourOrderHistoryOption);
		return new OrderHistoryPage(driver);
	}

	@FindBy(xpath = "//ul[@class='list-unstyled']//a[text()='Downloads']")
	private WebElement downloadsOption;

	public DownloadsPage clickOnDownloadsOption() {
		elementUtilities.clickOnElement(downloadsOption);
		return new DownloadsPage(driver);
	}
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='Your Reward Points']")
	private WebElement yourRewardPointOption;
	public YourRewardPointPage clickOnYourRewardPointOption()
	{
		elementUtilities.clickOnElement(yourRewardPointOption);
		return new YourRewardPointPage(driver);
	}
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='View your return requests']")
	private WebElement viewYourReturnRequestsOption;
	public ProductReturnsPage clickOnViewYourReturnRequestsOption()
	{
		elementUtilities.clickOnElement(viewYourReturnRequestsOption);
		return new ProductReturnsPage(driver);
	}
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='Your Transactions']")
	private WebElement yourTransactions;
	public YourTransactionsPage clickOnYourTransactions()
	{
		elementUtilities.clickOnElement(yourTransactions);
		return new YourTransactionsPage(driver);
	}
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='Recurring payments']")
	private WebElement recurringPaymentsOption;
	public RecurringPaymentsPage clickOnRecurringPaymentsOption()
	{
		elementUtilities.clickOnElement(recurringPaymentsOption);
		return new RecurringPaymentsPage(driver);
	}
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='Register for an affiliate account']")
	private WebElement registerForAnAffiliateAccountOption;
	public YourAffiliateInformationPage clickOnRegisterForAnAffiliateAccountOption()
	{
		elementUtilities.clickOnElement(registerForAnAffiliateAccountOption);
		return new YourAffiliateInformationPage(driver);
	}
}
