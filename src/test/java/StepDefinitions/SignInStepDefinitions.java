package StepDefinitions;

import PageObjects.LUMAHomePage;
import PageObjects.LUMASignInPage;
import Utilities.PropertiesReader;
import Utilities.TestUtils;
import io.cucumber.java.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInStepDefinitions {

    private WebDriver driver = Hooks.driver;
    private WebDriverWait wait;
    LUMAHomePage home;
    LUMASignInPage signIn;
    TestUtils utils;

    @SuppressWarnings("deprecation")
	public SignInStepDefinitions() throws Exception {

        PropertiesReader propertiesReader = new PropertiesReader();
        this.wait = new WebDriverWait(driver, propertiesReader.getTimeout());
        home = new LUMAHomePage(driver, wait);
        signIn = new LUMASignInPage(driver, wait);
        utils =new TestUtils(driver, wait);
	}


    @Given("Login form in login page")
    public void loginPage() {

        
        home.homePageIsDisplayed();
        home.clickSignInLink();
    }

    @When("Submit email using {string} and password using {string}")
    public void submitEmailPassword(String email, String password) {

        LUMASignInPage login = new LUMASignInPage(driver, wait);
        login.loginPageIsDisplayed();
        login.fillEmailData(email);
        login.fillPasswordData(password);
        login.clickSignInButton();
    }

    @Then("Success login to home page with displaying account button")
    public void successLogin() {

        LUMAHomePage home = new LUMAHomePage(driver, wait);
       // Assert.assertTrue(home.homePageAfterLoginIsDisplayed());
    }
    
    @When("user clicks on Menu item {string}")
    public void clickonMenuItem(String menuItem) {

      home.hoverOnmenMenuItemHeader();
    }
    
    @When("user clicks on category {string}")
    public void clickOnCategoryLink(String category) {

      home.hoverOnCategoryLink(category);
    }

    
    @Given("User is on the Sign In page of LUMA shopping site")
    public void user_is_on_the_sign_in_page_of_luma_shopping_site() throws IOException {
    	home.clickSignInLink();
    	signIn.loginPageIsDisplayed();
    	Assert.assertTrue("LUMA sign in page displayed successfully", signIn.loginPageIsDisplayed());
    	utils.takeScreenshotAtEndOfTest();
    	
    }
    @When("User enters valid login credentials and clicks on Sign In button")
    public void user_enters_valid_login_credentials_and_clicks_on_sign_in_button() throws Exception {
        signIn.fillEmailData(PropertiesReader.getValue("Email"));
        signIn.fillPasswordData(PropertiesReader.getValue("Password"));
        signIn.clickSignInButton();
        
    }
    @Then("User should be redirected to the homepage")
    public void user_should_be_redirected_to_the_homepage() throws IOException {
        signIn.loginIsSuccessfull();
        Assert.assertTrue("Sign in to LUMA site is successful", signIn.loginIsSuccessfull());
        utils.takeScreenshotAtEndOfTest();
        
    }
   
}