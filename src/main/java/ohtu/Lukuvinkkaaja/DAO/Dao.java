package ohtu.Lukuvinkkaaja.DAO;

import java.sql.*;
import java.util.*;

public interface Dao<Obj, Key> {
    List<Obj> listaaKaikki() throws SQLException;
    List<Obj> listaaLukemattomat() throws SQLException;
    List<Obj> listaaLuetut() throws SQLException;
    void tallenna(Obj object) throws SQLException;
    void paivita(int id) throws SQLException;
    void poista(Key key) throws SQLException;
    void annaTagi(int id, String tagi) throws SQLException;
}
