package scripts;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import pageObjects.BugReportPage;
import pageObjects.LoginPage;
import pageObjects.MenuPage;
import pageObjects.MyViewPage;
import util.Actions;
import util.DataProviderUtils;
import util.TestData;

public class CadastrarReportIssueTest extends BaseTest{
	private WebDriver driver;
	private LoginPage loginPage;
	private MyViewPage myViewPage;
	private MenuPage menuPage;
	private BugReportPage bugReportPage;
	
	@Test (dataProviderClass = DataProviderUtils.class, dataProvider = "allDataProvider")
	public void test(TestData massaDados, ITestContext context) {
		this.driver = getDriver();
		
		loginPage = new LoginPage(driver);
		myViewPage = new MyViewPage(driver);
		menuPage = new MenuPage(driver);
		bugReportPage = new BugReportPage(driver);

		loginPage.preencheUsuario(massaDados.get("UserName"));
		loginPage.preencheSenha(massaDados.get("Password"));
		loginPage.acionaBotaoLogin();
		
		Actions.takeSnapShot(driver, context);

		Assert.assertEquals(myViewPage.getUsuarioLogado(), massaDados.get("UserName"));
		
		menuPage.acessaReportIssue();
		
		bugReportPage.selecionaCategory(massaDados.get("Category"));
		
		bugReportPage.selecionaReproducibility(massaDados.get("Reproducibility"));
		
		bugReportPage.selecionaSeverity(massaDados.get("Severity"));
		
		bugReportPage.selecionaPriority(massaDados.get("Priority"));
		
		bugReportPage.selecionaProfile(massaDados.get("Select Profile"));
		
		bugReportPage.preecheCaixaTextoPlatform(massaDados.get("Platform"));
		
		bugReportPage.preecheCaixaTextoOS(massaDados.get("OS"));
		
		bugReportPage.preecheCaixaTextoOSVersion(massaDados.get("OS Version"));
		
		bugReportPage.selecionaAssignTo(massaDados.get("Assign To"));
		
		bugReportPage.preencheCaixaTextoSummary(massaDados.get("Summary"));
		
		bugReportPage.preencheCaixaTextoDescription(massaDados.get("Description"));
		
		bugReportPage.preencheCaixaTextoStepsToReproduce(massaDados.get("Steps To Reproduce"));
		
		bugReportPage.preencheCaixaTextoAdditionalInformation(massaDados.get("Additional Information"));
		
		bugReportPage.selecionaViewStatus(massaDados.get("View Status"));
		
		Actions.takeSnapShot(driver, context);
		
		bugReportPage.acionaBotaoSubmitReport(); 
		
		Actions.takeSnapShot(driver, context);
		
		if (massaDados.get("Resultado").equals("sucesso")) {
			if (!bugReportPage.isVisibleListaIssues()) {
				Assert.fail();
			}
		} else {
			if (!bugReportPage.isVisibleMensagemErro()) {
				Assert.fail();
			}
		}
		
		menuPage.logout();
		
		Actions.takeSnapShot(driver, context);		
	}
}
