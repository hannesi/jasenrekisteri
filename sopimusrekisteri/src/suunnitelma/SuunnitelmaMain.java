package suunnitelma;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author Käyttäjä
 * @version 15.1.2020
 *
 */
public class SuunnitelmaMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("SuunnitelmaGUIView.fxml"));
            final Pane root = ldr.load();
            //final SuunnitelmaGUIController suunnitelmaCtrl = (SuunnitelmaGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("suunnitelma.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Suunnitelma");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}