package Pages;

import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;
//	private JavascriptExecutor jse;
	private final String path = "data/pet-store-data.xlsx";

	public RegistrationPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	// Getter for Sign In
	public WebElement getSignIn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("signin")));
	}

	public void clickOnSignIn() {
		this.getSignIn().click();
	}

	// Getter for Registration
	public WebElement getRegistratio() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("register")));
	}

	public void clickOnRegisterNow() {
		this.getRegistratio().click();
	}

	public void goToRegistrationPage() {
		clickOnSignIn();
		clickOnRegisterNow();
	}

	// UserID
	public WebElement getUserId() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("user_id")));
	}

	public void setUserId(String ID) {
		this.getUserId().clear();
		this.getUserId().sendKeys(ID);
	}

	// New password
	public WebElement getNewPassword() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("new_password")));
	}

	public void setNewPassword(String newPassword) {
		this.getNewPassword().clear();
		this.getNewPassword().sendKeys(newPassword);
	}

	// Repeat password
	public WebElement getRepeatPassword() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("repeat_password")));
	}

	public void setRepeatPassword(String repeatPassword) {
		this.getRepeatPassword().clear();
		this.getRepeatPassword().sendKeys(repeatPassword);
	}

	// First name
	public WebElement getFirstName() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("first_name")));
	}

	public void setFirstName(String firstName) {
		this.getFirstName().clear();
		this.getFirstName().sendKeys(firstName);
	}

	// Last name
	public WebElement getLastName() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("last_name")));
	}

	public void setLastName(String lastName) {
		this.getLastName().clear();
		this.getLastName().sendKeys(lastName);
	}

	// Email
	public WebElement getEmail() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("email")));
	}

	public void setEmail(String email) {
		this.getEmail().clear();
		this.getEmail().sendKeys(email);
	}

	// Phone
	public WebElement getPhone() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("phone")));
	}

	public void setPhone(String phone) {
		this.getPhone().clear();
		this.getPhone().sendKeys(phone);
	}

	// Address 1
	public WebElement getAddress1() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("address_1")));
	}

	public void setAddress1(String address1) {
		this.getAddress1().clear();
		this.getAddress1().sendKeys(address1);
	}

	// Addres 2
	public WebElement getAddress2() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("address_2")));
	}

	public void setAddress2(String address2) {
		this.getAddress2().clear();
		this.getAddress2().sendKeys(address2);
	}

	// City
	public WebElement getCity() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("city")));
	}

	public void setCity(String city) {
		this.getCity().clear();
		this.getCity().sendKeys(city);
	}

	// State
	public WebElement getState() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("state")));
	}

	public void setState(String state) {
		this.getState().clear();
		this.getState().sendKeys(state);
	}

	// Zip
	public WebElement getZip() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("zip")));
	}

	public void setZip(String zip) {
		this.getZip().clear();
		this.getZip().sendKeys(zip);
	}

	// Country
	public WebElement getCounty() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("country")));
	}

	public void setCountry(String country) {
		this.getCounty().clear();
		this.getCounty().sendKeys(country);
	}

	// Select for language
	public WebElement getLanguage() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("language")));
	}

	public Select getLanguageSelect() {
		return new Select(this.getLanguage());
	}

	public void setLanguageByValue(String value) {
		this.getLanguageSelect().selectByValue(value);
	}

	public void setLanguageById(int id) {
		this.getLanguageSelect().selectByIndex(id);
	}

	// Select for favorite
	public WebElement getFavorite() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("favorite")));
	}

	public Select getFavoriteSelect() {
		return new Select(this.getFavorite());
	}

	public void setFavoriteByValue(String value) {
		this.getFavoriteSelect().selectByValue(value);
	}

	public void setFavoriteById(int id) {
		this.getFavoriteSelect().selectByIndex(id);
	}

	// My list
	public WebElement getMyListButton() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("my_list")));
	}

	public void clickOnMyListButton() {
		this.getMyListButton().click();
	}

	// My banner
	public WebElement getMyBannerButton() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("my_banner")));
	}

	public void clickOnMyBannerButton() {
		this.getMyBannerButton().click();
	}

	// Save button
	public WebElement getSaveButton() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("save")));
	}

	public void saveInformations() {
		this.getSaveButton().click();
	}

	// Exception message - 505
	public WebElement getExceptionMsg() {
		// this message is only received if all required fields are not filled
		return this.driver.findElement(By.xpath(this.locators.getProperty("exception_msg")));
	}

	// Home page image
	public WebElement getHomePageImg() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("homepage_img")));
	}

	// Registration
	public void fillRegistration() {
		ExcelUtils excel = new ExcelUtils();
		excel.setExcell(path);
		excel.setWorkSheet(1);
		
		for (int i = 1; i <= excel.getRowNumber(); i++) {
			
			this.setUserId(excel.getDataAt(i, 0));
			this.setNewPassword(excel.getDataAt(i, 1));
			this.setRepeatPassword(excel.getDataAt(i, 1));
			this.setFirstName(excel.getDataAt(i, 2));
			this.setLastName(excel.getDataAt(i, 3));
			this.setEmail(excel.getDataAt(i, 4));
			this.setPhone(excel.getDataAt(i, 5));
			this.setAddress1(excel.getDataAt(i, 6));
			this.setAddress2(excel.getDataAt(i, 7));
			this.setCity(excel.getDataAt(i, 8));
			this.setState(excel.getDataAt(i, 9));
			this.setZip(excel.getDataAt(i, 10));
		    this.setCountry(excel.getDataAt(i, 11));
		    this.setLanguageById(getRandomInteger(2, 1));
		    this.setFavoriteById(getRandomInteger(5, 1));
		    
		    this.saveInformations();
		    this.goToRegistrationPage();
		    
		    
		}
	
	}

	// Check if registration is successfully saved
	public boolean checkRegistraion() {
		boolean saved = false;
		try {
			if (this.getHomePageImg().isDisplayed()) {
				saved = true;
			}
		} catch (Exception e) {
			System.out.println("No such element!");
		}
		return saved;
	}

	//Generate random numbers
	private int getRandomInteger(int maximum, int minimum) {
		return ((int) (Math.random() * (maximum - minimum))) + minimum;
	}

}
