package UI;

import java.sql.SQLException;
import java.text.ParseException;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ohtu.Lukuvinkkaaja.UI.*;
import static org.junit.Assert.assertTrue;


public class KomentoriviTest {
    Komentorivi komentorivi; 

    @Before
    public void setUP() throws SQLException {
        komentorivi = new Komentorivi(new KomentoriviIO());
    }


    @Test
    public void tallenninToimii() {
        

    }

    @Test
    public void tallenninTyhjallaOtsikolla() throws SQLException, ParseException{
        IOStub io = new IOStub("T", "","Q");
        new Komentorivi(io).start();
        assertEquals("Otsikko on pakollinen", io.outputs.get(io.outputs.size()-2));

    }

    @Test
    public void tallenninOtsikollajaTyhjallaLinkilla() throws SQLException, ParseException{
        IOStub io = new IOStub("T", "Otsikko","","Q");
        new Komentorivi(io).start();
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
        new Komentorivi(io).start();
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
        new Komentorivi(io).start();
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
        new Komentorivi(io).start();
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
        new Komentorivi(io).start();
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
        new Komentorivi(io).start();
        boolean ok = false;
        for (String s : io.outputs) {
            if (s.contains("Anna kunnollinen id")) {
                ok = true;
            }    
        }
        assertTrue(ok);
    }

    @Test
    public void listanTyhjentaminenToimii() throws SQLException, ParseException {
        komentorivi.tyhjennaLista();
        IOStub io = new IOStub("L", "Q");
        new Komentorivi(io).start();
        boolean ok = false;
        for (String s : io.outputs) {
            if (s.contains("Et ole vielä tallentanut lukuvinkkejä")) {
                ok = true;
            }    
        }
        assertTrue(ok);
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
