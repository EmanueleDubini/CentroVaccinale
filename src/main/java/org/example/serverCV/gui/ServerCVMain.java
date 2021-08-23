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

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

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
                ServerCV.registry.unbind("ServerCV");
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
            Platform.exit();
            System.exit(0);
        });
    }


    private static Parent loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ServerCVMain.class.getResource("ServerCV.fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(); //todo quando si chiude la finestra del jar tramite la 'X' rimane in esecuzione il processo e se si riclicca sul jar non lo apre
    }

}








