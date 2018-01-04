package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import util.Actions;

public class BugReportPage {
	private WebDriver driver;
	
	@FindBy(how = How.NAME, using = "category_id")
	private WebElement selectCategoria;
	
	@FindBy(how = How.NAME, using = "reproducibility")
	private WebElement selectReproducibility;
	
	@FindBy(how = How.NAME, using = "severity")
	private WebElement selectSeverity;

	@FindBy(how = How.NAME, using = "priority")
	private WebElement selectPriority;
	
	@FindBy(how = How.NAME, using = "profile_id")
	private WebElement selectProfile;
	
	@FindBy(how = How.ID, using = "platform")
	private WebElement caixaTextoPlatform;
	
	@FindBy(how = How.ID, using = "os")
	private WebElement caixaTextoOS;
	
	@FindBy(how = How.ID, using = "os_build")
	private WebElement caixaTextoOSVersion;
	
	@FindBy(how = How.NAME, using = "handler_id")
	private WebElement selectAssignTo;
	
	@FindBy(how = How.NAME, using = "summary")
	private WebElement caixaTextoSummary;
	
	@FindBy(how = How.NAME, using = "description")
	private WebElement caixaTextoDescription;
	
	@FindBy(how = How.NAME, using = "steps_to_reproduce")
	private WebElement caixaTextoStepsToReproduce;
	
	@FindBy(how = How.NAME, using = "additional_info")
	private WebElement caixaTextoAdditionalInformation;
	
	@FindAll({ @FindBy(how = How.NAME, using = "view_state") })
	private List<WebElement> radioViewStatus;
	
	@FindBy(how = How.XPATH, using = "//input[@value = 'Submit Report']")
	private WebElement botaoSubmitReport;
	
	@FindBy(how = How.XPATH, using = "//td[@class='form-title' and contains(text(), 'APPLICATION ERROR #11')]")
	private WebElement mensagemErro;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(), 'Viewing Issues')]")
	private WebElement textoListaIssues;
	
	@FindBy(how = How.ID, using = "ufile[]")
	private WebElement UploadFile;
	
	public BugReportPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void selecionaCategory(String value) {
		Select select = new Select(selectCategoria);
		select.selectByVisibleText(value);
	}
	
	public void selecionaReproducibility(String value) {
		Select select = new Select(selectReproducibility);
		select.selectByVisibleText(value);
	}
	
	public void selecionaSeverity(String value) {
		Select select = new Select(selectSeverity);
		select.selectByVisibleText(value);
	}
	
	public void selecionaPriority(String value) {
		Select select = new Select(selectPriority);
		select.selectByVisibleText(value);
	}
	
	public void selecionaProfile(String value) {
		Select select = new Select(selectProfile);
		select.selectByVisibleText(value);
	}
	
	public void preecheCaixaTextoPlatform(String value) {
		caixaTextoPlatform.clear();
		caixaTextoPlatform.sendKeys(value);
	}
	
	public void preecheCaixaTextoOS(String value) {
		caixaTextoOS.clear();
		caixaTextoOS.sendKeys(value);
	}
	
	public void preecheCaixaTextoOSVersion(String value) {
		caixaTextoOSVersion.clear();
		caixaTextoOSVersion.sendKeys(value);
	}
	
	public void selecionaAssignTo(String value) {
		Select select = new Select(selectAssignTo);
		select.selectByVisibleText(value);
	}
	
	public void preencheCaixaTextoSummary(String value) {
		caixaTextoSummary.clear();
		if (value != null) {
			caixaTextoSummary.sendKeys(value);
		}		
	}
	
	public void preencheCaixaTextoDescription(String value) {
		caixaTextoDescription.clear();
		if (value != null) {
			caixaTextoDescription.sendKeys(value);
		}
	}
	
	public void preencheCaixaTextoStepsToReproduce(String value) {
		caixaTextoStepsToReproduce.clear();
		caixaTextoStepsToReproduce.sendKeys(value);
	}
	
	public void preencheCaixaTextoAdditionalInformation(String value) {
		caixaTextoAdditionalInformation.clear();
		caixaTextoAdditionalInformation.sendKeys(value);
	}
	
	public void selecionaViewStatus(String value) {		
		if (radioViewStatus.get(0).getText().equals(value)) {
			radioViewStatus.get(0).click();
		}
		 
		if (radioViewStatus.get(1).getText().equals(value)) {
			radioViewStatus.get(1).click();
		}
	}
	
	public void acionaBotaoSubmitReport() {
		botaoSubmitReport.click();
	}	
	
	public boolean isVisibleMensagemErro() {
		return mensagemErro.isDisplayed();
	}
	
	public boolean isVisibleListaIssues() {
		Actions.waitingObject(textoListaIssues, driver);
		return textoListaIssues.isDisplayed();
	}
}
