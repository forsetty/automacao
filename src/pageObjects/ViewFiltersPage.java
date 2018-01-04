package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ViewFiltersPage {
	@SuppressWarnings("unused")
	private WebDriver driver;
	
	@FindBy(how = How.NAME, using = "show_status[]")
	private WebElement selectStatus;
	
	@FindBy(how = How.NAME, using = "handler_id[]")
	private WebElement selectAssignedTo;
	
	@FindBy(how = How.NAME, using = "show_resolution[]")
	private WebElement selectResolution;
	
	@FindBy(how = How.NAME, using = "filter")
	private WebElement botaoApplyFilter;	
	
	public ViewFiltersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void selecionaStatus(String value) {
		Select select = new Select(selectStatus);
		select.selectByVisibleText(value);
	}
	
	public void selecionaAssignedTo(String value) {
		Select select = new Select(selectAssignedTo);
		select.selectByVisibleText(value);
	}
	
	public void selecionaResolution(String value) {
		Select select = new Select(selectResolution);
		select.selectByVisibleText(value);
	}
	
	public void acionaBotaoApplyFilter() {
		botaoApplyFilter.click();
	}
	
}
