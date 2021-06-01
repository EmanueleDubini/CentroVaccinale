package org.example.database.GenerateDataLib;

public class UserIDGenerator {
    private String name;
    private String surname;
    private UIDGenerator generator = new UIDGenerator();

    public UserIDGenerator(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String generate() {
        String userID = name + "-" + surname + "." + generator.randomUUID(4,5,'-', true);
        return userID;
    }
}
