package UI;

import java.sql.SQLException;
import java.text.ParseException;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ohtu.Lukuvinkkaaja.UI.*;


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
        assertEquals("Otsikko on pakollinen", io.outputs.get(14));

    }

    @Test
    public void tallenninOtsikollajaTyhjallaLinkilla() throws SQLException, ParseException{
        IOStub io = new IOStub("T", "Otsikko","","Q");
        new Komentorivi(io).start();
        assertEquals("Lukuvinkki tallennettu!\n\n", io.outputs.get(15));
    }

    @Test
    public void listaltaHakuToimii() throws SQLException {
        komentorivi.tallennin("otsikko", "linkki.fi");
        String expected = "otsikko" +" "+ "linkki.fi" +" "+ LocalDate.now();
        assertEquals(komentorivi.haeListalta(0).toString(), expected);
    }


    @Test
    public void listausKomentoToimii() throws SQLException, ParseException {
        IOStub io = new IOStub("T", "Otsikko","linkki.fi","T", "Toinen","toka.fi","L","Q");
        new Komentorivi(io).start();


        assertEquals("Otsikko" +" "+ "linkki.fi" +" "+ LocalDate.now() + "\n", io.outputs.get(22));
        assertEquals("Toinen" +" "+ "toka.fi" +" "+ LocalDate.now() + "\n", io.outputs.get(23));

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
