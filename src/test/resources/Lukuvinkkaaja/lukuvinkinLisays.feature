Feature: Kayttajana voin lisata lukuvinkin otsikolla ja linkilla komentorivin kautta    
    Scenario: Otsikko on pakollinen, mutta linkki vapaaehtoinen
    Given Tietokanta on olemassa
    When  Kayttaja lisaa lukuvinkin "Kivaa luettavaa" ja linkin ""
    Then  Syote hyvaksytaan
 
    Scenario: Tallennetaan myös lisaysaika
    Given Tietokanta on olemassa
    When  Kayttaja lisaa lukuvinkin "Blogi" ja linkin "www.stackoverflow.com"
    And   Lukuvinkit listataan
    Then  Lisaysaika on nakyvilla
 
    Scenario: Lisaysnakymaan siirrytaan erillisella komennolla alkunakymasta
    Given Tietokanta on olemassa
    When  Ohjelma käynnistetään
    Then   Ei tulostu "Anna otsikko: "
    When  Käyttäjä siirtyy lisäysnäkymään
    Then  Tulostuu "Anna otsikko: "
 