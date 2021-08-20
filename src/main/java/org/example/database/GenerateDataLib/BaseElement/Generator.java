/*
  LABORATORIO INTERDISCIPLINARE A - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.database.GenerateDataLib.BaseElement;

/**
 * Il cuore del package GenerateDataLib.
 * Un implementazione di questa interfaccia sarà utilizzato per generare tipi di dato
 * di tipo <code>Object</code>
 *
 */
public interface Generator {
    /**
     * Metodo che genera un oggetto di tipo Object
     * In base alla sua implementazione potrà generare dati casuali, dati estratti da un elenco prodefinito di valori,
     * collezioni di valori, etc.
     *
     * @return Il tipo di dato generato
     */
    java.lang.Object generate();
}
