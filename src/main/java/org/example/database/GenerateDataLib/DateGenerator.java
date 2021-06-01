package org.example.database.GenerateDataLib;

import java.util.Date;
import java.util.Random;

public class DateGenerator {

    // Get a new random instance, seeded from the clock
    Random rnd;

    public DateGenerator() {
        rnd = new Random();
    }

    public Date generate(){
        // Get an Epoch value roughly between 1940 and 2010
        // -946771200000L = January 1, 1940
        // Add up to 70 years to it (using modulus on the next long)
        Long ms = -946771200000L + (Math.abs(rnd.nextLong()) % (65L * 365 * 24 * 60 * 60 * 1000));

        // Construct a date

        return new Date(ms);
    }
}
