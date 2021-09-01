package org.example.centrivaccinali.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.serverCV.ServerCVI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.regex.Pattern;

/**
 * Questa classe controlla se l'ip inserito nel client e' corretto
 */
public class IpServerCheck_Controller {
    //grafica relativa alla pagina: 00_IpServerCheck.fxml
    /**
     * Label che contiene lo stato delle connessioni
     */
    @FXML
    public Label connectionStatus;
    /**
     * TextField per inserire l'ip del server
     */
    @FXML
    public TextField hostAddress;
    String address;

    //istanziazione registry
    Registry registry;
    /**
     * Oggetto remoto per il passaggio dei comandi
     */
    public static ServerCVI stub;

    /////////////////////grafica relativa alla pagina: 00_IpServerCheck.fxml //////////////////////////////////

    /**
     * Questo metodo gestisce la connessione del client al server
     */
    public void serverConnection() {
        address = hostAddress.getText().strip();

        if (address.equals("")) {
            connectionStatus.setText("indirizzo IP server non inserito");
        }
        else if (!(Pattern.matches("^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$", address))){ //verifica correttezza sintassi indirizzo ip
            connectionStatus.setText("indirizzo IP server errato");
        }
        else {
            try {
                int PORT = 1200;
                registry = LocateRegistry.getRegistry(address, PORT);

                //richiesta dell'oggeto server remoto inserito precedenemente dal serverCV nel registry
                stub = (ServerCVI) registry.lookup("ServerCV");
                ClientCVMain.setRoot("01_LandingPage");
            } catch (Exception e) {
                connectionStatus.setText("indirizzo IP server errato o server non disponibile");
            }
        }
    }//END_serverConnection
}
