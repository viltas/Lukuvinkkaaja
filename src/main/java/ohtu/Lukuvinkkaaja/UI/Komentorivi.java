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
                tallenna();
            }

            if (komento.equalsIgnoreCase("L")) {
                listaa();
            }

        }

    }

    public void aloitusViesti() {
        System.out.println("Tervetuloa lukuvinkkaajaan!");
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        System.out.println("---------------------------");

        System.out.println("Tallenna uusi linkki: T");
        System.out.println("Listaa linkit: L");
        System.out.println("Lopeta: Q");
        System.out.println("---------------------------");
        System.out.println("");
    }

    public void listaa() {
        System.out.println("...");

        for (LukuVinkki lukuvinkki : lista) {
            System.out.println(lukuvinkki);
            System.out.println("");
        }

        System.out.println("");
    }


    public void tallenna() {
        System.out.println("Anna otsikko: ");
        String otsikko = io.nextString();
        System.out.println("Anna URL");
        String linkki = io.nextString();

        LukuVinkki vinkki = new LukuVinkki(otsikko, linkki);

        lista.add(vinkki);

        System.out.println("Lukuvinkki tallennettu!");
        System.out.println("");
        System.out.println("");
    }
}