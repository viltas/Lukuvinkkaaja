Feature: Lukuvinkit voidaan jaotella luettuihin / lukemattomiin

    Scenario: Oma komento luetuille
    Given Tietokanta on olemassa
    When  Kayttaja lisaa lukuvinkin "Hesari" ja linkin "www.hs.fi"
    When  Kayttaja lisaa lukuvinkin "Iltalehti" ja linkin "www.il.fi"
    And   Kayttaja merkkaa lukuvinkin "Iltalehti" luetuksi
    And   Luetut listataan
    Then  Tulostuu "Iltalehti"
    Then  Tulostuu "www.il.fi"

    Scenario: Oma komento lukemattomille
    Given Tietokanta on olemassa
    When  Kayttaja lisaa lukuvinkin "Hesari" ja linkin "www.hs.fi"
    When  Kayttaja lisaa lukuvinkin "Iltasanomat" ja linkin "www.is.fi"
    And   Kayttaja merkkaa lukuvinkin "Iltasanomat" luetuksi
    And   Lukemattomat listataan
    Then  Tulostuu "Hesari"
    Then  Tulostuu "www.hs.fi"	