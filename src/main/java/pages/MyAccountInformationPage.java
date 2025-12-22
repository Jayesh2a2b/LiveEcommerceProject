package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class MyAccountInformationPage extends RootPage {
	WebDriver driver;

	public MyAccountInformationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	public String getFirstNameFieldAttribute(String attributeName) {
		return elementUtilities.getElementDomAttribute(firstNameField, attributeName);
	}

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	public String getLastNameFieldAttribute(String attributeName) {
		return elementUtilities.getElementDomAttribute(lastNameField, attributeName);
	}

	@FindBy(id = "input-email")
	private WebElement emailField;

	public String getEmailFieldAttribute(String attributeName) {
		return elementUtilities.getElementDomAttribute(emailField, attributeName);
	}

	@FindBy(id = "input-telephone")
	private WebElement telephone;

	public String getTelephoneFieldAttribute(String attributeName) {
		return elementUtilities.getElementDomAttribute(telephone, attributeName);
	}

	public String getEmailFieldDomPoperty(String propertyName) {
		return elementUtilities.getElementDomProperty(emailField, propertyName);
	}

}
