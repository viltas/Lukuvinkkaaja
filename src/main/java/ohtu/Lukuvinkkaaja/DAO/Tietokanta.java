package ohtu.Lukuvinkkaaja.DAO;

import java.sql.*;

public class Tietokanta {

    private String databaseAddress;

    public Tietokanta (String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

}