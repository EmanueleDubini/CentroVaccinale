/*
  LABORATORIO INTERDISCIPLINARE A - Como AA20-21

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
    Boolean registraCentroVaccinale(String id, String nomeCV, String qualificatore, String indirizzo, String numeroCivico, String comune, String provincia, String cap, String tipologia) throws RemoteException, SQLException;
    Boolean registraVaccinato(String id, String nomeCV, String cognome, String nome, String cf, String dataSomministrazione, String vaccinoSomministrato, String idVaccinazione) throws RemoteException, SQLException;


    // Metodi remoti per CITTADINI
    Boolean registraCittadino(String cf, String cognome, String nome, String email, String username, String password, String idVaccinazione) throws RemoteException, SQLException;
    Boolean login(String username, String password) throws RemoteException, SQLException;
    void cercaCentroVaccinale() throws RemoteException;
    ArrayList<CentroVaccinale> cercaCentroVaccinaleNome(String nomeCV) throws RemoteException, SQLException;
    void cercaCentroVaccinaleComuneTipologia() throws RemoteException, SQLException;
    void visualizzaInfoCentroVaccinale() throws RemoteException;


    // Metodi remoti per EVENTI_AVVERSI
    Boolean inserisciEventiAvversi(String id, String codiceFiscale,
                                   String mal_di_testa, String mal_di_testa_note,
                                   String febbre, String febbre_note,
                                   String dolori_muscolari_e_articolari, String dolori_muscolari_e_articolari_note,
                                   String linfoadenopatia, String linfoadenopatia_note,
                                   String tachicardia, String tachicardia_note,
                                   String crisi_ipertensiva, String crisi_ipertensiva_note) throws RemoteException, SQLException;
}
