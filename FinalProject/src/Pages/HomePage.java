package Pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;
	
	public HomePage(WebDriver driver,Properties locators, WebDriverWait waiter) {
		this.driver=driver;
		this.locators=locators;
		this.waiter=waiter;
	}
	
	public WebElement getEnter() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("enter_url")));
	}
	
	public void enterPage() {
		this.getEnter().click();
	}
	
	public WebElement getMainImage() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("main_image")));
	}
	
	public boolean mainImageDisplayed() {
		boolean displayed = false;
		if(this.getMainImage().isDisplayed()) {
			displayed = true;
		}
		return displayed;
	}
	
	
	
	
	
}
