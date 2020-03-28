package Pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StoreItemPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public StoreItemPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}
	
	// Quick links
	public WebElement getAddToCart(){
		return this.driver.findElement(By.xpath(this.locators.getProperty("add_to_cart")));
	}
	
	// Click on button
	public void clickOnAddToCart() {
		this.getAddToCart().click();
	}
	
	
	
}
