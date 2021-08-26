/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.centrivaccinali.gui;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.scene.control.TextField;
import org.example.common.ProgUtili;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.serverCV.ServerCV;
import org.example.serverCV.ServerRegistry;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


/**
 * Classe <code>ClientCVMain</code> contente i metodi utili alla parte grafica
 */
public class ClientCVMain extends Application {

    private static Scene scene;
    private static final String APP_NAME = ".: CENTRI VACCINALI :.  [LAB-B * Bancora / Casalnovo / Donato / Dubini @ UnInsubria 2020-2021]" + "              " + ProgUtili.stampaData() + "   |   " + ProgUtili.getOsName();

    /**
     * Questo metodo lancia la prima finestra per l'esecuzione del programma
     *
     * @param stage Stage
     * @throws IOException IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("00_IpServerCheck"));
        TextField titleTextField = new TextField();
        stage.titleProperty().bind(Bindings.concat(APP_NAME).concat(titleTextField.textProperty()));;
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
     * Metodo main che fa partire l'interfaccia grafica
     */
    public static void main(String[] args) {
        launch(args);

        /*
        ProgUtili.clearScreen();
        System.out.println("********************");
        System.out.println("* CENTRI VACCINALI * ");
        System.out.println("********************\n");
        System.out.println(ProgUtili.stampaData() + ": STARTED");
        System.out.println("\n");
        //new DataModel().generateAll();
        */

    }
}
