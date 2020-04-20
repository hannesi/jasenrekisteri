package misc;

import java.text.SimpleDateFormat;
import java.util.Date;

/**oikeellisuustarkistimia
 * @author Hannes Koivusipilä
 * @version 20.4.2020
 *
 */
public class Tarkistimet {
    
    /**tarkistaa, onko merkkijono hyväksyttävässä päivämäärämuodossa (01.04.2020)
     * @param s merkkijono
     * @return true jos merkkijono on käypä päivämäärä
     * @example
     * <pre name="test">
     * onkoPvm("01.01.2019") === true;
     * onkoPvm("1.1.2019") === true;
     * onkoPvm("01.2.2020") === true;
     * onkoPvm("10. 10. 2020") === true;
     * onkoPvm("29.02.2019") === false;
     * onkoPvm("29.02.2020") === true;
     * </pre>
     */
    public static boolean onkoPvm(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.setLenient(false);
        try {
            sdf.parseObject(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    /**palauttaa tuodun merkkijonon 01.01.1900 -muotoisena päivämäärämerkkijonona
     * @param s muotoiltava pvm
     * @return muotoiltu string
     * @example
     * <pre name="test">
     * muotoiltuPvm("02.03.2019") === "02.03.2019";
     * muotoiltuPvm("5.6.2020") === "05.06.2020";
     * muotoiltuPvm("20.   1.      2005") === "20.01.2005";
     * muotoiltuPvm("elokuun ensimmäinen torstai") === "Merkkijonosta \"elokuun ensimmäinen torstai\" ei saatu parsittua päivämäärää!";
     * </pre>
     */
    public static String muotoiltuPvm(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.setLenient(false);
        String palautettava = "";
        try {
            Date date = sdf.parse(s);
            palautettava = sdf.format(date);
        } catch (Exception e) {
            palautettava = "Merkkijonosta \"" + s + "\" ei saatu parsittua päivämäärää!";
        }
        return palautettava;
    }
}
