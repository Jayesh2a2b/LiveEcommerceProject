package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class OrderSuccessPage extends RootPage {
	WebDriver driver;

	public OrderSuccessPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Success']")
	private WebElement successBreadcrumb;
	public boolean didWeNavigateToSuccessPage()
	{
		return elementUtilities.isElementDisplayed(successBreadcrumb);
	}
}
