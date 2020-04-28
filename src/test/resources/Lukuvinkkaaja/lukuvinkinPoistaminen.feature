Feature: Lukuvinkin voi poistaa  

    Scenario: Poistaminen tapahtuu viittaamalla id:llä lukuvinkkiin
    Given Tietokanta on olemassa
    When  Kayttaja lisaa lukuvinkin "Poistettava" ja linkin ""
    And   Ohjelma käynnistetään
    And   Kayttaja poistaa lukuvinkin "Poistettava"
    Then  Lukuvinkki "Poistettava" on poistettu
    

 

 	

 