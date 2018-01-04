package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ViewPage {
	private WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//input[@value = 'Assign To:']")
	private WebElement botaoAssignTo;
	
	@FindBy(how = How.NAME, using = "handler_id")
	private WebElement selectAssignTo;
	
	@FindBy(how = How.NAME, using = "new_status")
	private WebElement selectNewStatus;
	
	@FindBy(how = How.XPATH, using = "//input[@value = 'Change Status To:']")
	private WebElement botaoChangeStatusTo;	
	
	private WebElement campoAssignToDetails;
	
	private WebElement campoStatusDetails;
	
	public ViewPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void acionaBotaoAssignTo() {
		botaoAssignTo.click();
	}
	
	public void selecionaAssignTo(String value) {
		Select select = new Select(selectAssignTo);
		select.selectByVisibleText(value);
	}
	
	public boolean isVisibleAssignToDetails(String value) {
		String xpath = "//td[@class = 'category']/following-sibling::td[contains(text(), '"+ value +"')]";
		By locator = By.xpath(xpath);
				
		campoAssignToDetails = driver.findElement(locator);
		
		return campoAssignToDetails.isDisplayed();		
	}
	
	public boolean isVisibleStatusDetails(String value) {
		String xpath = "//td[@class = 'category']/following-sibling::td[contains(text(), '"+ value +"')]";
		By locator = By.xpath(xpath);
				
		campoStatusDetails = driver.findElement(locator);
		
		return campoStatusDetails.isDisplayed();		
	}
	
	public void selecionaNewStatus(String value) {
		Select select = new Select(selectNewStatus);
		select.selectByVisibleText(value);
	}

	public void acionaBotaoChangeStatusTo() {
		botaoChangeStatusTo.click();
	}
}
