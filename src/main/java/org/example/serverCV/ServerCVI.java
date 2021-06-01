/*
  LABORATORIO INTERDISCIPLINARE A - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.serverCV;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ServerCVI extends Remote {

    // centri_vaccinali
    Boolean registraCentroVaccinale(String id, String nomeCV, String indirizzo, String comune, String provincia, String cap, String tipologia) throws RemoteException, SQLException;
    void registraVaccinato() throws RemoteException;

    // cittadini
    void registraCittadino() throws RemoteException;
    void login() throws RemoteException;
    void cercaCentroVaccinale() throws RemoteException;
    void cercaCentroVaccinaleNome() throws RemoteException, SQLException;
    void cercaCentroVaccinaleComuneTipologia() throws RemoteException, SQLException;
    void visualizzaInfoCentroVaccinale() throws RemoteException;



    // eventi_avversi
    void inserisciEventiAvversi() throws RemoteException;
}
