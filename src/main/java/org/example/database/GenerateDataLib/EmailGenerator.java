/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.database.GenerateDataLib;


/**
 * Classe per la generazione di email casuali
 */
public class EmailGenerator {
    String name;
    String surname;
    UIDGenerator generator = new UIDGenerator();


    /**
     * Costruttore per la creazione dell'email partendo dal nome e cognome
     *
     * @param name nome
     * @param surname cognome
     */
    public EmailGenerator(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }


    /**
     * Creazione della stringa email
     *
     * @return email basato sul nome e cognome
     */
    public String generate() {
        String email = name + "." + surname + generator.randomUUID(4,5,'-',true) + "@gmail.com";
        return email.toLowerCase();
    }
}
