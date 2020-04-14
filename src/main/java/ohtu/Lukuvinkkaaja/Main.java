/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.Lukuvinkkaaja;

import ohtu.Lukuvinkkaaja.UI.Komentorivi;
import ohtu.Lukuvinkkaaja.UI.KomentoriviIO;

/**
 *
 * @author iilkka
 */
public class Main {
    
    public static void main(String[] args) {
        Komentorivi komentorivi = new Komentorivi(new KomentoriviIO());
        komentorivi.start();
    }
}