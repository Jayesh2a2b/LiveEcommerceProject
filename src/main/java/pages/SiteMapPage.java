package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class SiteMapPage extends RootPage {
	WebDriver driver;

	public SiteMapPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='col-sm-6']//a[text()='Search']")
	private WebElement searchOption;

	public SearchPage clickOnSearchOptionInSiteMapPage() {
		elementUtilities.clickOnElement(searchOption);
		return new SearchPage(driver);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Site Map']")
	private WebElement siteMapBreadcrumbOption;

	public boolean didWeNavigateToSiteMapPage() {
		return elementUtilities.isElementDisplayed(siteMapBreadcrumbOption);
	}
}
