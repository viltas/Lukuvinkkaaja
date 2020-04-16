package ohtu.Lukuvinkkaaja.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import java.util.*;
import java.sql.*;

import ohtu.Lukuvinkkaaja.domain.LukuVinkki;

public class LukuVinkkiDao implements Dao<LukuVinkki, Integer> {

    private Tietokanta tietokanta;

    public LukuVinkkiDao(Tietokanta tietokanta) {
        this.tietokanta = tietokanta;
    }

    @Override
    public LukuVinkki etsiYKsi(Integer key) throws SQLException {
        Connection conn = tietokanta.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Lukuvinkki WHERE id = ?");
        stmt.setInt(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        // LukuVinkki lv = new LukuVinkki(rs.getInt("id"),
        // rs.getString("otsikko"),rs.getString("URL"),rs.getDate("lisatty"),
        // rs.getBoolean("luettu"));

        stmt.close();
        rs.close();

        conn.close();

        //return lv;
        return null;
    }

    

    @Override
    public List<LukuVinkki> listaaKaikki() throws SQLException {
        List<LukuVinkki> lista = new ArrayList<>();

       try ( Connection conn = tietokanta.getConnection();
        ResultSet rs = conn.prepareStatement("SELECT * FROM Lukuvinkki").executeQuery()) {

        while(rs.next()) {
            lista.add(new LukuVinkki(rs.getString(1),rs.getString(2))); //tarvitsee muokkausta oikeaan muotoon
        }
    }
        return lista;
    }

    @Override
    public void tallenna(LukuVinkki object) throws SQLException {
        Connection conn = tietokanta.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Lukuvinkki (something) VALUES (?)"); //fix to the right form
        
        stmt.setString(1, object.getOtsikko());
        stmt.executeUpdate();

        stmt.close();
        conn.close();


    }

    @Override
    public void paivita(LukuVinkki object) throws SQLException {
        // TODO Auto-generated method stub
    }

    @Override
    public void poista(Integer key) throws SQLException {
        Connection conn = tietokanta.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Lukuvinkki WHERE id = ?");

        stmt.setInt(1, key);
        stmt.executeUpdate();

        stmt.close();
        conn.close();

    }



}