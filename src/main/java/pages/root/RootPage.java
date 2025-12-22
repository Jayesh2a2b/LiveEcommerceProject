package pages.root;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.AccountSuccessPage;
import pages.FooterOptions;
import pages.HeaderOptions;
import pages.HomePage;
import pages.RightColumnOptions;
import utils.ElementUtilities;

public class RootPage {
	WebDriver driver;
  public ElementUtilities elementUtilities;
	public RootPage(WebDriver driver) {
		this.driver = driver;
		elementUtilities=new ElementUtilities(driver);
		PageFactory.initElements(driver, this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	@FindBy(xpath = "//div[@id='content']/h1")
	private WebElement pageHeading;

	public String getPageHeading() {
		return elementUtilities.getTextFromElement(pageHeading);
	}

	public HeaderOptions getHeaderOptions() {
		return new HeaderOptions(driver);
	}

	public RightColumnOptions getRightColumnOptions() {
		return new RightColumnOptions(driver);
	}

	public AccountSuccessPage getAccountSuccessPage() {
		return new AccountSuccessPage(driver);
	}

	public FooterOptions getFooterOptions() {
		return new FooterOptions(driver);
	}

	public WebDriver getDriver(WebDriver driver) {
		return driver;
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//i[@class='fa fa-home']")
	private WebElement homeBreadcrumb;

	public HomePage selectOnHomeBreadcrumb() {
		elementUtilities.clickOnElement(homeBreadcrumb);
		return new HomePage(driver);
	}
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement pageLevelWarning;
	public String getPageLevelWarning()
	{
		return elementUtilities.getTextFromElement(pageLevelWarning);
	}
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	private WebElement pageLevelSuccessMsg;
	public String getPageLevelSuccessMsg()
	{
		return elementUtilities.getTextFromElement(pageLevelSuccessMsg);
	}
	public boolean isPageLevelSuccessMsgAvailableOnPage()
	{
		return elementUtilities.isElementDisplayed(pageLevelSuccessMsg);
	}

}
