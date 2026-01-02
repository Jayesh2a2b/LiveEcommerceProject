package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class YourTransactionsPage extends RootPage {
	WebDriver driver;

	public YourTransactionsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='pull-right']//a[text()='Continue']")
	private WebElement continueButton;
	public MyAccountPage clickOnContinueButton()
	{
		elementUtilities.clickOnElement(continueButton);
		return new MyAccountPage(driver);
	}
}
