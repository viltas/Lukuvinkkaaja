package Cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
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
    
    @When("Lukemattomat listataan")
    public void lukemattomatListataan() throws SQLException, ParseException {
        io = new IOStub("U", "Q");
        komentorivi = new Komentorivi(io, tietokanta);
        komentorivi.start();
    }
    
    
    @When("Luetut listataan")
    public void luetutListataan() throws SQLException, ParseException {
        io = new IOStub("R", "Q");
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

    @Then("Kaikilla vinkeillä on oma id")
    public void molemmillaVinkeillaOnUniikkiId() {
        ArrayList<String> idt = new ArrayList<>();
        for (String s : io.outputs) {
            if (s.contains("lisätty:")) {
                idt.add(s.substring(0, s.indexOf(" ")));
            }
        }
        Set<String> idSet = new HashSet<String>(idt);
        
        assertTrue(idt.size() == idSet.size());
        
    }

    @When("Kayttaja merkkaa lukuvinkin {string} luetuksi")
    public void kayttajaMerkkaaLukuvinkinLuetuksi(String string) throws SQLException, ParseException {
        for (String s : io.outputs) {
            if (s.contains(string)) {
                id = s.substring(0, s.indexOf(" "));
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
    
    @When("Kayttaja antaa lukuvinkille {string} tagin {string}")
    public void kayttajaAntaaLukuvinkilleTagin(String string, String string2) throws SQLException, ParseException {
        for(String s: io.outputs) {
            if(s.contains(string)) {
                id = s.substring(0, s.indexOf(" "));
            }
        }
        
        io = new IOStub("A", id, string2, "Q");
        komentorivi = new Komentorivi(io, tietokanta);
        komentorivi.start();
        
    }
    


    @After
    public void restoreStreams() {
        File f = new File("testikanta.db");
        f.delete();
    }


}
