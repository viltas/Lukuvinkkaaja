package UI;

import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import ohtu.Lukuvinkkaaja.DAO.Tietokanta;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import ohtu.Lukuvinkkaaja.UI.*;
import static org.junit.Assert.assertTrue;


public class KomentoriviTest {
    Komentorivi komentorivi; 
    Tietokanta tietokanta;

    @Before
    public void setUP() throws SQLException {
        tietokanta = new Tietokanta("jdbc:sqlite:testikanta.db");
        komentorivi = new Komentorivi(new KomentoriviIO(), tietokanta);
        komentorivi.alustaTietokanta();
    }


    @Test
    public void tallenninToimii() {
        ////todo

    }

    @Test
    public void tallenninTyhjallaOtsikolla() throws SQLException, ParseException{
        IOStub io = new IOStub("T", "","Q");
        new Komentorivi(io, tietokanta).start();
        assertEquals("Otsikko on pakollinen", io.outputs.get(io.outputs.size()-2));

    }

    @Test
    public void tallenninOtsikollajaTyhjallaLinkilla() throws SQLException, ParseException{
        IOStub io = new IOStub("T", "Otsikko","","Q");
        new Komentorivi(io, tietokanta).start();
        assertEquals("Lukuvinkki tallennettu!\n\n", io.outputs.get(io.outputs.size()-2));
    }

    //unsupported
//    @Test
//    public void listaltaHakuToimii() throws SQLException {
//        komentorivi.tallennin("otsikko", "linkki.fi");
//        String expected = "otsikko" +" "+ "linkki.fi" +" "+ LocalDate.now();
//        assertTrue(komentorivi.haeListalta(0).toString().contains(expected));
//    }


    @Test
    public void listausKomentoToimiiTyhjallaListalla() throws SQLException, ParseException {
        IOStub io = new IOStub("Q");
        new Komentorivi(io, tietokanta).start();
        boolean ok = false;
        for (String s : io.outputs) {

            if (s.contains("Et ole vielä tallentanut lukuvinkkejä")) {
                ok = true;
            }
        }
        
        assertEquals(true, ok);

    }
    
    
        @Test
    public void lukemattomienListausKomentoToimii() throws SQLException, ParseException {
        IOStub io = new IOStub("T", "Eka","eka.fi","T", "Toka","toka.fi","M","2", "U","Q");
        new Komentorivi(io, tietokanta).start();
        boolean lukematta = false;
        boolean luettu = false;
        for (String s : io.outputs) {
            if (s.contains("Eka (eka.fi) [lisätty:  "+ LocalDate.now())) {
                lukematta = true;
            }
            if (s.contains("Toka (toka.fi) [lisätty:  "+ LocalDate.now())) {
                luettu = true;
            }
        }
        
        assertEquals(true, lukematta);
        assertEquals(false, luettu);
    }
    
            @Test
    public void lukemattomienListausKomentoToimiiKunEiLukemattomia() throws SQLException, ParseException {
        IOStub io = new IOStub("T", "Eka","eka.fi", "M", "1", "T", "Toka","toka.fi","M","2", "U","Q");
        new Komentorivi(io, tietokanta).start();
        boolean ekaLukematta = true;
        boolean tokaLukematta = true;
        boolean ilmoitus = false;
        for (String s : io.outputs) {
            if (s.contains("Eka (eka.fi) [lisätty:  "+ LocalDate.now())) {
                ekaLukematta = false;
            }
            if (s.contains("Toka (toka.fi) [lisätty:  "+ LocalDate.now())) {
                tokaLukematta = false;
            }
            if (s.contains("Lukemattomia lukuvinkkejä ei löytynyt")) {
                ilmoitus = true;
            }
        }
        
        assertEquals(true, ekaLukematta);
        assertEquals(true, tokaLukematta);
        assertEquals(true, ilmoitus);
    }
    
    @Test
    public void luettujenListausKomentoToimii() throws SQLException, ParseException {
        IOStub io = new IOStub("T", "Eka","eka.fi","T", "Toka","toka.fi","M","2", "R","Q");
        new Komentorivi(io, tietokanta).start();
        boolean lukematta = false;
        boolean luettu = false;
        for (String s : io.outputs) {
            if (s.contains("Eka (eka.fi) [lisätty:  "+ LocalDate.now())) {
                lukematta = true;
            }
            if (s.contains("Toka (toka.fi) [lisätty:  "+ LocalDate.now())) {
                luettu = true;
            }
        }
        
        assertEquals(false, lukematta);
        assertEquals(true, luettu);
    }
    
    @Test
    public void luettujenListausKomentoToimiiKunEiLuettuja() throws SQLException, ParseException {
        IOStub io = new IOStub("T", "Eka","eka.fi","T", "Toka","toka.fi", "R","Q");
        new Komentorivi(io, tietokanta).start();
        boolean ekaLukematta = true;
        boolean tokaLukematta = true;
        boolean ilmoitus = false;
        for (String s : io.outputs) {
            if (s.contains("Eka (eka.fi) [lisätty:  "+ LocalDate.now())) {
                ekaLukematta = false;
            }
            if (s.contains("Toka (toka.fi) [lisätty:  "+ LocalDate.now())) {
                tokaLukematta = false;
            }
            if (s.contains("Luettuja lukuvinkkejä ei löytynyt")) {
                ilmoitus = true;
            }
        }
        
        assertEquals(true, ekaLukematta);
        assertEquals(true, tokaLukematta);
        assertEquals(true, ilmoitus);
    }

    @Test
    public void poistaminenToimii() throws SQLException, ParseException {
        IOStub io = new IOStub("T", "Otsikko","linkki.fi","P","1","Q");
        new Komentorivi(io, tietokanta).start();
        boolean ok = false;
        for (String s : io.outputs) {
            if (s.contains("Lukuvinkki 1 poistettu.")) {
                ok = true;
            }    
        }
        assertTrue(ok);
    }

    @Test
    public void luetuksiMerkkaaminenToimii() throws SQLException, ParseException {
        IOStub io = new IOStub("T", "Otsikko","linkki.fi","M","1","Q");
        new Komentorivi(io, tietokanta).start();
        boolean ok = false;
        for (String s : io.outputs) {
            if (s.contains("Artikkeli 1 merkitty luetuksi!")) {
                ok = true;
            }    
        }
        assertTrue(ok);
    }

    @Test
    public void luetuksiMerkkaaminenEiToimiHuonollaInputilla() throws SQLException, ParseException {
        IOStub io = new IOStub("T", "Otsikko","linkki.fi","M","yksi","Q");
        new Komentorivi(io, tietokanta).start();
        boolean ok = false;
        for (String s : io.outputs) {
            if (s.contains("Anna kunnollinen id")) {
                ok = true;
            }    
        }
        assertTrue(ok);
    }

    @Test
    public void poistaminenEiToimiHuonollaInputilla() throws SQLException, ParseException {
        IOStub io = new IOStub("T", "Otsikko","linkki.fi","P","yksi","Q");
        new Komentorivi(io, tietokanta).start();
        boolean ok = false;
        for (String s : io.outputs) {
            if (s.contains("Anna kunnollinen id")) {
                ok = true;
            }    
        }
        assertTrue(ok);
    }
    
    @Test
    public void annaLukuvinkilleTagiToimii() throws SQLException, ParseException {
        IOStub io = new IOStub("T", "Otsikko", "linkki.fi","A","1","tagi","Q");
        new Komentorivi(io, tietokanta).start();
        boolean ok = false;
        for(String s : io.outputs) {
        if(s.contains("Tagi lisätty!")) {
            ok = true;
        }
        
        }
        
        assertTrue(ok);
    }
    
    @Test
    public void annaLukuvinkilleTagiEiToimiHuonollaInputilla() throws SQLException, ParseException {
        IOStub io = new IOStub("T","Otsikko","linkki.fi","A","yksi","tagi","Q");
        new Komentorivi(io, tietokanta).start();
        boolean ok = false;
        for(String s : io.outputs) {
            if(s.contains("Anna kunnollinen id")) {
                ok = true;
            }
        }
        
        assertTrue(ok);
        
    }


    @Test
    public void tulostajaToimii() {
    }

//      @Test
//    public void listanTyhjentaminenToimii() throws SQLException, ParseException {
//        IOStub io = new IOStub("L", "Q");
//        new Komentorivi(io, tietokanta).start();
//        komentorivi.tyhjennaLista();
//        boolean ok = false;
//        for (String s : io.outputs) {
//            if (s.contains("Et ole vielä tallentanut lukuvinkkejä")) {
//                ok = true;
//            }    
//        }
//        assertTrue(ok);
//    }


    
        
    @After
    public void tearDown() {
        File f = new File("testikanta.db");
        f.delete();
    }


}


class IOStub implements IO {

    String[] inputs;
    int mones;
    ArrayList<String> outputs;

    public IOStub(String... inputs) {
        this.inputs = inputs;
        this.outputs = new ArrayList<String>();
    }

    public String nextString() {
        return inputs[mones++];
    }

    public void print(String m) {
        outputs.add(m);
    }
}
