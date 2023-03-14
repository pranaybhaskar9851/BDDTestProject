package StepDefinitions;

import io.cucumber.java.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;

import Utilities.PropertiesReader;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Hooks {

	public static WebDriver driver;

	@Before
	public void openBrowser() throws Exception {
		

		WebDriverManager.chromedriver().setup();
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/driver/chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("--headless");
		options.addArguments( "--window-size=1440,768", "--disable-gpu");
		driver = new ChromeDriver(options);
		PropertiesReader propertiesReader = new PropertiesReader();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(propertiesReader.getTimeout()));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(PropertiesReader.getValue("url"));
		Thread.sleep(5000);
		System.out.println("url :"+PropertiesReader.getValue("url"));
	}
	
	//@After
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		//driver.quit();
	}

}
