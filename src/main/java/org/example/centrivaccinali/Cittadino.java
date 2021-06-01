/*
  LABORATORIO INTERDISCIPLINARE A - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.centrivaccinali;


/**
 * Le sue istanze rappresentano <strong>Cittadini</strong>
 */
public class Cittadino {
    //todo javadoc dei campi, lo facciamo alla fine per motivi di leggibilità
    private String codiceFiscale;  // PRIMARY KEY
    private String cognome;
    private String nome;
    private int eta;
    private String email;
    private String userId;
    private String password;
    private String idVaccinazione;

    /**
     * Costruisce un nuovo oggetto che rappresenta un Cittadino in cui sono presenti le informazioni specificate dall'argomento del metodo.
     *  @param codiceFiscale Codice Fiscale
     * @param cognome Cognome
     * @param nome Nome
     * @param eta Et&agrave;
     * @param email Email
     * @param userId UserId
     * @param password Password
     * @param idVaccinazione idVaccinazione
     *
     */
    public Cittadino(String codiceFiscale, String cognome, String nome, int eta, String email, String userId, String password, String idVaccinazione) {
        this.codiceFiscale = codiceFiscale;
        this.cognome = cognome;
        this.nome = nome;
        this.eta = eta;
        this.email = email;
        this.userId = userId;
        this.password = password;
        this.idVaccinazione = idVaccinazione;
    }

    /**
     * Restituisce una stringa che descrive il codice fiscale dell'oggetto <strong>Cittadino</strong> che esegue il metodo.
     *
     * @return codiceFiscale
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Definisce l'identificativo dell'oggetto <strong>Cittadino</strong> che esegue il metodo.
     *
     * @param codiceFiscale codice fiscale del Cittadino da modificare
     */
    public void setMatricola(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    /**
     * Restituisce una stringa che descrive il Cognome dell'oggetto <strong>Cittadino</strong> che esegue il metodo.
     *
     * @return cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Definisce il cognome dell'oggetto <strong>Cittadino</strong> che esegue il metodo.
     *
     * @param cognome cognome del Cittadino da modificare
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Restituisce una stringa che descrive il nome dell'oggetto <strong>Cittadino</strong> che esegue il metodo.
     *
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Definisce il nome dell'oggetto <strong>Cittadino</strong> che esegue il metodo.
     *
     * @param nome nome del Cittadino da modificare
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce una stringa che descrive l'et&agrave; dell'oggetto <strong>Cittadino</strong> che esegue il metodo.
     *
     * @return et&agrave;
     */
    public int getEta() {
        return eta;
    }

    /**
     * Definisce l'et&agrave; dell'oggetto <strong>Cittadino</strong> che esegue il metodo.
     *
     * @param eta et&agrave; del Cittadino da modificare
     */
    public void setEta(int eta) {
        this.eta = eta;
    }

    /**
     * Restituisce una stringa che descrive l'email; dell'oggetto <strong>Cittadino</strong> che esegue il metodo.
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Definisce l'et&agrave; dell'oggetto <strong>Cittadino</strong> che esegue il metodo.
     *
     * @param email
     */
    public void setEmail(int email) {
        this.eta = email;
    }


    /**
     * Restituisce una stringa che descrive lo userId; dell'oggetto <strong>Cittadino</strong> che esegue il metodo.
     *
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Definisce lo userID; dell'oggetto <strong>Cittadino</strong> che esegue il metodo.
     *
     * @param userId
     */
    public void setEmail(String userId) {
        this.userId = userId;
    }

    /**
     * Restituisce una stringa che descrive la password; dell'oggetto <strong>Cittadino</strong> che esegue il metodo.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Definisce la password; dell'oggetto <strong>Cittadino</strong> che esegue il metodo.
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Restituisce un numero di tipo <code>Long</code>  che descrive idVaccinazione; dell'oggetto <strong>Cittadino</strong> che esegue il metodo.
     *
     * @return idVaccianzione
     */
    public String getIdVaccinazione() {
        return idVaccinazione;
    }

    /**
     * Definisce lo idVaccinazione; dell'oggetto <strong>Cittadino</strong> che esegue il metodo.
     *
     * @param idVaccinazione
     */
    public void setIdVaccinazione(String idVaccinazione) {
        this.idVaccinazione = idVaccinazione;
    }


    /**
     * Restituisce una stringa che descrive il <code>Cittadino</code> rappresentato dall'oggetto che esegue il metodo.
     * @return String
     */
    public String toString() {
        return  "Codice fiscale: " + codiceFiscale +
                "\nCognome: " + cognome +
                "\nNome: " + nome +
                "\nEtà: " + eta +
                "\nEmail: " + email +
                "\nUserId: " + userId +
                "\nPassword: " + password +
                "\nIdVaccinazione: " + idVaccinazione +
                "\n----------------------------------";
    }

    /**
     * Restituisce true se e solo se il Cittadino rappresentato dall'oggetto che esegue il
     * metodo &egrave; uguale a quello specificato tramite l'argomento.
     *
     * @param cittadino Oggetto passato come parametro al metodo
     * @return boolean
     */
    public boolean equals(Cittadino cittadino) {
        return this.toString().equals(cittadino.toString());
    }

}//END_Cittadino_Class
