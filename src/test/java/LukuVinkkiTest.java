/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ohtu.Lukuvinkkaaja.domain.LukuVinkki;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

}
