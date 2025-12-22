package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class ChangePasswordPage extends RootPage {
	WebDriver driver;

	public ChangePasswordPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "input-password")
	private WebElement passwordfield;

	public void enterNewPasswordIntoPasswordField(String pwd) {
		elementUtilities.enterTextIntoElement(passwordfield, pwd);
	}

	@FindBy(id = "input-confirm")
	private WebElement confpasswordField;

	public void enterNewPasswordIntoConPasswordField(String conPWD) {
		elementUtilities.enterTextIntoElement(confpasswordField, conPWD);

	}

	@FindBy(xpath = "//input[@class='btn btn-primary']")
	private WebElement continueButton;

	public MyAccountPage clickOnContinueButton() {
		elementUtilities.clickOnElement(continueButton);
		return new MyAccountPage(driver);
	}
}
