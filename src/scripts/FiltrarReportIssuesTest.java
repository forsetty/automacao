package scripts;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.MenuPage;
import pageObjects.MyViewPage;
import pageObjects.ViewAllBugPage;
import pageObjects.ViewFiltersPage;
import util.Actions;
import util.DataProviderUtils;
import util.TestData;

public class FiltrarReportIssuesTest extends BaseTest{
	private WebDriver driver;
	private LoginPage loginPage;
	private MyViewPage myViewPage;
	private MenuPage menuPage;
	private ViewAllBugPage viewAllBugPage;
	private ViewFiltersPage viewFiltersPage;

	@Test(dataProviderClass = DataProviderUtils.class, dataProvider = "allDataProvider")
	public void test(TestData massaDados, ITestContext context) {
		this.driver = getDriver();

		loginPage = new LoginPage(driver);
		myViewPage = new MyViewPage(driver);
		menuPage = new MenuPage(driver);
		viewAllBugPage = new ViewAllBugPage(driver);
		viewFiltersPage = new ViewFiltersPage(driver);

		loginPage.preencheUsuario(massaDados.get("UserName"));
		loginPage.preencheSenha(massaDados.get("Password"));
		loginPage.acionaBotaoLogin();

		Actions.takeSnapShot(driver, context);

		Assert.assertEquals(myViewPage.getUsuarioLogado(), massaDados.get("UserName"));

		menuPage.acessaViewIssues();
		
		Actions.takeSnapShot(driver, context);
		
		viewAllBugPage.acessaSimpleFilter();
		
		viewFiltersPage.selecionaStatus(massaDados.get("Status"));
		
		viewFiltersPage.selecionaAssignedTo(massaDados.get("Assigned To"));
		
		viewFiltersPage.selecionaResolution(massaDados.get("Resolution"));
		
		Actions.takeSnapShot(driver, context);
		
		viewFiltersPage.acionaBotaoApplyFilter();
		
		if (!viewAllBugPage.isVisibleListaIssues()) {
			Assert.fail();
		}
		
		Actions.takeSnapShot(driver, context);
		
		menuPage.logout();
		
		Actions.takeSnapShot(driver, context);
		
	}	
}
