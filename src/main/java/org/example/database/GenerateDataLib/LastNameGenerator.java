/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.database.GenerateDataLib;

import org.example.database.GenerateDataLib.BaseElement.Generator;
import org.example.database.GenerateDataLib.BaseElement.GeneratorWrapperBase;
import org.example.database.GenerateDataLib.BaseElement.RandomSequenceArrayBasedGenerator;

/**
 * Generatore casuale di Cognomi.
 * La lista dei nomi è stata presa dai 200 nomi più comuni negli Stati Uniti
 *
 */
public class LastNameGenerator extends GeneratorWrapperBase<String> implements Generator<String> {
    /**
     * I più comuni cognomi degli Stati Uniti.
     */
    private static final String[] lastNames = new String[] { "Smith",
            "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller",
            "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson",
            "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez",
            "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall",
            "Allen", "Young", "Hernandez", "King", "Wright", "Lopez", "Hill",
            "Scott", "Green", "Adams", "Baker", "Gonzalez", "Nelson", "Carter",
            "Mitchell", "Perez", "Roberts", "Turner", "Phillips", "Campbell",
            "Parker", "Evans", "Edwards", "Collins", "Stewart", "Sanchez",
            "Morris", "Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy",
            "Bailey", "Rivera", "Cooper", "Richardson", "Cox", "Howard",
            "Ward", "Torres", "Peterson", "Gray", "Ramirez", "James", "Watson",
            "Brooks", "Kelly", "Sanders", "Price", "Bennett", "Wood", "Barnes",
            "Ross", "Henderson", "Coleman", "Jenkins", "Perry", "Powell",
            "Long", "Patterson", "Hughes", "Flores", "Washington", "Butler",
            "Simmons", "Foster", "Gonzales", "Bryant", "Alexander", "Russell",
            "Griffin", "Diaz", "Hayes", "Myers", "Ford", "Hamilton", "Graham",
            "Sullivan", "Wallace", "Woods", "Cole", "West", "Jordan", "Owens",
            "Reynolds", "Fisher", "Ellis", "Harrison", "Gibson", "McDonald",
            "Cruz", "Marshall", "Ortiz", "Gomez", "Murray", "Freeman", "Wells",
            "Webb", "Simpson", "Stevens", "Tucker", "Porter", "Hunter",
            "Hicks", "Crawford", "Henry", "Boyd", "Mason", "Morales",
            "Kennedy", "Warren", "Dixon", "Ramos", "Reyes", "Burns", "Gordon",
            "Shaw", "Holmes", "Rice", "Robertson", "Hunt", "Black", "Daniels",
            "Palmer", "Mills", "Nichols", "Grant", "Knight", "Ferguson",
            "Rose", "Stone", "Hawkins", "Dunn", "Perkins", "Hudson", "Spencer",
            "Gardner", "Stephens", "Payne", "Pierce", "Berry", "Matthews",
            "Arnold", "Wagner", "Willis", "Ray", "Watkins", "Olson", "Carroll",
            "Duncan", "Snyder", "Hart", "Cunningham", "Bradley", "Lane",
            "Andrews", "Ruiz", "Harper", "Fox", "Riley", "Armstrong",
            "Carpenter", "Weaver", "Greene", "Lawrence", "Elliott", "Chavez",
            "Sims", "Austin", "Peters", "Kelley", "Franklin", "Lawson",
            "Fields", "Gutierrez", "Ryan", "Schmidt", "Carr", "Vasquez",
            "Castillo", "Wheeler", "Chapman", "Oliver", "Montgomery",
            "Richards", "Williamson", "Johnston", "Banks", "Meyer", "Bishop",
            "McCoy", "Howell", "Alvarez", "Morrison", "Hansen", "Fernandez",
            "Garza", "Harvey", "Little", "Burton", "Stanley", "Nguyen",
            "George", "Jacobs", "Reid", "Kim", "Fuller", "Lynch", "Dean",
            "Gilbert", "Garrett", "Romero", "Welch", "Larson", "Frazier",
            "Burke", "Hanson", "Day", "Mendoza", "Moreno", "Bowman", "Medina",
            "Fowler" };

    /**
     * Costruttori.
     */
    public LastNameGenerator() {
        super(new RandomSequenceArrayBasedGenerator<String>(lastNames));
    }

    /*
     * (non-Javadoc)
     *
     * @see com.googlecode.jeneratedata.core.Generator#generate()
     */
    @Override
    public Object generate() {
        return generator.generate();
    }
}
