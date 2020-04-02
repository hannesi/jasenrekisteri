package fxSopimusrekisteri;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sopimusrekisteri.Liiga;

/**liigan tietojen muokkaamisen käytettävän ikkunan controller
 * @author hannesk
 * @version 2.4.2020
 *
 */
public class LiigaEditDialogController implements ModalControllerInterface<Liiga>, Initializable {
    
    @FXML private TextField editNimi;
    @FXML private TextField editPalkkaMin;
    @FXML private TextField editPalkkaMax;
    @FXML private TextField editKestoMax;
    @FXML private TextField editLkmMax;
    @FXML private TextField editMenotMin;
    @FXML private TextField editMenotMax;
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }
    
    @FXML private void handleTallenna() { //TODO: tarkistin ja visuaalinen palaute kokonaislukukentille
        try {
            liiga.muokkaa(editNimi.getText(),
                          Integer.parseInt(editPalkkaMin.getText()),
                          Integer.parseInt(editPalkkaMax.getText()),
                          Integer.parseInt(editKestoMax.getText()),
                          Integer.parseInt(editMenotMin.getText()),
                          Integer.parseInt(editMenotMax.getText()),
                          Integer.parseInt(editLkmMax.getText()));
        } catch (Exception e) {
            Dialogs.showMessageDialog("Virheellinen syöte:\n" + e.getMessage());
            return;
        }
        ModalController.closeStage(editMenotMax);
    }
    
    @FXML private void handlePeruuta() {
        liiga = null;
        ModalController.closeStage(editMenotMax);
    }
    
    
    
    
    //================================================================================================
    
    private Liiga liiga;
    
    @Override
    public Liiga getResult() {
        return liiga;
    }
    
    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }
    
    private void nayta(Liiga l) {
        if (l == null) return;
        editNimi.setText(l.getNimi());
        editPalkkaMin.setText("" + l.getMinPalkka());
        editPalkkaMax.setText("" + l.getMaxPalkka());
        editKestoMax.setText("" + l.getMaxPituus());    //TODO: nimeämiseen yhdenmukaisuutta
        editLkmMax.setText("" + l.getMaxSopimuksia());
        editMenotMin.setText("" + l.getPalkkalattia());
        editMenotMax.setText("" + l.getPalkkakatto());
    }
    
    @Override
    public void setDefault(Liiga oletus) {
        liiga = oletus;
        nayta(liiga);
        
    }
    
    
    /**Liigan tietojen muokkaaminen, joka palauttaa tuodun tietueen muokattuna tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param l liiga jota muokataan
     * @return null jos painetaan peruuta, muokattu liiga jos tallenna
     */
    public static Liiga kysyLiiga(Stage modalityStage, Liiga l) {
        return ModalController.showModal(LiigaEditDialogController.class.getResource("LiigaEditDialogView.fxml"), "Muokkaa liigaa", modalityStage, l, null);
        
    }

}
