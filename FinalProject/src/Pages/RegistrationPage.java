package Pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
	
	//Getters
	public WebElement getUserId() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("user_id")));
	}
	public WebElement getNewPassword() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("new_password")));
	}
	public WebElement getRepeatPassword() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("repeat_password")));
	}
	public WebElement getFirstName() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("first_name")));
	}
	public WebElement getLastName() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("last_name")));
	}
	public WebElement getPhone() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("phone")));
	}
	public WebElement getAddress1() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("address_1")));
	}
	public WebElement getAddress2() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("address_2")));
	}
	public WebElement getCity() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("city")));
	}
	public WebElement getState() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("state")));
	}
	public WebElement getZip() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("zip")));
	}
	public WebElement getCounty() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("country")));
	}
	
	//Select for language and favorite
	public WebElement getLanguage() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("language")));
	}
    public Select getLanguageSelect() {
    	return new Select(this.getLanguage());
    }
    public WebElement getFavorite() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("favorite")));
	}
    public Select getFavoriteSelect() {
    	return new Select(this.getFavorite());
    }
    
    
    public void fillFields() {
    	
    }
	
	
	
	
    
	
	
	

}
