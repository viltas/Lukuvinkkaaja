package ohtu.Lukuvinkkaaja.UI;

import java.util.ArrayList;
import java.util.Scanner;

import ohtu.Lukuvinkkaaja.domain.LukuVinkki;

public class KomentoriviUI {

    public void start() {

        Scanner lukija = new Scanner(System.in);
        ArrayList<LukuVinkki> lista = new ArrayList<>();

        System.out.println("Tervetuloa lukuvinkkaajaan!");
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        System.out.println("---------------------------");

        while (true) {

            System.out.println("Tallenna uusi linkki: T");
            System.out.println("Listaa linkit: L");
            System.out.println("Lopeta: Q");
            System.out.println("---------------------------");
            System.out.println("");

            if (lukija.nextLine().equals("Q")) {
                break;
            }

            if (lukija.nextLine().equalsIgnoreCase("T")) {
                System.out.println("Anna otsikko: ");
                String otsikko = lukija.nextLine();
                System.out.println("Anna URL");
                String linkki = lukija.nextLine();

                LukuVinkki vinkki = new LukuVinkki(otsikko, linkki);

                lista.add(vinkki);
            }

            if (lukija.nextLine().equals("L")) {
                System.out.println("...");
                System.out.println(lista);
            }

        }

    }
}