package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	@SuppressWarnings("unused")
	private WebDriver driver;
	
	@FindBy(how = How.NAME, using = "username")
	private WebElement caixaTextoUsuario;
	
	@FindBy(how = How.NAME, using = "password")
	private WebElement caixaTextoSenha;
	
	@FindBy(how = How.XPATH, using = "//input[@value = 'Login']")
	private WebElement botaoLogin;	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void preencheUsuario(String value) {
		caixaTextoUsuario.clear();
		caixaTextoUsuario.sendKeys(value);
	}
	
	public void preencheSenha(String value) {
		caixaTextoSenha.clear();
		caixaTextoSenha.sendKeys(value);
	}
	
	public void acionaBotaoLogin() {
		botaoLogin.click();
	}
}
