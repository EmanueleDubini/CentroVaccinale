package org.example.centrivaccinali.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.serverCV.ServerCV;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class ClientCTController {

    @FXML
    private TextField TextFieldUsername;

    @FXML
    private PasswordField TextFieldPassword;

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

    String nomeRegistrato, cognomeRegistrato, cfRegistrato, emailRegistrato ,usernameRegistrato, passwordRegistrato,  idVaccinazioneRegistrato;
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
    public void GeneraCittadinoRegistrato(ActionEvent actionEvent) {
        ////////////// campi registrazione Cittadino //////////////
        nomeRegistrato = TextFieldNomeVaccinato.getText().strip();
        cognomeRegistrato = TextFieldCognomeVaccinato.getText().strip();
        cfRegistrato = TextFieldCFVaccinato.getText().toUpperCase().strip();
        emailRegistrato = TextFieldEmailVaccinato.getText().strip();
        usernameRegistrato = TextFieldUsernameVaccinato.getText().strip();
        passwordRegistrato = TextFieldPasswordVaccinato.getText().strip();
        idVaccinazioneRegistrato = TextFieldIdVaccinazioneVaccinato.getText().strip();

        //controllo se tutti i campi sono vuoti
        if ((nomeRegistrato.equals("") ||
                cognomeRegistrato.equals("")) ||
                cfRegistrato.equals("") ||
                emailRegistrato.equals("") ||
                usernameRegistrato.equals("") ||
                passwordRegistrato.equals("") ||
                idVaccinazioneRegistrato.equals("")) {

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
        }

        else {
            System.out.print("Nome: " + nomeRegistrato + " Cognome: " + cognomeRegistrato + " CF: " + cfRegistrato + " Email:" + emailRegistrato + " Username: " + usernameRegistrato + " Password: " + passwordRegistrato + " IDVaccinazione: " + idVaccinazioneRegistrato);

            //todo aggiungere collegamento con il DB

            Boolean verify = true;

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
    }

    public void cercaCentroVaccinale(ActionEvent actionEvent) {

    }

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
        }
    }

    private void resetInserimentoLogin() {
        TextFieldUsername.setText("");
        TextFieldPassword.setText("");
    }


    public void inserisciEventiAvversi(ActionEvent actionEvent) {
        //todo prelevare da interfaccia grafica
        /*
        Boolean verify = ClientCVController.stub.inserisciEventiAvversi(String id, String codiceFiscale, String mal_di_testa, String mal_di_testa_note,
                                                                        String febbre, String febbre_note,
                                                                        String dolori_muscolari_e_articolari, String dolori_muscolari_e_articolari_note,
                                                                        String linfoaenopatia, String linfoaenopatia_note,
                                                                        String tachicardia, String tachicardia_note,
                                                                        String crisi_ipertensiva, String crisi_ipertensiva_note);
        if (verify) {

        }

         */
    }


}
