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
        System.out.println("----------------");
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

            if (komento.equalsIgnoreCase("M")) {
                komentoMerkkaaLuetuksi();
            }
            
            if (komento.equalsIgnoreCase("P")) {
                komentoPoista();
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
        io.print("Merkkaa luetuksi: M");
        io.print("Poista lukuvinkki: P");
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

    private void komentoMerkkaaLuetuksi() throws SQLException {
        io.print("Anna luetun artikkelin id: ");
        try {
            int id = Integer.parseInt(io.nextString());
            lvdao.paivita(id);
            io.print("Artikkeli " + id + " merkitty luetuksi!");
        } catch (NumberFormatException e) {
            io.print("Anna kunnollinen id");
            return;
        }
        
    }
    
    private void komentoPoista() throws SQLException {
        io.print("Anna poistettavan lukuvinkin id: ");
        try {
            int id = Integer.parseInt(io.nextString());
            lvdao.poista(id);
            io.print("Lukuvinkki " + id + " poistettu.");
        } catch (NumberFormatException e) {
            io.print("Anna kunnollinen id");
            return;
        } 
        
    }

}
