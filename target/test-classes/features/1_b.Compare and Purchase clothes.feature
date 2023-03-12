Feature: Product comparision and purchase
  As a registered user
  I want to check different clothing options for men
  So that I can compare the product and buy it

  #You added product Proteus Fitness Jackshirt to the comparison list.
  #Compare Products - add, delete, clear all
  
  #
  #Verify that the user is able to add the product to the cart and proceed to checkout.
  #Verify that the user is able to purchase the product using a registered account or as a guest user.
  #Verify that the user is able to view their order history and track the status of their order.
  #
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
