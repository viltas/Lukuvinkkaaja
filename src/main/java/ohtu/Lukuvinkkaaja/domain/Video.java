/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.Lukuvinkkaaja.domain;

/**
 *
 * @author iilkka
 */
public class Video {
    private String otsikko;
    private String URL;
    private String liittyvatKurssit;
    private String tyyppi;
    private String kommentti;
    
    public Video(String otsikko, String URL, String liittyvatKurssit, String tyyppi, String kommentti) {
        this.otsikko = otsikko;
        this.URL = URL;
        this.liittyvatKurssit = liittyvatKurssit;
        this.tyyppi = tyyppi;
        this.kommentti = kommentti;
    }
    
}
