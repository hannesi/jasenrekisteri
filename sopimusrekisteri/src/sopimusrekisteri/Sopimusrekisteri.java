package sopimusrekisteri;

import java.util.ArrayList;


/**
 * @author Hannes Koivusipilä
 * @version 20.2.2020
 *
 */
public class Sopimusrekisteri {
    
    private Pelaajat    pelaajat    = new Pelaajat();
    private Joukkueet   joukkueet   = new Joukkueet();
    private Liigat      liigat      = new Liigat();
    private Sopimukset  sopimukset  = new Sopimukset();
    
    
    /**
     * Lisää uuden pelaajan
     * @param p Lisättävä pelaaja
     * @example
     * <pre name="test">
     *  #THROWS SailoException
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
     *  Joukkue j1 = new Joukkue();
     *  Joukkue j2 = new Joukkue();
     *  j1.taytaJoukkue();
     *  j1.rekisteroi();
     *  j2.taytaJoukkue();
     *  j2.rekisteroi();
     *  sr.lisaa(j1);
     *  sr.lisaa(j2);
     *  sr.getJoukkueita() === 2;
     *  sr.poista(j1);
     *  sr.getJoukkue(0) === j2;
     *  sr.getJoukkueita() === 1;
     *  j2.muokkaa("Kiakko", "Kouvola", "K. Kummola", "k.kummola@kmail.com", 0);
     *  j2.getNimi() === "Kiakko";
     *  Liiga l1 = new Liiga();
     *  Liiga l2 = new Liiga();
     *  l1.taytaLiiga();
     *  l1.rekisteroi();
     *  l2.taytaLiiga();
     *  l2.rekisteroi();
     *  sr.lisaa(l1);
     *  sr.lisaa(l2);
     *  sr.getJoukkueenLiiga(j2) == l1 === true;
     *  j2.muokkaa("Kiakko", "Kouvola", "K. Kummola", "k.kummola@kmail.com", 1);
     *  sr.getJoukkueenLiiga(j2) == l2 === true;
     *  sr.getLiigoja() === 2;
     *  sr.poista(l1);
     *  sr.getLiigoja() === 1;
     *  sr.getJoukkueenLiiga(j2) == l2 === true;
     *  sr.lisaa(p2, j2, 1000000, 2020, 2023);
     *  sr.getPelaajanSopimus(p2).getPalkka() === 1000000;
     *  sr.poista(sr.getPelaajanSopimus(p2));
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
        sopimukset.poista(sopimukset.getByPid(p.getPid()));
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
        sopimukset.poistaByJid(j.getJid());
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
    

    /**lisää uuden sopimuksen
     * @param p pelaajaosapuoli
     * @param j joukkueosapuoli
     * @param palkka pelaajan palkka
     * @param alkaa sopimuksen alkamisvuosi
     * @param loppuu sopimuksen loppumisvuosi
     */
    public void lisaa(Pelaaja p, Joukkue j, int palkka, int alkaa, int loppuu) {
        Sopimus s = new Sopimus(p.getPid(), j.getJid(), palkka, alkaa, loppuu);
        s.rekisteroi();
        sopimukset.lisaa(s);
    }
    
    /**Poistaa liigan
     * @param s poistettava sopimus
     */
    public void poista(Sopimus s) {
        sopimukset.poista(s);
    }
    
    
    /**etsii pelaajan joukkueen sopimuksista
     * @param p pelaaja
     * @return pelaajan joukkue
     */
    public Joukkue getPelaajanJoukkue(Pelaaja p) { //TODO: turha, poista kun ei tarvi enää testatessa
        Joukkue j = null;
        try {
            j = joukkueet.getById(sopimukset.getJidByPid(p.getPid()));
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        return j;
    }
    
    


    /**hakee pelaajan sopimuksen
     * @param p pelaaja jonka sopimus halutaan
     * @return pelaajan sopimus
     */
    public Sopimus getPelaajanSopimus(Pelaaja p) {
        return sopimukset.getByPid(p.getPid());
    }
    
    
    /**palauttaa liigan jossa joukkue j pelaa
     * @param j joukkue jonka liigaa haetaan
     * @return liiga jossa joukkue pelaa
     */
    public Liiga getJoukkueenLiiga(Joukkue j) {
        Liiga l = null;
        try {
            l = liigat.getById(j.getLid());
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        return l;
    }
    
    /**palauttaa listan joukkueista joiden lid vastaa tuodun liigan lidiä
     * @param l liiga jonka joukkueet halutaan hakea
     * @return lista liigan joukkueista
     */
    public ArrayList<Joukkue> getLiiganJoukkueet(Liiga l){
        var jLista = new ArrayList<Joukkue>();
        for (int i = 0; i < joukkueet.getLkm(); i++)
            if (joukkueet.get(i).getLid() == l.getLid()) jLista.add(joukkueet.get(i));
        return jLista;
    }
    
    /**palauttaa listan pelaajista joilla on sopimus tuodun joukkueen kanssa
     * @param j joukkue jonka pelaajia haetaan
     * @return lista pelaajista joilla on sopimus joukkueen kanssa
     * @throws SailoException jos pidillä ei löydy pelaajaa
     */
    public ArrayList<Pelaaja> getJoukkueenPelaajat(Joukkue j) throws SailoException {
        var pLista = new ArrayList<Pelaaja>();
        for (int i = 0; i < sopimukset.getLkm(); i++) {
            Sopimus s = sopimukset.get(i);
            if (s.getJid() == j.getJid()) pLista.add(pelaajat.getById(s.getPid()));
        }
        return pLista;
    }
    
    
    /**
     * main sisältää vain Sopimusrekisteri-luokan testaamista
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Sopimusrekisteri sr = new Sopimusrekisteri();
   
        Pelaaja p1 = new Pelaaja();
        Pelaaja p2 = new Pelaaja();
        Pelaaja p3 = new Pelaaja();
        
        p1.taytaPelaaja();
        p1.rekisteroi();
        p2.taytaPelaaja();
        p2.rekisteroi();
        p3.taytaPelaaja();
        p3.rekisteroi();

        sr.lisaa(p1);
        sr.lisaa(p2);
        sr.lisaa(p3);
        
        Liiga l1 = new Liiga();
        l1.taytaLiiga();
        l1.rekisteroi();
        sr.lisaa(l1);

        
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
        j3.taytaJoukkue();
        j1.rekisteroi();
        j2.rekisteroi();
        j3.rekisteroi();
        
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
        
        j3.muokkaa("Dumpster Fire", "Detroit", "Matti Meikäläinen", "asd@ghj.com", 0);
        j3.tulosta(System.out);
        System.out.println(sr.getJoukkueita());
        sr.poista(j3);
        System.out.println(sr.getJoukkueita());
        sr.lisaa(j3);
        System.out.println("===sopimusten testaamista===");
        //System.out.println(p1.getNimi() + " pelaa joukkueessa " + sr.getPelaajanJoukkue(p1).getNimiPitka());
        sr.lisaa(p1, j3, 1200000, 2020, 2022);
        sr.lisaa(p3, j3, 800000, 2019, 2020);

        System.out.println(p1.getNimi() + " pelaa joukkueessa " + sr.getPelaajanJoukkue(p1).getNimiPitka());
        System.out.println(p1.getNimi() + " tienaa kaudessa " + sr.getPelaajanSopimus(p1).getPalkka());
        
        System.out.println("Joukkueen " + j3.getNimi() + " pelaajat: ");

        try {
            for (Pelaaja p : sr.getJoukkueenPelaajat(j3))
                System.out.println(p.getNimi());
        } catch (SailoException e) {
            System.err.println(e.getMessage());
            System.err.flush();
        }
        
        System.out.println("Liigan " + l1.getNimi() + " joukkueet:");
        for (Joukkue j : sr.getLiiganJoukkueet(l1))
            System.out.println(j.getNimiPitka());


    }




}
