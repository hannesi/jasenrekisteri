package sopimusrekisteri;

/**
 * 
 * @author Hannes Koivusipilä
 * @version 19.2.2020
 *
 */
public class Pelaajat {

    private static final int    PELAAJAT_KASVATUS   = 5;
    private static final int    MAX_PELAAJIA        = 5;
    private int                 lkm                 = 0;
    private String              tiedostonNimi       = "";
    private Pelaaja[]           pelaajat;
    
    
    /**
     * luokka hoitaa Pelaaja-olioiden hallinnan
     */
    public Pelaajat() {
        pelaajat = new Pelaaja[MAX_PELAAJIA];
    }
    
    
    /**
     * Palauttaa pelaajien lukumäärän
     * @return pelaajien lukumäärä kokonaislukuna
     */
    public int getLkm() {
        return lkm;
    }
    
    
    /**
     * Lisää uuden pelaajan tietorakenteeseen. Ottaa Pelaajan omistukseensa.
     * @param p lisättävän Pelaajan viite
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Pelaajat pelaajat = new Pelaajat();
     * Pelaaja p1 = new Pelaaja(), p2 = new Pelaaja(), p3 = new Pelaaja();
     * pelaajat.getLkm() === 0;
     * pelaajat.lisaa(p1); pelaajat.getLkm() === 1;
     * pelaajat.lisaa(p2); pelaajat.getLkm() === 2;
     * pelaajat.lisaa(p1); pelaajat.getLkm() === 3;
     * pelaajat.get(1) == p1 === false;
     * pelaajat.get(1) == p2 === true;
     * pelaajat.get(3) === p2; #THROWS IndexOutOfBoundsException
     * pelaajat.lisaa(p3); pelaajat.getLkm() === 4;
     * pelaajat.lisaa(p3); pelaajat.getLkm() === 5;
     * pelaajat.lisaa(p3); pelaajat.getLkm() === 6;
     * p1.rekisteroi(); p2.rekisteroi();
     * pelaajat.get(0) == p1 === true;
     * pelaajat.get(1) == p1 === false;
     * pelaajat.poista(p2);
     * pelaajat.getLkm() === 5;
     * pelaajat.get(1) == p1 === true;
     * 
     * 
     * </pre>
     */
    public void lisaa(Pelaaja p) {
        if (lkm >= pelaajat.length)
            kasvataTaulukko();
            //throw new SailoException("Liikaa alkioita!");

        pelaajat[lkm++] = p;
    }
    
    
    /**
     * Siirtää pelaajat-taulukon sisällön alkuperäisessä järjestyksessä uuteen taulukkoon, 
     * joka on kooltaan PELAAJAT_KASVATUS indeksiä suurempi.
     * 
     * pelaajat viittaa operaation jälkeen uuteen suurempaan taulukkoon. Alkuperäinen pelaajat muuttuu roskaksi!
     */
    private void kasvataTaulukko() {
        var tempPelaajat = new Pelaaja[pelaajat.length + PELAAJAT_KASVATUS];
        for (int i = 0; i < pelaajat.length; i++)
            tempPelaajat[i] = pelaajat[i];
        pelaajat = tempPelaajat;
    }
    
    
    /**
     * palauttaa i:nnen pelaajan alkiot-taulukosta
     * @param i monekso pelaaja haetaan
     * @return Pelaaja alkiot-taulukon kohdasta i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella
     */
    public Pelaaja get(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i) throw new IndexOutOfBoundsException("Laiton indeksi:" + i);
        return pelaajat[i];
    }
    
    /**hae pelaaja pelaaja-id:n perusteella
     * @param pid haettava pid
     * @return pelaaja jolla haettava pid
     * @throws SailoException jos tuotua pidiä ei ole pelaajalla
     */
    public Pelaaja getById(int pid) throws SailoException {
        for (int i = 0; i < pelaajat.length; i++) {
            if (pelaajat[i].getPid() == pid) return pelaajat[i];
        }
        throw new SailoException("Ei löydy pelaajaa, pid: " + pid);
    }

    /**Poistaa pelaajan
     * @param p poistettava pelaaja
     */
    public void poista(Pelaaja p) {
        for (int i = 0; i < pelaajat.length; i++) {
            if (pelaajat[i] == p) {
                for (int j = i; j < pelaajat.length - 1; j++) {
                    pelaajat[j] = pelaajat[j+1];
                }
                pelaajat[pelaajat.length - 1] = null;
                lkm--;
                break;
            }
        }
    }
    
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Pelaajat pelaajat = new Pelaajat();
        Pelaaja p1 = new Pelaaja();
        Pelaaja p2 = new Pelaaja();
        Pelaaja p3 = new Pelaaja();
        p1.taytaPelaaja();
        p1.rekisteroi();
        p2.taytaPelaaja();
        p2.rekisteroi();
        
        
        pelaajat.lisaa(p1);
        pelaajat.lisaa(p2);
        pelaajat.lisaa(p2);
        pelaajat.lisaa(p2);
        pelaajat.lisaa(p2);
        pelaajat.lisaa(p3);
        pelaajat.lisaa(p2);
        pelaajat.lisaa(p2);
        pelaajat.lisaa(p2);
        pelaajat.poista(p3);
        


        
        System.out.println("===Pelaajat-luokan testit alla===");
        for (int i = 0; i < pelaajat.getLkm(); i++) {
            Pelaaja pelaaja = pelaajat.get(i);
            pelaaja.tulosta(System.out);
        }
        System.out.println("Pelaajan muokkaaminen:");
        p1.tulosta(System.out);
        p1.muokkaa("Mynttinen", "Pertti", "02.01.2000", "Suomi");
        p1.tulosta(System.out);

    }


}
