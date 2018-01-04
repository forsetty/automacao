package scripts;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import pageObjects.BugChangeStatusPage;
import pageObjects.LoginPage;
import pageObjects.MenuPage;
import pageObjects.MyViewPage;
import pageObjects.ViewAllBugPage;
import pageObjects.ViewPage;
import util.Actions;
import util.DataProviderUtils;
import util.TestData;

public class FecharReportIssueTest extends BaseTest{
	private WebDriver driver;
	private LoginPage loginPage;
	private MyViewPage myViewPage;
	private MenuPage menuPage;
	private ViewAllBugPage viewAllBugPage;
	private ViewPage viewPage;
	private BugChangeStatusPage bugChangeStatusPage;

	@Test(dataProviderClass = DataProviderUtils.class, dataProvider = "allDataProvider")
	public void test(TestData massaDados, ITestContext context) {
		this.driver = getDriver();

		loginPage = new LoginPage(driver);
		myViewPage = new MyViewPage(driver);
		menuPage = new MenuPage(driver);
		viewAllBugPage = new ViewAllBugPage(driver);
		viewPage = new ViewPage(driver);
		bugChangeStatusPage = new BugChangeStatusPage(driver);

		loginPage.preencheUsuario(massaDados.get("UserName"));
		loginPage.preencheSenha(massaDados.get("Password"));
		loginPage.acionaBotaoLogin();

		Actions.takeSnapShot(driver, context);

		Assert.assertEquals(myViewPage.getUsuarioLogado(), massaDados.get("UserName"));

		menuPage.acessaViewIssues();
		
		Actions.takeSnapShot(driver, context);
		
		viewAllBugPage.acessaIdIssue(massaDados.get("Id Issue"));
		
		viewPage.selecionaNewStatus(massaDados.get("Change Status To"));
		
		Actions.takeSnapShot(driver, context);
		
		viewPage.acionaBotaoChangeStatusTo();
		
		bugChangeStatusPage.selecionaResolution(massaDados.get("Resolution"));
		
		bugChangeStatusPage.selecionaAssignedTo(massaDados.get("Assigned To"));
		
		bugChangeStatusPage.preencheCampoAddNote(massaDados.get("Add Note"));
		
		Actions.takeSnapShot(driver, context);
		
		bugChangeStatusPage.acionaBotaoCloseIssue();
		
		if (!viewPage.isVisibleStatusDetails(massaDados.get("Change Status To"))) {
			Assert.fail();
		}
		
		Actions.takeSnapShot(driver, context);
		
		menuPage.logout();
		
		Actions.takeSnapShot(driver, context);
	}
}
