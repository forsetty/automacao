package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	@SuppressWarnings("unused")
	private static XSSFRow Row;

	public static void setExcelFile(String Path, String SheetName) throws Exception {

		try {

			FileInputStream ExcelFile = new FileInputStream(Path);

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

		} catch (Exception e) {

			throw (e);

		}

	}

	public static Object[][] getTableArray(String FilePath, String SheetName, int iTestCaseRow) throws Exception

	{

		String[][] tabArray = null;

		try {

			FileInputStream ExcelFile = new FileInputStream(FilePath);

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheetAt(0);

			int startCol = 0;

			int ci = 0, cj = 0;

			int totalRows = 1;

			int totalCols = ExcelWSheet.getRow(0).getLastCellNum();

			tabArray = new String[totalRows][totalCols];

			String initialColumn = getCellData(iTestCaseRow, startCol);

			if (initialColumn == null || initialColumn.equals("")) {
				throw new IllegalArgumentException("Excel: A linha " + iTestCaseRow + " possui valores inválidos");
			}

			for (int j = startCol; j < totalCols; j++, cj++)

			{

				tabArray[ci][cj] = getCellData(iTestCaseRow, j);

			}

		}

		catch (FileNotFoundException e)

		{

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		catch (IOException e)

		{

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		return (tabArray);

	}

	public static Object[][] getTestData(String FilePath, String SheetName, int iTestCaseRow) throws Exception {
		Object cabcalho[][] = getTableArray(FilePath, SheetName, 0);
		Object dados[][] = getTableArray(FilePath, SheetName, iTestCaseRow);

		String merge[][] = new String[2][cabcalho[0].length];
		for (int i = 0; i < cabcalho[0].length; i++) {
			merge[0][i] = (String) cabcalho[0][i];
			merge[1][i] = (String) dados[0][i];
		}
		TestDataExcel testData = new TestDataExcel(merge);
		Object testDataArray[][] = { { testData } };
		return testDataArray;
	}

	public static Object[][] getAllTestData(String FilePath, String SheetName) throws Exception {
		Object cabecalho[][] = getTableArray(FilePath, SheetName, 0);

		int startRow = 1;
		int totalRows = ExcelWSheet.getLastRowNum();

		Object testDataArray[][] = new Object[totalRows][1];

		int j = 0;
		for (int i = startRow; i <= totalRows; i++) {
			try {
				Object dados[][] = getTableArray(FilePath, SheetName, i);
				String merge[][] = mergeArrays(cabecalho, dados);
				TestDataExcel testData = new TestDataExcel(merge);
				testDataArray[j++][0] = testData;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		}

		Object testDataArrayCopy[][] = new Object[j][1];

		System.arraycopy(testDataArray, 0, testDataArrayCopy, 0, j);

		return testDataArrayCopy;
	}

	private static String[][] mergeArrays(Object array1[][], Object array2[][]) {
		String merge[][] = new String[2][array1[0].length];
		for (int i = 0; i < array1[0].length; i++) {
			merge[0][i] = (String) array1[0][i];
			merge[1][i] = (String) array2[0][i];
		}
		return merge;
	}

	public static String getCellData(int RowNum, int ColNum) throws Exception {

		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			String CellData = "";

			switch (Cell.getCellType()) {
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC:
				Double number = new Double(Cell.getNumericCellValue());
				if (number.longValue() % 1 == 0)
					CellData = String.valueOf(number.longValue());
				else
					CellData = number.toString();
				break;
			default: {
				CellData = Cell.getStringCellValue();
				if (CellData.isEmpty())
					CellData = null;
			}
			}

			return CellData;

		} catch (Exception e) {

			return "";

		}

	}

	public static String getTestCaseName(String sTestCase) throws Exception {

		String value = sTestCase;

		try {

			int posi = value.indexOf("@");

			value = value.substring(0, posi);

			posi = value.lastIndexOf(".");

			value = value.substring(posi + 1);

			return value;

		} catch (Exception e) {

			throw (e);

		}

	}

	public static int getRowContains(String sTestCaseName, int colNum) throws Exception {

		int i;

		try {

			int rowCount = ExcelUtils.getRowUsed();

			for (i = 0; i < rowCount; i++) {

				if (ExcelUtils.getCellData(i, colNum).equalsIgnoreCase(sTestCaseName)) {

					break;

				}

			}

			return i;

		} catch (Exception e) {

			throw (e);

		}

	}

	public static int getRowUsed() throws Exception {

		try {

			int RowCount = ExcelWSheet.getLastRowNum();

			return RowCount;

		} catch (Exception e) {

			System.out.println(e.getMessage());

			throw (e);

		}

	}

}