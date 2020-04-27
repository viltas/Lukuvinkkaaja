Feature: Lukuvinkit tallentuvat pysyvästi
    Scenario: Lukuvinkit ovat tallella myos sovelluksen uudelleenkäynnistyksen jalkeen
    Given Tietokanta on olemassa
    When  Kayttaja lisaa lukuvinkin "Muista lukea" ja linkin "www.city.fi"
    And   Ohjelma käynnistetään
    Then  Tulostuu "Muista lukea"
