/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.Lukuvinkkaaja;

/**
 *
 * @author iilkka
 */
public class Podcast {
    private String author;
    private String nimi;
    private String otsikko;
    private String kuvaus;
    private String tyyppi;
    private String tagit;
    private String liittyvatKurssit;
    
    public Podcast(String author, String nimi, String otsikko, String kuvaus, String tyyppi, String tagit, String liittyvatKurssit) {
        this.author = author;
        this.nimi = nimi;
        this.otsikko = otsikko;
        this.kuvaus = kuvaus;
        this.tyyppi = tyyppi;
        this.tagit = tagit;
        this.liittyvatKurssit = liittyvatKurssit;
    }
  
    
}
