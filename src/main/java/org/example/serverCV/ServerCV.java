/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.serverCV;

import org.example.common.TipologiaCV;
import org.example.common.CentroVaccinale;
import org.example.common.Indirizzo;
import org.example.common.Qualificatore;
import org.example.database.DbHelper;
import org.example.serverCV.gui.ServerCVMain;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe <code>ServerCV</code> che costituisce l'entry point della sola parte server dell'applicazione (e per debug)
 * e contiene le implementazione die metodi remoti per RMI.
 * Porta di default = 1200
 */
public class ServerCV extends UnicastRemoteObject implements ServerCVI{
    @Serial
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
     * @return true se la registrazione va a buon fine
     *
     * @throws SQLException SQLException
     */
    @Override
    public synchronized Boolean registraCentroVaccinale(String id, String nomeCV, String qualificatore, String indirizzo, String numeroCivico, String comune, String provincia, String cap, String tipologia) throws SQLException {
        DbHelper.getConnection();
        Statement statement = DbHelper.getStatement();
        statement.executeUpdate("INSERT INTO centrivaccinali " +
                "(idCentroVaccinale, nome, qualificatore, indirizzo, numerocivico, comune, provincia, cap, tipologia)" +
                "VALUES(" + "'" + id + "'," + "'" + nomeCV + "',"+ "'" + qualificatore + "',"  + "'" + indirizzo + "'," + "'" + numeroCivico + "'," + "'" + comune + "'," + "'" + provincia + "'," + "'" + cap + "'," + "'" + tipologia + "'" + ")");

        System.out.println("SERVER: registracentroVaccinale() eseguito correttamente");
        return true;
    }

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
     * @return true se la registrazione va a buon fine
     *
     * @throws SQLException SQLException
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
                "codicefiscale VARCHAR(16) PRIMARY KEY," +
                "dataSomministrazione VARCHAR(50) NOT NULL," +
                "vaccinoSomministrato VARCHAR(50) NOT NULL," +
                "idVaccinazione VARCHAR(36) NOT NULL" +
                ")");
        statement.executeUpdate("INSERT INTO " + vaccinati_table +
                "(idCentroVaccinale, cognomeCittadino, nomeCittadino, codicefiscale, dataSomministrazione, vaccinoSomministrato, idVaccinazione)" +
                "VALUES(" + "'" + id + "'," + "'" + cognome + "'," + "'" + nome + "'," + "'" + cf + "'," + "'" + dataSomministrazione + "'," + "'" + vaccinoSomministrato + "'," + "'"+ idVaccinazione + "'" + ")");

        System.out.println("SERVER: registraVaccinato() eseguito correttamente");
        return true;
    }

    /**
     * Il metodo <code>getIdCentroVaccianlePerCV</code> estrae dal DB l'id del centro vaccinale passato come parametro
     *
     * @param nomeCV il nome del centro vaccinale di cui si vuole estrarre l'id
     * @return idLetto dal DB
     *
     * @throws SQLException SQLException
     */
    public synchronized String getIdCentroVaccianlePerCV(String nomeCV) throws SQLException{
        DbHelper.getConnection();
        Statement statement = DbHelper.getStatement();
        ResultSet rs = statement.executeQuery("SELECT idcentrovaccinale FROM centrivaccinali WHERE nome = '" + nomeCV + "'");

        String idLetto = "";

        while(rs.next()){
            idLetto = rs.getString("idcentrovaccinale");
        }

        return idLetto;
    }

    /// CITTADINI
    /// IMPLEMENTAZIONE Metodi dell'interfaccia ServerCVI ///

    /**
     * Il metodo <code>registraCittadino</code> registra nel DB un nuovo cittadino che e stato precendentemente vaccinato
     *
     * @param codicefiscale codice fiscale del cittadino
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
    @Override
    public synchronized Boolean registraCittadino(String codicefiscale, String cognome, String nome, String email, String username, String password, String idVaccinazione, String nomeCentroVaccinale) throws SQLException{
        DbHelper.getConnection();
        Statement statement = DbHelper.getStatement();
        ResultSet rs4 = statement.executeQuery("SELECT idcentrovaccinale FROM centrivaccinali WHERE nome = '" + nomeCentroVaccinale + "'");
        String idCentroVaccinale = "";
        while(rs4.next()){
            idCentroVaccinale = rs4.getString("idcentrovaccinale");
        }

        statement.executeUpdate("INSERT INTO cittadini_registrati " +
                "(codicefiscale, cognomecittadino, nomecittadino, email, username, password, idvaccinazione, idCentroVaccinale)" +
                "VALUES(" + "'" + codicefiscale + "'," + "'" + cognome + "'," + "'" + nome + "'," + "'" + email + "'," + "'" + username + "'," + "'" + password + "'," + "'" + idVaccinazione + "'," + "'" + idCentroVaccinale + "'" + ")");

        System.out.println("SERVER: registraCittadino() eseguito correttamente");
        return true;
    }

    /**
     * Il metodo <code>login</code> controlla nel DB la corrispondenza username/password per permettere l'accesso ai cittadini
     * registrati che volgiono inserire un evento avverso
     *
     * @param username username del vaccinato che vuole accedere
     * @param password password del vaccinato che vuole accedere
     *
     * @return true se il login va a buon fine
     * @throws RemoteException RemoteException
     * @throws SQLException SQLException
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

    /**
     * Il metodo <code>getIdCentroVaccinale</code> effettua la ricerca dell'id del centro vaccinale tramite user e password
     * inserite nella fase di login
     *
     * @param username username del cittadino che effettua login
     * @param password password del cittadino che effettua login
     *
     * @return <code>String</code> che contiene l'id del centro vaccinale corrispondente
     *
     * @throws SQLException SQLException
     */
    @Override
    public synchronized String getIdCentroVaccinale (String username, String password) throws SQLException {
        DbHelper.getConnection();
        PreparedStatement ps;
        ps = DbHelper.getConnection().prepareStatement("SELECT idcentrovaccinale " +
                                            "FROM cittadini_registrati " +
                                            "WHERE username = '" + username + "'" +
                                            "AND password = '" + password +"'");

        ResultSet result = ps.executeQuery();
        String idCentro = "";
        while (result.next()) {
            idCentro = result.getString("idcentrovaccinale");
        }
        return idCentro;
    }

    /**
     * Il metodo <code>getCentroFiscale</code> effettua la ricerca del codice fiscale tramite user e password
     * inserite nella fase di login
     *
     * @param username username del cittadino che effettua login
     * @param password password del cittadino che effettua login
     *
     * @return <code>String</code> che contiene il codice fiscale del cittadino che effettua login
     *
     * @throws SQLException SQLException
     */
    @Override
    public synchronized  String getCodiceFiscale (String username, String password) throws SQLException {
        DbHelper.getConnection();
        PreparedStatement ps;
        ps = DbHelper.getConnection().prepareStatement("SELECT codicefiscale " +
                "FROM cittadini_registrati " +
                "WHERE username = '" + username + "'" +
                "AND password = '" + password +"'");

        ResultSet result = ps.executeQuery();
        String codiceF = "codicef";
        while (result.next()) {
            codiceF = result.getString("codicefiscale");
        }
        return codiceF;
    }

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
    @Override
    public synchronized ArrayList<CentroVaccinale> cercaCentroVaccinaleNome(String nomeCV) throws RemoteException, SQLException {
        ArrayList<CentroVaccinale> centriVaccinali = new ArrayList<>();
        DbHelper.getConnection();
        Statement statement = DbHelper.getStatement();
        ResultSet rs1 = statement.executeQuery("SELECT * " +
                "FROM centrivaccinali " +
                "WHERE LOWER(nome) LIKE " + "LOWER('%" + nomeCV + "%')");


        //System.err.println(rs1.toString());

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
            //System.err.println(nome);

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
            //System.out.println(centriVaccinali);
        }
        //System.out.println(centriVaccinali);
        System.out.println("HO FINITO");
        return centriVaccinali;
    }

    /**
     * Il metodo <code>getAvg_Nsegnalazioni</code> calcola la media e il numero di segnalazioni di un dato centro vaccinale
     *
     * @param idCentroVaccinale l' id del centro vaccinale di cui si vuole sapere la media e il numero di segnalazioni
     *
     * @return un <code>array</code> che contiene in posizione [0] la media delle segnalazioni e in posizione [1] il numero delle segnalazioni
     *
     * @throws RemoteException RemoteException
     * @throws SQLException SQLException
     * @throws ArithmeticException ArithmeticException
     */
    public synchronized double[] getAvg_Nsegnalazioni(String idCentroVaccinale) throws RemoteException, SQLException, ArithmeticException {
        System.out.println(idCentroVaccinale);
        DbHelper.getConnection();
        Statement statement = DbHelper.getStatement();
        ResultSet rsSum = statement.executeQuery("SELECT SUM(mal_di_testa + febbre + dolori_muscolari_e_articolari + tachicardia + linfoadenopatia + crisi_ipertensiva) AS somma, COUNT (*) AS conta " +
                                                "FROM eventi_avversi " +
                                                "WHERE idcentrovaccinale = " + "'" + idCentroVaccinale + "'");
        int somma = 0;
        int conta = 0;
        double[] result = new double[2];

        while (rsSum.next()) {
            somma = rsSum.getInt("somma");
            conta = rsSum.getInt("conta");
        }

        if(conta != 0) {
            //è presente una o piu segnalazioni
            double media = (double) somma / ((double) conta * (double) 6);
            //modifichiamo le cifre decimali dopo la virgola della media della severia'. Per comodita' ne teniamo 2.
            double mediaTroncata = BigDecimal.valueOf(media).setScale(2, RoundingMode.HALF_UP).doubleValue();

            result[0] = mediaTroncata;
            result[1] = conta;
            return result;
        }
        else{
            //non sono presenti segnalazioni
            result[0] = -1;
            result[1] = conta; // conta = 0
            return result;
        }
    }

    /**
     * Il metodo <code>nomiCentriVaccinali</code> popola una lista con tutti i nomi dei centri vaccinali
     *
     * @return <code>ArrayList</code> che contiene una lista dei nomi dei Centri Vaccinali
     *
     * @throws SQLException SQLException
     */
    public synchronized ArrayList<String> nomiCentriVaccinali() throws SQLException {
        ArrayList<String> nomiCentri = new ArrayList<>();
        DbHelper.getConnection();
        Statement statement = DbHelper.getStatement();
        ResultSet rs3 = statement.executeQuery("SELECT nome " +
                "FROM centrivaccinali ORDER BY nome ASC");

        while (rs3.next()){
            String nome = rs3.getString("nome");
            nomiCentri.add(nome);
        }
        return nomiCentri;
    }

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
    public synchronized ArrayList<CentroVaccinale> cercaCentroVaccinaleComuneTipologia(String nomeComune, String tipologiaCV) throws RemoteException, SQLException { //todo questo metodo per ora non restituisce nulla e non riceve come parametro i criteri di ricerca
        ArrayList<CentroVaccinale> centriVaccinali = new ArrayList<>();

        DbHelper.getConnection();
        Statement statement = DbHelper.getStatement();
        ResultSet rs2 = statement.executeQuery("SELECT * " +
                "FROM centrivaccinali " +
                "WHERE LOWER(comune) LIKE " + "LOWER('%" + nomeComune + "%') AND " +
                    "tipologia = " + " '" + tipologiaCV + "'");

        while (rs2.next()) {
            String id = rs2.getString("idCentroVaccinale");
            String nome = rs2.getString("nome");
            String qualificatore = rs2.getString("qualificatore");
            String indirizzo = rs2.getString("indirizzo");
            String numeroCivico = rs2.getString("numeroCivico");
            String comune = rs2.getString("comune");
            String provincia = rs2.getString("provincia");
            String cap = rs2.getString("cap");
            String tipologia = rs2.getString("tipologia");

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
            //System.out.println(centriVaccinali);
        }
        //System.out.println(centriVaccinali);
        return centriVaccinali;
    }

    /**
     * //todo javadoc
     */
    public synchronized void visualizzaInfoCentroVaccinale() {
        //todo
    }


    /// EVENTI AVVERSI
    /// IMPLEMENTAZIONE Metodi dell'interfaccia ServerCVI ///

    /**
     * Il metodo <code>inserisciEventiAvversi</code> inserisce un evento avverso
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
    @Override
    public synchronized Boolean inserisciEventiAvversi(String id, String codiceFiscale,
                                                       int mal_di_testa, String mal_di_testa_note,
                                                       int febbre, String febbre_note,
                                                       int dolori_muscolari_e_articolari, String dolori_muscolari_e_articolari_note,
                                                       int tachicardia, String tachicardia_note,
                                                       int linfoadenopatia, String linfoadenopatia_note,
                                                       int crisi_ipertensiva, String crisi_ipertensiva_note) throws SQLException {

        //todo gestire l'eccezione che lancia nel caso in cui l'utente abbia già inserito l'evento avverso
        DbHelper.getConnection();
        Statement statement = DbHelper.getStatement();
        statement.executeUpdate("INSERT INTO eventi_avversi " +
                "(idcentrovaccinale, codicefiscale, mal_di_testa, mal_di_testa_note, febbre, febbre_note, dolori_muscolari_e_articolari, dolori_muscolari_e_articolari_note, tachicardia," +
                "tachicardia_note, linfoadenopatia, linfoadenopatia_note, crisi_ipertensiva, crisi_ipertensiva_note)" +
                "VALUES(" + "'" + id + "'," + "'" + codiceFiscale + "'," + "'" + mal_di_testa +  "','" + mal_di_testa_note +  "'," +  "'" + febbre + "'," + "'" + febbre_note + "'," + "'" + dolori_muscolari_e_articolari + "',"
                + "'" + dolori_muscolari_e_articolari_note + "'," + "'" + tachicardia + "'," + "'" + tachicardia_note + "'," + "'" + linfoadenopatia + "'," + "'" + linfoadenopatia_note +
                "'," + "'" + crisi_ipertensiva +"'," + "'" + crisi_ipertensiva_note +"'" + ")");

        System.out.println("SERVER: inserisciEventiAvversi() eseguito correttamente");
        return true;
    }

    /**
     * Metodo <code>verificaSeRegistrato</code> che verifica se l'utente che si sta registrando
     * non sia gia presente nella relazione cittadini-registrati presente nel database
     *
     * @param cfRegistrato codice fiscale del cittadino vaccinato che vuole registrarsi nell'applicazione cittadino
     * @param emailRegistrato email del cittadino vaccinato che vuole registrarsi nell'applicazione cittadino
     * @param usernameRegistrato username inserito dal cittadino vaccinato che vuole registrarsi nell'applicazione cittadino
     * @param idVaccinazioneRegistrato id di vaccinazione del cittadino vaccinato che vuole registrarsi nell'applicazione cittadino
     *
     * @return true se i campi inseriti dal cittadino registrato non sono gia presenti nel database e quindi esso puo registrasi, false altrimenti
     *
     * @throws RemoteException RemoteException
     */
    @Override
    public synchronized boolean verificaSeRegistrato(String cfRegistrato, String emailRegistrato, String usernameRegistrato, String idVaccinazioneRegistrato) throws RemoteException, SQLException {
        DbHelper.getConnection();
        Statement statement = DbHelper.getStatement();
        ResultSet rsSum = statement.executeQuery("SELECT COUNT (*) AS conta " +
                "FROM cittadini_registrati " +
                "WHERE codicefiscale = " + "'" + cfRegistrato + "' OR " +
                "email = " + "'" + emailRegistrato + "' OR " +
                "username = " + "'" + usernameRegistrato + "' OR " +
                "idvaccinazione = " + "'" + idVaccinazioneRegistrato + "'");

        int conta = 0;
        while (rsSum.next()) {
            conta = rsSum.getInt("conta");
        }
        if(conta > 0){
            // è stato trovato qualcosa nel result set quindi l'utente esiste gia
            return false;
        }else{
            // se conta è ancora zero vuol dire che i dati che l'utente ha inserito
            // non sono gia presenti nel db e quindi si puo registrare
            return true;
        }

    }

    /**
     * Metodo <code>verificaIdVaccinazione</code> che verifica se l'utente che si sta registrando
     * abbia inserito l'idVaccinazione corretto
     *
     * @param idVaccinazioneRegistrato id di vaccinazione del cittadino vaccinato che vuole registrarsi nell'applicazione cittadino
     * @param nomeCentroVaccinale nome del centro vaccinale in cui è stato somministrato il vaccino
     *
     * @return true se i campi inseriti dal cittadino registrato sono corretti, false altrimenti
     *
     * @throws RemoteException RemoteException
     * @throws SQLException SQLException
     */
    @Override
    public synchronized boolean verificaIdVaccinazione(String idVaccinazioneRegistrato, String nomeCentroVaccinale) throws RemoteException, SQLException {
        String vaccinati_table = "vaccinati_" + nomeCentroVaccinale;

        DbHelper.getConnection();
        Statement statement = DbHelper.getStatement();
        ResultSet rsSum = statement.executeQuery("SELECT COUNT (*) AS conta " +
                "FROM " + vaccinati_table +
                " WHERE idvaccinazione = " + "'" + idVaccinazioneRegistrato + "'");

        int conta = 0;
        while (rsSum.next()) {
            conta = rsSum.getInt("conta");
        }

        if(conta > 0){
            // è stato trovato qualcosa nel result set quindi l'id utente è presente
            return true;
        }else{
            // se conta è ancora zero vuol dire che l'id di vaccinazione non è presente nei sistemi
            return false;
        }
    }

    /**
     * Metodo <code>verificaEventoAvverso</code> che verifica se un utente ha gia inserito un avento avverso all'interno del database
     * effettuando la ricerca attraverso il codice fiscale
     * @param codiceF
     *
     * @return ritorna true se si puo effettuare l'inserimento di un centro vaccinale, altrimenti false
     *
     * @throws RemoteException RemoteException
     * @throws SQLException SQLException
     */
    @Override
    public synchronized boolean verificaEventoAvverso(String codiceF) throws RemoteException, SQLException {
        DbHelper.getConnection();
        Statement statement = DbHelper.getStatement();
        ResultSet rsSum = statement.executeQuery("SELECT COUNT (*) AS conta " +
                "FROM eventi_avversi " +
                "WHERE codicefiscale = " + "'" + codiceF + "'");

        int conta = 0;
        while (rsSum.next()) {
            conta = rsSum.getInt("conta");
        }
        if(conta > 0){
            // è stato trovato qualcosa nel result set quindi non si può inserire un evento avverso
            return false;
        }else{
            // se conta è ancora zero vuol dire che non è presente il suo codice fiscale nella relazione eventi_avversi
            // quindi può inserire un evanto avverso
            return true;
        }
    }

    /**
     * Metodo <code>Main</code> dell'applicazione ClientCV
     */
    public static void main(String[] args) throws RemoteException {

        //settiamo l'ip hostname con l'ip della macchina che esegue questo codice, ServerCV main()
        System.setProperty("java.rmi.server.hostname", IpAddressServer.getServerAddress());

        // Crea una instanza dell'oggetto server
        ServerCV serverCV = new ServerCV();

        //registriamo l'oggetto server sul registry
        registry = LocateRegistry.createRegistry(PORT);
        //bind
        registry.rebind("ServerCV", serverCV); //se è gia bound fa un bind con il nuovo valore

        // Partenza dell'interfaccia grafica
        ServerCVMain.main(args);

    }//END_Main

}//END_ServerCV