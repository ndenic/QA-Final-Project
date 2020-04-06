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
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import Pages.HomePage;
import Pages.PetStoreMenuPage;

public class PetStoreMenuTest {
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
		locators.load(new FileInputStream("config/pet_store_menu.properties"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void linksTest() throws Exception {
		this.driver.navigate().to(this.locators.getProperty("petstore_url"));

		SoftAssert sa = new SoftAssert();

		HomePage homePage = new HomePage(driver, locators, waiter);
		homePage.enterPage();

		PetStoreMenuPage petStore = new PetStoreMenuPage(driver, locators, waiter);

		sa.assertTrue(petStore.checkMenuContentLinks());
		sa.assertTrue(petStore.checkQucikMenuLinks());

		sa.assertAll();

	}
	
	@Test(priority = 2)
	public void menuContentTest() throws Exception{
		this.driver.navigate().to(this.locators.getProperty("petstore_url"));
		
		SoftAssert sa = new SoftAssert();
		
		HomePage homePage = new HomePage(driver, locators, waiter);
		homePage.enterPage();
		
		PetStoreMenuPage petStore = new PetStoreMenuPage(driver, locators, waiter);
		
		sa.assertTrue(petStore.checkCart());
		sa.assertTrue(petStore.checkSignIn());
		sa.assertTrue(petStore.checkHelp());
		
		sa.assertAll();

	}


	@Test(priority = 3)
	public void leftSideNavTest() throws Exception {
		this.driver.navigate().to(this.locators.getProperty("petstore_url"));
		
		HomePage homePage = new HomePage(driver, locators, waiter);
		homePage.enterPage();

		String name = "reptiles";
	
		PetStoreMenuPage petStore = new PetStoreMenuPage(driver, locators, waiter);
		
		Assert.assertTrue(petStore.clickOnLeftNavItem(name));

	}

	@AfterClass
	public void afterClass() {
		this.driver.close();
	}

}
