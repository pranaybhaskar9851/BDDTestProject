Feature: Product comparision and purchase
  As a registered user
  I want to check different clothing options for men
  So that I can compare the product and buy it

  Scenario: Verify that the user can view different clothing options for men
    Given user logged in as a registered user on the LUMA shopping site
    When user hover over the "Men" section in the navigation menu
    Then user should see a list of categories "<category name>" under "Men" section
      | category name |
      | Tops          |
      | Bottoms       |
    When user clikcs on the "Tops" category under "Men" section
    Then user should see a list of clothing options "<Menu Item>" under "Tops" category
      | Menu Item             |
      | Hoodies & Sweatshirts |
      | Jackets               |
      | Tees                  |
      | Tanks                 |
    When user clikcs on the "Bottoms" category under "Men" section
    Then user should see a list of clothing options "<Menu Item>" under "Bottoms" category
      | Menu Item |
      | Pants     |
      | Shorts    |

  Scenario Outline: Verify that the user able to view the clothing items and its details
    Given user logged in as a registered user on the LUMA shopping site
    When user navigates to  "Men" section in the navigation menu
    And selects an item "<Menu Item>" under "<Category>" category
    Then user should see the list of clothing items on the page
    When user selects a specific clothing item
    Then user should see the details of that item including price, color, size, quantity, more information, reviews etc.

    Examples: 
      | Category | Menu Item             |
      | Tops     | Jackets               |
      | Tops     | Hoodies & Sweatshirts |
      | Bottoms  | Pants                 |
      | Bottoms  | Shorts                |

  Scenario Outline: Verify the Sort functionality for Men's Clothing Options
    Given user logged in as a registered user on the LUMA shopping site
    And user navigates to "Men" section in the navigation menu
    And selects an item "<Menu Item>" under "<Category>" category
    When user selcts a sort option "<Sort By>"
    Then the clothing options should be displayed in ascending or descending order based on the selected sorting option

    Examples: 
      | Category | Menu Item             | Sort By      |
      | Tops     | Jackets               | Position     |
      | Tops     | Hoodies & Sweatshirts | Product Name |
      | Bottoms  | Pants                 | Price        |

  Scenario Outline: Verify the Filter functionality for Men's Clothing Options
    Given user logged in as a registered user on the LUMA shopping site
    And user navigates to "Men" section in the navigation menu
    And selects an item "Jackets" under a specific category
    When user selcts a filter option "<Filter By>"
    Then user should see only the clothing options for men that fall under that filtering category

    Examples: 
      | Filter By          |
      | STYLE              |
      | SIZE               |
      | PRICE              |
      | COLOR              |
      | MATERIAL           |
      | ECO COLLECTION     |
      | PERFORMANCE FABRIC |
      | ERIN RECOMMENDS    |
      | NEW                |
      | SALE               |
      | PATTERN            |
      | CLIMATE            |

  Scenario Outline: User tries to compare different clothing options for men
    Given user logged in as a registered user on the LUMA shopping site
    And user navigates to "Men" section in the navigation menu
    And selects an item "<Menu Item>" under "<Category>" category
    When user selects a product "<Product Name>" and clicks on "Add to Compare" button then a message "<Message>" should be displayed
      | Product Name | Message                                              |
      | <product1>   | You added product <product1> to the comparison list. |
      | <product2>   | You added product <product2> to the comparison list. |
      | <product3>   | You added product <product3> to the comparison list. |
    And user navigates to "Compare Products" page
    Then user should see a side by side comparison of the three items including their features, prices, and ratings

    Examples: 
      | Category | Menu Item | product1                  | product2              | product3            |
      | Tops     | Jackets   | Proteus Fitness Jackshirt | Taurus Elements Shell | Montana Wind Jacket |
      | Bottoms  | Pants     | Cronus Yoga Pant          | Thorpe Track Pant     | Zeppelin Yoga Pant  |

  Scenario Outline: Verify that the user able to remove an item from the Compare products list
    Given user logged in as a registered user on the LUMA shopping site
    And user navigates to "Men" section in the navigation menu
    And user navigates to "Compare Products" page
    When user tries to remove a product "<Product Name>" from the comparision list
    Then a popup should be displaying with the warning message "<Warn Message>"
    When user clicks on "OK" button on the popup
    Then user should see a success message saying "<Message Txt>"
    And the product "<Product Name>" should be removed from the comparision list
    And user should see a side by side comparison of the other two items

    Examples: 
      | Product Name              | Warn Message                                                               | Message  Txt                                                            |
      | Proteus Fitness Jackshirt | Are you sure you want to remove this item from your Compare Products list? | You removed product Proteus Fitness Jackshirt from the comparison list. |

  Scenario Outline: Verify that the user able to clear all items from the comparision list
    Given user logged in as a registered user on the LUMA shopping site
    And user navigates to "Men" section in the navigation menu
    When user clicks on "Clear All" button from the comparision list section
    Then a popup should be displaying with the warning message "<Warn Message>"
    When user clicks on "OK" button on the popup
    Then user should see a success message saying "<Message Txt>"
    And all the products should be removed from the comparision list

    Examples: 
      | Warn Message                                                               | Message  Txt                     |
      | Are you sure you want to remove all items from your Compare Products list? | You cleared the comparison list. |

  Scenario Outline: User tries to buy the product by choosing an item from the comparision list
    Given user logged in as a registered user on the LUMA shopping site
    And user navigates to "Men" section in the navigation menu
    And selects an item "<Menu Item>" under "<Category>" category
    And user selects a product "<Product Name>" and clicks on "Add to Compare" button then a message "<Message>" should be displayed
      | Product Name | Message                                              |
      | <product1>   | You added product <product1> to the comparison list. |
      | <product2>   | You added product <product2> to the comparison list. |
      | <product3>   | You added product <product3> to the comparison list. |
    And user navigates to "Compare Products" page
    When user compares the products list and chooses an item "<product2>" to purchase it
    And user clicks on "Add to Cart"
    Then a message "Message Txt"should be displayed indicating the user to choose the size, color, qunatity etc.
    When user selects the "size", "color" , "quantity" for the selected item and adds it to the cart
    And user navigates to cart and clicks on "Proceed to Checkout" option
    And user enters the "shipping address" and "shipping methods"
    And user proceeds to Review & Payments page and verifies order summary, billing, shipment details
    And user enters discount code and clicks on "Apply Discount" button
    Then Order total should be updated with the discounted price
    When user proceeds to purchase the item by clicking on "Place Order" button
    Then an Order should be placed with the order number
    When user clicks on the order numer
    Then user should be re-directed to My orders page
    And all the details of the current order should be displayed to the user

    Examples: 
      | Category | Menu Item | product1                  | product2              | product3            | Message Txt                               |
      | Tops     | Jackets   | Proteus Fitness Jackshirt | Taurus Elements Shell | Montana Wind Jacket | You need to choose options for your item. |
      | Bottoms  | Pants     | Cronus Yoga Pant          | Thorpe Track Pant     | Zeppelin Yoga Pant  | You need to choose options for your item. |
