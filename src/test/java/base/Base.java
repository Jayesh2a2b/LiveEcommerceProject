package base;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;
import pages.AboutUsPage;
import pages.AccountLogoutPage;
import pages.AccountSuccessPage;
import pages.AddAddressPage;
import pages.AddressBookPage;
import pages.AffiliatePage;
import pages.BrandsPage;
import pages.ChangePasswordPage;
import pages.CheckoutPage;
import pages.ContactUsPage;
import pages.DeliveryInformationPage;
import pages.DownloadsPage;
import pages.EditAddressPage;
import pages.FooterOptions;
import pages.ForgottenPasswordPage;
import pages.GiftCertificates;
import pages.HeaderOptions;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountInformationPage;
import pages.MyAccountPage;
import pages.MyWishList;
import pages.NewsletterPage;
import pages.NewsletterSubscriptionPage;
import pages.OrderHistoryPage;
import pages.OrderInformationPage;
import pages.OrderSuccessPage;
import pages.PrivacyPolicyPage;
import pages.ProductComparisonPage;
import pages.ProductDisplayPage;
import pages.ProductReturnsPage;
import pages.RecurringPaymentsPage;
import pages.RegisterPage;
import pages.ReturnInformationPage;
import pages.ReturnsPage;
import pages.RewardPointsPage;
import pages.RightColumnOptions;
import pages.SearchPage;
import pages.ShoppingCartPage;
import pages.SiteMapPage;
import pages.SpecialsOfferPage;
import pages.TermsAndConditionsPage;
import pages.TransactionsPage;
import pages.WishListPage;
import pages.YourAffiliateInformationPage;
import pages.YourRewardPointPage;
import pages.YourTransactionsPage;
import utils.CommonUtilities;

public class Base {
	public WebDriver driver;
	public Properties prop;
	public String browserName;
	public HeaderOptions headerOptions;
	public RegisterPage registerPage;
	public AccountSuccessPage accountSuccessPage;
	public MyAccountPage myAccountPage;
	public NewsletterSubscriptionPage newsletterSubscriptionPage;
	public LoginPage loginPage;
	public RightColumnOptions rightColumnOptions;
	public MyAccountInformationPage myAccountInformationPage;
	public SoftAssert softAssert;
	public ContactUsPage contactUsPage;
	public ShoppingCartPage shoppingCartPage;
	public CheckoutPage checkoutPage;
	public HomePage homePage;
	public SearchPage searchPage;
	public ForgottenPasswordPage forgottenPasswordPage;
	public AddressBookPage addressBookPage;
	public WishListPage wishListPage;
	public OrderHistoryPage orderHistoryPage;
	public DownloadsPage downloadsPage;
	public RecurringPaymentsPage recurringPaymentsPage;
	public RewardPointsPage rewardPointsPage;
	public ReturnsPage returnsPage;
	public TransactionsPage transactionsPage;
	public NewsletterPage newsletterPage;
	public FooterOptions footerOptions;
	public AboutUsPage aboutUsPage;
	public DeliveryInformationPage deliveryInformationPage;
	public PrivacyPolicyPage privacyPolicyPage;
	public TermsAndConditionsPage termsAndConditionsPage;
	public SiteMapPage siteMapPage;
	public BrandsPage brandsPage;
	public GiftCertificates giftCertificatesPage;
	public AffiliatePage affiliatePage;
	public SpecialsOfferPage specialOffersPage;
	public Actions actions;
	public AccountLogoutPage accountLogoutPage;
	public ChangePasswordPage changePasswordPage;
	public ProductDisplayPage productDisplayPage;
	public ProductComparisonPage productComparisonPage;
	public AddAddressPage addAddressPage;
	public EditAddressPage editAddressPage;
	public MyWishList myWishListPage;
	public YourRewardPointPage yourRewardPointPage;
	public OrderInformationPage orderInformationPage;
	public ProductReturnsPage productReturnsPage;
	public ReturnInformationPage returnInformationPage;
	public YourTransactionsPage yourTransactionsPage;
	public YourAffiliateInformationPage yourAffiliateInformationPage;
	public OrderSuccessPage orderSuccessPage;
	public WebDriver openBrowserAndApplicationPageUrl() {
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
		return driver;

	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();

	}

	public String getPageSourceCode() {
		return driver.getPageSource();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public void navigateBackInBrowser(WebDriver driver) {
		driver.navigate().back();
	}

	public void closeBrowser(WebDriver driver) {
		if (driver != null) { // Prevents NullPointerException
			driver.quit(); // Close browser & end session
			// driver = null;
		}
	}

	public Actions getActions(WebDriver driver) {
		Actions actions = new Actions(driver);
		return actions;
	}

	public Actions clickKeyboardkeyMultipleTimes(Actions actions, WebDriver driver, Keys keyName, int noOfTimes) {
		for (int i = 1; i <= noOfTimes; i++) {
			actions.sendKeys(keyName).build().perform();
		}
		return actions;
	}

	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Actions typeTextUsingActions(Actions actions, String text) {
		actions.sendKeys(text).perform();
		return actions;
	}

	public Properties swapPasswords(Properties prop) {
		String oldPassword = prop.getProperty("OldPWD");
		String newPassword = prop.getProperty("NewPWD");
		prop.setProperty("OldPWD", newPassword);
		prop.setProperty("NewPWD", oldPassword);
		prop = CommonUtilities.storePropertyFile(prop);
		return prop;
	}

	public void navigateToPage(String url) {
		driver.navigate().to(url);
	}
	public String getBaseURL()
	{
		return prop.getProperty("AppUrl");
	}
}
