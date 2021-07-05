/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.centrivaccinali;

import org.example.common.CentroVaccinale;

/**
 * Classe enumerativa per la gestione della <strong>Tipologia</strong> di {@link CentroVaccinale}.
 */
public enum TipologiaCV {
    /**
     * possibile tipo di un {@link CentroVaccinale}
     */
    Ospedaliero,

    /**
     * possibile tipo di un {@link CentroVaccinale}
     */
    Aziendale,

    /**
     * possibile tipo di un {@link CentroVaccinale}
     */
    Hub
}