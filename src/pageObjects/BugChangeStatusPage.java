package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BugChangeStatusPage {
	@SuppressWarnings("unused")
	private WebDriver driver;
	
	@FindBy(how = How.NAME, using = "resolution")
	private WebElement selectResolution;
	
	@FindBy(how = How.NAME, using = "handler_id")
	private WebElement selectAssignedTo;	
	
	@FindBy(how = How.NAME, using = "bugnote_text")
	private WebElement campoAddNote;
	
	@FindBy(how = How.XPATH, using = "//input[@value = 'Close Issue']")
	private WebElement botaoCloseIssue;	
	
	public BugChangeStatusPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void selecionaResolution(String value) {
		Select select = new Select(selectResolution);
		select.selectByVisibleText(value);
	}
	
	public void selecionaAssignedTo(String value) {
		Select select = new Select(selectAssignedTo);
		select.selectByVisibleText(value);
	}
	
	public void preencheCampoAddNote(String value) {
		campoAddNote.clear();
		campoAddNote.sendKeys(value);
	}
	
	public void acionaBotaoCloseIssue() {
		botaoCloseIssue.click();
	}
}
