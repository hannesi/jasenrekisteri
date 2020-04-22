package fxSopimusrekisteri;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import misc.Tarkistimet;
import sopimusrekisteri.Pelaaja;

/**pelaajan tietojen muokkaamiseen käytettävä ikkunan controller
 * @author hannesk
 * @version 18.3.2020
 *
 */
public class PelaajaEditDialogController implements ModalControllerInterface<Pelaaja>,Initializable {
    
    @FXML private TextField editEtunimi;
    @FXML private TextField editSukunimi;
    @FXML private TextField editSyntymaaika;
    @FXML private TextField editKansallisuus;
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }
    
    //TODO: tarkistin syntymäaikaan
    @FXML private void handleTallenna() {
        if (!Tarkistimet.onkoPvm(editSyntymaaika.getText())) {
            Dialogs.showMessageDialog("Syntymäaika ei ole kelvollinen päivämäärä! Käytä muotoa 01.01.2020");
            return;
        }
        pelaaja.muokkaa(editSukunimi.getText(), 
                        editEtunimi.getText(), 
                        editSyntymaaika.getText(), 
                        editKansallisuus.getText());
        ModalController.closeStage(editEtunimi);
    }
    
    @FXML private void handlePeruuta() {
        pelaaja = null;
        ModalController.closeStage(editEtunimi);
    }
    
    @FXML private void handleSyntymaaikaChanged() {
        if (Tarkistimet.onkoPvm(editSyntymaaika.getText())) editSyntymaaika.setStyle("");
        else editSyntymaaika.setStyle("-fx-background-color: red");
    }
    
    //==========================================================================================================================

    private Pelaaja pelaaja;
    
    @Override
    public Pelaaja getResult() {
        return pelaaja;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }
    
    
    private void nayta(Pelaaja p) {
        if (p == null) return;
        editEtunimi.setText(p.getEtunimi());
        editSukunimi.setText(p.getSukunimi());
        editSyntymaaika.setText(p.getSyntymaaika());
        editKansallisuus.setText(p.getKansallisuus());
    }
    

    @Override
    public void setDefault(Pelaaja oletus) {
        pelaaja = oletus;
        nayta(pelaaja);
        
    }

    /**Pelaajan tietojen muokkaaminen, joka palauttaa tuodun tietueen muokattuna tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param p pelaaja jota muokataan
     * @return null jos painetaan peruuta, muokattu pelaaja jos tallenna
     */
    public static Pelaaja kysyPelaaja(Stage modalityStage, Pelaaja p) {
        return ModalController.showModal(PelaajaEditDialogController.class.getResource("PelaajaEditDialogView.fxml"), "Muokkaa pelaajaa", modalityStage, p, null);
        
    }

}
