package Pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import Utils.ExcelUtils;

public class CartPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;
	private final String path = "data/pet-store-data.xlsx";
    
	
	public CartPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}
	
	// Item id 
	public List<WebElement> getItemsID() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("cart_items_id")));
	}
	
	
	// Check if product from cart_item sheet is successfully added
	public boolean checkForItems(String ID) {
		boolean added = false;
		
		List<WebElement> itemsID = this.getItemsID();
		
		for (int i = 0; i < itemsID.size(); i++) {
			String item = itemsID.get(i).getText();
			if(item.equalsIgnoreCase(ID)) {
				added = true;
			}
		}
		
		return added;
		
	}
	
	// Get cart 
	public WebElement getCart() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("cart")));
	}
	
	// Open cart
	public void openCart() {
		this.getCart().click();
	}
	
	//Get empty cart
	public WebElement getEmptyCartMessage() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("empty_cart")));
	}
	
	// Check for a cookie
	public boolean cookieCheck() {
		boolean deleted = false;
		
		if(this.getEmptyCartMessage().getText().contains("empty")) {
			deleted = true;
		}
		
		return deleted;
	}
	
	
	
	
	
}
