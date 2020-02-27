package sopimusrekisteri;

import java.util.ArrayList;
import java.util.List;

public class Joukkueet {
    
    private String              tiedostonNimi   = "";
    private ArrayList<Joukkue>  joukkueet          = new ArrayList<Joukkue>();
    
    
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
    
    
    /**palauttaa joukkueen listasta indeksin perusteella
     * @param indeksi palautettavan joukkueen indeksi
     * @return joukkue
     */
    public Joukkue get(int indeksi) {
        return joukkueet.get(indeksi);
    }
    
    /**Poistaa joukkueen 
     * @param j poistettava joukkue
     */
    public void poista(Joukkue j) {
        joukkueet.remove(j);
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


        
        System.out.println("===Pelaajat-luokan testit alla===");
        for (int i = 0; i < joukkueet.getLkm(); i++) {
            var joukkue = joukkueet.get(i);
            joukkue.tulosta(System.out);
        }
        System.out.println("Pelaajan muokkaaminen:");
        j1.tulosta(System.out);
        //j1.muokkaa("Mynttinen", "Pertti", "02.01.2000", "Suomi");
        j1.tulosta(System.out);

    }
}
