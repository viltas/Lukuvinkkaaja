package ohtu.Lukuvinkkaaja;

import ohtu.Lukuvinkkaaja.UI.Komentorivi;
import ohtu.Lukuvinkkaaja.UI.KomentoriviIO;
import java.sql.*;
import java.text.ParseException;
import ohtu.Lukuvinkkaaja.DAO.Tietokanta;


public class Main {

    public static void main(String[] args) throws SQLException, ParseException {
        Komentorivi komentorivi = new Komentorivi(new KomentoriviIO(), new Tietokanta("jdbc:sqlite:tietokanta.db"));
        komentorivi.start();
    }
}
