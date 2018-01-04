package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import util.Actions;

public class ViewAllBugPage {
	private WebDriver driver;
	private WebElement idIssue;
	
	@FindBy(how = How.ID, using = "reporter_id_filter")
	private WebElement linkReportFilter;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(), 'Viewing Issues')]")
	private WebElement textoListaIssues;
	
	public ViewAllBugPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void acessaIdIssue(String value) {
		String xpath = "//table[@id = 'buglist']/tbody/tr/td/a[contains(text(), '"+ value +"')]";
		By locator = By.xpath(xpath);
		
		idIssue = driver.findElement(locator);		
		idIssue.click();
	}
	
	public void acessaSimpleFilter() {
		linkReportFilter.click();
	}
	
	public boolean isVisibleListaIssues() {
		Actions.waitingObject(textoListaIssues, driver);
		return textoListaIssues.isDisplayed();
	}
}
