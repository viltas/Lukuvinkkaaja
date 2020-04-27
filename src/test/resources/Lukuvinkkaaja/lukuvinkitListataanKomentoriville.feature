Feature: Käyttäjänä saan lukuvinkkien listauksen komentoriville
    Scenario: Otsikko ja linkki per lukuvinkki nähtävillä
    Given Tietokanta on olemassa
    When  Kayttaja lisaa lukuvinkin "Tosi hyvä" ja linkin "www.google.fi"
    And   Lukuvinkit listataan
    Then  Tulostuu "Tosi hyvä"
    Then  Tulostuu "www.google.fi"

    Scenario: Ohjelma listaa lukuvinkit heti käynnistyksen yhteydessä
    Given Tietokanta on olemassa
    When  Ohjelma käynnistetään
    Then  Tulostuu "Tosi hyvä"