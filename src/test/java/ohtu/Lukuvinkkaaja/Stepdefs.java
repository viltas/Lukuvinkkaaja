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
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import ohtu.Lukuvinkkaaja.UI.Komentorivi;
import ohtu.Lukuvinkkaaja.UI.KomentoriviIO;
import ohtu.Lukuvinkkaaja.domain.LukuVinkki;
import org.junit.After;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author iilkka
 */
public class Stepdefs {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    Komentorivi komentorivi;
    LukuVinkki lukuVinkki;
    LocalDate pvm;
    
    
    @Before
    public void setup () {
        lukuVinkki = new LukuVinkki("otsikko","www.lukuvinkki.com");
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        pvm = LocalDate.now();
    }
    
    @Given("LukuVinkki is added")
    public void lukuVinkkiIsInitialized() {
        lukuVinkki = new LukuVinkki("otsikko", "www.otsikko.com");
        if (komentorivi != null) {
            komentorivi.tallennin("otsikko", "www.otsikko.com");
        }
    }
    
    @Then("heading is {string} and Url is {string}")
    public void headingIsAndUrlIs(String header, String url) {
       assertEquals(lukuVinkki.getOtsikko(), header);
       assertEquals(lukuVinkki.getURL(), url);
       
    }

    @Given("ui is running")
    public void uiIsRunning() {
        komentorivi  = new Komentorivi(new KomentoriviIO());
    }
    
    @When("tips are listed")
    public void tipsAreListed() {
        komentorivi.komentoListaa();      
    }

    @Then("empty list is shown")
    public void emptyListIsShown() {
        assertEquals("...\nEt ole vielä tallentanut lukuvinkkejä\n\n", outContent.toString());
    }

    @Then("tip is listed")
    public void tipIsListed() {
        assertEquals("...\notsikko www.otsikko.com " + pvm + "\n\n\n", outContent.toString());
    }
    
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }


}
