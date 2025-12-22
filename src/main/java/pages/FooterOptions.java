package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class FooterOptions extends RootPage {
	WebDriver driver;

	public FooterOptions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//footer//a[text()='About Us']")
	private WebElement aboutUsOption;

	public AboutUsPage clickOnAboutUsOption() {
		elementUtilities.clickOnElement(aboutUsOption);
		return new AboutUsPage(driver);
	}

	@FindBy(xpath = "//footer//a[text()='Delivery Information']")
	private WebElement deliveryInformationOption;

	public DeliveryInformationPage clickOnDeliveryInformationOption() {
		elementUtilities.clickOnElement(deliveryInformationOption);
		return new DeliveryInformationPage(driver);
	}

	@FindBy(xpath = "//footer//a[text()='Privacy Policy']")
	private WebElement privacyPolicy;

	public PrivacyPolicyPage clickOnPrivacyPolicyOption() {
		elementUtilities.clickOnElement(privacyPolicy);
		return new PrivacyPolicyPage(driver);
	}

	@FindBy(xpath = "//footer//a[text()='Terms & Conditions']")
	private WebElement termsAndConditions;

	public TermsAndConditionsPage clickOnTermsAndConditions() {
		elementUtilities.clickOnElement(termsAndConditions);
		return new TermsAndConditionsPage(driver);
	}

	@FindBy(xpath = "//footer//a[text()='Contact Us']")
	private WebElement contactUsPage;

	public ContactUsPage clickOnContactUsPage() {
		elementUtilities.clickOnElement(contactUsPage);
		return new ContactUsPage(driver);
	}

	@FindBy(xpath = "//footer//a[text()='Returns']")
	private WebElement returnsOption;

	public ReturnsPage clickOnReturnsOption() {
		elementUtilities.clickOnElement(returnsOption);
		return new ReturnsPage(driver);
	}

	@FindBy(xpath = "//footer//a[text()='Site Map']")
	private WebElement siteMap;

	public SiteMapPage clickOnSiteMap() {
		elementUtilities.clickOnElement(siteMap);
		return new SiteMapPage(driver);
	}

	@FindBy(xpath = "//footer//a[text()='Brands']")
	private WebElement brands;

	public BrandsPage clickOnBrandsOption() {
		elementUtilities.clickOnElement(brands);
		return new BrandsPage(driver);
	}

	@FindBy(xpath = "//footer//a[text()='Gift Certificates']")
	private WebElement giftCertificates;

	public GiftCertificates clickOnGiftCertificates() {
		elementUtilities.clickOnElement(giftCertificates);
		return new GiftCertificates(driver);
	}

	@FindBy(xpath = "//footer//a[text()='Affiliate']")
	private WebElement affiliate;

	public AffiliatePage clickOnAffiliateOption() {
		elementUtilities.clickOnElement(affiliate);
		return new AffiliatePage(driver);
	}

	@FindBy(xpath = "//footer//a[text()='Specials']")
	private WebElement specials;

	public SpecialsOfferPage clickOnSpecialsOption() {
		elementUtilities.clickOnElement(specials);
		return new SpecialsOfferPage(driver);
	}

	@FindBy(xpath = "//footer//a[text()='My Account']")
	private WebElement myAccount;

	public MyAccountPage clickOnMyAccountOption() {
		elementUtilities.clickOnElement(myAccount);
		return new MyAccountPage(driver);
	}

	@FindBy(xpath = "//footer//a[text()='Order History']")
	private WebElement orderHistory;

	public OrderHistoryPage clickOnOrderHistoryOption() {
		elementUtilities.clickOnElement(orderHistory);
		return new OrderHistoryPage(driver);
	}

	@FindBy(xpath = "//footer//a[text()='Wish List']")
	private WebElement wishList;

	public WishListPage clickOnWishListOption() {
		elementUtilities.clickOnElement(wishList);
		return new WishListPage(driver);
	}

	@FindBy(xpath = "//footer//a[text()='Newsletter']")
	private WebElement newsletterOption;

	public NewsletterPage clickOnNewsletterOption() {
		elementUtilities.clickOnElement(newsletterOption);
		return new NewsletterPage(driver);
	}
	public WebDriver getDriver()
	{
		return driver;
	}

}
