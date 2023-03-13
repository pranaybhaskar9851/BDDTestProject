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
    	orderTotalValue.isDisplayed();
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
    	orderSummaryExpand.click();
    	
    	return true;
		
	}

	List<String> orderSummaryList = new ArrayList<>();
	public void fetchOrderSummaryDetails() {
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
	}

	public void selectShippingMethod() throws Exception {

		WaitUntilElementVisible(shippingMethodRadioButton);
		shippingMethodRadioButton.isDisplayed();
		utils.highlightElement(shippingMethodRadioButton);
		shippingMethodRadioButton.click();

	}

	public void verifyShippingAddress() {
		//div[@class='billing-address-details']
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}