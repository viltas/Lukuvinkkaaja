Feature: Lukuvinkit järjestetään oletusarvoisesti lisäysajan mukaan uusin ylimmäksi
    Scenario: Listattaessa lukuvinkit lisäysaikajärjestyksessä
    Given Tietokanta on olemassa
    When  Kayttaja lisaa lukuvinkin "Vanha vinkki" ja linkin ""
    And   Kayttaja lisaa lukuvinkin "Uusi vinkki" ja linkin ""
    And   Ohjelma käynnistetään
    Then  Lukuvinkki "Uusi vinkki" on lukuvinkkia "Vanha vinkki" ylempana
