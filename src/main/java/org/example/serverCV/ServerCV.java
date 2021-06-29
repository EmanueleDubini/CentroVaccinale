/*
  LABORATORIO INTERDISCIPLINARE A - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.serverCV;

import org.example.centrivaccinali.gui.ClientCTController;
import org.example.centrivaccinali.TipologiaCV;
import org.example.common.CentroVaccinale;
import org.example.common.Indirizzo;
import org.example.common.Qualificatore;
import org.example.database.DbHelper;
import org.example.serverCV.gui.ServerCVMain;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.database.QueryRicerca.cercaCentroVaccinalePerComuneTipologia;
import static org.example.database.QueryRicerca.cercaCentroVaccinalePerNome;

/**
 * Classe <code>ServerCV</code> che costituisce l'entry point della sola parte server dell'applicazione (e per debug)
 * e contiene le implementazione die metodi remoti per RMI.
 * Porta di default = 1200
 */
public class ServerCV extends UnicastRemoteObject implements ServerCVI{
    private static final long serialVersionUID = 1L; //sono oggetti serializzati
    public static final int PORT = 1200; //todo sistemare e mettera la porta come campo statico nell'interfaccia del server
    public static Registry registry;


    // Oggetto server
    protected ServerCV() throws RemoteException {
        super();
    }

    /// CENTRI VACCINALI
    /// IMPLEMENTAZIONE Metodi dell'interfaccia ServerCVI ///

    /**
    * Metodo <code>registraCentroVaccinale</code> registra nel DB un nuovo Centro Vaccinale
    */
    @Override
    public synchronized Boolean registraCentroVaccinale(String id, String nomeCV, String qualificatore, String indirizzo, String numeroCivico, String comune, String provincia, String cap, String tipologia) throws SQLException {
        DbHelper.getConnection();
        Statement statement = DbHelper.getStatement();
        statement.executeUpdate("INSERT INTO centrivaccinali " +
                "(idCentroVaccinale, nome,qualificatore, indirizzo, numeroCivico, comune, provincia, cap, tipologia)" +
                "VALUES(" + "'" + id + "'," + "'" + nomeCV + "',"+ "'" + qualificatore + "',"  + "'" + indirizzo + "'," + "'" + numeroCivico + "'," + "'" + comune + "'," + "'" + provincia + "'," + "'" + cap + "'," + "'" + tipologia + "'" + ")");

        System.out.println("SERVER: registracentroVaccinale() eseguito correttamente");
        return true;
    }

    /**
     * Metodo <code>registraVaccinato</code> registra nel DB un nuovo cittadino che è appena stato vaccinato
     */
    @Override
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

        System.out.println("SERVER: registraVaccinato() eseguito correttamente");
        return true;
    }


    /// CITTADINI
    /// IMPLEMENTAZIONE Metodi dell'interfaccia ServerCVI ///

    /**
     * Metodo <code>registraCittadino</code> registra nel DB un nuovo cittadino che e stato precendentemente vaccinato
     */
    @Override
    public synchronized Boolean registraCittadino(String cf, String cognome, String nome, String email, String username, String password, String idVaccinazione) throws SQLException{
        DbHelper.getConnection();
        Statement statement = DbHelper.getStatement();
        statement.executeUpdate("INSERT INTO cittadini_registrati " +
                "(codicefiscale, cognomecittadino, nomecittadino, email, username, password, idvaccinazione)" +
                "VALUES(" + "'" + cf + "'," + "'" + cognome + "'," + "'" + nome + "'," + "'" + email + "'," + "'" + username + "'," + "'" + password + "'," + "'" + idVaccinazione + "'" + ")");

        System.out.println("SERVER: registraCittadino() eseguito correttamente");
        return true;
    }

    /**
     * Metodo <code>login</code> controlla nel DB la corrispondenza username/password per permettere l'accesso ai cittadini
     * registrati che volgiono inserire un evento avverso
     */
    @Override
    public synchronized Boolean login(String username, String password) throws RemoteException, SQLException {
        DbHelper.getConnection();
        PreparedStatement ps;
        ps = DbHelper.getConnection().prepareStatement("SELECT * " +
                "FROM cittadini_registrati " +
                "WHERE username = '" + username + "'" +
                "AND password = '" + password +"'");

        ResultSet result = ps.executeQuery();

        if(result.next()){
            return true;
        }
        else{
            System.err.println("Login Failed");
            return false;
        }

    }


    public synchronized void cercaCentroVaccinale() {
        //todo
    }

    @Override
    public synchronized ArrayList<CentroVaccinale> cercaCentroVaccinaleNome(String nomeCV) throws RemoteException, SQLException {
        ArrayList<CentroVaccinale> centriVaccinali = new ArrayList();
        DbHelper.getConnection();
        Statement statement = DbHelper.getStatement();
        //ResultSet rs1 = statement.executeQuery(cercaCentroVaccinalePerNome);
        ResultSet rs1 = statement.executeQuery("SELECT * " +
                "FROM centrivaccinali " +
                "WHERE nome LIKE " + "'%" + nomeCV + "%'");      //todo Metodo da implementare che deve restituire il nome del centro vaccinale che l'utente ha cercato


        System.err.println(rs1.toString());

        while (rs1.next()){
            String id = rs1.getString("idCentroVaccinale");
            String nome = rs1.getString("nome");
            String qualificatore = rs1.getString("qualificatore");
            String indirizzo = rs1.getString("indirizzo");
            String numeroCivico = rs1.getString("numeroCivico");
            String comune = rs1.getString("comune");
            String provincia = rs1.getString("provincia");
            String cap = rs1.getString("cap");
            String tipologia = rs1.getString("tipologia");

            System.err.println(nome);

            //assegnare il valore al qualificatore della via
            Qualificatore q;
            if(qualificatore.equalsIgnoreCase(Qualificatore.Via.name())){
                q = Qualificatore.Via;
            }else if(qualificatore.equalsIgnoreCase(Qualificatore.Viale.name())){
                q = Qualificatore.Viale;
            }else if(qualificatore.equalsIgnoreCase(Qualificatore.Piazza.name())){
                q = Qualificatore.Piazza;
            }else if(qualificatore.equalsIgnoreCase(Qualificatore.Largo.name())) {
                q = Qualificatore.Largo;
            }else if(qualificatore.equalsIgnoreCase(Qualificatore.Vicolo.name())) {
                q = Qualificatore.Vicolo;
            }else if(qualificatore.equalsIgnoreCase(Qualificatore.Piazzale.name())) {
                q = Qualificatore.Piazzale;
            }else{
                q = Qualificatore.Corso;
            }

            //assegnare il valore alla tipologia del centro vaccinale
            TipologiaCV t;
            if(tipologia.equalsIgnoreCase(TipologiaCV.Ospedaliero.name())){
                t = TipologiaCV.Ospedaliero;
            }else if(tipologia.equalsIgnoreCase(TipologiaCV.Aziendale.name())){
                t = TipologiaCV.Aziendale;}
            else{
                t = TipologiaCV.Hub;
            }

            //creazione dell'oggetto indirizzo per poter istanziare un oggetto CentroVaccinale
            CentroVaccinale CV = new CentroVaccinale(id, nome, new Indirizzo(q, indirizzo, numeroCivico,comune, Integer.parseInt(cap) , provincia), t);
            centriVaccinali.add(CV);
            System.out.println(centriVaccinali);
        }
        System.out.println(centriVaccinali);
        return centriVaccinali;
    }

    public synchronized void cercaCentroVaccinaleNome() throws RemoteException, SQLException {
        /*DbHelper.getConnection();
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
        }*/
    }

    public synchronized void cercaCentroVaccinaleComuneTipologia() throws RemoteException, SQLException { //todo questo metodo per ora non restituisce nulla e non riceve come parametro i criteri di ricerca

        DbHelper.getConnection();
        Statement statement = DbHelper.getStatement();
        ResultSet rs2 = statement.executeQuery(cercaCentroVaccinalePerComuneTipologia);

        while (rs2.next()) {
            String id = rs2.getString("idCentroVaccinale");
            String nome = rs2.getString("nome");
            String qualificatore = rs2.getString("qualificatore");
            String indirizzo = rs2.getString("indirizzo");
            String numeroCivico = rs2.getString("numeroCivico");
            String comune = rs2.getString("comune");
            String provincia = rs2.getString("provincia");
            String tipologia = rs2.getString("tipologia");

            //System.out.println("Id = " + id + " |Nome = " + nome + " |Indirizzo = " + indirizzo + " |Comune = " + comune + " |Provincia = " + provincia + " |Tipologia = " + tipologia);
        }
    }

    public synchronized void visualizzaInfoCentroVaccinale() {
        //todo
    }


    /// EVENTI AVVERSI
    /// IMPLEMENTAZIONE Metodi dell'interfaccia ServerCVI ///

    @Override
    public synchronized Boolean inserisciEventiAvversi(String id, String codiceFiscale,
                                                       String mal_di_testa, String mal_di_testa_note,
                                                       String febbre, String febbre_note,
                                                       String dolori_muscolari_e_articolari, String dolori_muscolari_e_articolari_note,
                                                       String linfoadenopatia, String linfoadenopatia_note,
                                                       String tachicardia, String tachicardia_note,
                                                       String crisi_ipertensiva, String crisi_ipertensiva_note) throws SQLException {


        DbHelper.getConnection();
        Statement statement = DbHelper.getStatement();
        statement.executeUpdate("INSERT INTO eventi_avversi " +
                "(idcentrovaccinale, codicefiscale, mal_di_testa, mal_di_testa_note, febbre, febbre_note, dolori_muscolari_e_articolari, dolori_muscolari_e_articolari_note, linfoadenopatia," +
                "linfoadenopatia_note, tachicardia, tachicardia_note, crisi_ipertensiva, crisi_ipertensiva_note)" +
                "VALUES(" + "'" + id + "'," + "'" + codiceFiscale + "'," + "'" + mal_di_testa +  "','" + mal_di_testa_note +  "'," +  "'" + febbre + "'," + "'" + febbre_note + "'," + "'" + dolori_muscolari_e_articolari + "',"
                + "'" + dolori_muscolari_e_articolari_note + "'," + "'" + linfoadenopatia + "'," + "'" + linfoadenopatia_note + "'," + "'" + tachicardia + "'," + "'" + tachicardia_note +
                "'," + "'" + crisi_ipertensiva +"'," + "'" + crisi_ipertensiva_note +"'" + ")");

        System.out.println("SERVER: inserisciEventiAvversi() eseguito correttamente");
        return true;
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