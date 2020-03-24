package Pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PetStoreMenuPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;
	private JavascriptExecutor jse;

	public PetStoreMenuPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public List<WebElement> getLeftSideNav() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("left_side_nav")));
	}

	public void clickOnLeftNavItem(String name) {
		List<WebElement> items = this.getLeftSideNav();
		jse = (JavascriptExecutor) driver;
		for (int i = 0; i < items.size(); i++) {
			String item = items.get(i).getAttribute("href").toLowerCase();
			if (item.contains(name)) {
				jse.executeScript("arguments[0].click()", items.get(i));
				break;
			}

		}
	}
	
	
	
	
	

	public int verifyURLStatus(String urlString) {
		int status = 404;
		try {
			URL link = new URL(urlString);
			HttpURLConnection hConn = null;
			hConn = (HttpURLConnection) link.openConnection();
			hConn.setRequestMethod("GET");
			hConn.connect();
			status = hConn.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;

	}

}
