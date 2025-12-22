package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class RightColumnOptions extends RootPage {
	WebDriver driver;

	public RightColumnOptions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//aside[@id='column-right']//a[text()='Register']")
	private WebElement registerOption;

	public RegisterPage clickOnRegisterOption() {
		elementUtilities.clickOnElement(registerOption);
		return new RegisterPage(driver);
	}

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Logout']")
	private WebElement logoutOption;

	public AccountLogoutPage clickOnLogoutOption() {
		logoutOption.click();
		return new AccountLogoutPage(driver);
	}

	public boolean isLogoutOptionAvailable() {
		return elementUtilities.isElementDisplayed(logoutOption);
	}

	@FindBy(xpath = "//div[@class='list-group']//a[text()='Login']")
	private WebElement loginOption;

	public LoginPage clickOnLoginOption() {
		elementUtilities.clickOnElement(loginOption);
		return new LoginPage(driver);
	}

	@FindBy(xpath = "//div[@class='list-group']//a[text()='Forgotten Password']")
	private WebElement forgottonPassword;

	public ForgottenPasswordPage clickOnForgottenPassword() {
		elementUtilities.clickOnElement(forgottonPassword);
		return new ForgottenPasswordPage(driver);
	}

	@FindBy(xpath = "//div[@class='list-group']//a[text()='My Account']")
	private WebElement myAccount;

	public MyAccountPage clickOnMyAccountPage() {
		elementUtilities.clickOnElement(myAccount);
		return new MyAccountPage(driver);
	}

	@FindBy(xpath = "//div[@class='list-group']//a[text()='Address Book']")
	private WebElement addressBook;

	public AddressBookPage clickOnAddressBook() {
		elementUtilities.clickOnElement(addressBook);
		return new AddressBookPage(driver);
	}

	@FindBy(xpath = "//div[@class='list-group']//a[text()='Wish List']")
	private WebElement wishList;

	public WishListPage clickOnWishListPage() {
		elementUtilities.clickOnElement(wishList);
		return new WishListPage(driver);
	}

	@FindBy(xpath = "//div[@class='list-group']//a[text()='Order History']")
	private WebElement orderHistory;

	public OrderHistoryPage clickOnOrderHistory() {
		elementUtilities.clickOnElement(orderHistory);
		return new OrderHistoryPage(driver);
	}

	@FindBy(xpath = "//div[@class='list-group']//a[text()='Downloads']")
	private WebElement downloads;

	public DownloadsPage clickOnDownloadsPage() {
		elementUtilities.clickOnElement(downloads);
		return new DownloadsPage(driver);
	}

	@FindBy(xpath = "//div[@class='list-group']//a[text()='Recurring payments']")
	private WebElement recurringPayments;

	public RecurringPaymentsPage clickOnRecurringPaymentsPage() {
		elementUtilities.clickOnElement(recurringPayments);
		return new RecurringPaymentsPage(driver);
	}

	@FindBy(xpath = "//div[@class='list-group']//a[text()='Reward Points']")
	private WebElement rewardPoints;

	public RewardPointsPage clickOnRewardPoints() {
		elementUtilities.clickOnElement(rewardPoints);
		return new RewardPointsPage(driver);
	}

	@FindBy(xpath = "//div[@class='list-group']//a[text()='Returns']")
	private WebElement returns;

	public ReturnsPage clickOnReturnsPage() {
		elementUtilities.clickOnElement(returns);
		return new ReturnsPage(driver);
	}

	@FindBy(xpath = "//div[@class='list-group']//a[text()='Transactions']")
	private WebElement transactions;

	public TransactionsPage clickOnTransactionsPage() {
		elementUtilities.clickOnElement(transactions);
		return new TransactionsPage(driver);
	}

	@FindBy(xpath = "//div[@class='list-group']//a[text()='Newsletter']")
	private WebElement newsletter;

	public NewsletterPage clickOnNewsletterPage() {
		elementUtilities.clickOnElement(newsletter);
		return new NewsletterPage(driver);
	}

	public AccountLogoutPage clickOnLogout() {
		clickOnMyAccountPage();
		return clickOnLogoutOption();
	}

	public boolean isLogoutOptionAvailableInRightColumbOptions() {
		return elementUtilities.isElementDisplayed(logoutOption);
	}
}
