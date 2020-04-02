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
public class Kirja {
    private String kirjoittaja;
    private String otsikko;
    private String tyyppi;
    private int ISBN;
    private String tagit;
    private String liittyvatKurssit;
    
    
    public Kirja (String kirjoittaja, String otsikko, String tyyppi, int ISBN, String tagit, String liittyvatKurssit) {
                
        this.kirjoittaja = kirjoittaja;
        this.tyyppi = tyyppi;
        this.ISBN = ISBN;
        this.tagit = tagit;
        this.liittyvatKurssit = liittyvatKurssit;

    }
}
