package sopimusrekisteri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * |--------------------------------------------------------------------------|
 * | Luokan nimi: Joukkueet                               | Avustajat:        |
 * |---------------------------------------------------------------------------
 * | Vastuualueet:                                        |                   |
 * |                                                      | - Joukkue         |
 * | - pitää yllä rekisteriä joukkueista eli osaa lisätä, |                   |
 * |   poistaa ja muokata joukkueita                      |                   |
 * | - lukee joukkueet tiedostosta ja kirjoittaa          |                   |
 * |   tiedostoon                                         |                   |
 * | - osaa etsiä ja lajitella joukkueita                 |                   |
 * |                                                      |                   |
 * |                                                      |                   |
 * |                                                      |                   |
 * |                                                      |                   |
 * |                                                      |                   |
 * |                                                      |                   |
 * |                                                      |                   |
 * |                                                      |                   |
 * |---------------------------------------------------------------------------
 * @author Hannes Koivusipilä
 * @version 27.2.2020
 *
 */
public class Joukkueet {
    
    private String              tiedostonNimi       = "./dat/joukkueet.dat";
    private ArrayList<Joukkue>  joukkueet           = new ArrayList<Joukkue>();
    
    
    
    /**tallentaa joukkueet tiedostoon
     * @throws SailoException jos tiedostoa ei löydy
     */
    public void tallenna() throws SailoException {
        try(PrintStream fo = new PrintStream(new FileOutputStream(tiedostonNimi))){
            for (Joukkue j : joukkueet)
                j.tallenna(fo);
        } catch (FileNotFoundException e) {
            throw new SailoException("Tiedostoa " + tiedostonNimi + " ei löydy!");
        }
    }
    
    /**lataa joukkueet tiedostosta
     * @throws SailoException jos tiedostoa ei löydy
     */
    public void lataa() throws SailoException {
        try(Scanner fi = new Scanner(new FileInputStream(new File(tiedostonNimi)))){
            while (fi.hasNext()) {
                Joukkue j = new Joukkue();
                j.parse(fi.nextLine());
                lisaa(j);
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Tiedostoa " + tiedostonNimi + " ei löydy!");
        }
    }
    
    
    /**lisää joukkue 
     * @param j lisättävä joukkue
     */
    public void lisaa(Joukkue j) {
        joukkueet.add(j);
    }
    
    
    /**palauttaa joukkueiden lukumäärän
     * @return joukkueiden lukumäärä kokonaislukuna
     */
    public int getLkm() {
        return joukkueet.size();
    }
    
    
    /**palauttaa liigassa jonka lid pelaavien joukkueiden lkm
     * @param lid liiga jonka joukkueiden lkm halutaan
     * @return joukkueiden lkm ko liigassa
     */
    public int getLkm(int lid) {
        int laskuri = 0;
        for (Joukkue j : joukkueet)
            if (j.getLid() == lid)
                laskuri++;
        return laskuri;
    }
    
    
    /**palauttaa joukkueen listasta indeksin perusteella
     * @param indeksi palautettavan joukkueen indeksi
     * @return joukkue
     */
    public Joukkue get(int indeksi) {
        return joukkueet.get(indeksi);
    }
    
    /**hae joukkue joukkue-id:n perusteella
     * @param jid haettava jid
     * @return joukkue jolla haettava jid
     * @throws SailoException jos tuotua jidiä ei ole joukkueella
     */
    public Joukkue getById(int jid) throws SailoException {
        for (int i = 0; i < joukkueet.size(); i++) {
            if (joukkueet.get(i).getJid() == jid) return joukkueet.get(i);
        }
        throw new SailoException("Ei löydy joukkuetta, jid: " + jid);
    }
    
    /**Poistaa joukkueen 
     * @param j poistettava joukkue
     */
    //TODO: kun joukkue poistetaan, poistetaan myös sopimukset, joissa se on osapuolena
    public void poista(Joukkue j) {
        joukkueet.remove(j);
    }    
    
    
    /**yrittää korvata olemassa olevan joukkueen jolla sama jid kuin tuodulla joukkueella. Jos tuodun joukkueen jidiä vastaavaa joukkuetta ei ole olemassa, luodaan uusi joukkue.
     * @param j korvaava joukkue
     * @return true jos korvattava löytyi ja korvattiin, false jos luotiin uusi
     */
    public boolean korvaaTaiLisaa(Joukkue j) {
        for (int i = 0; i < getLkm(); i++) {
            if (joukkueet.get(i).getJid() == j.getJid()) {
                joukkueet.set(i, j);
                return true;
            }
        }
        this.lisaa(j);
        return false;
    }
    
    /**
     * main sisältää vain testaamista
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Joukkueet joukkueet = new Joukkueet();
        var j1 = new Joukkue();
        var j2 = new Joukkue();
        j1.taytaJoukkue();
        j1.rekisteroi();
        j2.taytaJoukkue();
        j2.rekisteroi();
        
        
        joukkueet.lisaa(j1);
        joukkueet.lisaa(j2);
        joukkueet.lisaa(j2);
        joukkueet.lisaa(j2);
        joukkueet.lisaa(j2);
        joukkueet.lisaa(j2);
        joukkueet.lisaa(j2);


        
        System.out.println("===Joukkueet-luokan testit alla===");
        for (int i = 0; i < joukkueet.getLkm(); i++) {
            var joukkue = joukkueet.get(i);
            joukkue.tulosta(System.out);
        }
        System.out.println("Joukkueen muokkaaminen:");
        j1.tulosta(System.out);
        j1.muokkaa("Coyotes", "Phoenix", "Karhukopla", "asd@fgh.jkl", 0);
        j1.tulosta(System.out);

    }


}
