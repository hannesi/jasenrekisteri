package fxSopimusrekisteri;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author Hannes Koivusipilä
 * @version 9.2.2020
 *
 */
public class SopimusrekisteriMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("SopimusrekisteriGUIView.fxml"));
            final Pane root = ldr.load();
            //final SuunnitelmaGUIController suunnitelmaCtrl = (SuunnitelmaGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("sopimusrekisteri.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Sopimusrekisteri");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei k�yt�ss�
     */
    public static void main(String[] args) {
        launch(args);
    }
}