package sopimusrekisteri;

/**
 * @author Hannes Koivusipilä
 * @version 20.2.2020
 *
 */
public class Sopimusrekisteri {
    
    Pelaajat    pelaajat    = new Pelaajat();
    Joukkueet   joukkueet   = new Joukkueet();
    Liigat      liigat      = new Liigat();
    Sopimukset  sopimukset  = new Sopimukset();
    
    
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
    
    /**palauttaa pelaajan pelaaja-id:n perusteella
     * @param pid pelaaja-id
     * @return pidiä vastaava pelaaja
     */
    public Pelaaja getPelaajaById(int pid) {
        Pelaaja p = null;
        try {
            p = pelaajat.getById(pid);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        return p;
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
    
    /**palauttaa joukkueen joukkue-id:n perusteella
     * @param jid joukkue-id
     * @return jidiä vastaava joukkue
     */
    public Joukkue getJoukkueById(int jid) {
        Joukkue j = null;
        try {
            j = joukkueet.getById(jid);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        return j;
    }
    
    /**Poistaa joukkueen
     * @param j poistettava joukkue
     */
    public void poista(Joukkue j) {
        joukkueet.poista(j);
    }
        
    
    
    /**lisää uusi liiga
     * @param l lisättävä liiga
     */
    public void lisaa(Liiga l) {
        liigat.lisaa(l);
    }
    
    
    /**
     * Palauttaa liigojen lukumäärän
     * @return liigojen lukumäärä
     */
    public int getLiigoja() {
        return liigat.getLkm();
    }
    
    
    /**Palauttaa i:nnen liigan
     * @param i monesko liiga palautetaan
     * @return viite i:teen liigaan
     */
    public Liiga getLiiga(int i) {
        return liigat.get(i);
    }
    
    /**palauttaa liigan liiga-id:n perusteella
     * @param lid liiga-id
     * @return lidiä vastaava liiga
     */
    public Liiga getLiigaById(int lid) {
        Liiga l = null;
        try {
            l = liigat.getById(lid);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        return l;
    }
    
    /**Poistaa liigan
     * @param l poistettava liiga
     */
    public void poista(Liiga l) {
        liigat.poista(l);
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
