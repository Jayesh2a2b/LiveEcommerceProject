package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class ProductDisplayPage extends RootPage {
	WebDriver driver;

	public ProductDisplayPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath = "//ul[@class='nav nav-tabs']//a[text()='Reviews (0)']")
	private WebElement productReviews;
	@FindBy(id = "button-cart")
	private WebElement addToCartButton;
	public boolean isAddToCartOptionAvailableOnPage()
	{
		return elementUtilities.isElementDisplayed(addToCartButton );
	}

	public boolean didWeNavigateToProductDisplayPage() {
		return elementUtilities.isElementDisplayed(productReviews);
	}

}
