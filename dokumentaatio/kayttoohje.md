# Käyttöohje


Lukuvinkkaajan avulla voit tallentaa sekä listata lukuvinkkejä. Ohjelmaa käytetään komentoriviltä. 


## Ohjelman asennus ja käynnistäminen

Ohjelma asennetaan toistaiseksi lataamalla ohjelman sisältämä github-repositorio omalle tietokoneelle. Sitten komentorivillä hakeudutaan ohjelman juurikansioon ja suoritetaan ohjelman käynnistävä komento.

Ohjelma käynnistetään komennolla:
```
gradle run
```

Eteen avautuu tervetuloa-teksti sekä lista ohjelman komennoista.

Ohjelman voi suorittaa myös esimerkiksi tmcbeans-sovelluksessa.


## Lukuvinkin luominen

Uusi vinkki tallennetaan komennolla: 
```
T
```

Komennon käyttämisen jälkeen ohjelma kysyy uuden lukuvinkin otsikkoa. Annettuasi otsikon kysyy ohjelma puolestaan lukuvinkin URL-osoitetta. Osoitteen saatuaan Lukuvinkkaaja tallentaa annetun lukuvinkin ja pyytää uutta komentoa. Voit tallentaa uuden lukuvinkin, listata lukuvinkit tai poistua sovelluksesta. 


## Lukuvinkkien listaaminen

Vinkit listataan komennolla:
```
L
```

Lukuvinkkaaja listaa tallennetut lukuvinkit omille riveilleen muodossa nimi-osoite-päivämäärä. Päivämäärä on lukuvinkin lisäyspäivämäärä. Lukuvinkit listataan lisäyspäivämäärän mukaan, niin että tuoreimmat lukuvinkit on päällimäisenä. Lukuvinkkien listaamisen jälkeen ohjelma pyytää uutta komentoa.

## Lukuvinkin merkkaaminen luetuksi

Lukuvinkit merkataan luetuksi komennolla:
```
M
```

Lukuvinkkaajassa voi merkata lukuvinkin luetuksi. Luettuun lukuvinkkiin tallentuu myös päivämäärä, jolloin se on merkattu luetuksi. Lukuvinkin luetuksi merkkaamisen jälkeen ohjelma pyytää uutta komentoa.

## Lukuvinkin poistaminen

Lukuvinkit poistetaan komennolla:
```
P
```

Lukuvinkin poistaminen poistaa lukuvinkin tietokannasta kokonaisuudessaan. Poistettua lukuvinkkiä ei voi palauttaa. Poistamisen jälkeen ohjelma kysyy uutta komentoa.


## Sovelluksen sulkeminen

Sovellus suljetaan komennolla
```
Q
```


