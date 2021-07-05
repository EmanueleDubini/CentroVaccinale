/*
  LABORATORIO INTERDISCIPLINARE A - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.database.GenerateDataLib;

import java.security.SecureRandom;
import java.util.Random;

public class UIDGenerator {

    final private String Numeric = "0123456789";
    final private String Alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final private Random rng = new SecureRandom();

    char randomChar(boolean numeric){
        if(numeric){
            return Numeric.charAt(rng.nextInt(Numeric.length()));
        }else{
            return Alphabet.charAt(rng.nextInt(Alphabet.length()));
        }

    }

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
