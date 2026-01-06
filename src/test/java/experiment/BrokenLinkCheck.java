package experiment;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLinkCheck {

	public static void main(String[] args) {
		// Broken link means does not have any resource in server
		WebDriver driver = new ChromeDriver();
		// driver.get("https://phptravels.com/demo/");
		driver.get("http://www.zlti.com");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		// capture all links from website
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		System.out.println(allLinks.size());

		int noOfBroenLinks = 0;
		for (WebElement links : allLinks) {
			System.out.println(links.getText() + " ");

			String hrefAttributeValue = links.getDomAttribute("href");
			if (hrefAttributeValue == null || hrefAttributeValue.isEmpty()) {
				System.out.println("href attribute value is null or empty so not possible to ckeck ");
				continue;
			}
			try {

				URL linkURL = new URL(hrefAttributeValue);// convert string(hrefAttributeValue) to URL(linkURL) format
				// open connection to server
				HttpURLConnection conn = (HttpURLConnection) linkURL.openConnection();
				conn.connect();// connect to server and send request to server
				if (conn.getResponseCode() >= 400) {
					System.out.println(hrefAttributeValue + "--------->" + " Broken link");
					noOfBroenLinks++;
				} else {
					System.out.println(hrefAttributeValue + "=========>" + " not Broken Link ");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("number of broken links " + noOfBroenLinks);

	}

}
