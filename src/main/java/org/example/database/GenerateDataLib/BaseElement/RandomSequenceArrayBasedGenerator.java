/*
  LABORATORIO INTERDISCIPLINARE A - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.database.GenerateDataLib.BaseElement;

import java.util.Random;

/**
 * Genera oggetti di tipo <code>Object</code> utilizzando i valori salvati nell'array,
 * che fa da contenitore, selezionandoli casualmente.
 *
 * @param <Object>> Tipo di dato dell'elemento generato.
 */
public class RandomSequenceArrayBasedGenerator<Object> extends ArrayBasedGeneratorBase<Object> implements Generator<Object> {
    /**
     * Un istanza della classe {@link Random} che genera l'indirizzo degli oggetti
     * contenuti all'interno dell'array.
     */
    private Random random;

    /**
     * Costruttore.
     *
     * @param values Un array di valori dai quali si estrarranno i tipi di dato.
     */
    public RandomSequenceArrayBasedGenerator(Object... values) {
        super(values);
        random = new Random();
    }

    /* (non-Javadoc)
     *
     */
    @Override
    public java.lang.Object generate() {
        return values[random.nextInt(values.length)];
    }
}
