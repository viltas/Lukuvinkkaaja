package ohtu.Lukuvinkkaaja.domain;

import java.time.LocalDate;

public class LukuVinkki {
    private int id;
    private String otsikko;
    private String URL;
    private boolean onkoluettu;
    private LocalDate lisatty;
    private LocalDate luettu;
    private String tagi;
    
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
    
    public String getTagi() {
        return tagi;
    }
    
    public void setTagi(String tagi) {
        this.tagi = tagi;
    }
   
    
    @Override
    public String toString() {
        String lukuteksti = "";
        if (this.onkoluettu) {
            lukuteksti = (", (luettu: " + this.luettu + ")");
        }
        
        String tagiTeksti = "";
        if(this.tagi != null) {
            tagiTeksti = (", (tagi(t): " + getTagi() + ")");
        }
        return this.id + " " + this.otsikko +" ("+ this.URL + ") " + "[lisätty:  "+ this.lisatty + lukuteksti +"] " + tagiTeksti;
    }

    public void setId(int id) {
        this.id = id;
    }

}
