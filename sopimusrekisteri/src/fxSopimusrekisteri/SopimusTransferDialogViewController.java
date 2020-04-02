package fxSopimusrekisteri;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import sopimusrekisteri.Joukkue;
import sopimusrekisteri.Sopimus;

/**sopimuksen siirtämisen käsittelevän ikkunan controller
 * @author hannesk
 * @version 3.4.2020
 *
 */
public class SopimusTransferDialogViewController implements ModalControllerInterface<Sopimus>, Initializable {
    
    @FXML ComboBoxChooser<Joukkue> cbJoukkueValitsin;
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }
    
    @FXML private void handleTallenna() {
        sopimus.setJid(cbJoukkueValitsin.getSelectedObject().getJid());
        ModalController.closeStage(cbJoukkueValitsin);
    }
    
    @FXML private void handlePeruuta() {
        ModalController.closeStage(cbJoukkueValitsin);
    }
    
    //====================================================================================
    private static List<Joukkue> joukkueet;
    private Sopimus sopimus;


    @Override
    public Sopimus getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(Sopimus oletus) {
        sopimus = oletus;
        nayta(sopimus);
        
    }
    private void nayta(Sopimus s) {
        if (s == null) return;
        for (Joukkue j : joukkueet)
            cbJoukkueValitsin.add(j.getNimiPitka(), j);
        //TODO: nykyinen joukkue eka valinnaksi
    }

    /**sopimuksen siirtäminen
     * @param modalityStage mille ollaan modaalisia
     * @param s siirrettävä sopimus
     * @param j lista joukkueista joihin sopimus voidaan siirtää
     */
    public static void muokkaa(Stage modalityStage, Sopimus s, List<Joukkue> j) {
        joukkueet = j;
        ModalController.showModal(SopimusTransferDialogViewController.class.getResource("SopimusTransferDialogView.fxml"), "Siirrä sopimus", modalityStage, s, null);
    }



}
