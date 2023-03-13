package PageObjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.Table.Cell;

import Utilities.BaseClass;
import Utilities.TestUtils;

public class ClothingItemsPage extends BaseClass {

	WebDriver driver;
	TestUtils utils ;

	public ClothingItemsPage(WebDriver driver, WebDriverWait wait) {

		super(driver, wait);
		PageFactory.initElements(driver, this);
		this.driver = driver;
		utils = new TestUtils(driver, wait);

	}

	final String quantityInputXpath = "//input[@name='qty']";
	@FindBy(xpath = quantityInputXpath)
	@CacheLookup
	private WebElement quantityInput;

	final String addToCartButtonXpath = "//button[@title='Add to Cart']";
	@FindBy(xpath = addToCartButtonXpath)
	@CacheLookup
	private WebElement addToCartButton;

	final String sortBySelectXpath = "(//select[@id='sorter'])[1]";
	@FindBy(xpath = sortBySelectXpath)
	@CacheLookup
	private WebElement sortBySelect;
	
	
	String itemLinkXpath = "//a[contains(@class,'product-item-link') and contains(text(),'%s')]";
	String itemSizeXpath = "//div[contains(@id,'option-label-size-') and text()='%s']";
	String itemColorXpath = "//div[contains(@id,'option-label-color-') and @option-label='%s']";
	String successMsgXpath = "//div[contains(text(),'You added %s')]//a[text()='shopping cart']";
	String successMsgXpath1 = "//div[contains(text(),'You added %s')]";
	
	public void clickOnItemLink(String item) {
		WebElement itemLink = driver.findElement(By.xpath(String.format(itemLinkXpath, item)));
		WaitUntilElementVisible(itemLink);
		itemLink.isEnabled();
		utils.highlightElement(itemLink);
		itemLink.click();
		
	}

	public void enterItemSize(String size) {
		WebElement itemLink = driver.findElement(By.xpath(String.format(itemSizeXpath, size)));
		WaitUntilElementVisible(itemLink);
		itemLink.isEnabled();
		itemLink.click();
	}

	public void enterItemColor(String color) {
		WebElement itemColor = driver.findElement(By.xpath(String.format(itemColorXpath, color)));
		WaitUntilElementVisible(itemColor);
		itemColor.isEnabled();
		itemColor.click();
	}

	public void enterQuantity(String qunatity) {
		try {
			WaitUntilElementRefreshed(quantityInputXpath);
			WaitUntilElementVisible(quantityInput);
			quantityInput.isEnabled();
			quantityInput.clear();
			utils.highlightElement(quantityInput);
			quantityInput.sendKeys(qunatity);
		} catch (Exception e) {
			WebElement ele = reInitiateWebElementForStateElement(addToCartButtonXpath);
			WaitUntilElementVisible(ele);
			ele.isEnabled();
			utils.highlightElement(ele);
			ele.sendKeys(qunatity);
		}

	}

	public void clickOnaddToCartButton() {

		try {
			WaitUntilElementRefreshed(addToCartButtonXpath);
			WaitUntilElementVisible(addToCartButton);
			addToCartButton.isEnabled();
			utils.highlightElement(addToCartButton);
			addToCartButton.click();
		} catch (Exception e) {
			WebElement ele = reInitiateWebElementForStateElement(addToCartButtonXpath);
			WaitUntilElementVisible(ele);
			ele.isEnabled();
			utils.highlightElement(ele);
			ele.click();
		}

	}

	public boolean validateCartAddition(String product) {
		WebElement itemLink = driver.findElement(By.xpath(String.format(successMsgXpath, product)));
		WaitUntilElementVisible(itemLink);
		itemLink.isEnabled();
		itemLink.isDisplayed();
		utils.highlightElement(driver.findElement(By.xpath(String.format(successMsgXpath1, product))));
		itemLink.click();
		return true;
	}
	
	public void sortByProductName() {

		try {
			WaitUntilElementRefreshed(sortBySelectXpath);
			WaitUntilElementVisible(sortBySelect);
			Select select = new Select(sortBySelect);
			select.selectByIndex(1);
		} catch (Exception e) {
			WebElement ele = reInitiateWebElementForStateElement(sortBySelectXpath);
			WaitUntilElementVisible(ele);
			Select select = new Select(ele);
			select.selectByIndex(1);

		}
	}

	public WebElement reInitiateWebElementForStateElement(String xpath) {
		WebElement reWebEle = driver.findElement(By.xpath(xpath));
		return reWebEle;
	}

	
	
}