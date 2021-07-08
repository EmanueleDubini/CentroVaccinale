/*
  LABORATORIO INTERDISCIPLINARE A - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */
package org.example.database.GenerateDataLib.BaseElement;

/**
 * Una classe astratta che andrà estesa da altre classi che vorranno generare elementi
 * da un array di valori di tipo <code>Object</code>.
 *
 * @param <Object>> Tipo di dato, dell'array di valori, che verranno generati dalle classi
 *                  che estenderanno questa classe
 */
public abstract class ArrayBasedGeneratorBase<Object> {
    /**
     * Il valore che verrà utilizzato per generare i tipi di dato.
     */
    protected Object[] values;

    /**
     * Costruttore.
     *
     * @param values Un array di valori dai quali si estrarranno i tipi di dato.
     */
    @SafeVarargs
    public ArrayBasedGeneratorBase(Object... values) {
        this.values = values;
    }

}
