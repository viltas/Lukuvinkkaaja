Feature: Lukuvinkki voidaan merkitä luetuksi ja lukuajankohta tallentuu
    Scenario: Lukuvinkeillä on oma id, johon viitata
    Given Tietokanta on olemassa
    When  Kayttaja lisaa lukuvinkin "Satunnainen vinkki" ja linkin "www.satun.fi"    
    And   Kayttaja lisaa lukuvinkin "Uniikki vinkki" ja linkin ""
    And   Kayttaja lisaa lukuvinkin "Uniikki vinkki" ja linkin ""
    And   Ohjelma käynnistetään
    Then  Kaikilla vinkeillä on oma id

    Scenario: Viittaamalla id:seen voi merkata vinkin luetuksi
    Given Tietokanta on olemassa
    When  Kayttaja lisaa lukuvinkin "Luettava" ja linkin ""
    And   Ohjelma käynnistetään
    And   Kayttaja merkkaa lukuvinkin "Luettava" luetuksi
    Then  Lukuvinkki "Luettava" on luettu
