/*
  LABORATORIO INTERDISCIPLINARE A - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.serverCV;

import org.example.database.DbHelper;
import org.example.serverCV.gui.ServerCVMain;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.database.QueryRicerca.cercaCentroVaccinalePerComuneTipologia;
import static org.example.database.QueryRicerca.cercaCentroVaccinalePerNome;

/**
 * Classe <code>ServerCV</code> che costituisce l'entry point della sola parte server (e per debug)
 *
 */
public class ServerCV extends UnicastRemoteObject implements ServerCVI{
    private static final long serialVersionUID = 1L; //sono oggetti serializzati
    public static final int PORT = 1200; //todo sistemare e mettera la porta come campo statico nell'interfaccia del server
    public static Registry registry;


    // Oggetto server
    protected ServerCV() throws RemoteException {
        super();
    }

    /// IMPLEMENTAZIONE Metodi dell'interfaccia ServerCVI ///

    @Override
    public synchronized Boolean registraCentroVaccinale(String id, String nomeCV, String indirizzo, String comune, String provincia, String cap, String tipologia) throws SQLException {
        DbHelper.getConnection();
        Statement statement = DbHelper.getStatement();
        statement.executeUpdate("INSERT INTO centrivaccinali " +
                "(idCentroVaccinale, nome, indirizzo, comune, provincia, cap, tipologia)" +
                "VALUES(" + "'" + id + "'," + "'" + nomeCV + "'," + "'" + indirizzo + "'," + "'" + comune + "'," + "'" + provincia + "'," + "'" + cap + "'," + "'" + tipologia + "'" + ")");

        System.out.println("SERVER: registracentroVaccinale() eseguito correttamente");
        return true;
    }

    public synchronized Boolean registraVaccinato(String id, String nomeCV, String cognome, String nome, String cf, String dataSomministrazione, String vaccinoSomministrato, String idVaccinazione) throws SQLException {

        String vaccinati_table = "vaccinati_" + nomeCV;

        DbHelper.getConnection();
        Statement statement = DbHelper.getStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + vaccinati_table + "(" +
                "idCentroVaccinale VARCHAR(36)," +
                "cognomeCittadino VARCHAR(50) NOT NULL," +
                "nomeCittadino VARCHAR(50) NOT NULL," +
                "cf VARCHAR(50) PRIMARY KEY," +
                "dataSomministrazione VARCHAR(50) NOT NULL," +
                "vaccinoSomministrato VARCHAR(50) NOT NULL," +
                "idVaccinazione VARCHAR(36) NOT NULL" +
                ")");
        statement.executeUpdate("INSERT INTO " + vaccinati_table +
                "(idCentroVaccinale, cognomeCittadino, nomeCittadino, cf, dataSomministrazione, vaccinoSomministrato, idVaccinazione)" +
                "VALUES(" + "'" + id + "'," + "'" + cognome + "'," + "'" + nome + "'," + "'" + cf + "'," + "'" + dataSomministrazione + "'," + "'" + vaccinoSomministrato + "'," + "'"+ idVaccinazione + "'" + ")");

        System.out.println("SERVER: registracentroVaccinale() eseguito correttamente");
        return true;
    }

    public synchronized void registraCittadino() {
        //todo
    }

    @Override
    public synchronized void login() throws RemoteException {
        //todo
    }

    /*
    public void login() throws SQLException{
        PreparedStatement statement = DbHelper.getConnection().prepareStatement("SELECT * " +
                                                                                "FROM cittadini_registrati" +
                                                                                "WHERE userid = '" + getUser + "'" +        //todo Implementare metodo per prendere l'id scritto dall'utente in fase di Login
                                                                                "AND password = '" + getPassword +"'"       //todo Implementare metodo per prendere la password scritta dall'utente in fase di Login
        );
        //todo
    }
     */

    public synchronized void cercaCentroVaccinale() {
        //todo
    }

    public synchronized void cercaCentroVaccinaleNome() throws RemoteException, SQLException {
        DbHelper.getConnection();
        Statement statement = DbHelper.getStatement();
        ResultSet rs1 = statement.executeQuery(cercaCentroVaccinalePerNome);

        while (rs1.next()){
            String id = rs1.getString("idCentroVaccinale");
            String nome = rs1.getString("nome");
            String indirizzo = rs1.getString("indirizzo");
            String comune = rs1.getString("comune");
            String provincia = rs1.getString("provincia");
            String tipologia = rs1.getString("tipologia");

            System.out.println("Id = " + id + " |Nome = " + nome + " |Indirizzo = " + indirizzo + " |Comune = " + comune + " |Provincia = " + provincia + " |Tipologia = " + tipologia);
        }
    }

    public synchronized void cercaCentroVaccinaleComuneTipologia() throws RemoteException, SQLException {

        DbHelper.getConnection();
        Statement statement = DbHelper.getStatement();
        ResultSet rs2 = statement.executeQuery(cercaCentroVaccinalePerComuneTipologia);

        while (rs2.next()) {
            String id = rs2.getString("idCentroVaccinale");
            String nome = rs2.getString("nome");
            String indirizzo = rs2.getString("indirizzo");
            String comune = rs2.getString("comune");
            String provincia = rs2.getString("provincia");
            String tipologia = rs2.getString("tipologia");

            System.out.println("Id = " + id + " |Nome = " + nome + " |Indirizzo = " + indirizzo + " |Comune = " + comune + " |Provincia = " + provincia + " |Tipologia = " + tipologia);
        }
    }

    public synchronized void visualizzaInfoCentroVaccinale() {
        //todo
    }

    public synchronized void inserisciEventiAvversi() {
        //todo
    }

    public static void main(String[] args) throws RemoteException {

        // security
        // policy file
        // stub

        //settiamo l'ip hostname con l'ip della macchina che esegue questo codice, ServerCV main()
        System.setProperty("java.rmi.server.hostname", IpAddressServer.getServerAddress());


        // Create an instance of the server object
        ServerCV serverCV = new ServerCV();

        //registriamo l'oggetto server sul registry
        registry = LocateRegistry.createRegistry(PORT);
        //bind
        registry.rebind("ServerCV", serverCV); //se è gia bound fa un bind con il nuovo valore



        // Partenza dell'interfaccia grafica
        ServerCVMain.main(args);

    }//END_Main

}//END_ServerCV