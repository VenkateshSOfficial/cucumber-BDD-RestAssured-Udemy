Feature: add place api validation
  @smoke @addPlace
  Scenario Outline: verify if place is being added successfully using addPlaceAPI
    Given user has add place payload with "<name>" and "<address>"
    When user calls "ADD_PLACE_RESOURCE" with "POST" http request
    Then API call is success with status code 200
    And "status" in response is "OK"
    Examples:
      | name        | address         |
      | sweet house | 1st good street |

