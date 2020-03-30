package Tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.CartPage;
import Pages.HomePage;
import Pages.StoreItemPage;
import Utils.ExcelUtils;

public class CartTest {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;
	private final String path = "data/pet-store-data.xlsx";

	public void navigateToPage(String locator) {
         this.driver.navigate().to(this.locators.getProperty(locator));
	}

	@BeforeClass
	public void setup() throws FileNotFoundException, IOException {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.locators = new Properties();
		locators.load(new FileInputStream("config/cart.properties"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void addAllProductsInCartTest() throws Exception {
	
		SoftAssert sa = new SoftAssert();
		CartPage cart = new CartPage(driver, locators, waiter);
		
		cart.addToCart();
		
		cart.openCart();
		
		// check if all products are added in cart
		ExcelUtils excel = new ExcelUtils();		
		excel.setExcell(path);
		excel.setWorkSheet(0);
		
		for (int i = 1; i < excel.getRowNumber(); i++) {
			
			String itemID = excel.getDataAt(i, 0);
			sa.assertTrue(cart.checkForItems(itemID));
		
		}
		
		sa.assertAll();
		
		
		
	}
	
	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
