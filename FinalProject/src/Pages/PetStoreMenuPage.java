package Pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PetStoreMenuPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;
	private JavascriptExecutor jse;

	public PetStoreMenuPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	//Getter for left side navigation
	public List<WebElement> getLeftSideNav() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("left_side_nav")));
	}

	//Click on one of navigation item 
	public boolean clickOnLeftNavItem(String name) {
		List<WebElement> items = this.getLeftSideNav();		
		boolean clicked = false;	
		jse = (JavascriptExecutor) driver;
		for (int i = 0; i < items.size(); i++) {
			String item = items.get(i).getAttribute("href").toLowerCase();
			if (item.contains(name)) {
				jse.executeScript("arguments[0].click()", items.get(i));
				clicked = true;
				break;
			}
		}
		return clicked;
	}
	
	//Getter for menu contnent
    public List<WebElement> getMenuContent(){
    	return this.driver.findElements(By.xpath(this.locators.getProperty("menu_content")));
    }
    
    //Check validation of menu content links
	public boolean checkMenuContentLinks() {
		List<WebElement> links = this.getMenuContent();
		boolean isOkay = false;
		for (int i = 0; i < links.size(); i++) {
			String link = links.get(i).getAttribute("href");
			if(this.verifyURLStatus(link)<400) {
				isOkay = true;
			}
		}
		return isOkay;
	}
	
	//Getter for quick links
	public List<WebElement> getQuickLinks() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("quick_links")));
	}
	
	//Check validation of quick menu links 
    public boolean checkQucikMenuLinks() {
    	List<WebElement> links = this.getQuickLinks();
    	boolean isOkay = false;
    	for (int i = 0; i < links.size(); i++) {
			String link = links.get(i).getAttribute("href");
			if(this.verifyURLStatus(link)<400) {
				isOkay = true;
			}
		}
    	return isOkay;
    }
    
    //Getters for Cart
    public WebElement getCart() {
    	return this.driver.findElement(By.xpath(this.locators.getProperty("cart")));
    }
    public WebElement getCartMessage() {
    	return this.driver.findElement(By.xpath(this.locators.getProperty("shopping_cart")));
    }
    public WebElement getBackFromCart() {
    	return this.driver.findElement(By.xpath(this.locators.getProperty("get_back_cart")));
    }
    //Check cart
    public boolean checkCart() {
    	boolean isDisplayed = false;
    	this.getCart().click();
    	if(this.getCartMessage().isDisplayed()) {
    		isDisplayed = true;
    	}
    	this.getBackFromCart().click();
    	return isDisplayed;	
    }
    
    //Getters for Sign In
    public WebElement getSignIn() {
    	return this.driver.findElement(By.xpath(this.locators.getProperty("sign_in")));
    }
    public WebElement getLoginButton() {
    	return this.driver.findElement(By.xpath(this.locators.getProperty("login_button")));
    }
    public WebElement getJPetStore() {
    	return this.driver.findElement(By.xpath(this.locators.getProperty("jpet_store")));
    }
    //Check Sign in
    public boolean checkSignIn() {
    	boolean isDisplayed = false;
    	this.getSignIn().click();
    	if(this.getLoginButton().isDisplayed()) {
    		isDisplayed = true;   		
    	}
    	this.getJPetStore().click();
    	return isDisplayed;
    	
    }
    
    //Getters for Help link
    public WebElement getHelp() {
    	return this.driver.findElement(By.xpath(this.locators.getProperty("help")));
    }
    public WebElement getHelpHeader() {
    	return this.driver.findElement(By.xpath(this.locators.getProperty("help_header")));
    }
    //Check Help
    public boolean checkHelp() {
    	boolean isDisplayed = false;
    	this.getHelp().click();
    	if(this.getHelpHeader().isDisplayed()) {
    		isDisplayed = true;
    	}
    	this.driver.navigate().back();
    	return isDisplayed;
    }
    
	//Method for checking URL validation
	public int verifyURLStatus(String urlString) {
		int status = 404;
		try {
			URL link = new URL(urlString);
			HttpURLConnection hConn = null;
			hConn = (HttpURLConnection) link.openConnection();
			hConn.setRequestMethod("GET");
			hConn.connect();
			status = hConn.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;

	}

}
