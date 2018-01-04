package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MenuPage {	
	@SuppressWarnings("unused")
	private WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//td[@class = 'menu']/a[contains(text(), 'Report Issue')]")
	private WebElement reportIssue;
	
	@FindBy(how = How.XPATH, using = "//td[@class = 'menu']/a[contains(text(), 'View Issues')]")
	private WebElement viewIssues;
	
	@FindBy(how = How.XPATH, using = "//td[@class = 'menu']/a[contains(text(), 'Logout')]")
	private WebElement logout;	
	
	public MenuPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void acessaReportIssue() {
		reportIssue.click();
	}
	
	public void acessaViewIssues() {
		viewIssues.click();
	}
	
	public void logout() {
		logout.click();
	}
	
}	
