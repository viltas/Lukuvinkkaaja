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

Komennon käyttämisen jälkeen ohjelma kysyy uuden lukuvinkin otsikkoa. Annettuasi otsikon kysyy ohjelma puolestaan lukuvinkin URL-osoitetta. Osoite on vapaaehtoinen. Osoitteen saatuaan Lukuvinkkaaja tallentaa annetun lukuvinkin ja pyytää uutta komentoa. Voit tallentaa uuden lukuvinkin, listata lukuvinkit tai poistua sovelluksesta. 


## Lukuvinkkien listaaminen

Vinkit listataan komennolla:
```
L
```

Lukuvinkkaaja listaa tallennetut lukuvinkit omille riveilleen. Lukuvinkistä näytetään id-numero, nimi, osoite (jos sellainen on), lisäyspäivämäärä ja lukemispäivämäärä (jos sellainen on) ja tagi (jos sellainen on). Lukuvinkit listataan lisäyspäivämäärän mukaan, niin että tuoreimmat lukuvinkit on päällimäisenä. Lukuvinkkien listaamisen jälkeen ohjelma pyytää uutta komentoa.


## Lukemattomien lukuvinkkien listaaminen

Vinkit listataan komennolla:
```
U
```

Lukuvinkkaaja listaa käyttäjälle ne lukuvinkit joita käyttäjä ei ole merkannut luetuiksi. Lukuvinkistä näytetään id-numero, nimi, osoite (jos sellainen on), lisäyspäivämäärä ja lukemispäivämäärä (jos sellainen on) ja tagi (jos sellainen on). Jos kaikki lukuvinkit on merkitty luetuiksi, ohjelma ilmoittaa, että lukemattomia lukuvinkkejä ei löytynyt. Lukuvinkkien listaamisen jälkeen ohjelma pyytää uutta komentoa.


## Luettujen lukuvinkkien listaaminen

Vinkit listataan komennolla:
```
R
```

Lukuvinkkaaja listaa käyttäjälle ne lukuvinkit jotka käyttäjä on merkannut luetuiksi. Lukuvinkistä näytetään id-numero, nimi, osoite (jos sellainen on), lisäyspäivämäärä ja lukemispäivämäärä (jos sellainen on) ja tagi (jos sellainen on). Jos mitään lukuvinkkiä ei ole vielä merkitty luetuksi, ohjelma ilmoittaa, että luettuja lukuvinkkejä ei löytynyt. Lukuvinkkien listaamisen jälkeen ohjelma pyytää uutta komentoa.


## Lukuvinkin merkkaaminen luetuksi

Lukuvinkit merkataan luetuksi komennolla:
```
M
```

Lukuvinkkaajassa voi merkata lukuvinkin luetuksi. Luetuksi merkattava lukuvinkki ilmoitetaan kyseisen vinkin id-numerolla. Luettuun lukuvinkkiin tallentuu myös päivämäärä, jolloin se on merkattu luetuksi. Lukuvinkin luetuksi merkkaamisen jälkeen ohjelma pyytää uutta komentoa.


## Tagin antaminen lukuvinkille

Lukuvinkille annetaan tagi komennolla:
```
A
```

Ohjelma kysyy lukuvinkin id-numeroa. Annettuasi id-numeron voit kirjoittaa lukuvinkille tagin. Tagi näkyy lukuvinkin perässä kun lukuvinkit listataan. 


## Lukuvinkin poistaminen

Lukuvinkit poistetaan komennolla:
```
P
```

Lukuvinkkaaja poistaa lukuvinkin sen id:n perusteella. Lukuvinkin poistaminen poistaa lukuvinkin tietokannasta kokonaisuudessaan. Poistettua lukuvinkkiä ei voi palauttaa. Poistamisen jälkeen ohjelma kysyy uutta komentoa.


## Sovelluksen sulkeminen

Sovellus suljetaan komennolla
```
Q
```


