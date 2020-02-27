package fxSopimusrekisteri;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JTextField;

import fi.jyu.mit.fxgui.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import sopimusrekisteri.*;

/**
 * @author Hannes Koivusipilä
 * @version 9.2.2020
 *
 */
public class SopimusrekisteriGUIController implements Initializable {

    
    //Pelaaja-tabi
    @FXML ListChooser<Pelaaja> chooserPelaajat;
    @FXML ListChooser<Joukkue> chooserJoukkueet;
    @FXML ListChooser<Liiga> chooserLiigat;
    @FXML TextField pTextfieldSukunimi;
    @FXML TextField pTextfieldEtunimi;
    @FXML TextField pTextfieldSyntymaaika;
    @FXML TextField pTextfieldKansallisuus;
    @FXML TextField pTextfieldJoukkue;
    @FXML TextField pTextfieldSopimusAlku;
    @FXML TextField pTextfieldSopimusLoppu;
    @FXML TextField pTextfieldPalkka;
    
    @FXML TextField jTextfieldNimi;
    @FXML TextField jTextfieldKaupunki;
    @FXML TextField jTextfieldOmistaja;
    @FXML TextField jTextfieldYhteystieto;
    
    @FXML TextField lTextfieldNimi;
    @FXML TextField lTextfieldMinPalkka;
    @FXML TextField lTextfieldMaxPalkka;
    @FXML TextField lTextfieldMaxKesto;
    @FXML TextField lTextfieldMaxLkm;
    @FXML TextField lTextfieldLattia;
    @FXML TextField lTextfieldKatto;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
            alusta();
    }
    
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
        liigaLisaa();
    }

    @FXML void handleSarjaMuokkaa() {
        liigaMuokkaa();
    }

    @FXML void handleSarjaPoista() {
        liigaPoista();
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
      
    //=====================================Tämän alapuolella ie suoraa käyttöliittymään liittyvää koodia================================================
    
    private Sopimusrekisteri sopimusrekisteri;
    
    private void alusta() {
        chooserPelaajat.clear();
        chooserPelaajat.addSelectionListener(e -> naytaPelaaja());
        chooserJoukkueet.clear();
        chooserJoukkueet.addSelectionListener(e -> naytaJoukkue());
        chooserLiigat.clear();
        chooserLiigat.addSelectionListener(e -> naytaLiiga());
    }
    
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
        Joukkue j = new Joukkue();
        j.rekisteroi();
        j.taytaJoukkue();//TODO: Korvaa oikealla dialogilla
        sopimusrekisteri.lisaa(j);

        haeJoukkue(j.getJid());
        //ModalController.showModal(SopimusrekisteriGUIController.class.getResource("JoukkueNewDialogView.fxml"), "Lisää joukkue", null, "");
    }
    
    //hakee joukkueen tiedot ruudulle jidin perusteella
    //TODO: tämä korvataan samoin kuin haePelaaja
    private void haeJoukkue(int jid) {
        chooserJoukkueet.clear();
        int index = 0;
        for (int i = 0; i < sopimusrekisteri.getJoukkueita(); i++) {
            Joukkue j = sopimusrekisteri.getJoukkue(i);
            if (j.getJid() == jid) index = i;
            chooserJoukkueet.add(j.getNimiPitka(), j);
        }
        chooserJoukkueet.setSelectedIndex(index);
        naytaJoukkue();
    } 
    
  //Näyttää valitun joukkueen tiedot kentissä
    private void naytaJoukkue() {
        Joukkue jKohdalla = chooserJoukkueet.getSelectedObject();
        if (jKohdalla == null) return;
        
        jTextfieldNimi.setText(jKohdalla.getNimi());
        jTextfieldKaupunki.setText(jKohdalla.getKaupunki());
        jTextfieldOmistaja.setText(jKohdalla.getOmistaja());
        jTextfieldYhteystieto.setText(jKohdalla.getYhteystieto());
        }
    
    
    private void joukkueMuokkaa() {
        ModalController.showModal(SopimusrekisteriGUIController.class.getResource("JoukkueEditDialogView.fxml"), "Muokkaa joukkuetta", null, "");
    }
    
    private void joukkuePoista() {
        Joukkue jKohdalla = chooserJoukkueet.getSelectedObject();
        if (jKohdalla == null) return;
        sopimusrekisteri.poista(jKohdalla);
        haeJoukkue(-1);
        //ModalController.showModal(SopimusrekisteriGUIController.class.getResource("JoukkueRemoveDialogView.fxml"), "Poista joukkue", null, "");
    }
    
    
    private void pelaajaLisaa() {
        Pelaaja p = new Pelaaja();
        p.rekisteroi();
        p.taytaPelaaja();//TODO: Korvaa oikealla dialogilla
        sopimusrekisteri.lisaa(p);

        haePelaaja(p.getPid());
        //ModalController.showModal(SopimusrekisteriGUIController.class.getResource("PelaajaNewDialogView.fxml"), "Lisää pelaaja", null, "");
    }
    
    //mikähän pointti pidin perusteella hakemisessa oli olevinaan?
    //TODO: korvaa päivitä-metodilla jolle tuodaan lista listattavista pelaajista parametrina(?)
    private void haePelaaja(int pid) {
        chooserPelaajat.clear();
        int index = 0;
        for (int i = 0; i < sopimusrekisteri.getPelaajia(); i++) {
            Pelaaja p = sopimusrekisteri.getPelaaja(i);
            if (p.getPid() == pid) index = i;
            chooserPelaajat.add(p.getNimi(), p);
        }
        chooserPelaajat.setSelectedIndex(index);
        naytaPelaaja();
    }
    
    //Näyttää valitun pelaajan tiedot kentissä
    private void naytaPelaaja() {
        Pelaaja pKohdalla = chooserPelaajat.getSelectedObject();
        if (pKohdalla == null) return;

        pTextfieldSukunimi.setText(pKohdalla.getSukunimi());
        pTextfieldEtunimi.setText(pKohdalla.getEtunimi());
        pTextfieldSyntymaaika.setText(pKohdalla.getSyntymaaika());
        pTextfieldKansallisuus.setText(pKohdalla.getKansallisuus());
        
        //pTextfieldJoukkue.setText(sopimusrekisteri.getPelaajanJoukkue(pKohdalla).getNimiPitka());
        /*
        areaLisenssi.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaLisenssi)){
            pKohdalla.tulosta(os); 
        }
        */
        
    }
    
    private void pelaajaMuokkaa() {
        ModalController.showModal(SopimusrekisteriGUIController.class.getResource("PelaajaEditDialogView.fxml"), "Muokkaa pelaajaa", null, "");
    }
    
    private void pelaajaPoista() {
        Pelaaja pKohdalla = chooserPelaajat.getSelectedObject();
        if (pKohdalla == null) return;
        sopimusrekisteri.poista(pKohdalla);
        haePelaaja(-1);
        //ModalController.showModal(SopimusrekisteriGUIController.class.getResource("PelaajaRemoveDialogView.fxml"), "Poista pelaaja", null, "");
    }
    
    private void liigaLisaa() {
        Liiga l = new Liiga();
        l.rekisteroi();
        l.taytaLiiga();//TODO: Korvaa oikealla dialogilla
        sopimusrekisteri.lisaa(l);

        haeLiiga(l.getLid());
        //ModalController.showModal(SopimusrekisteriGUIController.class.getResource("SarjaNewDialogView.fxml"), "Lisää sarja", null, "");
    }
    
  //Näyttää valitun liigan tiedot kentissä
    private void naytaLiiga() {
        Liiga lKohdalla = chooserLiigat.getSelectedObject();
        if (lKohdalla == null) return;

        lTextfieldNimi.setText(lKohdalla.getNimi());
        lTextfieldMinPalkka.setText(Integer.toString(lKohdalla.getMinPalkka()));
        lTextfieldMaxPalkka.setText(Integer.toString(lKohdalla.getMaxPalkka()));
        lTextfieldMaxKesto.setText(Integer.toString(lKohdalla.getMaxPituus()));
        lTextfieldLattia.setText(Integer.toString(lKohdalla.getPalkkalattia()));
        lTextfieldKatto.setText(Integer.toString(lKohdalla.getPalkkakatto()));
        lTextfieldMaxLkm.setText(Integer.toString(lKohdalla.getMaxSopimuksia()));
    }
    
    
    
    private void liigaMuokkaa() {
        ModalController.showModal(SopimusrekisteriGUIController.class.getResource("SarjaEditDialogView.fxml"), "Muokkaa sarjaa", null, "");
    }
    
    //TODO: sama hoito kuin haePelaaja
    private void haeLiiga(int lid) {
        chooserLiigat.clear();
        int index = 0;
        for (int i = 0; i < sopimusrekisteri.getLiigoja(); i++) {
            Liiga l = sopimusrekisteri.getLiiga(i);
            if (l.getLid() == lid) index = i;
            chooserLiigat.add(l.getNimi(), l);
        }
        chooserLiigat.setSelectedIndex(index);
        naytaLiiga();
    }
    
    private void liigaPoista() {
        Liiga lKohdalla = chooserLiigat.getSelectedObject();
        if (lKohdalla == null) return;
        sopimusrekisteri.poista(lKohdalla);
        haeLiiga(-1);
        //ModalController.showModal(SopimusrekisteriGUIController.class.getResource("SarjaRemoveDialogView.fxml"), "Poista sarja", null, "");
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

    /**
     * Asetetaan kontrollerin sopimusrekisteriviite
     * @param sopimusrekisteri sopimusrekisteri johon viitataan
     */
    public void setSopimusrekisteri(Sopimusrekisteri sopimusrekisteri) {
        this.sopimusrekisteri = sopimusrekisteri;
        
    }
    
}