/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.common;

import org.example.serverCV.ServerCV;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.*;

/**
 * Classe che contiene metodi utili di servizio comuni a antrambe le applicazioni
 */
public class ProgUtili {

    /**
     * Restituisce il Sistema Operativo.
     * @return Sistema Operativo.
     */
    public static String getOsName(){
        return System.getProperty("os.name");
    }

    /**
     * Effettua la pulizia dello schermo a console.
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Crea un codice numerico univoco casuale da associare a ogni ristorante.
     * @return String composta dall'id a 16 cifre.
     */
    public static String createId() {
        DecimalFormat df = new DecimalFormat("0000000000000000");
        return df.format(((Math.random() * 10000) + 10000) % 10000);
    }

    /**
     * Stampa la data odierna.
     * @return La data in formato gg-mm-aaaa.
     */
    public static String stampaData() {
        Date oggi = new Date(); // Data di oggi
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        return sdf.format(oggi);
    }

    /**
     * Controlla la validità del Codice Fiscale
     *
     * @param cf codice fiscale
     *
     * @return true se il codice fiscale è valido, altrimenti false
     */
    public static boolean checkCodiceFiscale(String cf){
        String regex = "^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$";
        if (Pattern.matches(regex, cf))
            return true;
        else
            return false;
    }


}//END_ProgUtili_Class
