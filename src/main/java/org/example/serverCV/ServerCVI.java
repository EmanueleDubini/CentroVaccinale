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
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Classe <code>ServerCVI</code> che contiene i metodi remoti senza implementazione per l'utilizzo con RMI
 *
 */
public interface ServerCVI extends Remote {


    // Metodi remoti per CENTRI_VACCINALI

    /**
     * Il metodo <code>registraCentroVaccinale</code> registra nel DB un nuovo Centro Vaccinale
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
     *
     * @return true se la registrazione va a buon fine
     *
     * @throws SQLException SQLException
     */
    Boolean registraCentroVaccinale(String id, String nomeCV, String qualificatore, String indirizzo, String numeroCivico, String comune, String provincia, String cap, String tipologia) throws RemoteException, SQLException;

    /**
     * Il metodo <code>registraVaccinato</code> registra nel DB un nuovo cittadino che è appena stato vaccinato
     *
     * @param id id del cittadino vaccinato
     * @param nomeCV nome del centro vaccinale in cui si e vaccinato il cittadino
     * @param cognome cognome del cittadino vaccinato
     * @param nome id del cittadino vaccinato
     * @param cf nome del cittadino vaccinato
     * @param dataSomministrazione data vaccinazione del cittadino vaccinato
     * @param vaccinoSomministrato tipo di vaccino somministrato
     * @param idVaccinazione id vaccinazione del cittadino vaccinato
     *
     * @return true se la registrazione va a buon fine
     *
     * @throws SQLException SQLException
     */
    Boolean registraVaccinato(String id, String nomeCV, String cognome, String nome, String cf, String dataSomministrazione, String vaccinoSomministrato, String idVaccinazione) throws RemoteException, SQLException;


    // Metodi remoti per CITTADINI

    /**
     * Il metodo <code>registraCittadino</code> registra nel DB un nuovo cittadino che e stato precendentemente vaccinato
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
     *
     * @throws SQLException SQLException
     */
    Boolean registraCittadino(String cf, String cognome, String nome, String email, String username, String password, String idVaccinazione, String nomeCentroVaccinale) throws RemoteException, SQLException;

    /**
     * Il metodo <code>login</code> controlla nel DB la corrispondenza username/password per permettere l'accesso ai cittadini
     * registrati che volgiono inserire un evento avverso
     *
     * @param username username del vaccinato che vuole accedere
     * @param password password del vaccinato che vuole accedere
     *
     * @return true se il login va a buon fine
     *
     * @throws RemoteException RemoteException
     * @throws SQLException SQLException
     */
    Boolean login(String username, String password) throws RemoteException, SQLException;


    /**
     * Il metodo <code>getIdCentroVaccianlePerCV</code> estrae dal DB l'id del centro vaccinale passato come parametro
     *
     * @param nomeCV il nome del centro vaccinale di cui si vuole estrarre l'id
     *
     * @return idLetto dal DB
     *
     * @throws RemoteException
     * @throws SQLException
     */
    String getIdCentroVaccianlePerCV(String nomeCV) throws RemoteException, SQLException;

    /**
     * Il metodo <code>cercaCentroVaccinaleNome</code> effettua la ricerca di un centro vaccinale tramite il nome
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
     * Il metodo <code>getAvg_Nsegnalazioni</code> calcola la media e il numero di segnalazioni di un dato centro vaccinale
     *
     * @param idCentroVaccinale l' id del centro vaccinale di cui si vuole sapere la media e il numero di segnalazioni
     *
     * @return un <code>array</code> che contiene in posizione [0] la media delle segnalazioni e in posizione [1] il numero delle segnalazioni
     *
     * @throws RemoteException
     * @throws SQLException
     */
    double[] getAvg_Nsegnalazioni(String idCentroVaccinale) throws RemoteException, SQLException;

    /**
     * Il metodo <code>getIdCentroVaccinale</code> effettua la ricerca dell'id del centro vaccinale tramite user e password
     * inserite nella fase di login
     *
     * @param username username del cittadino che effettua login
     * @param password password del cittadino che effettua login
     *
     * @return <code>String</code> che contiene l'ide del centro vaccinale corrispondente
     *
     * @throws RemoteException
     * @throws SQLException
     */
    String getIdCentroVaccinale(String username, String password) throws  RemoteException, SQLException;

    /**
     * Il metodo <code>getCentroFiscale</code> effettua la ricerca del codice fiscale tramite user e password
     * inserite nella fase di login
     *
     * @param username username del cittadino che effettua login
     * @param password password del cittadino che effettua login
     *
     * @return <code>String</code> che contiene il codice fiscale del cittadino che effettua login
     *
     * @throws RemoteException
     * @throws SQLException
     */
    String getCodiceFiscale(String username, String password) throws RemoteException, SQLException;

    /**
     * Il metodo <code>cercaCentroVaccinaleComuneTipologia</code> effettua la ricerca di un centro vaccinale tramite il nome
     *
     * @param  nomeComune comune del centro vaccinale secondo cui effettuare la ricerca
     * @param  tipologiaCV tipologia del centro vaccinale secondo cui effettuare la ricerca
     *
     * @return <code>ArrayList</code> che contiene tutti i centri vaccinali che rispettano i criteri di ricerca
     *
     * @throws RemoteException RemoteException
     * @throws SQLException SQLException
     */
    ArrayList<CentroVaccinale> cercaCentroVaccinaleComuneTipologia(String nomeComune, String tipologiaCV) throws RemoteException, SQLException;

    /**
     * Il metodo <code>nomiCentriVaccinali</code> popola una lista con tutti i nomi dei centri vaccinali
     *
     * @return <code>ArrayList</code> che contiene una lista dei nomi dei Centri Vaccinali
     *
     * @throws RemoteException
     * @throws SQLException
     */
    ArrayList<String> nomiCentriVaccinali() throws RemoteException, SQLException;

    void visualizzaInfoCentroVaccinale() throws RemoteException;

    //void visualizzaInfoCentroVaccinale() throws RemoteException;


    // Metodi remoti per EVENTI_AVVERSI

    /**
     * Il metodo <code>inserisciEventiAvversi</code> che inserisce un evento avverso
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
     *
     * @throws SQLException SQLException
     */
    Boolean inserisciEventiAvversi(String id, String codiceFiscale,
                                   int mal_di_testa, String mal_di_testa_note,
                                   int febbre, String febbre_note,
                                   int dolori_muscolari_e_articolari, String dolori_muscolari_e_articolari_note,
                                   int linfoadenopatia, String linfoadenopatia_note,
                                   int tachicardia, String tachicardia_note,
                                   int crisi_ipertensiva, String crisi_ipertensiva_note) throws RemoteException, SQLException;

    /**
     * Metodo <code>verificaSeRegistrato</code> che verifica se l'utente che si sta registrando
     * non sia gia presente nella relazione cittadini-registrati presente nel database
     *
     * @param cfRegistrato codice fiscale del cittadino vaccinato che vuole registrarsi nell'applicazione cittadino
     * @param emailRegistrato email del cittadino vaccinato che vuole registrarsi nell'applicazione cittadino
     * @param usernameRegistrato username inserito dal cittadino vaccinato che vuole registrarsi nell'applicazione cittadino
     * @param idVaccinazioneRegistrato id di vaccinazione del cittadino vaccinato che vuole registrarsi nell'applicazione cittadino
     *
     * @return ritorna true se i campi inseriti dal cittadino registrato non sono gia presenti nel database e quindi esso puo registrasi
     * @throws RemoteException RemoteException
     */
    Boolean verificaSeRegistrato(String cfRegistrato, String emailRegistrato, String usernameRegistrato, String idVaccinazioneRegistrato) throws RemoteException, SQLException;

    /**
     * Metodo <code>verificaSeRegistrato</code> che verifica l'id vaccinazione dell'utente che si sta registrando,
     * controllando che il suo id sia presente nel database
     *
     * @param idVaccinazioneRegistrato id di vaccinazione del cittadino vaccinato che vuole registrarsi nell'applicazione cittadino
     *
     * @return ritorna true se l'id vaccinazione inserito dal cittadino registrato è presente nel database e quindi esso puo registrasi
     *
     * @throws RemoteException RemoteException
     */
    boolean verificaIdVaccinazione(String idVaccinazioneRegistrato, String nomeCentroVaccinale) throws RemoteException, SQLException;
}


