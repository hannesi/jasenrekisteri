package sopimusrekisteri;

/**poikkeusluokka tietorakenteesta aiheutuville poikkeuksille
 *
 */
public class SailoException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /**
     * pikkeuksen muodostaja jolle tuodaan poikkeuksessa käytettävä viesti
     * @param viesti pokkeuksen viesti
     */
    public SailoException(String viesti) {
        super(viesti);
    }

}
