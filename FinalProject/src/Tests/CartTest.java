package Tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import Pages.CartPage;
import Pages.StoreItemPage;
import Utils.ExcelUtils;

public class CartTest {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	@BeforeClass
	@Parameters("browser")
	public void setup(String browser) throws Exception {
		if(browser.equalsIgnoreCase("chrome") || browser.equalsIgnoreCase("google chrome")) {
			System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
			this.driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox") || browser.equalsIgnoreCase("mozzila")) {
			System.setProperty("webdriver.gecko.driver", "driver-lib\\geckodriver.exe");
			this.driver = new FirefoxDriver();
		}
	    else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		this.locators = new Properties();
		locators.load(new FileInputStream("config/cart.properties"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test(priority = 1)
	public void addAllProductsInCartTest() throws Exception {
	
		SoftAssert sa = new SoftAssert();
		CartPage cart = new CartPage(driver, locators, waiter);
		
		ExcelUtils excel = new ExcelUtils();		
		ExcelUtils.setExcell(this.locators.getProperty("data"));
		ExcelUtils.setWorkSheet(0);
		
		
		StoreItemPage storeItem = new StoreItemPage(driver, locators, waiter);
		
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			
			String itemLink = ExcelUtils.getDataAt(i, 1);
			this.driver.navigate().to(itemLink);
			storeItem.clickOnAddToCart();
			
		
		}
		
		cart.openCart();	
		
		for (int i = 1; i < excel.getRowNumber(); i++) {
			
			String itemID = excel.getDataAt(i, 0);
			sa.assertTrue(cart.checkForItems(itemID));
		
		}
		
		sa.assertAll();

	}
	
	@Test(priority = 2)
	public void deleteCartCookieTest() throws Exception {
		SoftAssert sa = new SoftAssert();
		CartPage cart = new CartPage(driver, locators, waiter);
		
		ExcelUtils excel = new ExcelUtils();		
		ExcelUtils.setExcell(this.locators.getProperty("cart_data"));
		ExcelUtils.setWorkSheet(0);
		
		
		StoreItemPage storeItem = new StoreItemPage(driver, locators, waiter);
		
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			
			String itemLink = ExcelUtils.getDataAt(i, 1);
			this.driver.navigate().to(itemLink);
			storeItem.clickOnAddToCart();
			
		
		}
		
		cart.openCart();
		
		this.driver.manage().deleteCookieNamed(this.locators.getProperty("cookie"));
		this.driver.navigate().refresh();
		sa.assertTrue(cart.cookieCheck());
	}
	
	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
