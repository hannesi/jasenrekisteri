package sopimusrekisteri;

/**
 * @author Hannes Koivusipilä
 * @version 20.2.2020
 *
 */
public class Sopimusrekisteri {
    
    Pelaajat pelaajat = new Pelaajat();
    
    
    /**
     * Lisätään uusi pelaaja
     * @param p Lisättävä pelaaja
     * @throws SailoException Jos ei mahdu
     */
    public void lisaa(Pelaaja p) throws SailoException {
        pelaajat.lisaa(p);
    }
    
    
    /**
     * Palauttaa pelaajien lukumäärän
     * @return pelaajien lukumäärä kokonaislukuna
     */
    public int getPelaajia() {
        return pelaajat.getLkm();
    }
    
    
    /**palauttaa i:nnen pelaajan
     * @param i monesko pelaaja palautetaan
     * @return viite i:teen pelaajaan
     */
    public Pelaaja annaPelaaja(int i) {
        return pelaajat.anna(i);
    }
    
    
    /**
     * main sisältää vain Sopimusrekisteri-luokan testaamista
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Sopimusrekisteri sr = new Sopimusrekisteri();
        
        Pelaaja p1 = new Pelaaja();
        Pelaaja p2 = new Pelaaja();
        
        p1.taytaPelaaja();
        p1.rekisteroi();
        p2.taytaPelaaja();
        p2.rekisteroi();
        
        
        try {
            sr.lisaa(p1);
            sr.lisaa(p2);
            sr.lisaa(p2);
            sr.lisaa(p2);
            sr.lisaa(p2);
            sr.lisaa(p2);
            sr.lisaa(p2);
            sr.lisaa(p2);
            sr.lisaa(p2);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
            System.err.flush();
        }
        
        System.out.println("===sopimusrekisterin testaus alla====");
        for (int i = 0; i < sr.getPelaajia(); i++) {
            Pelaaja p = sr.annaPelaaja(i);
            System.out.println("Pelaaja paikassa " + i);
            p.tulosta(System.out);
        }
    }

}
