package sopimusrekisteri;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Random;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi: Liiga                                 | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   |
 * |                                                    |                   |
 * | - tietää liigan kentät                             |                   |
 * | - osaa tarkistaa tietyn kentät oikeellisuuden      |                   |
 * |                                                    |                   |
 * | - osaa muuttaa merkkijonon liigan tiedoiksi        |                   |
 * |(1|Hokiliiga|700000|12500000|8|60240000|81500000|50)|                   |
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
public class Liiga implements Cloneable, Comparable<Liiga> {
    
    private int     lid;
    private String  nimi            = "";
    private int     minPalkka;
    private int     maxPalkka;
    private int     maxPituus;
    private int     palkkalattia;
    private int     palkkakatto;
    private int     maxSopimuksia;
    

    private static int seuraavaLid;
    
    
    /**kirjoittaa liigan tiedot tuotuun tietovirtaan
     * @param out mihin tallennetaan
     */
    public void tallenna(PrintStream out) {
         out.println(lid + "|" + nimi + "|" + minPalkka + "|" + maxPalkka + "|" + maxPituus + "|" + palkkalattia + "|" + palkkakatto + "|" + maxSopimuksia);
    }
    
    
    /**muuttaa merkkijonon liigan tiedoiksi
     * @param s merkkijono muodossa:
     * "123|hokiliiga|123123213|654654654|     5|123765324|867987674566|22"
     *  lid|nimi     |minpalkka|maxpalkka|pituus|p.lattia |p.katto     |max sopimuksia
     */
    public void parse(String s) {
        var sb = new StringBuilder(s);
        this.lid = Integer.parseInt(Mjonot.erota(sb, '|'));
        this.nimi = Mjonot.erota(sb, '|');
        this.minPalkka = Integer.parseInt(Mjonot.erota(sb, '|'));
        this.maxPalkka = Integer.parseInt(Mjonot.erota(sb, '|'));
        this.maxPituus = Integer.parseInt(Mjonot.erota(sb, '|'));
        this.palkkalattia = Integer.parseInt(Mjonot.erota(sb, '|'));
        this.palkkakatto = Integer.parseInt(Mjonot.erota(sb, '|'));
        this.maxSopimuksia = Integer.parseInt(Mjonot.erota(sb, '|'));
        seuraavaLid = this.lid < seuraavaLid ? seuraavaLid : this.lid + 1;
    }
    
    
    
    /**palauttaa liigan liiga-id:n
     * @return liigan lid
     */
    public int getLid() {
        return lid;
    }
    
    /**palauttaa liigan nimen
     * @return liigan nimi
     */
    public String getNimi() {
        return nimi;
    }
    
    /**palauttaa liigan minimipalkan
     * @return liigan minimipalkka
     */
    public int getMinPalkka() {
        return minPalkka;
    }
    
    /**palauttaa liigan maksimipalkan
     * @return liigan maksimipalkka
     */
    public int getMaxPalkka() {
        return maxPalkka;
    }
    
    /**palauttaa liigan sopimusten maksimipituuden
     * @return liigan sopimusten maksimipituus
     */
    public int getMaxPituus() {
        return maxPituus;
    }
    
    /**palauttaa liigan palkkalattian
     * @return liigan palkkalattia
     */
    public int getPalkkalattia() {
        return palkkalattia;
    }
    
    /**palauttaa liigan palkkakaton
     * @return liigan palkkatto
     */
    public int getPalkkakatto() {
        return palkkakatto;
    }
    
    /**palauttaa liigan joukkueiden maksimisopimusmäärän
     * @return liigan joukkueiden maksimisopimusmäärä
     */
    public int getMaxSopimuksia() {
        return maxSopimuksia;
    }
    
    /**
     * tulostetaan liigan tiedot
     * @param out tietovirta, johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d",  lid) + " " + nimi);
        out.println("Yksittäinen sopimus: " + minPalkka + " - " + maxPalkka + " rahayksikköä per kausi, max pituus: " + maxPituus);
        out.println("Joukkueen palkkalattia: " + palkkalattia + ", palkkakatto: " + palkkakatto + ", sopimuksia enintään " + maxSopimuksia + " kpl");
    }
    
    
    /**
     * tulostetaan liigan tiedot
     * (tarvitaan tulevaisuudessa, kun tulostetaan tavaraa käyttöliittymän laatikoihin
     * @param os tietovirta, johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**korvaa liigan tietot
     * @param nimi nimi
     * @param minPalkka minimipalkka
     * @param maxPalkka maksimipalkka
     * @param maxPituus sopimuksen max kesto
     * @param palkkalattia joukkueen palkkalattia
     * @param palkkakatto joukkueen palkkakatto
     * @param maxSopimuksia joukkueen sopimusten maksimimäärä
     */
    @SuppressWarnings("hiding")
    public void muokkaa(String nimi, int minPalkka, int maxPalkka, int maxPituus, int palkkalattia, int palkkakatto, int maxSopimuksia) {
        this.nimi = nimi;
        this.minPalkka = minPalkka;
        this.maxPalkka = maxPalkka;
        this.maxPituus = maxPituus;
        this.palkkalattia = palkkalattia;
        this.palkkakatto = palkkakatto;
        this.maxSopimuksia = maxSopimuksia;
    }
    
    
    /** 
     * Antaa liigalle liiga-id:n (lid) ja kasvattaa seuraavaa rekisteröitävää liiga-id:tä yhdellä.
     * @return pid
     */
    public int rekisteroi() {
        lid = seuraavaLid++;
        return lid;
    }
    
    
    @Override
    public Liiga clone() throws CloneNotSupportedException {
        return (Liiga) super.clone();
    }
    
    @Override
    public int compareTo(Liiga o) {
        return this.getNimi().compareTo(o.getNimi());
    }
    
    /**
     * Pääohjelmassa vain luokan testailua
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Liiga l1 = new Liiga();
        Liiga l2 = new Liiga();

        l1.rekisteroi();
        l2.rekisteroi();
        
        l1.tulosta(System.out);
        l1.taytaLiiga(); //temp
        l1.tulosta(System.out);
        
        l2.tulosta(System.out);
        l2.taytaLiiga(); //temp
        l2.tulosta(System.out);
        
    }


    /**
     * apumetodi luokan testaamiseen
     */
    public void taytaLiiga() {
        nimi = "Hokiliiga";
        minPalkka = 700000;
        maxPalkka = 12500000;
        maxPituus = 8;
        palkkalattia = 60240000;
        palkkakatto = 81500000;
        var rand = new Random();
        maxSopimuksia = rand.nextInt(99);
        
    }

}
