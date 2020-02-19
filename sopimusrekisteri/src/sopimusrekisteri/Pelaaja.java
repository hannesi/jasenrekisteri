package sopimusrekisteri;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.Random;

/**
 * @author Hannes Koivusipilä
 * @version 19.2.2020
 *
 */
public class Pelaaja {
    
    private int     pid;            //Pelaaja-id
    private String  sukunimi        = "";
    private String  etunimi         = "";
    private String  syntymaaika     = "";
    private String  kansallisuus    = "";
    
    private static int seuraavaPid;
    
    
    /**palauttaa pelaaja-id:n
     * @return pid
     */
    public int getPid() {
        return pid;
    }
    
    
    /**
     * tulostetaan pelaajan tiedot
     * @param out tietovirta, johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%05d",  pid) + " " + sukunimi + ", " + etunimi);
        out.println(syntymaaika + ", " + kansallisuus);
    }
    
    
    /**
     * tulostetaan pelaajan tiedot
     * (tarvitaan tulevaisuudessa, kun tulostetaan tavaraa käyttöliittymän laatikoihin
     * @param os tietovirta, johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /** Antaa pelaajalle pelaaja-id:n (pid) ja kasvattaa seuraavaa rekisteröitävää pelaaja-id:tä yhdellä.
     * @return pid
     */
    public int rekisteroi() {
        pid = seuraavaPid++;
        return pid;
    }
    
    /**
     * Pääohjelmassa vain luokan testailua
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Pelaaja p = new Pelaaja();
        Pelaaja p2 = new Pelaaja();

        p.rekisteroi();
        p2.rekisteroi();
        
        p.tulosta(System.out);
        p.taytaPelaaja(); //temp
        p.tulosta(System.out);
        
        p2.tulosta(System.out);
        p2.taytaPelaaja(); //temp
        p2.tulosta(System.out);
        
    }


    /**
     * apumetodi luokan testaamiseen
     */
    public void taytaPelaaja() {
        sukunimi = "Doe";
        etunimi = "John";
        Random rand = new Random();
        syntymaaika = Integer.toString(rand.nextInt(9999));
        kansallisuus = "Yhdysvallat";
        
    }

}
