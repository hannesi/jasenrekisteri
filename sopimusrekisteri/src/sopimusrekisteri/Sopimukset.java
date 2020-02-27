package sopimusrekisteri;

import java.util.ArrayList;

/**
 * @author Hannes Koivusipilä
 * @version 27.2.2020
 *
 */
public class Sopimukset {
    private ArrayList<Sopimus> sopimukset = new ArrayList<Sopimus>();
   
    
    /**lisää sopimuksen
     * @param s lisättävä sopimus
     */
    public void lisaa(Sopimus s) {
        sopimukset.add(s);
    }
    
    /**palauttaa sopimusten lukumäärän
     * @return sopimusten määrä
     */
    public int getLkm() {
        return sopimukset.size();
    }
    
    /**palauttaa sopimuksen listasta indeksin perusteella
     * @param indeksi haettavan sopimuksen indeksi
     * @return indeksin mukainen sopimus
     */
    public Sopimus get(int indeksi) {
        return sopimukset.get(indeksi);
    }
    
    /**poistaa sopimuksen
     * @param s poistettava sopimus
     */
    public void poista(Sopimus s) {
        sopimukset.remove(s);
    }


}
