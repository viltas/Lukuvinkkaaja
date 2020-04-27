package ohtu.Lukuvinkkaaja.UI;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

import ohtu.Lukuvinkkaaja.DAO.LukuVinkkiDao;
import ohtu.Lukuvinkkaaja.DAO.Tietokanta;
import ohtu.Lukuvinkkaaja.domain.LukuVinkki;

public class Komentorivi {

    private IO io;
    private Tietokanta tietokanta;
    private LukuVinkkiDao lvdao;

    public Komentorivi(IO io, Tietokanta tietokanta) throws SQLException {
        this.tietokanta = tietokanta;
        this.lvdao = new LukuVinkkiDao(tietokanta);
        this.io = io;

    }

    public void start() throws SQLException, ParseException {
        lvdao.luoTaulu();
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

            if (komento.equalsIgnoreCase("U")) {
                komentoListaaLukemattomat();
            }

            if (komento.equalsIgnoreCase("R")) {
                komentoListaaLuetut();
            }

            if (komento.equalsIgnoreCase("M")) {
                komentoMerkkaaLuetuksi();
            }

            if (komento.equalsIgnoreCase("P")) {
                komentoPoista();
            }

            if (komento.equalsIgnoreCase("A")) {
                komentoAnnaTagi();
            }

        }

    }

    public void aloitusViesti() {
        io.print("Tervetuloa lukuvinkkaajaan!");
        io.print("---------------------------");
        io.print("---------------------------");
        io.print("---------------------------");

        io.print("Tallenna uusi lukuvinkki: T");
        io.print("Listaa kaikki lukuvinkit: L");
        io.print("Listaa lukemattomat: U");
        io.print("Listaa luetut: R");
        io.print("Merkkaa luetuksi: M");
        io.print("Poista lukuvinkki: P");
        io.print("Anna lukuvinkille tagi: A");
        io.print("Lopeta: Q");
        io.print("---------------------------");
        io.print("");
    }

    public void komentoListaa() throws SQLException, ParseException {
        ArrayList<LukuVinkki> lista = lvdao.listaaKaikki();
        io.print("...");
        tulostaja(lista);
        if (lista.isEmpty()) {
            io.print("Et ole vielä tallentanut lukuvinkkejä");
        }

        io.print("");
    }

    public void komentoListaaLukemattomat() throws SQLException, ParseException {
        ArrayList<LukuVinkki> lista = lvdao.listaaLukemattomat();
        io.print("...");
        for (int i = 0; i < lista.size(); i++) {
            io.print(lista.get(i).toString() + "\n");
        }
        if (lista.isEmpty()) {
            io.print("Lukemattomia lukuvinkkejä ei löytynyt");
        }

        io.print("");
    }

    public void komentoListaaLuetut() throws SQLException, ParseException {
        ArrayList<LukuVinkki> lista = lvdao.listaaLuetut();
        io.print("...");
        for (int i = 0; i < lista.size(); i++) {
            io.print(lista.get(i).toString() + "\n");
        }
        if (lista.isEmpty()) {
            io.print("Luettuja lukuvinkkejä ei löytynyt");
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

    public void tallennin(String otsikko, String linkki) throws SQLException {
        LukuVinkki temp = new LukuVinkki(otsikko, linkki);
        lvdao.tallenna(temp);

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

    private void komentoAnnaTagi() throws SQLException {
        io.print("Anna lukuvinkin id: ");
        try {
            int id = Integer.parseInt(io.nextString());

            io.print("Kirjoita tagi (jos annat useamman, erota tagit pilkulla): ");
            String tagi = io.nextString();
            lvdao.annaTagi(id, tagi);

            io.print("Tagi lisätty!");

        } catch (NumberFormatException e) {
            io.print("Anna kunnollinen id");
            return;
        }
    }

    public void tyhjennaLista() throws SQLException {
        ArrayList<LukuVinkki> lista = lvdao.listaaKaikki();
        for (LukuVinkki lukuVinkki : lista) {
            lvdao.poista(lukuVinkki.getId());
        }
    }

    public void alustaTietokanta() throws SQLException {
        lvdao.luoTaulu();
    }

    public void tulostaja(ArrayList<LukuVinkki> lista) {

        for (LukuVinkki lv : lista) {
            io.print("----------");
            io.print("ID: " + lv.getId());
            io.print("Otsikko: " + lv.getOtsikko());
            if (!(lv.getURL().isEmpty())) {
                io.print("Url: " + lv.getURL());
            }

            if (lv.isOnkoluettu()) {
                io.print("Luettu: " + lv.getLuettu());
            }

            io.print("Lisätty: " + lv.getLisatty());

            if (lv.getTagi() != null) {
                io.print("Tagit: " + lv.getTagi());
            }
            io.print("----------");
            io.print("\n");

        }

    }
}
