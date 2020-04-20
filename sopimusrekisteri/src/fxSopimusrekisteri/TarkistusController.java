package fxSopimusrekisteri;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**Tarkistus-ikkunan controlleri
 * @author hannesk
 * @version 5.4.2020
 *
 */
public class TarkistusController implements ModalControllerInterface<String> {
    
    @FXML TextArea textAreaMain;
    
    @FXML private void handleSulje() {
        ModalController.closeStage(textAreaMain);
    }
    

    @Override
    public String getResult() {
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(String oletus) {
        textAreaMain.setText(oletus);
    }
    
    /**Näyttää tuodun merkkijonon textareassa
     * @param s merkkijono
     * @return .
     */
    public static TarkistusController tarkista(String s) {
        TarkistusController tarkistusCtrl = ModalController.showModeless(TarkistusController.class.getResource("TarkistusView.fxml"), "Joukkueiden tarkistus", s);
        return tarkistusCtrl;
    }

}
