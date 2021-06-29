/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.serverCV;

import org.example.common.CentroVaccinale;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Classe <code>ServerCVI</code> che contiene i metodi remoti senza implementazione per l'utilizzo con RMI
 *
 */
public interface ServerCVI extends Remote {

    // Metodi remoti per CENTRI_VACCINALI

    /**
     * Metodo <code>registraCentroVaccinale</code> registra nel DB un nuovo Centro Vaccinale
     *
     * @param id id del centro vaccinale
     * @param nomeCV nome del centro vaccinale
     * @param qualificatore qualificatore del centro vaccinale
     * @param indirizzo indirizzo del centro vaccinale
     * @param numeroCivico numero civico del centro vaccinale
     * @param comune comune del centro vaccinale
     * @param provincia provincia del centro vaccinale
     * @param cap cap del centro vaccinale
     * @param tipologia tipologia del centro vaccinale
     * @return true se la registrazione va a buon fine
     *
     * @throws SQLException SQLException
     */
    Boolean registraCentroVaccinale(String id, String nomeCV, String qualificatore, String indirizzo, String numeroCivico, String comune, String provincia, String cap, String tipologia) throws RemoteException, SQLException;

    /**
     * Metodo <code>registraVaccinato</code> registra nel DB un nuovo cittadino che è appena stato vaccinato
     *
     * @param id id del cittadino vaccinato
     * @param nomeCV nome del centro vaccinale in cui si e vaccinato il cittadino
     * @param cognome cognome del cittadino vaccinato
     * @param nome id del cittadino vaccinato
     * @param cf nome del cittadino vaccinato
     * @param dataSomministrazione data vaccinazione del cittadino vaccinato
     * @param vaccinoSomministrato tipo di vaccino somministrato
     * @param idVaccinazione id vaccinazione del cittadino vaccinato
     * @return true se la registrazione va a buon fine
     *
     * @throws SQLException SQLException
     */
    Boolean registraVaccinato(String id, String nomeCV, String cognome, String nome, String cf, String dataSomministrazione, String vaccinoSomministrato, String idVaccinazione) throws RemoteException, SQLException;


    // Metodi remoti per CITTADINI

    /**
     * Metodo <code>registraCittadino</code> registra nel DB un nuovo cittadino che e stato precendentemente vaccinato
     *
     * @param cf codice fiscale del cittadino
     * @param cognome cognome del cittadino
     * @param nome nome del cittadino
     * @param email email del cittadino
     * @param username username del cittadino
     * @param password password del cittadino
     * @param idVaccinazione id vaccinazione del cittadino
     *
     * @return @return true se la registrazione va a buon fine
     * @throws SQLException SQLException
     */
    Boolean registraCittadino(String cf, String cognome, String nome, String email, String username, String password, String idVaccinazione) throws RemoteException, SQLException;

    /**
     * Metodo <code>login</code> controlla nel DB la corrispondenza username/password per permettere l'accesso ai cittadini
     * registrati che volgiono inserire un evento avverso
     *
     * @param username username del vaccinato che vuole accedere
     * @param password password del vaccinato che vuole accedere
     *
     * @return true se il login va a buon fine
     * @throws RemoteException RemoteException
     * @throws SQLException SQLException
     */
    Boolean login(String username, String password) throws RemoteException, SQLException;
    void cercaCentroVaccinale() throws RemoteException;

    /**
     * Metodo <code>cercaCentroVaccinaleNome</code> che effettua la ricerca di un centro vaccinale tramite il nome
     *
     * @param nomeCV nome del centro vaccinale secondo cui effettuare la ricerca
     *
     * @return <code>ArrayList</code> che contiene tutti i centri vaccinali che rispettano i criteri di ricerca
     *
     * @throws RemoteException RemoteException
     * @throws SQLException SQLException
     */
    ArrayList<CentroVaccinale> cercaCentroVaccinaleNome(String nomeCV) throws RemoteException, SQLException;

    /**
     * //todo javadoc, prendere dalla classe serverCV
     * @throws RemoteException
     * @throws SQLException
     */
    void cercaCentroVaccinaleComuneTipologia() throws RemoteException, SQLException;
    void visualizzaInfoCentroVaccinale() throws RemoteException;


    // Metodi remoti per EVENTI_AVVERSI

    /**
     * Metodo <code>inserisciEventiAvversi</code> che inserisce un evento avverso
     *
     * @param id id del cittadino vaccinato che vuole inserire un evento avverso
     * @param codiceFiscale codice fiscale del cittadino vaccinato che vuole inserire un evento avverso
     * @param mal_di_testa evento avverso mal di testa
     * @param mal_di_testa_note nota evento avverso mal di testa
     * @param febbre evento avverso febbre
     * @param febbre_note nota evento avverso febbre
     * @param dolori_muscolari_e_articolari evento avverso dolori muscolari e articolari
     * @param dolori_muscolari_e_articolari_note note evento avverso dolori muscolari e articolari
     * @param linfoadenopatia evento avverso linfoadenopatia
     * @param linfoadenopatia_note note evento avverso linfoadenopatia
     * @param tachicardia evento avverso tachicardia
     * @param tachicardia_note note evento avverso tachicardia
     * @param crisi_ipertensiva evento avverso crisi ipertensiva
     * @param crisi_ipertensiva_note note evento avverso crisi ipertensiva
     *
     * @return true se l'inserimento dell'evento avverso va a buon fine
     * @throws SQLException
     */
    Boolean inserisciEventiAvversi(String id, String codiceFiscale,
                                   String mal_di_testa, String mal_di_testa_note,
                                   String febbre, String febbre_note,
                                   String dolori_muscolari_e_articolari, String dolori_muscolari_e_articolari_note,
                                   String linfoadenopatia, String linfoadenopatia_note,
                                   String tachicardia, String tachicardia_note,
                                   String crisi_ipertensiva, String crisi_ipertensiva_note) throws RemoteException, SQLException;
}
