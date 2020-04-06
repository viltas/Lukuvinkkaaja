/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.Lukuvinkkaaja;

import java.util.List;
import ohtu.Lukuvinkkaaja.domain.LukuVinkki;
import org.junit.Before;


/**
 *
 * @author iilkka
 */
public class Stepdefs {
    LukuVinkki lukuVinkki;
    List<String> inputLines;
    
    
    @Before
    public void setup () {
        lukuVinkki = new LukuVinkki("otsikko","www.lukuvinkki.com");
    }
    

            
            
    
}
