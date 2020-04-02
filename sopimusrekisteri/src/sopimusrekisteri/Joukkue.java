package sopimusrekisteri;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Random;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi: Joukkue                               | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   |
 * |                                                    |                   |
 * | - tietää joukkueen kentät                          |                   |
 * | - osaa tarkistaa tietyn kentät oikeellisuuden      |                   |
 * |                                                    |                   |
 * | - osaa muuttaa merkkijonon joukkueen tiedoiksi     |                   |
 * |(1|1|Hurricanes|Carolina|Aku Ankka|aku@ankka.vif)   |                   |
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
 * @version 27.2.2020
 *
 */
public class Joukkue implements Cloneable {
    
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
    
    /**tallentaa pelaajan tiedot tuotuun tiedostoon
     * @param out mihin tallennetaan
     */
    public void tallenna(PrintStream out) {
        out.println(jid + "|" + lid + "|" + nimi + "|" + kaupunki + "|" + omistaja + "|" + yhteystieto);
    }

    
    /**muuttaa merkkijonon joukkueen tiedoiksi
     * @param s merkkijono muodossa:
     * "123|456|Palloilijat|Uuskaarlepyy|R. Ankka|e@mail.com"
     *  jid|lid|nimi       |kaupunki    |omistaja|yhteystieto
     */
    public void parse(String s) {
        var sb = new StringBuilder(s);
        this.jid = Integer.parseInt(Mjonot.erota(sb, '|'));
        this.lid = Integer.parseInt(Mjonot.erota(sb, '|'));
        this.nimi = Mjonot.erota(sb, '|');
        this.kaupunki = Mjonot.erota(sb, '|');
        this.omistaja = Mjonot.erota(sb, '|');
        this.yhteystieto = sb.toString();
        seuraavaJid = this.jid < seuraavaJid ? seuraavaJid : this.jid + 1;
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
    
    @Override
    public Joukkue clone() throws CloneNotSupportedException {
        return (Joukkue) super.clone();
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
