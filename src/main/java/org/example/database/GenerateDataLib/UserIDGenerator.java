/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.database.GenerateDataLib;

public class UserIDGenerator {
    private final String name;
    private final String surname;
    private final UIDGenerator generator = new UIDGenerator();

    public UserIDGenerator(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String generate() {
        return name + "-" + surname + "." + generator.randomUUID(4,5,'-', true);
    }
}
