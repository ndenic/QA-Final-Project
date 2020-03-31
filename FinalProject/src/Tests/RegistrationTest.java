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
import Utils.ExcelUtils;

public class RegistrationTest {
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
		locators.load(new FileInputStream("config/user.properties"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test(priority = 1)
	public void registrationTest() throws Exception {
		this.navigateToPage("registration_url");
		
		SoftAssert sa = new SoftAssert();

		RegistrationPage registration = new RegistrationPage(driver, locators, waiter);
		
		ExcelUtils excel = new ExcelUtils();
		excel.setExcell(path);
		// Worksheet users
		excel.setWorkSheet(1);

		excel.setUniqueID();

		for (int i = 1; i < excel.getRowNumber(); i++) {

			String userId = excel.getDataAt(i, 0);
			String newPassword = excel.getDataAt(i, 1);
			String repeatPassword = excel.getDataAt(i, 1);
			String firstName = excel.getDataAt(i, 2);
			String lastName = excel.getDataAt(i, 3);
			String email = excel.getDataAt(i, 4);
			String phone = excel.getDataAt(i, 5);
			String address1 = excel.getDataAt(i, 6);
			String address2 = excel.getDataAt(i, 7);
			String city = excel.getDataAt(i, 8);
			String state = excel.getDataAt(i, 9);
			String zip = excel.getDataAt(i, 10);
			String country = excel.getDataAt(i, 11);
			
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
	
	@Test(priority = 2)
	public void registrationWithoutPasswordTest() throws Exception {
		this.navigateToPage("registration_url");
	
		SoftAssert sa = new SoftAssert();
		RegistrationPage registration = new RegistrationPage(driver, locators, waiter);
		
		ExcelUtils excel = new ExcelUtils();
		excel.setExcell(path);
		// Worksheet users_without_password
		excel.setWorkSheet(2);
		
		excel.setUniqueID();

		for (int i = 1; i < excel.getRowNumber(); i++) {

			String userId = excel.getDataAt(i, 0);
			String newPassword = excel.getDataAt(i, 1);
			String repeatPassword = excel.getDataAt(i, 1);
			String firstName = excel.getDataAt(i, 2);
			String lastName = excel.getDataAt(i, 3);
			String email = excel.getDataAt(i, 4);
			String phone = excel.getDataAt(i, 5);
			String address1 = excel.getDataAt(i, 6);
			String address2 = excel.getDataAt(i, 7);
			String city = excel.getDataAt(i, 8);
			String state = excel.getDataAt(i, 9);
			String zip = excel.getDataAt(i, 10);
			String country = excel.getDataAt(i, 11);
			
			registration.setLanguageById(registration.getRandomInteger(2, 0));
			registration.setFavoriteById(registration.getRandomInteger(5, 0));
			
			registration.setUser(userId, newPassword, repeatPassword, firstName,
					             lastName, email, phone, address1, address2, 
					             city, state, zip, country);
			
			registration.saveInformations();
			
			// ako je prikazan save button znaci da je nakon pokusaja prosledjivanje informacija
			// ostao na istoj strani 
		    sa.assertTrue(registration.checkRegistration());
			
			
			this.driver.navigate().back();
		}    

		sa.assertAll();
		
	}
	
	/*@Test(priority = 3)
	public void registrationWithoutFirstNameTest() throws Exception {
        this.navigateToPage("registration_url");
	
		
		RegistrationPage registration = new RegistrationPage(driver, locators, waiter);
		
		
		
	    registration.checkForInteralServerError();
		
		
	}*/
	
	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
