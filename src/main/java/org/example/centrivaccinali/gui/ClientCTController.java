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
import javafx.geometry.Pos;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static org.example.centrivaccinali.gui.IpServerCheck_Controller.stub;


/**
 * Classe controller di ClientCT
 */
public class ClientCTController  implements Initializable{

    //TextField relativi alla pagina: 03CT_LoginWindow
    @FXML
    private TextField TextFieldUsername;

    @FXML
    private PasswordField TextFieldPassword;

    /**
     * ComboBox per il nome del centro vaccinale
     */
    @FXML
    public ComboBox<String> nomeCentroComboBox = new ComboBox<>();

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
    private Label textFieldBenvenutoEventiAvversi = new Label();

    @FXML
    private Spinner<Integer> spinnerMalDiTesta = new Spinner<>();
    //inizializzazione dell'oggetto che modifica il valore dello spinner
    SpinnerValueFactory<Integer> spinnerValueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0);


    @FXML
    private Spinner<Integer> spinnerFebbre = new Spinner<>();
    //inizializzazione dell'oggetto che modifica il valore dello spinner
    SpinnerValueFactory<Integer> spinnerValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0);


    @FXML
    private Spinner<Integer> spinnerDoloriMuscolari = new Spinner<>();
    //inizializzazione dell'oggetto che modifica il valore dello spinner
    SpinnerValueFactory<Integer> spinnerValueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0);

    @FXML
    private Spinner<Integer> spinnerTachicardia = new Spinner<>();
    //inizializzazione dell'oggetto che modifica il valore dello spinner
    SpinnerValueFactory<Integer> spinnerValueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0);

    @FXML
    private Spinner<Integer> spinnerLinfoadenopatia = new Spinner<>();
    //inizializzazione dell'oggetto che modifica il valore dello spinner
    SpinnerValueFactory<Integer> spinnerValueFactory5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0);

    @FXML
    private Spinner<Integer> spinnerCrisiIpertensiva = new Spinner<>();
    //inizializzazione dell'oggetto che modifica il valore dello spinner
    SpinnerValueFactory<Integer> spinnerValueFactory6 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0);



    @FXML
    private TextArea noteMalDiTesta;

    @FXML
    private TextArea noteFebbre;

    @FXML
    private TextArea noteDolori;

    @FXML
    private TextArea noteTachicardia;

    @FXML
    private TextArea noteLinfoadenopatia;

    @FXML
    private TextArea noteCrisiIpertensiva;

    String note1;
    String note2;
    String note3;
    String note4;
    String note5;
    String note6;

    int severita1;
    int severita2;
    int severita3;
    int severita4;
    int severita5;
    int severita6;

    /**
     * Variabili locali per il salvataggio del cittadino che si é loggato
     */
    public static String copiaUsername, copiaPassword;


    String nomeRegistrato, cognomeRegistrato, cfRegistrato, emailRegistrato ,usernameRegistrato, passwordRegistrato,  idVaccinazioneRegistrato, nomeCentroVaccinale;

    /**
     * Questo metodo inizializza il combobox della finestra 03CT_RegistrazioneAdCV per inserire il nome del centro vaccinale
     *
     * @param arg0 arg0
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL arg0, ResourceBundle resourceBundle) {

        ////////////// COMBO BOX NOME CENTRO VACCINALE //////////////
        nomeCentroComboBox.setValue("");
        try {
            List<String> nomiCentriVaccinali = stub.nomiCentriVaccinali();
            for(String nome : nomiCentriVaccinali) {
                nomeCentroComboBox.getItems().add(nome);
            }
        } catch (RemoteException | SQLException e) {
            e.printStackTrace();
        }
        ////////////// SPINNER INSERIMENTO EVENTI AVVERSI CENTRO VACCINALE //////////////
        spinnerMalDiTesta.setValueFactory(spinnerValueFactory1);
        spinnerFebbre.setValueFactory(spinnerValueFactory2);
        spinnerDoloriMuscolari.setValueFactory(spinnerValueFactory3);
        spinnerTachicardia.setValueFactory(spinnerValueFactory4);
        spinnerLinfoadenopatia.setValueFactory(spinnerValueFactory5);
        spinnerCrisiIpertensiva.setValueFactory(spinnerValueFactory6);

        spinnerMalDiTesta.editorProperty().get().setAlignment(Pos.CENTER);
        spinnerFebbre.editorProperty().get().setAlignment(Pos.CENTER);
        spinnerDoloriMuscolari.editorProperty().get().setAlignment(Pos.CENTER);
        spinnerTachicardia.editorProperty().get().setAlignment(Pos.CENTER);
        spinnerLinfoadenopatia.editorProperty().get().setAlignment(Pos.CENTER);
        spinnerCrisiIpertensiva.editorProperty().get().setAlignment(Pos.CENTER);

        ////////////// INIZIALIZZAZIONE FINESTRA EVENTI AVVERSI CENTRO VACCINALE //////////////

        textFieldBenvenutoEventiAvversi.setText("Benvenuto: " + copiaUsername);

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
    public void guida() {
        Alert info = new Alert(Alert.AlertType.NONE,
                """
                        'Centro Vaccinale' - Client CV
                        """, ButtonType.OK);

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

                        BANCORA Davide
                        CASALNOVO Giacomo
                        DONATO Benedetta
                        DUBINI Emanuele

                        UnInsubria.it
                        Copyleft - all rights reversed""", ButtonType.OK);

        info.showAndWait();
    }


    /**
     * Questo metodo è collegato al bottone ' Registra Cittadino' dell'applicaizione cittadino
     * nel file "03CT_RegistrazioneAdCVold.fxml".
     *
     * Contiene dei controlli relativi a:
     * - Campi vuoti
     * - Codice Fiscale
     * - ID vaccinazione
     * - Email
     *
     * Resituisce un messaggio di errore nel caso in cui i campi inseriti dall'utente
     * non siano corretti
     *
     * @param actionEvent actionEvent
     *
     * @throws SQLException SQLException
     * @throws IOException IOException
     */
    public void GeneraCittadinoRegistrato(ActionEvent actionEvent) throws SQLException, IOException {
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
        else if(!(Pattern.matches("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", emailRegistrato))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("'Email' non valido.\nRiprovare");

            alert.showAndWait();
        }
        //verifica che l'utente che si sta registrando non sia gia presente nella relazione cittadini-registrati
        else if(!stub.verificaSeRegistrato(cfRegistrato, emailRegistrato, usernameRegistrato, idVaccinazioneRegistrato)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("Utente già presente nel sistema.\nRiprovare");

            alert.showAndWait();
        }
        //verificare se l'idVacinazione esiste ed è associato alla persona che si sta registrando
        else if(!stub.verificaIdVaccinazione(nomeRegistrato, cognomeRegistrato, cfRegistrato, idVaccinazioneRegistrato, nomeCentroVaccinale)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("Nessun id vaccinale o codice fiscale riscontrato nel centro vaccinale specificato.\nRiprovare");

            alert.showAndWait();
        }
        else {
            Boolean verify = stub.registraCittadino(cfRegistrato, cognomeRegistrato, nomeRegistrato, emailRegistrato, usernameRegistrato, passwordRegistrato, idVaccinazioneRegistrato, nomeCentroVaccinale);
            if(verify) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Info");
                alert.setHeaderText("Operazione effettuata correttamente");
                alert.setContentText("Ti sei registrato correttamente\n");
                resetInserimentoRegistrazione();
                alert.showAndWait();
                to_02CT_MainWindow();
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
     * Controlla e verifica il login: se l'utente si é registrato correttamente inserendo
     * username e password. In caso contrario, lo fará notare all'utente con una stringa di errore
     * @param actionEvent actionEvent
     * @throws SQLException SQLException
     * @throws IOException IOException
     */
    public void login(ActionEvent actionEvent) throws SQLException, IOException {
        String username = TextFieldUsername.getText();
        String password = TextFieldPassword.getText();

        Boolean verify = stub.login(username, password);

        if(verify) {
            resetInserimentoLogin();
            copiaUsername = username;
            copiaPassword = password;
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
     * @param actionEvent actionEvent
     * @throws SQLException SQLException
     * @throws RemoteException RemoteException
     */
    public void inserisciEventiAvversi(ActionEvent actionEvent) throws SQLException, IOException {

        severita1 = spinnerMalDiTesta.getValue();
        note1 = noteMalDiTesta.getText();

        severita2 = spinnerFebbre.getValue();
        note2 = noteFebbre.getText();

        severita3 = spinnerDoloriMuscolari.getValue();
        note3 = noteDolori.getText();

        severita4 = spinnerTachicardia.getValue();
        note4 = noteTachicardia.getText();

        severita5 = spinnerLinfoadenopatia.getValue();
        note5 = noteLinfoadenopatia.getText();

        severita6 = spinnerCrisiIpertensiva.getValue();
        note6 = noteCrisiIpertensiva.getText();


        if ((severita1 > 5) || (severita2 > 5) ||
            (severita3 > 5) || (severita4 > 5) ||
            (severita5 > 5) || (severita6 > 5)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("Dati non validi.\nRiprovare");

            alert.showAndWait();
        } //END_if*/
        if (note1.length() > 256 || note2.length() > 256 ||
                note3.length() > 256 || note4.length() > 256 ||
                note5.length() > 256 || note6.length() > 256) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("Hai inserito troppi caratteri nelle note.\nMassimo 256 caratteri per nota");

            alert.showAndWait();
        }  else {
            //Ottiene idCentroVaccinale in base a username e psw inseriti nella schermata di login
            //DEBUG System.out.println(copiaPassword + " " + copiaPassword);
            String idCentroVaccinale = stub.getIdCentroVaccinale(copiaUsername, copiaPassword);
            //DEBUG System.err.println(idCentroVaccinale);

            //Ottiene codice fiscale in base a username e psw inseriti nella schermata di login
            String codiceF = stub.getCodiceFiscale(copiaUsername, copiaPassword);
            //DEBUG System.err.println(codiceF);

            if(stub.verificaEventoAvverso(codiceF)) {
                stub.inserisciEventiAvversi(idCentroVaccinale, codiceF, severita1, note1, severita2, note2, severita3, note3, severita4, note4, severita5, note5, severita6, note6);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Info");
                alert.setHeaderText("Operazione effettuata correttamente");
                alert.setContentText("Evento avverso inserito correttamente\n");
                resetInserimentoEventiAvversi();
                alert.showAndWait();
                to_02CT_MainWindow();

                //DEBUG System.out.println("Ok, l'evento è stato inserito");

            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Si è verificato un Errore");
                alert.setContentText("Hai già inserito gli eventi avversi\n");

                alert.showAndWait();
                to_02CT_MainWindow();
            }
        }
    }//end_inserisci eventi avversi

    /**
     * Resetta tutti i campi degli eventi avversi
     */
    private void resetInserimentoEventiAvversi() {
        //spinnerMalDiTesta.setValueFactory(spinnerValueFactory1);
        noteMalDiTesta.setText("");

        //spinnerFebbre.setValueFactory(spinnerValueFactory2);
        noteFebbre.setText("");

        //spinnerDoloriMuscolari.setValueFactory(spinnerValueFactory3);
        noteDolori.setText("");

        //spinnerTachicardia.setValueFactory(spinnerValueFactory4);
        noteTachicardia.setText("");

        //spinnerLinfoadenopatia.setValueFactory(spinnerValueFactory5);
        noteLinfoadenopatia.setText("");

        //spinnerCrisiIpertensiva.setValueFactory(spinnerValueFactory6);
        noteCrisiIpertensiva.setText("");
    }
}//End_Class
