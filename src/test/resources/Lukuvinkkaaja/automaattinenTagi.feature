Feature: Automaattinen tagien lisäys urlin perusteella

    Scenario: Automaattinen video tagin lisäys urlin perusteella
    Given Tietokanta on olemassa
    When Kayttaja lisaa lukuvinkin "Videolinkki" ja linkin "www.youtube.com"
    And Ohjelma käynnistetään
    Then Lukuvinkilla on tagi "video"

    Scenario: Automaattinen julkaisu tagin lisäys urlin perusteella
    Given Tietokanta on olemassa
    When Kayttaja lisaa lukuvinkin "artikkelilinkki" ja linkin "www.julkaisu.doi"
    And Ohjelma käynnistetään
    Then Lukuvinkilla on tagi "julkaisu"



