/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import ohtu.Lukuvinkkaaja.domain.LukuVinkki;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author iilkka
 */
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
        assertTrue(lukuVinkki.getDate().equals(LocalDate.now()));
    }


    @Test(expected = IllegalArgumentException.class)
    public void virheTyhjallaOtsikolla() {
        
       LukuVinkki lukuV = new LukuVinkki("","https://www.lv.fi/");

    }

    @Test(expected = IllegalArgumentException.class)
    public void virheNullOtsikolla() {
        
       LukuVinkki lukuV = new LukuVinkki(null,"https://www.lv.fi/");

    }







}
