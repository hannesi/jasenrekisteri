package sopimusrekisteri;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Random;

/**
 * @author Hannes Koivusipilä
 * @version 27.2.2020
 *
 */
public class Joukkue {
    
    private int     jid;
    private int     lid;
    private String  nimi        = "";
    private String  kaupunki    = "";
    private String  omistaja    = "";
    private String  yhteystieto = "";

    private static int seuraavaJid;
    
    
    
    /**korvaa joukkueen tiedot
     * @param nimi joukkueen nimi
     * @param kaupunki joukkueen kaupunki
     * @param omistaja joukkueen omistaja
     * @param yhteystieto joukkueen yhtestieto
     * @param lid liiga-id
     */
    @SuppressWarnings("hiding")
    public void muokkaa(String nimi, String kaupunki, String omistaja,
            String yhteystieto, int lid) {
        this.nimi = nimi;
        this.kaupunki = kaupunki;
        this.omistaja = omistaja;
        this.yhteystieto = yhteystieto;
        this.lid = lid;
        
    }
    
    /**palauttaa joukkue-id:n
     * @return jid
     */
    public int getJid() {
        return jid;
    }
    

    /**palauttaa joukkueen liiga-id:n
     * @return lid
     */
    public int getLid() {
        return lid;
    }
    
    /**palauttaa joukkueen nimen pitkässä muodossa "Kaupunki Nimi"
     * @return joukkueen kaupunki ja nimi
     */
    public String getNimiPitka() {
        return kaupunki + " " + nimi;
    }
    
    /**palauttaa joukkueen nimen
     * @return joukkueen nimi
     */
    public String getNimi() {
        return nimi;
    }
    
    /**palauttaa joukkueen kaupungin
     * @return joukkueen kaupunki
     */
    public String getKaupunki() {
        return kaupunki;
    }
    
    /**palauttaa joukkueen omistajan
     * @return joukkueen omistaja
     */
    public String getOmistaja() {
        return omistaja;
    }
    
    /**palauttaa joukkueen yhteystiedon
     * @return joukkueen yhteystieto
     */
    public String getYhteystieto() {
        return yhteystieto;
    }
    
    /**
     * tulostetaan joukkueen tiedot
     * @param out tietovirta, johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d",  jid) + " " + lid + " " + kaupunki + " " + nimi);
        out.println(omistaja + ", " + yhteystieto);
    }
    
    
    /**
     * tulostetaan joukkueen tiedot
     * (tarvitaan tulevaisuudessa, kun tulostetaan tavaraa käyttöliittymän laatikoihin
     * @param os tietovirta, johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    /** Antaa joukkueelle joukkue-id:n (jid) ja kasvattaa seuraavaa rekisteröitävää joukkue-id:tä yhdellä.
     * @return jid
     */
    public int rekisteroi() {
        jid = seuraavaJid++;
        return jid;
    }
    
    
    /**luokan testaamista
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        var j1 = new Joukkue();
        var j2 = new Joukkue();
        
        j1.rekisteroi();
        j2.rekisteroi();
        
        j1.tulosta(System.out);
        j1.taytaJoukkue(); //temp
        j1.tulosta(System.out);
        
        j2.tulosta(System.out);
        j2.taytaJoukkue(); //temp
        j2.tulosta(System.out);
        
    }
    
    /**
     * apumetodi luokan testaamiseen
     */
    public void taytaJoukkue() {
        lid = 0;
        nimi = "Coyotes";
        kaupunki = "Arizona";
        omistaja = "Karhukopla";
        Random rand = new Random();
        yhteystieto = Integer.toString(rand.nextInt(9999)) + "@email.vif";        
    }


}
