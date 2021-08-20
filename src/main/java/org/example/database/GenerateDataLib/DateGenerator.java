/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.database.GenerateDataLib;

import java.util.Date;
import java.util.Random;


/**
 * Classe dedicata alla creazione di una data casuale
 */
public class DateGenerator {

    // Get a new random instance, seeded from the clock
    Random rnd;


    /**
     * Creazione di un oggetto Random
     */
    public DateGenerator() {
        rnd = new Random();
    }


    /**
     * Creazione di una data casuale
     *
     * @return una data casuale
     */
    public Date generate(){
        // Get an Epoch value roughly between 1940 and 2010
        // -946771200000L = January 1, 1940
        // Add up to 70 years to it (using modulus on the next long)
        long ms = -946771200000L + (Math.abs(rnd.nextLong()) % (65L * 365 * 24 * 60 * 60 * 1000));

        // Construct a date

        return new Date(ms);
    }
}
