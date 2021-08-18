/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.common;

import org.example.centrivaccinali.TipologiaCV;

import java.io.Serial;
import java.io.Serializable;

/**
 * Le sue istanze rappresentano <strong>Centri Vaccinali</strong>
 */
public class CentroVaccinale implements Serializable {
    /**
     * numero di versione seriale
     */
    @Serial
    private static final long serialVersionUID = 1;

    /**
     * id identificativo del <code>CentroVaccinale</code>
     */
    private String idCentroVacciale;  // PRIMARY KEY

    /**
     * nome del <code>CentroVaccinale</code>
     */
    private String nome;

    /**
     * Indirzzo del <code>CentroVaccinale</code>
     */
    private Indirizzo indirizzo;

    /**
     * tipologia del <code>CentroVaccinale</code>
     */
    private TipologiaCV tipologia;


    /**
     * Costruisce un nuovo oggetto che rappresenta un Centro Vaccinale in cui sono presenti le informazioni specificate dall'argomento del metodo.
     *
     * @param idCentroVacciale ID Centro Vaccinale
     * @param nome Nome
     * @param indirizzo Indirizzo
     * @param tipologia Tipologia
     */
    public CentroVaccinale(String idCentroVacciale, String nome, Indirizzo indirizzo, TipologiaCV tipologia) {
        this.idCentroVacciale = idCentroVacciale;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.tipologia = tipologia;
    }



    /**
     * Restituisce una stringa che descrive l'identificativo dell'oggetto <strong>Centro Vaccinale</strong> che esegue il metodo.
     *
     * @return idCentroVaccinale
     */
    public String getIdCentroVacciale() {
        return idCentroVacciale;
    }

    /**
     * Definisce l'identificativo dell'oggetto <strong>Centro Vaccinale</strong> che esegue il metodo.
     *
     * @param idCentroVacciale id del centro vaccinale da modificare
     */
    public void setIdCentroVacciale(String idCentroVacciale) {
        this.idCentroVacciale = idCentroVacciale;
    }

    /**
     * Restituisce una stringa che descrive il nome dell'oggetto <strong>Centro Vaccinale</strong> che esegue il metodo.
     *
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Definisce il nome dell'oggetto <strong>Centro Vaccinale</strong> che esegue il metodo.
     *
     * @param nome nome del centro vaccinale da modificare
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce una stringa che descrive l'indirizzo dell'oggetto <strong>Centro Vaccinale</strong> che esegue il metodo.
     *
     * @return indirizzo
     */
    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    /**
     * Definisce l'indirizzo dell'oggetto <strong>Centro Vaccinale</strong> che esegue il metodo.
     *
     * @param indirizzo indirizzo del centro vaccinale da modificare
     */
    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * Restituisce una stringa che descrive la tipologia dell'oggetto <strong>Centro Vaccinale</strong> che esegue il metodo.
     *
     * @return tipologia
     */
    public TipologiaCV getTipologia() {
        return tipologia;
    }

    /**
     * Definisce la tipologia dell'oggetto <strong>Centro Vaccinale</strong> che esegue il metodo.
     *
     * @param tipologia tipologia del centro vaccinale da modificare
     */
    public void setTipologia(TipologiaCV tipologia) {
        this.tipologia = tipologia;
    }


    /**
     * Restituisce una stringa che descrive il <code>CentroVaccinale</code> rappresentato dall'oggetto che esegue il metodo.
     * @return String
     */
    public String toString() {
        return    "ID del Centro Vaccinale: " + idCentroVacciale +
                "\nNome: " + nome +
                "\nIndirizzo: " + indirizzo +
                "\nTipologia: " + tipologia +
                "\n----------------------------------";
    }

    /**
     * Restituisce true se e solo se il Centro Vaccinale rappresentato dall'oggetto che esegue il metodo &egrave; uguale a quello specificato tramite l'argomento.
     *
     * @param centroVaccinale Oggetto passato come parametro al metodo
     * @return boolean
     */
    public boolean equals(CentroVaccinale centroVaccinale) { return this.toString().equals(centroVaccinale.toString()); }


}//END_Centrovaccianle_Class
