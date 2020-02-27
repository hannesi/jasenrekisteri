package sopimusrekisteri;

/**
 * @author Hannes Koivusipilä
 * @version 20.2.2020
 *
 */
public class Sopimusrekisteri {
    
    Pelaajat pelaajat = new Pelaajat();
    Joukkueet joukkueet = new Joukkueet();
    
    
    /**
     * Lisää uuden pelaajan
     * @param p Lisättävä pelaaja
     *      * @example
     * <pre name="test">
     *  Sopimusrekisteri sr = new Sopimusrekisteri();
     *  Pelaaja p1 = new Pelaaja();
     *  Pelaaja p2 = new Pelaaja();
     *  p1.taytaPelaaja();
     *  p1.rekisteroi();
     *  p2.taytaPelaaja();
     *  p2.rekisteroi();
     *  sr.lisaa(p1);
     *  sr.lisaa(p2);
     *  sr.lisaa(p2);
     *  sr.lisaa(p2);
     *  sr.lisaa(p2);
     *  sr.lisaa(p2);
     *  sr.lisaa(p2);
     *  sr.lisaa(p2);
     *  sr.lisaa(p2);
     *  p1.getSukunimi() === "Doe";
     *  p1.muokkaa("Doge", "John", "647832", "Canada");
     *  p1.getSukunimi() === "Doge";
     *  sr.getPelaajia() === 9;
     *  sr.poista(p1);
     *  sr.getPelaajia() === 8;
     * </pre>
     */
    public void lisaa(Pelaaja p) {
        pelaajat.lisaa(p);
    }
    
    
    /**
     * Palauttaa pelaajien lukumäärän
     * @return pelaajien lukumäärä
     */
    public int getPelaajia() {
        return pelaajat.getLkm();
    }
    
    
    /**Palauttaa i:nnen pelaajan
     * @param i monesko pelaaja palautetaan
     * @return viite i:teen pelaajaan
     */
    public Pelaaja getPelaaja(int i) {
        return pelaajat.get(i);
    }
    
    /**Poistaa pelaajan
     * @param p poistettava pelaaja
     */
    public void poista(Pelaaja p) {
        pelaajat.poista(p);
    }
    
    
    /**
     * Lisää uuden joukkueen
     * @param j Lisättävä joukkue
     */
    public void lisaa(Joukkue j) {
        joukkueet.lisaa(j);
    }
    
    
    /**
     * Palauttaa joukkuiden lukumäärän
     * @return joukkueiden lukumäärä
     */
    public int getJoukkueita() {
        return joukkueet.getLkm();
    }
    
    
    /**Palauttaa i:nnen joukkueen
     * @param i monesko joukkue palautetaan
     * @return viite i:teen joukkueeseen
     */
    public Joukkue getJoukkue(int i) {
        return joukkueet.get(i);
    }
    
    /**Poistaa joukkueen
     * @param j poistettava joukkue
     */
    public void poista(Joukkue j) {
        joukkueet.poista(j);
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
        

        sr.lisaa(p1);
        sr.lisaa(p2);
        sr.lisaa(p2);
        sr.lisaa(p2);
        sr.lisaa(p2);
        sr.lisaa(p2);
        sr.lisaa(p2);
        sr.lisaa(p2);
        sr.lisaa(p2);
        
        System.out.println("===sopimusrekisterin testaus alla====");
        for (int i = 0; i < sr.getPelaajia(); i++) {
            Pelaaja p = sr.getPelaaja(i);
            System.out.println("Pelaaja paikassa " + i);
            p.tulosta(System.out);
        }
        p1.muokkaa("Pasanen", "Pertti", "432523", "Suomi");
        p1.tulosta(System.out);
       
        Joukkue j1 = new Joukkue();
        Joukkue j2 = new Joukkue();
        Joukkue j3 = new Joukkue();
        
        j1.taytaJoukkue();
        j2.taytaJoukkue();
        j1.rekisteroi();
        j2.rekisteroi();
        
        sr.lisaa(j1);
        sr.lisaa(j2);
        sr.lisaa(j2);
        sr.lisaa(j2);
        sr.lisaa(j2);
        sr.lisaa(j2);
        sr.lisaa(j2);
        sr.lisaa(j2);
        sr.lisaa(j3);
        

        
        for (int i = 0; i < sr.getJoukkueita(); i++) {
            Joukkue j = sr.getJoukkue(i);
            System.out.println("Joukkue paikassa "  + i);
            j.tulosta(System.out);
        }
        
        j3.muokkaa("Dumpster Fire", "Detroit", "Matti Meikäläinen", "asd@ghj.com");
        j3.tulosta(System.out);
        System.out.println(sr.getJoukkueita());
        sr.poista(j3);
        System.out.println(sr.getJoukkueita());
        
    }

}
