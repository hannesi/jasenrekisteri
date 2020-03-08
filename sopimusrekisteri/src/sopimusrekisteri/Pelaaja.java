package sopimusrekisteri;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Random;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi: Pelaaja                               | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   |
 * |                                                    |                   |
 * | - tietää pelaajan kentät                           |                   |
 * | - osaa tarkistaa tietyn kentät oikeellisuuden      |                   |
 * |   (ei numeroita nimissä, syntymäaika jne)          |                   |
 * | - osaa muuttaa merkkijonon pelaajan tiedoiksi      |                   |
 * |   (1|Aho|Sebastian|01.01.1900|Suomi)               |                   |
 * | - osaa laittaa merkkijonon tietyn kentän tiedoiksi |                   |
 * | - osaa palauttaa tietyn kentän tiedot merkkijonona |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |-------------------------------------------------------------------------
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
     * @return pelaajan pid
     */
    public int getPid() {
        return pid;
    }
    

    /**palauttaa pelaajan nimen muodossa "Sukunimi, Etunimi"
     * @return pelaajan nimi
     */
    public String getNimi() {
        return sukunimi + ", " + etunimi;
    }
    
    /**
     * palauttaa pelaajan sukunimen
     * @return pelaajan sukunimi
     */
    public String getSukunimi() {
        return sukunimi;
    }
    
    /**
     * palauttaa pelaajan etunimen
     * @return pelaajan etunimi
     */
    public String getEtunimi() {
        return etunimi;
    }
    
    
    /**palauttaa pelaajan syntymäajan
     * @return pelaajan syntymäaika
     */
    public String getSyntymaaika() {
        return syntymaaika;
    }
    
    /**palauttaa pelaajan kansallisuuden
     * @return pelaajan kansallisuus
     */
    public String getKansallisuus() {
        return kansallisuus;
    }

    
    
    /**korvaa pelaajan tiedot
     * @param sukunimi sukunimi
     * @param etunimi etunimi
     * @param syntymaaika syntymäaika
     * @param kansallisuus kansallisuus
     */
    @SuppressWarnings("hiding")
    public void muokkaa(String sukunimi, String etunimi, String syntymaaika, String kansallisuus) {
        this.sukunimi = sukunimi;
        this.etunimi = etunimi;
        this.syntymaaika = syntymaaika;
        this.kansallisuus = kansallisuus;
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
    
    
    /** 
     * Antaa pelaajalle pelaaja-id:n (pid) ja kasvattaa seuraavaa rekisteröitävää pelaaja-id:tä yhdellä.
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
        syntymaaika = Integer.toString(rand.nextInt(9999)); //TODO: muista muuttaa oikeaksi syntymäajaksi
        kansallisuus = "Yhdysvallat";
        
    }

}
