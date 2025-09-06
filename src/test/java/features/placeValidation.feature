Feature: add place api validation
  Scenario: verify if place is being added successfully using addPlaceAPI
    Given user has add place payload
    When user calls "addPlaceAPI" with "POST" http request
    Then API call is success with status code 200
    And "status" in response is "OK"