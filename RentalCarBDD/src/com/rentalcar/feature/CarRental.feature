Feature: Rental Car with Rest Assured and Cucumber

Scenario: 1. Get Request - List of all cars
Given I mock the URL
When I perform get request
Then I get response and check status code
And I assert if the response body contains tesla
And scenario 1 is completed

Scenario: 2. List of blue tesla with notes
When I perform get request
Then I put car make in a list and I get blue tesla with notes
And scenario 2 is completed

Scenario: 3. Lowest per day rent with price and with price after discount
When I perform get request
Then I get lowest per day rent with price and with price after discount
And scenario 3 is completed

Scenario: 4. Hightest revenue for last year
When I perform get request
Then I get highest revenue for last year
And scenario 4 is completed




