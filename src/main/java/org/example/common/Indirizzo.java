/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.common;

import java.io.Serial;
import java.io.Serializable;

/**
 * Le sue istanze rappresentano un <strong>Indirizzo</strong>
 */
public class Indirizzo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1;
    private Qualificatore qualificatore;
    private String nome;
    private String numeroCivico;
    private String comune;
    private int cap;
    private String prov;

    /**
     * Costruisce un nuovo oggetto che rappresenta un Indirizzo in cui sono presenti le informazioni specificate dall'argomento del metodo.
     *
     * @param qualificatore Qualificatore
     * @param nome Nome
     * @param numeroCivico NumeroCivico
     * @param comune Comune
     * @param prov Provincia
     * @param cap Cap
     */
    public Indirizzo(Qualificatore qualificatore, String nome, String numeroCivico, String comune, int cap, String prov) {
        this.qualificatore = qualificatore;
        this.nome = nome;
        this.numeroCivico = numeroCivico;
        this.comune = comune;
        this.cap = cap;
        this.prov = prov;
    }

    /**
     * Restituisce una stringa che descrive il qualificatore dell'oggetto <strong>Indirizzo</strong> che esegue il metodo.
     *
     * @return Qualificatore
     */
    public Qualificatore getQualificatore() {
        return qualificatore;
    }

    /**
     * Definisce il qualificatore dell'oggetto <strong>Indirizzo</strong> che esegue il metodo.
     *
     * @param qualificatore nome dell'indirizzo da modificare
     */
    public void setQualificatore(Qualificatore qualificatore) {
        this.qualificatore = qualificatore;
    }

    /**
     * Restituisce una stringa che descrive il nome dell'oggetto <strong>Indirizzo</strong> che esegue il metodo.
     *
     * @return Nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Definisce il nome dell'oggetto <strong>Indirizzo</strong> che esegue il metodo.
     *
     * @param nome nome dell'indirizzo da modificare
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce una stringa che descrive il numero civico dell'oggetto <strong>Indirizzo</strong> che esegue il metodo.
     *
     * @return Numero Civico
     */
    public String getNumeroCivico() {
        return numeroCivico;
    }

    /**
     * Definisce il numero civico dell'oggetto <strong>Indirizzo</strong> che esegue il metodo.
     *
     * @param numeroCivico numero civico dell'indirizzo da modificare
     */
    public void setNumeroCivico(String numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    /**
     * Restituisce una stringa che descrive il comune dell'oggetto <strong>Indirizzo</strong> che esegue il metodo.
     *
     * @return Comune
     */
    public String getComune() {
        return comune;
    }

    /**
     * Definisce il comune dell'oggetto <strong>Indirizzo</strong> che esegue il metodo.
     *
     * @param comune comune dell'indirizzo da modificare
     */
    public void setComune(String comune) {
        this.comune = comune;
    }

    /**
     * Restituisce una stringa che descrive il cap dell'oggetto <strong>Indirizzo</strong> che esegue il metodo.
     *
     * @return Cap
     */
    public int getCap() {
        return cap;
    }

    /**
     * Definisce il cap dell'oggetto <strong>Indirizzo</strong> che esegue il metodo.
     *
     * @param cap cap dell'indirizzo da modificare
     */
    public void setCap(int cap) {
        this.cap = cap;
    }

    /**
     * Restituisce una stringa che descrive il provincia dell'oggetto <strong>Indirizzo</strong> che esegue il metodo.
     *
     * @return Provincia
     */
    public String getProv() {
        return prov;
    }

    /**
     * Definisce la provincia dell'oggetto <strong>Indirizzo</strong> che esegue il metodo.
     *
     * @param prov provincia dell'indirizzo da modificare
     */
    public void setProv(String prov) {
        this.prov = prov;
    }

    /**
     * Restituisce una stringa che descrive  <code>Indirizzo</code> rappresentato dall'oggetto che esegue il metodo.
     *
     * @return String
     */
    @Override
    public String toString() {
        return qualificatore + " " +
                nome + " " +
                numeroCivico + " " +
                comune + " " +
                cap + " " +
                prov;
    }

}
