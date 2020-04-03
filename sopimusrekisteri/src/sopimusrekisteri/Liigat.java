package sopimusrekisteri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * |--------------------------------------------------------------------------|
 * | Luokan nimi: Liigat                                  | Avustajat:        |
 * |---------------------------------------------------------------------------
 * | Vastuualueet:                                        |                   |
 * |                                                      | - Liiga           |
 * | - pitää yllä rekisteriä liigoista eli osaa lisätä,   |                   |
 * |   poistaa ja muokata liigoja                         |                   |
 * | - lukee liigat tiedostosta ja kirjoittaa             |                   |
 * |   tiedostoon                                         |                   |
 * | - osaa etsiä ja lajitella liigoja                    |                   |
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
public class Liigat {
    
    private String              tiedostonNimi   = "./dat/liigat.dat";
    private ArrayList<Liiga>    liigat          = new ArrayList<Liiga>();

    
    /**
     * tallentaa liigat tiedostoon
     * @throws SailoException jos tiedostoa ei löydy 
     */
    public void tallenna() throws SailoException {
        try(PrintStream fo = new PrintStream(new FileOutputStream(tiedostonNimi))) {
            for (Liiga l : liigat)
                l.tallenna(fo);
        } catch (FileNotFoundException e) {
            throw new SailoException("Tiedostoa " + tiedostonNimi + " ei löydy!");
        }
    }
    
    
    /**
     * lataa liigat tiedostosta
     * @throws SailoException jos tiedostoa ei löydy
     */
    public void lataa() throws SailoException {
        try(Scanner fi = new Scanner(new FileInputStream(new File(tiedostonNimi)))){
            while (fi.hasNext()) {
                Liiga l = new Liiga();
                l.parse(fi.nextLine());
                lisaa(l);
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Tiedostoa " + tiedostonNimi + " ei löydy!");
        }
            
    }
    
    
    
    /**lisää liiga
     * @param l lisättävä liiga
     * @example
     * <pre name="test">
     *   var liigat = new Liigat();
     *   var l1 = new Liiga();
     *   var l2 = new Liiga();
     *   l1.taytaLiiga();
     *   l2.taytaLiiga();
     *   liigat.lisaa(l1);
     *   liigat.getLkm() === 1;
     *   liigat.lisaa(l2);
     *   liigat.getLkm() === 2;
     *   l1.getNimi() == l2.getNimi() === true;
     *   l1.muokkaa("Kaljaliiga", 500, 10000, 2, 5000, 25000, 25); 
     *   l1.getNimi() == l2.getNimi() === false;
     *   liigat.poista(l1);
     *   liigat.get(0) == l2 === true;
     *   liigat.getLkm() === 1;
     * </pre>
     */
    public void lisaa(Liiga l) {
        liigat.add(l);
    }
    
    /**palauttaa liigojen lukumäärän
     * @return liigojen lukumäärä
     */
    public int getLkm() {
        return liigat.size();
    }
    
    /**palauttaa liigan listasta indeksin perusteella
     * @param indeksi palautettavan liigan indeksi
     * @return liiga
     */
    public Liiga get(int indeksi) {
        return liigat.get(indeksi);
    }
    


    /**palauttaa listan liigoista joiden nimi täsmää parametrina tuotuun merkkijonoon
     * @param s hakusana
     * @return lista liigoja
     */
    public List<Liiga> get(String s) {
        var palautettava = new ArrayList<Liiga>();
        for (Liiga l : liigat)
            if (l.onkoNimeni(s))
                palautettava.add(l);
        return palautettava;
    }
    
    /**poistaa liigan
     * @param l poistettava liiga
     *
     */
    //TODO: kun liiga poistetaan poistetaan myös siinä olevat joukkueet
    public void poista(Liiga l) {
        liigat.remove(l);
    }
    
    /**hae liiga liiga-id:n perusteella
     * @param lid haettava lid
     * @return liiga jolla haettava lid
     * @throws SailoException jos tuotua lidiä ei ole liigalla
     */
    public Liiga getById(int lid) throws SailoException {
        for (int i = 0; i < liigat.size(); i++) {
            if (liigat.get(i).getLid() == lid) return liigat.get(i);
        }
        throw new SailoException("Ei löydy liigaa, lid: " + lid);
    }
    

    /**palauttaa listan liigoista, jotka sallivat tuodun palkan ja keston sopimussäännöissään
     * @param palkka palkka
     * @param kesto kesto
     * @return lista liigoista
     */
    public List<Integer> getJokainenLidForSopimus(int palkka, int kesto) {
        var palautettava = new ArrayList<Integer>();
        for (Liiga l : liigat)
            if (l.tarkistaPalkkaKesto(palkka, kesto))
                palautettava.add(l.getLid());
        return palautettava;
    }

    

    /**palauttaa listan lidejä joita vastaavien liigojen nimet täsmäävät hakuehtoon
     * @param s  hakuehto
     * @return lista lidejä kokonaislukuina
     */
    public List<Integer> getLids(String s) {
        var palautettava = new ArrayList<Integer>();
        for (Liiga l : this.get(s))
            palautettava.add(l.getLid());
        return palautettava;
    }

    
    /**yrittää korvata olemassa olevan liigan jolla sama lid kuin tuodulla liigalla. Jos tuodun liigan lidiä vastaavaa liigaa ei ole olemassa, luodaan uusi liiga.
     * @param l korvaava liiga
     * @return true jos korvattava löytyi ja korvattiin, false jos luotiin uusi
     */
    public boolean korvaaTaiLisaa(Liiga l) {
        for (int i = 0; i < getLkm(); i++) {
            if (liigat.get(i).getLid() == l.getLid()) {
                liigat.set(i, l);
                return true;
            }
        }
        this.lisaa(l);
        return false;
    }


    /**palauttaa aakkosjärjestyksessä olevan kopion liigat-listasta.
     * @return liigat-lista
     */
    public List<Liiga> getKaikkiLiigatSorted() {
        var sortedLista = new ArrayList<Liiga>();
        for (Liiga l : liigat)
            sortedLista.add(l);
        Collections.sort(sortedLista);
        return sortedLista;
    }




  
}
