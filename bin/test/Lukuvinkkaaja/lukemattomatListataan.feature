Feature: Käyttäjänä saan lukemattomien lukuvinkkien listauksen komentoriville
    Scenario: Lukemattomien otsikko ja linkki per lukuvinkki nähtävillä
    Given Tietokanta on olemassa
    When  Kayttaja lisaa lukuvinkin "Hesari" ja linkin "www.hs.fi"
    When  Kayttaja lisaa lukuvinkin "Iltasanomat" ja linkin "www.is.fi"
    And   Kayttaja merkkaa lukuvinkin "Iltasanomat" luetuksi
    And   Lukemattomat listataan
    Then  Tulostuu "Hesari"
    Then  Tulostuu "www.hs.fi"	