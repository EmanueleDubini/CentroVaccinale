/*
  LABORATORIO INTERDISCIPLINARE A - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.serverCV.gui;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.centrivaccinali.CentroVaccinale;
import org.example.centrivaccinali.Cittadino;
import org.example.database.DataModel;
import org.example.database.DbHelper;
import org.example.database.QueryDebug;
import org.example.serverCV.ServerCV;
import org.example.serverCV.IpAddressServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.serverCV.ServerCV.PORT;


/**
 *
 * Classe controller di ServerCV
 *
 */
public class ServerCVController {
    public ServerCVController() {
    }

    @FXML
    private Button connectButton;
    @FXML
    private Button resetButton;
    @FXML
    public TextArea logArea;
    @FXML
    private TextField dbUsername;
    @FXML
    private PasswordField dbPassword;
    @FXML
    private TextField dbHost;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label hostLabel;
    @FXML
    private MenuBar menuBar;

    String username = DbHelper.getUsername();
    String password = DbHelper.getPassword();
    String host = DbHelper.getDbAddress();

    private final int NUMCT = 1000; // numero CT generati auto
    private int NUMCV = 1000; // numero CV generati auto

    @FXML
    public void serverLogin() throws RemoteException {
        checkLogin();
    }


    private void checkLogin() throws RemoteException {
        String usernameText = "postgres";
        String passwordText = "postgres";
        String hostText = "localhost";

        // DA TENERE
        //String usernameText = dbUsername.getText();
        //String passwordText = dbPassword.getText();
        //String hostText = dbHost.getText();



        if ((usernameText.compareTo(username) == 0) && (passwordText.compareTo(password) == 0) && (hostText.compareTo(host) == 0)) {

            usernameLabel.setVisible(false);
            passwordLabel.setVisible(false);
            hostLabel.setVisible(false);
            dbUsername.setVisible(false);
            dbPassword.setVisible(false);
            dbHost.setVisible(false);
            connectButton.setVisible(false);
            resetButton.setVisible(false);
            menuBar.setVisible(true);
            logArea.appendText("User " + "'" + username + "'" + " connected\n");

            //se registry è null vuol dire che non è ancora stato creato
            //il registry dovrebbe essere stato creato nel main
            if(ServerCV.registry != null) {
                logArea.appendText("Server   " + IpAddressServer.getServerAddress() + "   ready " + "on port:   " + PORT + "\n");            }else {
                ServerCV.registry = LocateRegistry.createRegistry(PORT);
            }

            DataModel p = new DataModel();
            p.generateAll();


        } else if (usernameText.isEmpty() && passwordText.isEmpty() && hostText.isEmpty()) {
            logArea.appendText("Compila TUTTI i campi\n");

        } else if (passwordText.compareTo(password) == 0 && hostText.compareTo(host) == 0) {
            logArea.appendText("Campo 'username' non corretto\n");

        } else if (usernameText.compareTo(username) == 0 && hostText.compareTo(host) == 0) {
            logArea.appendText("Campo 'password' non corretto\n");

        } else if (usernameText.compareTo(username) == 0 && passwordText.compareTo(password) == 0) {
            logArea.appendText("Campo 'host' non corretto\n");

        } else logArea.appendText("Inserimento dati non corretto\n");
    }

    public void reset(){
        dbUsername.setText("");
        dbPassword.setText("");
        dbHost.setText("");
    }


    public void guida(){
        Alert info = new Alert(Alert.AlertType.NONE,
                """
                        'Centro Vaccinale' - Server CV

                        Se siete arrivati fino a qui sapete bene di cosa si tratta.
                        Se volete, leggete pure questa guida, ma l'unica risposta che troverete sarà 42.""", ButtonType.OK);

        info.showAndWait();
    }

   public void info() {
        Alert info = new Alert(Alert.AlertType.NONE,
                """
                        'Centro Vaccinale' - Server CV

                        BANCORA Davide, The Pianist
                        CASALNOVO Giacomo, Symphonic Metal Man
                        DONATO Benedetta, The Beauty
                        DUBINI Emanuele, Money Man

                        UnInsubria.it
                        Copyleft - all rights reversed""", ButtonType.OK);

        info.showAndWait();

    }

    // DB
    public void createDb() throws SQLException {
        DbHelper.getConnection();
        Statement statement = DbHelper.getConnection().createStatement();
        statement.executeUpdate(QueryDebug.createDB);
        statement.close();
        DbHelper.closeConnection();
        logArea.appendText("Database 'centrivaccinalidb' creato\n");
    }

    public void dropDb() throws SQLException {
        //DbHelper.closeConnection();
        DbHelper.getConnection();
        Statement statement = DbHelper.getConnection().createStatement();
        statement.executeUpdate(QueryDebug.dropDB);
        statement.close();
        DbHelper.closeConnection();
        logArea.appendText("Database 'centrivaccinalidb' eliminato\n");
    }

    // METODI DELLE TABELLE
    public void createCentriVaccinaliTable() throws SQLException {
        DbHelper.getConnection();
        Statement statement = DbHelper.getConnection().createStatement();
        statement.executeUpdate(QueryDebug.createCentriVaccinaliTable);
        statement.close();
        DbHelper.closeConnection();
        logArea.appendText("Tabella 'centrivaccinali' creata\n");
    }

    public void dropCentriVaccinaliTable() throws SQLException {
        DbHelper.getConnection();
        Statement statement = DbHelper.getConnection().createStatement();
        statement.executeUpdate(QueryDebug.dropCentriVaccinaliTable);
        statement.close();
        DbHelper.closeConnection();
        logArea.appendText("Tabella 'centrivaccinali' eliminata\n");
    }

    public void createCittadiniRegistratiTable() throws SQLException {
        DbHelper.getConnection();
        Statement statement = DbHelper.getConnection().createStatement();
        statement.executeUpdate(QueryDebug.createCittadiniRegistratiTable);
        statement.close();
        DbHelper.closeConnection();
        logArea.appendText("Tabella 'cittadini_registrati' creata\n");
    }

    public void dropCittadiniRegistratiTable() throws SQLException {
        DbHelper.getConnection();
        Statement statement = DbHelper.getConnection().createStatement();
        statement.executeUpdate(QueryDebug.dropCittadiniRegistratiTable);
        statement.close();
        DbHelper.closeConnection();
        logArea.appendText("Tabella 'cittadini_registrati' eliminata\n");
    }

    public void createEventiAvversiTable() throws SQLException {
        DbHelper.getConnection();
        Statement statement = DbHelper.getConnection().createStatement();
        statement.executeUpdate(QueryDebug.createEventiAvversiTable);
        statement.close();
        DbHelper.closeConnection();
        logArea.appendText("Tabella 'eventi_avversi' creata\n");
    }

    public void dropEventiAvversiTable() throws SQLException {
        DbHelper.getConnection();
        Statement statement = DbHelper.getConnection().createStatement();
        statement.executeUpdate(QueryDebug.dropEventiAvversiTable);
        statement.close();
        DbHelper.closeConnection();
        logArea.appendText("Tabella 'eventi_avversi' eliminata\n");
    }

    // METODI DEGLI INSERIMENTI TABELLE (debug)
   public void inserimentoCentriVaccinali() throws SQLException {
        PreparedStatement statement = DbHelper.getConnection().prepareStatement(
                "INSERT INTO centrivaccinali(idCentroVaccinale, nome, indirizzo, comune, provincia, cap, tipologia) VALUES (?,?,?,?,?,?,?)");

        // numero CV generati auto
        for(int i = 0; i < NUMCV; i++){
            CentroVaccinale cv = DataModel.generateCentroVaccinale();
            statement.setString(1, cv.getIdCentroVacciale());
            statement.setString(2, cv.getNome());
            statement.setString(3, (cv.getIndirizzo().getQualificatore().name() + " " +
                                                   cv.getIndirizzo().getNome() + " " +
                                                   cv.getIndirizzo().getNumeroCivico()));
            statement.setString(4, cv.getIndirizzo().getComune());
            statement.setString(5, cv.getIndirizzo().getProv());
            statement.setInt(6, cv.getIndirizzo().getCap());
            statement.setString(7, cv.getTipologia().name());
            statement.executeUpdate();
        }
        logArea.appendText("Dati in tabella 'centrivaccinali' inseriti\n");


    }

    public void inserimentoCittadini() throws SQLException {
        PreparedStatement statement = DbHelper.getConnection().prepareStatement(
                "INSERT INTO cittadini_registrati(codiceFiscale, cognomeCittadino, nomeCittadino, email, userid, password, idVaccinazione) VALUES (?,?,?,?,?,?,?)");

        //String cf;
        for(int i = 0; i < NUMCT; i++){
            Cittadino ct = DataModel.generateCittadino();

            //cf = (ct.getCodiceFiscale()).replaceAll("\\u0000", " ");
            statement.setString(1, ct.getCodiceFiscale());
            statement.setString(2, ct.getCognome());
            statement.setString(3, ct.getNome());
            statement.setString(4, ct.getEmail());
            statement.setString(5, ct.getUserId());
            statement.setString(6, ct.getPassword());
            statement.setString(7, ct.getIdVaccinazione());
            statement.executeUpdate();
        }
        logArea.appendText("Dati in tabella 'cittadini_registrati' inseriti\n");

    }

}//END_ServerCVController

