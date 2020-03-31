package Tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.HomePage;
import Pages.PetStoreMenuPage;

public class PetStoreMenuTest {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public void navigateToPage(String locator) {
         this.driver.navigate().to(this.locators.getProperty(locator));
	}

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

	@Test(priority = 1)
	public void linksTest() throws Exception {
		this.navigateToPage("petstore_url");

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
		this.navigateToPage("petstore_url");
		
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
		this.navigateToPage("petstore_url");
		
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
