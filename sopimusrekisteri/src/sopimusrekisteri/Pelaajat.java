package sopimusrekisteri;

/**
 * @author Hannes Koivusipilä
 * @version 19.2.2020
 *
 */
public class Pelaajat {

    private static final int    MAX_PELAAJIA    = 5;
    private int                 lkm             = 0;
    private String              tiedostonNimi   = "";
    private Pelaaja[]           alkiot;
    
    
    public Pelaajat() {
        alkiot = new Pelaaja[MAX_PELAAJIA];
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
     * @throws SailoException jos liikaa alkioita
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Pelaajat pelaajat = new Pelaajat();
     * Pelaaja p1 = new Pelaaja(), p2 = new Pelaaja();
     * pelaajat.getLkm() === 0;
     * pelaajat.lisaa(p1); pelaajat.getLkm() === 1;
     * pelaajat.lisaa(p2); pelaajat.getLkm() === 2;
     * pelaajat.lisaa(p2); pelaajat.getLkm() === 3;
     * pelaajat.anna(1) == p1 === false;
     * pelaajat.anna(1) == p2 === true;
     * pelaajat.anna(3) === p2; #THROWS IndexOutOfBoundsException
     * pelaajat.lisaa(p1); pelaajat.getLkm() === 4;
     * pelaajat.lisaa(p1); pelaajat.getLkm() === 5;
     * pelaajat.lisaa(p1); #THROWS SailoException
     */
    public void lisaa(Pelaaja p) throws SailoException {
        if (lkm >= alkiot.length) throw new SailoException("Liikaa alkioita!");
        alkiot[lkm++] = p;
    }
    
    
    /**
     * palauttaa i:nnen pelaajan alkiot-taulukosta
     * @param i monekso pelaaja haetaan
     * @return Pelaaja alkiot-taulukon kohdasta i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella
     */
    public Pelaaja anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i) throw new IndexOutOfBoundsException("Laiton indeksi:" + i);
        return alkiot[i];
    }
    
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Pelaajat pelaajat = new Pelaajat();
        Pelaaja p1 = new Pelaaja();
        Pelaaja p2 = new Pelaaja();
        p1.taytaPelaaja();
        p1.rekisteroi();
        p2.taytaPelaaja();
        p2.rekisteroi();
        

        try {
            pelaajat.lisaa(p1);
            pelaajat.lisaa(p2);
            pelaajat.lisaa(p2);
            pelaajat.lisaa(p2);
            pelaajat.lisaa(p2);
            pelaajat.lisaa(p2);
            pelaajat.lisaa(p2);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }

        
        System.out.println("===Pelaajat-luokan testit alla===");
        for (int i = 0; i < pelaajat.getLkm(); i++) {
            Pelaaja pelaaja = pelaajat.anna(i);
            pelaaja.tulosta(System.out);
        }

    }

}
