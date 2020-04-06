Feature: As a user I can add a reading tip with a header and a link through command line
    Scenario: Add a reading tip
    Given command Tallenna uusi linkki is selected
    When heading "Merge sort algorithm" and Url "https://www.youtube.com/watch?v=TzeBrDU-JaY" is added
    Then system will respond "Lukuvinkki tallennettu!"
