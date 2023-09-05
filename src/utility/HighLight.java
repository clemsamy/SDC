package utility;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

public class HighLight 
{
	public static void highLightElement(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver; 
		js.executeScript("arguments[0].setAttribute('style', 'background: red; border: 2px solid red;');", element);
	 
		try 
		{
			Thread.sleep(500);
		} 
		catch (InterruptedException e) 
		{
		 	System.out.println(e.getMessage());
		} 
		 
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element); 
		 
	}
	//public WebElement fluentWait(final By locator, WebDriver driver) 
	@SuppressWarnings("deprecation")
	public WebElement fluentWait(final By xpath, WebDriver driver) 
	{
	    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	            .withTimeout(30, TimeUnit.SECONDS)
	            .pollingEvery(5, TimeUnit.SECONDS)
	            .ignoring(NoSuchElementException.class);

	    WebElement foo = wait.until(new Function<WebDriver, WebElement>() 
	    {
	        public WebElement apply(WebDriver driver) 
	        {
	            return driver.findElement(xpath);
	            //return driver.
	        }
	    });

	    return  foo;
	}
	
	// Calling fluentwait ---> WebElement textbox = fluentWait(By.id("textbox"));
	
}
