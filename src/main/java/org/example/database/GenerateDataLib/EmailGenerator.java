/*
  LABORATORIO INTERDISCIPLINARE A - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.database.GenerateDataLib;

public class EmailGenerator {
    private String name;
    private String surname;
    private UIDGenerator generator = new UIDGenerator();

    public EmailGenerator(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String generate() {
        String email = name + "." + surname + generator.randomUUID(4,5,'-',true) + "@gmail.com";
        return email.toLowerCase();
    }
}
