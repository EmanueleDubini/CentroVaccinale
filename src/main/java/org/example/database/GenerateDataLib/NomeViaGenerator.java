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
 * Generatore casuale di nomi di vie
 * La lista di indirizzi è stata creata selezionando alcuni indirizzi civici In Italia
 *
 */
public class NomeViaGenerator extends GeneratorWrapperBase<String> implements Generator {
    /**
     * Alcuni nomi degli indirizzi civici Italiani
     */
    private static final String[] names = new String[] { "Abbadia" , "Accuse", "Agocchie", "Agresti", "Albari", "Albiroli", "Allemagna", "Altabella", "Altaseta", "S. Alò",
            "Angeli", "Apostoli", "delle Asse", "Avesa", "Azzogardino", "Bagarotti", "Banzole", "Barbaria", "Basadonne", "Battibecco", "Battisasso",
            "Begato", "Belfiore di Saragozza", "Belmeloro", "Berlina", "Bocca di Lupo", "Borchetta","Castel Tialto", "Castellata", "Castellazzo", "Cattani" , "Cavaliera" ,
            "Cavaticcio","Facchini", "Falegnami", "Fantuzzi", "Felicini", "Fiaccalcollo", "Fico", "Fondazza", "Fontanina", "Foscarari", "Imperiale",
            "dell'Inferno", "Inghilterra", "della Libertà", "dei Libri", "del Limbo", "Luretta", "del Luzzo", "della Maddalena", "Maggi", "del Maglio", "Malacquisto",
            "Malcontenti", "Malgrado", "Malpertugio", "Mandria", "Marchesana", "Marescalchi", "Mascarella", "dei Mattugliani", "Miola", "Miramonte", "Mirasol Grande",
            "Mirasole di Mezzo", "Mirasole di Sopra", "delle Moline", "Monari", "Napoli", "della Neve", "Nosadella", "Olanda", "Oleari", "Orbaga", "degli Orefici",
            "dell'Orto", "Piccoli Vignacci", "Pietrafitta", "Pietralata", "dei Pignattari", "dei Pini", "del Piombo", "Poggi", "del Poggiale", "Ponte della Carità",
            "Ponte di Ferro", "Ponticello di Sant'Arcangelo", "Porta di Castello", "Porta Nova", "del Porto", "Pratello", "Registro","Remorsella", "Rialto", "del Rivale",
            "Roma", "San Pietro Martire", "Sanmartini", "Santa", "Santa Barbara", "Santa Croce", "Santa Croce", "Savonella", "Schiavonia", "Uccelli", "Urbana", "Usberti",
            "delle Vigne", "Vinazzetti", "Vinazzi", "Vinazzoli", "dei Vitali", "della Zecca ",};

    /**
     * Costruttore
     */
    public NomeViaGenerator() {
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
