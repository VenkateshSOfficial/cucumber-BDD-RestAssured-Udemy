Feature: validate if get place api call works correctly
  @smoke @getPlace
  Scenario Outline: get place validation
    Given having placeId's created
    When user hits "GET_PLACE_RESOURCE" using "GET" request
    Then validate if name "<name>" matches the ones received in the response

    Examples:
      | name        |
      | sweet house |
