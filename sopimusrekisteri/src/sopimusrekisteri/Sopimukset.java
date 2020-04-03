package sopimusrekisteri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * |--------------------------------------------------------------------------|
 * | Luokan nimi: Sopimukset                              | Avustajat:        |
 * |---------------------------------------------------------------------------
 * | Vastuualueet:                                        |                   |
 * |                                                      | - Sopimus         |
 * | - pitää yllä rekisteriä sopimuksista eli osaa lisätä,|                   |
 * |   poistaa ja muokata sopimuksia                      |                   |
 * | - lukee sopimukset tiedostosta ja kirjoittaa         |                   |
 * |   tiedostoon                                         |                   |
 * | - osaa etsiä ja lajitella sopimuksia                 |                   |
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
public class Sopimukset {
    
    private String              tiedostonNimi   = "./dat/sopimukset.dat";
    private ArrayList<Sopimus> sopimukset = new ArrayList<Sopimus>();
   
    
    /**tallentaa sopimukset tiedostoon
     * @throws SailoException jos tiedostoa ei löydy
     */
    public void tallenna() throws SailoException {
        try(PrintStream fo = new PrintStream(new FileOutputStream(tiedostonNimi))) {
            for (Sopimus sop : sopimukset)
                sop.tallenna(fo);
        } catch (FileNotFoundException e) {
            throw new SailoException("Tiedostoa " + tiedostonNimi + " ei löydy!");
        }
    }
    
    
    /**
     * lataa sopimukset tiedostosta
     * @throws SailoException jos tiedostoa ei löydy
     */
    public void lataa() throws SailoException {
        try(Scanner fi = new Scanner(new FileInputStream(new File(tiedostonNimi)))){
            while (fi.hasNext()) {
                Sopimus sop = new Sopimus();
                sop.parse(fi.nextLine());
                lisaa(sop);
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Tiedostoa " + tiedostonNimi + " ei löydy!");
        }
            
    }
    
    
    /**lisää sopimuksen
     * @param s lisättävä sopimus
     */
    public void lisaa(Sopimus s) {
        sopimukset.add(s);
    }
    
    /**palauttaa sopimusten lukumäärän
     * @return sopimusten määrä
     */
    public int getLkm() {
        return sopimukset.size();
    }
    
    /**palauttaa sopimuksen listasta indeksin perusteella
     * @param indeksi haettavan sopimuksen indeksi
     * @return indeksin mukainen sopimus
     */
    public Sopimus get(int indeksi) {
        return sopimukset.get(indeksi);
    }
    
    /**poistaa sopimuksen
     * @param s poistettava sopimus
     */
    public void poista(Sopimus s) {
        sopimukset.remove(s);
    }
    
    
    
    /**poistaa tuodun jidin joukkuetta koskevat sopimukset
     * @param jid joukkue jonka sopimukset poistetaan
     */
    public void poistaByJid(int jid) {
        ListIterator<Sopimus> iter = sopimukset.listIterator();
        while(iter.hasNext()) {
            if(iter.next().getJid() == jid)
                iter.remove();
        }
    }
    
    
    /**palauttaa kaikkien sopimusten pid:it kokonaislukuina
     * @return lista pideistä
     */
    public List<Integer> getKaikkiPid() {
        var palautettava = new ArrayList<Integer>();
        for (Sopimus s : sopimukset)
            palautettava.add(s.getPid());
        return palautettava;
    }
    

    //===============================================================================================
    //Tämän alapuolella hakuun liittyviä aliohjelmia
    
    /**palauttaa tuodun pid vastaavan pelaajan sopimuksen joukkueosapuolen jid
     * @param pid pelaaja-id jonka joukkuetta haetaan
     * @return joukkueen jid
     * @throws SailoException jos pid:lle ei löydy sopimusta 
     */
    public int getJidByPid(int pid) throws SailoException {
        for (int i = 0; i < sopimukset.size(); i++)
            if (sopimukset.get(i).getPid() == pid)
                return sopimukset.get(i).getJid();
        throw new SailoException("Ei löytynyt sopimusta jossa pid: " + pid);
    }

    /**palauttaa sopimuksen pid perusteella
     * @param pid haettavan sopimuksen pelaajaosapuolen pid
     * @return pelaajan sopimus
     */
    public Sopimus getByPid(int pid) {
        for (int i = 0; i < sopimukset.size(); i++)
            if (sopimukset.get(i).getPid() == pid)
                return sopimukset.get(i);
        //throw new SailoException("Ei löytynyt sopimusta jossa pid: " + pid);
        return null;
    }

    /**palauttaa joukkueen sopimusten lukumäärän
     * @param jid joukkue jonka sopimusten lkm palautetaan
     * @return joukkueen sopimusten lkm
     */
    public int getJoukkueenPelaajatLkm(int jid) {
        int laskuri = 0;
        for (Sopimus s : sopimukset)
            if (s.getJid() == jid)
                laskuri++;
        return laskuri;
    }

    /**palauttaa joukkueen sopimusten kokonaisvuosipalkkojen määrän
     * @param jid joukkue jonka sopimusten palkkamenot palautetaan
     * @return joukkueen sopimusten palkkamenot
     */
    public int getJoukkueenPalkat(int jid) {
        int laskuri = 0;
        for (Sopimus s: sopimukset)
            if (s.getJid() == jid)
                laskuri += s.getPalkka();
        return laskuri;
    }



}
