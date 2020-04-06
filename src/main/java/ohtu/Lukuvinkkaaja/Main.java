/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.Lukuvinkkaaja;
import java.util.Scanner;
import ohtu.Lukuvinkkaaja.UI.KomentoriviUI;

/**
 *
 * @author iilkka
 */
public class Main {
    
    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);
        KomentoriviUI komentoriviUI = new KomentoriviUI(lukija);
        komentoriviUI.start();
    }
}