/*
  LABORATORIO INTERDISCIPLINARE A - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.centrivaccinali.gui;

import org.example.common.ProgUtili;
import org.example.common.exceptions.ServerNotFoundException;
import org.example.serverCV.ServerCV;
import org.example.serverCV.gui.ServerCVMain;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Classe <code>ClientCV</code> che costituisce l'entry point dell'applicazione.
 *
 * fornisce tutti i servizi e funzionalità designate per gli ultilizzatori dell’applicazione.
 */
public class ClientCV {

    /**
     * Metodo <code>Main</code> dell'applicazione ClientCV
     */
    public static void main(String[] args) {

        ClientCVMain.main(args); // fa partire l'intrerfaccia grafica

        //legge il file policy nella cartella client con i permessi di chi puo usare l'app
        //System.setSecurityManager(new SecurityManager());
    }
}