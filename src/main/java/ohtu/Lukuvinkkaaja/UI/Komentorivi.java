package ohtu.Lukuvinkkaaja.UI;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;


import ohtu.Lukuvinkkaaja.DAO.LukuVinkkiDao;
import ohtu.Lukuvinkkaaja.DAO.Tietokanta;
import ohtu.Lukuvinkkaaja.domain.LukuVinkki;


public class Komentorivi {

    private  IO io;
    private Connection connection;
    private  Tietokanta tietokanta;
    private  LukuVinkkiDao lvdao;

    public Komentorivi( IO io) throws SQLException {
        this.tietokanta = new Tietokanta("jdbc:sqlite:tietokanta.db");
        this.lvdao = new LukuVinkkiDao(tietokanta);
        this.io = io;

    }

    public void start() throws SQLException, ParseException {

        aloitusViesti();
        komentoListaa();

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

    public void komentoListaa() throws SQLException, ParseException {
         ArrayList<LukuVinkki> lista = lvdao.listaaKaikki();
        io.print("...");
        for (int i = 0; i < lista.size(); i++) {
            io.print(lista.get(i).toString() + "\n");
        }
        if (lista.isEmpty()) {
            io.print("Et ole vielä tallentanut lukuvinkkejä");
        }

        io.print("");
    }


    public void komentoTallenna() throws SQLException {
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

    public void tallennin( String otsikko, String linkki) throws SQLException {
        LukuVinkki temp = new LukuVinkki(otsikko, linkki);
        lvdao.tallenna(temp);
        //
        
    }

    public Object haeListalta( int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
