package PageObjects;

import Utilities.BaseClass;
import Utilities.TestUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LUMASignInPage extends BaseClass {

	TestUtils utils;
    public LUMASignInPage(WebDriver driver, WebDriverWait wait) {

        super(driver, wait);
        PageFactory.initElements(driver, this);
        utils = new TestUtils(driver, wait);
		
    }

    @FindBy(id = "email")
    @CacheLookup
    private WebElement emailField;

    @FindBy(id = "pass")
    @CacheLookup
    private WebElement passwordField;

    @FindBy(xpath = "//button[@class='action login primary']")
    @CacheLookup
    private WebElement signInButton;

    

    @FindBy(xpath = "(//span[contains(text(),'Welcome, ')])[1]")
    @CacheLookup
    private WebElement welcomeUserTitle;

    public void fillEmailData(String email) {

        WaitUntilElementVisible(emailField);
        emailField.isEnabled();
        emailField.clear();
        emailField.sendKeys(email);
        utils.highlightElement(emailField);
    }

    public void fillPasswordData(String password) {

        WaitUntilElementVisible(passwordField);
        passwordField.isEnabled();
        passwordField.clear();
        passwordField.sendKeys(password);
        utils.highlightElement(passwordField);
    }

    public void clickSignInButton() {

        WaitUntilElementVisible(signInButton);
        signInButton.isEnabled();
        utils.highlightElement(signInButton);
        signInButton.click();
        
    }
    
   

    public boolean loginPageIsDisplayed() {

    	WaitUntilElementVisible(emailField);
        emailField.isDisplayed();
        
        WaitUntilElementVisible(passwordField);
        passwordField.isDisplayed();
        
        WaitUntilElementVisible(signInButton);
        signInButton.isDisplayed();
        
        
        return true;
    }
    
    public boolean loginIsSuccessfull() {

    	 WaitUntilElementVisible(welcomeUserTitle);
    	 welcomeUserTitle.isDisplayed();
    	 utils.highlightElement(welcomeUserTitle);
        
        return true;
    }
}