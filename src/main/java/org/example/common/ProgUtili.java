/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Classe che contiene metodi utili di servizio comuni a antrambe le applicazioni
 */
public class ProgUtili {

    /**
     * Restituisce il noome del Sistema Operativo su cui gira il Client
     *
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
     * Stampa la data odierna.
     *
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
        return Pattern.matches(regex, cf);
    }


    /**
     * Questo metodo rende maiuscola la prima lettera della stringa passata come parametro
     *
     * @param stringa stringa da modificare
     *
     * @return la stringa con la prima lettera maiuscola
     */
    public static String capitalize(String stringa) {
        return  stringa.substring(0, 1).toUpperCase() + stringa.substring(1);
    }
}//END_ProgUtili_Class
