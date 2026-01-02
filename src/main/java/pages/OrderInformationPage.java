package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class OrderInformationPage extends RootPage {
	WebDriver driver;

	public OrderInformationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='btn btn-primary'][text()='Continue']")
	private WebElement continueButton;
	public OrderHistoryPage clickOnContinueButton()
	{
		elementUtilities.clickOnElement(continueButton);
		return new OrderHistoryPage(driver);
	}
}
