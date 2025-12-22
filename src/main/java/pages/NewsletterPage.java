package pages;

import org.openqa.selenium.WebDriver;

import pages.root.RootPage;

public class NewsletterPage extends RootPage {
WebDriver driver;
	public NewsletterPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

}
