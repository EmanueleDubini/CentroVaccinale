/*
  LABORATORIO INTERDISCIPLINARE A - Como AA20-21

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
 * Generatore casuale di nomi di centri vaccinali.
 *
 */
public class VaccinationCentreNameGenerator extends GeneratorWrapperBase<String> implements Generator<String> {
    /**
     * I più comuni centri vaccinali negli Stati Uniti.
     */
    private static final String[] names = new String[] { "Memory Centre", //todo aggiungere altri centri, sarebbe più giusto metterli tutti in italiano e corti
            "Jeewan Nursing",
             "Medlife Hospital", "NKS Hospital",
            "Sanjeevan Hospital", "Sant Hospital", "Kolmet Hospital",
            "Apex Hospital", "Bedok Centre",
             };

    /**
     * Costruttore.
     */
    public VaccinationCentreNameGenerator() {
        super(new RandomSequenceArrayBasedGenerator<String>(names));
    }

    /*
     * (non-Javadoc)
     */
    @Override
    public Object generate() {
        return generator.generate();
    }
}
