/*
  LABORATORIO INTERDISCIPLINARE A - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.database.GenerateDataLib;

import org.example.common.Qualificatore;
import org.example.database.GenerateDataLib.BaseElement.Generator;
import org.example.database.GenerateDataLib.BaseElement.GeneratorWrapperBase;
import org.example.database.GenerateDataLib.BaseElement.RandomSequenceArrayBasedGenerator;

/**
 * Generatore casuale di qualificatori per indirizzi civici.
 * La lista dei qualificatori è stata creata selezionando alcuni indirizzi civici In Italia..
 *
 */
public class QualificatoreViaGenerator extends GeneratorWrapperBase<String> implements Generator<String> {
    /**
     * Alcuni dei qualificatori di indirizzi civici Italiani.
     */
    private static final String[] names = new String[] { Qualificatore.Via.name(), Qualificatore.Viale.name(), Qualificatore.Piazza.name(),
            Qualificatore.Largo.name(), Qualificatore.Vicolo.name(), Qualificatore.Piazzale.name(), Qualificatore.Corso.name()};

    /**
     * Costruttore.
     */
    public QualificatoreViaGenerator() {
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
