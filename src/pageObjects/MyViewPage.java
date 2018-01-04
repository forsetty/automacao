package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MyViewPage {	
	@SuppressWarnings("unused")
	private WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//td[@class = 'login-info-left']/span[@class = 'italic']")
	private WebElement textoUsuarioLogado;
	
	public MyViewPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getUsuarioLogado(){
		return textoUsuarioLogado.getText();
	}
}
