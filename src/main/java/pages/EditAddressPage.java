package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class EditAddressPage extends RootPage {
	WebDriver driver;

	public EditAddressPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@class='btn btn-primary']")
	private WebElement continueButton;
	public AddressBookPage clickOnContinueButton()
	{
		elementUtilities.clickOnElement(continueButton);
		return new AddressBookPage(driver);
	}
}
