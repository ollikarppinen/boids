# PARVISIMULAATIO
Tämä ohjelma simuloi lintuparven liikettä ja kuvaa simulaatiota graafisesti. Lintujen liike simuloidaan jokaiselle linnulle erikseen noudattaen kolmea liikettä ohjaavaa sääntöä. Linnut välttävät toisiinsa törmäämista, pyrkivät kohti lähellä olevia lintuja, ja pyrkivät liikkumaan muiden lähellä olevien lintujen kanssa samaan suuntaan. Näitä sääntöjä noudattamalla saadaan aikaiseksi melko luonnollisen näköinen lintujen liike ja parveutuminen. Ohjelmassa on mahdollista säätä simulaation asetuksia simulaation kuluessa. Simulaation asetuksia säätämällä on mahdollista saada aikaiseksi useita erilaisia käyttäytymismalleja lintuparvelle.
Simulaatio pohjautuu Craig Reynoldsin artikkeliin ”Steering Behaviors For Autonomous Characters”, jossa Reynolds on kuvannut miten parvisimulaatio voidaan toteuttaa vektorilaskennalla ja liikkumismallilla.
Työ on toteututtu vaativan tasoisena. Pyrkimykseni oli täyttää kaikki tehtävänannon vaatimukset erityyppisiä alkutilanteita lukuunottamatta. Tehtävänannossa mainitut: graafinen käyttöliittymä, lintujen satunnainen liike simulaation alussa, simulaation sääntöjen painokertoimien muuttaminen ja mahdollisuus puuttua simulaation painokertoimiin simulaation kuluessa. Kaikki nämä ominaisuudet ovat ohjelmassa.
## KÄYTTÖ
Ohjelma toiminta on hyvin suoraviivaista. Ohjelma koostuu simulaation graafisesta esityksestä ja asetuspaneelista. Käyttäjä voi vaikuttaa simulaatioon ja sitä kautta epäsuorasti lintujen liikkeisiin muuttamalla asetuspaneelista asetuksia.
### ASETUKSET
* Weight: Muuttaa linnun massaa. Mitä pienempi massa sitä vaivattomammin lintu pystyy kääntymään, kiihdyttämään ja reagoimaan ympäristöönsä.
* Perception distance: Määrää etäisyyden, joka vaikuttaa siihen miten lintu löytää muut linnut, joita se pitää lähellä olevana ja siten vaikuttaa sen liikkeisiin. Ts. jos kaksi lintua ovat alle tämän etäisyyden päässä toisistaan, niin ne vaikuttavat toistensa liikkeisiin. Mitä suurempi tämä etäisyys on sitä suurempi on sen vaikutus simulaation raskauteen. Tällöin jokaisen linnun liikkeisiin vaikuttavien muiden lintujen määrä kasvaa.
* Surrounding influence: Määrää kuinka suuri lintua ohjaava voima voi enintään olla.
* Speed: Määrää linnun huippunopeuden.
* #: Määrää simuloitavien lintujen määrän. Tällä asetuksella on suuri vaikutus simulaation raskauteen.
* Separation: Painokerroin sääntövektorille, joka määrää kuinka voimakkaasti linnut pyrkivät välttää toisiaan ja toisiinsa törmäämistä.
* Cohesion: Painokerroin sääntövektorille, joka määrää kuinka voimakkaasti linnut pyrkivät parveutumaan, eli hakeutumaan toisien lintujen luokse.
* Alignment: Painokerroin sääntövektorille, joka määrää kuinka voimakkaasti linnut pyrkivät liikkumaan samansuuntaisesti toisten lähellä olevien lintujen kanssa.
* Pause: Pysäyttää simulaation, mikäli se on käynnissä, ja jatkaa sen suorittamista mikäli se oli pysäytetty.
* Restart: Poistaa nykyisen parven. Luo sen tilalle uuden samankokoisen parven. Lintujen sijainti ja liike on satunnainen. Mitkään asetukset eivät muutu.
* Random: Luo uuden parven jossa on satunnainen määrä lintuja. Lintujen sijainti ja liike on myös satunnainen. Kaikki simulaation arvot asetetaan myös satunnaiseen arvoon.
* Reset: Luo uuden parven, jossa linnut ovat satunnaisissa paikoissa ja satunnaisessa liikkessä. Kaikki simulaation arvot asetetaan oletusarvoihin. Ts. arvoihin, jotka ovat käytössä, kun ohjelma käynnistetään.

## Ohjelman rakenne
Ohjelma pyrkii noudattamaan model-view-controller-mallia. Joskin tämän ohjelman kohdalla käyttäjän vaikutus ohjelmaan onkin hyvin rajattua. Siten ohjelman rakenteessa ei ole selkeästi eroteltuna käyttäjän syötteiden ja graafisen esityksen kokonaisuuksia. Mielestäni tämä suunnitelumalli kuitenkin toteutuu tässä ohjelman rakenteessa. 
Visuaalisesta esityksestä vastaa SimulationPanel-luokka (view), joka piirtää siis piirtää lintuparven ruudulle käyttäen Simulation-luokan flockia, joka on siis tietorakenne joka pitää sisällään simulaation linnut. Edellä mainittu Simulaatio-luokka siis vastaa simulaatio loopin pyörittämisestä ja siten vastaa model-luokkaa. SettingsPanel-luokan voi taas katsoa hallitsevan käyttäjän syötteet. (controller)
Tämä suunnittelumalli tarjoaa ohjelmalle loogisen rakenteen, jossa tehtävät ovat jaettu ennalta-arvattavasti. Riippuvuussuhteet luokkien välillä ovat mielestäni loogisia, mikä parantaa ohjelman laajennettavuutta ja selkeyttä.
### Luokat
#### GUI
Ohjelman pääluokka, joka pitää sisällään pääikkunan. Pääikkuna pitää sisällään SimulationPanel-paneelin, joka vastaa simulaation graafisesta esityksestä, ja SettingsPanel eli asetus-paneelista. GUI:ssa on määritelty reaktiot asetus-paneelin nappeihin ja slidereihin. Niiden muuttuessa arvot muutetaan, joko Boid-kumppaniolioon, tai lintujen määrän tapauksessa simulaatio-luokkaan.
#### SettingsPanel
Asetuspaneeli, jossa siis näkyvät napit ja sliderit, joilla käyttäjä voi vaikuttaa simulaatioon. 
#### SimulationPanel
Vastaa simulaation visuaalisesta kuvauksesta. Sisältää drawBoid-metodin, joka siis piirtää annetun linnun polygonina. Polygonin piirtämisessä täytyy laskea jokaiselle linnulle polygonin kulmat ja tästä aiheutuu jokaista lintua kohden ylimääräistä laskemista.
Tämän lisäksi SimulationPanel pitää sisällään säikeen, joka suorittaa simulaatiota, eli lintujen paikkoja ja uusien paikkojen laskemista.
#### Simulation
Pitää sisällään tietorakenteen simulaatiosta.(flock) Tämän lisäksi täällä on tieto simulaation koosta ja metodi generateFlock(i: Int), jolla voi luoda halutun kokoisen satunnaisen parven.
Simulation-objektissa on  simulaatio-looppi, eli looppi joka vastaa siitä että simulaatio pysyy käynnissä. Simulaatio-looppi on run-metodin sisällä. Simulaatio-loopissa on toteutettu yksinkertainen päivitysrajoitin, joka rajoittaa simulaation päivityksen enintään yhteen kertaan 30 millisekunnissa. Tämän tarkoitus on tasata hieman ruudunpäivitystä.
#### Boid
Boid-luokan edustaja edustaa yksittäistä lintua. Simuloitava lintuparvi koostuu useista linnuista. Linnuilla on paikka-vektori, sekä liike-vektori.
Move-metodi palauttaa uuden linnun, joka kuvastaa tämän linnun liikettä suhteessa sen omaan parveen, eli simulation-luokan flock-kokoelmaan. Move-metodissa lasketään liikutettavalle linnulle sääntövektorit Reynoldsin artikkelin mukaisesti ja palautetaan lopuksi niihin pohjautuva lintu, jolla on päivitetty paikka- ja liike-vektori.
Move-metodin alussa linnulle lasketaan lähellä olevat linnut. Tämä tapahtuu nearbyBoids-arvossa. Tämä toteutus on todella raskas sillä jokainen lintu tarkastelee kaikki muut linnut ja vertaa etäisyyttä. Mikäli tämän ohjelman suorituskykyä haluaisi parantaa, niin aloittaisin tästä.
Boid-luokan kumppaniolio pitää sisällään suuren osan simulaation asetuksista. Sekä metodin, joka luo satunnaisen boidin.
#### Vector2D
Vektorilaskuja varten luotu luokka. Noudattaa pääosin normaaleita vektorilaskun laskusääntöjä. Tämä luokka pohjautuu Asteroids-tehtävän vektoriluokkaan.
https://greengoblin.cs.hut.fi/o1_s2014/course/projektit/annetut/Asteroids/src/asteroids/Vector2D.scala
Tähän on kuitenkin lisätty tarpeellisia ominaisuuksia, kuten vektorin normalisointi. Tästä luokasta löytyy myös hassuttelu kokeiluna fastInvSqrt-metodi, joka pohjautuu seuraavaan artikkeliin: http://en.wikipedia.org/wiki/Fast_inverse_square_root.  Tätä hyödynnetään mm. vektorin normalisoinnissa. Vaikutus ohjelman suorituskykyyn ei kuitenkaan ole merkittävä, koska tätä laskutoimistusta ei käytetä kovinkaan paljoa. Tämän vuoksi itse näen tämän metodin enemmänkin kuriositeettinä ja kokeiluna, kuin tarpeellisena suorituskykyä parantavana ratkaisuna.
### Funktionaalisuus
Eräs ohjelman kehitystä ohjanneista päätöksistä oli simulaation funktionaalinen toteutus. Kantavana ajatuksena oli luoda simulaatio funktionaalisesti, jotta rinnakkaisuutta saataisiin hyödynnettyä tehokkaasti. Tässä ohjelma mielestäni onnistuukin. Olen pyrkinyt käyttämään ohjelmassa immutable-tietorakenteita ja käyttämään sivuvaikutuksettomia funtioita. Tästä konkreettisin esimerkki on boid-luokan move-metodi, joka luo uuden linnun vanhan pohjalta.
Tämä rakenteellinen päätös näkyy selkeimmin boid- ja simulation luokissa. Funktionaalisen tyylin konkreettiset hyödyt mielestäni laajennettavuus, helppolukuisuus ja rinnaisuus. Rinnakkaisuutta on hyödynnetty Simulation-luokan parVector kokoelmassa. Tämä on siis kokoelma, joka hyödyntää rinnakkaisuutta ja siten parantaa ohjelman suorituskykyä moderneilla moniytimisillä prosessoreilla.
## Algoritmit
Suurin laskennallinen ongelma parvisimulaatiossa on, miten yksittäinen lintu laskee itselleen seuraavan sijainnin. Lintu noudattaa 3 sääntöä: törmäilyn välttämistä, parven kanssa samaa nopeutta lentämistä ja parven keskipistettä kohti pyrkimistä. Nämä kolme sääntöä antavat kukin oman nopeusvektorin linnulle ja summaamalla nämä vektorit saadaan tulokseksi linnun liike. Nämä algoritmit perustuvat lähdemateriaalissa, Reynoldsin artikkelissa kuvattuihin ratkaisuihin.
Alla selitettynä mielestäni oleellisimpien algoritmien toteutus. 
### Boyd-luokan algoritmit
#### Sijainti
1.	uusi nopeusvektori = vanha nopeusvektori + kolme säännöistä saatua vektoria
2.	uusi sijainti = vanha sijainti + uusi nopeusvektori
#### Naapuri lintujen löytäminen
1. 	Verrataan halutun linnun sijaintia jokaiseen toiseen lintuun
2. 	Mikäli etäisyys on haluttua pienempi lisätään lintu lähellä oleviin
Tämä on todella raskas tapa löytää lähellä olevat linnut. Koska jokainen lintu tekee tämän ja vertaa omaa sijaintiaan muihin lintuihin on tämän algoritmin raskaus lintujen määrä toiseen potenssiin. Tätä voisi parantaa esimerkiksi hajautus-taululla, jossa voisi suoraan valita tietyt linnut tietäen että ne varmasti ovat naapureita. Tällöin hajautus-taulu pitäisi luoda ainoastaan kertaalleen per simulaation päivitys ja verrattavien lintujen määrää saataisiin pienennettyä huomattavasti. En kuitenkaan päättänyt toteuttaa tätä ratkaisua, sillä mielestäni ohjelma ajaa asiansa tälläkin tavalla. Jos lähtisin kehittämään tätä ohjelmaa eteenpin aloittaisin tästä.
#### Parveutuminen
1.	Lasketaan läheisten lintujen sijaintien keskiarvo
2.	Jaetaan se muiden lintujen lukumäärällä
3.	Palautetaan tämän säännön vektori
#### Lintujen välttäminen
1.	Etsitään linnun lähellä olevat toiset linnut (käytetään aiemmin tehtyä kokoelmaa) 
2.	Lasketaan jokaiselle näistä karkoittava vektori suhteessa kohde lintuun
3.	Summataan nämä vektorit jollain vakiolla kerrottuna
4.	Palautetaan tämän säännön vektori
#### Yhdensuuntainen liike
1.	Etsitään linnut läheltä (käytetään aiemmin tehtyä kokoelmaa)
2.	Lasketaan näiden suunnan keskiarvo
3.	Palautetaan tämä vektori
#### Vector2D-luokassa käytettyjä algoritmeja
Tämä luokka noudattaa vektoreiden laskusääntöjä, jotka löytyvät mm. täältä: http://fi.wikipedia.org/wiki/Vektori.
Vektorin normalisointi
Vektori jaettuna pituudellaan
Pituuden käänteisfunktio
1 / (vektorin pituus^(1 / 2))
Vektorin typistys
Truncate eli vektorin typistys palauttaa annetun vektorin, jos sen pituus on alle annetun parametrin. Muuten palautetaan alkuperäisen suuntainen vektori, jonka pituus on muutettu annetun parametrin pituiseksi.
