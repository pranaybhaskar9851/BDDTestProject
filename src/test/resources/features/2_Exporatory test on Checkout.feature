2. Do some exploratory tests on Checkout functionality in the website link provided and find as many bugs as you can

a. A short description (1-2 sentences) of the approach you took to your testing.

	.For the exploratory testing of the Checkout functionality, I started by understanding the flow of the checkout process, its requirements, and its expected behavior. 
	 Then I explored the application by manually reviewing the checkout process focusing on the different steps involved, by providing different inputs 
	 (selecting the products of different categories with different sizes, colors, quantities etc.,) and by interacting with different UI fields, filling up forms,
	 submitting data and validating the results to find any potential issues.
	.For any given user story, I will start by reviewing the requirements and design of the feature, and then identify test scenarios (positive cases, negative cases) 
	 based on the user flow. I will perform end-to-end testing by simulating real user actions using various test data sets (different items, shipping addresses, shipment methods, 
	 payment methods) to place orders etc. and validate the behavior of the system.

 b. A bulleted list of the scenarios you tried, even if they were successful.
 
 Here are some of the scenarios:

  .Added a single product to the cart and proceeded to checkout
	  .Added a multiple products of different categories to the cart and proceeded to checkout
	  .Checked the behavior of the website when the user is not logged in
	  .Added products with different sizes, colors, quantities to the cart and proceeded to checkout
	  .Checked View and Edit cart functionality by updating the quantity, size, color of the products
	  .Increase/decrease the product quantity and verify the total cost
	  .Remove a product from the cart and verify the updated total cost
	  .Added a product to the cart and then removed it before checking out and Tried to checkout with an empty cart
	  .Validated Shipping page by adding/updating different shipping addresses
	  .Validated  Review & Payments page by updating shipment details and providing same/different billing and shipping address
	  .Checked the functionality of Ship to Multiple Addresses 
	  .Tested checkout with multiple shipping address (same address for 2 items, different address for 2 items) and different shipping methods and verified the billing details
	  .checked if 2 orders are created when items are shipped to 2 different addresses. 1 order when items are shipped to same address
	  .Tested with different shipping addresses (India. United States, Australia etc.) to check the shipping charges vary based on the addresses
	  .Tested with different shipment methods (Flat Rate, Best Way) to check the Order totals are calculated properly (Test the display and calculation of taxes and shipping fees)
	  .Tried to place an order without filling up all the mandatory fields in the Shipping address form or without selecting the shipping methods
	  .Tested the behavior of the website when the user enters an invalid address details like zip code, phone number etc.
	  .Checked the functionality of the discount coupon code
	  .Checked if the website is handling the errors properly, like when a user tries to use an expired/invalid coupon codes
	  .Tested the order confirmation page and email notification
	  .Tested the My orders page and order details displayed there
	  .Tested the compatibility of the website across different browsers
  
  
c. A quick description of any bugs you found.  (Attached the evidences at src\test\resources\features\Checkout issues.docx)

    .When the country value is updated in the Shopping Cart page,  State and Zip values are not being reset to default
		Steps to reproduce:
		1. Check the shipping charges by selecting country =India, State=Telangana, Zip=123454  		
		2. Update the Country to Chile 
		3. When the country value is updated then state, zip fields should be reset to default values.Instead values of previous selection is being displayed there.		
   		4. Changed country to Chile but State, Zip is not reset to default , Still State=Telangana, Zip=123454 is being displayed.
   		Expected behavior: When the country value is updated then state, zip fields should be reset to default values.
   		
   	.Able to enter alphabets in phone number field
   	    Entered phone number as abcd1234 and able to create the shipping address.
		Expected behavior: Error should be displayed saying Enter Phone number in valid format
   	    	



d. A list of further testing areas that you would want completed before you would be comfortable shipping this feature to users. Consider the risks of the feature as you
understand it.

	Before delivering this feature to users, I would want to perform the following tests to ensure that it is working as expected:
	
	Integration testing: To ensure the checkout process integrates correctly with other systems (e.g. 3rd Party payment gateways, inventory managements etc.,).
	Load testing: To check how the application performs under high traffic (user volume or transaction volume)
				  To ensure the system can handle a high volume of traffic during peak hours.
	Security testing: To ensure that user data is secure during the checkout process
					  To identify vulnerabilities in the payment gateway and data protection.			  
	Compatibility testing to ensure that the application is working properly across different browsers and devices.
	Usability testing/User Acceptance Testing: To gather feedback from users on the checkout process and identify areas for improvement.
	Testing should be covered with different payment methods, currencies, different shipping options and rates.
	Comprehensive testing approach that covers all aspects of the checkout process is crucial before shipping this feature to users. 
	

#####################################

3. Pick a bug from Task No. 2 that you think is most critical and write down how you would log it in JIRA provided the following JIRA fields,
		a. Summary
		b. Description
		c. Attachment
			
			
	Defect Summary:  State and Zip fields are not being reset to default values when the country value is updated in the Shopping Cart page.
	Description: 
	    When the country value is updated in the Shopping Cart page,  State and Zip values are not being reset to default
		Steps to reproduce:
		1. Login to LUMA shopping site as a registed user
		2. Add a clothing item and proceed to checkout
		3. Click on View and Edit Cart 
		4. Check the shipping charges by selecting country =India, State=Telangana, Zip=123454  		
		5. Update the Country to Chile 
		6. Changed country to Chile but State, Zip is not reset to default. Still State=Telangana, Zip=123454 is being displayed.
		6. When the country value is updated then state, zip fields should be reset to default values. Instead values of previous selection is being displayed there.		
   		
   		Expected behavior: When the country value is updated then state, zip fields should be reset to default values.
   		
	 Attachment:  Attached the evidences at src\test\resources\features\Checkout issues.docx
	 
	 Apart from these fields, other important fields such as Sprint value, squad/Team, Linked Jira (User story), assigned To, Severity, Defect Category, 
	 RCA (Root cause and Analysis and resolution will be updated by dev) should be updated in the bug.
	  	
			
