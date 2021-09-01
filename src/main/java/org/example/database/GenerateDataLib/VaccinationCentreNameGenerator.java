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
 * Generatore casuale di nomi di centri vaccinali
 *
 */
public class VaccinationCentreNameGenerator extends GeneratorWrapperBase<String> implements Generator {
    /**
     * I più comuni centri vaccinali negli Stati Uniti
     */
    private static final String[] names = new String[] { "San Raffaele",
            "Sant Anna", "Synlab", "Centro San Paolo",
            "Fatebenefratelli", "Valduce", "Humanitas",
            "Niguarda", "San Matteo", "Luigi Sacco",
            "San Gerardo", "San Donato", "Sacro Cuore",
            "San Bassiano", "Ospedale di Cittadella", "ASST Lariana",
            "Ospedale Maggiore", "Santa Maria Nuova", "Arpa Lazio",
            "ASL", "Guido Salvini", "Villa Aprica",
            "Villa Maria Pia", "Civico Chivasso", "Policlinico di Monza",
            "Policlinico Ambrosiano", "Santa Croce SRL", "Villa Grazia",
            "Beata Vergine Consolata", "Ospedale degli infermi", "Istituto Stomatologico",
            "San Carlo", "Istituiti Clinici Zucchi", "Policlinico San Marco",
            "Villa delle Terme", "Spedali Riuniti", "Ospedale Fiorentino",
            "Civile Ferrari", "Clinica Lami", "Polo Ospedaliero",
            "Villa Immacolata", "San Pietro", "San Paolo",
            "San Gennaro", "Santa Rita da Cascia", "Sant Eugenio",
            "Santo Volto", "Padre Pio", "Villa delle Querce",
            "Bambino Gesù", "San Camillo", "Bernardini",
            "Martina Franca", "Monopoli", "Villa dei Gerani",
            "Serra San Bruno", "Villa Caminiti", "Giovanni Paolo II",
            "casa di cura Torina", "Guzzardi Vittoria", "Rizza",
            "Villa Rizzo", "Arnas Garibaldi", "Paolo Merlo la Maddalena",
            "Tommasini", "Trinità", "Kinetika",
            "Santa Barbara", "San Giovanni", "San Vito",
            "Santa Sofia", "Vittorio Emanuele II", "Del Garda",
            "Santo Spirito", "della Francia corta", "Rivoli",
            "Cellini", "Promea", "Amedeo di Savoia",
            "Gradenigo", "Pinerolo", "Madonna dei boschi",
            "Santa Maria degli Incurabili", "Salvatore Maugeri", "ISAV SPA",
            "HABILITA", "Gemma Gardone", "Dominato Leonense",
            "San Benedetto", "Mangioni", "San Pellegrino"
             };

    /**
     * Costruttore
     */
    public VaccinationCentreNameGenerator() {
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
