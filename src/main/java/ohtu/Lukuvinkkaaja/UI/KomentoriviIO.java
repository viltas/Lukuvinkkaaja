package ohtu.Lukuvinkkaaja.UI;

import java.util.Scanner;

public class KomentoriviIO implements IO {
    private Scanner lukija;

    public KomentoriviIO() {
        lukija = new Scanner(System.in);
    }


	public String nextString() {
		return lukija.nextLine();
	}


	public void print(String s) {
		System.out.println(s);
		
	}

}