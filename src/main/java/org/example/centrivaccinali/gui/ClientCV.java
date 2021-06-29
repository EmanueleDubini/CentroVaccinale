/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.centrivaccinali.gui;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Classe <code>ClientCV</code> che costituisce l'entry point dell'applicazione.
 *
 * fornisce tutti i servizi e funzionalità designate per gli ultilizzatori dell’applicazione.
 */
public class ClientCV {

    /**
     * Metodo <code>Main</code> dell'applicazione ClientCV
     */
    public static void main(String[] args) throws IOException {

        ClientCVMain.main(args); // fa partire l'intrerfaccia grafica

        //legge il file policy nella cartella client con i permessi di chi puo usare l'app
        //System.setSecurityManager(new SecurityManager());
    }
}