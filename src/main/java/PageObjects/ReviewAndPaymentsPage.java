package PageObjects;

import Utilities.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReviewAndPaymentsPage extends BaseClass {

    public ReviewAndPaymentsPage(WebDriver driver, WebDriverWait wait) {

        super(driver, wait);
        PageFactory.initElements(driver, this);
		
    }

    @FindBy(id = "email")
    @CacheLookup
    private WebElement emailField;

   
    public void fillEmailData(String email) {

        WaitUntilElementVisible(emailField);
        emailField.isEnabled();
        emailField.clear();
        emailField.sendKeys(email);
    }

}