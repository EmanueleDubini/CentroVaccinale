/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.centrivaccinali.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class ClientCTController  implements Initializable{

    //TextField relativi alla pagina: 03CT_LoginWindow
    @FXML
    private TextField TextFieldUsername;

    @FXML
    private PasswordField TextFieldPassword;

    //TextField relativi alla pagina: 03CT_RegistrazioneAdCV
    @FXML
    public ComboBox<String> nomeCentroComboBox = new ComboBox();

    @FXML
    private TextField TextFieldNomeVaccinato;

    @FXML
    private TextField TextFieldCognomeVaccinato;

    @FXML
    private TextField TextFieldCFVaccinato;

    @FXML
    private TextField TextFieldEmailVaccinato;

    @FXML
    private TextField TextFieldUsernameVaccinato;

    @FXML
    private TextField TextFieldPasswordVaccinato;

    @FXML
    private TextField TextFieldIdVaccinazioneVaccinato;

    //TextField & Combobox relativi alla pagina: 04CT_EventiAvver

    @FXML
    private TextField severitaMalDiTesta;
    @FXML
    private TextField noteMalDiTesta;

    @FXML
    private TextField severitaFebbre;
    @FXML
    private TextField noteFebbre;

    @FXML
    private TextField severitaDolori;
    @FXML
    private TextField noteDolori;

    @FXML
    private TextField severitaTachicardia;
    @FXML
    private TextField noteTachicardia;

    @FXML
    private TextField severitaLinfoadenopatia;
    @FXML
    private TextField noteLinfoadenopatia;

    @FXML
    private TextField severitaCrisiIpertensiva;
    @FXML
    private TextField noteCrisiIpertensiva;

    String severita1, note1;
    String severita2, note2;
    String severita3, note3;
    String severita4, note4;
    String severita5, note5;
    String severita6, note6;

    String nomeRegistrato, cognomeRegistrato, cfRegistrato, emailRegistrato ,usernameRegistrato, passwordRegistrato,  idVaccinazioneRegistrato, nomeCentroVaccinale;

    /**
     * Questo metodo inizializza il combobox della finestra 03CT_RegistrazioneAdCV per inserire il nome del centro vaccinale
     *
     * @param arg0
     * @param resourceBundle
     */
    @Override
    public void initialize(URL arg0, ResourceBundle resourceBundle) {

        ////////////// COMBO BOX NOME CENTRO VACCINALE //////////////
        nomeCentroComboBox.setValue("");
        try {
            List<String> nomiCentriVaccinali = ClientCVController.stub.nomiCentriVaccinali();
            for(String nome : nomiCentriVaccinali) {
                nomeCentroComboBox.getItems().add(nome);
            }
        } catch (RemoteException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Questo metodo crea la schermata iniziale 01_LandingPage
     * @throws IOException IOException
     */
    public void to_01_LandingPage() throws IOException {
        ClientCVMain.setRoot("01_LandingPage");
    }

    /**
     * Questo metodo crea la schermata principale dell'applicazione cittadini 02CT_MainWindow
     * @throws IOException IOException
     */
    public void to_02CT_MainWindow() throws IOException {
        ClientCVMain.setRoot("02CT_MainWindow");
    }

    /**
     * Questo metodo crea la schermata 03CT_RegistraCT, per la registrazione da parte del cittadino
     * @throws IOException IOException
     */
    public void to_03CT_RegistraCT() throws IOException {
        ClientCVMain.setRoot("03CT_RegistrazioneAdCV");
    }

    /**
     * Questo metodo crea la schermata 03CT_EventiAvversiCT, per registrare eventi avversi da parte del cittadino
     * @throws IOException IOException
     */
    public void to_03CT_EventiAvversiCT() throws IOException {
        ClientCVMain.setRoot("03CT_LoginWindow");
    }

    /**
     * Questo metodo crea la schermata 03CT_InfoCV, per visualizzare le informazioni dei centri vaccinali
     * @throws IOException IOException
     */
    public void to_03CT_InfoCV() throws IOException {
        ClientCVMain.setRoot("03CT_CercaCV");
    }

    /**
     * Questo metodo crea la schermata 03CT_LoginWindow, per fare il login
     * @throws IOException IOException
     */
    public void to_03CT_LoginWindow(ActionEvent actionEvent) throws IOException {
        ClientCVMain.setRoot("03CT_LoginWindow");
    }

    /**
     * Questo metodo crea la schermata 04CT_EventiAvversiCT, per visualizzare e inserire gli eventi avversi dopo la
     * somministrazine del vaccino.
     * @throws IOException IOException
     */
    public void to_04CT_EventiAvversiCT() throws IOException {
        ClientCVMain.setRoot("04CT_EventiAvversiCT");
    }

    /**
     * Questo metodo è associato al bottone "exit" dell'applicazione per chiuderla
     */
    public void onClickQuit() {
        Platform.exit();
    }


    /**
     * Questo metodo crea un messaggio di Alert che restituisce informazioni riguardanti il progetto
     */
    public void guida(){
        Alert info = new Alert(Alert.AlertType.NONE,
                """
                        'Centro Vaccinale' - Client CV

                        Se siete arrivati fino a qui sapete bene di cosa si tratta.
                        Se volete, leggete pure questa guida, ma l'unica risposta che troverete sarà 42.""", ButtonType.OK);

        info.showAndWait();
    }

    /**
     * Questo metodo crea un messaggio di Alert che restituisce informazioni riguardanti i componenti
     * del gruppo che hanno partecipato alla realizzazione del Progetto
     */
    public void info() {
        Alert info = new Alert(Alert.AlertType.NONE,
                """
                        'Centro Vaccinale' - Client CV

                        BANCORA Davide, The Pianist
                        CASALNOVO Giacomo, Symphonic Metal Man
                        DONATO Benedetta, The Beauty
                        DUBINI Emanuele, Money Man

                        UnInsubria.it
                        Copyleft - all rights reversed""", ButtonType.OK);

        info.showAndWait();
    }

    /**
     * Questo metodo è collegato al bottone ' Registra Cittadino' dell'applicaizione cittadino
     * nel file "03CT_RegistrazioneAdCV.fxml".
     *
     * contiene dei controlli relativi a:
     * - Campi vuoti
     * - Codice Fiscale
     * - ID vaccinazione
     * - Email
     *
     * Resituisce un messaggio di errore nel caso in cui i campi inseriti dall'utente
     * non siano corretti
     */
    public void GeneraCittadinoRegistrato(ActionEvent actionEvent) throws SQLException, RemoteException {
        ////////////// campi registrazione Cittadino //////////////
        nomeRegistrato = TextFieldNomeVaccinato.getText().strip();
        cognomeRegistrato = TextFieldCognomeVaccinato.getText().strip();
        cfRegistrato = TextFieldCFVaccinato.getText().toUpperCase().strip();
        emailRegistrato = TextFieldEmailVaccinato.getText().strip();
        usernameRegistrato = TextFieldUsernameVaccinato.getText().strip();
        passwordRegistrato = TextFieldPasswordVaccinato.getText().strip();
        idVaccinazioneRegistrato = TextFieldIdVaccinazioneVaccinato.getText().strip();
        nomeCentroVaccinale = nomeCentroComboBox.getValue();


        //controllo se tutti i campi sono vuoti
        if ((nomeRegistrato.equals("") ||
                cognomeRegistrato.equals("")) ||
                cfRegistrato.equals("") ||
                emailRegistrato.equals("") ||
                usernameRegistrato.equals("") ||
                passwordRegistrato.equals("") ||
                idVaccinazioneRegistrato.equals("") ||
                nomeCentroVaccinale.equals("")) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("Campi utili per la registrazione mancanti.\nRiprovare");

            alert.showAndWait();
        } //END_if

        //Controllo validità del CF
        else if(!(Pattern.matches("[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]", cfRegistrato))){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("'Codice Fiscale' non valido.\nRiprovare");

            alert.showAndWait();
        }

        //Controllo validità id vaccinazione
        else if(idVaccinazioneRegistrato.length() != 16){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("'ID Vaccinazione' non valido.\nRiprovare");

            alert.showAndWait();
        }

        //Controllo validità email
        else if(!(Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", emailRegistrato))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("'Email' non valido.\nRiprovare");

            alert.showAndWait();
        } else {
            Boolean verify = ClientCVController.stub.registraCittadino(cfRegistrato, cognomeRegistrato, nomeRegistrato, emailRegistrato, usernameRegistrato, passwordRegistrato, idVaccinazioneRegistrato, nomeCentroVaccinale);
            if(verify) {
                resetInserimentoRegistrazione();
            }
        }
    }

    /**
     * Questo metodo resetta tutti i campi inseriti dall'utente dopo che si registra
     */
    private void resetInserimentoRegistrazione() {
        TextFieldNomeVaccinato.setText("");
        TextFieldCognomeVaccinato.setText("");
        TextFieldCFVaccinato.setText("");
        TextFieldEmailVaccinato.setText("");
        TextFieldUsernameVaccinato.setText("");
        TextFieldPasswordVaccinato.setText("");
        TextFieldIdVaccinazioneVaccinato.setText("");
        nomeCentroComboBox.setValue("");
    }

    /**
     * Questo metodo permette la ricerca di un Centro Vaccinale
     * @param actionEvent
     */
    public void cercaCentroVaccinale(ActionEvent actionEvent) {
    }

    /**
     * Controlla e verifica il login: se l'utente si é registrato correttamente inserendo
     * username e password. In caso contrario, lo fará notare all'utente con una stringa di errore
     * @param actionEvent
     * @throws SQLException SQLException
     * @throws IOException IOException
     */
    public void login(ActionEvent actionEvent) throws SQLException, IOException {
        String username = TextFieldUsername.getText();
        String password = TextFieldPassword.getText();

        Boolean verify = ClientCVController.stub.login(username, password);

        if(verify) {
            resetInserimentoLogin();
            to_04CT_EventiAvversiCT();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("Utente non registrato!");
            alert.showAndWait();
        }
    }

    /**
     * Resetta l'username e la password nella finestra di login.
     */
    private void resetInserimentoLogin() {
        TextFieldUsername.setText("");
        TextFieldPassword.setText("");
    }

    /**
     * Inserisce eventuali eventi avversi dopo la somministrazine del vaccino.
     * @throws SQLException SQLException
     * @throws RemoteException RemoteException
     */
    public void inserisciEventiAvversi(ActionEvent actionEvent) throws SQLException, RemoteException {

        severita1 = severitaMalDiTesta.getText();
        note1 = noteMalDiTesta.getText();

        severita2 = severitaFebbre.getText();
        note2 = noteFebbre.getText();

        severita3 = severitaDolori.getText();
        note3 = noteDolori.getText();

        severita4 = severitaTachicardia.getText();
        note4 = noteTachicardia.getText();

        severita5 = severitaLinfoadenopatia.getText();
        note5 = noteLinfoadenopatia.getText();

        severita6 = severitaCrisiIpertensiva.getText();
        note6 = noteCrisiIpertensiva.getText();

        if ((severita1.equals("")) || note1.equals("") ||
            (severita2.equals("")) || note2.equals("") ||
            (severita3.equals("")) || note3.equals("") ||
            (severita4.equals("")) || note4.equals("") ||
            (severita5.equals("")) || note5.equals("") ||
            (severita6.equals("")) || note6.equals("")) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("Campi utili per la registrazione mancanti.\nRiprovare");

            alert.showAndWait();
        } //END_if
        else {
            //todo sistemare per prendere dinamicamente id e codice fiscale

            Boolean verify = ClientCVController.stub.inserisciEventiAvversi("5f93b2ad-f2ee-442d-9c5b-d509c059562d", "PPPRRR98B10C933V", severita1, note1, severita2, note2, severita3, note3,severita4,note4,severita5, note5, severita6, note6);
        }
    }
}//End_Class
