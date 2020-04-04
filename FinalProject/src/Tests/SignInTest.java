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

import Pages.SignInPage;
import Utils.ExcelUtils;

public class SignInTest {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;
	private final String path = "data/pet-store-data.xlsx";


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
	public void loginTest() throws Exception {
		this.driver.navigate().to(this.locators.getProperty("login_url"));

		SoftAssert sa = new SoftAssert();

		SignInPage signIn = new SignInPage(driver, locators, waiter);

		ExcelUtils excel = new ExcelUtils();
		ExcelUtils.setExcell(path);
		ExcelUtils.setWorkSheet(1);

		for (int i = 1; i < excel.getRowNumber(); i++) {

			String username = excel.getDataAt(i, 0);
			String password = excel.getDataAt(i, 1);

			signIn.setLogin(username, password);
			signIn.clickOnLoginBtn();
			sa.assertTrue(signIn.checkLogin());
			signIn.goBack();

		}

		sa.assertAll();

	}

	@AfterClass
	public void afterClass() {
		this.driver.close();
	}

}
