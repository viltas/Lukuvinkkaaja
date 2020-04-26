Feature: Käyttäjänä saan luettujen lukuvinkkien listauksen komentoriville
    Scenario: Luettujen otsikko ja linkki per lukuvinkki nähtävillä
    Given Tietokanta on olemassa
    When  Kayttaja lisaa lukuvinkin "Hesari" ja linkin "www.hs.fi"
    When  Kayttaja lisaa lukuvinkin "Iltalehti" ja linkin "www.il.fi"
    And   Kayttaja merkkaa lukuvinkin "Iltalehti" luetuksi
    And   Luetut listataan
    Then  Tulostuu "Iltalehti"
    Then  Tulostuu "www.il.fi"