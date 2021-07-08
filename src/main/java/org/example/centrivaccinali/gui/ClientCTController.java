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
    private Label labelMalDiTesta;
    @FXML
    private TextField severitaMalDiTesta;
    @FXML
    private TextField noteMalDiTesta;

    @FXML
    private Label labelFebbre;
    @FXML
    private TextField severitaFebbre;
    @FXML
    private TextField noteFebbre;

    @FXML
    private Label labelDolori;
    @FXML
    private TextField severitaDolori;
    @FXML
    private TextField noteDolori;

    @FXML
    private Label labelTachicardia;
    @FXML
    private TextField severitaTachicardia;
    @FXML
    private TextField noteTachicardia;

    @FXML
    private Label labelLinfoadenopatia;
    @FXML
    private TextField severitaLinfoadenopatia;
    @FXML
    private TextField noteLinfoadenopatia;

    @FXML
    private Label labelCrisiIpertensiva;
    @FXML
    private TextField severitaCrisiIpertensiva;
    @FXML
    private TextField noteCrisiIpertensiva;

    String evento1, severita1, note1;
    String evento2, severita2, note2;
    String evento3, severita3, note3;
    String evento4, severita4, note4;
    String evento5, severita5, note5;
    String evento6, severita6, note6;

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
     * @throws IOException
     */
    public void to_01_LandingPage() throws IOException {
        ClientCVMain.setRoot("01_LandingPage");
    }

    /**
     * Questo metodo crea la schermata principale dell'applicazione cittadini 02CT_MainWindow
     * @throws IOException
     */
    public void to_02CT_MainWindow() throws IOException {
        ClientCVMain.setRoot("02CT_MainWindow");
    }

    /**
     * Questo metodo crea la schermata 03CT_RegistraCT, per la registrazione da parte del cittadino
     * @throws IOException
     */
    public void to_03CT_RegistraCT() throws IOException {
        ClientCVMain.setRoot("03CT_RegistrazioneAdCV");
    }

    /**
     * Questo metodo crea la schermata 03CT_EventiAvversiCT, per registrare eventi avversi da parte del cittadino
     * @throws IOException
     */
    public void to_03CT_EventiAvversiCT() throws IOException {
        ClientCVMain.setRoot("03CT_LoginWindow");
    }

    /**
     * Questo metodo crea la schermata 03CT_InfoCV, per visualizzare le informazioni dei centri vaccinali
     * @throws IOException
     */
    public void to_03CT_InfoCV() throws IOException {
        //ClientCVMain.setRoot("03CT_CercaCV");
        ClientCVMain.setRoot("03CT_CercaCV");
    }

    /**
     * uesto metodo crea la schermata 03CT_LoginWindow, per fare il login
     * @throws IOException
     */
    public void to_03CT_LoginWindow(ActionEvent actionEvent) throws IOException {
        ClientCVMain.setRoot("03CT_LoginWindow");
    }

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

    public void cercaCentroVaccinale(ActionEvent actionEvent) {

    }

    /**
     * Controlla e verifica il login: se l'utente si é registrato correttamente inserendo
     * username e password. In caso contrario, lo fará notare all'utente con una stringa di errore
     * @param actionEvent
     * @throws SQLException
     * @throws IOException
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
     * @param actionEvent
     * @throws SQLException
     * @throws RemoteException
     */
    public void inserisciEventiAvversi(ActionEvent actionEvent) throws SQLException, RemoteException {
        //evento1 = labelMalDiTesta.getText();
        severita1 = severitaMalDiTesta.getText();
        note1 = noteMalDiTesta.getText();

       //evento2 = labelFebbre.getText();
        severita2 = severitaFebbre.getText();
        note2 = noteFebbre.getText();

        //evento3 = labelDolori.getText();
        severita3 = severitaDolori.getText();
        note3 = noteDolori.getText();

        //evento4 = labelTachicardia.getText();
        severita4 = severitaTachicardia.getText();
        note4 = noteTachicardia.getText();

        //evento5 = labelLinfoadenopatia.getText();
        severita5 = severitaLinfoadenopatia.getText();
        note5 = noteLinfoadenopatia.getText();

        //evento6 = labelCrisiIpertensiva.getText();
        severita6 = severitaCrisiIpertensiva.getText();
        note6 = noteCrisiIpertensiva.getText();

        if (    (severita1.equals("")) || note1.equals("") ||
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

            /*
            if(evento1.equals("Mal di testa")) {
                Boolean verify = ClientCVController.stub.inserisciEventiAvversi("5f93b2ad-f2ee-442d-9c5b-d509c059562d", "PPPRRR98B10C933V", severita1, note1, null, null, null, null,null,null,null, null, null, null);
            }
            if(evento1.equals("Febbre")) {
                Boolean verify = ClientCVController.stub.inserisciEventiAvversi("5f93b2ad-f2ee-442d-9c5b-d509c059562d", "PPPRRR98B10C933V", null, null, severita1, note1, null, null,null,null,null, null, null, null);
            }
            if(evento1.equals("Dolori muscolari e articolari")) {
                Boolean verify = ClientCVController.stub.inserisciEventiAvversi("5f93b2ad-f2ee-442d-9c5b-d509c059562d", "PPPRRR98B10C933V", null, null, null, null, severita1, note1,null,null,null, null, null, null);
            }
            if(evento1.equals("Linfoadenopatia")) {
                Boolean verify = ClientCVController.stub.inserisciEventiAvversi("5f93b2ad-f2ee-442d-9c5b-d509c059562d", "PPPRRR98B10C933V", null, null, null, null, null, null, severita1, note1,null, null, null, null);
            }
            if(evento1.equals("Tachicardia")) {
                Boolean verify = ClientCVController.stub.inserisciEventiAvversi("5f93b2ad-f2ee-442d-9c5b-d509c059562d", "PPPRRR98B10C933V", null, null, null, null, null, null,null,null, severita1, note1, null, null);
            }
            if(evento1.equals("Crisi ipertensiva")) {
                Boolean verify = ClientCVController.stub.inserisciEventiAvversi("5f93b2ad-f2ee-442d-9c5b-d509c059562d", "PPPRRR98B10C933V", null, null, null, null, null, null,null,null,null, null, severita1, note1);
            }

             */
        }
    }
}//End_Class
