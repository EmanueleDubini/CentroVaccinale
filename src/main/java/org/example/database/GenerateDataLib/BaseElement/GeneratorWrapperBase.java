/*
  LABORATORIO INTERDISCIPLINARE A - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.database.GenerateDataLib.BaseElement;

/**
 * Una classe astratta che andrà estesa da classi che desidereranno processare i valori generati
 * dall'istanza della classe wrapper {@link Generator}
 *
 * @param <Object>> Tipo di dato dell'elemento generato dalla classe wrapper {@link Generator}.
 */
public abstract class GeneratorWrapperBase<Object> {
    /**
     * Istanza della classe wrapper {@link Generator} utilizzato come sottoclasse di questa classe
     */
    protected Generator generator;

    /**
     * Costruttore
     *
     * @param generator Istanza della classe wrapper {@link Generator}
     */
    public GeneratorWrapperBase(RandomSequenceArrayBasedGenerator<Object> generator) {
        super();
        this.generator = generator;
    }
}
