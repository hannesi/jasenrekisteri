package sopimusrekisteri;

import fi.jyu.mit.fxgui.*;
import javafx.fxml.FXML;

/**
 * @author Hannes Koivusipilä
 * @version 9.2.2020
 *
 */
public class SopimusrekisteriGUIController {
    
    @FXML void handleApua() {
        apua();
    }

    @FXML void handleJoukkueLisaa() {
        joukkueLisaa();
    }

    @FXML void handleJoukkueMuokkaa() {
        joukkueMuokkaa();
    }

    @FXML void handleJoukkuePoista() {
        joukkuePoista();
    }

    @FXML void handleLataa() {
        lataa();
    }

    @FXML void handlePelaajaLisaa() {
        pelaajaLisaa();
    }

    @FXML void handlePelaajaMuokkaa() {
        pelaajaMuokkaa();
    }

    @FXML void handlePelaajaPoista() {
        pelaajaPoista();
    }

    @FXML void handleSarjaLisaa() {
        sarjaLisaa();
    }

    @FXML void handleSarjaMuokkaa() {
        sarjaMuokkaa();
    }

    @FXML void handleSarjaPoista() {
        sarjaPoista();
    }

    @FXML void handleSopimusPoista() {
        sopimusPoista();
    }

    @FXML void handleSopimusSiirra() {
        sopimusSiirra();
    }

    @FXML void handleSopimusUusi() {
        sopimusUusi();
    }

    @FXML void handleSulje() {
        sulje();
    }

    @FXML void handleTallenna() {
        tallenna();
    }

    @FXML void handleTarkistaJoukkuuet() {
        tarkistaJoukkueet();
    }

    @FXML void handleTietoja() {
        tietoja();
    }
      
    //=====================================================================================
    
    private void tallenna() {
        Dialogs.showMessageDialog("Ei osata vielä tallentaa!");
    }
    
    private void lataa() {
        Dialogs.showMessageDialog("Ei osata vielä ladata!");
    }
    
    private void sulje() {
        Dialogs.showMessageDialog("Ei osata vielä sulkea!");
    }
    
    private void tarkistaJoukkueet() {
        Dialogs.showMessageDialog("Ei osata vielä tarkistaa joukkueita!");
    }
    
    private void apua() {
        Dialogs.showMessageDialog("Ei osata vielä auttaa!");
    }
    
    private void tietoja() {
        Dialogs.showMessageDialog("Sopimusrekisteri\n\nHannes Koivusipilä");
    }
    
    private void joukkueLisaa() {
        ModalController.showModal(SopimusrekisteriGUIController.class.getResource("JoukkueNewDialogView.fxml"), "Lisää joukkue", null, "");
    }
    
    private void joukkueMuokkaa() {
        ModalController.showModal(SopimusrekisteriGUIController.class.getResource("JoukkueEditDialogView.fxml"), "Muokkaa joukkuetta", null, "");
    }
    
    private void joukkuePoista() {
        ModalController.showModal(SopimusrekisteriGUIController.class.getResource("JoukkueRemoveDialogView.fxml"), "Poista joukkue", null, "");
    }
    
    private void pelaajaLisaa() {
        ModalController.showModal(SopimusrekisteriGUIController.class.getResource("PelaajaNewDialogView.fxml"), "Lisää pelaaja", null, "");
    }
    
    private void pelaajaMuokkaa() {
        ModalController.showModal(SopimusrekisteriGUIController.class.getResource("PelaajaEditDialogView.fxml"), "Muokkaa pelaajaa", null, "");
    }
    
    private void pelaajaPoista() {
        ModalController.showModal(SopimusrekisteriGUIController.class.getResource("PelaajaRemoveDialogView.fxml"), "Poista pelaaja", null, "");
    }
    
    private void sarjaLisaa() {
        ModalController.showModal(SopimusrekisteriGUIController.class.getResource("SarjaNewDialogView.fxml"), "Lisää sarja", null, "");
    }
    
    private void sarjaMuokkaa() {
        ModalController.showModal(SopimusrekisteriGUIController.class.getResource("SarjaEditDialogView.fxml"), "Muokkaa sarjaa", null, "");
    }
    
    private void sarjaPoista() {
        ModalController.showModal(SopimusrekisteriGUIController.class.getResource("SarjaRemoveDialogView.fxml"), "Poista sarja", null, "");
    }
    
    private void sopimusPoista() {
        ModalController.showModal(SopimusrekisteriGUIController.class.getResource("SopimusRemoveDialogView.fxml"), "Poista sopimus", null, "");
    }
    
    private void sopimusUusi() {
        ModalController.showModal(SopimusrekisteriGUIController.class.getResource("SopimusNewDialogView.fxml"), "Uusi sopimus", null, "");
    }
    
    private void sopimusSiirra() {
        ModalController.showModal(SopimusrekisteriGUIController.class.getResource("SopimusTransferDialogView.fxml"), "Siirrä sopimus", null, "");
    }
    
}