package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;
import utils.ElementUtilities;

public class LoginPage extends RootPage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[text()='Continue']")
	private WebElement continueButton;

	public void clickOnContinueButton() {
		elementUtilities.clickOnElement(continueButton);
	}

	@FindBy(id = "input-email")
	private WebElement emailAddressField;

	public void enterEmailIntoEmailAddressField(String emailtext) {
		elementUtilities.enterTextIntoElement(emailAddressField, emailtext);
	}

	@FindBy(id = "input-password")
	private WebElement passwordField;

	public void enterPasswordIntoPasswordField(String passwordText) {
		elementUtilities.enterTextIntoElement(passwordField, passwordText);
	}

	@FindBy(xpath = "//input[@class='btn btn-primary']")
	private WebElement loginButton;

	public MyAccountPage clickOnLoginButton() {
		elementUtilities.clickOnElement(loginButton);
		return new MyAccountPage(driver);
	}

	@FindBy(linkText = "Forgotten Password")
	private WebElement forgottenPasswordLink;

	public ForgottenPasswordPage clickOnForgottenPasswordLink() {
		elementUtilities.clickOnElement(forgottenPasswordLink);
		return new ForgottenPasswordPage(driver);

	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Login']")
	private WebElement loginBreadCrumb;

	public boolean didWeNavigateToLoginPage() {
		return elementUtilities.isElementDisplayedOnPage(loginBreadCrumb);
	}

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible'][contains(text(),'Warning: No match for E-Mail Address and/or Password.')]")
	private WebElement loginPageWarningMsg;

	public boolean pageLevelWarningMsg() {
		return elementUtilities.isElementDisplayedOnPage(loginPageWarningMsg);
	}

	public String getEmailFieldPlaceholderText() {
		return elementUtilities.getElementDomAttribute(emailAddressField, "placeholder");
	}

	public String getPasswordFieldPlaceholderText() {
		return elementUtilities.getElementDomAttribute(passwordField, "placeholder");
	}

	public MyAccountPage loginIntoApplication(String emailtext, String passwordText) {
		elementUtilities.enterTextIntoElement(emailAddressField, emailtext);
		elementUtilities.enterTextIntoElement(passwordField, passwordText);
		elementUtilities.clickOnElement(loginButton);
		return new MyAccountPage(driver);

	}

	@FindBy(linkText = "Logout")
	private WebElement logoutOption;

	public AccountLogoutPage clickOnLogoutOption() {
		elementUtilities.clickOnElement(logoutOption);
		return new AccountLogoutPage(driver);
	}
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']//i[@class='fa fa-exclamation-circle']")
	private WebElement unsuccessfulWarningMsg;
	public String getTextOfUnsucessfulAttemptsWarningMsg()
	{
       //return elementUtilities.getTextFromElement(unsuccessfulWarningMsg);
		return unsuccessfulWarningMsg.getText();
	}
	public boolean unsuccessfulWarningMsgAvailableOnPage()
	{
		return unsuccessfulWarningMsg.isDisplayed();
	}
	public String getPasswordFieldDomAttribute(String attributeName)
	{
	return elementUtilities.getElementDomAttribute(passwordField, attributeName);
	}
	public void copyPasswordFromPasswordField()
	{
		elementUtilities.clickOnElement(passwordField);
		elementUtilities.copyingTextUsingKeyboardKeys(driver);
		
	}
	public void pasteCopiedTextIntoEmailAddressField()
	{
		elementUtilities.pasteTextIntoFieldUsingKeyboardKeys(emailAddressField, driver);
	}
	public String getPastedTextFromEmailField()
	{
		return elementUtilities.getElementDomAttribute(emailAddressField, "value");
	}

}
