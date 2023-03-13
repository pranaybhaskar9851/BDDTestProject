package StepDefinitions;

import PageObjects.CheckoutPage;
import PageObjects.ClothingItemsPage;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutStepDefinitions {

    private WebDriver driver = Hooks.driver;
    private WebDriverWait wait;
    LUMAHomePage home;
    ClothingItemsPage product;
    CheckoutPage checkout;
    TestUtils utils;

    public CheckoutStepDefinitions() throws Exception {

        PropertiesReader propertiesReader = new PropertiesReader();
        this.wait = new WebDriverWait(driver, propertiesReader.getTimeout());
        home = new LUMAHomePage(driver, wait);
        product = new ClothingItemsPage(driver, wait);
        checkout = new CheckoutPage(driver, wait);
        utils = new TestUtils(driver, wait);
	}


    
    @Given("User is on the {string} Clothing page")
    public void user_is_on_the_clothing_page(String string) {
        home.hoverOnmenMenuItemHeader();
    }
    
    List<String> productListActual = new ArrayList<String>();
    @When("User selects {string} under {string}, selects an item {string} with below details and adds it to cart")
    public void user_selects_under_selects_an_item_with_below_details_and_adds_it_to_cart(String string, String string2, String string3, io.cucumber.datatable.DataTable dataTable) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
    	for (Map<String, String> data : dataTable.asMaps(String.class, String.class)) {
    		home.hoverOnCategoryLink("Men");
    		home.hoverOnCategoryLink(data.get("Category"));
    		home.hoverOnCategoryLink(data.get("Menu Item")); 
			home.clickOnCategoryLink(data.get("Category"));
			
			product.sortByProductName();
			productListActual.add(data.get("Product"));
			product.clickOnItemLink(data.get("Product"));
			product.enterItemSize(data.get("Size"));
			product.enterItemColor(data.get("Color"));
			product.enterQuantity(data.get("Quantity"));
			
			product.clickOnaddToCartButton();
			//product.validateCartAddition(data.get("Product"));
			Assert.assertTrue("Item added to the cart successfully", product.validateCartAddition(data.get("Product")));
			
		    
		}
    	utils.takeScreenshotAtEndOfTest();
    	
    }
    
    List<String> productListExpected = new ArrayList<String>();
    
    @Then("User should see the items added to the cart")
    public void user_should_see_the_items_added_to_the_cart() throws IOException {
        
    	checkout.verifyItemsListAtCart();
    	Assert.assertEquals("Items are added to the cart succesfully", productListActual.size(), checkout.verifyItemsListAtCart());
    	utils.takeScreenshotAtEndOfTest();
    }
    @Given("User is on the cart page")
    public void user_is_on_the_cart_page() {
        checkout.isShoppingCartPageDisplayed();
        Assert.assertTrue("Cart is displayed successfully", checkout.isShoppingCartPageDisplayed());
    }
    @When("verifies the order total summary before checkout")
    public void verify_order_summary() throws Exception {
    	Assert.assertTrue("Cart is displayed successfully", checkout.verifyOrderTotalSummary());
        utils.takeScreenshotAtEndOfTest();
    	
    }
    @When("User clicks on proceed to checkout button")
    public void user_clicks_on_proceed_to_checkout_button() {
        checkout.clickOnProceedToCheckout();
    }
    @Then("User should be redirected to the shipping page")
    public void user_should_be_redirected_to_the_shipping_page() throws IOException {
        Assert.assertTrue("Navigated to shipping page successfully",checkout.isShippingPageDisplayed());
        utils.takeScreenshotAtEndOfTest();
        
    }
    @Given("User is on the order summary page")
    public void user_is_on_the_order_summary_page() throws IOException {
    	Assert.assertTrue("User is on the order summary section",checkout.isOrderSummaryDisplayed());
        utils.takeScreenshotAtEndOfTest();
    }
    @When("User verifies the product and price")
    public void user_verifies_the_product_and_price() {
        checkout.fetchOrderSummaryDetails();
    }
    @Then("User should see the correct product and price information")
    public void user_should_see_the_correct_product_and_price_information() throws Exception {
    	Assert.assertTrue("Product and price information verified",checkout.validateOrderSummaryDetails());
    	utils.takeScreenshotAtEndOfTest();
    }
    @Given("User is on the shipping address page")
    public void user_is_on_the_shipping_address_page() {
        checkout.isShippingPageDisplayed();
    }
    @When("User enters a valid shipping address")
    public void user_enters_a_valid_shipping_address() throws Exception {
        checkout.enterShippingAddress();
        utils.takeScreenshotAtEndOfTest();
    }
    @When("User selcts a shipping method and clicks on next")
    public void user_selcts_a_shipping_method_and_clicks_on_next() throws Exception {
        checkout.selectShippingMethod();
    }
    @Then("the entered shipping address, selected shipping method should be saved on the Review & Payments page")
    public void the_entered_shipping_address_selected_shipping_method_should_be_saved_on_the_review_payments_page() {
        checkout.verifyShippingAddress();
    }
    @Given("User is on the Review & Payments page")
    public void user_is_on_the_review_payments_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("User verifies the order summary, shipment and billing details")
    public void user_verifies_the_order_summary_shipment_and_billing_details() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("User should see correct information")
    public void user_should_see_correct_information() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Given("User is on the order confirmation page")
    public void user_is_on_the_order_confirmation_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("User clicks on place order button")
    public void user_clicks_on_place_order_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Then("User should see the order has been submitted successfully")
    public void user_should_see_the_order_has_been_submitted_successfully() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Given("User is on the my orders page")
    public void user_is_on_the_my_orders_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("User verifies the submitted order")
    public void user_verifies_the_submitted_order() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("User should see the correct order information")
    public void user_should_see_the_correct_order_information() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

   
}