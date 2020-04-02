package Pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.ExcelUtils;

public class SignInPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;
	private final String path = "data/pet-store-data.xlsx";

	public SignInPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	// Getter for Sign In
	public WebElement getSignIn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("signin")));
	}

	public void clickOnSignIn() {
		this.getSignIn().click();
	}

	// Username
	public WebElement getUsername() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("username")));
	}

	public void setUsername(String username) {
		this.getUsername().clear();
		this.getUsername().sendKeys(username);
	}

	// Password
	public WebElement getPassword() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("password")));
	}

	public void setPassword(String password) {
		this.getPassword().clear();
		this.getPassword().sendKeys(password);
	}

	// Login button
	public WebElement getLoginBtn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("login_btn")));
	}

	public void clickOnLoginBtn() {
		this.getLoginBtn().click();
	}

	// Sign out
	public WebElement getSignOut() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("signout")));
	}

	public void clickOnSignOut() {
		this.getSignOut().click();
	}
	
	// Go back to login
	public void goBack() {
		this.clickOnSignOut();
		this.clickOnSignIn();
	}

	// Login failed message
	public WebElement getLoginFailedMessage() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("loginfailed_msg")));
	}

	// Fill login form
	public void setLogin(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);

	}
	// Check if login is successful
		public boolean checkLogin() {
			boolean successful = false;
			try {
				if (this.getSignOut().isDisplayed()) {
					successful = true;
				}
			} catch (Exception e) {
				System.out.println(this.getLoginFailedMessage().getText());		
			}
			return successful;

		}

}
