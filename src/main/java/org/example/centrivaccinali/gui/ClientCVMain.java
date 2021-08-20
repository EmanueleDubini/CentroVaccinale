/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.centrivaccinali.gui;

import javafx.application.Platform;
import org.example.common.ProgUtili;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Classe <code>ClientCVMain</code> contente i metodi utili alla parte grafica
 */
public class ClientCVMain extends Application {


    ///////////////////////////VERSIONE PRESENTE NEL PROGETTO ORIGINALE PRIMA DI IMPORTARE//////////////////////

    /*private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stg = primaryStage; //salvo nell avariabile stg per usare l'oggeto primarystage anche fuori da questo metodo
        Parent root = getParentFromFxml("src/main/java/org/example/centrivaccinali/gui/fxml/00_IpServerCheck.fxml");  //aggiornare con questa riga di codice e aggiungere il metodo sottostante
        primaryStage.setTitle("Centro Vaccinale - ClientCV" + "  /  " + ProgUtili.stampaData() + "  /  " + ProgUtili.getOsName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root)); //da root togliere le dimensioni della finestra presenti come defaul messe da intellij per usare quelle settate tramite scene builder
        primaryStage.show();
    }

    private Parent getParentFromFxml(String resourcePath) throws IOException {
        URL fxmlUrl = new File(resourcePath).toURI().toURL();
        System.out.println(fxmlUrl.toString());
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
        return fxmlLoader.load();
    }

    public void changeScene(String fxml, String title) throws IOException {
        Parent pane = getParentFromFxml(fxml);
        stg.setTitle(title);
        stg.getScene().setRoot(pane);
    }*/
    //////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////VERSIONE INSERITA PER USARE MAVEN NEL PROGETTO//////////////////////

    private static Scene scene;

    /**
     * Questo metodo lancia la prima finestra per l'esecuzione del programma
     *
     * @param stage Stage
     *
     * @throws IOException IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("00_IpServerCheck"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        //per chiudere correttamente il jar
        Platform.setImplicitExit(true);
        stage.setOnCloseRequest((ae) -> {
            Platform.exit();
            System.exit(0);
        });
    }

    /**
     * Questo metodo imposta la finestra principale leggendola dal file fxml
     *
     * @param fxml fxml
     * @throws IOException IOException
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }


    /**
     * Questo metodo permette di caricare fxmlLoader
     *
     * @param fxml fxml
     *
     * @return il loader fxml
     *
     * @throws IOException IOException
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientCVMain.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Metodo main
     */
    public static void main(String[] args) {
        ProgUtili.clearScreen();
        System.out.println("********************");
        System.out.println("* CENTRI VACCINALI * ");
        System.out.println("********************\n");
        System.out.println("Il miglior software di sempre per i Cittadini !!");
        System.out.println(ProgUtili.stampaData());
        System.out.println("\n");


        //new DataModel().generateAll();

        launch(args);
    }
}
