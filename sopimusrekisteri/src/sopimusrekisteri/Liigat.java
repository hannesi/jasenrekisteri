package sopimusrekisteri;

import java.util.ArrayList;

/**
 * @author Hannes Koivusipilä
 * @version 27.2.2020
 *
 */
public class Liigat {
    
    private String              tiedostonNimi   = "";
    private ArrayList<Liiga>    liigat          = new ArrayList<Liiga>();

    /**lisää liiga
     * @param l lisättävä liiga
     * @example
     * <pre name="test">
     *   var liigat = new Liigat();
     *   var l1 = new Liiga();
     *   var l2 = new Liiga();
     *   l1.taytaLiiga();
     *   l2.taytaLiiga();
     *   liigat.lisaa(l1);
     *   liigat.getLkm() === 1;
     *   liigat.lisaa(l2);
     *   liigat.getLkm() === 2;
     *   l1.getNimi() == l2.getNimi() === true;
     *   l1.muokkaa("Kaljaliiga", 500, 10000, 2, 5000, 25000, 25); 
     *   l1.getNimi() == l2.getNimi() === false;
     * </pre>
     */
    public void lisaa(Liiga l) {
        liigat.add(l);
    }
    
    /**palauttaa liigojen lukumäärän
     * @return liigojen lukumäärä
     */
    public int getLkm() {
        return liigat.size();
    }
    
    /**palauttaa liigan listasta indeksin perusteella
     * @param indeksi palautettavan liigan indeksi
     * @return liiga
     */
    public Liiga get(int indeksi) {
        return liigat.get(indeksi);
    }
    
    /**poistaa liigan
     * @param l poistettava liiga
     *
     */
    //TODO: kun liiga poistetaan poistetaan myös siinä olevat joukkueet
    public void poista(Liiga l) {
        liigat.remove(l);
    }
    
}
