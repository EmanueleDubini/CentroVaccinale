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
import org.example.common.CFGenerator.CalcolaCodiceFiscale;
import org.example.common.ProgUtili;
import org.example.database.GenerateDataLib.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

import static org.example.centrivaccinali.gui.IpServerCheck_Controller.stub;

/**
 * Classe controller di ClientCV
 */
public class ClientCVController implements Initializable {

    /**
     * Combobox relativa al tipo di centro vaccinale
     */
    @FXML
    public ComboBox<String> tipologiaCheckBox = new ComboBox<>();
    /**
     * ComboBox qualificatore indirizzo
     */
    @FXML
    public ComboBox<String> qualificatoreIndirizzoCheckBox = new ComboBox<>();
    /**
     * TextField nome del centro vaccinale
     */
    @FXML
    public TextField textFieldNomeCentrovaccinale;
    /**
     * TextField nome via
     */
    @FXML
    public TextField textFieldNomeVia;
    /**
     * TextField Numero civico
     */
    @FXML
    public TextField textFieldNumeroCivico;
    /**
     * TextField comune
     */
    @FXML
    public TextField textFieldComune;
    /**
     * TextField Provincia
     */
    @FXML
    public TextField textFieldProvincia;
    /**
     * TextField cap
     */
    @FXML
    public TextField textFieldCap;
    /**
     * Combobox relativa al tipo di vaccino somministrato al cittadino
     */
    @FXML
    public ComboBox<String> TipologiaVaccinoCheckBox = new ComboBox<>();


    //Bottoni relativi alla pagina: 03CV_RegistraCT

    /**
     * TextField nome del cittadino vaccinato
     */
    @FXML
    public TextField TextFieldNomeVaccinatoCT;
    /**
     * TextField cognome del cittadino vaccinato
     */
    @FXML
    public TextField TextFieldCognomeVaccinatoCT;
    /**
     * TextField per il codice fiscale
     */
    @FXML
    public TextField TextFieldCodicefiscaleCT;
    /**
     * TextField id Vaccinazione del cittadino vaccinato 
     */
    @FXML
    public TextField TextFieldIdVaccinazioneCT;
    /**
     * DatePicker data somministrazione vaccino
     */
    @FXML
    public DatePicker DatePickerSomministrazioneCT;
    /**
     * ComboBox per il nome del centro vaccinale
     */
    @FXML
    public ComboBox<String> nomeCentroComboBox = new ComboBox<>();

    String nomeCvCT, nomeCT, cognomeCT, codiceFiscaleCT, vaccinoSomministratoCT, idVaccinazioneCT, dataVaccinazioneCV;

    private final ClientCVMain m = new ClientCVMain();

    String nomeCV, tipologiaCV, qualificatoreVia, nomeVia, numeroCivico, comune, provincia, cap;

    /**
     * Controller relativo ai centri vaccinali
     */
    public ClientCVController(){
    }

    /**
     * Questo metodo inizializza tutti i combobox presenti nelle varie finestre
     *
     * @param arg0 ereditato da superclasse
     * @param resourceBundle ereditato da superclasse
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

    }

    /**
     * Questo metodo permette di spostarsi tra le varie finestre
     *
     * @throws IOException IOException
     */
    public void to_01_LandingPage() throws IOException {
        ClientCVMain.setRoot("01_LandingPage");
    }

    /**
     * Questo metodo permette di spostarsi tra le varie finestre
     *
     * @throws IOException IOException
     */
    public void to_02CT_MainWindow() throws IOException {
        ClientCVMain.setRoot("02CT_MainWindow");
    }

    /**
     * Questo metodo permette di spostarsi tra le varie finestre
     *
     * @throws IOException IOException
     */
    public void to_02CV_MainWindow() throws IOException {
        ClientCVMain.setRoot("02CV_MainWindow");
    }

    /**
     * Questo metodo permette di spostarsi tra le varie finestre
     *
     * @throws IOException IOException
     */
    public void to_03CV_RegistraCV() throws IOException {
        ClientCVMain.setRoot("03CV_RegistraCV");
    }

    /**
     * Metodo per la navigazione all'interno dell'applicazione
     *
     * @throws IOException IOException
     */
    public void to_03CV_RegistraCT() throws IOException {
        ClientCVMain.setRoot("03CV_RegistraCT");
    }


    /////////////////////// metodi di controllo menu bar ///////////////////////

    /**
     * Questo metodo, situatosi nella menu bar, permette l'uscita dalla finestra
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
     * Questo metodo è collegato al Bottone "Registra Centro Vaccinale" nel file:
     * "03CV_RegistraCTOLD.fxml"
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
     * @throws RemoteException RemoteException
     * @throws SQLException SQLException
     */

    public void generaCentroVaccinale() throws RemoteException, SQLException {
        ////////////// campi registrazione CV //////////////
        nomeCV = textFieldNomeCentrovaccinale.getText().toLowerCase().strip(); //non richiede controlli
        tipologiaCV = tipologiaCheckBox.getValue(); //non richiede controlli
        qualificatoreVia = qualificatoreIndirizzoCheckBox.getValue(); //non richiede controlli
        nomeVia = textFieldNomeVia.getText().strip();
        numeroCivico = textFieldNumeroCivico.getText();
        comune = textFieldComune.getText().toLowerCase().strip();  //corregge il fatto che magari venga inserito 'cErMeNate' e diventa 'cermenate'
        provincia = textFieldProvincia.getText().toUpperCase().strip(); //se viene scritto il cap minusciolo viene letto come maiuscolo
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
        //controllo validità nome centro vaccinale
        else if(nomeCV.length() < 3){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("'Nome Centro Vaccinale' minore di 3 caratteri.\nRiprovare");

            alert.showAndWait();
        }

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
            Boolean verify = stub.registraCentroVaccinale(id, nomeCV, qualificatoreVia , nomeVia, numeroCivico, ProgUtili.capitalize(comune), provincia, cap, tipologiaCV);

            if (verify) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Info");
                alert.setHeaderText("Operazione effettuata correttamente");
                alert.setContentText("Centro Vaccinale registrato correttamente\n");
                resetInserimentoCV();
                alert.showAndWait();
            }
        }
        //todo, prima di inserire nel database il centro vaccinale, fare una lettura da db con query per evitare che il cv sia già stato registrato

    }

    /**
     * Questo metodo resetta gli inserimenti.
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
     * "03CV_RegistraCTOLD.fxml"
     *
     *  contiene dei controlli relativi a:
     *- Campi vuoti
     *- Validità del codice fiscale
     *- Validità id Vaccinazione (Lunghezza 16 bit)
     *- Validità della data inserita
     *
     * Resituisce un messaggio di errore nel caso in cui i campi inseriti dall'operatore
     * non siano corretti
     *
     * @throws SQLException SQLException
     * @throws RemoteException RemoteException
     */

    public void generaCittadinoVaccinato() throws SQLException, RemoteException {
        ////////////// CAMPI REGISTRAZIONE CT //////////////

        //nomeCvCT = TextFieldNomeCentrovaccinaleCT.getText().toLowerCase().strip();
        nomeCvCT = nomeCentroComboBox.getValue();
        nomeCT = TextFieldNomeVaccinatoCT.getText().strip();    //non servono controlli
        cognomeCT = TextFieldCognomeVaccinatoCT.getText().strip();  //non servono controlli
        codiceFiscaleCT = TextFieldCodicefiscaleCT.getText().toUpperCase().strip();             //Strip() rimuove spazi all'inizio e alla fine del testo inserito
        vaccinoSomministratoCT = TipologiaVaccinoCheckBox.getValue();      //non servono controlli
        idVaccinazioneCT =  TextFieldIdVaccinazioneCT.getText().strip();
        String idCentroVaccinale = stub.getIdCentroVaccianlePerCV(nomeCvCT);

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

        else if (DatePickerSomministrazioneCT.getValue() == null | Objects.requireNonNull(DatePickerSomministrazioneCT.getValue()).isAfter(LocalDate.now())) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("'Data' non valida.\nRiprovare");

            alert.showAndWait();

        } else if (idCentroVaccinale.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Si è verificato un Errore");
            alert.setContentText("Nome centro vaccinale non valido.\nRiprovare");

            alert.showAndWait();

        } else {

            dataVaccinazioneCV = selectDateCV();

            Boolean verify = stub.registraVaccinato(idCentroVaccinale, nomeCvCT, cognomeCT, nomeCT, codiceFiscaleCT, dataVaccinazioneCV, vaccinoSomministratoCT, idVaccinazioneCT);
            if (verify) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Info");
                alert.setHeaderText("Operazione effettuata correttamente");
                alert.setContentText("Cittadino registrato correttamente\n");
                resetInserimentoCT();

                alert.showAndWait();
            }
        }

        //todo nel caso verificare se aggiungere controllo per la data

    }

    /**
     * Questo metodo resetta tutti i campi inseriti dall'utente dopo che si registra il cittadino vaccinato
     * tranne la data //todo la data lancia eccezione
     */

    private void resetInserimentoCT() {
        nomeCentroComboBox.setValue("");
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
     * Questo metodo controlla che il cap inserito dall'utente esiste e che sia corretto
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

    /**
     * Questo metodo legge la data inserita dall'utente e ne cambia il formato in dd-MM-yyyy
     *
     * @return una stringa contente la data scelta dall'utente nel formato dd-MM-yyyy
     */
    public String selectDateCV() {
        return DatePickerSomministrazioneCT.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString();
        //todo benny
    }

    //Metodi di debug relativi al file "03CV_RegistraCTOLD.fxml"

    /**
     * Questo metodo permette di inserire il codice fiscale in fase di debug
     *
     * @param actionEvent actionEvent
     */

    public void inserisciCodiceFiscaleCT_Debug(ActionEvent actionEvent) {
        Random randomGenerator = new Random();

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
        CalcolaCodiceFiscale calcolaCodiceFiscale = new CalcolaCodiceFiscale();
        String codiceFiscale = CalcolaCodiceFiscale.calcolaCf(cognome, nome, sesso, dateGenerator.generate(), calcolaCodiceFiscale.toCodiceErariale((String)comuneGen.generate()));
        TextFieldCodicefiscaleCT.setText(codiceFiscale);
    }

    /**
     * Questo metodo permette di generare in modo casuale gli ID Univoc
     *
     * @param actionEvent actionEvent
     */
    public void generaIdCasuale(ActionEvent actionEvent) {
        UIDGenerator uidGenerator = new UIDGenerator();
        String idVaccinazione = uidGenerator.randomUUID(16, 17, '-', true);
        TextFieldIdVaccinazioneCT.setText(idVaccinazione);
    }
}//End_Class



