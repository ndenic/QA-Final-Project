package Pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;
//	private JavascriptExecutor jse;

	public RegistrationPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}
	
	//Getter for Sign In
	public WebElement getSignIn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("signin")));
	}
	
	public void clickOnSignIn() {
		this.getSignIn().click();
	}
	
	//Getter for Registration
	public WebElement getRegistratio() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("register")));
	}
	
	public void clickOnRegisterNow() {
		this.getRegistratio().click();
	}
	
	public void goToRegisterForm() {
		clickOnSignIn();
		clickOnRegisterNow();
	}
	
	
	
    
	
	
	

}
