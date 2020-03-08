package sopimusrekisteri;

import java.util.ArrayList;

/**
 * |--------------------------------------------------------------------------|
 * | Luokan nimi: Sopimukset                              | Avustajat:        |
 * |---------------------------------------------------------------------------
 * | Vastuualueet:                                        |                   |
 * |                                                      | - Sopimus         |
 * | - pitää yllä rekisteriä sopimuksista eli osaa lisätä,|                   |
 * |   poistaa ja muokata sopimuksia                      |                   |
 * | - lukee sopimukset tiedostosta ja kirjoittaa         |                   |
 * |   tiedostoon                                         |                   |
 * | - osaa etsiä ja lajitella sopimuksia                 |                   |
 * |                                                      |                   |
 * |                                                      |                   |
 * |                                                      |                   |
 * |                                                      |                   |
 * |                                                      |                   |
 * |                                                      |                   |
 * |                                                      |                   |
 * |                                                      |                   |
 * |---------------------------------------------------------------------------
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
    
    public void poistaByJid(int jid) {
        for (Sopimus s : sopimukset)
            if (s.getJid() == jid)
                poista(s);
    }
    

    //===============================================================================================
    //Tämän alapuolella hakuun liittyviä aliohjelmia
    
    /**palauttaa tuodun pid vastaavan pelaajan sopimuksen joukkueosapuolen jid
     * @param pid pelaaja-id jonka joukkuetta haetaan
     * @return joukkueen jid
     * @throws SailoException jos pid:lle ei löydy sopimusta 
     */
    public int getJidByPid(int pid) throws SailoException {
        for (int i = 0; i < sopimukset.size(); i++)
            if (sopimukset.get(i).getPid() == pid)
                return sopimukset.get(i).getJid();
        throw new SailoException("Ei löytynyt sopimusta jossa pid: " + pid);
    }

    /**palauttaa sopimuksen pid perusteella
     * @param pid haettavan sopimuksen pelaajaosapuolen pid
     * @return pelaajan sopimus
     */
    public Sopimus getByPid(int pid) {
        for (int i = 0; i < sopimukset.size(); i++)
            if (sopimukset.get(i).getPid() == pid)
                return sopimukset.get(i);
        //throw new SailoException("Ei löytynyt sopimusta jossa pid: " + pid);
        return null;
    }

}
