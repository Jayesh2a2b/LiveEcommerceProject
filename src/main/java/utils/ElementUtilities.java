package utils;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ElementUtilities  
{
WebDriver driver;
Select select;
public ElementUtilities(WebDriver driver)
{
	this.driver=driver;
}
public void clickOnElement(WebElement element)
{
         if(isElementDisplayed(element)&& element.isEnabled())
         {
        	    try {
        	        element.click();
        	    } catch (ElementClickInterceptedException e) {
        	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        	    }

         }
}
public String getTextFromElement(WebElement element)
{
	String elementText="";
	if(isElementDisplayed(element))
	{
		elementText=element.getText();
	}
	return elementText;
}
public boolean isElementDisplayed(WebElement element)
{
	boolean b=false;
	try
	{
		b=element.isDisplayed();
	}
	catch(NoSuchElementException e)
	{
		b=false;
	}
	return b;
}

public String getElementDomAttribute(WebElement element,String attributeName)
{
	return element.getDomAttribute(attributeName);
}
public String getElementDomProperty(WebElement element,String attributeName)
{
	return element.getDomProperty(attributeName);
}
public boolean isElementSelected(WebElement element)
{
	boolean b=false;
	if(isElementDisplayed(element))
	{
		b=element.isSelected();
	}
	return b;
}
public boolean isElementDisplayedOnPage(WebElement element)
{
	boolean b=false;
	b=element.isDisplayed();
	return b;
}
public String getElementCSSValue(WebElement element,String cssPropertyName)
{
	String value="";
	value=element.getCssValue(cssPropertyName);
	return value;
}
public void clearTextFromElement(WebElement element)
{
	if(isElementDisplayedOnPage(element)&& element.isEnabled())
	{
		element.clear();
	}
}
public void enterTextIntoElement(WebElement element,String text)
{
	if(element.isDisplayed()&& element.isEnabled())
	{
		clearTextFromElement(element);
		element.sendKeys(text);
	}
}
public void copyingTextUsingKeyboardKeys(WebDriver driver)
{
	Actions actions=new Actions(driver);
	actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
	.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).build().perform();

}
public void pasteTextIntoFieldUsingKeyboardKeys(WebElement element,WebDriver driver)
{
	Actions actions=new Actions(driver);
	actions.click(element).keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL)
	.build().perform();

}
public int getProductCount(List<WebElement> elements) {
	int count = 0;
	try {
		count = elements.size();
	} catch (NoSuchElementException e) {
		count = 0;
	}
	return count;
}
public void selectOptionfromDropdownFieldByUsingIndex(WebElement element,int optionIndex)
{

	if(isElementDisplayedOnPage(element)&& element.isEnabled())
	{
		select=new Select(element);
		select.selectByIndex(optionIndex);
	}

}
public void selectOptionfromDropdownFieldByUsingVisibleText(WebElement element,String optionText)
{

	if(isElementDisplayedOnPage(element)&& element.isEnabled())
	{
		select=new Select(element);
		select.selectByVisibleText(optionText);
	}

}
public List<String> getTextOfElements(List<WebElement>items)
{
	List<String>itemNames=new ArrayList<>();
	for(WebElement item:items)
	{
		//String itemName=item.getText();
		String itemName=getTextFromElement(item);
		itemNames.add(itemName);
	}
	return itemNames;
}

public  boolean verifyPriceIsAscendingOrder(
        List<Double> actualPrices, List<Double> expectedPrices) {

    return actualPrices.equals(expectedPrices);
}

}
