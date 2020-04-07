Feature: As a user I can list all reading tips through command line
    Scenario: List tips when no tips have been added
    Given ui is running
    When tips are listed
    Then no tips are shown

    Scenario: List tips when tips have been added
    Given ui is running
    And LukuVinkki is added
    When tips are listed
    Then tip is listed
