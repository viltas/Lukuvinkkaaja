package ohtu.Lukuvinkkaaja.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
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

        // return lv;
        return null;
    }

    @Override
    public ArrayList<LukuVinkki> listaaKaikki() throws SQLException {
        ArrayList<LukuVinkki> lista = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try (Connection conn = tietokanta.getConnection();
                ResultSet rs = conn.prepareStatement("SELECT * FROM Lukuvinkki ORDER BY lisatty DESC").executeQuery()) {

            while (rs.next()) {
                java.util.Date lisatty = dateFormat.parse(rs.getString("lisatty"));

                int id = rs.getInt("id");
                String otsikko = rs.getString("otsikko");
                String osoite = rs.getString("url");
                LukuVinkki temp = new LukuVinkki(otsikko, osoite);
                temp.setId(id);
                temp.setOnkoluettu(rs.getBoolean("luettu"));
                temp.setLisatty(lisatty.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                if (temp.isOnkoluettu()) {
                    java.util.Date luettu = dateFormat.parse(rs.getString("luettu"));    
                    temp.setLuettu(luettu.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                }
                lista.add(temp); // tarvitsee muokkausta oikeaan muotoon
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void tallenna(LukuVinkki lukuvinkki) throws SQLException {
        Connection conn = tietokanta.getConnection();
        java.util.Date paivays = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strPaivays = dateFormat.format(paivays);
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO lukuvinkki VALUES (?, ?, ?, ?, ?, ?)");

        stmt.setString(2, lukuvinkki.getOtsikko());
        stmt.setString(3, lukuvinkki.getURL());
        stmt.setString(4, strPaivays);
        stmt.setString(5, "0");

        stmt.execute();

        stmt.close();


    }

    @Override
    public void paivita(int id) throws SQLException {
        Connection conn = tietokanta.getConnection();
        java.util.Date paivays = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strPaivays = dateFormat.format(paivays);
        PreparedStatement stmt = conn.prepareStatement("UPDATE lukuvinkki SET onkoluettu = ?, luettu = ?  WHERE id = ?");
        
        stmt.setBoolean(1, true);
        stmt.setString(2, strPaivays);
        stmt.setInt(3, id);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
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

    public void luoTaulu() throws SQLException {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS Lukuvinkki" 
        + "  (ID              Integer,"
        + "  Otsikko         Varchar(60),"
        + "  Url             Varchar(60),"
        + "  lisatty         DATE,"   
        + "  onkoluettu      Boolean,"     
        + "  luettu          DATE);";     
        
        Connection conn = tietokanta.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sqlCreate);
        stmt.execute();

    }
}