Feature: Lukuvinkille voidaan antaa tagi
    Scenario: Lukuvinkkiin voidaan liittää tagi
    Given Tietokanta on olemassa
    When  Kayttaja lisaa lukuvinkin "Kodarit" ja linkin "www.kodarit.fi"    
    And   Ohjelma käynnistetään
    And   Kayttaja antaa lukuvinkille "Kodarit" tagin "web"
    Then  Tulostuu "Tagi(t) lisätty!"

