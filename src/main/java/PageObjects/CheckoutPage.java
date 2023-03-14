package PageObjects;

import Utilities.BaseClass;
import Utilities.PropertiesReader;
import Utilities.TestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class CheckoutPage extends BaseClass {

	TestUtils utils;
	WebDriver driver;
    public CheckoutPage(WebDriver driver, WebDriverWait wait) {

        super(driver, wait);
        PageFactory.initElements(driver, this);
        utils = new TestUtils(driver, wait);
        this.driver = driver;
		
    }

    @FindBy(xpath = "//table[@id='shopping-cart-table']//tbody")
    @CacheLookup
    private List<WebElement> itemsList;
    
    @FindBy(xpath = "//table[@id='shopping-cart-table']//tbody//strong//a")
    @CacheLookup
    private List<WebElement> itemsName;
    
    @FindBy(xpath = "//span[text()='Shopping Cart']")
    @CacheLookup
    private WebElement shoppingCart;
    
    @FindBy(xpath = "//strong[text()='Summary']")
    @CacheLookup
    private WebElement summaryTitle;
    
    @FindBy(xpath = "//span[@data-th='Subtotal']")
    @CacheLookup
    private WebElement subTotalValue;
    
    @FindBy(xpath = "//*[@data-th='Tax']//span")
    @CacheLookup
    private WebElement taxValue;
    
    @FindBy(xpath = "//*[@data-th='Order Total']//span")
    @CacheLookup
    private WebElement orderTotalValue;
    
    @FindBy(xpath = "//button[@title='Proceed to Checkout']//span/..")
    @CacheLookup
    private WebElement proceedToCheckoutButton;
    

    @FindBy(xpath = "//*[text()='Shipping Address']")
    @CacheLookup
    private WebElement shippingAddressTitle;
    

    @FindBy(xpath = "//span[text()='Order Summary']")
    @CacheLookup
    private WebElement orderSummaryTitle;
    
    @FindBy(xpath = "//ol[@class='minicart-items']//li//strong[@class='product-item-name']")
    @CacheLookup
    private List<WebElement> orderSummaryInfo;
  
    @FindBy(xpath = "//div[@class='block items-in-cart']//div[@role='tab']")
    @CacheLookup
    private WebElement orderSummaryExpand;
    
    @FindBy(xpath = "//input[@name='street[0]']")
    @CacheLookup
    private WebElement street1;
 
    @FindBy(xpath = "//input[@name='street[1]']")
    @CacheLookup
    private WebElement street2;
    
    @FindBy(xpath = "//input[@name='city']")
    @CacheLookup
    private WebElement city;
    
    @FindBy(xpath = "//input[@name='postcode']")
    @CacheLookup
    private WebElement postalCode;
    
    @FindBy(xpath = "//input[@name='telephone']")
    @CacheLookup
    private WebElement phoneNumber;
 
    @FindBy(xpath = "//select[@name='country_id']")
    @CacheLookup
    private WebElement countrySelect;
 
    @FindBy(xpath = "//select[@name='region_id']")
    @CacheLookup
    private WebElement stateSelect;

    @FindBy(xpath = "//input[@type='radio' and @value='flatrate_flatrate']")
    @CacheLookup
    private WebElement shippingMethodRadioButton;

    @FindBy(xpath = "//button[@type='submit']//span[text()='Next']")
    @CacheLookup
    private WebElement nextButton;
    
    @FindBy(xpath = "//div[@class='billing-address-details']")
    @CacheLookup
    private WebElement billingAddressDetails;
    
    @FindBy(xpath = "//*[text()='Payment Method']")
    @CacheLookup
    private WebElement paymentMethodTitle;

    @FindBy(xpath = "//div[@class='ship-to']//div[@class='shipping-information-content']")
    @CacheLookup
    private WebElement shipto;

    @FindBy(xpath = "//div[@class='ship-via']//div[@class='shipping-information-content']")
    @CacheLookup
    private WebElement shipVia;
    
    @FindBy(xpath = "//span[@data-th='Cart Subtotal']")
    @CacheLookup
    private WebElement cartSubTotalValue;
    
    @FindBy(xpath = "//span[@data-th='Shipping']")
    @CacheLookup
    private WebElement shipping;

    @FindBy(xpath = "//button[@title='Place Order']//span/..")
    @CacheLookup
    private WebElement placeOrderButton;
    
    @FindBy(xpath = "//*[text()='Thank you for your purchase!']")
    @CacheLookup
    private WebElement orderConfirmMsg;
   
    @FindBy(xpath = "//a[@class='order-number']/strong")
    @CacheLookup
    private WebElement orderNumber;
  
    @FindBy(xpath = "//span[contains(text(),'Order #')]")
    @CacheLookup
    private WebElement myOrderNumber;
    
    @FindBy(xpath = "//div[@class='order-details-items ordered']")
    @CacheLookup
    private WebElement submittedOrder;
    
    @FindBy(xpath = "//div[@class='block block-order-details-view']")
    @CacheLookup
    private WebElement submittedOrderInfo;
    
    @FindBy(xpath = "//div[@class='new-address-popup']//button")
    @CacheLookup
    private WebElement newAddressButton;
    
    @FindBy(xpath = "//span[text()='Ship here']/..")
    @CacheLookup
    private WebElement shipHereButton;
    
    @FindBy(xpath = "//*[@data-th='Order Total']//span")
    @CacheLookup
    private WebElement orderSummaryTotalValue;
    
    public int verifyItemsListAtCart() {
    	System.out.println("entered verifyItemsListAtCart");
        int i=0;
    	for(WebElement e : itemsList) {
        	utils.highlightElement(e);
        	utils.highlightElement(itemsName.get(i));
        	System.out.println("item added :"+itemsName.get(i).getText());
        	i++;
        }
        utils.reduceScreenSize("75");
        System.out.println("itemList size : "+itemsList.size());
        return itemsList.size();
        
    }
    
    public boolean isShoppingCartPageDisplayed() {
    	WaitUntilElementVisible(shoppingCart);
    	shoppingCart.isDisplayed();
        utils.highlightElement(shoppingCart);
        return true;
        
    }

    HashMap<String, String> orderTotalMap = new HashMap<>();
    
	public boolean verifyOrderTotalSummary() throws Exception {
		WaitUntilElementVisible(summaryTitle);
		summaryTitle.isDisplayed();
    	utils.highlightElement(summaryTitle);
    	
    	WaitUntilElementVisible(subTotalValue);
    	subTotalValue.isDisplayed();
    	utils.highlightElement(subTotalValue);
    	orderTotalMap.put("SubTotal", subTotalValue.getText());
    	
    	WaitUntilElementVisible(taxValue);
    	taxValue.isDisplayed();
    	utils.highlightElement(taxValue);
    	orderTotalMap.put("Tax", taxValue.getText());
    	
    	WaitUntilElementVisible(orderTotalValue);
    	orderTotalValue.isDisplayed();
    	utils.highlightElement(orderTotalValue);
    	orderTotalMap.put("OrderTotal", orderTotalValue.getText());
    	
    	System.out.println("Order Total summary map: "+orderTotalMap);
    	
    	boolean matched = true;
    	for(Entry m : orderTotalMap.entrySet() ) {
    		System.out.println("Expected value: "+orderTotalMap.get(m.getKey()));
    		System.out.println("Actual value: "+PropertiesReader.getValue(String.valueOf(m.getKey())));
    		if(!orderTotalMap.get(m.getKey()).equals(PropertiesReader.getValue(String.valueOf(m.getKey())))) {
    			matched = false;
    		}
    	}
    	return matched;
	}

	public void clickOnProceedToCheckout() {
		utils.reduceScreenSize("100");
		WaitUntilElementVisible(proceedToCheckoutButton);
		proceedToCheckoutButton.isDisplayed();
    	utils.highlightElement(proceedToCheckoutButton);
    	new Actions(driver).moveToElement(proceedToCheckoutButton).click().build().perform();
    	
    	//proceedToCheckoutButton.click();
	}

	public boolean isShippingPageDisplayed() {
		
		WaitUntilElementVisible(shippingAddressTitle);
		shippingAddressTitle.isDisplayed();
    	utils.highlightElement(shippingAddressTitle);
    	return true;
		
	}
	
	public boolean isOrderSummaryDisplayed() {
		
		WaitUntilElementVisible(orderSummaryTitle);
		orderSummaryTitle.isDisplayed();
    	utils.highlightElement(orderSummaryTitle);
    	
    	WaitUntilElementVisible(orderSummaryExpand);
    	orderSummaryExpand.isDisplayed();
    	utils.highlightElement(orderSummaryExpand);
    	//orderSummaryExpand.click();
    	
    	new Actions(driver).moveToElement(orderSummaryExpand).click().build().perform();
    	return true;
		
	}

	List<String> orderSummaryList = new ArrayList<>();
	public void fetchOrderSummaryDetails() {
		//new Actions(driver).moveToElement(orderSummaryExpand).click().build().perform();
		int i =0;
		for(WebElement e: orderSummaryInfo) {
			utils.highlightElement(e);
        	System.out.println("order summary details :"+e.getText());
        	orderSummaryList.add(e.getText());
        	i++;
		}
		
	}

	public boolean validateOrderSummaryDetails() throws Exception {
		System.out.println("Order summary map :"+orderSummaryList);
		if(orderSummaryList.size()==3)
			return true;
		else
			return false;
		
	}

	public void enterShippingAddress() throws Exception {
		
//		if(newAddressButton.isDisplayed())
//		{
//			WaitUntilElementVisible(newAddressButton);
//			newAddressButton.isDisplayed();
//	    	utils.highlightElement(newAddressButton);
//	    	newAddressButton.click();
//		}
		
    	
		WaitUntilElementVisible(street1);
		street1.isDisplayed();
    	utils.highlightElement(street1);
    	street1.sendKeys(PropertiesReader.getValue("street1"));
    	
    	WaitUntilElementVisible(street2);
		street2.isDisplayed();
    	utils.highlightElement(street2);
    	street2.sendKeys(PropertiesReader.getValue("street2"));
    	
    	WaitUntilElementVisible(city);
    	city.isDisplayed();
    	utils.highlightElement(city);
    	city.sendKeys(PropertiesReader.getValue("city"));
    	
    	WaitUntilElementVisible(countrySelect);
    	countrySelect.isDisplayed();
    	utils.highlightElement(countrySelect);
    	new Select(countrySelect).selectByVisibleText(PropertiesReader.getValue("country"));
    	
    	WaitUntilElementVisible(stateSelect);
    	stateSelect.isDisplayed();
    	utils.highlightElement(stateSelect);
    	new Select(stateSelect).selectByVisibleText(PropertiesReader.getValue("state"));
    	
    	WaitUntilElementVisible(postalCode);
    	postalCode.isDisplayed();
    	utils.highlightElement(postalCode);
    	postalCode.sendKeys(PropertiesReader.getValue("postalcode"));
    	
    	WaitUntilElementVisible(phoneNumber);
    	phoneNumber.isDisplayed();
    	utils.highlightElement(phoneNumber);
    	phoneNumber.sendKeys(PropertiesReader.getValue("phone"));
    
//    	if(newAddressButton.isDisplayed()) {
//	    	WaitUntilElementVisible(shipHereButton);
//			newAddressButton.isDisplayed();
//	    	utils.highlightElement(shipHereButton);
//	    	shipHereButton.click();
//    	}
    	
	}

	public void selectShippingMethod() throws Exception {

		WaitUntilElementVisible(shippingMethodRadioButton);
		shippingMethodRadioButton.isDisplayed();
		utils.highlightElement(shippingMethodRadioButton);
		shippingMethodRadioButton.click();
		
		WaitUntilElementVisible(nextButton);
		nextButton.isDisplayed();
		utils.highlightElement(nextButton);
		nextButton.click();
		
	}

	public boolean verifyShippingAddress() {

		WaitUntilElementVisible(billingAddressDetails);
		billingAddressDetails.isDisplayed();
		utils.highlightElement(billingAddressDetails);
		return true;
		
	}
	
	public boolean isPaymentPageDisplayed() {
		
		WaitUntilElementVisible(paymentMethodTitle);
		paymentMethodTitle.isDisplayed();
    	utils.highlightElement(paymentMethodTitle);
    	return true;
		
	}

	HashMap<String, String> orderSummaryMap = new HashMap<>();
    
	public void fetchShipmentDetails() {
		WaitUntilElementVisible(shipto);
		shipto.isDisplayed();
    	utils.highlightElement(shipto);
    	
    	WaitUntilElementVisible(shipVia);
    	shipVia.isDisplayed();
    	utils.highlightElement(shipVia);
    	
    	WaitUntilElementVisible(cartSubTotalValue);
    	cartSubTotalValue.isDisplayed();
    	utils.highlightElement(cartSubTotalValue);
    	orderSummaryMap.put("CartSubTotal", cartSubTotalValue.getText());
    	
    	WaitUntilElementVisible(shipping);
    	shipping.isDisplayed();
    	utils.highlightElement(shipping);
    	orderSummaryMap.put("Shipping", shipping.getText());
    	
    	
    	WaitUntilElementVisible(orderSummaryTotalValue);
    	orderSummaryTotalValue.isDisplayed();
    	utils.highlightElement(orderSummaryTotalValue);
    	orderSummaryMap.put("OrderTotalFinal", orderSummaryTotalValue.getText());
    	
    	System.out.println("Order summary map: "+orderSummaryMap);
	}
	
	public boolean validateShipmentDetails() throws Exception{
		boolean matched = true;
    	for(Entry m : orderSummaryMap.entrySet() ) {
    		System.out.println("Expected value: "+orderSummaryMap.get(m.getKey()));
    		System.out.println("Actual value: "+PropertiesReader.getValue(String.valueOf(m.getKey())));
    		if(!orderSummaryMap.get(m.getKey()).equals(PropertiesReader.getValue(String.valueOf(m.getKey())))) {
    			matched = false;
    		}
    	}
    	return matched;
	}
	
	public void clickOnPlaceOrder() {
		WaitUntilElementVisible(placeOrderButton);
		placeOrderButton.isDisplayed();
    	utils.highlightElement(placeOrderButton);
    	new Actions(driver).moveToElement(placeOrderButton).click().build().perform();
    	
	}

	String orderNo;
	public boolean verifyOrderConfirmationMsg() {
		
		WaitUntilElementVisible(orderConfirmMsg);
		orderConfirmMsg.isDisplayed();
    	utils.highlightElement(orderConfirmMsg);
    	
    	WaitUntilElementVisible(orderNumber);
    	orderNumber.isDisplayed();
    	utils.highlightElement(orderNumber);
    	
    	orderNo = String.valueOf(orderNumber.getText());
    	System.out.println("Order number is :"+orderNo);
    	
    	return true;
	}
	
	
	public void clickOnOrderNumber() {
		WaitUntilElementVisible(orderNumber);
    	orderNumber.isDisplayed();
    	orderNumber.click();
	}

	public boolean isMyOrdersPageDisplayed() {
		WaitUntilElementVisible(myOrderNumber);
		myOrderNumber.isDisplayed();
    	utils.highlightElement(myOrderNumber);
    	if(myOrderNumber.getText().split(" ")[2].equals(orderNo))
    		return true;
    	else 
    		return false;
	}

	public void verifySubmittedOrder() {
		WaitUntilElementVisible(submittedOrder);
		submittedOrder.isDisplayed();
		new Actions(driver).moveToElement(submittedOrder);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", submittedOrder);    
		utils.highlightElement(submittedOrder);
		
	}
	
	public void verifySubmittedOrderInfo() {
		WaitUntilElementVisible(submittedOrderInfo);
		submittedOrderInfo.isDisplayed();
		new Actions(driver).moveToElement(submittedOrderInfo);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", submittedOrderInfo);    
		utils.highlightElement(submittedOrderInfo);
		 
	}
	
}