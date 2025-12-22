package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class NewsletterSubscriptionPage extends RootPage {
	WebDriver driver;

	public NewsletterSubscriptionPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Newsletter']")
	private WebElement NewsletterInBreadcrumb;

	public boolean IsNewsletterInBreadcrumbAvailableOnNewsletterSubscriptionPage() {
		return elementUtilities.isElementDisplayed(NewsletterInBreadcrumb);
	}

	@FindBy(xpath = "//div[@class='col-sm-10']//input[contains(@value,'1')]")
	private WebElement subscribeOption;

	public boolean IsSubscribeOptionInSelectedMode() {
		return elementUtilities.isElementSelected(subscribeOption);
	}
}
