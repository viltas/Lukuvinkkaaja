Feature: Lukuvinkki voidaan merkitä luetuksi ja lukuajankohta tallentuu
    Scenario: Lukuvinkeillä on oma id, johon viitata
    Given Tietokanta on olemassa
    When  Kayttaja lisaa lukuvinkin "Uniikki vinkki" ja linkin ""
    And   Kayttaja lisaa lukuvinkin "Uniikki vinkki" ja linkin ""
    And   Ohjelma käynnistetään
    Then  Molemmilla vinkeilla "Uniikki vinkki" on uniikki id

    Scenario: Viittaamalla id:seen voi merkata vinkin luetuksi
    Given Tietokanta on olemassa
    When  Kayttaja lisaa lukuvinkin "Luettava" ja linkin ""
    And   Ohjelma käynnistetään
    And   Kayttaja merkka lukuvinkin "Luettava" luetuksi
    Then  Lukuvinkki "Luettava" on luettu
