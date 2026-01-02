package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class YourAffiliateInformationPage extends RootPage {
	WebDriver driver;

	public YourAffiliateInformationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "input-cheque")
	private WebElement chequePayeeNameField;
	public void enterChequePayeeNameInChequePayeeNameField(String chequePayeeName)
	{
		elementUtilities.enterTextIntoElement(chequePayeeNameField, chequePayeeName);
	}
	@FindBy(xpath="//input[@name='agree']")
private WebElement agreeOption;
	public void selectAgreeOption()
	{
		elementUtilities.clickOnElement(agreeOption);
	}
	@FindBy(xpath="//input[@class='btn btn-primary']")
	private WebElement continueButton;
	public MyAccountPage clickOnContinueButton()
	{
		elementUtilities.clickOnElement(continueButton);
		return new MyAccountPage(driver);
	}
}
