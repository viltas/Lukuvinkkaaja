package DAO;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.*;

import ohtu.Lukuvinkkaaja.DAO.LukuVinkkiDao;
import ohtu.Lukuvinkkaaja.DAO.Tietokanta;
import ohtu.Lukuvinkkaaja.domain.LukuVinkki;

public class LukuVinkkiDaoTest {

    Tietokanta tietokanta;
    LukuVinkkiDao lvdao;

    @Before
    public void setUp() throws SQLException {
        tietokanta = new Tietokanta("jdbc:sqlite:testikanta.db");
        lvdao = new LukuVinkkiDao(tietokanta);
        lvdao.luoTaulu();
    }

    @Test
    public void tallennusToimii() throws SQLException {
        LukuVinkki temp = new LukuVinkki("Otsikko", "nettisivu.fi");
        lvdao.tallenna(temp);
        ArrayList<LukuVinkki> lista = lvdao.listaaKaikki();
        assertTrue(lista.get(0).getOtsikko().equals(temp.getOtsikko()));
    }

    @Test
    public void listausToimiiUseammalla() throws SQLException {
        LukuVinkki temp = new LukuVinkki("Otsikko", "nettisivu.fi");
        LukuVinkki temp2 = new LukuVinkki("Toinen", "toinen.fi");
        lvdao.tallenna(temp);
        lvdao.tallenna(temp2);
        ArrayList<LukuVinkki> lista = lvdao.listaaKaikki();
        assertTrue(lista.get(0).getOtsikko().equals(temp.getOtsikko()));
        assertTrue(lista.get(1).getOtsikko().equals(temp2.getOtsikko()));
    }

    @Test
    public void lukemattomienListausToimii() throws SQLException {
        LukuVinkki temp = new LukuVinkki("Otsikko", "nettisivu.fi");
        LukuVinkki temp2 = new LukuVinkki("Toinen", "toinen.fi");
        lvdao.tallenna(temp);
        lvdao.tallenna(temp2);
        lvdao.paivita(1);
        ArrayList<LukuVinkki> lista = lvdao.listaaLukemattomat();
        assertTrue(lista.get(0).getOtsikko().equals(temp2.getOtsikko()));
    }

    @Test
    public void luettujenListausToimii() throws SQLException {
        LukuVinkki temp = new LukuVinkki("Otsikko", "nettisivu.fi");
        LukuVinkki temp2 = new LukuVinkki("Toinen", "toinen.fi");
        lvdao.tallenna(temp);
        lvdao.tallenna(temp2);
        lvdao.paivita(2);
        ArrayList<LukuVinkki> lista = lvdao.listaaLuetut();
        assertTrue(lista.get(0).getOtsikko().equals(temp2.getOtsikko()));
    }

    @Test
    public void luettujenListausEiPalautaMitaan() throws SQLException {
        LukuVinkki temp = new LukuVinkki("Otsikko", "nettisivu.fi");
        lvdao.tallenna(temp);
        ArrayList<LukuVinkki> lista = lvdao.listaaLuetut();
        assertTrue(lista.isEmpty());
    }

    @Test
    public void poistaminenToimii() throws SQLException {
        LukuVinkki temp = new LukuVinkki("Otsikko", "nettisivu.fi");
        LukuVinkki temp2 = new LukuVinkki("Toinen", "toinen.fi");
        lvdao.tallenna(temp);
        lvdao.tallenna(temp2);
        lvdao.poista(1);
        ArrayList<LukuVinkki> lista = lvdao.listaaKaikki();
        assertTrue(lista.get(0).getOtsikko().equals(temp2.getOtsikko()));
    }

    @Test
    public void taginAntaminenToimii() throws SQLException {
        LukuVinkki temp = new LukuVinkki("Otsikko", "nettisivu.fi");
        lvdao.tallenna(temp);
        String tagi = "kiinostava";
        lvdao.annaTagi(1, tagi);
        ArrayList<LukuVinkki> lista = lvdao.listaaKaikki();
        assertTrue(lista.get(0).getTagi().equals(tagi));
    }

    @Test
    public void parseExceptionKaikkienListauksessa() throws SQLException {
        Connection conn = tietokanta.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO lukuvinkki VALUES (?, ?, ?, ?, ?, ?, ?)");

        stmt.setString(2, "otsikko");
        stmt.setString(3, "nettisivu.fi");
        stmt.setString(4, "55");
        stmt.execute();
        stmt.close();
        lvdao.listaaKaikki();
    }
    
    @Test
    public void parseExceptionLukemattomienListauksessa() throws ParseException, SQLException {
        Connection conn = tietokanta.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO lukuvinkki VALUES (?, ?, ?, ?, ?, ?, ?)");

        stmt.setString(2, "otsikko");
        stmt.setString(3, "nettisivu.fi");
        stmt.setString(4, "55");
        stmt.setBoolean(5,false);
        stmt.execute();
        stmt.close();

        lvdao.listaaLukemattomat(); 


    }

    @Test 
    public void parseExceptionLuettujenListauksessa() throws SQLException {
        Connection conn = tietokanta.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO lukuvinkki VALUES (?, ?, ?, ?, ?, ?, ?)");

        stmt.setString(2, "otsikko");
        stmt.setString(3, "nettisivu.fi");
        stmt.setString(4, "55");
        stmt.setBoolean(5, true);
        stmt.execute();
        stmt.close();

        lvdao.listaaLuetut();
    }

    @After
    public void tearDown() {
        File f = new File("testikanta.db");
        f.delete();
    }


}