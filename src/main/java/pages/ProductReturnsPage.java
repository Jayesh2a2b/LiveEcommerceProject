package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class ProductReturnsPage extends RootPage {
	WebDriver driver;

	public ProductReturnsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Product Returns']")
	private WebElement productReturnsBreadcrumb;

	public boolean didWeNavigateToProductReturnsPage() {
		return elementUtilities.isElementDisplayed(productReturnsBreadcrumb);
	}

	@FindBy(xpath = "//i[@class='fa fa-eye']")
	private WebElement viewOption;

	public ReturnInformationPage clickOnViewOption() {
		elementUtilities.clickOnElement(viewOption);
		return new ReturnInformationPage(driver);
	}

	@FindBy(xpath = "//div[@class='pull-right']//a[text()='Continue']")
	private WebElement continueButton;

	public MyAccountPage clickOnContinueButton() {
		elementUtilities.clickOnElement(continueButton);
		return new MyAccountPage(driver);
	}
}
