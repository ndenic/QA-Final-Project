package Tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import Pages.RegistrationPage;

public class RegistrationTest {
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
		locators.load(new FileInputStream("config/user.properties"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test(priority = 1)
	public void registrationTest() throws Exception {
		this.navigateToPage("petstore_url");
		
		SoftAssert sa = new SoftAssert();
		
		HomePage homePage = new HomePage(driver, locators, waiter);
		homePage.enterPage();
		
		RegistrationPage registration = new RegistrationPage(driver, locators, waiter);
		
		registration.goToRegistrationPage();
		registration.fillRegistration();      

		sa.assertAll();
		
	}
	
	@Test(priority = 2)
	public void registrationWithoutPasswordTest() throws Exception {
		this.navigateToPage("petstore_url");
		
		HomePage homePage = new HomePage(driver, locators, waiter);
		homePage.enterPage();
		
		RegistrationPage registration = new RegistrationPage(driver, locators, waiter);
		registration.goToRegistrationPage();
		registration.fillRegistrationWithoutPassword();
		
		
	    Assert.assertTrue(registration.checkRegistrationFailed());
		
	}
	
	@Test(priority = 3)
	public void registrationWithoutFirstNameTest() throws Exception {
        this.navigateToPage("petstore_url");
		
		HomePage homePage = new HomePage(driver, locators, waiter);
		homePage.enterPage();
		
		RegistrationPage registration = new RegistrationPage(driver, locators, waiter);
		
		registration.goToRegistrationPage();
		registration.fillRegistrationWithoutFirstName();
		
	    Assert.assertTrue(registration.checkForInteralServerError());
		
		
	}
	
	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
