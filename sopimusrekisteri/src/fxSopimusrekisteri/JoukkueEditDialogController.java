package fxSopimusrekisteri;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sopimusrekisteri.Joukkue;
import sopimusrekisteri.Liiga;

/**
 * joukkueen tietojen muokkaamiseen käytettävän ikkunan controller
 * @author Hannes Koivusipilä
 * @version 2.4.2020
 *
 */
public class JoukkueEditDialogController implements ModalControllerInterface<Joukkue>, Initializable {
    
    @FXML private ComboBoxChooser<Liiga> cbLiigaValitsin;
    @FXML private TextField editNimi;
    @FXML private TextField editKaupunki;
    @FXML private TextField editOmistaja;
    @FXML private TextField editYhteystieto;
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }
    
    //TODO: tarkistin yhteystietoon
    @FXML private void handleTallenna() {
        joukkue.muokkaa(editNimi.getText(), 
                        editKaupunki.getText(), 
                        editOmistaja.getText(), 
                        editYhteystieto.getText(),
                        cbLiigaValitsin.getSelectedObject().getLid());
        ModalController.closeStage(editKaupunki);
    }
    
    @FXML private void handlePeruuta() {
        joukkue = null;
        ModalController.closeStage(editKaupunki);
    }

    
    
    //=======================================================================================================
    
    private Joukkue joukkue;
    private static List<Liiga> liigat;
    
    @Override
    public Joukkue getResult() {
        return joukkue;
    }
    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }
    
    private void nayta(Joukkue j) {
        if (j==null) return;

        int indeksi = 0;
        int valinta = 0;
        for (Liiga l : liigat) {
            cbLiigaValitsin.add(l.getNimi(), l);
            if (l.getLid() == j.getLid())
                valinta = indeksi;
            indeksi++;
        }
        cbLiigaValitsin.setSelectedIndex(valinta);
        
        editNimi.setText(j.getNimi());
        editKaupunki.setText(j.getKaupunki());
        editOmistaja.setText(j.getOmistaja());
        editYhteystieto.setText(j.getYhteystieto());
    }
    
    @Override
    public void setDefault(Joukkue oletus) {
        joukkue = oletus;
        nayta(joukkue);
    }
    

    /**Joukkueen tietojen muokkaaminen, joka palauttaa tuodun tietueen muokattuna tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param j joukkue jota muokataan
     * @param l lista liigoista joista joukkueen liiga valitaan
     * @return null jos painetaan peruuta, muokattu joukkue jos tallenna
     */
    public static Joukkue kysyJoukkue(Stage modalityStage, Joukkue j, List<Liiga> l) {
        liigat = l;
        return ModalController.showModal(JoukkueEditDialogController.class.getResource("JoukkueEditDialogView.fxml"), "Muokkaa joukkuetta", modalityStage, j, null);
    }
    
}
