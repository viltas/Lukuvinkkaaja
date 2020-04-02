/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ohtu.Lukuvinkkaaja.domain.LukuVinkki;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author iilkka
 */
public class LukuVinkkiTest {
    
    LukuVinkki lukuVinkki;
    
    @BeforeAll
    public void setUp() {
        lukuVinkki = new LukuVinkki("otsikko","www.lukuvinkki.com");
    }
    
    @Test
    public void tallennaLukuVinkki() {
       
        assertEquals("otsikko",lukuVinkki.getOtsikko());
    }

}
