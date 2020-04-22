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
    public void listausKomentoToimii() throws SQLException, ParseException {
        IOStub io = new IOStub("T", "Otsikko","linkki.fi","T", "Toinen","toka.fi","L","Q");
        new Komentorivi(io, tietokanta).start();
        boolean ok = false;
        boolean ok2 = false;
        for (String s : io.outputs) {
            if (s.contains("Otsikko (linkki.fi) [lisätty:  "+ LocalDate.now())) {
                ok = true;
            }
            if (s.contains("Toinen (toka.fi) [lisätty:  "+ LocalDate.now())) {
                ok2 = true;
            }
        }
        
        assertEquals(true, ok);
        assertEquals(true, ok2);
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
    
    @After
    public void tearDown() {
        File f = new File("testikanta.db");
        f.delete();
    }


}
