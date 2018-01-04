package util;

import java.io.File;
import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class DataProviderUtils {
	public static int iTestCaseRow = 1;
	public static final String EXCEL_EXTENSION = ".xlsx";

	@DataProvider
	public static Object[][] dataProvider(ITestContext context, Method method) throws Exception {
		String excelFilePath = getExcelFilePath(context);

		String testName = context.getName();

		ExcelUtils.setExcelFile(excelFilePath, testName);

		Object[][] testObjArray = ExcelUtils.getTestData(excelFilePath, testName, iTestCaseRow);

		return testObjArray;
	}

	@DataProvider
	public static Object[][] allDataProvider(ITestContext context, Method method) throws Exception {
		String excelFilePath = getExcelFilePath(context);

		String testName = context.getName();

		ExcelUtils.setExcelFile(excelFilePath, testName);

		Object[][] testObjArray = ExcelUtils.getAllTestData(excelFilePath, testName);

		return (testObjArray);
	}

	public static String getExcelFilePath(ITestContext context) {
		String testName = context.getName();

		String dirPlanilhas = "..\\automacao-mantis\\resources\\data\\";

		String excelFilePath = dirPlanilhas + testName + DataProviderUtils.EXCEL_EXTENSION;

		File file = new File(excelFilePath);

		if (!file.exists()) {
			String dataFilePath = context.getSuite().getParameter("dataFilePath");

			excelFilePath = dataFilePath + testName + DataProviderUtils.EXCEL_EXTENSION;
		}

		System.out.println("Read Excel File: " + excelFilePath);
		return excelFilePath;
	}
}
