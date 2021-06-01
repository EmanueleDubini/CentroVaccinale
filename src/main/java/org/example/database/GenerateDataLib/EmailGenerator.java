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
