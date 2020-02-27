package sopimusrekisteri;

import java.util.ArrayList;

/**
 * @author Hannes Koivusipilä
 * @version 27.2.2020
 *
 */
public class Joukkueet {
    
    private String              tiedostonNimi       = "";
    private ArrayList<Joukkue>  joukkueet           = new ArrayList<Joukkue>();
    
    
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
        j1.muokkaa("Coyotes", "Phoenix", "Karhukopla", "asd@fgh.jkl");
        j1.tulosta(System.out);

    }
}
