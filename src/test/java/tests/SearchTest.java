package tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.FooterOptions;
import pages.HeaderOptions;
import pages.LoginPage;
import pages.ProductComparisonPage;
import pages.ProductDisplayPage;
import pages.SearchPage;
import utils.CommonUtilities;

public class SearchTest extends Base {
	public WebDriver driver;

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
		expected = "You must login or create an account to save " + prop.getProperty("ExistingProductThree")
				+ " to your wish list!";
		Assert.assertTrue(searchPage.getPageLevelSuccessMsg().contains(expected));
		// Assert.assertTrue(searchPage.isWishlistStatusAvailableOnPage());
		refreshPage(driver);
		searchPage.clickOnCompareThisProductOption();
		expected = "Success: You have added " + prop.getProperty("ExistingProductThree")
				+ " to your product comparison!";
		Assert.assertTrue(searchPage.getPageLevelSuccessMsg().contains(expected));
		Assert.assertTrue(searchPage.isProductComparisonStatusAvailableOnPage());
		productDisplayPage = searchPage.clickOnProductOneImage();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(driver);
		productDisplayPage = searchPage.clickOnProductName();
		Assert.assertTrue(productDisplayPage.isAddToCartOptionAvailableOnPage());
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(driver);
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterIntoSearchCriteriaField(prop.getProperty("ExistingProductThree"));
		searchPage.clickOnSearchButton();
		searchPage.clickOnGridOption();
		Assert.assertTrue(searchPage.getProductCount() == 1);
		searchPage.clickOnAddToCartOption();
		expected = "Success: You have added " + prop.getProperty("ExistingProductThree") + " to your shopping cart!";
		// Verification checkPoint 1
		Assert.assertTrue(searchPage.getPageLevelSuccessMsg().contains(expected));
		// Verification checkpoint 1 alternate
		Assert.assertTrue(searchPage.isAddToCartSuccessStatusAvailableOnPage());
		refreshPage(driver);
		searchPage.clickOnAddToWishListOption();
		expected = "You must login or create an account to save " + prop.getProperty("ExistingProductThree")
				+ " to your wish list!";
		Assert.assertTrue(searchPage.getPageLevelSuccessMsg().contains(expected));
		// Assert.assertTrue(searchPage.isWishlistStatusAvailableOnPage()); //
		// Verification checkpoint alternate
		refreshPage(driver);
		searchPage.clickOnCompareThisProductOption();
		expected = "Success: You have added " + prop.getProperty("ExistingProductThree")
				+ " to your product comparison!";
		Assert.assertTrue(searchPage.getPageLevelSuccessMsg().contains(expected));
		Assert.assertTrue(searchPage.isProductComparisonStatusAvailableOnPage());
		productDisplayPage = searchPage.clickOnProductOneImage();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(driver);
		productDisplayPage = searchPage.clickOnProductName();
		Assert.assertTrue(productDisplayPage.isAddToCartOptionAvailableOnPage());
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
	}

	@Test(priority = 12)
	public void VerifyListAndGridViewsWhenMultipleProductsAreDisplayedInTheSearchResults() {
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterIntoSearchCriteriaField(prop.getProperty("ExistingProductTwo"));
		searchPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		Assert.assertTrue(searchPage.getProductCount() > 1);
		searchPage.clickOnListOption();
		Assert.assertTrue(searchPage.getProductCount() > 1);
		searchPage.clickOnGridOption();
		;
		Assert.assertTrue(searchPage.getProductCount() > 1);
	}

	@Test(priority = 13)
	public void VerifyNavigatingToProductComparePageFromSearchResultsPage() {
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterIntoSearchCriteriaField(prop.getProperty("ExistingProductThree"));
		searchPage.clickOnSearchButton();
		productComparisonPage = searchPage.clickOnProductCompareLink();
		Assert.assertTrue(productComparisonPage.didWeNavigateToProductComparisonPage());

	}

	@Test(priority = 14)
	public void VerifySortingOptionInSearchResultPageByDefaultOptoion() {
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterIntoSearchCriteriaField(prop.getProperty("ExistingProductTwo"));
		searchPage.clickOnSearchButton();
		searchPage.clickOnListOption();
		Assert.assertTrue(searchPage.getProductCount() > 1);
		searchPage.selectSortOptionInDropdownField("Default");
		Assert.assertTrue(searchPage.didProductsGotDisplayedInAscendingOrder());
	}

	@Test(priority = 15)
	public void VerifySortingOptionInSearchResultPageByDesendingName() {
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterIntoSearchCriteriaField(prop.getProperty("ExistingProductTwo"));
		searchPage.clickOnSearchButton();
		searchPage.clickOnListOption();
		Assert.assertTrue(searchPage.getProductCount() > 1);
		searchPage.selectSortOptionInDropdownField("Name (Z - A)");
		Assert.assertTrue(searchPage.didProductsGotDisplayedInSearchPageInDesendingOrder());
	}

	@Test(priority = 16)
	public void VerifySortingOptionPriceInAscendingOrder() {
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterIntoSearchCriteriaField(prop.getProperty("ExistingProductTwo"));
		searchPage.clickOnSearchButton();
		searchPage.clickOnListOption();
		Assert.assertTrue(searchPage.getProductCount() > 1);
		searchPage.selectSortOptionInDropdownField("Price (Low > High)");
		Assert.assertTrue(searchPage.didProductsGotDisplayedInSearchPageSortedPriceInAscendingOrder());

	}

	@Test(priority = 17)
	public void VerifySortingOptionPriceInDescendingOrder() {
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterIntoSearchCriteriaField(prop.getProperty("ExistingProductTwo"));
		searchPage.clickOnSearchButton();
		searchPage.clickOnListOption();
		Assert.assertTrue(searchPage.getProductCount() > 1);
		searchPage.selectSortOptionInDropdownField("Price (High > Low)");
		Assert.assertTrue(searchPage.didProductsGotDisplayedInSearchPageSortedPriceInDescendingOrder());

	}

	@Test
	public void VerifySearchTextboxFieldAndTheButtonHavingSearchIconAreDisplayedOnAllThePageOfTheApplication() {
		headerOptions = new HeaderOptions(driver);
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		navigateToPage(getBaseURL()+prop.getProperty("RegisterPageURL"));
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		navigateToPage(getBaseURL()+prop.getProperty("LoginPage"));
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		navigateToPage(getBaseURL()+prop.getProperty("ShoppingCartPage"));
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		navigateToPage(getBaseURL()+prop.getProperty("ContactUsPage"));
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		navigateToPage(getBaseURL()+prop.getProperty("WishListPage"));
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		navigateToPage(getBaseURL()+prop.getProperty("LogoutPage"));
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		navigateToPage(getBaseURL()+prop.getProperty("LoginPage"));
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());

		loginPage = new LoginPage(driver);
		myAccountPage = loginPage.loginIntoApplication(prop.getProperty("ValidEmail"), prop.getProperty("PWD"));
		Assert.assertTrue(myAccountPage.didWeNavigateToMYAccountPage());
		myAccountPage.clickOnEditYourAccountInformation();
		navigateToPage(getBaseURL()+prop.getProperty("MyAccountInformationPage"));
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		navigateBackInBrowser(driver);
		myAccountPage.clickOnChangeYourPasswordOption();
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		navigateBackInBrowser(driver);
		addressBookPage = myAccountPage.clickOnModifyYourAddressBookEntries();
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		addAddressPage = addressBookPage.clickOnNewAddressButton();
		addAddressPage.addAddress(prop.getProperty("FirstName"), prop.getProperty("LastName"),
				prop.getProperty("Address"), prop.getProperty("City"), prop.getProperty("PostCode"),
				prop.getProperty("CountryName"), prop.getProperty("StateName"));
		Assert.assertTrue(addressBookPage.isPageLevelSuccessMsgAvailableOnPage());
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		editAddressPage = addressBookPage.clickOnEditOption();
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		editAddressPage.clickOnContinueButton();
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		headerOptions.clickOnMyAccountOptionInDropdownMenu();
		Assert.assertTrue(myAccountPage.didWeNavigateToMYAccountPage());
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		myWishListPage = myAccountPage.clickOnModifyYourWishListOption();

		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		Assert.assertTrue(myWishListPage.didWeNavigateToMyWishListPage());
		myAccountPage = myWishListPage.clickOnContinueButton();
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		orderHistoryPage = myAccountPage.clickOnViewYourOrderHistoryOption();
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		orderInformationPage = orderHistoryPage.clickOnViewIcon();
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		orderHistoryPage = orderInformationPage.clickOnContinueButton();
		myAccountPage = headerOptions.clickOnMyAccountOptionInDropdownMenu();
		downloadsPage = myAccountPage.clickOnDownloadsOption();
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		myAccountPage = downloadsPage.clickOnContinueButton();
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		yourRewardPointPage = myAccountPage.clickOnYourRewardPointOption();
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		myAccountPage = yourRewardPointPage.clickOnContinueButton();
		productReturnsPage = myAccountPage.clickOnViewYourReturnRequestsOption();
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		returnInformationPage = productReturnsPage.clickOnViewOption();
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		productReturnsPage=returnInformationPage.clickOnContinueButton();
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		myAccountPage=productReturnsPage.clickOnContinueButton();
		yourTransactionsPage = myAccountPage.clickOnYourTransactions();
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		myAccountPage=yourTransactionsPage.clickOnContinueButton();
		recurringPaymentsPage=myAccountPage.clickOnRecurringPaymentsOption();
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
		myAccountPage=recurringPaymentsPage.clickOnContinueButton();
		yourAffiliateInformationPage = myAccountPage.clickOnRegisterForAnAffiliateAccountOption();
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());
//		yourAffiliateInformationPage.enterChequePayeeNameInChequePayeeNameField(prop.getProperty("FirstName"));
//		yourAffiliateInformationPage.selectAgreeOption();
//		yourAffiliateInformationPage.clickOnContinueButton();
		newsletterSubscriptionPage=myAccountPage.clickOnSubscibeAndUnsubscribeToNewsletterLinkOnMyaccountpage();
		Assert.assertTrue(headerOptions.isSearchButtonAndSearchFieldAvilableOnPage());

		newsletterSubscriptionPage.IsNewsletterInBreadcrumbAvailableOnNewsletterSubscriptionPage();
	}

	@Test
	public void VerifyWeCanUseAllTheOptionsOfSearchFunctionalityUsingTheKeybaordKeys() {

		actions = getActions(driver);
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.TAB, 9);
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.ENTER, 1);

		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.TAB, 21);
		typeTextUsingActions(actions, prop.getProperty("ExistingProductTwo"));
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.TAB, 1);
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.ARROW_DOWN, 1);
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.TAB, 1);
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.SPACE, 1);
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.TAB, 2);
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.ENTER, 1);
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.TAB, 26);
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.ENTER, 1);
		searchPage = new SearchPage(driver);
		Assert.assertTrue(searchPage.isExistingProductTwoIMacAvailableOnPage());
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.TAB, 1);
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.ENTER, 1);
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.TAB, 1);
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.ENTER, 1);
		productComparisonPage = new ProductComparisonPage(driver);
		Assert.assertTrue(productComparisonPage.didWeNavigateToProductComparisonPage());
		navigateBackInBrowser(driver);
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.TAB, 1);
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.ARROW_DOWN, 1);
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.TAB, 30);
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.ARROW_DOWN, 2);
		// actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.ENTER, 1);
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.TAB, 31);
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.ENTER, 1);
		productDisplayPage = new ProductDisplayPage(driver);
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(driver);
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.TAB, 2);
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.ENTER, 1);
		Assert.assertTrue(searchPage.isAddToCartSuccessStatusAvailableOnPage());
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.TAB, 1);
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.ENTER, 1);
		Assert.assertTrue(searchPage.isWishlistStatusAvailableOnPage());
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.TAB, 1);
		actions = clickKeyboardkeyMultipleTimes(actions, driver, Keys.ENTER, 1);
		Assert.assertTrue(searchPage.isProductComparisonStatusAvailableOnPage());

	}

	@Test
	public void VerifyNavigatingToSearchPageFromTheSiteMapPage() {
		footerOptions = new FooterOptions(driver);
		siteMapPage = footerOptions.clickOnSiteMap();
		Assert.assertTrue(siteMapPage.didWeNavigateToSiteMapPage());
		searchPage = siteMapPage.clickOnSearchOptionInSiteMapPage();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());

	}
	@Test
	public void VerifySearchProductToCheckoutProduct()
	{
		loginPage=headerOptions.navigateToLoginPage();
		loginPage.loginIntoApplication(prop.getProperty("ValidEmail"), prop.getProperty("PWD"));
		headerOptions.enterSearchProductIntoSearchField(prop.getProperty("ExistingProduct"));
		searchPage=headerOptions.clickOnSearchButton();
		productDisplayPage=searchPage.clickOnProductOneImage();
		productDisplayPage.clickOnAddToCartButton();
		checkoutPage=headerOptions.selectOnCheckoutHeaderOption();
		Assert.assertTrue(checkoutPage.didWeNavigateToCheckoutPage());
		checkoutPage.clickOnBillingDetaisContinueButton();
		checkoutPage.clickOnDeliveryDetailsContinueButton();
		checkoutPage.clickOnDeliveryMethodContinueButton();
		checkoutPage.clickOnAgreeOption();
		checkoutPage.clickOnPaymentMethodContinueButton();
		orderSuccessPage = checkoutPage.clickOnConfirmOrderButton();
		Assert.assertTrue(orderSuccessPage.didWeNavigateToSuccessPage());
	}
}
