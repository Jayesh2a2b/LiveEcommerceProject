package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class AddAddressPage extends RootPage {
	WebDriver driver;

	public AddAddressPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Add Address']")
	private WebElement addAddressBreadcrumb;
	public void didWeNavigateToAddAddressPage()
	{
		elementUtilities.isElementDisplayed(addAddressBreadcrumb);
	}
	@FindBy(id="input-firstname")
	private WebElement firstName;
	public void enterFirstName(String firstNameText)
	{
		elementUtilities.enterTextIntoElement(firstName,firstNameText );
	}
	@FindBy(id="input-lastname")
	private WebElement lastName;
	public void enterLastName(String lastNameText)
	{
		elementUtilities.enterTextIntoElement(lastName,lastNameText );
	}

	@FindBy(id="input-address-1")
	private WebElement address1;
	public void enterAddress(String addressText)
	{
		elementUtilities.enterTextIntoElement(address1,addressText );
	}

	@FindBy(id="input-city")
	private WebElement city;
	public void enterCity(String cityText)
	{
		elementUtilities.enterTextIntoElement(city,cityText );
	}

	@FindBy(id="input-postcode")
	private WebElement postCode;
	public void enterPostCode(String postCodeText)
	{
		elementUtilities.enterTextIntoElement(postCode,postCodeText );
	}

	@FindBy(xpath="//select[@id='input-country']")
	private WebElement country;
	public void selectCountryName(String countryName)
	{
		elementUtilities.selectOptionfromDropdownFieldByUsingVisibleText(country, countryName);
	}
	@FindBy(xpath="//select[@id='input-zone']")
	private WebElement state;
	public void selectStateName(String stateName)
	{
		elementUtilities.selectOptionfromDropdownFieldByUsingVisibleText(state, stateName);
	}

	@FindBy(xpath="//input[@class='btn btn-primary']")
	private WebElement continueButton;
	public void clickOnContinueButton()
	{
		elementUtilities.clickOnElement(continueButton);
	}
	public void addAddress(String firstName,String lastName,String addressText,String cityText,String postCodeText,String countryName,String stateName)
	{
		enterFirstName(firstName);
		enterLastName(lastName);
		enterAddress(addressText);
		enterCity(cityText);
		enterPostCode(postCodeText);
		selectCountryName(countryName);
		selectStateName(stateName);
		clickOnContinueButton();
		
	}
}
