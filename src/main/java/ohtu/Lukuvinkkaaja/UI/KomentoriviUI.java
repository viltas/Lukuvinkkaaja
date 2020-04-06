package ohtu.Lukuvinkkaaja.UI;

import java.util.ArrayList;
import java.util.Scanner;

import ohtu.Lukuvinkkaaja.domain.LukuVinkki;

public class KomentoriviUI {
    private Scanner lukija;
    
    
    public KomentoriviUI (Scanner lukija) {
        this.lukija = lukija;
    }
    
    public void start() {

        
        ArrayList<LukuVinkki> lista = new ArrayList<>();
        

        System.out.println("Tervetuloa lukuvinkkaajaan!");
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        System.out.println("---------------------------");
                    
        System.out.println("Tallenna uusi linkki: T");
        System.out.println("Listaa linkit: L");
        System.out.println("Lopeta: Q");
        System.out.println("---------------------------");
        System.out.println("");


        while (true) {
            System.out.println("komento: ");
            String komento = lukija.nextLine();

            if (komento.equalsIgnoreCase("Q")) {
                break;
            }

            if (komento.equalsIgnoreCase("T")) {
                System.out.println("Anna otsikko: ");
                String otsikko = lukija.nextLine();
                System.out.println("Anna URL");
                String linkki = lukija.nextLine();

                LukuVinkki vinkki = new LukuVinkki(otsikko, linkki);

                lista.add(vinkki);
                
                System.out.println("Lukuvinkki tallennettu!");
            }

            if (komento.equalsIgnoreCase("L")) {
                System.out.println("...");
                
                for(LukuVinkki lukuvinkki : lista) {
                    System.out.println(lukuvinkki);
                }
            }

        }

    }
}