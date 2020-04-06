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

import Pages.RegistrationPage;
import Utils.ExcelUtils;

public class RegistrationTest {
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
		locators.load(new FileInputStream("config/user.properties"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
    @Test
	public void registrationTest() throws Exception {
    	this.driver.navigate().to(this.locators.getProperty("registration_url"));
		
		SoftAssert sa = new SoftAssert();

		RegistrationPage registration = new RegistrationPage(driver, locators, waiter);
		
		ExcelUtils excel = new ExcelUtils();
		ExcelUtils.setExcell(this.locators.getProperty("data"));
		ExcelUtils.setWorkSheet(1);

		excel.setUniqueID();

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {

			String userId = ExcelUtils.getDataAt(i, 0);
			String newPassword = ExcelUtils.getDataAt(i, 1);
			String repeatPassword = ExcelUtils.getDataAt(i, 1);
			String firstName = ExcelUtils.getDataAt(i, 2);
			String lastName = ExcelUtils.getDataAt(i, 3);
			String email = ExcelUtils.getDataAt(i, 4);
			String phone = ExcelUtils.getDataAt(i, 5);
			String address1 = ExcelUtils.getDataAt(i, 6);
			String address2 = ExcelUtils.getDataAt(i, 7);
			String city = ExcelUtils.getDataAt(i, 8);
			String state = ExcelUtils.getDataAt(i, 9);
			String zip = ExcelUtils.getDataAt(i, 10);
			String country = ExcelUtils.getDataAt(i, 11);
			
			registration.setLanguageById(registration.getRandomInteger(2, 0));
			registration.setFavoriteById(registration.getRandomInteger(5, 0));
			
			registration.setUser(userId, newPassword, repeatPassword, firstName,
					             lastName, email, phone, address1, address2, 
					             city, state, zip, country);
			
			registration.saveInformations();
			sa.assertTrue(registration.checkRegistration());
			this.driver.navigate().back();
		}    

		sa.assertAll();
		
	}
	
	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
