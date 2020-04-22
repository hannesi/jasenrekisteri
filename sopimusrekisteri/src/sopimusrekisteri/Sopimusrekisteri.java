package sopimusrekisteri;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi: Sopimusrekisteri                      | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   |
 * |                                                    | - Pelaajat        |
 * | - huolehtii Pelaajat-, Joukkueet-, Liigat- ja      | - Joukkueet       |
 * |   Sopimukset-luokkien välisestä yhteistyöstä ja    | - Liigat          |
 * |                                                    | - Sopimukset      |
 * | - lukee ja kirjoittaa sopimusrekisterin tiedostoon | - Pelaaja         |
 * |   pyytämällä apua avustajiltaan                    | - Joukkue         |
 * |                                                    | - Liiga           |
 * |                                                    | - Sopimus         |
 * |                                                    |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |-------------------------------------------------------------------------
 * @author Hannes Koivusipilä
 * @version 20.2.2020
 *
 */
public class Sopimusrekisteri {
    
    private Pelaajat    pelaajat    = new Pelaajat();
    private Joukkueet   joukkueet   = new Joukkueet();
    private Liigat      liigat      = new Liigat();
    private Sopimukset  sopimukset  = new Sopimukset();
    
    /**lataa tiedot tiedostoista kun olio luodaan
     * @throws SailoException jos tiedostoja hukassa
     */
    public Sopimusrekisteri() throws SailoException {
        this.lataa();
    }
    
    
    /**
     * tallentaa avoinna olevat tiedot
     * @throws SailoException jos tallentaessa tapahtuu hirveitä
     */
    public void tallenna() throws SailoException {
        pelaajat.tallenna();
        joukkueet.tallenna();
        liigat.tallenna();
        sopimukset.tallenna();
    }
    
    
    /**
     * lataa tiedot tiedostoista
     * @throws SailoException jos menee pieleen
     */
    public void lataa() throws SailoException {
        liigat.lataa();
        joukkueet.lataa();
        pelaajat.lataa();
        sopimukset.lataa();
    }
    
    
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
        return pelaajat.getById(pid);
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
    

    /**palauttaa listan kaikista joukkueista aakkosjärjestyksessä
     * @return lista joukkueista
     */
    public List<Joukkue> getKaikkiJoukkueetSorted() {
        return joukkueet.getKaikkiJoukkueetSorted();
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
    
    /**palauttaa kaikki liigat listassa aakkojärjestyksessä
     * @return lista jossa kaikki sopimusrekisterin liigat
     */
    public List<Liiga> getKaikkiLiigatSorted() {
        return liigat.getKaikkiLiigatSorted();
    }
    
    /**Poistaa liigan
     * @param l poistettava liiga
     * @throws SailoException jos liigassa on vielä joukkueita
     */
    public void poista(Liiga l) throws SailoException {
        if (getLiiganJoukkueetLkm(l) != 0) throw new SailoException("Ei voida poistaa, koska liigassa on vielä joukkueita!"); 
        liigat.poista(l);
    }
    

    /**palauttaa liigassa pelaavien joukkueiden määrän
     * @param l liiga jonka joukkueiden lkm halutaan
     * @return joukkueiden lkm
     */
    public int getLiiganJoukkueetLkm(Liiga l) {
        return joukkueet.getLkm(l.getLid());
    }


    /**lisää uuden sopimuksen
     * @param s sopimus
     */
    public void lisaa(Sopimus s) {
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
    
    
    /**korvaa pelaajan pid perusteella. jos pid ei löydy, luo uuden pelaajan.
     * @param p pelaaja, joka korvaa vanhan tai joka luodaan
     */
    public void korvaaTaiLisaa(Pelaaja p) {
        pelaajat.korvaaTaiLisaa(p);
    }

    /**korvaa joukkueen jid perusteella. jos jid ei löydy, luo uuden joukkueen.
     * @param j joukkue, joka korvaa vanhan tai joka luodaan
     */
    public void korvaaTaiLisaa(Joukkue j) {
        joukkueet.korvaaTaiLisaa(j);
        
    }
    
    /**korvaa liigan lid perusteella. jos lid ei löydy, luo uuden liigan.
     * @param l liiga, joka korvaa vanhan tai joka luodaan
     */
    public void korvaaTaiLisaa(Liiga l) {
        liigat.korvaaTaiLisaa(l);
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
    

    /**palauttaa joukkueen pelaajien lukumäärän
     * @param j joukkue jonka pelaajien lkm halutaan
     * @return pelaajien lkm
     */
    public int getJoukkueenPelaajatLkm(Joukkue j) {
        return sopimukset.getJoukkueenPelaajatLkm(j.getJid());
    }


    /**palauttaa joukkueen kokonaispalkan vuodelta
     * @param j joukkue jonka palkat halutaan
     * @return joukkueen palkat
     */
    public int getJoukkueenPalkat(Joukkue j) {
        return sopimukset.getJoukkueenPalkat(j.getJid());
    }
    

    /**palauttaa lista joukkueista jotka pelaavat sarjoissa joiden sopimussääntöihin sopimus sopii
     * @param s sopimus jonka kanssa yhteensopivien sarjojen joukkueet halutaan
     * @return lista joukkueista
     * @throws SailoException sopimusvirheitä
     */
    public List<Joukkue> getJoukkueetForSopimus(Sopimus s) throws SailoException {
        int kesto = s.getLoppumisvuosi() - s.getAlkamisvuosi();
        var sopivatLiigat = liigat.getJokainenLidForSopimus(s.getPalkka(), kesto);
        var sopivatJoukkueet = getJoukkueetByLids(sopivatLiigat);
        Collections.sort(sopivatJoukkueet);
        return sopivatJoukkueet;
    }
    
    /** palauttaa lidejä vastaavissa liigoissa pelaavat joukkueet
     * @param lids lista lideistä
     * @return lista joukkueista
     */
    public List<Joukkue> getJoukkueetByLids(List<Integer> lids) {
        return joukkueet.getJoukkueetByLids(lids);
    }
    
    /**pelaajien haun suorittava aliohjelma
     * @param hakusana millä hakusanalla suodatetaan
     * @param hakutyyppi millä perusteella suodatetaan. 0: Pelaajan nimi (kaikki pelaajat), 1: Pelaajan nimi (sopimuksettomat), 2: Joukkueen nimi jonka kanssa pelaajalla sopimus, 3: Sarjan nimi jossa pelaavalla joukkueella sopimus pelaajan kanssa
     * @return lista hakuehdot täyttävistä pelaajista
     */
    public List<Pelaaja> getPelaajalista(String hakusana, int hakutyyppi) {
        List<Pelaaja> palautettava = null;
        switch (hakutyyppi) {
            case 0:
                palautettava = pelaajat.get(hakusana, new ArrayList<Integer>());
                break;
            case 1:
                palautettava = pelaajat.get(hakusana, sopimukset.getKaikkiPid());
                break;
            case 2:
                palautettava = pelaajat.get(
                               sopimukset.getPidsByJids(
                               joukkueet.getJids(hakusana)));
                break;
            case 3:
                palautettava = pelaajat.get(
                               sopimukset.getPidsByJids(
                               joukkueet.getJidsByLids(
                               liigat.getLids(hakusana))));
                break;
        default:
                break;
        }
        Collections.sort(palautettava);
        return palautettava;
    }
    

    /**joukkueiden haun suorittava aliohjelma
     * @param hakusana millä hakusanalla suodatetaan
     * @param hakutyyppi millä perusteella suodatetaan. 0: joukkueen nimi, 1: sarjan nimi jossa pelaavat joukkueet halutaan
     * @return lista hakuehdot täyttävistä joukkueista
     */
    public List<Joukkue> getJoukkuelista(String hakusana, int hakutyyppi) {
        List<Joukkue> palautettava = null;
        switch (hakutyyppi) {
        case 0:
            palautettava = joukkueet.get(hakusana);
            break;
        case 1:
            palautettava = joukkueet.getByLid(liigat.getLids(hakusana));
            break;
        default:
            break;
        }
        Collections.sort(palautettava);
        return palautettava;
    }
    


    /**liigojen haun suorittava aliohjelma
     * @param hakusana millä hakusanalla suodatetaan
     * @return lista hakuehdot täyttävistä liigoista
     */
    public List<Liiga> getLiigalista(String hakusana) {
        return liigat.get(hakusana);
    }
    


    /**tarkistaa joukkueet ja palauttaa rivitetyn merkkijonon huomautuksista
     * @return merkkijono huomautuksista
     * @throws SailoException jos jotain menee mönkään
     */
    public String tarkistaJoukkueet() throws SailoException {
        var palautettava = new StringBuilder();
        for (int i = 0; i < joukkueet.getLkm(); i++) {
            Joukkue j = joukkueet.get(i);
            String apuJono = liigat.getById(j.getLid()).tarkistaJoukkue(
                                                        sopimukset.getJoukkueenPalkat(j.getJid()),
                                                        sopimukset.getJoukkueenPelaajatLkm(j.getJid())); //TODO: tätä vois siistiä aika paljonki
            if (!apuJono.equals(""))
                palautettava.append(j.getNimiPitka() + ": " + apuJono + "\n");
        }
        return palautettava.toString();
    }
    
    /**tarkistaa käykö sopimuksen palkka ja kesto sarjan sääntöihin
     * @param s tarkastettava sopimus
     * @return null jos ei täsmää
     * @throws SailoException jos ei täsmää
     */
    public boolean tarkistaSopimus(Sopimus s) throws SailoException {
        return (liigat.getById(joukkueet.getById(s.getJid()).getLid()).tarkistaPalkkaKestoExceptionilla(s.getPalkka(), s.getLoppumisvuosi()-s.getAlkamisvuosi()));
    }

    
    
    /**
     * main sisältää vain Sopimusrekisteri-luokan testaamista
     * @param args ei käytössä
     * @throws SailoException jos pelaajalla jo sopimus
     */
    public static void main(String[] args) throws SailoException {
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
        
        Liiga l2 = new Liiga();
        l2.taytaLiiga();
        l2.rekisteroi();
        sr.lisaa(l2);
        
        System.out.println(l1.getLid());
        System.out.println(l2.getLid());

        
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
        
        System.out.println("===sopimusten poistaminen joukkueen poiston yhteydessä===");
        
        //Pelaaja sp1 = new Pelaaja();

        sr.tallenna();
        sr.lataa();
        for (int i = 0; i < sr.getPelaajia(); i++)
            sr.getPelaaja(i).tulosta(System.out);
        for (int i = 0; i < sr.getJoukkueita(); i++)
            sr.getJoukkue(i).tulosta(System.out);
        
        
        System.out.println("getKaikkiLiigat testiä");
        List<Liiga> liigatGetattuna = sr.getKaikkiLiigatSorted();
        for (Liiga l : liigatGetattuna) System.out.println(l.getNimi());

    }



}
