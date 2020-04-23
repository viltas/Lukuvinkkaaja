package Cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import ohtu.Lukuvinkkaaja.DAO.Tietokanta;
import ohtu.Lukuvinkkaaja.UI.Komentorivi;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;

public class Stepdefs {
    Tietokanta tietokanta;
    Komentorivi komentorivi;
    IOStub io;
    String id;

    @Before
    public void setUp() throws SQLException {

    }
    
    @Given("Tietokanta on olemassa")
    public void tietokantaOnOlemassa() throws SQLException, ParseException {
        tietokanta = new Tietokanta("jdbc:sqlite:testikanta.db");
        komentorivi = new Komentorivi(new IOStub(), tietokanta);
        komentorivi.alustaTietokanta();
    }

    @When("Kayttaja lisaa lukuvinkin {string} ja linkin {string}")
    public void kayttajaLisaaLukuvinkinJaLinkin(String otsikko, String linkki) throws SQLException, ParseException {
        io = new IOStub("T", otsikko, linkki, "Q");
        komentorivi = new Komentorivi(io, tietokanta);
        komentorivi.start();
    }

    @Then("Syote hyvaksytaan")
    public void syoteHyvaksytaan() {
        boolean b = io.outputs.contains("Lukuvinkki tallennettu!\n\n");
        assertTrue(b);
    }
    
    @When("Lukuvinkit listataan")
    public void lukuvinkitListataan() throws SQLException, ParseException {
        io = new IOStub("L", "Q");
        komentorivi = new Komentorivi(io, tietokanta);
        komentorivi.start();
    }

    @Then("Lisaysaika on nakyvilla")
    public void lisaysaikaOnNakyvilla() {
        LocalDate pvm = LocalDate.now();
        boolean b = false;
        
        for (String s : io.outputs) {
            if (s.contains(pvm.toString())) {
                b = true;
            }
        }
        
        assertTrue(b);    
    }

    @Given("Ei tulostu {string}")
    public void eiTulostu(String string) {
        boolean b = false;
        
        for (String s : io.outputs) {
            if (s.contains(string)) {
                b = true;
            }
        }
        
        assertTrue(!b); 
    }

    @Then("Lukuvinkki {string} on lukuvinkkia {string} ylempana")
    public void lukuvinkkiOnLukuvinkkiaYlempana(String string, String string2) {
        int uusiRivi  = 0, vanhaRivi = 0;
        for (int i = 0; i < io.outputs.size(); i++) {
            if (io.outputs.get(i).contains(string)) {
                vanhaRivi = i;
            }
            if (io.outputs.get(i).contains(string2)) {
                uusiRivi = i;
            }
        }
        assertTrue(uusiRivi < vanhaRivi); 
    }

    
    @When("Ohjelma käynnistetään")
    public void ohjelmaKäynnistetään() throws SQLException, ParseException {
        io = new IOStub("Q");
        komentorivi = new Komentorivi(io, tietokanta);
        komentorivi.start();        
    }

    @When("Käyttäjä siirtyy lisäysnäkymään")
    public void käyttäjäSiirtyyLisäysnäkymään() throws SQLException, ParseException {
        io = new IOStub("T", "Jee", "", "Q");
        komentorivi = new Komentorivi(io, tietokanta);
        komentorivi.start();        
    }

    @Then("Tulostuu {string}")
    public void tulostuu(String string) {
        boolean b = false;
        
        for (String s : io.outputs) {
            if (s.contains(string)) {
                b = true;
            }
        }
        
        assertTrue(b); 
    }

    @Then("Molemmilla vinkeilla {string} on uniikki id")
    public void molemmillaVinkeillaOnUniikkiId(String string) {
        ArrayList<String> vinkit = new ArrayList<>();
        for (String s : io.outputs) {
            if (s.contains(string)) {
                vinkit.add(s);
            }
        }

        assertTrue(!vinkit.get(0).equals(vinkit.get(1)));
        
    }

    @When("Kayttaja merkka lukuvinkin {string} luetuksi")
    public void kayttajaMerkkaLukuvinkinLuetuksi(String string) throws SQLException, ParseException {
        for (String s : io.outputs) {
            if (s.contains(string)) {
                System.out.println(s);
                id = s.substring(0, s.indexOf(" "));
                System.out.println(id);
            }
        }
        
        io = new IOStub("M", id, "Q");
        komentorivi = new Komentorivi(io, tietokanta);
        komentorivi.start();      
    }

    @Then("Lukuvinkki {string} on luettu")
    public void lukuvinkkiOnLuettu(String string) {
        assertTrue(io.outputs.contains("Artikkeli " + id + " merkitty luetuksi!"));
    }
    

    




    
    @After
    public void restoreStreams() {
        File f = new File("testikanta.db");
        f.delete();
    }


}
