package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;
import utils.CommonUtilities;

public class SearchPage extends RootPage {
	WebDriver driver;

	public SearchPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Search']")
	private WebElement searchPageBreadcrumb;

	public boolean didWeNavigateToSearchPage() {
		return elementUtilities.isElementDisplayed(searchPageBreadcrumb);
	}

	@FindBy(linkText = "HP LP3065")
	private WebElement existingProductOne;

	public boolean isExistingProductDisplayInsearchResultPage() {
		return elementUtilities.isElementDisplayed(existingProductOne);
	}

	@FindBy(xpath = "//div[@class='row']//p[text()='There is no product that matches the search criteria.']")
	private WebElement noProductMsg;

	public boolean isNoProductMatchesMsgAvailableOnPage() {
		return elementUtilities.isElementDisplayed(noProductMsg);
	}

	public String getNoProductMsg() {
		return elementUtilities.getTextFromElement(noProductMsg);
	}

	@FindBy(xpath = "//div[@class='product-thumb']")
	private List<WebElement> producctThumbnails;

	public int getProductCount() {
		return elementUtilities.getProductCount(producctThumbnails);
	}

	@FindBy(xpath = "//input[@id='input-search']")
	private WebElement searchCriteriaField;

	public String getSearchCriteriaFieldPlaceholderText() {
		return elementUtilities.getElementDomAttribute(searchCriteriaField, "placeholder");
	}

	public void enterIntoSearchCriteriaField(String textInProductDiscription) {
		elementUtilities.enterTextIntoElement(searchCriteriaField, textInProductDiscription);
	}

	@FindBy(id = "button-search")
	private WebElement searchButton;

	public void clickOnSearchButton() {
		elementUtilities.clickOnElement(searchButton);
	}

	@FindBy(id = "description")
	private WebElement searchInProductDiscriptionCheckbox;

	public void clickOnSearchInProductDiscriptionCheckbox() {
		elementUtilities.clickOnElement(searchInProductDiscriptionCheckbox);
	}

	@FindBy(xpath = "//a[text()='iMac']")
	private WebElement existingProductTwo;

	public boolean isExistingProductTwoIMacAvailableOnPage() {
		return elementUtilities.isElementDisplayed(existingProductTwo);
	}

	public boolean isProductHavingTextInDiscriptionDisplayedInSearchResults() {
		return elementUtilities.isElementDisplayed(existingProductTwo);
	}

	@FindBy(xpath = "//select[@name='category_id']")
	private WebElement allCategoriesDropDown;

	public void selectOptionfromCategoryDropdown(int optionIndex) {
		elementUtilities.selectOptionfromDropdownFieldByUsingIndex(allCategoriesDropDown, optionIndex);
	}

	public void selectOptionfromCategoryDropdown(String optionText) {
		elementUtilities.selectOptionfromDropdownFieldByUsingVisibleText(allCategoriesDropDown, optionText);
	}

	@FindBy(xpath = "//input[@name='sub_category']")
	private WebElement searchInsubgroupsCheckboxField;

	public void clickOnSearchInsubgroupsCheckboxField() {
		elementUtilities.clickOnElement(searchInsubgroupsCheckboxField);
	}

	@FindBy(id = "list-view")
	private WebElement List;

	public void clickOnListOption() {
		elementUtilities.clickOnElement(List);
	}

	@FindBy(xpath = "//span[text()='Add to Cart']")
	private WebElement addToCartOption;

	public void clickOnAddToCartOption() {
		elementUtilities.clickOnElement(addToCartOption);
	}

	@FindBy(xpath = "//button[@*='Add to Wish List']")
	private WebElement addToWishListOption;

	public void clickOnAddToWishListOption() {
		elementUtilities.clickOnElement(addToWishListOption);
	}

	@FindBy(xpath = "//button[@*='Compare this Product']")
	private WebElement compareThisProductOption;

	public void clickOnCompareThisProductOption() {
		elementUtilities.clickOnElement(compareThisProductOption);
	}

	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']//a[text()='shopping cart']")
	private WebElement addToCartSuccessStatus;

	public boolean isAddToCartSuccessStatusAvailableOnPage() {
		return elementUtilities.isElementDisplayed(addToCartSuccessStatus);
	}

	@FindBy(xpath = "//div[contains(@class,'alert') and contains(normalize-space(),'wish list')]")
	private WebElement wishlistStatusOnPage;

	public boolean isWishlistStatusAvailableOnPage() {
		return elementUtilities.isElementDisplayed(wishlistStatusOnPage);
	}

	@FindBy(xpath = "//div[contains(@class,'alert-success') and contains(normalize-space(),'product comparison')]")
	private WebElement productComparisonStatus;

	public boolean isProductComparisonStatusAvailableOnPage() {
		return elementUtilities.isElementDisplayed(productComparisonStatus);
	}

	@FindBy(xpath = "(//div[@class='product-thumb']//img)[1]")
	private WebElement productOneImage;

	public ProductDisplayPage clickOnProductOneImage() {
		elementUtilities.clickOnElement(productOneImage);
		return new ProductDisplayPage(driver);
	}

	@FindBy(xpath = "(//div[@class='product-thumb']//h4)[1]")
	private WebElement productNameOne;

	public ProductDisplayPage clickOnProductName() {
		elementUtilities.clickOnElement(productOneImage);
		return new ProductDisplayPage(driver);
	}

	@FindBy(id = "grid-view")
	private WebElement gridOption;

	public void clickOnGridOption() {
		elementUtilities.clickOnElement(gridOption);
	}

	@FindBy(id = "compare-total")
	private WebElement productCompareLink;

	public ProductComparisonPage clickOnProductCompareLink() {
		elementUtilities.clickOnElement(productCompareLink);
		return new ProductComparisonPage(driver);
	}

	@FindBy(id = "input-sort")
	private WebElement sortDropdownField;

	public void selectSortOptionInDropdownField(String optionText) {
		elementUtilities.selectOptionfromDropdownFieldByUsingVisibleText(sortDropdownField, optionText);
	}

	@FindBy(xpath = "//div[@class='product-thumb']//h4")
	private List<WebElement> sortedProducts;

	public boolean didProductsGotDisplayedInAscendingOrder() {
		List<String> originalList = elementUtilities.getTextOfElements(sortedProducts);
		return CommonUtilities.areItemsInListAreInAscendingOrder(originalList);

	}

	public boolean didProductsGotDisplayedInSearchPageInDesendingOrder() {
		List<String> originalList = elementUtilities.getTextOfElements(sortedProducts);
		return CommonUtilities.areItemsInListAreInDescendingOrder(originalList);

	}

	@FindBy(xpath = "//div[@class='product-thumb']//p[@class='price']")
	private List<WebElement> priceElements;

	public List<Double> getActualPriceListFromUI() {
         List<Double> actualPrices = new ArrayList<Double>();

		for (WebElement price : priceElements) {
			String text = price.getText().split("\n")[0];
			text = text.replace("$", "").replace(",", "").trim();
			actualPrices.add(Double.parseDouble(text));
		}
		return actualPrices;
	}

	public static List<Double> getExpectedPriceListAfterSortingInAscending(List<Double> actualPrices) {

		List<Double> expectedPrices = new ArrayList<Double>(actualPrices);
		Collections.sort(expectedPrices); // Ascending
		return expectedPrices;
	}
	public static List<Double> getExpectedPriceListAfterSortingInDescending(List<Double> actualPrices) {

		List<Double> expectedPrices = new ArrayList<Double>(actualPrices);
		Collections.sort(expectedPrices); // Ascending
		Collections.reverse(expectedPrices);
		return expectedPrices;
	}
	public boolean didProductsGotDisplayedInSearchPageSortedPriceInDescendingOrder() {
	    List<Double> actualPrices = getActualPriceListFromUI();
        List<Double> expectedPrices = getExpectedPriceListAfterSortingInDescending(actualPrices);
        return actualPrices.equals(expectedPrices);
	}

	public boolean didProductsGotDisplayedInSearchPageSortedPriceInAscendingOrder() {
	    List<Double> actualPrices = getActualPriceListFromUI();
        List<Double> expectedPrices = getExpectedPriceListAfterSortingInAscending(actualPrices);
        return actualPrices.equals(expectedPrices);
	}

}
