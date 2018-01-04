package scripts;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.MenuPage;
import pageObjects.MyViewPage;
import util.Actions;
import util.DataProviderUtils;
import util.TestData;

public class LoginTest extends BaseTest {
	private WebDriver driver;
	private LoginPage loginPage;
	private MyViewPage myViewPage;
	private MenuPage menuPage;

	@Test (dataProviderClass = DataProviderUtils.class, dataProvider = "allDataProvider")
	public void test(TestData massaDados, ITestContext context) {
		this.driver = getDriver();
		loginPage = new LoginPage(driver);
		myViewPage = new MyViewPage(driver);
		menuPage = new MenuPage(driver);

		loginPage.preencheUsuario(massaDados.get("UserName"));
		loginPage.preencheSenha(massaDados.get("Password"));
		loginPage.acionaBotaoLogin();
		
		Actions.takeSnapShot(driver, context);

		Assert.assertEquals(myViewPage.getUsuarioLogado(), massaDados.get("UserName"));
		
		menuPage.logout();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
