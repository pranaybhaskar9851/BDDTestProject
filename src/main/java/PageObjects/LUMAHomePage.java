package PageObjects;

import Utilities.BaseClass;

import java.awt.Desktop.Action;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LUMAHomePage extends BaseClass {

	Actions action = null;
	JavascriptExecutor js =  null;
	WebDriver driver = null;
	
	public LUMAHomePage(WebDriver driver, WebDriverWait wait) {

        super(driver, wait);
        PageFactory.initElements(driver, this);
        action = new Actions(driver);
        js = (JavascriptExecutor) driver;
        this.driver = driver;
		
    }

   
    @FindBy(xpath = "//a[@href='https://magento.softwaretestingboard.com/']")
    @CacheLookup
    private WebElement homePageLogo;

    @FindBy(xpath = "(//a[contains(@href,'login')])[1]")
    @CacheLookup
    private WebElement signInLink;
    
    @FindBy(xpath = "//a[@role='menuitem']//span[text()='Women']")
    @CacheLookup
    private WebElement womenMenuItemHeader;
    
    @FindBy(xpath = "//a[@role='menuitem']//span[text()='Men']")
    @CacheLookup
    private WebElement menMenuItemHeader;
    
    
    public boolean homePageIsDisplayed() {

        WaitUntilElementVisible(homePageLogo);
        homePageLogo.isDisplayed();
        WaitUntilElementVisible(signInLink);
        homePageLogo.isDisplayed();
       
        return true;
    }

    public void clickSignInLink() {

        WaitUntilElementVisible(signInLink);
        signInLink.isEnabled();
        signInLink.click();
    }
    
    public void hoverOnmenMenuItemHeader() {

        WaitUntilElementVisible(menMenuItemHeader);
        menMenuItemHeader.isEnabled();
        //menMenuItemHeader.click();
        action.moveToElement(menMenuItemHeader).perform();
    }
    
    String categoryXpath = "//a[@role='menuitem' and contains(@href,'/men.html')]/..//span[text()='%s']";
    
    public void hoverOnCategoryLink(String category) {
    	WebElement categoryLink = driver.findElement(By.xpath(String.format(categoryXpath, category)));
    	 WaitUntilElementVisible(categoryLink);
    	categoryLink.isEnabled();       
        //js.executeScript("arguments[0].scrollIntoView(true);", categoryLink);    
        //categoryLink.click();
        action.moveToElement(categoryLink).build().perform();
    }
    
    public void clickOnCategoryLink(String category) {
    	WebElement categoryLink = driver.findElement(By.xpath(String.format(categoryXpath, category)));
    	WaitUntilElementVisible(categoryLink);
    	categoryLink.isEnabled();       
        categoryLink.click();
    }

    
}