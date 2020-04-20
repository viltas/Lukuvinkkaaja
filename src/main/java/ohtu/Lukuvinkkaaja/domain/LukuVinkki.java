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

    private String otsikko;
    private String URL;
    private boolean luettu;
    private LocalDate lisatty;
    
    
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
    
    public LocalDate getDate() {
        return this.lisatty;
    }

    public void setDate(LocalDate date) {
        this.lisatty = date;
    }
    
    public LukuVinkki(Kirja kirja) {
     
    }
    
    public LukuVinkki(Podcast podcast) {
        
    }
    
    public LukuVinkki(Video video) {
        
    }
    
    @Override
    public String toString() {
        
        return this.otsikko +" "+ this.URL +" "+ this.lisatty;
    }

}
