package ohtu.Lukuvinkkaaja.UI;

import java.util.ArrayList;

import ohtu.Lukuvinkkaaja.domain.LukuVinkki;

public class Komentorivi {
    private IO io;
    private ArrayList<LukuVinkki> lista;

    public Komentorivi(IO io) {
        this.io = io;
        this.lista = new ArrayList<>();
    }

    public void start() {

        aloitusViesti();

        while (true) {

            io.print("komento: ");
            String komento = io.nextString();

            if (komento.equalsIgnoreCase("Q")) {
                break;
            }

            if (komento.equalsIgnoreCase("T")) {
                komentoTallenna();
            }

            if (komento.equalsIgnoreCase("L")) {
                komentoListaa();
            }

        }

    }

    public void aloitusViesti() {
        io.print("Tervetuloa lukuvinkkaajaan!");
        io.print("---------------------------");
        io.print("---------------------------");
        io.print("---------------------------");

        io.print("Tallenna uusi lukuvinkki: T");
        io.print("Listaa lukuvinkit: L");
        io.print("Lopeta: Q");
        io.print("---------------------------");
        io.print("");
    }

    public void komentoListaa() {
        io.print("...");


        for (int i = 0; i < lista.size(); i++) {
            io.print(haeListalta(i).toString() + "\n");
        }

        io.print("");
    }

    public LukuVinkki haeListalta(int mones) {
        return lista.get(mones);
    }


    public void komentoTallenna() {
        io.print("Anna otsikko: ");
        String otsikko = io.nextString();

        if (otsikko.isEmpty()) {
            io.print("Otsikko on pakollinen");
            return;
        }

        io.print("Anna URL");
        String linkki = io.nextString();

        tallennin(otsikko, linkki);

        io.print("Lukuvinkki tallennettu!\n\n");
    }

    public void tallennin(String otsikko, String linkki) {
        LukuVinkki vinkki = new LukuVinkki(otsikko, linkki);
        lista.add(vinkki);
    }

    public ArrayList<LukuVinkki> getLista() {
        return this.lista;
    }
}