package UI;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ohtu.Lukuvinkkaaja.UI.*;


public class KomentoriviTest {
    Komentorivi komentorivi; 

    @Before
    public void setUP() {
        komentorivi = new Komentorivi(new KomentoriviIO());
    }


    @Test
    public void tallenninToimii() {
        komentorivi.tallennin("otsikko", "linkki.fi");
        String expected = "otsikko" +" "+ "linkki.fi" +" "+ LocalDate.now();
        String l = komentorivi.getLista().get(0).toString();
        assertEquals(l, expected);

    }

    @Test
    public void tallenninTyhjallaOtsikolla(){
        IOStub io = new IOStub("T", "","Q");
        new Komentorivi(io).start();
        assertEquals("Otsikko on pakollinen", io.outputs.get(11));

    }

    @Test
    public void tallenninOtsikollajaTyhjallaLinkilla(){
        IOStub io = new IOStub("T", "Otsikko","","Q");
        new Komentorivi(io).start();
        assertEquals("Lukuvinkki tallennettu!\n\n", io.outputs.get(12));
    }

    @Test
    public void listaltaHakuToimii() {
        komentorivi.tallennin("otsikko", "linkki.fi");
        String expected = "otsikko" +" "+ "linkki.fi" +" "+ LocalDate.now();
        assertEquals(komentorivi.haeListalta(0).toString(), expected);
    }


    @Test
    public void listausKomentoToimii() {
        IOStub io = new IOStub("T", "Otsikko","linkki.fi","T", "Toinen","toka.fi","L","Q");
        new Komentorivi(io).start();


        assertEquals("Otsikko" +" "+ "linkki.fi" +" "+ LocalDate.now() + "\n", io.outputs.get(19));
        assertEquals("Toinen" +" "+ "toka.fi" +" "+ LocalDate.now() + "\n", io.outputs.get(20));

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
