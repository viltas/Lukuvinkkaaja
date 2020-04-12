package ohtu.Lukuvinkkaaja;

import ohtu.Lukuvinkkaaja.UI.Komentorivi;
import ohtu.Lukuvinkkaaja.UI.KomentoriviIO;
import java.sql.*;
import java.text.ParseException;


public class Main {

    public static void main(String[] args) throws SQLException, ParseException {


        Komentorivi komentorivi = new Komentorivi(new KomentoriviIO());
        komentorivi.start();
    }
}
