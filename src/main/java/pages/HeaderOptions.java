package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class HeaderOptions extends RootPage {
	WebDriver driver;

	public HeaderOptions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;

	@FindBy(linkText = "Register")
	private WebElement registerOption;

	public RegisterPage clickOnRegisterOption() {
		elementUtilities.clickOnElement(registerOption);

		return new RegisterPage(driver);
	}

	public void clickOnMyAccountDropmenu() {
		elementUtilities.clickOnElement(myAccountDropMenu);
	}

	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[text()='Login']")
	private WebElement Login;
	public boolean isLoginOptionAvailableInDropMenu()
	{
	return	elementUtilities.isElementDisplayed(Login);
	}

	public LoginPage clickOnLoginOption() {
		elementUtilities.clickOnElement(Login);
		return new LoginPage(driver);
	}

	@FindBy(xpath = "//ul[@class='list-inline']//i[@class='fa fa-phone']")
	private WebElement phoneIcon;

	public ContactUsPage selectPhoneIconOption() {
		elementUtilities.clickOnElement(phoneIcon);
		return new ContactUsPage(driver);
	}

	@FindBy(xpath = "//a[@id='wishlist-total']//i[@class='fa fa-heart']")
	private WebElement heartIcon;

	public LoginPage selectOnHeartIcon() {
		elementUtilities.clickOnElement(heartIcon);
		return new LoginPage(driver);
	}

	@FindBy(xpath = "//span[@class='hidden-xs hidden-sm hidden-md'][text()='Wish List (0)']")
	private WebElement wishListHeaderOption;

	public LoginPage selectWishListHeaderOption() {
		elementUtilities.clickOnElement(wishListHeaderOption);
		return new LoginPage(driver);
	}

	@FindBy(xpath = "//a[@title='Shopping Cart']//i[@class='fa fa-shopping-cart']")
	private WebElement wishListOption;

	public LoginPage selectOnWishListOption() {
		elementUtilities.clickOnElement(wishListOption);
		return new LoginPage(driver);
	}

	@FindBy(xpath = "//a[@title='Shopping Cart']//i[@class='fa fa-shopping-cart']")
	private WebElement shoppingCartIcon;

	public ShoppingCartPage selectOnShoppingCartIcon() {
		elementUtilities.clickOnElement(shoppingCartIcon);
		return new ShoppingCartPage(driver);
	}

	@FindBy(xpath = "//span[@class='hidden-xs hidden-sm hidden-md'][text()='Shopping Cart']")
	private WebElement shoppingCartHeaderOption;

	public ShoppingCartPage selectOnShoppingCartHeaderOption() {
		elementUtilities.clickOnElement(shoppingCartHeaderOption);
		return new ShoppingCartPage(driver);
	}

	@FindBy(xpath = "//a[@title='Checkout']//i[@class='fa fa-share']")
	private WebElement checkoutIcon;

	public CheckoutPage selectOnCheckoutIcon() {
		elementUtilities.clickOnElement(checkoutIcon);
		return new CheckoutPage(driver);
	}

	@FindBy(xpath = "//span[@class='hidden-xs hidden-sm hidden-md'][text()='Checkout']")
	private WebElement checkoutHeaderOption;

	public CheckoutPage selectOnCheckoutHeaderOption() {
		elementUtilities.clickOnElement(checkoutHeaderOption);
		return new CheckoutPage(driver);
	}

	@FindBy(linkText = "Qafox.com")
	private WebElement logo;

	public HomePage clickOnLogo() {
		elementUtilities.clickOnElement(logo);
		return new HomePage(driver);
	}

	@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
	private WebElement searchButton;

	public SearchPage clickOnSearchButton() {
		elementUtilities.clickOnElement(searchButton);
		return new SearchPage(driver);
	}
	@FindBy(linkText = "Logout")
	private WebElement logoutOption;

	public AccountLogoutPage clickOnLogoutOption() {
		elementUtilities.clickOnElement(logoutOption);
		return new AccountLogoutPage(driver);
	}
    public LoginPage getLoginPage()
    {
    	return new LoginPage(driver);
    }
	public LoginPage navigateToLoginPage() {
		clickOnMyAccountDropmenu();
		return clickOnLoginOption();
	}
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[text()='Logout']")
	private WebElement logoutOptionInDropdown;
	public AccountLogoutPage clickOnLogoutOptionInDropMenu()
	{
		elementUtilities.clickOnElement(logoutOptionInDropdown);
		return new AccountLogoutPage(driver);
	}
	public boolean isLogoutOptionAvailableInDropMenu()
	{
		return elementUtilities.isElementDisplayed(logoutOptionInDropdown);
	}
	public void logoutFromDropdownMenu()
	{
		clickOnMyAccountDropmenu();
		clickOnLogoutOptionInDropMenu();
		
	}
	public AccountLogoutPage getAccountLogoutPage()
	{
		return new AccountLogoutPage(driver);
	}
	@FindBy(xpath="//input[@name='search']")
	private WebElement searchField;
	public void enterSearchProductIntoSearchField(String productName)
	{
		elementUtilities.enterTextIntoElement(searchField, productName);
	}
public String getSearchBoxFieldPlaceholderText()
{
	return elementUtilities.getElementDomAttribute(searchField, "placeholder");
}
}
