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