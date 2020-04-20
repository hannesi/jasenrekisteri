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
     * @param kohde tiedoston polku
     * @throws SailoException jos tiedostoa ei löydy
     * @example
     * <pre name="test">
     * #import java.io.File;
     * #import java.util.Scanner;
     * #import java.io.FileInputStream;
     *  File f = new File("./test/joukkueet.dat");
     *  Joukkueet joukkueet = new Joukkueet();
     *  Joukkue j1 = new Joukkue();
     *  Joukkue j2 = new Joukkue();
     *  j1.muokkaa("a", "b", "c", "d", 0);
     *  j2.muokkaa("h", "i", "j", "k", 4);
     *  j1.rekisteroi();
     *  j2.rekisteroi();
     *  joukkueet.lisaa(j1);
     *  joukkueet.lisaa(j2);
     *  
     *  //testataan joukkueiden tallentaminen tiedostoon
     *  
     *  try {
     *   joukkueet.tallenna("./test/joukkueet.dat"); 
     *   } catch (Exception e){ 
     *   // 
     *   }
     *   
     *   //tiedoston sisällön testaaminen
     *   
     *   try(Scanner fi = new Scanner(new FileInputStream(f))){
     *   fi.nextLine() === "0|0|a|b|c|d";
     *   fi.nextLine() === "1|4|h|i|j|k";
     *   fi.hasNext() === false; 
     *   } catch (Exception e) {
     *   //
     *   }
     *   
     *   Joukkueet joukkueetLadattu = new Joukkueet();
     *   try{
     *   joukkueetLadattu.lataa("./test/joukkueet.dat");
     *   } catch (Exception e) {
     *   //
     *   }
     *   joukkueetLadattu.getLkm() === 2;
     *   joukkueetLadattu.get(1).getJid() === 1;
     *   joukkueetLadattu.get(0).getNimi() === "a";
     *          
     *  f.delete();
     * </pre>
     */
    public void tallenna(String kohde) throws SailoException {
        try(PrintStream fo = new PrintStream(new FileOutputStream(kohde))){
            for (Joukkue j : joukkueet)
                j.tallenna(fo);
        } catch (FileNotFoundException e) {
            throw new SailoException("Tiedostoa " + kohde + " ei löydy!");
        }
    }
    
    /**tallentaa joukkueet tiedostoon
     * @throws SailoException jos tiedostoa ei löydy
     */
    public void tallenna() throws SailoException {
        tallenna(tiedostonNimi);
    }
    
    /**lataa joukkueet tiedostosta
     * @param kohde mistä ladataan
     * @throws SailoException jos tiedostoa ei löydy
     */
    public void lataa(String kohde) throws SailoException {
        try(Scanner fi = new Scanner(new FileInputStream(new File(kohde)))){
            while (fi.hasNext()) {
                Joukkue j = new Joukkue();
                j.parse(fi.nextLine());
                lisaa(j);
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Tiedostoa " + kohde + " ei löydy!");
        }
    }
    
    /**lataa joukkueet tiedostosta
     * @throws SailoException jos tieodostoa ei löydy
     */
    public void lataa() throws SailoException {
        lataa(tiedostonNimi);
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
    
    
    /**palauttaa listan joukkueista joiden nimi täsmää tuodun hakusanan kanssa
     * @param s hakusana
     * @return lista täsmääviä joukkueita
     */
    public List<Joukkue> get(String s) {
        var palautettava = new ArrayList<Joukkue>();
        for (Joukkue j : joukkueet)
            if (j.onkoNimeni(s))
                palautettava.add(j);
        return palautettava;
                
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
    

    /**palauttaa listan joukkueista joiden lid täsmää johonkin listassa olevaan lidiin
     * @param lids haettavat lidit
     * @return lista täsmäävistä joukkueista
     */
    public List<Joukkue> getJoukkueetByLids(List<Integer> lids) {
        var palautettava = new ArrayList<Joukkue>();
        for (Joukkue j : joukkueet)
            for (int i : lids)
                if (i == j.getLid())
                    palautettava.add(j);
        return palautettava;
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
     * 
     * @return lista kaikista joukkueista aakkosjärjestyksessä (kaupunki + nimi)
     */
    public List<Joukkue> getKaikkiJoukkueetSorted() {
        var sortedLista = new ArrayList<Joukkue>();
        for (Joukkue j : joukkueet)
            sortedLista.add(j);
        Collections.sort(sortedLista);
        return sortedLista;
        
    }
    

    /**palauttaa listan joukkueista joiden lid löytyy parametrina tuodusta listasta
     * @param lidLista lista lideistä joita vastaavia joukkueita haetaan
     * @return lista joukkueista jotka pelaavat listan lidiä edustavissa liigoissa
     */
    public List<Joukkue> getByLid(List<Integer> lidLista) {
        var palautettava = new ArrayList<Joukkue>();
        for (Joukkue j : joukkueet)
            for (int i : lidLista)
                if (j.getLid() == i) {
                    palautettava.add(j);
                    break;
                }
                    
        return palautettava;
    }
    

    /**palauttaa listan hakuehtoja vastaavien joukkueiden jideistä
     * @param hakusana hakusana
     * @return lista jidejä
     */
    public List<Integer> getJids(String hakusana) {
        var palautettava = new ArrayList<Integer>();
        for (Joukkue j : this.get(hakusana))
            palautettava.add(j.getJid());
        return palautettava;
    }
    

    /**palauttaa listan joukkueiden jidejä jotka vastaavat tuodun listan lidejä
     * @param lidLista lista lidejä joita vastaavien liigojen joukkueiden jidit halutaan
     * @return lista jidejä
     */
    public List<Integer> getJidsByLids(List<Integer> lidLista) {
        var palautettava = new ArrayList<Integer>();
        for (Joukkue j : joukkueet)
            for (int i : lidLista)
                if (j.getLid() == i) {
                    palautettava.add(j.getJid());
                    break;
                }
        return palautettava;
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
