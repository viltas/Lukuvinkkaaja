package ohtu.Lukuvinkkaaja.DAO;

import java.sql.*;
import java.util.*;

public interface Dao<Obj, Key> {
    Obj etsiYKsi(Key key) throws SQLException;
    List<Obj> listaaKaikki() throws SQLException;
    void tallenna(Obj object) throws SQLException;
    void paivita(Obj object) throws SQLException;
    void poista(Key key) throws SQLException;
}
