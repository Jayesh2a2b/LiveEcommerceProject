package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class RegisterPage extends RootPage {
	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	public WebElement getFirstNameFieldElement() {
		return firstNameField;
	}

	public void getFirstNameFieldReset() {
		elementUtilities.clearTextFromElement(firstNameField);
	}

	public String getFirstNamePlaceholderText() {
		return elementUtilities.getElementDomAttribute(firstNameField, "placeholder");
	}

	public String getLastNamePlaceholderText() {
		return elementUtilities.getElementDomAttribute(lastNameField, "placeholder");
	}

	public WebElement getLastNameFieldElement() {
		return lastNameField;
	}

	public WebElement getEmailFieldElement() {
		return emailField;
	}

	public WebElement getTelephoneFieldElement() {
		return telephoneField;
	}

	public WebElement getPasswordFieldElement() {
		return passwordField;
	}

	public WebElement getConfirmPasswordFieldElement() {
		return confirmPasswordField;
	}

	public String getEmailPlaceholderText() {
		return elementUtilities.getElementDomAttribute(emailField, "placeholder");
	}

	public String getPasswordPlaceholderText() {
		return elementUtilities.getElementDomAttribute(passwordField, "placeholder");
	}

	public String getTelephonePlaceholderText() {
		return elementUtilities.getElementDomAttribute(telephoneField, "placeholder");
	}

	public String getPasswordConfirmPlaceholderText() {
		return elementUtilities.getElementDomAttribute(confirmPasswordField, "placeholder");
	}

	public void enterFirstName(String firstNameText) {
		elementUtilities.enterTextIntoElement(firstNameField, firstNameText);
		//firstNameField.sendKeys(firstNameText);
	}

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	public void enterLastName(String lastNameText) {
		elementUtilities.enterTextIntoElement(lastNameField, lastNameText);
		//lastNameField.sendKeys(lastNameText);
	}

	public void getLastNameFieldReset() {
		elementUtilities.clearTextFromElement(lastNameField);
	}

	@FindBy(id = "input-email")
	private WebElement emailField;

	public void enterEmail(String emailText) {
		elementUtilities.enterTextIntoElement(emailField, emailText);
		//emailField.sendKeys(emailText);
	}

	public void getEmailFieldReset() {
		elementUtilities.clearTextFromElement(emailField);
	}

	public String getEmailWarningMsg() {
		return elementUtilities.getTextFromElement(emailField);
	}

	public String getEmailValidationMessage() {
		return elementUtilities.getElementDomAttribute(emailField, "validationMessage");
		//return emailField.getAttribute("validationMessage");
	}

	@FindBy(id = "input-telephone")
	private WebElement telephoneField;

	public void enterTelephone(String telephoneText) {
		elementUtilities.enterTextIntoElement(telephoneField, telephoneText);
		//telephoneField.sendKeys(telephoneText);
	}

	public void getTelephoneFieldReset() {
		elementUtilities.clearTextFromElement(telephoneField);
	}

	@FindBy(id = "input-password")
	private WebElement passwordField;

	public void enterPassword(String passwordText) {
		elementUtilities.enterTextIntoElement(passwordField, passwordText);
		//passwordField.sendKeys(passwordText);
	}

	public void getPasswordFieldReset() {
		elementUtilities.clearTextFromElement(passwordField);
	}

	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordField;

	public void enterConfirmPassword(String confirmPaaswordText) {
		elementUtilities.enterTextIntoElement(confirmPasswordField, confirmPaaswordText);
		//confirmPasswordField.sendKeys(confirmPaaswordText);
	}

	@FindBy(name = "agree")
	private WebElement privacyPolicyField;

	public boolean statusOfPrivacyPolicyField() {
		return elementUtilities.isElementSelected(privacyPolicyField);
	}

	public void clickOnPrivacyPolicy() {
		elementUtilities.clickOnElement(privacyPolicyField);
	}

	@FindBy(xpath = "//input[@class='btn btn-primary']")
	private WebElement continueField;

	public WebElement getContinueButtonFieldElement() {
		return continueField;
	}

	public boolean getStatusOfContinueButton() {
		return elementUtilities.isElementDisplayed(continueField);
	}

	public String getFirstNameCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(firstNameField, propertyName);
	}

	public String getLastNameCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(lastNameField, propertyName);
	}

	public String getEmailCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(emailField, propertyName);
	}

	public String getTelephoneCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(telephoneField, propertyName);
	}

	public String getPasswordCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(passwordField, propertyName);
	}

	public String getPasswordFieldDomAttribute(String attributeName) {
		return elementUtilities.getElementDomAttribute(passwordField, attributeName);
		//return passwordField.getDomAttribute(attributeName);
	}

	public String getConfirmPasswordFieldDomAttribute(String attributeName) {
		return elementUtilities.getElementDomAttribute(confirmPasswordField, attributeName);
		//return confirmPasswordField.getDomAttribute(attributeName);
	}

	public String getConfirmPasswordCSSValue(String propertyName) {
		return elementUtilities.getElementCSSValue(confirmPasswordField, propertyName);
	}

	public AccountSuccessPage clickOnContinueButton() {
		elementUtilities.clickOnElement(continueField);
		return new AccountSuccessPage(driver);
	}

	@FindBy(name = "newsletter")
	private WebElement newsLetterOption;

	public void clickOnNewsLetterOption() {
		elementUtilities.clickOnElement(newsLetterOption);
	}

	@FindBy(xpath = "//div[text()='First Name must be between 1 and 32 characters!']")
	private WebElement FirstNameWarningMsg;

	public boolean isFirstNameWarningMsgAvailableOnRegisterPage() {
		return elementUtilities.isElementDisplayed(FirstNameWarningMsg);
	}

	public String getFirstNameWarningMsg() {
		return elementUtilities.getTextFromElement(FirstNameWarningMsg);
	}

	@FindBy(xpath = "//div[text()='Last Name must be between 1 and 32 characters!']")
	private WebElement LastNameWarningMsg;

	public boolean IsLastNameWarningMsgAvailableOnRegisterPage() {
		return elementUtilities.isElementDisplayed(LastNameWarningMsg);
	}

	public String getLastNameWarningMsg() {
		return elementUtilities.getTextFromElement(LastNameWarningMsg);
	}

	@FindBy(xpath = "//div[text()='E-Mail Address does not appear to be valid!']")
	private WebElement EmailWarningMsg;

	public boolean IsEmailWarningMsgAvailableOnRegisterPage() {
		return	elementUtilities.isElementDisplayed(EmailWarningMsg);
	}

	@FindBy(xpath = "//div[text()='Telephone must be between 3 and 32 characters!']")
	private WebElement TelephoneWarningMsg;

	public boolean IsTelephoneWarningMsgAvailableOnRegisterPage() {
		return elementUtilities.isElementDisplayed(TelephoneWarningMsg);
	}

	public String getTelephoneWarningMsg() {
		return elementUtilities.getTextFromElement(TelephoneWarningMsg);
	}

	@FindBy(xpath = "//div[text()='Password must be between 4 and 20 characters!']")
	private WebElement PasswordWarningMsg;

	public boolean IsPasswordWarningMsgAvailableOnRegisterPage() {
		return elementUtilities.isElementDisplayed(PasswordWarningMsg);
	}

	public String getPasswordWarningMsg() {
		return elementUtilities.getTextFromElement(PasswordWarningMsg);
	}

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement PrivacyPolicyWarningMsg;

	public boolean IsPrivacyPolicyWarningMsgAvailableOnRegisterPage() {
		return elementUtilities.isElementDisplayed(PrivacyPolicyWarningMsg);
	}

	public String getPrivacyPolicyWarningMsg() {
		return elementUtilities.getTextFromElement(PrivacyPolicyWarningMsg);
	}

	@FindBy(xpath = "//div[text()='Password confirmation does not match password!']")
	private WebElement PasswordConfirmWarningMsg;

	public String getTextOfPasswordConfirmWarningMsgAvailableOnRegisterPage() {
		return elementUtilities.getTextFromElement(PasswordConfirmWarningMsg);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Register']")
	private WebElement RegisterOptionContainInBreadcrumb;

	public boolean didWeNavigateToRegisterPage() {
		return	elementUtilities.isElementDisplayed(RegisterOptionContainInBreadcrumb);
	}

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement duplicateEmailWarningMsg;

	public boolean didWeGetDuplicateEmailWarningMsg() {
		return elementUtilities.isElementDisplayed(duplicateEmailWarningMsg);
	}

	@FindBy(css = "label[for='input-firstname']")
	private WebElement firstNameFieldLabel;

	public WebElement getFirstNameFieldLabel() {
		return firstNameFieldLabel;
	}

	@FindBy(css = "label[for='input-lastname']")
	private WebElement lastNameFieldLabel;

	public WebElement getLastNameFieldLabel() {
		return lastNameFieldLabel;
	}

	@FindBy(css = "label[for='input-email']")
	private WebElement emailFieldLabel;

	public WebElement getEmailFieldLabel() {
		return emailFieldLabel;
	}

	@FindBy(css = "label[for='input-telephone']")
	private WebElement telephoneFieldLabel;

	public WebElement getTelephoneFieldLabel() {
		return telephoneFieldLabel;
	}

	@FindBy(css = "label[for='input-password']")
	private WebElement passwordFieldLabel;

	public WebElement getPasswordFieldLabel() {
		return passwordFieldLabel;
	}

	@FindBy(css = "label[for='input-confirm']")
	private WebElement confirmPasswordFieldLabel;

	public WebElement getConfirmPasswordFieldLabel() {
		return confirmPasswordFieldLabel;
	}

}
