package scripts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import util.PropertiesManager;
import util.WebDriverManager;

public class BaseTest {
	public WebDriverManager driverManager = null;
	
	@BeforeMethod
	public void beforeMethod() {
		
		driverManager = new WebDriverManager(PropertiesManager.getBrowser(), PropertiesManager.getUrlAplicacao());
		driverManager.startWebDriver();

	//	String exePath = "..\\automacao-mantis\\resources\\drivers\\chromedriver.exe";
	//	System.setProperty("webdriver.chrome.driver", exePath);
	//	driver = new ChromeDriver();
		
	//	String exePath = "..\\automacao-mantis\\resources\\drivers\\geckodriver.exe";
	//	System.setProperty("webdriver.gecko.driver", exePath);
	//	driver = new FirefoxDriver();
	}

	@AfterMethod
	public void afterMethod() {
		driverManager.stopDriver();

	}
	
	public WebDriver getDriver() {
		return driverManager.getWebDriver();
	}
}
