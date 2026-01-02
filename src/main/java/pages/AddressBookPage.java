package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class AddressBookPage extends RootPage {
	WebDriver driver;

	public AddressBookPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//a[text()='New Address']")
	private WebElement newAddressButton;
	public AddAddressPage clickOnNewAddressButton()
	{
		elementUtilities.clickOnElement(newAddressButton);
		return new AddAddressPage(driver);
	}
	@FindBy(linkText="Edit")
	private WebElement editOption;
	public EditAddressPage clickOnEditOption()
	{
		elementUtilities.clickOnElement(editOption);
		return new EditAddressPage(driver);
				
	}
}
