/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.database.GenerateDataLib;


/**
 * Classe che genera uno username casuale per l'utente che si vuole registrare
 */
public class UserIDGenerator {
    private final String name;
    private final String surname;
    private final UIDGenerator generator = new UIDGenerator();


    /**
     * Costruttore che genera uno username casuale
     *
     * @param name nome del cittadino che vuole registrarsi
     * @param surname cognome del cittadino che vuole registrarsi
     */
    public UserIDGenerator(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }


    /**
     * Genera una stringa contenente lo username dei cittadini registrati
     *
     * @return una stringa con username casuale
     */
    public String generate() {
        return name + "-" + surname + "." + generator.randomUUID(4,5,'-', true);
    }
}
