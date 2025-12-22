package tests;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.HeaderOptions;
import pages.ProductComparisonPage;
import pages.ProductDisplayPage;
import utils.CommonUtilities;

public class Search extends Base {
	WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = openBrowserAndApplicationPageUrl();
		headerOptions = new HeaderOptions(driver);

	}

	@AfterMethod
	public void teardowm() {
		closeBrowser(driver);
	}
 
	@Test(priority = 1)
	public void VerifySearchingWithAnExistingProductName() {
		headerOptions.enterSearchProductIntoSearchField(prop.getProperty("ExistingProduct"));
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		Assert.assertTrue(searchPage.isExistingProductDisplayInsearchResultPage());
	}

	@Test(priority = 2)
	public void VerifySearchingWithNonExistingProductName() {
		headerOptions.enterSearchProductIntoSearchField(prop.getProperty("NonExistingProduct"));
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		Assert.assertEquals(searchPage.getNoProductMsg(), "There is no product that matches the search criteria.");
	}

	@Test(priority = 3)
	public void VerifySearchingWithoutProvidingAnyProductName() {
		headerOptions.enterSearchProductIntoSearchField("");
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertEquals(searchPage.getNoProductMsg(), "There is no product that matches the search criteria.");

	}

	@Test(priority = 4)
	public void VeriftySearchingForAProductAfterLoginToTheApplication() {
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.loginIntoApplication(prop.getProperty("ValidEmail"), prop.getProperty("PWD"));
		headerOptions = myAccountPage.getHeaderOptions();
		headerOptions.enterSearchProductIntoSearchField(prop.getProperty("ExistingProduct"));
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		Assert.assertTrue(searchPage.isExistingProductDisplayInsearchResultPage());

	}

	@Test(priority = 5)
	public void VerifyProductSearchResultingMultipleProducts() {
		headerOptions.enterSearchProductIntoSearchField(prop.getProperty("ExistingProductTwo"));
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		Assert.assertTrue(searchPage.getProductCount() > 0);
	}

	@Test(priority = 6)
	public void VerifySearchFieldPlaceholder() {
		Assert.assertEquals(headerOptions.getSearchBoxFieldPlaceholderText(), "Search");
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertEquals(searchPage.getSearchCriteriaFieldPlaceholderText(), "Keywords");

	}

	@Test(priority = 7)
	public void VerifySearchingUsingSearchCriteriaField() {
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterIntoSearchCriteriaField(prop.getProperty("ExistingProduct"));
		searchPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.isExistingProductDisplayInsearchResultPage());
	}

	@Test(priority = 8)
	public void VerifySearchUsingTheTextFromTheProductDescription() {
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		searchPage.enterIntoSearchCriteriaField(prop.getProperty("ExistingProductTwoHavingTextInDescription"));
		searchPage.clickOnSearchInProductDiscriptionCheckbox();
		searchPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.isProductHavingTextInDiscriptionDisplayedInSearchResults());

	}

	@Test(priority = 9)
	public void VerifySearchBySelectingTheCategoryOfProduct() {
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		searchPage.enterIntoSearchCriteriaField(prop.getProperty("ExistingProductThree"));
		searchPage.selectOptionfromCategoryDropdown(
				CommonUtilities.convertToInt(prop.getProperty("CorrectCategoryIndex")));
		searchPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.isExistingProductTwoIMacAvailableOnPage());
		searchPage
				.selectOptionfromCategoryDropdown(CommonUtilities.convertToInt(prop.getProperty("WrongCategoryIndex")));
		searchPage.clickOnSearchButton();
		Assert.assertFalse(searchPage.isExistingProductTwoIMacAvailableOnPage());
		Assert.assertTrue(searchPage.isNoProductMatchesMsgAvailableOnPage());

	}

	@Test(priority = 10)
	public void VerifySearchBySelectingToSearchInSubcategories() {
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		searchPage.enterIntoSearchCriteriaField(prop.getProperty("ExistingProductThree"));
		searchPage.selectOptionfromCategoryDropdown(prop.getProperty("Subcategories"));
		searchPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.isNoProductMatchesMsgAvailableOnPage());
		searchPage.clickOnSearchInsubgroupsCheckboxField();
		searchPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.isExistingProductTwoIMacAvailableOnPage());

	}

	@Test(priority = 11)
	public void VerifyListAndGridViewsInSearchResultsPageHavingOneProduct() {
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterIntoSearchCriteriaField(prop.getProperty("ExistingProductThree"));
		searchPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		searchPage.clickOnListOption();
		Assert.assertTrue(searchPage.getProductCount() == 1);
		searchPage.clickOnAddToCartOption();
		String expected = "Success: You have added " + prop.getProperty("ExistingProductThree")
				+ " to your shopping cart!";
		// Verification checkPoint 1
		Assert.assertTrue(searchPage.getPageLevelSuccessMsg().contains(expected));
		// Verification checkpoint 1 alternate
		Assert.assertTrue(searchPage.isAddToCartSuccessStatusAvailableOnPage());
		refreshPage(driver);
		searchPage.clickOnAddToWishListOption();
		expected = "You must login or create an account to save "+prop.getProperty("ExistingProductThree")+" to your wish list!";
		Assert.assertTrue(searchPage.getPageLevelSuccessMsg().contains(expected));
		// Assert.assertTrue(searchPage.isWishlistStatusAvailableOnPage());
		refreshPage(driver);
		searchPage.clickOnCompareThisProductOption();
		expected = "Success: You have added "+prop.getProperty("ExistingProductThree")+" to your product comparison!";
		Assert.assertTrue(searchPage.getPageLevelSuccessMsg().contains(expected));
		Assert.assertTrue(searchPage.isProductComparisonStatusAvailableOnPage());
		productDisplayPage = searchPage.clickOnProductOneImage();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(driver);
		productDisplayPage=searchPage.clickOnProductName();
		Assert.assertTrue(productDisplayPage.isAddToCartOptionAvailableOnPage());
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(driver);
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterIntoSearchCriteriaField(prop.getProperty("ExistingProductThree"));
		searchPage.clickOnSearchButton();
		searchPage.clickOnGridOption();
		Assert.assertTrue(searchPage.getProductCount() == 1);
		searchPage.clickOnAddToCartOption();
		 expected = "Success: You have added " + prop.getProperty("ExistingProductThree")
				+ " to your shopping cart!";
		// Verification checkPoint 1
		Assert.assertTrue(searchPage.getPageLevelSuccessMsg().contains(expected));
		// Verification checkpoint 1 alternate 
		Assert.assertTrue(searchPage.isAddToCartSuccessStatusAvailableOnPage());
		refreshPage(driver);
		searchPage.clickOnAddToWishListOption();
		expected = "You must login or create an account to save "+prop.getProperty("ExistingProductThree")+" to your wish list!";
		Assert.assertTrue(searchPage.getPageLevelSuccessMsg().contains(expected));
		// Assert.assertTrue(searchPage.isWishlistStatusAvailableOnPage()); // Verification checkpoint alternate 
		refreshPage(driver);
		searchPage.clickOnCompareThisProductOption();
		expected = "Success: You have added "+prop.getProperty("ExistingProductThree")+" to your product comparison!";
		Assert.assertTrue(searchPage.getPageLevelSuccessMsg().contains(expected));
		Assert.assertTrue(searchPage.isProductComparisonStatusAvailableOnPage());
		productDisplayPage = searchPage.clickOnProductOneImage();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(driver);
		productDisplayPage=searchPage.clickOnProductName();
		Assert.assertTrue(productDisplayPage.isAddToCartOptionAvailableOnPage());
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());	
	}
	@Test(priority=12)
	public void VerifyListAndGridViewsWhenMultipleProductsAreDisplayedInTheSearchResults()
	{
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterIntoSearchCriteriaField(prop.getProperty("ExistingProductTwo"));
		searchPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		Assert.assertTrue(searchPage.getProductCount()>1);
		searchPage.clickOnListOption();
		Assert.assertTrue(searchPage.getProductCount()>1);
		searchPage.clickOnGridOption();;
		Assert.assertTrue(searchPage.getProductCount()>1);
	}
	@Test(priority=13)
	public void VerifyNavigatingToProductComparePageFromSearchResultsPage()
	{
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterIntoSearchCriteriaField(prop.getProperty("ExistingProductThree"));
		searchPage.clickOnSearchButton();
		productComparisonPage = searchPage.clickOnProductCompareLink();
		Assert.assertTrue(productComparisonPage.didWeNavigateToProductComparisonPage());

	}
	@Test
	public void VerifySortingOptionInSearchResultPageByDefaultOptoion()
	{ 
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterIntoSearchCriteriaField(prop.getProperty("ExistingProductTwo"));
		searchPage.clickOnSearchButton();
		searchPage.clickOnListOption();
		Assert.assertTrue(searchPage.getProductCount()>1);
		searchPage.selectSortOptionInDropdownField("Default");
		Assert.assertTrue(searchPage.didProductsGotDisplayedInAscendingOrder());
	}
	@Test
	public void VerifySortingOptionInSearchResultPageByDesendingName()
	{ 
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterIntoSearchCriteriaField(prop.getProperty("ExistingProductTwo"));
		searchPage.clickOnSearchButton();
		searchPage.clickOnListOption();
		Assert.assertTrue(searchPage.getProductCount()>1);
		searchPage.selectSortOptionInDropdownField("Name (Z - A)");
		Assert.assertTrue(searchPage.didProductsGotDisplayedInSearchPageInDesendingOrder());
	}
	@Test
	public void VerifySortingOptionPriceInAscendingOrder()
	{
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterIntoSearchCriteriaField(prop.getProperty("ExistingProductTwo"));
		searchPage.clickOnSearchButton();
		searchPage.clickOnListOption();
		Assert.assertTrue(searchPage.getProductCount()>1);
		searchPage.selectSortOptionInDropdownField("Price (Low > High)");
		Assert.assertTrue(searchPage.didProductsGotDisplayedInSearchPageSortedPriceInAscendingOrder());

	
	}
	
	@Test
	public void VerifySortingOptionPriceInDescendingOrder()
	{
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterIntoSearchCriteriaField(prop.getProperty("ExistingProductTwo"));
		searchPage.clickOnSearchButton();
		searchPage.clickOnListOption();
		Assert.assertTrue(searchPage.getProductCount()>1);
		searchPage.selectSortOptionInDropdownField("Price (High > Low)");
		Assert.assertTrue(searchPage.didProductsGotDisplayedInSearchPageSortedPriceInDescendingOrder());

	
	}

	
}
