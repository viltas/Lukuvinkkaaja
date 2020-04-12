package ohtu.Lukuvinkkaaja.UI;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ohtu.Lukuvinkkaaja.domain.LukuVinkki;

public class Komentorivi {

    private IO io;
    private final Connection connection;

    public Komentorivi(IO io) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:tietokanta.db");
        this.io = io;
        
    }

    public void start() throws SQLException, ParseException {

        aloitusViesti();
        komentoListaa();

        while (true) {

            io.print("komento: ");
            String komento = io.nextString();

            if (komento.equalsIgnoreCase("Q")) {
                connection.close();
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
        io.print("...");
        System.out.println("Lukuvinkit: ");
        System.out.println("");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM lukuvinkki;");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        while (rs.next()) {
            int id = rs.getInt("id");
            String otsikko = rs.getString("otsikko");
            String osoite = rs.getString("url");
            java.util.Date lisatty = dateFormat.parse(rs.getString("lisatty"));
            String lisattyString = dateFormat.format(lisatty);

            System.out.println(" Otsikko: " + otsikko + "   Osoite: " + osoite + "   Lisatty: " + lisattyString);
        }
        if (lista.isEmpty()) {
            io.print("Et ole vielä tallentanut lukuvinkkejä");
        }

        statement.close();
        rs.close();

        io.print("");
    }

    //public LukuVinkki haeListalta(int mones) {
    //    return lista.get(mones);
    //}

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
        
    }

    public Object haeListalta(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
