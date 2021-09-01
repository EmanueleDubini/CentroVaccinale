/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.database.GenerateDataLib;

import org.example.common.Indirizzo;
import org.example.common.Qualificatore;
import org.example.database.GenerateDataLib.BaseElement.Generator;
import org.example.database.GenerateDataLib.BaseElement.GeneratorWrapperBase;
import org.example.database.GenerateDataLib.BaseElement.RandomSequenceArrayBasedGenerator;

/**
 * Generatore casuale di indirizzi civici
 * La lista di indirizzi è stata creata selezionando alcuni indirizzi civici In Italia
 *
 */
public class IndirizzoGenerator extends GeneratorWrapperBase<String> implements Generator {

    static QualificatoreViaGenerator qualificatore = new QualificatoreViaGenerator(); //qualificatore via
    static NomeViaGenerator nome = new NomeViaGenerator(); //nome via
    static NumeroCivicoGenerator numero = new NumeroCivicoGenerator(); //numero civico
    /**
     * Alcuni indirizzi civici Italiani
     */
    private static final Indirizzo[] names = {new Indirizzo(Qualificatore.valueOf((String)qualificatore.generate()), (String)nome.generate(), (String)numero.generate(), "Cermenate", 22072, "CO"),
            new Indirizzo(Qualificatore.valueOf((String)qualificatore.generate()), (String)nome.generate(), (String)numero.generate(), "Vertemate con minoprio", 22070, "CO"),
            new Indirizzo(Qualificatore.valueOf((String)qualificatore.generate()), (String)nome.generate(), (String)numero.generate(), "Assago", 20057, "MI"),
            new Indirizzo(Qualificatore.valueOf((String)qualificatore.generate()), (String)nome.generate(), (String)numero.generate(), "Besate", 20080, "MI"),
            new Indirizzo(Qualificatore.valueOf((String)qualificatore.generate()), (String)nome.generate(), (String)numero.generate(), "Arese", 20044, "MI"),
            new Indirizzo(Qualificatore.valueOf((String)qualificatore.generate()), (String)nome.generate(), (String)numero.generate(), "Paderno Dugnano", 20037, "MI")};


    /**
     * Costruttore
     */
    public IndirizzoGenerator() {
        super(new RandomSequenceArrayBasedGenerator(names));
    }

    /*
     * (non-Javadoc)
     */
    @Override
    public Object generate() {
        return generator.generate();
    }
}
