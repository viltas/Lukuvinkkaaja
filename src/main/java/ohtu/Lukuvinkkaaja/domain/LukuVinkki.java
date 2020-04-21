package ohtu.Lukuvinkkaaja.domain;

import java.time.LocalDate;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author iilkka
 */
public class LukuVinkki {
    private int id;
    private String otsikko;
    private String URL;
    private boolean onkoluettu;
    private LocalDate lisatty;
    private LocalDate luettu;
    
    public LukuVinkki(String otsikko, String URL) {
        if (otsikko == null || otsikko.isEmpty()) {
            throw new IllegalArgumentException("Otsikko on pakollinen.");
        }
        LocalDate pvm = LocalDate.now();
        this.otsikko = otsikko;
        this.URL = URL;
        this.lisatty = pvm;
       
    }
    
    public String getOtsikko() {
        return this.otsikko;
    }
    
    public String getURL() {
        return this.URL;
    }
    
    public LocalDate getLisatty() {
        return this.lisatty;
    }

    public void setLisatty(LocalDate date) {
        this.lisatty = date;
    }

    public int getId() {
        return id;
    }

    public boolean isOnkoluettu() {
        return onkoluettu;
    }

    public LocalDate getLuettu() {
        return luettu;
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setOnkoluettu(boolean onkoluettu) {
        this.onkoluettu = onkoluettu;
    }

    public void setLuettu(LocalDate luettu) {
        this.luettu = luettu;
    }
    
    
    
    public LukuVinkki(Kirja kirja) {
     
    }
    
    public LukuVinkki(Podcast podcast) {
        
    }
    
    public LukuVinkki(Video video) {
        
    }
    
    @Override
    public String toString() {
        String lukuteksti = "";
        if (this.onkoluettu) {
            lukuteksti = (", luettu: " + this.luettu);
        }
        return this.id + " " + this.otsikko +" "+ this.URL +" "+ this.lisatty + lukuteksti;
    }

    public void setId(int id) {
        this.id = id;
    }

}
