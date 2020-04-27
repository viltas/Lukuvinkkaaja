
package domain;

import ohtu.Lukuvinkkaaja.domain.LukuVinkki;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;


public class LukuVinkkiTest {

    LukuVinkki lukuVinkki;

    @Before
    public void setUp() {
        lukuVinkki = new LukuVinkki("otsikko","www.lukuvinkki.com");
    }
    
    @Test
    public void tallennaLukuVinkki() {
       
        assertEquals("otsikko",lukuVinkki.getOtsikko());
    }

    @Test
    public void palauttaaOikeanURLin() {
        assertTrue(lukuVinkki.getURL().equals("www.lukuvinkki.com"));
    }

    @Test
    public void paivamaaraOnOikein() {
        assertTrue(lukuVinkki.getLisatty().equals(LocalDate.now()));
    }


    @Test(expected = IllegalArgumentException.class)
    public void virheTyhjallaOtsikolla() {
        
       LukuVinkki lukuV = new LukuVinkki("","https://www.lv.fi/");

    }

    @Test(expected = IllegalArgumentException.class)
    public void virheNullOtsikolla() {
        
       LukuVinkki lukuV = new LukuVinkki(null,"https://www.lv.fi/");

    }
    
    @Test
    public void listaaKaikkiLukuvinkit() {
        LukuVinkki vinkki1 = new LukuVinkki("lukuvinkki1", "www.lukuvinkki1.com");
        LukuVinkki vinkki2 = new LukuVinkki("lukuvinkki2", "www.lukuvinkki2.com");
        
        
    }

    @Test
    public void otsikonSettaaminenToimii() {
        lukuVinkki.setOtsikko("uusiOtsikko");
        assertEquals("uusiOtsikko", lukuVinkki.getOtsikko());

    }

    @Test
    public void urlinSettaaminenToimii() {
        lukuVinkki.setURL("uusiURL");
        assertEquals("uusiURL", lukuVinkki.getURL());

    }


    @Test
    public void luetunSettaaminenToimii() {
        lukuVinkki.setLuettu(LocalDate.now());
        assertEquals(LocalDate.now(), lukuVinkki.getLuettu());

    }









}
