package pages;

import org.openqa.selenium.WebDriver;

import pages.root.RootPage;

public class WishListPage extends RootPage 
{
WebDriver driver;
	public WishListPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
	}

}
