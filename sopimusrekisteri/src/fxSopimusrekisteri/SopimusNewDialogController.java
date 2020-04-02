package fxSopimusrekisteri;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sopimusrekisteri.Joukkue;
import sopimusrekisteri.Sopimus;

/**sopimuksen luomiseen käytettävän ikkunan controller
 * @author hannesk
 * @version 2.4.2020
 *
 */
public class SopimusNewDialogController implements ModalControllerInterface<Sopimus>, Initializable {
    
    @FXML private ComboBoxChooser<Joukkue> cbJoukkueValitsin;
    @FXML private TextField editAlku;
    @FXML private TextField editLoppu;
    @FXML private TextField editPalkka;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }
    
    @FXML private void handleTallenna() {
        try {
            sopimus.muokkaa(cbJoukkueValitsin.getSelectedObject().getJid(),
                            Integer.parseInt(editAlku.getText()),
                            Integer.parseInt(editLoppu.getText()),
                            Integer.parseInt(editPalkka.getText()));
        } catch (Exception e) {
            Dialogs.showMessageDialog("Virheellinen syöte:\n" + e.getMessage());
            return;
        }
        ModalController.closeStage(editAlku);
    }
    
    @FXML private void handlePeruuta() {
        sopimus = null;
        ModalController.closeStage(editAlku);
    }
    
    
    //==========================================================================================

    private Sopimus sopimus;
    private static List<Joukkue> joukkueet;
    
    @Override
    public Sopimus getResult() {
        return sopimus;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    private void nayta(Sopimus s) {
        if (s == null) return;
        for (Joukkue j : joukkueet)
            cbJoukkueValitsin.add(j.getNimiPitka(), j);
        editAlku.setText("" + s.getAlkamisvuosi());
        editLoppu.setText("" + s.getLoppumisvuosi());
        editPalkka.setText("" + s.getPalkka());
        
    }
    
    @Override
    public void setDefault(Sopimus oletus) {
        sopimus = oletus;
        nayta(sopimus);
        
    }
    
    /**Sopimuksen tietojen muokkaaminen, joka palauttaa tuodun tietueen muokattuna tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param s muokattava sopimus
     * @param j lista joukkueista joiden kanssa sopimus voidaan tehdä
     * @return null jos painetaan peruuta, muokattu sopimus jos tallenna
     */
    public static Sopimus kysySopimus(Stage modalityStage, Sopimus s, List<Joukkue> j) {
        joukkueet = j;
        return ModalController.showModal(SopimusNewDialogController.class.getResource("SopimusNewDialogView.fxml"), "Luo sopimus", modalityStage, s, null);
        
    }

}
