Feature: Place an order for Men's clothing items
     User places an order for 2 Men Tops and 1 Men Bottoms

  @Automated
  Scenario: User logs in to LUMA site and adds 2 Men Tops and 1 Men bottons to Cart
    Given User is on the Sign In page of LUMA shopping site
    When User enters valid login credentials and clicks on Sign In button
    Then User should be redirected to the homepage
    Given User is on the "Men" Clothing page
    When User selects "<Menu Item>" under "<Category>", selects an item "<Product>" with below details and adds it to cart
      | Category | Menu Item | Product                    | Size | Color | Quantity |
      | Tops     | Jackets   | Abominable Hoodie          | XS   | Blue  |        1 |
      | Tops     | Jackets   | Ajax Full-Zip Sweatshirt   | S    | Red   |        1 |
      | Bottoms  | Pants     | Geo Insulated Jogging Pant | 34   | Green |        1 |
    Then User should see the items added to the cart

  #Scenario: User reviews the cart and proceeds to checkout
    Given User is on the cart page
    And verifies the order total summary before checkout
    When User clicks on proceed to checkout button
    Then User should be redirected to the shipping page

  #Scenario: User reviews the order details
    Given User is on the order summary page
    When User verifies the product and price
    Then User should see the correct product and price information

  #Scenario: User enters shipment details for the order and reviews order summary
    Given User is on the shipping address page
    When User enters a valid shipping address
    And User selcts a shipping method and clicks on next
    Then the entered shipping address, selected shipping method should be saved on the Review & Payments page
    
    Given User is on the Review & Payments page
    When User verifies the order summary, shipment and billing details
    Then User should see correct information

  #Scenario: User places an order and verifies the placed order
    #Given User is on the order confirmation page
    #When User clicks on place order button
    #Then User should see the order has been submitted successfully
    #Given User is on the my orders page
    #When User verifies the submitted order
    #Then User should see the correct order information
