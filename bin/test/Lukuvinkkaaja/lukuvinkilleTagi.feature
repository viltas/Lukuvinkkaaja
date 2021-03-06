Feature: Lukuvinkille voidaan antaa tagi
    Scenario: Lukuvinkkiin voidaan liittää tagi
    Given Tietokanta on olemassa
    When  Kayttaja lisaa lukuvinkin "Kodarit" ja linkin "www.kodarit.fi"    
    And   Ohjelma käynnistetään
    And   Kayttaja antaa lukuvinkille "Kodarit" tagin "web"
    Then  Tulostuu "Tagi(t) lisätty!"

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



