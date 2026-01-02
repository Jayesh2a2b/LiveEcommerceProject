package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class CheckoutPage extends RootPage {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Checkout']")
	private WebElement checkoutBreadcrumbOptions;

	public boolean didWeNavigateToCheckoutPage() {
		return elementUtilities.isElementDisplayed(checkoutBreadcrumbOptions);
	}
	@FindBy(xpath="//div[@class='pull-right']//input[@id='button-payment-address']")
	private WebElement billingDetailsContinueButton;
	public void clickOnBillingDetaisContinueButton()
	{
		elementUtilities.waitForElementAndClick(billingDetailsContinueButton, 2);
	}
	@FindBy(xpath="//div[@class='pull-right']//input[@id='button-shipping-address']")
	private WebElement deliveryDetailsContinueButton;
	public void clickOnDeliveryDetailsContinueButton()
	{
		elementUtilities.waitForElementAndClick(deliveryDetailsContinueButton, 2);
	}
	@FindBy(xpath="//div[@class='pull-right']//input[@id='button-shipping-method']")
	private WebElement deliveryMethodContinueButton;
	public void clickOnDeliveryMethodContinueButton()
	{
		elementUtilities.waitForElementAndClick(deliveryMethodContinueButton, 2);
	}
	@FindBy(xpath="//div[@class='pull-right']//input[@id='button-payment-method']")
	private WebElement paymentMethodContinueButton;
	public void clickOnPaymentMethodContinueButton()
	{
		elementUtilities.waitForElementAndClick(paymentMethodContinueButton, 2);
	}
	@FindBy(xpath="//input[@name='agree']")
	private WebElement agreeOption;
	public void clickOnAgreeOption()
	{
		elementUtilities.waitForElementAndClick(agreeOption, 2);
	}
	@FindBy(xpath="//input[@id='button-confirm']")
	private WebElement confirmOrderButton;
	public OrderSuccessPage clickOnConfirmOrderButton()
	{
		elementUtilities.waitForElementAndClick(confirmOrderButton, 2);
		return new OrderSuccessPage(driver);
	}
}
