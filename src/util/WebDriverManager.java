package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {
	private WebDriver driver;
	private String browser;
	private String url;
	
	public WebDriverManager(String browser, String url) {
		this.browser = browser;
		this.url = url;
	}
	
	public void startWebDriver() {
		WebDriverManager.setPathDriverExecutable(browser);
		if (browser.equals("FF")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("GC")) {
			driver = new ChromeDriver();
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	public void stopDriver() {
		try {
			if (driver != null) {
				driver.quit();
			}
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
	}
	
	private static void setPathDriverExecutable(String browser) {
		if (browser.equals("FF")) {
			System.setProperty("webdriver.gecko.driver", "..\\automacao-mantis\\resources\\drivers\\geckodriver.exe");
		} else if (browser.equals("GC")) {
			System.setProperty("webdriver.chrome.driver", "..\\automacao-mantis\\resources\\drivers\\chromedriver.exe");
		} else {
			throw new IllegalArgumentException("Webdriver disponível não informado!");
		}
	}
	
	public WebDriver getWebDriver() {
		return driver;
	}

}
