package sopimusrekisteri;

import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi: Sopimus                               | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   |
 * |                                                    |                   |
 * | - tietää sopimuksen kentät                         |                   |
 * | - osaa tarkistaa tietyn kentät oikeellisuuden      |                   |
 * |                                                    |                   |
 * | - osaa muuttaa merkkijonon sopimuksen tiedoiksi    |                   |
 * |(1|1|1|8000000|2019|2025)                           |                   |
 * | - osaa laittaa merkkijonon tietyn kentän tiedoiksi |                   |
 * | - osaa palauttaa tietyn kentän tiedot merkkijonona |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |-------------------------------------------------------------------------
 * @author Hannes Koivusipilä
 * @version 27.2.2020
 *
 */
public class Sopimus {
    private int sid;
    private int pid;
    private int jid;
    private int palkka;
    private int alkamisvuosi;
    private int loppumisvuosi;

    private static int seuraavaSid;
    
    /**
     * @param pid sopimuksen pelaajaosapuolen pid
     * @param jid sopimuksen joukkueosapuolen jid
     * @param palkka palkka kaudessa
     * @param alkamisvuosi alkamisvuosi
     * @param loppumisvuosi loppumisvuosi
     */
    public Sopimus(int pid, int jid, int palkka, int alkamisvuosi, int loppumisvuosi) {
        this.pid = pid;
        this.jid = jid;
        this.palkka = palkka;
        this.alkamisvuosi = alkamisvuosi;
        this.loppumisvuosi = loppumisvuosi;
    }
    
    
    /**
     * tyhjä constructor jota käytetään kun luodaan sopimus tiedostosta ladattaessa
     */
    public Sopimus() {
        //
    }


    /**kirjoittaa sopimuksen tiedot tuotuun tietovirtaan
     * @param out mihin tallennetaan
     */
    public void tallenna(PrintStream out) {
         out.println(sid + "|" + pid + "|" + jid + "|" + palkka + "|" + alkamisvuosi + "|" + loppumisvuosi);
    }
    
    /**muuttaa merkkijonon sopimuksen tiedoiksi
     * @param s merkkijono muodossa:
     * "000|123|987|10000000|2019|2020"
     *  sid|pid|jid|palkka  |alku|loppu
     */
    public void parse(String s) {
        var sb = new StringBuilder(s);
        this.sid = Integer.parseInt(Mjonot.erota(sb, '|'));
        this.pid = Integer.parseInt(Mjonot.erota(sb, '|'));
        this.jid = Integer.parseInt(Mjonot.erota(sb, '|'));
        this.palkka = Integer.parseInt(Mjonot.erota(sb, '|'));
        this.alkamisvuosi = Integer.parseInt(Mjonot.erota(sb, '|'));
        this.loppumisvuosi = Integer.parseInt(Mjonot.erota(sb, '|'));
        seuraavaSid = this.sid < seuraavaSid ? seuraavaSid : this.sid + 1;
    }
    
    
    
    /**palauttaa sopimuksen pelaajaosapuolen pelaaja-id:n
     * @return pid
     */
    public int getPid() {
        return pid;
    }
    
    /**palauttaa sopimuksen joukkueosapuolen joukkue-id:n
     * @return jid
     */
    public int getJid() {
        return jid;
    }
    
    /**pelauttaa sopimuksen pelaajaosapuolelle maksettavan vuotuisen korvauksen
     * @return pelaajan vuosipalkka
     */
    public int getPalkka() {
        return palkka;
    }
    
    /**palauttaa sopimuksen alkamisvuoden
     * @return sopimuksen alkamisvuosi
     */
    public int getAlkamisvuosi() {
        return alkamisvuosi;
    }
    
    /**palauttaa sopimuksen loppumisvuoden
     * @return sopimuksen loppumisvuosi
     */
    public int getLoppumisvuosi() {
        return loppumisvuosi;
    }
    
    /**aseta uusi arvo joukkue-id:lle
     * @param jid uusi joukkue-id
     */
    public void setJid(int jid) {
        this.jid = jid;
    }
    
    /** 
     * Antaa sopimukselle sopimus-id:n (sid) ja kasvattaa seuraavaa rekisteröitävää sopimus-id:tä yhdellä.
     * @return sid
     */
    public int rekisteroi() {
        sid = seuraavaSid++;
        return sid;
    }
}
