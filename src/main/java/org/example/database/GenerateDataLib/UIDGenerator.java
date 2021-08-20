/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.database.GenerateDataLib;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Classe per la creazione dell' id centro vaccinale e id vaccinazione in modo casuale
 */
public class UIDGenerator {

    String Alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    Random rng = new SecureRandom();


    /**
     * Metodo per la scelta casuale dei caratteri e/o numeri
     *
     * @param numeric carattere booleano per indicare se si e' interessati a id vaccinazione o id centro vaccinale
     *
     * @return un carattere casuale
     */
    char randomChar(boolean numeric){
        if(numeric){
            String numeric1 = "0123456789";
            return numeric1.charAt(rng.nextInt(numeric1.length()));
        }else{
            return Alphabet.charAt(rng.nextInt(Alphabet.length()));
        }

    }

    /**
     * crea una stringa di id casuali
     *
     * @param lunghezza lunghezza della stringa
     * @param spaziatura numero di spaziature
     * @param carSpaziatore il carattere spaziatore
     * @param numeric carattere booleano per indicare se si e' interessati a id vaccinazione o id centro vaccinale
     *
     * @return stringa con id vaccinazione o id centri vaccinali
     */
    public String randomUUID(int lunghezza, int spaziatura, char carSpaziatore, boolean numeric){
        StringBuilder sb = new StringBuilder();
        int spacer = 0;
        while(lunghezza > 0){
            if(spacer == spaziatura){
                sb.append(carSpaziatore);
                spacer = 0;
            }
            lunghezza--;
            spacer++;
            sb.append(randomChar(numeric));
        }
        return sb.toString();
    }
}
