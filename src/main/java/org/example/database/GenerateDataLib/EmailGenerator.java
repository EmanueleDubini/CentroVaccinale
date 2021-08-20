/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.database.GenerateDataLib;

//todo javadoc
public class EmailGenerator {
    String name;
    String surname;
    UIDGenerator generator = new UIDGenerator();

    //todo javadoc
    public EmailGenerator(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    //todo javadoc
    public String generate() {
        String email = name + "." + surname + generator.randomUUID(4,5,'-',true) + "@gmail.com";
        return email.toLowerCase();
    }
}
