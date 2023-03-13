package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtils {

    private static WebDriver driver;
    public static WebDriverWait wait;

    public TestUtils(WebDriver driver, WebDriverWait wait) {

        TestUtils.driver = driver;
        TestUtils.wait = wait;
    }

//    public String ReadCellData(int vRow, int vColumn) {
//		String value = null;
//		Workbook wb = null;
//		try {
//
//			FileInputStream fis = new FileInputStream("C:\\Users\\ppran\\git\\BDDTestProject\\src\\test\\resources\\config\\TestData.xlsx");
//			wb = new XSSFWorkbook(fis);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//	
//		org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0);
//		Row row = sheet.getRow(vRow);
//		org.apache.poi.ss.usermodel.Cell cell = row.getCell(vColumn);
//		value = cell.getStringCellValue();
//		return value;
//	} 
	
    public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}
    
    public void highlightElement(WebElement element) {
    	JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    	jsExecutor. executeScript("arguments[0]. setAttribute('style', 'border:2px solid red;')", element);

    }
    
    public void reduceScreenSize(String size) {
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("document.body.style.zoom = '"+size+"%'");
    }
    
}