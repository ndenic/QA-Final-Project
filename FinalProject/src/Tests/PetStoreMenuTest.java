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

import Pages.HomePage;
import Pages.PetStoreMenuPage;

public class PetStoreMenuTest {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	@BeforeClass
	public void setup() throws FileNotFoundException, IOException {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.locators = new Properties();
		locators.load(new FileInputStream("config/pet_store_menu.properties"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void leftSideNavTest() throws Exception{
		this.driver.navigate().to(this.locators.getProperty("petstore_url"));
		
		HomePage homePage = new HomePage(driver, locators, waiter);
		homePage.enterPage();
		
		String name = "reptiles";
		
		PetStoreMenuPage petstore = new PetStoreMenuPage(driver, locators, waiter);
		petstore.clickOnLeftNavItem(name);
		
		Thread.sleep(3000);
	}
	

	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
