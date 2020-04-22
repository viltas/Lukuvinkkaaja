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

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import ohtu.Lukuvinkkaaja.UI.Komentorivi;
import ohtu.Lukuvinkkaaja.UI.KomentoriviIO;
import ohtu.Lukuvinkkaaja.domain.LukuVinkki;
import org.junit.After;


/**
 *
 * @author iilkka
 */
public class Stepdefs {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private PrintStream originalOut = System.out;
    private PrintStream originalErr = System.err;
    Komentorivi komentorivi;
    LukuVinkki lukuVinkki;
    LocalDate pvm;

    @Before
    public void setup() {
        lukuVinkki = new LukuVinkki("otsikko", "www.lukuvinkki.com");
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        pvm = LocalDate.now();
    }

    @Given("LukuVinkki is added")
    public void lukuVinkkiIsInitialized() throws SQLException {
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

//    @Given("ui is running")
//    public void uiIsRunning() throws SQLException {
//        komentorivi = new Komentorivi(new KomentoriviIO());
//    }

    @When("tips are listed")
    public void tipsAreListed() throws SQLException, ParseException {
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
