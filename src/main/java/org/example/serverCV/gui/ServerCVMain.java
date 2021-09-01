/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.serverCV.gui;

import javafx.application.Platform;
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
 * Classe <code>ServerCVMain</code> contente i metodi utili alla parte grafica
 */
public class ServerCVMain extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(loadFXML());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        //per chiudere correttamente il jar
        Platform.setImplicitExit(true);
        stage.setOnCloseRequest((ae) -> {
            try {
                if(ServerRegistry.registry != null){
                    ServerRegistry.registry.unbind("ServerCV");
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
            Platform.exit();
            System.exit(0);
        });
    }


    /**
     * Questo metodo imposta la finestra principale leggendola dal file fxml
     *
     * @return il loader fxml
     *
     * @throws IOException IOException
     */
    private static Parent loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ServerCVMain.class.getResource("ServerCV.fxml"));
        return fxmlLoader.load();
    }

    /**
     * Metodo main
     *
     * @param args args
     */
    public static void main(String[] args) {
        launch();
    }
}








