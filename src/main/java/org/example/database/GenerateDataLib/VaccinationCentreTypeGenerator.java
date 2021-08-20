/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.database.GenerateDataLib;

import org.example.common.TipologiaCV;
import org.example.database.GenerateDataLib.BaseElement.Generator;
import org.example.database.GenerateDataLib.BaseElement.GeneratorWrapperBase;
import org.example.database.GenerateDataLib.BaseElement.RandomSequenceArrayBasedGenerator;

/**
 * Generatore casuale del tipo di un centro vaccinale.
 *
 */
public class VaccinationCentreTypeGenerator extends GeneratorWrapperBase<String> implements Generator {
    /**
     * Lista dei tipi di centro vaccinale.
     */
    private static final String[] names = new String[] { TipologiaCV.Ospedaliero.name(), TipologiaCV.Aziendale.name(), TipologiaCV.Hub.name()};

    /**
     * Costruttore.
     */
    public VaccinationCentreTypeGenerator() {
        super(new RandomSequenceArrayBasedGenerator<>(names));
    }

    /*
     * (non-Javadoc)
     */
    @Override
    public Object generate() {
        return generator.generate();
    }
}
