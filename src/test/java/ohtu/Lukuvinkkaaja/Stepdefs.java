/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.Lukuvinkkaaja;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.List;
import ohtu.Lukuvinkkaaja.domain.LukuVinkki;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author iilkka
 */
public class Stepdefs {
    LukuVinkki lukuVinkki;
    
    
    @Before
    public void setup () {
        lukuVinkki = new LukuVinkki("otsikko","www.lukuvinkki.com");
    }
    
    @Given("LukuVinkki is added")
    public void lukuVinkkiIsInitialized() {
        lukuVinkki = new LukuVinkki("otsikko", "www.otsikko.com");
    }
    
    @Then("heading is {string} and Url is {string}")
    public void headingIsAndUrlIs(String header, String url) {
       assertEquals(lukuVinkki.getOtsikko(), header);
       assertEquals(lukuVinkki.getURL(), url);
       
    }   
    

            
            
    
}
