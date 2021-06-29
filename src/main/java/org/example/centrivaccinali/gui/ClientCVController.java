/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.centrivaccinali.gui;

import org.example.common.CFGenerator.CalcolaCodiceFiscale;
import org.example.database.GenerateDataLib.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.serverCV.ServerCVI;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 *
 * Classe controller di ClientCV
 *
 */
public class ClientCVController implements Initializable {
    private final long serialVersionUID = 1L;

    private final int PORT = 1200;



    @FXML
    public ComboBox<String> tipologiaCheckBox = new ComboBox();
    @FXML
    public ComboBox<String> qualificatoreIndirizzoCheckBox = new ComboBox();
    @FXML
    public TextField textFieldNomeCentrovaccinale;
    @FXML
    public TextField textFieldNomeVia;
    @FXML
    public TextField textFieldNumeroCivico;
    @FXML
    public TextField textFieldComune;
    @FXML
    public TextField textFieldProvincia;
    @FXML
    public TextField textFieldCap;
    public ComboBox<String> QualificatoreIndirizzoCheckBox = new ComboBox();

    @FXML
    public ComboBox<String> TipologiaVaccinoCheckBox = new ComboBox();


    //Bottoni relativi alla pagina: 03CV_RegistraCT

    @FXML
    public TextField TextFieldNomeCentrovaccinaleCT;
    @FXML
    public TextField TextFieldNomeVaccinatoCT;
    @FXML
    public TextField TextFieldCognomeVaccinatoCT;
    @FXML
    public TextField TextFieldCodicefiscaleCT;
    @FXML
    public TextField TextFieldIdVaccinazioneCT;

    @FXML
    public DatePicker DatePickerSomministrazioneCT;


    String nomeCvCT, nomeCT, cognomeCT, codiceFiscaleCT, vaccinoSomministratoCT, idVaccinazioneCT, dataVaccinazioneCV;


    private final ClientCVMain m = new ClientCVMain();

    String nomeCV, tipologiaCV, qualificatoreVia, nomeVia, numeroCivico, comune, provincia, cap;

    //grafica relativa alla pagina: 00_IpServerCheck.fxml
    @FXML
    public Label connectionStatus;
    @FXML
    public TextField hostAddress;
    String address;

    //istanziazione registry
    Registry registry;
    public static ServerCVI stub;

    public ClientCVController() throws IOException {
    }

    /**
     * Questo metodo inizializza tutti i combobox presenti nelle varie finestre
     * @param arg0 //todo
     * @param resourceBundle //todo
     */
    @Override
    public void initialize(URL arg0, ResourceBundle resourceBundle) {
        ////////////// COMBO BOX TIPOLOGIA //////////////
        tipologiaCheckBox.setValue("");

        tipologiaCheckBox.getItems().add("Ospedaliero");
        tipologiaCheckBox.getItems().add("Aziendale");
        tipologiaCheckBox.getItems().add("Hub");

        ////////////// COMBO BOX QUALIFICATORE INDIRIZZO //////////////
        qualificatoreIndirizzoCheckBox.setValue("");

        qualificatoreIndirizzoCheckBox.getItems().add("Via");
        qualificatoreIndirizzoCheckBox.getItems().add("Viale");
        qualificatoreIndirizzoCheckBox.getItems().add("Piazza");
        qualificatoreIndirizzoCheckBox.getItems().add("Piazzale");
        qualificatoreIndirizzoCheckBox.getItems().add("Largo");
        qualificatoreIndirizzoCheckBox.getItems().add("Vicolo");
        qualificatoreIndirizzoCheckBox.getItems().add("Corso");

        ////////////// COMBO BOX VACCINO //////////////
        TipologiaVaccinoCheckBox.setValue("");
        TipologiaVaccinoCheckBox.getItems().add("AstraZeneca");
        TipologiaVaccinoCheckBox.getItems().add("Johnson & Johnson");
        TipologiaVaccinoCheckBox.getItems().add("Moderna");
        TipologiaVaccinoCheckBox.getItems().add("Pfizer");

    }

    /**
     * Questi metodi permettono di spostarsi tra le varie finestre.
     * @throws IOException
     */
    public void to_01_LandingPage() throws IOException {
        ClientCVMain.setRoot("01_LandingPage");
    }

    //todo
    public void to_02CT_MainWindow() throws IOException {
        ClientCVMain.setRoot("02CT_MainWindow");
    }

    public void to_02CV_MainWindow() throws IOException {
        ClientCVMain.setRoot("02CV_MainWindow");
    }

    public void to_03CV_RegistraCV() throws IOException {
        ClientCVMain.setRoot("03CV_RegistraCV");
    }

    public void to_03CV_RegistraCT() throws IOException {
        ClientCVMain.setRoot("03CV_RegistraCT");
    }



    /////////////////////// metodi di controllo menu bar ///////////////////////
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
     * Questo metodo è collegato al Bottone "Registra Centro Vaccinale" nel file:
     * "03CV_RegistraCT.fxml"
     *
     * contiene dei controlli relativi a:
     * - Campi vuoti
     * - Validità nome via
     * - Validità numero via
     * - Validità comune
     * - Validità provincia
     * - Validità cap
     *
     * Resituisce un messaggio di errore nel caso in cui i campi inseriti dall'operatore
     * non siano corretti
     *
     * @throws RemoteException
     * @throws NotBoundException
     * @throws SQLException
     */

    public void generaCentroVaccinale() throws RemoteException, SQLException {
        ////////////// campi registrazione CV //////////////
        nomeCV = textFieldNomeCentrovaccinale.getText(); //non richiede controlli
        tipologiaCV = tipologiaCheckBox.getValue(); //non richiede controlli
        qualificatoreVia = qualificatoreIndirizzoCheckBox.getValue(); //non richiede controlli
        nomeVia = textFieldNomeVia.getText();
        numeroCivico = textFieldNumeroCivico.getText();
        comune = textFieldComune.getText().toLowerCase();  //corregge il fatto che magari venga inserito 'cErMeNate' e diventa 'cermenate' //todo scegliere come fare il controllo sulle maiuscole prima di inserire nel db
        provincia = textFieldProvincia.getText().toUpperCase(); //se viene scritto il cap minusciolo viene letto come maiuscolo
        cap = textFieldCap.getText();


        //controllo se tutti i campi sono vuoti
        if ((nomeCV.equals("") ||
                tipologiaCV.equals("")) ||
                qualificatoreVia.equals("") ||
                nomeVia.equals("") ||
                numeroCivico.equals("") ||
                comune.equals("") ||
                provincia.equals("") ||
                cap.equals("")) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("Campi utili all'inserimento del Centro Vaccinale mancanti.\nRiprovare");

            alert.showAndWait();
        } //END_if

        //controllo validità nome via
        else if (!(Pattern.matches("[a-zA-Zàèìòù.\\s]{0,30}[0-9]{0}", nomeVia))) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("'Nome Via' non valido.\nRiprovare");

            alert.showAndWait();
        }

        //controllo validità numero via
        else if (!(Pattern.matches("^[0-9]{1,3}([a-z]?)$", numeroCivico))) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("'Numero Civico' non valido.\nRiprovare");

            alert.showAndWait();
        }

        //controllo validità comune
        else if (!validitaComune(comune)) { //se il metodo restituisce true vuol dire che ah trovato il comune e quindi è stato inserito regolarmente

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("'Comune' non valido.\nRiprovare");

            alert.showAndWait();
        }

        //controllo validità provincia
        else if (!validitaProvincia(provincia)) {//todo nella verifica controlare che la provincia corrisponda al comune scritto dall'utente

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("'Provincia' non valida.\nRiprovare");

            alert.showAndWait();
        }

        //controllo validità cap
        else if (!validitaCap(cap)) { //todo nella verifica controlare che il cap corrisponda al comune e provincia scritto dall'utente

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("'CAP' non valido.\nRiprovare");

            alert.showAndWait();
        } else {


            String id = UUID.randomUUID().toString();
            Boolean verify = stub.registraCentroVaccinale(id, nomeCV, qualificatoreVia , nomeVia, numeroCivico, comune, provincia, cap, tipologiaCV);
            if (verify) {
                resetInserimentoCV();
            }
        }
        //todo, prima di inserire nel database il centro vaccinale, fare una lettura da db con query per evitare che il cv sia già stato registrato

    }

    /**
     * //Questo metodo resetta gli inserimenti.
     */

    private void resetInserimentoCV() {
        textFieldNomeCentrovaccinale.setText("");
        tipologiaCheckBox.setValue("");
        qualificatoreIndirizzoCheckBox.setValue("");
        textFieldNomeVia.setText("");
        textFieldNumeroCivico.setText("");
        textFieldComune.setText("");
        textFieldProvincia.setText("");
        textFieldCap.setText("");
    }


    /**
     * Questo metodo è collegato al Bottone "Registra Centro Vaccinale" nel file:
     * "03CV_RegistraCT.fxml"
     *
     *  contiene dei controlli relativi a:
     *- Campi vuoti
     *- Validità del codice fiscale
     *- Validità id Vaccinazione (Lunghezza 16 bit)
     *- Validità della data inserita
     *
     * Resituisce un messaggio di errore nel caso in cui i campi inseriti dall'operatore
     * non siano corretti
     */

    public void generaCittadinoVaccinato() throws SQLException, RemoteException {
        ////////////// CAMPI REGISTRAZIONE CT //////////////

        nomeCvCT = TextFieldNomeCentrovaccinaleCT.getText();    //todo implementare controllo per verificare se esiste il centro vaccinale (nel DB) inserito dall'utente
        nomeCT = TextFieldNomeVaccinatoCT.getText();    //non servono controlli
        cognomeCT = TextFieldCognomeVaccinatoCT.getText();  //non servono controlli
        codiceFiscaleCT = TextFieldCodicefiscaleCT.getText().toUpperCase().strip();             //Strip() rimuove spazi all'inizio e alla fine del testo inserito
        vaccinoSomministratoCT = TipologiaVaccinoCheckBox.getValue();      //non servono controlli
        idVaccinazioneCT =  TextFieldIdVaccinazioneCT.getText().strip();

        //Controlliamo se i campi sono vuoti
        if ((nomeCvCT.equals("") ||
                nomeCT.equals("")) ||
                cognomeCT.equals("") ||
                codiceFiscaleCT.equals("") ||
                vaccinoSomministratoCT.equals("") ||
                DatePickerSomministrazioneCT.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("Campi utili all'inserimento del Cittadino vaccinato mancanti.\nRiprovare");

            alert.showAndWait();
        } //END_if

        else if(!(Pattern.matches("[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]", codiceFiscaleCT))){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("'Codice Fiscale' non valido.\nRiprovare");

            alert.showAndWait();
        }

        else if(idVaccinazioneCT.length() != 16){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("'ID Vaccinazione' non valido.\nRiprovare");

            alert.showAndWait();
        }

        else if (DatePickerSomministrazioneCT.getValue() == null) {   //todo sistemare il controllo data

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("'Data' non valida.\nRiprovare");

            alert.showAndWait();

        } else {

            dataVaccinazioneCV = selectDateCV();

            String id = UUID.randomUUID().toString();
            Boolean verify = stub.registraVaccinato(id, nomeCvCT, cognomeCT, nomeCT, codiceFiscaleCT, dataVaccinazioneCV, vaccinoSomministratoCT, idVaccinazioneCT);
            if (verify) {
                resetInserimentoCT();
            }
        }

        //todo nel caso verificare se aggiungere controllo per la data

    }

    /**
     * Questo metodo resetta tutti i campi inseriti dall'utente dopo che si registra il cittadino vaccinato
     * tranne la data //todo la data lancia eccezione
     */

    private void resetInserimentoCT() {
        TextFieldNomeCentrovaccinaleCT.setText("");
        TextFieldNomeVaccinatoCT.setText("");
        TextFieldCognomeVaccinatoCT.setText("");
        TextFieldCodicefiscaleCT.setText("");
        TipologiaVaccinoCheckBox.setValue("");
        TextFieldIdVaccinazioneCT.setText("");
    }



    /**
     * Questo metodo controlla che il comune inserito dall'utente esiste e che sia corretto
     *
     * @param comune da verificarne la validità
     * @return true nel caso in cui il comune sia corretto, false altrimenti
     */

    private Boolean validitaComune(String comune) {
        String letturaFile;

        //File file = new File("src/main/java/org/example/common/CFGenerator/listacomuni.csv");
        try (
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(trovaFileListaComuni()))) {
                 //Scanner scanner = new Scanner(file)) {

                while ((letturaFile = bufferedReader.readLine()) != null) { //leggo una riga alla volta scorrendo il file
                    String[] chiaveValore = letturaFile.split(","); // spezzo la stringa letta in due

                    //chiaveValore[0] = Provincia, chiaveValore[1] = comune, chiavevalore[2] = CAP

                    if (chiaveValore[1].equalsIgnoreCase(comune)) { //metodo che ignora il fatto che una stringa abbia maiuscole o minuscole diverse da un altra, guarda i caratteri unicode e la lunghezza della stringa per dire se sia uguale ad un altra
                        return true; //comune trovato
                    }
                }
            }
        catch (Exception e) {
            System.err.println("File: listacomuni.csv letto in modo errato da verificaComune(String Comune)");
            e.printStackTrace();
        }
        return false; //comune non trovato
    }

    /**
     * Questo metodo controlla che la provincia inserita dall'utente esiste e che sia corretta
     *
     * @param provincia da verificarne la validità
     * @return true nel caso in cui il comune sia corretto, false altrimenti
     */

    private Boolean validitaProvincia(String provincia) {
        String letturaFile;

        //File file = new File("src/main/java/org/example/common/CFGenerator/listacomuni.csv");
        try (
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(trovaFileListaComuni()))) {
            //Scanner scanner = new Scanner(file)) {
            while ((letturaFile = bufferedReader.readLine()) != null) { //leggo una riga alla volta scorrendo il file
                String[] chiaveValore = letturaFile.split(","); // spezzo la stringa letta in due

                //chiaveValore[0] = Provincia, chiaveValore[1] = comune, chiavevalore[2] = CAP

                if (chiaveValore[0].equalsIgnoreCase(provincia)) { //metodo che ignora il fatto che una stringa abbia maiuscole o minuscole diverse da un altra, guarda i caratteri unicode e la lunghezza della stringa per dire se sia uguale ad un altra
                    return true; //provincia trovata
                }
            }
        } catch (Exception e) {
            System.err.println("File: listacomuni.csv letto in modo errato da verificaProvincia(String provincia)");
            e.printStackTrace();
        }
        return false; //provincia non trovata
    }


    /**
     * Questo metodo controlla che il cap inserito dall'utente esiste e che sia corretto     *
     *
     * @param cap da verificarne la validità
     * @return true nel caso in cui il comune sia corretto, false altrimenti
     */

    private Boolean validitaCap(String cap) {
        String letturaFile;

        //File file = new File("src/main/java/org/example/common/CFGenerator/listacomuni.csv");
        try(
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(trovaFileListaComuni()))) {
                //Scanner scanner = new Scanner(file)) {
            while ((letturaFile = bufferedReader.readLine()) != null) { //leggo una riga alla volta scorrendo il file
                String[] chiaveValore = letturaFile.split(","); // spezzo la stringa letta in due

                //chiaveValore[0] = Provincia, chiaveValore[1] = comune, chiavevalore[2] = CAP

                if(chiaveValore[2].equalsIgnoreCase(cap)){ //metodo che ignora il fatto che una stringa abbia maiuscole o minuscole diverse da un altra, guarda i caratteri unicode e la lunghezza della stringa per dire se sia uguale ad un altra
                    return true; //cap trovato
                }
            }
        } catch (Exception e) {
            System.err.println("File: listacomuni.csv letto in modo errato da verificaProvincia(String provincia)");
            e.printStackTrace();
        }
        return false; //cap non trovato
    }

    /**
     * Metodo che restituisce un InputStream associato al file 'listacomuni.csv' utilizzato per verificare la validità del comune, provincia , cap inseriti
     * dall'utente nella finestra '03CV_RegistraCV.fxml'
     *
     * @return InputStream associato al file 'listacomuni.csv'
     */

    private InputStream trovaFileListaComuni() {
        return getClass().getResourceAsStream("listacomuni.csv");
    }

    //Questo metodo seleziona e ritorna una stringa contenete la data della somministrazione del vaccino al cittadino nella finestra 03CV_RegistraCT

    //todo


    /**
     * Questo metodo legge la data inserita dall'utente e ne cambia il formato in dd-MM-yyyy
     *
     * @return una stringa contente la data scelta dall'utente nel formato dd-MM-yyyy
     */
    public String selectDateCV() {
        return DatePickerSomministrazioneCT.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString();
        //todo benny
    }

    //Metodi di debug relativi al file "03CV_RegistraCT.fxml"

    public void inserisciCodiceFiscaleCT_Debug(ActionEvent actionEvent) {
        Random randomGenerator = new Random();
        int eta = randomGenerator.nextInt(30) + 20;

        LastNameGenerator lastNameGenerator = new LastNameGenerator();
        String cognome = (String)lastNameGenerator.generate();

        String nome, sesso;
        FemaleNameGenerator femaleNameGenerator = new FemaleNameGenerator();
        MaleNameGenerator maleNameGenerator = new MaleNameGenerator();
        if (randomGenerator.nextInt(100) > 65) {
            nome = (String)maleNameGenerator.generate();
            sesso = "m";
        } else {
            nome = (String)femaleNameGenerator.generate();
            sesso = "f";
        }

        ComuneGenerator comuneGen = new ComuneGenerator();
        DateGenerator dateGenerator = new DateGenerator();
        String codiceFiscale = CalcolaCodiceFiscale.calcolaCf(cognome, nome, sesso, dateGenerator.generate(), CalcolaCodiceFiscale.toCodiceErariale((String)comuneGen.generate()));
        TextFieldCodicefiscaleCT.setText(codiceFiscale);
    }

    public void inserisciIDVaccinazioneCT_Debug(ActionEvent actionEvent) {
        UIDGenerator uidGenerator = new UIDGenerator();
        String idVaccinazione = uidGenerator.randomUUID(16, 17, '-', true);
        TextFieldIdVaccinazioneCT.setText(idVaccinazione);
    }

    /////////////////////grafica relativa alla pagina: 00_IpServerCheck.fxml //////////////////////////////////

    public void serverConnection() {
        address = hostAddress.getText().strip();

        if (address.equals("")) {
            connectionStatus.setText("indirizzo IP server non inserito");
        }
        else if (!(Pattern.matches("^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$", address))){ //verifica correttezza sintassi indirizzo ip
            connectionStatus.setText("indirizzo IP server errato");
        }
        else {
            try {
                registry = LocateRegistry.getRegistry(address, PORT);

            //richiesta dell'oggeto server remoto inserito precedenemente dal serverCV nel registry
                stub = (ServerCVI) registry.lookup("ServerCV");
                to_01_LandingPage();
            } catch (Exception e) {
                e.printStackTrace();
                connectionStatus.setText("indirizzo IP server errato");
                //todo se il server non è in esecuzione il metodo "stub = (ServerCVI) registry.lookup("ServerCV");"
                //da eccezione e viene stampato che è inserito un indirizzo Ip errato al posto di segnalare
                //che il server non è in esecuzione
            }
        }
    }//END_serverConnection

}//End_Class



