package experiment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class PrintPriceOfProductsOnDisplayPage {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		driver.findElement(By.id("input-search")).sendKeys("Mac");
		driver.findElement(By.id("button-search")).click();

		driver.findElement(By.id("list-view")).click();
		WebElement sortBy = driver.findElement(By.id("input-sort"));
		Select select = new Select(sortBy);
		select.selectByVisibleText("Price (Low > High)");

		List<WebElement> priceElements = driver
				.findElements(By.xpath("//div[@class='product-thumb']//p[@class='price']"));
        //  ACTUAL list
        List<Double> actualPrices =
                getActualPriceListFromUI(priceElements);

        //  EXPECTED list
        List<Double> expectedPrices =
                getExpectedPriceListAfterSorting(actualPrices);

        //  VERIFY
        System.out.println("Actual Prices   : " + actualPrices);
        System.out.println("Expected Prices : " + expectedPrices);

        System.out.println("Is Price Sorted Ascending ? : "
                + verifyPriceIsAscendingOrder(actualPrices, expectedPrices));

        driver.quit();
    }

    // UI → Double list
    public static List<Double> getActualPriceListFromUI(List<WebElement> priceElements) {

        List<Double> actualPrices = new ArrayList<Double>();

        for (WebElement price : priceElements) {
            String text = price.getText().split("\n")[0];
            text = text.replace("$", "").replace(",", "").trim();
            actualPrices.add(Double.parseDouble(text));
        }
        return actualPrices;
    }

    // Expected sorted list
    public static List<Double> getExpectedPriceListAfterSorting(List<Double> actualPrices) {

        List<Double> expectedPrices = new ArrayList<Double>(actualPrices);
        Collections.sort(expectedPrices); // Ascending
        return expectedPrices;
    }

    // Verification
    public static boolean verifyPriceIsAscendingOrder(
            List<Double> actualPrices, List<Double> expectedPrices) {

        return actualPrices.equals(expectedPrices);
    }
}