package fxSopimusrekisteri;

import java.net.URL;
import java.util.ResourceBundle;

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
    @FXML TextField pTextfieldSukunimi;
    @FXML TextField pTextfieldEtunimi;
    @FXML TextField pTextfieldSyntymaaika;
    @FXML TextField pTextfieldKansallisuus;
    @FXML TextField pTextfieldJoukkue;
    @FXML TextField pTextfieldSopimusAlku;
    @FXML TextField pTextfieldSopimusLoppu;
    @FXML TextField pTextfieldPalkka;

    //Joukkue-tabi
    @FXML ListChooser<Joukkue> chooserJoukkueet;
    @FXML TextField jTextfieldNimi;
    @FXML TextField jTextfieldKaupunki;
    @FXML TextField jTextfieldOmistaja;
    @FXML TextField jTextfieldYhteystieto;
    @FXML TextField jTextfieldSopimuksia;
    @FXML TextField jTextfieldPalkat;
    @FXML TextField jTextfieldSarja;

    //Liiga-tabi
    @FXML ListChooser<Liiga> chooserLiigat;
    @FXML TextField lTextfieldNimi;
    @FXML TextField lTextfieldMinPalkka;
    @FXML TextField lTextfieldMaxPalkka;
    @FXML TextField lTextfieldMaxKesto;
    @FXML TextField lTextfieldMaxLkm;
    @FXML TextField lTextfieldLattia;
    @FXML TextField lTextfieldKatto;
    
    //hakuvärmeet
    @FXML TextField editHakusanaPelaaja;
    @FXML TextField editHakusanaJoukkue;
    @FXML TextField editHakusanaLiiga;
    @FXML ComboBoxChooser<String> cbHakuvalitsinPelaaja;
    @FXML ComboBoxChooser<String> cbHakuvalitsinJoukkue;
    
    
    
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
    
    
    @FXML void handleTabPelaaja() {
        naytaPelaaja();
    }
    
    @FXML void handleTabJoukkue() {
        naytaJoukkue();
    }

    @FXML void handleTabLiiga() {
        naytaLiiga();
    }
    
    @FXML void handleHaePelaaja() {
        haePelaaja(-1);
    }
    
    @FXML void handleHaeJoukkue() {
        haeJoukkue(-1);
    }
    
    @FXML void handleHaeLiiga() {
        haeLiiga(-1);
    }
    
    
      
    //=====================================Tämän alapuolella ei suoraa käyttöliittymään liittyvää koodia================================================
    
    private Sopimusrekisteri    sopimusrekisteri;
    private Pelaaja             pKohdalla;
    private Joukkue             jKohdalla;
    private Liiga               lKohdalla;
    
    
    // alustaa chooserit ja lisää kuuntelijat
    private void alusta() {
        chooserPelaajat.clear();
        chooserPelaajat.addSelectionListener(e -> naytaPelaaja());
        chooserJoukkueet.clear();
        chooserJoukkueet.addSelectionListener(e -> naytaJoukkue());
        chooserLiigat.clear();
        chooserLiigat.addSelectionListener(e -> naytaLiiga());
        
    }
    
    //tallentaa pelaajat, joukkueet, liigat ja sopimukset
    private void tallenna() {
        try {
            sopimusrekisteri.tallenna();
        } catch (SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
        //Dialogs.showMessageDialog("Ei osata vielä tallentaa!");
    }
    
    //sulkee ohjelman
    private void sulje() {
        Dialogs.showMessageDialog("Ei osata vielä sulkea!");
    }
    
    
    //tarkistaa joukkueet ja näyttää tulokset uudessa ikkunassa
    private void tarkistaJoukkueet() {
        try {
            TarkistusController.tarkista(sopimusrekisteri.tarkistaJoukkueet());
        } catch (SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
    }
    
    //näyttää apustuksen
    private void apua() {
        Dialogs.showMessageDialog("Ei osata vielä auttaa!");
    }
    
    //näyttää tietoja ohjelmasta
    private void tietoja() {
        Dialogs.showMessageDialog("Sopimusrekisteri\n\nHannes Koivusipilä");
    }
    
    //avaa dialogin joukkueen lisäämiseksi
    private void joukkueLisaa() {
        Joukkue j = new Joukkue();
        j = JoukkueEditDialogController.kysyJoukkue(null, j, sopimusrekisteri.getKaikkiLiigatSorted());
        if (j == null) return;
        j.rekisteroi();
        sopimusrekisteri.lisaa(j);

        haeJoukkue(j.getJid());
    }
    
    //hakee joukkueet chooseriin
    private void haeJoukkue(int jid) {
        chooserJoukkueet.clear();
        var listattavatJoukkueet = sopimusrekisteri.getJoukkuelista("*" + editHakusanaJoukkue.getText() + "*", cbHakuvalitsinJoukkue.getSelectedIndex()); 
        int index = 0;
        for (int i = 0; i < listattavatJoukkueet.size(); i++) {
            Joukkue j = listattavatJoukkueet.get(i);
            if (j.getJid() == jid) index = i;
            chooserJoukkueet.add(j.getNimiPitka(), j);
        }
        chooserJoukkueet.setSelectedIndex(index);
        naytaJoukkue();
    } 
    
  //Näyttää valitun joukkueen tiedot kentissä
    private void naytaJoukkue() {
        jKohdalla = chooserJoukkueet.getSelectedObject();
        if (jKohdalla == null) return;
        
        jTextfieldNimi.setText(jKohdalla.getNimi());
        jTextfieldKaupunki.setText(jKohdalla.getKaupunki());
        jTextfieldOmistaja.setText(jKohdalla.getOmistaja());
        jTextfieldYhteystieto.setText(jKohdalla.getYhteystieto());
        jTextfieldSarja.setText(sopimusrekisteri.getJoukkueenLiiga(jKohdalla) == null ? "" : sopimusrekisteri.getJoukkueenLiiga(jKohdalla).getNimi()); //TODO: korvataan sillä että joukkueen liiga valitaan luodessa jolloin olematonta lidiä ei pitäisi päätyä joukkueen tietoihin.
        jTextfieldSopimuksia.setText(Integer.toString(sopimusrekisteri.getJoukkueenPelaajatLkm(jKohdalla)));
        jTextfieldPalkat.setText(Integer.toString(sopimusrekisteri.getJoukkueenPalkat(jKohdalla)));
        }
    
    //avaa dialogin jossa muokataan joukkueen tietoja
    private void joukkueMuokkaa() {        
    if (jKohdalla == null) return;
    Joukkue j;
    try {
        j = JoukkueEditDialogController.kysyJoukkue(null, jKohdalla.clone(), sopimusrekisteri.getKaikkiLiigatSorted());
        if (j == null) return;
        sopimusrekisteri.korvaaTaiLisaa(j);
        haeJoukkue(j.getJid());
    } catch (CloneNotSupportedException e) {
        //
    }
    }
    
    //poistaa joukkueen TODO: vois lisätä confirmation-hommelin
    private void joukkuePoista() {
        jKohdalla = chooserJoukkueet.getSelectedObject();
        if (jKohdalla == null) return;
        sopimusrekisteri.poista(jKohdalla);
        haeJoukkue(-1);
        //ModalController.showModal(SopimusrekisteriGUIController.class.getResource("JoukkueRemoveDialogView.fxml"), "Poista joukkue", null, "");
    }
    
    //avaa dialogin jossa annetaan uuden pelaajan tiedot
    private void pelaajaLisaa() {
        Pelaaja p = new Pelaaja();
        p = PelaajaEditDialogController.kysyPelaaja(null, p);
        if (p == null) return;
        p.rekisteroi();
        sopimusrekisteri.lisaa(p);

        haePelaaja(p.getPid());
    }
    
    //hakee pelaajat chooseriin
    private void haePelaaja(int pid) {
        chooserPelaajat.clear();
        var listattavatPelaajat = sopimusrekisteri.getPelaajalista("*" + editHakusanaPelaaja.getText() + "*", cbHakuvalitsinPelaaja.getSelectedIndex()); 
        int index = 0;
        for (int i = 0; i < listattavatPelaajat.size(); i++) {
            Pelaaja p = listattavatPelaajat.get(i);
            if (p.getPid() == pid) index = i;
            chooserPelaajat.add(p.getNimi(), p);
        }
        chooserPelaajat.setSelectedIndex(index);
        naytaPelaaja();
    }
    
    //Näyttää valitun pelaajan tiedot kentissä
    private void naytaPelaaja() {
        pKohdalla = chooserPelaajat.getSelectedObject();
        if (pKohdalla == null) return;

        pTextfieldSukunimi.setText(pKohdalla.getSukunimi());
        pTextfieldEtunimi.setText(pKohdalla.getEtunimi());
        pTextfieldSyntymaaika.setText(pKohdalla.getSyntymaaika());
        pTextfieldKansallisuus.setText(pKohdalla.getKansallisuus());
        
        Sopimus sKohdalla = sopimusrekisteri.getPelaajanSopimus(pKohdalla);
        pTextfieldJoukkue.setText(sKohdalla == null ? "Vapaa Agentti" : sopimusrekisteri.getJoukkueById(sKohdalla.getJid()).getNimiPitka());
        pTextfieldSopimusAlku.setText(sKohdalla == null ? "" : Integer.toString(sKohdalla.getAlkamisvuosi()));
        pTextfieldSopimusLoppu.setText(sKohdalla == null ? "" : Integer.toString(sKohdalla.getLoppumisvuosi()));
        pTextfieldPalkka.setText(sKohdalla == null ? "" : Integer.toString(sKohdalla.getPalkka()));
        
        //pTextfieldJoukkue.setText(sopimusrekisteri.getPelaajanJoukkue(pKohdalla).getNimiPitka());
        /*
        areaLisenssi.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaLisenssi)){
            pKohdalla.tulosta(os); 
        }
        */
        
    }
    
    //avaa dialogin jossa voi muokata pelaajan tietoja
    private void pelaajaMuokkaa() {
        if (pKohdalla == null) return;
        Pelaaja p;
        try {
            p = PelaajaEditDialogController.kysyPelaaja(null, pKohdalla.clone());
            if (p == null) return;
            sopimusrekisteri.korvaaTaiLisaa(p);
            haePelaaja(p.getPid());
        } catch (CloneNotSupportedException e) {
            //
        }
    }
    
    
    //poistaa pelaajan
    private void pelaajaPoista() {
        if (pKohdalla == null) return;
        sopimusrekisteri.poista(pKohdalla);
        haePelaaja(-1);
        //ModalController.showModal(SopimusrekisteriGUIController.class.getResource("PelaajaRemoveDialogView.fxml"), "Poista pelaaja", null, "");
    }
    
    //avaa dialogin jossa syötetään uuden liigan tiedot
    private void liigaLisaa() {
        Liiga l = new Liiga();
        l = LiigaEditDialogController.kysyLiiga(null, l);
        if (l == null) return;
        l.rekisteroi();
        sopimusrekisteri.lisaa(l);

        haeLiiga(l.getLid());
    }
    
  //Näyttää valitun liigan tiedot kentissä
    private void naytaLiiga() {
        lKohdalla = chooserLiigat.getSelectedObject();
        if (lKohdalla == null) return;

        lTextfieldNimi.setText(lKohdalla.getNimi());
        lTextfieldMinPalkka.setText(Integer.toString(lKohdalla.getMinPalkka()));
        lTextfieldMaxPalkka.setText(Integer.toString(lKohdalla.getMaxPalkka()));
        lTextfieldMaxKesto.setText(Integer.toString(lKohdalla.getMaxPituus()));
        lTextfieldLattia.setText(Integer.toString(lKohdalla.getPalkkalattia()));
        lTextfieldKatto.setText(Integer.toString(lKohdalla.getPalkkakatto()));
        lTextfieldMaxLkm.setText(Integer.toString(lKohdalla.getMaxSopimuksia()));
    }
    
    
    //avaa dialogin jossa voi muokata liigan tietoja
    private void liigaMuokkaa() {
        if (lKohdalla == null) return;
        Liiga l;
        try {
            l = LiigaEditDialogController.kysyLiiga(null, lKohdalla.clone());
            if (l == null) return;
            sopimusrekisteri.korvaaTaiLisaa(l);
            haeLiiga(l.getLid());
        } catch (CloneNotSupportedException e) {
            //
        }
    }
    
    //hakee liigat chooseriin
    private void haeLiiga(int lid) {
        chooserLiigat.clear();
        var listattavatLiigat = sopimusrekisteri.getLiigalista("*" + editHakusanaLiiga.getText() + "*"); 
        int index = 0;
        for (int i = 0; i < listattavatLiigat.size(); i++) {
            Liiga l = listattavatLiigat.get(i);
            if (l.getLid() == lid) index = i;
            chooserLiigat.add(l.getNimi(), l);
        }
        chooserLiigat.setSelectedIndex(index);
        naytaLiiga();
    }
    
    //poistaa liigan
    private void liigaPoista() {
        if (lKohdalla == null) return;
        try {
            sopimusrekisteri.poista(lKohdalla);
        } catch (SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
        haeLiiga(-1);
        //ModalController.showModal(SopimusrekisteriGUIController.class.getResource("SarjaRemoveDialogView.fxml"), "Poista sarja", null, "");
    }
    
    //poistaa sopimuksen
    private void sopimusPoista() {
        if (pKohdalla == null) return;
        sopimusrekisteri.poista(sopimusrekisteri.getPelaajanSopimus(pKohdalla));
        naytaPelaaja();
        //ModalController.showModal(SopimusrekisteriGUIController.class.getResource("SopimusRemoveDialogView.fxml"), "Poista sopimus", null, "");
    }
    
    //avaa dialogin jossa luodaan uusi sopimus
    private void sopimusUusi() {
        if (pKohdalla == null) return;
        if (sopimusrekisteri.getPelaajanSopimus(pKohdalla) != null) {
            Dialogs.showMessageDialog("Pelaajalla on jo sopimus!"); //TODO: tämä pois jos toteutetaan versio jossa voi olla useampi perättäinen sopimus tallessa
            return;
        }
        Sopimus s = new Sopimus();
        s.setPid(pKohdalla.getPid());
        s = SopimusNewDialogController.kysySopimus(null, s, sopimusrekisteri.getKaikkiJoukkueetSorted());
        if (s == null) return;
        s.rekisteroi();
        sopimusrekisteri.lisaa(s);

        naytaPelaaja();
    }
    
    //avaa dialogin jossa sopimus voidaan siirtää toiseen joukkueeseen
    private void sopimusSiirra() {
        if (pKohdalla == null) return;
        Sopimus s = sopimusrekisteri.getPelaajanSopimus(pKohdalla);
        if (s != null)
            SopimusTransferDialogViewController.muokkaa(null, s, sopimusrekisteri.getJoukkueetForSopimus(s));
        
        naytaPelaaja();
    }

    /**
     * Asetetaan kontrollerin sopimusrekisteriviite
     * @param sopimusrekisteri sopimusrekisteri johon viitataan
     */
    public void setSopimusrekisteri(Sopimusrekisteri sopimusrekisteri) {
        this.sopimusrekisteri = sopimusrekisteri;
        haePelaaja(-1);
        haeJoukkue(-1);
        haeLiiga(-1);
    }
    
}