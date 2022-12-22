Feature: Swiggy Automation

  @biteMe
  Scenario Outline: Validate Swiggy online food order flow
    Given I launch the <Browser> and navigate to <URL>
    When I am on <URL> page
    Then I verify the page title is displayed as <Title>
    When I enter the delivery location as <Location>
    Then I select the result which is <Result>
    Then I should navigate to <Restaurants> page
    When I search for <Restaurant> and click on search
    Then I select the Restaurant as <Restaurant>
    Then I select outlet as <Outlet> if displayed
    Then I should land on the <Restaurant> page
    Then I add 2 Red Velvet Cupcake to the cart
    And I add 2 Tiramisu Cupcake to the cart
    And I add 1 Irish Coffee Cupcake to the cart
    And I add 1 Choco Choco Cupcake to the cart
    When I click on checkout
    Then I verify items added in checkout page <Checkout Page>
    Then I click on New to Swiggy? SIGN UP
    And I enter name as <Name>
    And I enter phone number as <Phone Number>
    And I enter email as <Email>
    And I enter password as <Password>
    And I click on the Have a referral code?
    When I click on the CONTINUE
    Then I should see an error message displayed as <Error Message> and I capture the screenshot
    Then I change the Red Velvet Cupcake quantity to 1
    Then I capture the screenshot
    And I close the browser

    Examples: 
      | Browser | URL                     | Title                                                                                      | Location               | Result                                   | Restaurants                                  | Restaurant | Outlet      | Checkout Page                   | Name    | Phone Number | Email       | Password | Error Message         |
      | Chrome  | https://www.swiggy.com/ | Order food online from India's best food delivery service. Order from restaurants near you | Indiranager, Bengaluru | Indiranagar, Bengaluru, Karnataka, India | https://www.swiggy.com/bangalore/restaurants | Bite Me    | Indiranagar | https://www.swiggy.com/checkout | abc abc |   0000000000 | abc@def.com | abcdef   | Invalid email address |

  #| Firefox | https://www.swiggy.com/ | Order food online from India's best food delivery service. Order from restaurants near you | Indiranager, Bengaluru | Indiranagar, Bengaluru, Karnataka, India | https://www.swiggy.com/bangalore/restaurants | Bite Me    | Indiranagar | https://www.swiggy.com/checkout | abc abc |   0000000000 | abc@def.com | abcdef   | Invalid email address |
  @leonGrill
  Scenario Outline: Validate Swiggy online food order flow
    Given I launch the <Browser> and navigate to <URL>
    When I am on <URL> page
    Then I verify the page title is displayed as <Title>
    When I enter the delivery location as <Location>
    Then I select the result which is <Result>
    Then I should navigate to <Restaurants> page
    When I search for <Restaurant> and click on search
    Then I select the Restaurant as <Restaurant>
    Then I select outlet as <Outlet> if displayed
    Then I should land on the <Restaurant> page
    Then I add 2 Smoodies Groovy Guava to the cart
    And I add 2 Smoodies Mighty Mango to the cart
    And I add 1 Smoodies Jhakaas Jamun to the cart
    And I add 1 Smoodies Peppy Peach to the cart
    When I click on checkout
    Then I verify items added in checkout page <Checkout Page>
    Then I click on New to Swiggy? SIGN UP
    And I enter name as <Name>
    And I enter phone number as <Phone Number>
    And I enter email as <Email>
    And I enter password as <Password>
    And I click on the Have a referral code?
    When I click on the CONTINUE
    Then I should see an error message displayed as <Error Message> and I capture the screenshot
    Then I change the Smoodies Groovy Guava quantity to 1
    Then I capture the screenshot
    And I close the browser

    Examples: 
      | Browser | URL                     | Title                                                                                      | Location               | Result                                   | Restaurants                                  | Restaurant | Outlet      | Checkout Page                   | Name    | Phone Number | Email       | Password | Error Message         |
      | Chrome  | https://www.swiggy.com/ | Order food online from India's best food delivery service. Order from restaurants near you | Indiranager, Bengaluru | Indiranagar, Bengaluru, Karnataka, India | https://www.swiggy.com/bangalore/restaurants | Leon Grill | Indiranagar | https://www.swiggy.com/checkout | abc abc |   0000000000 | abc@def.com | abcdef   | Invalid email address |
      #| Firefox | https://www.swiggy.com/ | Order food online from India's best food delivery service. Order from restaurants near you | Indiranager, Bengaluru | Indiranagar, Bengaluru, Karnataka, India | https://www.swiggy.com/bangalore/restaurants | Leon Grill | Indiranagar | https://www.swiggy.com/checkout | abc abc |   0000000000 | abc@def.com | abcdef   | Invalid email address |
